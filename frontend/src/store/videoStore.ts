import { defineStore } from 'pinia'
import { OpenVidu, Session, Publisher, Subscriber } from 'openvidu-browser'
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
  persist: {
    storage: sessionStorage
  }
})
