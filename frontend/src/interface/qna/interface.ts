export interface tagInfo{
    id: number,
    subject: string,
    level: string,
    grade: number
  
  }

export interface user{
    id: number,
    nickname: string,
    profile: string
}

export interface answerInfo{
  chosen: boolean,
  content: string,
  createAt: string,
  delete: boolean,
  id: number,
  modifiedAt: string,
  tutor:user
}

export interface questionInfo{
    questionId: number,
    answerList: answerInfo[],
    title: string,
    content: string,
    writer: user,
    createDate: string,
    modifyedDate: string,
    end: boolean,
    tag: tagInfo
  }

export interface questionResponse{
  questions:{
    content: questionInfo[]
  }
}

export interface errorResponse{
  timestamp: string,
  message: string
}

export interface commonResponse{
  message: string
}