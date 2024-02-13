import { instance } from '@/axios/axiosConfig'

export async function tutorReview(param: number): Promise<void> {
  const url = import.meta.env.VITE_VUE_API_URL + '/review/tutor/' + param
  return instance.get(url)
}

export async function myReview(param: number): Promise<void> {
  const url = import.meta.env.VITE_VUE_API_URL + '/review/user/' + param
  return instance.get(url)
}

export async function tutorPromotion(): Promise<void> {
  const url = import.meta.env.VITE_VUE_API_URL + '/lecture'
  return instance.get(url)
}

export async function tutorInfo(param: number): Promise<void> {
  const url = import.meta.env.VITE_VUE_API_URL + '/tutor/' + param
  return instance.get(url)
}
