<script setup lang="ts">
import OnlineVideo from './OnlineVideo.vue'
import ScreenShare from './ScreenShare.vue'
import { ref, onMounted, onBeforeUnmount, watch } from 'vue'
import { OpenVidu, StreamManager } from 'openvidu-browser'
import type { Ref } from 'vue'
import axios from 'axios'
axios.defaults.headers.post['Content-Type'] = 'application/json'

const APPLICATION_SERVER_URL =
  import.meta.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/'
const OV = ref<OpenVidu | undefined>(undefined)
const session = ref<OpenVidu.Session | undefined>(undefined)
const mainStreamManager = ref<OpenVidu.StreamManager | undefined>(undefined)
const shareStreamManager = ref<OpenVidu.StreamManager | undefined>(undefined)
const publisher = ref<OpenVidu.Publisher | undefined>(undefined)
const subscribers = ref<OpenVidu.Subscriber[]>([])
const mySessionId = ref('SessionA')
const myUserName = ref('Participant' + Math.floor(Math.random() * 100))
const joinSession = () => {
  // --- 1) Get an OpenVidu object ---
  OV.value = new OpenVidu()

  // --- 2) Init a session ---
  session.value = OV.value.initSession()

  // --- 3) Specify the actions when events take place in the session ---

  // On every new Stream received...
  session.value.on('streamCreated', ({ stream }) => {
    const subscriber = session.value?.subscribe(stream)
    if (subscriber) {
      subscribers.value.push(subscriber)
    }
  })

  // On every Stream destroyed...
  session.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.indexOf(stream.streamManager, 0)
    if (index >= 0) {
      subscribers.value.splice(index, 1)
    }
  })

  // On every asynchronous exception...
  session.value.on('exception', ({ exception }) => {
    console.warn(exception)
  })
  getToken(mySessionId.value).then((token) => {
    // First param is the token. Second param can be retrieved by every user on event
    // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
    session.value
      ?.connect(token, { clientData: myUserName.value })
      .then(() => {
        // --- 5) Get your own camera stream with the desired properties ---

        // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
        // element: we will manage it on our own) and with the desired properties
        let newPublisher = OV.value?.initPublisher(undefined, {
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

        session.value?.publish(publisher.value)
      })
      .catch((error: any) => {
        console.log('There was an error connecting to the session:', error.code, error.message)
      })
  })

  window.addEventListener('beforeunload', leaveSession)
}
const leaveSession = () => {
  // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
  if (session.value) session.value.disconnect()

  // Empty all properties...
  session.value = undefined
  mainStreamManager.value = undefined
  publisher.value = undefined
  subscribers.value = []
  OV.value = undefined

  // Remove beforeunload listener
  window.removeEventListener('beforeunload', leaveSession)
}

const updateMainVideoStreamManager = (stream: OpenVidu.StreamManager) => {
  if (mainStreamManager.value === stream) return
  mainStreamManager.value = stream
}
const getToken = async (sessionId: string) => {
  const newSessionId = await createSession(sessionId)
  return await createToken(newSessionId)
}

const createSession = async (sessionId: string) => {
  const response = await axios.post(
    `${APPLICATION_SERVER_URL}api/sessions`,
    { customSessionId: sessionId },
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )
  return response.data // The sessionId
}

const createToken = async (sessionId: string) => {
  const response = await axios.post(
    `${APPLICATION_SERVER_URL}api/sessions/${sessionId}/connections`,
    {},
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )
  return response.data // The token
}
function isVideoVisible(sub: string): boolean {
  // 제한된 영역 내에서 비디오를 표시할지 여부를 결정하는 로직을 작성합니다.
  // 예를 들어, 화면에 최대 4개의 비디오만 표시하고 싶다면:
  return subscribers.value.indexOf(sub) < 4
}

onMounted(() => joinSession())
onBeforeUnmount(() => leaveSession())
</script>
<template>
  <ScreenShare :stream-manager="shareStreamManager" />
  <div v-if="!subscribers">
    <OnlineVideo class="w-[800px] h-[500px]" :stream-manager="mainStreamManager" />
  </div>
  <div v-else>
    <div class="flex">
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
</template>

<style scoped></style>
