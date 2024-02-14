import { instance } from '@/axios/axiosConfig'
import type { commonResponse } from '@/interface/common/interface';
import type { LectureHistory, lectureResponse, modifyIntro, modifynickname, modifynotify, modifypassword, modifytags, tutorcallResponse } from '@/interface/mypage/interface';
import type { AxiosResponse } from 'axios';

export async function tutorcallHistory(param:string):
Promise<AxiosResponse<tutorcallResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/tutorCall?"+param;
    return instance.get<tutorcallResponse>(url);
}

export async function lectureHistory(param: string):
Promise<AxiosResponse<lectureResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/lecture?"+param;
    return instance.get<lectureResponse>(url);

}

export async function modifynickname(param:modifynickname):
Promise<AxiosResponse<commonResponse>>{
    
    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/nickname";
    return instance.patch<commonResponse>(url, param);

}

export async function modifyPassword(param: modifypassword):
Promise<AxiosResponse<commonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/password";
    return instance.patch<commonResponse>(url, param);
}

export async function modifyAlert(param: modifynotify):
Promise<AxiosResponse<commonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/notification";
    return instance.patch<commonResponse>(url, param);
}

export async function modifyTag(param: modifytags):
Promise<AxiosResponse<commonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/tutor/tag";
    return instance.put<commonResponse>(url, param);
}

export async function modifyIntro(param: modifyIntro):
Promise<AxiosResponse<commonResponse>>{

    const url:string = import.meta.env.VITE_VUE_API_URL+"/mypage/tutor/intro";
    return instance.patch<commonResponse>(url, param);
}
