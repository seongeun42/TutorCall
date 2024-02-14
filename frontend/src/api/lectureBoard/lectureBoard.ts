import { instance } from '@/axios/axiosConfig';
import type { CommonResponse } from '@/interface/common/interface';
import { type LectureResponse, type detailLecture, type RegistResponse, type DeleteResponse, type Promotion } from '@/interface/lectureBoard/interface';
import type { AxiosResponse } from 'axios';

export async function lectureList(param:string)
:Promise<AxiosResponse<LectureResponse>>
{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture?"+param;
    return instance.get<LectureResponse>(url);

}

export async function oneLecture(param:number)
:Promise<AxiosResponse<detailLecture>>
{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/promotion/"+param;
    return instance.get<detailLecture>(url);
}

export async function registLecture(param:number)
:Promise<AxiosResponse<RegistResponse>>{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/participant/"+param;
    return instance.post<RegistResponse>(url);
}

export async function deletePromotion(param:number)
:Promise<AxiosResponse<DeleteResponse>>{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/promotion/"+param;

    return instance.delete<DeleteResponse>(url);
}

export async function cancleRegistLecture(param:number)
:Promise<AxiosResponse<CommonResponse>>
{
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/participant/"+param;
    return instance.delete<CommonResponse>(url);
}

export async function registPromotion(param:Promotion)
:Promise<AxiosResponse<RegistResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/promotion";

    return instance.post<RegistResponse>(url, param);
}

export async function editPromotion(param:Promotion, lectureId: number)
:Promise<AxiosResponse<RegistResponse>>{
    
    const url:string = import.meta.env.VITE_VUE_API_URL+"/lecture/promotion/"+lectureId;

    return instance.patch<RegistResponse>(url, param);
}




