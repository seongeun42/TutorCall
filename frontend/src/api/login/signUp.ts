import { instance } from '@/axios/axiosCofig'

export async function sendEmailCode(param: any): Promise<any> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/email'
  const data = { email: param }
  return instance.post(url, data)
}

export async function checkCode(param: any): Promise<any> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/email/check'
  return instance.post(url, param)
}

export async function nickDupCheck(param: any): Promise<any> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/nick-check'
  return instance.post(url, param)
}

export async function signUp(param: any): Promise<any> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/signup'
  return instance.post(url, param)
}

export async function login(param: any) {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/login'
  return instance.post(url, param)
}
