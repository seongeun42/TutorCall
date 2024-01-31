import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '@/pages/mainpage/MainPage.vue'
import SignUp from '@/pages/account/SignUp.vue'
// import MyPage from '@/pages/mypage/MyPage.vue'
import Notice from '@/pages/board/notice/Notice.vue'
import DetailNotice from '@/pages/board/notice/DetailNotice.vue'
import FAQArticle from '@/pages/board/notice/FAQArticle.vue'
import LectureRecruit from '@/pages/board/lecturerecruiting/LectureRecruit.vue'
import DetailLecture from '@/pages/board/lecturerecruiting/DetailLecture.vue'
import QA from '@/pages/board/q&a/QA.vue'
import DetailQA from '@/pages/board/q&a/DetailQA.vue'
import OnlineLecture from '@/pages/onlinelecture/OnlineLecture.vue'
import TutorCallPage from '@/pages/tutorcall/TutorCallPage.vue'
import InformationUpdate from '@/pages/mypage/tutor/InformationUpdate.vue'
import ReviewCheck from '@/pages/mypage/tutor/ReviewCheck.vue'
import ProfitCheck from '@/pages/mypage/tutor/ProfitCheck.vue'
import WithdrawlPage from '@/pages/mypage/tutor/WithdrawlPage.vue'
import MyLectureList from '@/pages/mypage/tutor/MyLectureList.vue'
import StudentInformationUpdate from '@/pages/mypage/student/information/StudentInformationUpdate.vue'
import PointUsage from '@/pages/mypage/student/point/PointUsage.vue'
import StudentMyLecture from '@/pages/mypage/student/information/StudentMyLecture.vue'
import MyPaymentInfo from '@/pages/mypage/payment/MyPaymentInfo.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // 메인화면
      path: '/',
      name: 'main',
      component: MainPage
    },
    {
      // 로그인 및 회원가입
      path: '/login',
      name: 'signform',
      component: SignUp
    },
    {
      // 마이페이지 및 하부요소는 수정 후 반영 예정
      path: '/mypage',
      name: 'mypage',
      children: [
        {
          // 선생님 마이페이지
          path: '/tutor',
          name: 'tutorMyPage',
          children: [
            // 개인정보 수정
            {
              path: '/update',
              name: 'tutorUpdate',
              component: InformationUpdate
            },
            // 리뷰 확인
            {
              path: '/reviews',
              name: 'reviewCheck',
              component: ReviewCheck
            },
            // 수익 통계
            {
              path: '/profits',
              name: '/profitCheck',
              component: ProfitCheck
            },
            // 출금
            {
              path: '/withdrawl',
              name: 'withdrawl',
              component: WithdrawlPage
            },
            // 내 과외
            {
              path: '/lectures',
              name: 'tutorMyLectures',
              component: MyLectureList
            }
          ]
        },
        {
          // 학생 마이페이지
          path: '/user',
          name: 'userMyPage',
          children: [
            // 개인정보 수정
            {
              path: '/update',
              name: 'userUpdate',
              component: StudentInformationUpdate
            },
            // 포인트 내역
            {
              path: '/points',
              name: 'pointUsage',
              component: PointUsage
            },
            // 내 과외
            {
              path: '/lectures',
              name: 'userMyLectures',
              component: StudentMyLecture
            },
            // 결제 정보
            {
              path: '/payments',
              name: 'paymentInfo',
              component: MyPaymentInfo
            }
          ]
        }
      ]
    },
    {
      // 공지사항 게시판
      path: '/notice',
      name: 'notice',
      component: Notice,
      children: [
        // 공지사항 상세
        {
          path: ':noticeNum',
          name: 'noticeDetail',
          component: DetailNotice
        }
      ]
    },
    {
      // FAQ 게시판
      path: '/faq',
      name: 'faq',
      component: FAQArticle
    },
    {
      // 과외 구하는 모집 및 홍보 게시판
      path: '/lecturespromotion',
      name: 'lecturesPromo',
      component: LectureRecruit,
      children: [
        // 모홍게 상세
        {
          path: ':promotionNum',
          name: 'lectureDetail',
          component: DetailLecture
        }
      ]
    },
    {
      // 문제 질문 게시판
      path: '/qna',
      name: 'qna',
      component: QA,
      children: [
        // 질게 상세
        {
          path: ':qnaNum',
          name: 'qnaDetail',
          component: DetailQA
        }
      ]
    },
    {
      // 튜터콜 (컴포넌트 없음)
      path: '/onlinelecture',
      name: 'onlineLecture',
      children: [
        // 개별 온라인 과외방 + 튜터콜
        {
          path: ':tutorId/:onlineLectureNum',
          name: 'onlineLectureNum',
          component: OnlineLecture
        },
        // 튜터콜 대기실
        {
          path: ':userId/waiting',
          name: 'waitingRoom',
          component: TutorCallPage
        }
      ]
    }
  ]
})

export default router
