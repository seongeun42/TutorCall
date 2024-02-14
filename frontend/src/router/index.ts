import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '@/pages/mainpage/MainPage.vue'
import SignUp from '@/pages/account/SignUp.vue'
import MyPage from '@/pages/mypage/MyPage.vue'
import Notice from '@/pages/board/notice/Notice.vue'
import NoticeArticle from '@/pages/board/notice/NoticeArticle.vue'
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
import InquiryEditor from '@/pages/board/editor/InquiryEditor.vue'
import MatchCall from '@/pages/tutorcall/MatchCall.vue'
import StudentBoardEditor from '@/pages/board/editor/StudentBoardEditor.vue'
import TutorBoardEditor from '@/pages/board/editor/TutorBoardEditor.vue'
import MyTutorcallList from '@/pages/mypage/student/information/MyTutorcallList.vue'
import TutorCallList from '@/pages/mypage/tutor/MytutorCallList.vue'
import { useUserStore } from '@/store/userStore'
import path from 'path'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior(to, from, savedPosition) {
    return { top: 0 }
  },
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
      component: MyPage,
      children: [
        // 선생님 마이페이지
        // 개인정보 수정
        {
          path: '/tutorupdate',
          name: 'tutorUpdate',
          component: InformationUpdate,
          props: true
        },
        // 리뷰 확인
        {
          path: '/reviews',
          name: 'reviewCheck',
          component: ReviewCheck,
          props: true
        },
        // 수익 통계
        {
          path: '/profits',
          name: 'profitCheck',
          component: ProfitCheck,
          props: true

        },
        // 출금
        {
          path: '/withdrawl',
          name: 'withdrawl',
          component: WithdrawlPage,
          props: true

        },
        // 내 과외
        {
          path: '/lecturelists',
          name: 'tutorMyLectures',
          component: MyLectureList,
          props: true

        },
        {
          path:'/tutorcallList',
          name: 'tutorcalllist',
          component: TutorCallList,
        },

        // 학생 마이페이지
        // 개인정보 수정
        {
          path: '/userupdate',
          name: 'userUpdate',
          component: StudentInformationUpdate,
          props: true

        },
        // 포인트 내역
        {
          path: '/points',
          name: 'pointUsage',
          component: PointUsage,
          props: true

        },
        // 내 과외
        {
          path: '/mylectures',
          name: 'userMyLectures',
          component: StudentMyLecture,
          props: true

        },
        // 결제 정보
        {
          path: '/payments',
          name: 'paymentInfo',
          component: MyPaymentInfo,
          props: true

        },
        {
          path:'/tutorcallList',
          name: 'studenttutorcalllist',
          component: MyTutorcallList,
        }
      ]
    },
    {
      // 공지사항과 FAQ 모음
      path: '/notice',
      name: 'notice',
      component: Notice,
      children: [
        {
          // 공지사항 게시판
          path: '/articles',
          name: 'noticeArticles',
          component: NoticeArticle
        },
        // 공지사항 상세
        {
          path: '/articles/:noticeNum',
          name: 'noticeDetail',
          component: DetailNotice
        },
        {
          // FAQ 게시판
          path: '/faq',
          name: 'faq',
          component: FAQArticle
        }
      ]
    },
    {
      // 과외 구하는 모집 및 홍보 게시판
      path: '/lecturespromotion',
      name: 'lecturesPromo',
      redirect: {"name":'lectureList'},
      children: [
        // 모홍게 상세
        {
          path:'/list',
          name:'lectureList',
          component: LectureRecruit,

        },
        {
          path: ':promotionNum',
          name: 'lectureDetail',
          component: DetailLecture
        },
        {
          path:'/edit/:promotionNum',
          name:'editlecture',
          component: TutorBoardEditor,
        }
      ]
    },
    {
      // 문제 질문 게시판
      path: '/qna',
      name: 'qna',
      redirect: { name: 'qnalist' },
      children: [
        // 질게 상세
        {
          path: 'list',
          name: 'qnalist',
          component: QA
        },
        {
          path: ':qnaNum',
          name: 'qnaDetail',
          component: DetailQA
        },
        {
          path:'writeqna',
          name:'writeqna',
          component: StudentBoardEditor
        },
        {
          path:'edit/:qnaNum',
          name:'editqna',
          component: StudentBoardEditor
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
    },
    {
      path: '/inquiry',
      name: 'inquiry',
      component: InquiryEditor
    },
    {
      path: '/matchcall',
      name: 'matchcall',
      component: MatchCall,
    },
    // 학생 튜터콜 및 Q&A 에디터
    {
      path: '/problemform',
      name: 'studentRequestForm',
      component: StudentBoardEditor
    },
    // 선생님 홍보 에디터
    {
      path: '/promotionform',
      name: 'teacherPromotionForm',
      component: TutorBoardEditor
    }
  ]
})

// 컴포넌트 가드
router.beforeEach((to) => {
  const userStore = useUserStore();
  // 로그인을 안한 상태에서 회원전용 페이지에 접근하려는 경우
  if (
    !userStore.isLogin &&
    (to.name == "teacherPromotionForm" ||
      to.name == "studentRequestForm" ||
      to.name == "matchcall" ||
      to.name == "waitingRoom" ||
      to.name == "onlineLectureNum" ||
      to.name == "onlineLecture" ||
      to.name == "writeqna" ||
      to.name == "qnaDetail" ||
      to.name == "lectureDetail" ||
      to.name == "mypage")
  ) {
    alert("회원전용 기능입니다. 로그인이 필요합니다.");
    return { name: "signform" };
  }
  // 로그인을 안했거나, 학생이 아닌 선생님이 학생 마이페이지에 접근하려 할 때 라우터가드
  if (
    (!userStore.isLogin || userStore.isLogin && userStore.isTutor) && (
      to.name == "userUpdate" ||
      to.name == "pointUsage" ||
      to.name == "userMyLectures" ||
      to.name == "paymentInfo"
    )
  ) {
    alert("해당 페이지에 접근 권한이 없습니다.")
    return { name: 'main' }
  }
    // 로그인을 안했거나, 학생이 아닌 선생님이 학생 마이페이지에 접근하려 할 때 라우터가드
    if (
      (!userStore.isLogin || userStore.isLogin && !userStore.isTutor) && (
        to.name == "tutorUpdate" ||
        to.name == "reviewCheck" ||
        to.name == "profitCheck" ||
        to.name == "withdrawl" ||
        to.name == "tutorMyLectures"
      )
    ) {
      alert("해당 페이지에 접근 권한이 없습니다.")
      return { name: 'main' }
    }
});


export default router
