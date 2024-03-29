import { instance } from '@/axios/axiosConfig'
import type { AxiosResponse } from 'axios'
import type { noticeInfo, noticeResponse, faqInfo, faqResponse } from '@/interface/notice/interface'

export async function getNoticeData(): Promise<AxiosResponse<noticeResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/notice'

  return instance.get<noticeResponse>(url)
}

export async function getOneNoticeData(
  param: number
): Promise<AxiosResponse<{ notice: noticeInfo }>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/notice/' + param

  return instance.get<{ notice: noticeInfo }>(url)
}

export async function getFaqData(): Promise<AxiosResponse<faqResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/notice/faq'

  return instance.get<faqResponse>(url)
}
