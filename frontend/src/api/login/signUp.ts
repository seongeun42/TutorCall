import { instance } from '@/axios/axiosConfig'
import type{ AxiosResponse } from 'axios';
import type{ emailSend, emailCodeCheck,
    nickCheck, loginForm, signUpForm, signUpResponse, user } from '@/interface/account/interface'
import type { commonResponse } from '@/interface/common/interface';

export async function sendEmailCode(param: emailSend): Promise<AxiosResponse<commonResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/email'
  return instance.post<commonResponse>(url, param)
}

export async function checkCode(param: emailCodeCheck): Promise<AxiosResponse<commonResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/email/check'
  return instance.post<commonResponse>(url, param)
}

export async function nickDupCheck(param: nickCheck): Promise<AxiosResponse<commonResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/nick-check'
  return instance.post<commonResponse>(url, param)
}

export async function signUp(param: signUpForm): Promise<AxiosResponse<signUpResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/signup'
  return instance.post<signUpResponse>(url, param)
}

export async function login(param: loginForm): Promise<AxiosResponse<user>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/login'
  return instance.post<user>(url, param)
}
