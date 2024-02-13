<script setup lang="ts">
import OnlineVideo from './OnlineVideo.vue'
import ScreenShare from './ScreenShare.vue'
import { ref, onMounted, onBeforeUnmount, watch } from 'vue'
import { OpenVidu, StreamManager, Session, Publisher, Subscriber } from 'openvidu-browser'
import type { Ref } from 'vue'
import { useVideoStore } from '@/store/videoStore'
import axios from 'axios'
axios.defaults.headers.post['Content-Type'] = 'application/json'
const APPLICATION_SERVER_URL =
  import.meta.env.NODE_ENV === 'production' ? '' : 'http://localhost:8080/'
const OVCamera = ref<OpenVidu | undefined>(undefined)
const OVScreen = ref<OpenVidu | undefined>(undefined)
const sessionCamera = ref<Session | undefined>(undefined)
const sessionScreen = ref<Session | undefined>(undefined)
const mainStreamManager = ref<StreamManager | undefined>(undefined)
const shareStreamManager = ref<StreamManager | undefined>(undefined)
const publisher = ref<Publisher | undefined>(undefined)
const publisherScreen = ref<Publisher | undefined>(undefined)
const videoStore = useVideoStore()
const subscribers: Ref<Subscriber[]> = ref([])
const nowSharing: Ref<boolean> = ref(false)
const screenSub: Ref<any> = ref('')
const lectureId: Ref<number> = ref(1)
const userName: Ref<string> = ref('Participant' + Math.floor(Math.random() * 100))
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
  getToken(lectureId.value).then((token) => {
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
        console.log(mainStreamManager.value)

        sessionCamera.value?.publish(publisher.value)
      })
      .catch((error: any) => {
        console.log('There was an error connecting to the session:', error.code, error.message)
      })
  })

  window.addEventListener('beforeunload', leaveSession)
}
const leaveSession = () => {
  if (sessionCamera.value) sessionCamera.value.disconnect()

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
  const newSessionId = await createSession(sessionId)
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

    return response.data.sessionId.replace('tutorCall', '')
  } catch (error) {
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

  sessionScreen.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.findIndex((sub) => sub.stream === stream)
    if (index >= 0) {
      subscribers.value.splice(index, 1)
    }
  })
  getToken(lectureId.value)
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
              const screenIdToRemove = screenSub.value
              screenSub.value = ''
              nowSharing.value = false
              const index = subscribers.value.findIndex((sub) => sub.id === screenIdToRemove)
              if (index !== -1) {
                subscribers.value.splice(index, 1)
              }
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
  sessionScreen.value?.unpublish(publisherScreen.value)
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
  <div v-if="subscribers">
    <div v-if="nowSharing">
      <div class="flex mb-10">
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
      <div class="flex mb-10">
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
      <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
    </div>
  </div>
  <div v-else>
    <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
  </div>
</template>

<style scoped></style>
