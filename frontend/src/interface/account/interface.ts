import type{ commonResponse, errorResponse } from '@/interface/common/interface'

export interface signUpResponse extends commonResponse {
  id: string
}

export interface user {
  id: number,
  role: string,
  email: string,
  nickname: string,
  profile: string,
}

export interface accountErrorResponse extends errorResponse{
    status: number,
    error: string,
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
  role: string,
}
