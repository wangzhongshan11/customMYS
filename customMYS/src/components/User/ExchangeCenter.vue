<template>
  <div class="change-miyoubi-wrap">
    <div class="change-miyoubi-header">
      <el-row style="margin-top:0px;width:100%">
        <el-col :span="1" :offset="1">
          <div>
            <i class="el-icon-arrow-left" style="color: #fff;cursor:pointer;" @click="goback"></i>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div>
            <span style="color: white;">兑换中心</span>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="change-miyoubi-main">
      <el-row>
        <el-col :span="6" :offset="1">
          <div style="display:flex; justify-content:flex-start;">
            <span class="miyoubi-icon-wrap">
              <i class="el-icon-info" style="color: #FFFF33;"></i>
              <span style="color: white;">200</span>
            </span>
          </div>
          <div style="display:flex; align:center; justify-content:flex-start;">
            <span style="color: white;">查看兑换记录</span>
            <i class="el-icon-caret-right" style="padding-top:5px;color:white;"></i>
          </div>
        </el-col>
        <el-col :span="4" :offset="12">
          <div
            style="display:flex; align:center; align-items:center; justify-content:flex-end;height:57px"
          >
            <!-- <span class="duihuan-wrap">抽抽乐</span> -->
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
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
.change-miyoubi-header {
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
.change-miyoubi-main {
  margin-top: 80px;
  background-color: #fff;
  padding-top: 30px;
  padding-left: 3%;
  padding-right: 2%;
  background-color: #3399ff;
  height:150px;
}
.change-miyoubi-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
</style>