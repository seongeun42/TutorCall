import { defineStore } from 'pinia'
import axios from 'axios'
import { OpenVidu, Session, Publisher, Subscriber } from 'openvidu-browser'
import { useUserStore } from './userStore'
const APPLICATION_SERVER_URL = 'http://localhost:8080/'
const userStore = useUserStore()
export const useVideoStore = defineStore({
  id: 'video',
  state: () => ({
    micActive: true,
    videoActive: true,
    OVCamera: undefined as OpenVidu | undefined,
    sessionCamera: undefined as Session | undefined,
    mainStreamManager: undefined as Publisher | undefined,
    subscribers: [] as Subscriber[],
    nowSharing: false,
    screenSub: '',
    token: '',
    userName: 'Participant' + Math.floor(Math.random() * 100),
    sessionId: 1,
    showScreen: false
  }),
  actions: {
    async joinSession() {
      this.OVCamera = new OpenVidu()
      this.sessionCamera = this.OVCamera.initSession()

      this.sessionCamera.on('streamCreated', ({ stream }) => {
        const subscriber = this.sessionCamera?.subscribe(stream, undefined)
        if (subscriber) {
          this.subscribers.push(subscriber)
        }
      })

      this.sessionCamera.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.findIndex((sub) => sub.stream === stream)
        if (index >= 0) {
          this.subscribers.splice(index, 1)
        }
      })

      this.sessionCamera.on('exception', (exception) => {
        console.warn(exception)
      })

      const token = await this.createToken(this.sessionId)
      try {
        await this.sessionCamera.connect(token, { clientData: this.userName })
        const newPublisher = this.OVCamera?.initPublisher(undefined, {
          audioSource: undefined,
          videoSource: undefined,
          publishAudio: true,
          publishVideo: true,
          resolution: '1000x500',
          frameRate: 30,
          insertMode: 'APPEND',
          mirror: false
        })
        this.mainStreamManager = newPublisher
        this.sessionCamera?.publish(this.mainStreamManager)
      } catch (error: any) {
        console.log('There was an error connecting to the session:', error.code, error.message)
      }

      window.addEventListener('beforeunload', this.leaveSession)
    },

    async leaveSession() {
      if (this.nonScreenSubscribersCount === 0) {
        const endPoint = `tutorcall/${this.sessionId}/disconnection`
        await axios.delete(APPLICATION_SERVER_URL + endPoint, {
          headers: { 'Content-Type': 'application/json' },
          withCredentials: true
        })
      } else {
        if (this.sessionCamera) this.sessionCamera.disconnect()
      }
      this.sessionCamera = undefined
      this.mainStreamManager = undefined
      this.subscribers = []
      this.OVCamera = undefined

      window.removeEventListener('beforeunload', this.leaveSession)
    },

    async createSession(sessionId: number) {
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
        console.log('세션 생성 실패', error)
        return 1
      }
    },

    async createToken(sessionId: number) {
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
  },
  getters: {
    nonScreenSubscribersCount() {
      return this.subscribers.filter((sub) => sub.stream.typeOfVideo !== 'SCREEN').length
    }
  },
  persist: {
    storage: sessionStorage
  }
})
