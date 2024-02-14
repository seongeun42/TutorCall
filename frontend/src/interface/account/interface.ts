import type{ CommonResponse, ErrorResponse } from '@/interface/common/interface'

export interface signUpResponse extends CommonResponse {
  id: string
}

export interface User {
  id: string
  role: string
  nickname: string
  point: number
  profile: string
}

export interface AccountErrorResponse extends ErrorResponse{
    status: number,
    error: string,
}

export interface EmailSend {
  email: string
}

export interface EmailCodeCheck extends EmailSend {
  code: string
}

export interface NickCheck {
  nickname: string
}

export interface LoginForm {
  email: string
  password: string
}

export interface SignUpForm extends LoginForm {
  nickname: string
}
