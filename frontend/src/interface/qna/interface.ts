import type{User, TagInfo, CommonResponse} from '@/interface/common/interface'

export interface AnswerInfo {
  chosen: boolean
  content: string
  createAt: string
  delete: boolean
  id: number
  modifiedAt: string
  tutor: User
}

export interface QuestionInfo {
  questionId: number
  answerList: AnswerInfo[]
  title: string
  content: string
  writer: User
  createdAt: string
  modifiedAt: string
  end: boolean
  tag: TagInfo
}

export interface QuestionResponse {
  questions: {
    content: QuestionInfo[]
    totalPages: number
  }
}

export interface QuestionWriteForm{
  questionTitle: string,
  questionContent: string,
  tagId: number
}

export interface EditAnswer{
  answerContent: string
}
export interface AnswerForm extends EditAnswer{
  questionId: number
  answerContent: string
}

export interface AnswerResponse extends CommonResponse {
  questionId: number
}
