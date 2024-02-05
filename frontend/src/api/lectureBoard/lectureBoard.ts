import { instance } from '@/axios/axiosConfig';
import type { commonResponse } from '@/interface/common/interface';
import { type lectureResponse, type detailLecture, type registResponse, type deleteResponse } from '@/interface/lectureBoard/interface';
import type { AxiosResponse } from 'axios';

export async function lectureList(param:string)
:Promise<AxiosResponse<lectureResponse>>
{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture?"+param;
    return instance.get<lectureResponse>(url);

}

export async function oneLecture(param:number)
:Promise<AxiosResponse<detailLecture>>
{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/promotion/"+param;
    return instance.get<detailLecture>(url);
}

export async function registLecture(param:number)
:Promise<AxiosResponse<registResponse>>{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/participant/"+param;
    return instance.post<registResponse>(url);
}

export async function deletePromotion(param:number)
:Promise<AxiosResponse<deleteResponse>>{
    const url:string = import.meta.env.VITE_VUE_API_URL+"lecture/promotion/"+param;

    return instance.delete<deleteResponse>(url);
}

export async function cancleRegistLecture(param:number)
:Promise<AxiosResponse<commonResponse>>
{
    const url:string = import.meta.env.VITE_VUE_API_URL+"lecture/participant/"+param;

    return instance.delete<commonResponse>(url);
}




