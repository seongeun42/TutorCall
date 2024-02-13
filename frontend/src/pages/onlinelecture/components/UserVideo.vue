<script setup lang="ts">
import OnlineVideo from './OnlineVideo.vue'
import ScreenShare from './ScreenShare.vue'
import { ref, onMounted, onBeforeUnmount, watch, computed } from 'vue'
import { OpenVidu, StreamManager, Session, Publisher, Subscriber } from 'openvidu-browser'
import type { Ref } from 'vue'
import { useVideoStore } from '@/store/videoStore'
import axios from 'axios'
axios.defaults.headers.post['Content-Type'] = 'application/json'
const APPLICATION_SERVER_URL = 'http://localhost:8080/'
const OVCamera = ref<OpenVidu | undefined>(undefined)
const OVScreen = ref<OpenVidu | undefined>(undefined)
const sessionCamera = ref<Session | undefined>(undefined)
const sessionScreen = ref<Session | undefined>(undefined)
const mainStreamManager = ref<StreamManager | undefined>(undefined)
const shareStreamManager = ref<StreamManager | undefined>(undefined)
const publisher: Ref<Publisher | undefined> = ref(undefined)
const publisherScreen = ref<Publisher | undefined>(undefined)
const videoStore = useVideoStore()
const subscribers: Ref<Subscriber[]> = ref([])
const nowSharing: Ref<boolean> = ref(false)
const screenSub: Ref<any> = ref('')
const userName: Ref<string> = ref('Participant' + Math.floor(Math.random() * 100))
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
const joinSession = () => {
  OVCamera.value = new OpenVidu()

  sessionCamera.value = OVCamera.value.initSession()

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

  sessionCamera.value.on('exception', (exception) => {
    console.warn(exception)
  })
  getToken(videoStore.sessionId).then((token) => {
    sessionCamera.value
      ?.connect(token, { clientData: userName.value })
      .then(() => {
        let newPublisher = OVCamera.value?.initPublisher(undefined, {
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
        sessionCamera.value?.publish(publisher.value)
      })
      .catch((error: any) => {
        console.log('There was an error connecting to the session:', error.code, error.message)
      })
  })

  window.addEventListener('beforeunload', leaveSession)
}
const nonScreenSubscribersCount = computed(() => {
  return subscribers.value.filter((sub) => sub.stream.typeOfVideo !== 'SCREEN').length
})

const leaveSession = async () => {
  // 회의에 혼자 남은 상황에서 새로 고침하거나 나가면 세션 종료
  if (nonScreenSubscribersCount.value == 0) {
    const endPoint = `tutorcall/${videoStore.sessionId}/disconnection`
    await axios.delete(APPLICATION_SERVER_URL + endPoint, {
      headers: { 'Content-Type': 'application/json' },
      withCredentials: true
    })
  } else {
    if (sessionCamera.value) sessionCamera.value.disconnect()
  }
  sessionCamera.value = undefined
  mainStreamManager.value = undefined
  publisher.value = undefined
  subscribers.value = []
  OVCamera.value = undefined

  window.removeEventListener('beforeunload', leaveSession)
}

const updateMainVideoStreamManager = (stream: StreamManager) => {
  if (mainStreamManager.value === stream) return
  mainStreamManager.value = stream
}
const getToken = async (sessionId: number) => {
  console.log(sessionId)
  const newSessionId = await createSession(sessionId)
  console.log(newSessionId)
  return await createToken(newSessionId)
}

const createSession = async (sessionId: number) => {
  try {
    const response = await axios.post(
      `${APPLICATION_SERVER_URL}tutorcall/${sessionId}/session`,
      { customSessionId: sessionId },
      {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: true
      }
    )
    console.log('세션 생성', response)
    return response.data.sessionId.replace('tutorCall', '')
  } catch (error) {
    console.log('세션 생성 실패', error)
    return 1
  }
}

const createToken = async (sessionId: number) => {
  const response = await axios.post(
    `${APPLICATION_SERVER_URL}tutorcall/${sessionId}/connection`,
    {},
    {
      headers: { 'Content-Type': 'application/json' },
      withCredentials: true
    }
  )
  console.log('토큰', response.data.token)
  return response.data.token
}
function isVideoVisible(sub: any): boolean {
  return subscribers.value.indexOf(sub) < 4
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

watch(subscribers.value, (newValue: any) => {
  for (const sub of newValue) {
    if (sub.stream.typeOfVideo === 'SCREEN') {
      nowSharing.value = true
      screenSub.value = sub
      return
    }
  }
  nowSharing.value = false
  screenSub.value = ''
})

function showScreenShare() {
  OVScreen.value = new OpenVidu()
  sessionScreen.value = OVScreen.value.initSession()
  getToken(videoStore.sessionId)
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
              screenSub.value = ''
              nowSharing.value = false
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
  if (sessionScreen.value) sessionScreen.value.disconnect()
  // sessionScreen.value?.unpublish(publisherScreen.value)
  publisherScreen.value = undefined
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
  <div v-if="subscribers.length != 0">
    <div v-if="nowSharing">
      <div class="flex mb-5 subscribers">
        <OnlineVideo class="w-[250px] h-[150px]" :stream-manager="mainStreamManager" />
        <div v-for="sub in subscribers" :key="sub.stream.connection.connectionId">
          <div v-if="sub.stream.typeOfVideo !== 'SCREEN'">
            <OnlineVideo
              class="w-[250px] h-[150px]"
              v-if="isVideoVisible(sub)"
              :stream-manager="sub"
              @click="updateMainVideoStreamManager(sub)"
            />
          </div>
        </div>
      </div>
      <ScreenShare :stream-manager="screenSub" />
    </div>
    <div v-else>
      <div class="flex mb-5 subscribers">
        <div v-for="sub in subscribers" :key="sub.stream.connection.connectionId">
          <OnlineVideo
            class="w-[250px] h-[150px]"
            v-if="isVideoVisible(sub)"
            :stream-manager="sub"
            @click="updateMainVideoStreamManager(sub)"
          />
        </div>
      </div>
      <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
    </div>
  </div>
  <div v-else>
    <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
  </div>
</template>

<style scoped>
.subscribers {
  border: 1px solid #dbdbdb;
}
</style>
