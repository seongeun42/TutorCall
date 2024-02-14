import { instance } from '@/axios/axiosConfig'
import type { AxiosResponse } from 'axios'
import type { NoticeInfo, NoticeResponse, FaqInfo, FaqResponse } from '@/interface/notice/interface'

export async function getNoticeData(): Promise<AxiosResponse<NoticeResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/notice'
  //   const url = 'http://localhost:8080/notice'

  return instance.get<NoticeResponse>(url)
}

export async function getOneNoticeData(
  param: number
): Promise<AxiosResponse<{ notice: NoticeInfo }>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/notice/' + param
  //   const url = 'http://localhost:8080/notice/' + param

  return instance.get<{ notice: NoticeInfo }>(url)
}

export async function getFaqData(): Promise<AxiosResponse<FaqResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/notice/faq'
  //   const url = 'http://localhost:8080/notice/faq'

  return instance.get<FaqResponse>(url)
}
