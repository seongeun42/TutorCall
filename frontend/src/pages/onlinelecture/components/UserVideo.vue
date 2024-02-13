<script setup lang="ts">
import OnlineVideo from './OnlineVideo.vue'
import ScreenShare from './ScreenShare.vue'
import { ref, onMounted, onBeforeUnmount, watch } from 'vue'
import { OpenVidu, StreamManager, Session, Publisher, Subscriber } from 'openvidu-browser'
import type { Ref } from 'vue'
import axios from 'axios'
axios.defaults.headers.post['Content-Type'] = 'application/json'
const props = defineProps<{ screenShare: boolean }>()
const showScreen: Ref<boolean> = ref(false)
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
const isScreenSharing = ref(false)
const subscribers = ref<Subscriber[]>([])
const lectureId: Ref<number> = ref(1)
const userName: Ref<string> = ref('Participant' + Math.floor(Math.random() * 100))
const joinSession = () => {
  // --- 1) Get an OpenVidu object ---
  OVCamera.value = new OpenVidu()

  // --- 2) Init a session ---
  sessionCamera.value = OVCamera.value.initSession()

  // --- 3) Specify the actions when events take place in the session ---

  // On every new Stream received...
  sessionCamera.value.on('streamCreated', ({ stream }) => {
    const subscriber = sessionCamera.value?.subscribe(stream, undefined)
    if (subscriber) {
      subscribers.value.push(subscriber)
    }
  })

  // On every Stream destroyed...
  sessionCamera.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.findIndex((sub) => sub.stream === stream)
    if (index >= 0) {
      subscribers.value.splice(index, 1)
    }
  })

  // On every asynchronous exception...
  sessionCamera.value.on('exception', (exception) => {
    console.warn(exception)
  })
  getToken(lectureId.value).then((token) => {
    // First param is the token. Second param can be retrieved by every user on event
    // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
    sessionCamera.value
      ?.connect(token, { clientData: userName.value })
      .then(() => {
        // --- 5) Get your own camera stream with the desired properties ---

        // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
        // element: we will manage it on our own) and with the desired properties
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

        // Set the main video in the page to display our webcam and store our Publisher
        mainStreamManager.value = newPublisher
        publisher.value = newPublisher
        console.log(mainStreamManager.value)
        // --- 6) Publish your stream ---

        sessionCamera.value?.publish(publisher.value)
      })
      .catch((error: any) => {
        console.log('There was an error connecting to the session:', error.code, error.message)
      })
  })

  window.addEventListener('beforeunload', leaveSession)
}
const leaveSession = () => {
  // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
  if (sessionCamera.value) sessionCamera.value.disconnect()

  // Empty all properties...
  sessionCamera.value = undefined
  mainStreamManager.value = undefined
  publisher.value = undefined
  subscribers.value = []
  OVCamera.value = undefined

  // Remove beforeunload listener
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

    console.log(response)
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
  // 제한된 영역 내에서 비디오를 표시할지 여부를 결정하는 로직을 작성합니다.
  // 예를 들어, 화면에 최대 4개의 비디오만 표시하고 싶다면:
  return subscribers.value.indexOf(sub) < 4
}
watch(
  () => props.screenShare,
  (newVal: boolean) => {
    if (newVal) {
      showScreen.value = true
    } else {
      showScreen.value = false
    }
  }
)
watch(
  () => showScreen.value,
  (newVal: boolean) => {
    if (newVal) {
      showScreenShare()
    } else {
      stopScreenSharing()
    }
  }
)

function showScreenShare() {
  if (isScreenSharing.value) {
    window.alert('이미 화면이 공유되고 있습니다.')
    return
  }
  OVScreen.value = new OpenVidu()
  sessionScreen.value = OVScreen.value.initSession()
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
              showScreen.value = false
            })
        })
        screenPublisher?.once('accessDenied', (event) => {
          console.warn('ScreenShare: Access Denied')
        })
        // Set the screen sharing publisher as the main video in the page
        shareStreamManager.value = screenPublisher
        publisherScreen.value = screenPublisher

        // Publish the screen sharing stream
        sessionScreen.value?.publish(publisherScreen.value)
        isScreenSharing.value = true
      })
    })
    .catch((error) => {
      console.warn('There was an error connecting to the session:', error.code, error.message)
    })
}

const stopScreenSharing = () => {
  // Unpublish the screen sharing stream
  sessionScreen.value?.unpublish(publisherScreen.value)

  // Reset the main video in the page to display the webcam stream
  publisherScreen.value = undefined
  shareStreamManager.value = undefined
  OVScreen.value = undefined
  showScreen.value = false
  isScreenSharing.value = false
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
  <div v-if="showScreen">
    <div class="flex mb-10">
      <OnlineVideo class="w-[250px] h-[150px]" :stream-manager="mainStreamManager" />
      <div v-for="sub in subscribers" :key="sub.stream.connection.connectionId">
        <OnlineVideo
          class="w-[250px] h-[150px]"
          v-if="isVideoVisible(sub)"
          :stream-manager="sub"
          @click.native="updateMainVideoStreamManager(sub)"
        />
      </div>
    </div>
    <ScreenShare :stream-manager="shareStreamManager" />
  </div>
  <div v-else>
    <div v-if="!subscribers">
      <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
    </div>
    <div v-else>
      <div class="flex mb-5 border-gray-100">
        <div v-for="sub in subscribers" :key="sub.stream.connection.connectionId">
          <OnlineVideo
            class="w-[200px] h-[100px]"
            v-if="isVideoVisible(sub)"
            :stream-manager="sub"
            @click.native="updateMainVideoStreamManager(sub)"
          />
        </div>
      </div>
      <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
    </div>
  </div>
</template>

<style scoped></style>
