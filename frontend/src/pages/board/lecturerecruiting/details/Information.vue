<template>
  <div class="container mx-auto bg-blue-100 pb-10 rounded-xl">
    <div class="ml-10 pt-5">
      <p>선생님 정보</p>
      <div class="flex">
        <img
          src="https://starteacher-v5-prod.s3.ap-northeast-2.amazonaws.com/tutor/profilebb06b015c44930414511bebd24f825af68f7d189947e79a5c9b1be0b27d1bb86"
          alt=""
          class="mx-10 rounded-full"
          style="width: 300px; height: 300px"
        />
        <div class="container max-w-52 mt-10 mx-20">
          <h5 class="text-2xl font-bold mb-3">투블럭의 여집합</h5>
          <div class="flex justify-between">
            <p>평점 평균</p>
            <div class="flex ml-5">
              <p>4.0</p>
            </div>
          </div>
          <div class="pt-6">
            <p class="text-sm">항목별 평점</p>
            <hr />
            <div class="flex justify-between">
              <p>전문성</p>
              <p>5.0</p>
            </div>
            <div class="flex justify-between">
              <p>강의 매너</p>
              <p>5.0</p>
            </div>
            <div class="flex justify-between">
              <p>내용 전달력</p>
              <p>5.0</p>
            </div>
          </div>
        </div>
        <div class="container">
          <div class="mt-10 mx-10">
            <p class="text-2xl font-bold">소개글</p>
            <h5 class="mt-12 font-bold text-4xl">수포자들 들어와라</h5>
          </div>
        </div>
      </div>
    </div>
    <div class="flex justify-end">
      <button class="mr-10 text-white bg-blue-900 w-32 h-10 rounded-xl font-bold">신청하기</button>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped></style>
