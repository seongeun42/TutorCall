import type{user, tagInfo, commonResponse} from '@/interface/common/interface'

export interface answerInfo {
  chosen: boolean
  content: string
  createAt: string
  delete: boolean
  id: number
  modifiedAt: string
  tutor: user
}

export interface questionInfo {
  questionId: number
  answerList: answerInfo[]
  title: string
  content: string
  writer: user
  createDate: string
  modifyedDate: string
  end: boolean
  tag: tagInfo
}

export interface questionResponse {
  questions: {
    content: questionInfo[]
    totalPages: number
  }
}

export interface questionWriteForm{
  questionTitle: string,
  questionContent: string,
  tagId: number
}

export interface editAnswer{
  answerContent: string
}
export interface answerForm extends editAnswer{
  questionId: number
  answerContent: string
}

export interface answerResponse extends commonResponse {
  questionId: number
}
