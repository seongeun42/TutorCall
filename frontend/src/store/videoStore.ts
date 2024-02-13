import { defineStore } from 'pinia'

export const useVideoStore = defineStore({
  id: 'video',
  state: () => ({
    showScreen: false,
    micActive: true,
    videoActive: true
  }),
  actions: {
    share() {
      this.showScreen = true
    },
    stopShare() {
      this.showScreen = false
    }
  },
  persist: {
    storage: sessionStorage
  }
})
