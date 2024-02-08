import { instance } from '@/axios/axiosConfig'

export async function tutorcallHistory(param:string):Promise<void>{

    const url = import.meta.env.VITE_VUE_API_URL+"/mypage/tutorCall?"+param;
    return instance.get(url);
}