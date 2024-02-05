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

export interface errorResponse{
    timestamp: string,
    message: string
  }
  
export interface commonResponse{
    message: string
  }