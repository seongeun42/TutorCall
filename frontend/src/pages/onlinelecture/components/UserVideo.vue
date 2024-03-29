<script setup lang="ts">
import OnlineVideo from './OnlineVideo.vue'
import { ref, onMounted, onBeforeUnmount, watch, computed } from 'vue'
import { OpenVidu, StreamManager, Session, Publisher, Subscriber } from 'openvidu-browser'
import type { Ref } from 'vue'
import { useVideoStore } from '@/store/videoStore'
import { useUserStore } from '@/store/userStore'
import { useNotificationStore } from '@/store/notificationStore'
import axios from 'axios'
import { useRouter } from 'vue-router'
import type { ComputedRef } from 'vue'
axios.defaults.headers.post['Content-Type'] = 'application/json'
const userStore = useUserStore()
const APPLICATION_SERVER_URL = import.meta.env.VITE_VUE_API_URL
const OVCamera = ref<OpenVidu | undefined>(undefined)
const OVScreen = ref<OpenVidu | undefined>(undefined)
const sessionCamera = ref<Session | undefined>(undefined)
const sessionScreen = ref<Session | undefined>(undefined)
const mainStreamManager: Ref<StreamManager | undefined> = ref(undefined)
const shareStreamManager = ref<StreamManager | undefined>(undefined)
const publisher: Ref<Publisher | undefined> = ref(undefined)
const publisherScreen: Ref<Publisher | undefined> = ref(undefined)
const videoStore = useVideoStore()
const notificationStore = useNotificationStore()
const sessionId: ComputedRef<number> = computed(() => {

  if (notificationStore.$state.roomSessionId?.includes('Call')) {
    return Number(notificationStore.$state.roomSessionId.replace('tutorCall', ''))
  } else return Number(notificationStore.$state.roomSessionId?.replace('lecture', ''))
})

const router = useRouter()
const subscribers: Ref<Subscriber[]> = ref([])
const userName: string = userStore.nickname

// 음소거, 화면 끄기 로직
watch(
  () => videoStore.videoActive,
  (newVal: boolean) => {
    if (!newVal) {
      // videoActive 값이 false가 되면 카메라 비활성화
      publisher.value?.publishVideo(false)
    } else {
      publisher.value?.publishVideo(true)
    }
  }
)

watch(
  () => videoStore.micActive,
  (newVal: boolean) => {
    if (!newVal) {
      publisher.value?.publishAudio(false)
    } else {
      publisher.value?.publishAudio(true)
    }
  }
)

const joinSession = async () => {
  OVCamera.value = new OpenVidu()

  sessionCamera.value = OVCamera.value.initSession()
  videoStore.sessionCamera = sessionCamera.value
  sessionCamera.value.on('streamCreated', ({ stream }) => {
    const subscriber = sessionCamera.value?.subscribe(stream, undefined)
    if (subscriber) {
      subscribers.value.push(subscriber)
    }
  })

  sessionCamera.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.findIndex((sub) => sub.stream === stream)
    if (index >= 0) {
      subscribers.value.splice(index, 1)
    }
  })
  sessionCamera.value.on('signal', (event) => {
    videoStore.messages.push({
      userName: JSON.parse(event.from?.data).clientData,
      message: event.data
    })
  })
  sessionCamera.value.on('exception', (exception) => {
    console.warn(exception)
  })
  try {
    const token = await getToken(sessionId.value)
    await sessionCamera.value.connect(token, { clientData: userName })
    const newPublisher = OVCamera.value?.initPublisher(undefined, {
      audioSource: undefined,
      videoSource: undefined,
      publishAudio: true,
      publishVideo: true,
      resolution: '1000x500',
      frameRate: 30,
      insertMode: 'APPEND',
      mirror: false
    })

    mainStreamManager.value = newPublisher
    publisher.value = newPublisher
    sessionCamera.value.publish(publisher.value)
  } catch (error: any) {

  }
  window.addEventListener('beforeunload', leaveSession)
}

const videoSubscribers = computed(() =>
  subscribers.value.filter((sub) => sub.stream.typeOfVideo === 'CAMERA')
)
const screenSub = computed(() => {
  const v = subscribers.value.find((sub) => sub.stream.typeOfVideo === 'SCREEN')
  return v != undefined ? v : null
})

const leaveSession = async () => {
  let url = `/`
  if (notificationStore.$state.roomSessionId?.includes('Call')) {
    url += `tutorcall/${sessionId.value}/disconnection`
  } else {
    url += `lecture/${sessionId.value}/disconnection`
  }
  await axios.delete(APPLICATION_SERVER_URL + url, {
    headers: { 'Content-Type': 'application/json' },
    withCredentials: true
  })

  if (sessionCamera.value) {
    sessionCamera.value.disconnect()
  }
  if (sessionScreen.value) {
    sessionScreen.value.disconnect()
  }

  shareStreamManager.value = undefined
  mainStreamManager.value = undefined
  publisherScreen.value = undefined
  publisher.value = undefined
  subscribers.value = []
  OVScreen.value = undefined
  OVCamera.value = undefined
  if (!userStore.isTutor) {
    router.push('/reviewform')
  }
  window.removeEventListener('beforeunload', leaveSession)
}

const getToken = async (sessionId: number) => {
  return await createToken(sessionId)
}

const createToken = async (sessionId: number) => {
  let url = `${APPLICATION_SERVER_URL}/`
  if (notificationStore.$state.roomSessionId?.includes('Call')) {
    url += `tutorcall/${sessionId}/connection`
  } else {
    url += `lecture/${sessionId}/connection`
  }
  const response = await axios.post(
    url,
    {},
    {
      headers: { 'Content-Type': 'application/json' },
      withCredentials: true
    }
  )
  videoStore.token = response.data.token
  return response.data.token
}

watch(
  () => videoStore.showScreen,
  () => {
    if (videoStore.showScreen) {
      showScreenShare()
    } else {
      stopScreenSharing()
    }
  }
)

function showScreenShare() {
  OVScreen.value = new OpenVidu()
  sessionScreen.value = OVScreen.value.initSession()
  getToken(sessionId.value)
    .then((token) => {
      sessionScreen.value?.connect(token).then(() => {
        let screenPublisher = OVScreen.value?.initPublisher(undefined, {
          audioSource: false,
          videoSource: 'screen',
          publishAudio: false,
          publishVideo: true,
          resolution: '1920x1080',
          frameRate: 30,
          insertMode: 'APPEND',
          mirror: false
        })
        screenPublisher?.once('accessAllowed', (event) => {
          screenPublisher?.stream
            .getMediaStream()
            .getVideoTracks()[0]
            .addEventListener('ended', () => {
              videoStore.showScreen = false
            })
        })
        screenPublisher?.once('accessDenied', (event) => {
          videoStore.showScreen = false
          console.warn('ScreenShare: Access Denied')
        })
        shareStreamManager.value = screenPublisher
        publisherScreen.value = screenPublisher

        sessionScreen.value?.publish(publisherScreen.value)
      })
    })
    .catch((error) => {
      console.warn('There was an error connecting to the session:', error.code, error.message)
    })
}

const stopScreenSharing = () => {
  if (sessionScreen.value) {
    sessionScreen.value.disconnect()
    sessionScreen.value = undefined
  }
  if (publisherScreen.value) {
    publisherScreen.value = undefined
  }
  shareStreamManager.value = undefined
  OVScreen.value = undefined
  videoStore.showScreen = false
}

onMounted(() => {
  joinSession()
})
onBeforeUnmount(() => {
  stopScreenSharing()
  leaveSession()
})
</script>
<template>
  <!-- 화면이 공유될 때 -->
  <div v-if="screenSub">
    <div class="subscribers">
      <OnlineVideo class="w-[250px] h-[150px]" :stream-manager="mainStreamManager" />
      <div v-for="sub in videoSubscribers" :key="sub.stream.connection.connectionId">
        <OnlineVideo class="w-[250px] h-[150px] mx-2" :stream-manager="sub" />
      </div>
    </div>
    <OnlineVideo :stream-manager="screenSub" />
  </div>
  <!-- 화면 공유 중이 아닐 때 -->
  <div v-else>
    <div v-if="videoSubscribers.length > 0">
      <div class="subscribers">
        <div v-for="sub in videoSubscribers" :key="sub.stream.connection.connectionId">
          <OnlineVideo class="w-[250px] h-[150px]" :stream-manager="sub" />
        </div>
      </div>
    </div>
    <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
    <!-- <div v-else>
      <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
    </div> -->
  </div>
</template>

<style scoped>
.subscribers {
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  display: flex;
  margin-bottom: 5px;
  justify-content: center;
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>
