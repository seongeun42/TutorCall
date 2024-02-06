<script setup lang="ts">
    
</script>
<template>
    <div class="w-full grid justify-items-stretch">
        <div class="rounded-full justify-self-end bg-black w-4 h-4 mr-2 relative top-[8px] text-white flex justify-center items-center">
            <p class="text-center text-white relativ top-[-8px]">x</p>
        </div>
        <div class="bg-sky-200 mx-4 mb-4 min-h-28 rounded-lg">
            <div class="flex justify-between mt-2 mr-2">
                <div class="flex flex-row ml-2">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="yellow" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M14.857 17.082a23.848 23.848 0 0 0 5.454-1.31A8.967 8.967 0 0 1 18 9.75V9A6 6 0 0 0 6 9v.75a8.967 8.967 0 0 1-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 0 1-5.714 0m5.714 0a3 3 0 1 1-5.714 0" />
                    </svg>
                    <p class="text-center text-sm font-semibold">OOO님이 문제 풀이 요청을 보냈습니다.</p>
                </div>
                <div>
                    <p class="text-center text-xs">1시간 전</p>
                </div>
            </div>
            <div class="flex flex-row justify-between mt-4">
                <div class="flex flex-col ml-5">
                    <p class="font-semibold">과목명(태그)</p>
                    <p>튜터콜 제목</p>
                </div>
                 <div class="mr-5 pt-2">
                    <p class="text-white rounded-lg bg-black p-2">자세히</p>
                        <div class="modal-box fixed top-1/3 left-1/3">
                            <p>dddddddd</p>
                        </div>
                 </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
</style>