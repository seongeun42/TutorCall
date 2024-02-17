<script setup lang="ts">
import type { Ref } from 'vue';
import { ref } from 'vue';
import { onMounted } from 'vue';
import TagSelectForm from '@/pages/mypage/tutor/TagSelectForm.vue';


interface selectform {
  value: number
  name: string
}

interface TagSelect{
    school: string,
    subject: string,
    grade: string|selectform,
    subjectDisabled: boolean,
    gradeDisabled: boolean,
    btnDisabled: boolean,
    idx: number,
    tag:number,
    tags: number[],
    disabled: boolean,
}
let tags:number[] = [];
let tagForms:Ref<TagSelect[]> = ref([]);

onMounted(()=>{
  const initData:TagSelect ={
    school: '',
    subject: '',
    grade: '',
    subjectDisabled: true,
    gradeDisabled: true,
    btnDisabled: true,
    idx: 0,
    tag: 0,
    tags: tags,
    disabled: false
  }
  tagForms.value.push(initData);
})
function addTagForm(so:TagSelect):void{
  
  tagForms.value[so.idx] = so;

  const initData:TagSelect ={
    school: '',
    subject: '',
    grade: '',
    subjectDisabled: true,
    gradeDisabled: true,
    btnDisabled: true,
    idx: so.idx+1,
    tag: 0,
    tags: tags,
    disabled: false
  }

  tagForms.value.push(initData);
  tags.push(so.tag);
  
}

function deleteTagForm(idx: number):void{

  tagForms.value[idx].disabled = true;
  tags = tags.filter((t)=> t!= tagForms.value[idx].tag);

}

const emit = defineEmits(['update']);

function closeModal():void{
    if(tags.length == 0){
      alert("태그는 1개 이상 선택해 주세요!");
      return;
    }
    emit('update', tags);
}

</script>
<template>
    <div>
        <div class="grid justify-items-stretch">
            <div class="justify-self-end" @click="closeModal">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
                </svg>
            </div>
        </div>
        <div class="mt-8">
            <div v-for="(tf, index) in tagForms" :key="index">
                <TagSelectForm v-if="!tf.disabled" :selectOption="tf" @update="addTagForm" @change="deleteTagForm"/>
            </div>
        </div>
        <div class="mt-8 grid justify-items-stretch">
            <div class="justify-self-end">
                <p @click="closeModal" class="m-4 p-4 bg-green-200 rounded-lg">제출하기</p>
            </div>
        </div>
    </div>
</template>
<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 배경을 투명한 검정색으로 설정 */
}
</style>