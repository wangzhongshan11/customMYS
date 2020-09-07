<template>
  <div class="get-miyoubi-wrap">
    <div class="get-miyoubi-header">
      <el-row class="el-row-miyoubi">
        <el-col :span="1" :offset="1">
          <div>
            <i class="el-icon-arrow-left" style="color: #fff;cursor:pointer;" @click="goback"></i>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div>
            <span style="color: #fff;">获取米游币</span>
          </div>
        </el-col>
        <el-col :span="2" :offset="6">
          <div>
            <span style="color: #fff;">说明</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="get-miyoubi-main" style="padding: 0px;">
      <div style="background-color: #3399ff;height:120px;">
        <el-row class="el-row-miyoubi">
          <el-col :span="6" :offset="1">
            <div style="display:flex; justify-content:flex-start;">
              <span class="miyoubi-icon-wrap">
                <i class="el-icon-star-on" style="color: #FFFF33;"></i>
                <span style="color: white;">200</span>
              </span>
            </div>
            <div style="display:flex; align:center; justify-content:flex-start;">
              <span style="color: white;">查看获取记录</span>
              <i class="el-icon-caret-right" style="padding-top:5px;color:white;"></i>
            </div>
          </el-col>
          <el-col :span="4" :offset="12">
            <div
              style="display:flex; align:center; align-items:center; justify-content:flex-end;height:57px"
            >
              <span class="duihuan-wrap">兑换中心</span>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="miyoubi-task-wrap">
        <div class="get-miyoubi-menu-wrap">
          <el-menu default-active="0" mode="horizontal" router>
            <template v-for="(item,index) in this.$router.options.routes[6].children">
              <el-menu-item
                class="get-miyoubi-menu-item"
                :index="item.path"
                :key="index"
              >{{item.name}}</el-menu-item>
            </template>
          </el-menu>
        </div>
        <div>
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  mounted() {
    // console.log(this.$router.options.routes);
    document.getElementsByClassName("get-miyoubi-menu-item")[0].click();
  },
  methods: {
    goback() {
      this.$router.push({
        name: "Userwzs",
      });
    },
  },
};
</script>
<style>
.get-miyoubi-header {
  width: 100%;
  background-color: #3399ff;
  position: fixed;
  top: 0px;
  left: 0px;
  height: 80px;
  line-height: 80px;
  font-size: 20px;
  z-index: 2;
}
.get-miyoubi-main {
  margin-top: 80px;
  background-color: #fff;
  padding-top: 0px;
  padding-left: 3%;
  padding-right: 2%;
}
.get-miyoubi-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.miyoubi-icon-wrap {
  font-size: 25px;
  background-color: #338feb;
  border-radius: 0.5em 0.5em 0.5em 0.5em;
  height: 32px;
  width: 90px;
  padding-top: 4px;
}
.duihuan-wrap {
  color: white;
  border-radius: 11.4px 11.4px 11.4px 11.4px;
  border-style: solid;
  border-color: white;
  border-width: 1px;
  font-size: 10px;
  padding: 3px;
}
.miyoubi-task-wrap {
  margin-top: -30px;
  margin-left: 20px;
  margin-right: 20px;
  background-color: #fff;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  padding-bottom: 20px;
  margin-bottom: 30px;
}
.get-miyoubi-menu-wrap .el-menu {
  display: flex;
  justify-content: center;
  border-radius: 8px;
  border-bottom: none;
}
.el-row-miyoubi {
  width: 100%;
  background: #3399ff;
}
</style>