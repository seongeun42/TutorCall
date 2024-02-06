<template>
  <Ckeditor :editor="editor" v-model="text" :config="editorConfig"></Ckeditor>
</template>

<script setup lang="ts">
import { ClassicEditor } from '@ckeditor/ckeditor5-editor-classic'
import { Essentials } from '@ckeditor/ckeditor5-essentials'
import { Bold, Italic } from '@ckeditor/ckeditor5-basic-styles'
import { BlockQuote } from '@ckeditor/ckeditor5-block-quote'
import { Link } from '@ckeditor/ckeditor5-link'
import { Paragraph } from '@ckeditor/ckeditor5-paragraph'
import { Indent } from '@ckeditor/ckeditor5-indent'
import { List } from '@ckeditor/ckeditor5-list'
import { MediaEmbed } from '@ckeditor/ckeditor5-media-embed'
import { Table, TableColumnResize, TableToolbar } from '@ckeditor/ckeditor5-table'
import { TextTransformation } from '@ckeditor/ckeditor5-typing'
import { Alignment } from '@ckeditor/ckeditor5-alignment'
import { useEditStore } from '@/store/editStore'
import {
  Image,
  ImageCaption,
  ImageStyle,
  ImageToolbar,
  ImageUpload,
  ImageResize
} from '@ckeditor/ckeditor5-image'

import { ref, watch, type Ref } from 'vue'


const emits = defineEmits(['update:modelValue'])
const editStore = useEditStore();
let text: Ref<string> = ref(editStore.content);


/**
 * v-model 값 연결
 */
watch(text, (newValue: string, oldValue: string) => {
  const textOnly: string = newValue.replace(/<[^>]+>/g, '')
  emits('update:modelValue', textOnly)
})

// import Upload Adapter
import UploadAdapter from '@/components/UploadAdapter'
import { onMounted } from 'vue'

// Custom Upload Adapter Plugin function
function CustomUploadAdapterPlugin(editor: any) {
  editor.plugins.get('FileRepository').createUploadAdapter = (loader: any) => {
    // Create new object and pass server url
    return new UploadAdapter(loader)
  }
}

let editor=ClassicEditor;


const editorConfig = {
  extraPlugins: [CustomUploadAdapterPlugin],
  plugins: [
    Essentials,
    Bold,
    Italic,
    Link,
    Paragraph,
    BlockQuote,
    Indent,
    List,
    Table,
    TableToolbar,
    TableColumnResize,
    TextTransformation,
    Alignment,
    Image,
    ImageCaption,
    ImageStyle,
    ImageToolbar,
    ImageUpload,
    ImageResize,
    MediaEmbed
  ],
  toolbar: {
    items: [
      'bold',
      'italic',
      'link',
      'imageUpload',
      'indent',
      'outdent',
      'numberedList',
      'bulletedList',
      'alignment',
      'blockQuote',
      'mediaEmbed',
      'undo',
      'redo',
      'insertTable'
    ]
  },
  image: {
    toolbar: [
      'imageTextAlternative',
      'toggleImageCaption',
      'imageStyle:inline',
      'imageStyle:block',
      'imageStyle:side'
    ]
  },
  table: {
    contentToolbar: [
      'tableColumn',
      'tableRow',
      'mergeTableCells',
      'tableProperties',
      'tableCellProperties'
    ]
  }
}

</script>

<style>
.ck.ck-editor {
  width: 100%;
  margin: 0 auto;
}
.ck-toolbar__items {
  align-items: center;
  justify-content: center;
}
.ck-editor__editable {
  min-height: 400px !important;
  max-height: 400px !important;
}
</style>
