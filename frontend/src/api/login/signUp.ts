import { instance } from '@/axios/axiosCofig'


export async function sendEmailCode(param:any):Promise<any> {

    const url:string = import.meta.env.VITE_VUE_API_URL+"/auth/email";
    const data = {"email":param};
    console.log("send url: "+url);
    return instance.post(url, data);

}

export async function sendEmi():Promise<any>{
    console.log("ddd");
}

