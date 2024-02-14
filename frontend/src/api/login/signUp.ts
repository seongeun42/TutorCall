import { instance } from '@/axios/axiosConfig'
import type{ AxiosResponse } from 'axios';
import type{ EmailSend, EmailCodeCheck,
    NickCheck, LoginForm, SignUpForm, signUpResponse, User } from '@/interface/account/interface'
import type { CommonResponse } from '@/interface/common/interface';
import { ref, type Ref } from 'vue';

const token:Ref<string> = ref('');
export async function sendEmailCode(param: EmailSend): Promise<AxiosResponse<CommonResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/email'
  return instance.post<CommonResponse>(url, param)
}

export async function checkCode(param: EmailCodeCheck): Promise<AxiosResponse<CommonResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/email/check'
  return instance.post<CommonResponse>(url, param)
}

export async function nickDupCheck(param: NickCheck): Promise<AxiosResponse<CommonResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/nick-check'
  return instance.post<CommonResponse>(url, param)
}

export async function signUp(param: SignUpForm): Promise<AxiosResponse<signUpResponse>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/signup'
  return instance.post<signUpResponse>(url, param)
}

export async function login(param: LoginForm): Promise<AxiosResponse<User>> {
  const url: string = import.meta.env.VITE_VUE_API_URL + '/auth/login'
  return instance.post<User>(url, param)
}
