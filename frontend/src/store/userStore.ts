import { defineStore } from 'pinia'

export const useUserStore = defineStore({
  id: 'user',
  state: () => ({
    isLoggedIn: false,
    email: '',
    isTutor: false
  }),
  actions: {
    login(isTutor: boolean, email: string) {
      this.isLoggedIn = true
      this.isTutor = isTutor
      this.email = email
    },
    logout() {
      this.isLoggedIn = false
      this.isTutor = false
      this.email = ''
    }
  },
  persist: true
})
