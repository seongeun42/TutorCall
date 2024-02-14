import { defineStore } from 'pinia'

export const useUserStore = defineStore({
  id: 'user',
  state: () => ({
    isLogin: false,
    nickname: '',
    profile: '',
    email: '',
    isTutor: false,
    isActiveCall: false,
    userId: '',
  }),
  actions: {
    login(isTutor: boolean, email: string, nickname: string, profile: string, userId:string) {
      this.isLogin = true
      this.isTutor = isTutor
      this.email = email
      this.nickname = nickname,
      this.profile = profile,
      this.userId = userId
    },
    logout() {
      this.isLogin = false
      this.isTutor = false
      this.email = ''
      this.nickname = '',
      this.profile= '',
      this.userId = ''
    }
  },
  persist: {
    storage: sessionStorage
  }
})
