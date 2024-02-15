import { defineStore } from 'pinia'

export const useEditStore = defineStore({
  id: 'board',
  state: () => ({
    school: 0,
    grade: 0,
    subject: 0,
    title: '',
    content: '',
    needEdit: false,
    images: [] as Array<string>
  }),
  actions: {
    save(school:number, grade:number, subject:number, title:string, content:string, needEdit:boolean, images: Array<string>){
        this.school=school;
        this.grade = grade;
        this.subject = subject;
        this.title = title;
        this.content = content;
        this.needEdit = needEdit;
        this.images = images;
    },
    init(){
        this.school = 0,
        this.grade = 0,
        this.subject = 0,
        this.title = "",
        this.content = "",
        this.needEdit = false
        this.images = []
    },
    addImage(filename:string){
      this.images.push(filename);
    }
  },
  persist: {
    storage: sessionStorage
  }
})
