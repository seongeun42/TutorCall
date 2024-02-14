import { instance } from '@/axios/axiosConfig'
import type{ Axios, AxiosResponse } from 'axios';
import type{ questionInfo, questionResponse, answerForm, answerResponse, questionWriteForm, editAnswer } from '@/interface/qna/interface'
import type { commonResponse } from '@/interface/common/interface';

export async function getQnAData(param: string): Promise<AxiosResponse<questionResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/question' + param

  return instance.get<questionResponse>(url)
}

export async function getOneQuestionData(
  param: number
): Promise<AxiosResponse<{ question: questionInfo }>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/question/' + param

  return instance.get<{ question: questionInfo }>(url)
}

export async function deleteQuestion(param: number): Promise<AxiosResponse<commonResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/question/' + param

  return instance.delete<commonResponse>(url)
}

export async function deleteAnswer(param: number): Promise<AxiosResponse<commonResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/answer/' + param
  return instance.delete<commonResponse>(url)
}

export async function registAnswer(param: answerForm): Promise<AxiosResponse<answerResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/answer'

  return instance.post<answerResponse>(url, param)
}

export async function editQuestion(param:questionWriteForm, id:number)
:Promise<AxiosResponse<commonResponse>>{

  const url = import.meta.env.VITE_VUE_API_URL+'/qna/question/'+id;

  return instance.patch<commonResponse>(url, param);
}


export async function writeQuestion
(param:questionWriteForm)
:Promise<AxiosResponse<answerResponse>>{
  const url = import.meta.env.VITE_VUE_API_URL+'/qna/question';
  return instance.post<answerResponse>(url, param);
}

export async function editAnswer(param:editAnswer, answerId: number)
:Promise<AxiosResponse<commonResponse>>{
  const url = import.meta.env.VITE_VUE_API_URL +'/qna/answer/'+answerId;
  return instance.patch<commonResponse>(url, param);
}