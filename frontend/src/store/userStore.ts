import { defineStore } from 'pinia'

export const useUserStore = defineStore({
  id: 'user',
  state: () => ({
    isLogin: false,
    nickname: '',
    profile: '',
    email: '',
    isTutor: false,
    isActiveCall: false
  }),
  actions: {
    login(isTutor: boolean, email: string, nickname: string, profile: string) {
      this.isLogin = true
      this.isTutor = isTutor
      this.email = email
      this.nickname = nickname,
      this.profile = profile
    },
    logout() {
      this.isLogin = false
      this.isTutor = false
      this.email = ''
    }
  },
  persist: {
    storage: sessionStorage
  }
})
