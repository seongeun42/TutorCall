<script setup lang="ts">
import type { Ref } from 'vue';
import { watch } from 'vue';
import { ref } from 'vue';

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
    tag: number,
    tags: number[]
}

const props = defineProps<{"selectOption":TagSelect}>();
const emit = defineEmits(['update','change']);

let tag:number = 0;
const school:Ref<string> = ref(props.selectOption.school);
const subject:Ref<string> = ref(props.selectOption.subject);
const subjectDisabled: Ref<boolean> = ref(props.selectOption.subjectDisabled)
const gradeDisabled: Ref<boolean> = ref(props.selectOption.gradeDisabled)
const gradeSelected: Ref<selectform | string> = ref('')
const btnDisabled: Ref<boolean> = ref(props.selectOption.btnDisabled);
const addDisabled: Ref<boolean> = ref(false);

let grade: selectform[] = []

watch(
  () => school.value,
  (oldValue) => {
    if (Number(oldValue) == 1) {
      grade = []
      for (let i = 0; i < 6; i++) grade.push({ value: i * 5, name: `${i + 1}학년` })
      gradeDisabled.value = false
      gradeSelected.value = ''
      subjectDisabled.value = true
      subject.value = ''
    } else if (Number(oldValue) == 31 || Number(oldValue) == 46) {
      grade = []
      for (let i = 0; i < 3; i++) grade.push({ value: i * 5, name: `${i + 1}학년` })
      gradeDisabled.value = false
      gradeSelected.value = ''
      subjectDisabled.value = true
      subject.value = ''
    }
  }
)

watch(
  () => gradeSelected.value,
  (newValue, oldValue) => {
    if (Number(newValue) >= 0) {
      subjectDisabled.value = false
    }
  }
)

watch(
  () => subject.value,
  () => {
    tag = Number(school.value) + Number(gradeSelected.value) + Number(subject.value)
    btnDisabled.value = false;
  }
)
function init():void{
    btnDisabled.value = true;
    addDisabled.value = false;
}
function addTags(event: Event):void{

    event.preventDefault();
    let flag:boolean = true;
    props.selectOption.tags.forEach((d)=>{
        if(d == tag) {
        alert("중복 태그는 추가할 수 없습니다!");
        flag = false;
        return
        }
    });

    if(!flag) {
        init();
        return;
    }

    const updateTags:TagSelect={
        school: school.value,
        subject: subject.value,
        grade: gradeSelected.value,
        subjectDisabled: subjectDisabled.value,
        gradeDisabled: gradeDisabled.value,
        btnDisabled: btnDisabled.value,
        idx: props.selectOption.idx,
        tag: tag,
        tags: props.selectOption.tags
    }
    addDisabled.value = true;
    emit('update', updateTags);
}

function deleteTags(event:Event):void{
    event.preventDefault();
    emit('change', props.selectOption.idx);
}
</script>
<template>
    <div>
        <div class="mt-8 flex flex-row gap-2">
            <div class="flex flex-row w-5/6 gap-2">
                <div class="flex flex-col w-1/3">
                    <p class="text-xl mb-5">관심 학교 선택</p>
                    <select class="bg-gray-200 w-full h-20 text-lg rounded-lg" v-model="school">
                    <option value="" disabled>관심 학교 선택</option>
                    <option value="1">초등학교</option>
                    <option value="31">중학교</option>
                    <option value="46">고등학교</option>
                    </select>
                </div>
                <div class="flex flex-col w-1/3">
                    <p class="text-xl mb-5 ">관심 학년 선택</p>
                    <select class="bg-gray-200 w-full h-20 text-lg rounded-lg"
                        v-model="gradeSelected"
                        :disabled="gradeDisabled"
                    >
                        <option value="" disabled>학년 선택</option>
                        <option v-for="g in grade" v-bind:value="g.value" :key="g.value">{{ g.name }}</option>
                    </select>
                </div>
                <div class="flex flex-col w-1/3">
                    <p class="text-xl mb-5 ">관심 과목 선택</p>
                    <select class="bg-gray-200 w-full h-20 text-lg rounded-lg" v-model="subject" :disabled="subjectDisabled">
                    <option value="" disabled>관심 과목 선택</option>
                    <option value="0">국어</option>
                    <option value="1">수학</option>
                    <option value="2">과학</option>
                    <option value="3">사회</option>
                    <option value="4">영어</option>
                    </select>
                </div>
            </div>
            <div v-if="!btnDisabled" class="w-1/6 ">
                <p class="text-xl mb-5">태그 관리</p>
                <div class="bg-green-200 rounded-xl h-20 text-center flex items-center justify-center ">
                    <p v-if="!addDisabled" @click="addTags">추가</p>
                    <p v-else @click="deleteTags">삭제</p>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
</style>