import { instance } from '@/axios/axiosConfig'
import type { lectureHistory, lectureResponse, tutorCallHistory } from '@/interface/mypage/interface';
import type { AxiosResponse } from 'axios';

export async function tutorcallHistory(param:string):
Promise<AxiosResponse<tutorCallHistory>>{

    const url = import.meta.env.VITE_VUE_API_URL+"/mypage/tutorCall?"+param;
    return instance.get<tutorCallHistory>(url);
}

export async function lectureHistory(param: string):
Promise<AxiosResponse<lectureResponse>>{

    const url = import.meta.env.VITE_VUE_API_URL+"/mypage/lecture?"+param;
    
    return instance.get<lectureResponse>(url);

}

