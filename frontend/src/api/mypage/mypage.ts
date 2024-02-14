import { instance } from '@/axios/axiosConfig'
import type { CommonResponse } from '@/interface/common/interface';
import type { LectureHistory, LectureResponse, ModifyIntro, Modifynickname, Modifynotify, Modifypassword, Modifytags, TutorcallResponse } from '@/interface/mypage/interface';
import type { AxiosResponse } from 'axios';

export async function tutorcallHistory(param:string):
Promise<AxiosResponse<TutorcallResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/tutorCall?"+param;
    return instance.get<TutorcallResponse>(url);
}

export async function lectureHistory(param: string):
Promise<AxiosResponse<LectureResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/lecture?"+param;
    return instance.get<LectureResponse>(url);

}

export async function modifynickname(param:Modifynickname):
Promise<AxiosResponse<CommonResponse>>{
    
    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/nickname";
    return instance.patch<CommonResponse>(url, param);

}

export async function modifyPassword(param: Modifypassword):
Promise<AxiosResponse<CommonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/password";
    return instance.patch<CommonResponse>(url, param);
}

export async function modifyAlert(param: Modifynotify):
Promise<AxiosResponse<CommonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/notification";
    return instance.patch<CommonResponse>(url, param);
}

export async function modifyTag(param: Modifytags):
Promise<AxiosResponse<CommonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/tutor/tag";
    return instance.put<CommonResponse>(url, param);
}

export async function modifyIntro(param: ModifyIntro):
Promise<AxiosResponse<CommonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/tutor/intro";
    return instance.patch<CommonResponse>(url, param);
}
