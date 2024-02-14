import { defineStore } from 'pinia'
import { OpenVidu, Session, Publisher, Subscriber } from 'openvidu-browser'
interface Message {
  userName: string
  message: string
}

export const useVideoStore = defineStore({
  id: 'video',
  state: () => ({
    micActive: true,
    videoActive: true,
    OVCamera: undefined as OpenVidu | undefined,
    sessionCamera: undefined as Session | undefined,
    mainStreamManager: undefined as Publisher | undefined,
    subscribers: [] as Subscriber[],
    messages: [] as Message[],
    nowSharing: false,
    screenSub: '',
    token: '',
    showScreen: false
  }),
  persist: {
    storage: sessionStorage
  }
})
