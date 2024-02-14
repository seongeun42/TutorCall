<script setup lang="ts">
import type { Ref } from 'vue';
import { ref } from 'vue';
import { useUserStore } from '@/store/userStore';
import * as api from '@/api/mypage/mypage'
import type { CommonResponse, ErrorResponse } from '@/interface/common/interface';
import { isAxiosError, type AxiosResponse } from 'axios';
import router from '@/router';

const userStore = useUserStore();

const nickname:Ref<string> = ref(userStore.nickname);
const password:Ref<string> = ref('');
const newPassword:Ref<string> = ref('');
const alram:Ref<string>
= ref('true');
const checkPassword:Ref<string> = ref('');


async function modifyed(event: Event):Promise<void>{

  event.preventDefault();

  if(nickname.value.length!=0 && nickname.value != userStore.nickname){
    await api.modifynickname({nickname:nickname.value})
    .then((response: AxiosResponse<CommonResponse>)=>{
      userStore.nickname= nickname.value;
    })
    .catch((error : unknown)=>{
      if(isAxiosError<ErrorResponse>(error)) alert(error.response?.data.message);
      throw error;
    })
  }
  if(password.value.length !=0 && newPassword.value.length !=0){
    if(password.value == newPassword.value){
      alert("이전 비밀번호와 변경 비밀번호가 같습니다.");
      return;
    }
    else if (newPassword.value == checkPassword.value){
      await api.modifyPassword({password:password.value, newPassword: newPassword.value})
      .catch((error : unknown)=>{
      if(isAxiosError<ErrorResponse>(error)) alert(error.response?.data.message);
      throw error;
    })
    }else{
      alert('새로운 비밀번호를 다시 확인해주세요.');
      return;
    }
  }

  await api.modifyAlert({notificationOption: alram.value})
  .catch((error : unknown)=>{
      if(isAxiosError<ErrorResponse>(error)) alert(error.response?.data.message);
      throw error;
  })

  alert('정보 수정을 완료했습니다');
  router.push({"name":"mypage"});

}

</script>
<template>
  <div class="mx-40 my-40">
    <p class="font-bold text-2xl mb-10">프로필 사진</p>
    <div class="flex mx-20">
      <img src="@/img/default_profile.png" class="w-28 h-28 rounded-full" alt="" />
      <div class="mx-10 my-3">
        <label
          for="file-upload"
          class="relative cursor-pointer rounded-md bg-white font-semibold text-indigo-600 focus-within:outline-none focus-within:ring-2 focus-within:ring-indigo-600 focus-within:ring-offset-2 hover:text-indigo-500"
        >
          <button class="border-4 border-blue-300 w-40 h-16 text-blue-500">사진 업로드</button>
          <input id="file-upload" name="file-upload" type="file" class="sr-only" />
        </label>

        <p class="text-center mt-2 text-lg">삭제</p>
      </div>
      <p class="border-2 mx-20"></p>
      <div class="mx-10">
        <p class="text-2xl mb-4">프로필 이미지 요구사항</p>
        <p class="my-2">1. 최소 400x400px</p>
        <p>2. 파일용량 최대 2MB</p>
      </div>
    </div>
    <p class="my-10 border-2"></p>
    <p class="font-bold text-2xl">사용자 정보 세부사항</p>

    <div class="mt-8">
      <p class="text-xl mb-5">닉네임</p>
      <div class="input-group">
        <input
          class="bg-gray-200 w-full h-20 text-lg rounded-lg"
          type="text"
          name=""
          id=""
          placeholder="닉네임을 입력하세요"
          v-model="nickname"
        />
      </div>
    </div>
    <div class="mt-8">
      <p class="text-xl mb-5">기존 비밀번호 확인</p>
      <input
        class="bg-gray-200 w-full h-20 text-lg rounded-lg"
        type="password"
        name=""
        id=""
        placeholder=""
        v-model="password"
      />
    </div>
    <div class="mt-8">
      <p class="text-xl mb-5">비밀번호 변경</p>
      <input
        class="bg-gray-200 w-full h-20 text-lg rounded-lg"
        type="password"
        name=""
        id=""
        placeholder=""
        v-model="newPassword"
      />
    </div>
    <div class="mt-8">
      <p class="text-xl mb-5">변경 비밀번호 확인</p>
      <input
        class="bg-gray-200 w-full h-20 text-lg rounded-lg"
        type="password"
        name=""
        id=""
        placeholder=""
        v-model="checkPassword"
      />
    </div>
    <div class="mt-8">
      <p class="text-xl mb-5">알림 허용</p>
      <select class="bg-gray-200 w-full h-20 text-lg rounded-lg" v-model="alram">
        <option value="true" selected>허용</option>
        <option value="false">차단</option>
      </select>
    </div>
    <div class="flex justify-end">
      <button class="w-24 h-14 mt-10 bg-blue-800 text-white flex items-center justify-center"
      @click="modifyed($event)">
        저장
      </button>
    </div>
  </div>
</template>

<style scoped>
input::placeholder {
  font-size: 1.2em;
  font-style: italic;
  font-weight: bold;
  text-align: center;
}
input[type='date']::before {
  content: attr(data-placeholder);
  width: 100%;
}
input[type='date']:focus::before,
input[type='date']:valid::before {
  display: none;
}
.input-group {
  display: flex;
  align-items: center;
}

.input-group input {
  flex: 1;
  margin-right: 10px;
}

.input-group button {
  background-color: #49ba8b;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
</style>
