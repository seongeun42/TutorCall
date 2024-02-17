export function tagConvert(data: any){
    return data.map((item:any)=>{
        let schoolname:string = '';
        switch(item.tag.level){
          case "ELEMENTARY":
            schoolname = "초등학교";
            break;
          case "MIDDLE":
            schoolname = "중학교";
            break;
          case "HIGH":
            schoolname = "고등학교";
            break; 
        }
        const newtag = {...item.tag, level:schoolname}
        return {...item, tag:newtag};
      })
}