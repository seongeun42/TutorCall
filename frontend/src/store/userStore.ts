import { defineStore } from 'pinia'

export const useUserStore = defineStore({
  id: 'user',
  state: () => ({
    isLogin: false,
    email: '',
    isTutor: false,
    isActiveCall: false
  }),
  actions: {
    login(isTutor: boolean, email: string) {
      this.isLogin = true
      this.isTutor = isTutor
      this.email = email
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
