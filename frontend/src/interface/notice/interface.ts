export interface NoticeInfo {
    noticeId: number
    title: string
    content: string
    createdAt: Date
  }
  
  export interface NoticeResponse {
    notices: {
      content: NoticeInfo[]
    }
  }
  
  export interface FaqInfo {
    faqId: number
    question: string
    answer: string
  }
  
  export interface FaqResponse {
    faqs: {
      content: FaqInfo[]
    }
  }
  
  export interface FaqData extends FaqInfo {
    showAnswer: boolean
  }
  