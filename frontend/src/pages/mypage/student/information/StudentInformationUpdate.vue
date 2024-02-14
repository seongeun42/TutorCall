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
        />
        <button class="">중복 확인</button>
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
      />
    </div>
    <div class="mt-8">
      <p class="text-xl mb-5">관심 과목 선택</p>
      <select class="bg-gray-200 w-full h-20 text-lg rounded-lg">
        <option value="과목1">과목1</option>
        <option value="과목2">과목2</option>
        <option value="과목3">과목3</option>
        <option value="과목4">과목4</option>
      </select>
    </div>
    <div class="mt-8">
      <p class="text-xl mb-5">알림 허용</p>
      <select class="bg-gray-200 w-full h-20 text-lg rounded-lg">
        <option value="과목1">허용</option>
        <option value="과목2">차단</option>
      </select>
    </div>
    <div class="flex justify-end">
      <button class="w-24 h-14 mt-10 bg-blue-800 text-white flex items-center justify-center">
        저장
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
const today: String = new Date().toISOString().split('T')[0]
</script>

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
