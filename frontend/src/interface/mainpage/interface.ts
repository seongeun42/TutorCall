export interface ReviewerInfo {
  userId: number
  nickname: string
  profile: string
}

export interface TutorInfo {
  id: number
  nickname: string
  profile: string
}

export interface TutorReviewInfo {
  introduction: string
  isActive: boolean
  mannerRate: number
  communicationRate: number
  professionalRate: number
}

export interface TutorReviewResponse {
  reviewer: {
    content: ReviewerInfo
  }
  type: string
  mannerRate: number
  communicationRate: number
  professionalismRate: number
  content: string
  createdAt: Date
}

export interface StudentReviewResponse {
  tutor: {
    content: TutorReviewInfo
  }
  reviewer: {
    content: ReviewerInfo
  }
  type: string
  mannerRate: number
  communicationRate: number
  professionalismRate: number
  content: string
  createdAt: Date
}

export interface TagInfo {
  id: number
  subject: string
  level: string
  grade: number
}

export interface LectureResponse {
  id: number
  title: string
  content: string
  tag: {
    content: TagInfo
  }
  tutor: {
    content: TutorInfo
  }
  promotionState: boolean
  createdAt: Date
}
