export interface TagInfo{
    id: number,
    subject: string,
    level: string,
    grade: number
  
  }

export interface User{
    id: number,
    nickname: string,
    profile: string
}

export interface ErrorResponse{
    timestamp: string,
    message: string
  }
  
export interface CommonResponse{
    message: string
  }

export interface Review{
  mannerRate: number,
  communicationRate: number,
  professionalismRate: number,
  content: string,
  createAt: string,
}

export interface ReviewResponse extends CommonResponse{
  id: number
}