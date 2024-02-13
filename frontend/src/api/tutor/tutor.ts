import { instance } from '@/axios/axiosConfig'
import type { AxiosResponse } from 'axios';
import type { tagResponse } from '@/interface/common/interface';

export async function getTutorTags(): Promise<AxiosResponse<tagResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/tutor/tag'
  return instance.get<tagResponse>(url)
}