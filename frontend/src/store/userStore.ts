import { defineStore } from 'pinia'
import type { user } from '@/interface/common/interface'

export const useUserStore = defineStore({
  id: 'user',
  state: () => ({
    id: -1,
    email: '',
    nickname: '',
    role: '',
    profile: '',
    isTutor: false,
    isLogin: false,
    isActiveCall: false
  }),
  actions: {
    login(user: user) {
      this.id = user.id
      this.nickname = user.nickname
      this.role = user.role
      this.isTutor = user.role === "TUTOR"
      this.email = user.email
      this.profile = user.profile
      this.isLogin = true
    },
    logout() {
      this.id = -1
      this.role = ''
      this.email = ''
      this.nickname = ''
      this.profile = ''
      this.isTutor = false
      this.isLogin = false
      this.isActiveCall = false
    }
  },
  persist: {
    storage: sessionStorage
  }
})
