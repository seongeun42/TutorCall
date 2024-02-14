import { instance } from '@/axios/axiosConfig'
import type{ Axios, AxiosResponse } from 'axios';
import type{ questionInfo, QuestionResponse, AnswerForm, AnswerResponse, QuestionWriteForm, EditAnswer } from '@/interface/qna/interface'
import type { CommonResponse } from '@/interface/common/interface';

export async function getQnAData(param: string): Promise<AxiosResponse<QuestionResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/question' + param

  return instance.get<QuestionResponse>(url)
}

export async function getOneQuestionData(
  param: number
): Promise<AxiosResponse<{ question: questionInfo }>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/question/' + param

  return instance.get<{ question: questionInfo }>(url)
}

export async function deleteQuestion(param: number): Promise<AxiosResponse<CommonResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/question/' + param

  return instance.delete<CommonResponse>(url)
}

export async function deleteAnswer(param: number): Promise<AxiosResponse<CommonResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/answer/' + param
  return instance.delete<CommonResponse>(url)
}

export async function registAnswer(param: AnswerForm): Promise<AxiosResponse<AnswerResponse>> {
  const url = import.meta.env.VITE_VUE_API_URL + '/qna/answer'

  return instance.post<AnswerResponse>(url, param)
}

export async function editQuestion(param:QuestionWriteForm, id:number)
:Promise<AxiosResponse<CommonResponse>>{

  const url = import.meta.env.VITE_VUE_API_URL+'/qna/question/'+id;

  return instance.patch<CommonResponse>(url, param);
}


export async function writeQuestion
(param:QuestionWriteForm)
:Promise<AxiosResponse<AnswerResponse>>{
  const url = import.meta.env.VITE_VUE_API_URL+'/qna/question';
  return instance.post<AnswerResponse>(url, param);
}

export async function editAnswer(param:EditAnswer, answerId: number)
:Promise<AxiosResponse<CommonResponse>>{
  const url = import.meta.env.VITE_VUE_API_URL +'/qna/answer/'+answerId;
  return instance.patch<CommonResponse>(url, param);
}

export async function selectAnswer(param:number)
:Promise<AxiosResponse<CommonResponse>>{
  const url:string = import.meta.env.VITE_VUE_API_URL+'/qna/answer/selection/'+param;
  return instance.patch<CommonResponse>(url);
}