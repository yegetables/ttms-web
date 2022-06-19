<template>
  <div>
    <el-dialog
  title="上传图片"
  :visible.sync="innerVisibleImg"
  width="30%"
  append-to-body>
  <upload-img></upload-img>

  <span slot="footer" class="dialog-footer">
    <el-button @click="innerVisibleImg = false">取 消</el-button>
    <el-button type="primary" @click="innerVisibleImg = false">确 定</el-button>
  </span>
</el-dialog>
  </div>
</template>

<script>
  import UploadImg from './UploadImg.vue'
  export default {
    components:{
      UploadImg
    },
    data(){
      return {
        innerVisibleImg:false//内弹框
      }
    }
  }
</script>

<style scoped>

</style>