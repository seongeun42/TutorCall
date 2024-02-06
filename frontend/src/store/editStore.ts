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
  }),
  actions: {
    save(school:number, grade:number, subject:number, title:string, content:string, needEdit:boolean){
        this.school=school,
        this.grade = grade,
        this.subject = subject,
        this.title = title,
        this.content = content,
        this.needEdit = needEdit
    },
    init(){
        this.school = 0,
        this.grade = 0,
        this.subject = 0,
        this.title = "",
        this.content = "",
        this.needEdit = false
    },
  },
  persist: {
    storage: sessionStorage
  }
})
