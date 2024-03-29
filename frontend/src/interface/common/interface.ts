export interface tagInfo {
  id: number,
  subject: string,
  level: string,
  grade: number
}

export interface user {
  id: number,
  role: string,
  email: string,
  nickname: string,
  profile: string,
}

export interface userSimple {
  id: number,
  nickname: string,
  profile: string
}

export interface errorResponse {
  timestamp: string,
  message: string
}

export interface commonResponse {
  message: string
}

export interface tagResponse {
  content: tagInfo[]
}

export interface tutorDetail {
  id: number,
  nickname: string,
  profile: string,
  introduction: string,
  reliability: number,
  mannerRate: number,
  communicationRate: number,
  professionalismRate: number,
  content: string,
  createAt: string,
  tags: tagInfo[]
}


export interface review{
  mannerRate: number,
  communicationRate: number,
  professionalismRate: number,
  content: string,
  createAt: string,
}
export interface reviewResponse extends commonResponse{
  id: number
}