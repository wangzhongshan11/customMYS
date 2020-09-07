<template>
  <div class="user-subscribe-wrap">
    <div class="subscribes-header">
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
        <template v-for="(item,index) in this.$router.options.routes[4].children">
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
import { isNotNullORBlank } from "../../utils/utils";
export default {
  mounted() {
    // console.log(this.$router.options);
    console.log(this.$route.params);

    if (
      isNotNullORBlank(this.$route.params.from) &
      (this.$route.params.from == "newsFind")
    ) {
      document.getElementsByClassName("user-subscribe-item-wrap")[1].click();
    } else {
      document.getElementsByClassName("user-subscribe-item-wrap")[0].click();
    }
  },
  methods: {
    subscribeGoBack() {
      this.$router.push({
        name: this.$store.state.subscribePrePath,
      });
    },
  },
};
</script>
<style>
.subscribes-header {
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
.user-subscribe-wrap {
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

.flex-div-start-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.flex-div-end-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.flex-div-center-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>