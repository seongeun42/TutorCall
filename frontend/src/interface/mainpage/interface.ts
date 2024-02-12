export interface reviewerInfo {
  userId: number
  nickname: string
  profile: string
}

export interface tutorInfo {
  id: number
  nickname: string
  profile: string
}

export interface tutorReviewInfo {
  introduction: string
  isActive: boolean
  mannerRate: number
  communicationRate: number
  professionalRate: number
}

export interface tutorReviewResponse {
  reviewer: {
    content: reviewerInfo
  }
  type: string
  mannerRate: number
  communicationRate: number
  professionalismRate: number
  content: string
  createdAt: Date
}

export interface studentReviewResponse {
  tutor: {
    content: tutorReviewInfo
  }
  reviewer: {
    content: reviewerInfo
  }
  type: string
  mannerRate: number
  communicationRate: number
  professionalismRate: number
  content: string
  createdAt: Date
}

export interface tagInfo {
  id: number
  subject: string
  schoolType: string
  grade: number
}

export interface lectureResponse {
  id: number
  title: string
  content: string
  tag: {
    content: tagInfo
  }
  tutor: {
    content: tutorInfo
  }
  promotionState: boolean
  createdAt: Date
}
