export interface noticeInfo {
    noticeId: number
    title: string
    content: string
    createdAt: Date
  }
  
  export interface noticeResponse {
    notices: {
      content: noticeInfo[]
    }
  }
  
  export interface faqInfo {
    faqId: number
    question: string
    answer: string
  }
  
  export interface faqResponse {
    faqs: {
      content: faqInfo[]
    }
  }
  
  export interface faqData extends faqInfo {
    showAnswer: boolean
  }
  