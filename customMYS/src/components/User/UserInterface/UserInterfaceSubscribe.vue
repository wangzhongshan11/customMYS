<template>
  <div  class="ud-post-wrap">
    <div class="fans-header">
      <el-row style="background-color:#dddddd;font-size:20px;">
        <el-col :span="1" :offset="1">
          <div @click="subscribeGoBack">
            <i class="el-icon-arrow-left" style="cursor:pointer;"></i>
          </div>
        </el-col>
        <el-col :span="2" :offset="9">
          <div>
            <span>关注</span>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#646464;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
    </div>
    <div class="el-menu-demo">
      <el-menu default-active="0" mode="horizontal" router>
        <template v-for="(item,index) in this.$router.options.routes[29].children">
          <el-menu-item
            class="user-subscribe-item-wrap"
            :index="item.path"
            :key="index"
          >{{item.name}}</el-menu-item>
        </template>
      </el-menu>
    </div>
    <div style="margin-top:150px;">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {
  mounted() {
    // console.log(this.$router.options);
    document.getElementsByClassName("user-subscribe-item-wrap")[0].click();
  },
  methods: {
    subscribeGoBack() {
      this.$router.push({
        path:this.$store.state.subscribePrePath,
      });
      // this.$router.go(-1);
    },
  },
};
</script>
<style>
.fans-header {
  width: 98%;
  background-color: #dddddd;
  position: fixed;
  top: 0px;
  left: 0px;
  z-index: 2;
  height: 80px;
  line-height: 80px;
}
.subscribe-main {
  margin: 0px;
  height: 100%;
  width: 100%;
}
.ud-post-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;

}
.kkry-wrap {
  font-size: 40px;
}
.el-menu-demo {
  position: fixed;
  top: 80px;
  left: 0px;
  z-index: 2;
  height: 80px;
  width: 98%;
}
.el-menu-demo .el-menu {
  display: flex;
  justify-content: space-between;
  border-radius: 0px;
}
.el-menu-demo .el-menu-item {
  margin-left: 20%;
  margin-right: 20%;
}
</style>