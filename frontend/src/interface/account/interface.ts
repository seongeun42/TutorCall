export interface commonResponse {
  message: string
}

export interface signUpResponse extends commonResponse {
  id: string
}

export interface user {
  id: string
  role: string
  nickname: string
  point: number
  profile: string
}

export interface errorResponse {
  timestamp: string
  status: number
  error: string
  message: string
}

export interface emailSend {
  email: string
}

export interface emailCodeCheck extends emailSend {
  code: string
}

export interface nickCheck {
  nickname: string
}

export interface loginForm {
  email: string
  password: string
}

export interface signUpForm extends loginForm {
  nickname: string
}
