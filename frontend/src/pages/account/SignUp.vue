<script setup lang="ts">
import { ref } from 'vue'
import type { Ref } from 'vue'
import SelectRole from './SelectRole.vue'
import * as api from '@/api/login/signUp'
import router from '@/router/index'
import { useUserStore } from '@/store/userStore'
import type{ EmailCodeCheck,
    NickCheck, LoginForm, SignUpForm, signUpResponse, User, AccountErrorResponse } from '@/interface/account/interface'

import type { CommonResponse, ErrorResponse } from '@/interface/common/interface'
import { isAxiosError, type AxiosResponse } from 'axios'

// import exp from 'constants'

const isSignUp: Ref<boolean> = ref(false)
const isSignIn: Ref<boolean> = ref(true)
const emailAddr: Ref<string> = ref('')
const vaildCode: Ref<string> = ref('')
const nickname: Ref<string> = ref('')
const password: Ref<string> = ref('')
const passwordCheck: Ref<string> = ref('')
const recommander: Ref<string> = ref('')
const isEmailChecked: Ref<boolean> = ref(false)
const isNickNameUsed: Ref<boolean> = ref(false)
const loginEmail: Ref<string> = ref('')
const loginPassword: Ref<string> = ref('')
const userStore = useUserStore()

const status = router.currentRoute.value.query.signUp

// init
if (status) {
  if (status == 'true') {
    isSignUp.value = true
    isSignIn.value = false
  } else {
    isSignUp.value = false
    isSignIn.value = true
  }
}

function clearRegistInputValue(): void {
  emailAddr.value = ''
  vaildCode.value = ''
  nickname.value = ''
  password.value = ''
  passwordCheck.value = ''
  recommander.value = ''
}
function toggle(): void {
  isSignUp.value = !isSignUp.value
  isSignIn.value = !isSignIn.value
}

function handleFormStatus(): void {
  isSignUp.value = !isSignUp.value
  isSignIn.value = !isSignIn.value
}

function isValidEmail(): boolean {
  const emailRegex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return emailRegex.test(emailAddr.value)
}

async function receiveEmailCode() {
  console.log('현재 입력된 이메일 값: ' + emailAddr.value)

  if (!isValidEmail()) {
    alert('이메일 형식이 아닙니다.')
    return
  }

  await api.sendEmailCode({email:emailAddr.value})
  .then((response: AxiosResponse<CommonResponse>)=>{
    if(response.status == 201) alert(response.data.message);
  })
  .catch((error: unknown)=>{
    if(isAxiosError<AccountErrorResponse>(error)){
      alert(error.response?.data.message);
    }
  })

}

async function checkEmailValidCode(){

  const param:EmailCodeCheck = {
    'email':emailAddr.value,
    'code':vaildCode.value
  };

  await api.checkCode(param)
  .then((response: AxiosResponse<CommonResponse>)=>{
    alert(response.data.message);
    isEmailChecked.value = true;
  })
  .catch((error: unknown)=>{
    if(isAxiosError<AccountErrorResponse>(error)){
      alert(error.response?.data.message);
    }
  })

}

function checkPassword(): boolean {
  if (password.value != passwordCheck.value) return false
  return true
}

async function doSignUp(event: Event) {
  event.preventDefault()

  const nickcheck: NickCheck = {
    nickname: nickname.value
  }

  await api.nickDupCheck(nickcheck).catch((error: unknown) => {
    if (isAxiosError<ErrorResponse>(error)) {
      alert(error.response?.data.message)
      isNickNameUsed.value = true
    }
  })

  const param: SignUpForm = {
    nickname: nickname.value,
    password: password.value,
    email: emailAddr.value
  }

  if (checkPassword() == true && isEmailChecked.value && !isNickNameUsed.value) {
    await api
      .signUp(param)
      .then((response: AxiosResponse<signUpResponse>) => {
        alert(response.data.message)
        isSignUp.value = false
        isSignIn.value = true
        clearRegistInputValue()
        router.push({ name: 'signform', query: { signUp: 'false' } })
        return
      })
      .catch((error: unknown) => {
        if (isAxiosError<ErrorResponse>(error)) {
          alert(error.response?.data.message)
        }
      })
  } else {
    alert('입력 다시 확인')
  }
}


async function doLogin(event: Event) {
  event.preventDefault()
  const param: LoginForm = {
    email: loginEmail.value,
    password: loginPassword.value
  }

  await api
    .login(param)
    .then((response: AxiosResponse<User>) => {
      const roleType:string = response.data.role;
      if (roleType == 'TUTOR') {
        userStore.login(true, loginEmail.value, response.data.nickname, response.data.profile, response.data.id)
      } else {
        userStore.login(false, loginEmail.value, response.data.nickname, response.data.profile, response.data.id)
      }
      router.push('/')
    })
    .catch((error: unknown) => {
      if (isAxiosError<ErrorResponse>(error)) {
        alert(error.response?.data.message)
      }
    })
}

</script>

<template>
  <div id="container" :class="['container', { 'sign-in': isSignIn, 'sign-up': isSignUp }]">
    <!-- FORM SECTION -->
    <div class="row">
      <!-- SIGN UP -->
      <div class="col align-items-center flex-col sign-up">
        <div class="form-wrapper align-items-center">
          <form>
            <div class="form sign-up">
              <div class="input-group">
                <i class="bx bxs-user"></i>
                <input type="text" placeholder="닉네임" required v-model="nickname" />
              </div>
              <div class="input-group">
                <i class="bx bxs-lock-alt"></i>
                <input type="password" placeholder="비밀번호" required v-model="password" />
              </div>
              <div class="input-group">
                <i class="bx bxs-lock-alt"></i>
                <input
                  type="password"
                  placeholder="비밀번호 확인"
                  required
                  v-model="passwordCheck"
                />
              </div>
              <div class="input-group" style="position: relative">
                <i class="bx bx-mail-send"></i>
                <input type="email" placeholder="이메일" required v-model="emailAddr" />
                <div
                  class="bg-[#6181ad] rounded-lg w-14 h-9 text-white flex items-center justify-center font-semibold"
                  style="position: absolute; top: 50%; right: 10px; transform: translateY(-50%)"
                  @click="receiveEmailCode"
                >
                  <!--@click 추가해서 이메일 인증 발송에 사용 예정-->
                  발송
                </div>
              </div>
              <div class="input-group" style="position: relative">
                <i class="bx bxs-user"></i>
                <input type="text" placeholder="이메일 인증" required v-model="vaildCode" />
                <div
                  class="bg-[#43766C] rounded-lg w-14 h-9 text-white flex items-center justify-center font-semibold"
                  style="position: absolute; top: 50%; right: 10px; transform: translateY(-50%)"
                  @click="checkEmailValidCode"
                >
                  <!--@click 추가해서 이메일 인증에 사용 예정-->
                  인증
                </div>
              </div>
              <div class="input-group">
                <i class="bx bxs-user"></i>
                <input type="text" placeholder="추천인" v-model="recommander" />
              </div>
              <button type="submit" @click="doSignUp">회원 가입</button>
              <p>
                <span> 이미 계정이 있으신가요? </span>
                <b
                  @click="toggle"
                  class="pointer"
                  style="
                    text-decoration-line: underline;
                    font-weight: bold;
                    font-size: larger;
                    margin-left: 0.5rem;
                  "
                >
                  여기서 로그인하기!
                </b>
              </p>
            </div>
          </form>
        </div>
      </div>
      <!-- END SIGN UP -->
      <!-- SIGN IN -->
      <div class="col align-items-center flex-col sign-in">
        <div class="form-wrapper align-items-center">
          <form>
            <div class="form sign-in">
              <div class="input-group">
                <i class="bx bxs-user"></i>
                <input type="email" placeholder="이메일" required v-model="loginEmail" />
              </div>
              <div class="input-group">
                <i class="bx bxs-lock-alt"></i>
                <input type="password" placeholder="비밀번호" required v-model="loginPassword" />
              </div>
              <button type="submit" @click="doLogin">로그인</button>

              <p>
                <b> or continue with </b>
                <span>
                  <div style="max-width: 45px; display: inline-flex; justify-content: center">
                    <img
                      src="@/img/google_logo.png"
                      alt="구글계정로그인"
                      style="margin-left: 5px; margin-right: 5px"
                    />
                    <img
                      src="@/img/naver_logo.png"
                      alt="네이버계정로그인"
                      style="margin-left: 5px; margin-right: 5px"
                    />
                    <img
                      src="@/img/kakao_logo.png"
                      alt="카카오계정로그인"
                      style="margin-left: 5px; margin-right: 5px"
                    />
                    <img
                      src="@/img/insta_logo.png"
                      alt="인스타계정로그인"
                      style="margin-left: 5px; margin-right: 5px"
                    />
                  </div>
                </span>
              </p>
              <p>
                <span style="font-size: small; font-weight: 900"> 계정이 없으신가요? </span>
                <b class="pointer">
                  <SelectRole @update:changeForm="handleFormStatus" />
                </b>
              </p>
            </div>
          </form>
        </div>
        <div class="form-wrapper"></div>
      </div>
      <!-- END SIGN IN -->
    </div>
    <!-- END FORM SECTION -->
    <!-- CONTENT SECTION -->
    <div class="row content-row">
      <!-- SIGN IN CONTENT -->
      <div class="col align-items-center flex-col">
        <div class="text sign-in">
          <h2 style="text-align: left">
            <div style="text-align: left; font-size: 1.5rem; font-weight: bold">
              누구나 선생님이 될 수 있는,
            </div>
            TutorCall
          </h2>
        </div>
        <div class="img sign-in"></div>
      </div>
      <!-- END SIGN IN CONTENT -->
      <!-- SIGN UP CONTENT -->
      <div class="col align-items-center flex-col">
        <div class="img sign-up"></div>
        <div class="text sign-up">
          <h2>환영합니다.</h2>
        </div>
      </div>
      <!-- END SIGN UP CONTENT -->
    </div>
    <!-- END CONTENT SECTION -->
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600&display=swap');

* {
  font-family: 'Poppins', sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.container {
  position: relative;
  max-height: 670px;
  overflow: hidden;
}

.row {
  display: flex;
  flex-wrap: wrap;
  height: 100vh;
}

.col {
  width: 50%;
}

.align-items-center {
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.form-wrapper {
  width: 100%;
  max-width: 28rem;
}

.form {
  padding: 1rem;
  background-color: #ffffff;
  border-radius: 1.5rem;
  width: 100%;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  transform: scale(0);
  transition: 0.5s ease-in-out;
  transition-delay: 1s;
}

.input-group {
  position: relative;
  width: 100%;
  margin: 1rem 0;
}

.input-group i {
  position: absolute;
  top: 50%;
  left: 1rem;
  transform: translateY(-50%);
  font-size: 1.4rem;
  color: #757575;
}

.input-group input {
  width: 100%;
  padding: 1rem 3rem;
  font-size: 1rem;
  background-color: #eae9e9;
  border-radius: 0.5rem;
  border: 0.125rem solid #ffffff;
  outline: none;
}

.input-group input:focus {
  border: 0.125rem solid #dbd6ff;
}

.form button {
  cursor: pointer;
  width: 100%;
  padding: 0.6rem 0;
  border-radius: 0.5rem;
  border: none;
  background-color: #5c5edc;
  color: #ffffff;
  font-size: 1.2rem;
  outline: none;
}

.form p {
  margin: 1rem 0;
  font-size: 0.7rem;
}

.flex-col {
  flex-direction: column;
}
.pointer {
  cursor: pointer;
}

.container.sign-in .form.sign-in,
.container.sign-in .social-list.sign-in,
.container.sign-in .social-list.sign-in > div,
.container.sign-up .form.sign-up,
.container.sign-up .social-list.sign-up,
.container.sign-up .social-list.sign-up > div {
  transform: scale(1);
}

.content-row {
  position: absolute;
  top: 0;
  left: 0;
  pointer-events: none;
  z-index: 6;
  width: 100%;
}

.text {
  margin: 4rem;
  color: #ffffff;
}

.text h2 {
  font-size: 3.5rem;
  font-weight: 800;
  margin: 2rem 0;
  transition: 1s ease-in-out;
}

.text p {
  font-weight: 600;
  transition: 1s ease-in-out;
  transition-delay: 0.2s;
}

.img img {
  width: 30vw;
  transition: 1s ease-in-out;
  transition-delay: 0.4s;
}

.text.sign-in h2,
.text.sign-in p,
.img.sign-in img {
  transform: translateX(-250%);
}

.text.sign-up h2,
.text.sign-up p,
.img.sign-up img {
  transform: translateX(250%);
}

.container.sign-in .text.sign-in h2,
.container.sign-in .text.sign-in p,
.container.sign-in .img.sign-in img,
.container.sign-up .text.sign-up h2,
.container.sign-up .text.sign-up p,
.container.sign-up .img.sign-up img {
  transform: translateX(0);
}

/* BACKGROUND */

.container::before {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  height: 100vh;
  width: 300vw;
  transform: translate(35%, 0);
  background-image: linear-gradient(to bottom, #7f7fd5, #86a8e7, #91eae4);
  transition: 1s ease-in-out;
  z-index: 6;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-bottom-right-radius: max(50vw, 50vh);
  border-top-left-radius: max(50vw, 50vh);
}

.container.sign-in::before {
  transform: translate(0, 0);
  right: 50%;
}

.container.sign-up::before {
  transform: translate(100%, 0);
  right: 50%;
}

/* RESPONSIVE */

@media only screen and (max-width: 480px) {
  .container::before,
  .container.sign-in::before,
  .container.sign-up::before {
    height: 100vh;
    border-bottom-right-radius: 0;
    border-top-left-radius: 0;
    z-index: 0;
    transform: none;
    right: 0;
  }

  .container.sign-in .col.sign-up {
    transform: translateY(100%);
  }

  .container.sign-in .col.sign-in,
  .container.sign-up .col.sign-up {
    transform: translateY(0);
  }

  .content-row {
    align-items: flex-start !important;
  }

  .content-row .col {
    transform: translateY(0);
    background-color: unset;
  }

  .col {
    width: 100%;
    position: absolute;
    padding: 2rem;
    background-color: #ffffff;
    border-top-left-radius: 2rem;
    border-top-right-radius: 2rem;
    transform: translateY(100%);
    transition: 1s ease-in-out;
  }

  .row {
    align-items: flex-end;
    justify-content: flex-end;
  }

  .form,
  .social-list {
    box-shadow: none;
    margin: 0;
    padding: 0;
  }

  .text {
    margin: 0;
  }

  .text p {
    display: none;
  }

  .text h2 {
    margin: 0.5rem;
    font-size: 2rem;
  }
}
</style>
