<template>
  <div style="text-align:center;margin:20px">
    <el-pagination
        v-model:current-page="currentPage"
        :page-size="pageSize"
        :total="total" background
        layout="total,prev, pager, next,jumper"
        @current-change="changePage">
    </el-pagination>
  </div>
</template>

<script>
export default {
  props: {
    total: {
      type: Number,
      default: 0
    },
    pageSize: {
      type: Number,
      default: 5
    },
    currentPage: {
      type: Number,
      default: 1
    }
  },
  methods: {
    changePage(page) {
      this.$emit('changePage', page)
    }
  }
}
</script>

<style scoped>

</style>