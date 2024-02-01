import { instance } from '@/axios/axiosCofig'
import type{ AxiosResponse } from 'axios';
import type{ questionInfo, questionResponse } from '@/interface/qna/interface'
import type { commonResponse } from '@/interface/account/interface';

export async function getQnAData(param:string)
    :Promise<AxiosResponse<questionResponse>>
    {
        const url = import.meta.env.VITE_VUE_API_URL+"/qna/question"
        +param;

        return instance.get<questionResponse>(url);
}

export async function getOneQuestionData(param:string) 
:Promise<AxiosResponse<{question:questionInfo}>>   
{
    const url = import.meta.env.VITE_VUE_API_URL+"/qna/question/"
    +param;

    return instance.get<{question:questionInfo}>(url);
}

export async function deleteQuestion(param:string)

{
    const url = import.meta.env.VITE_VUE_API_URL+"/qna/question/"+param;

    return instance.delete(url);
}