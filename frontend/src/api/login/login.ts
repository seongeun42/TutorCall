import { instance } from '@/axios/axiosCofig'

export async function login(param:any){
    const url:string = import.meta.env.VITE_VUE_API_URL+"/auth/login";
    return instance.post(url, param);
}