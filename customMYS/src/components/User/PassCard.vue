<template>
  <div class="user-passcard-wrap">
    <div class="user-passcard-header">
      <el-row>
        <el-col :span="1" :offset="1">
          <div>
            <i class="el-icon-arrow-left" style="cursor:pointer;" @click="goBack"></i>
          </div>
        </el-col>
        <el-col :span="20" :offset="0">
          <div>
            <span>通行证</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="user-passcard-main">
      <div>
      <el-row style="height:50px;line-height:50px;">
        <div @click="userShipAddress" style="cursor:pointer;">
          <el-col :span="1" :offset="0" style>
            <div class="flex-div-start-wrap" style="font-size:20px;height:50px;">
              <i class="el-icon-location-outline"></i>
            </div>
          </el-col>
          <el-col :span="22" :offset="0" style>
            <div class="flex-div-start-wrap" style="height:50px;">
              <span>管理收货地址</span>
            </div>
          </el-col>
          <el-col :span="1" :offset="0" style>
            <div class="flex-div-end-wrap" style="height:50px;">
              <i class="el-icon-arrow-right"></i>
            </div>
          </el-col>
        </div>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:96%;margin-bottom:10px;float:right;"
      />
      <div style="clear:both:"></div>
      <el-row style="height:50px;line-height:50px;">
        <el-col :span="1" :offset="0" style>
          <div class="flex-div-start-wrap" style="font-size:20px;height:50px;">
            <i class="el-icon-location-outline"></i>
          </div>
        </el-col>
        <el-col :span="22" :offset="0" style>
          <div @click="userNewPassword" class="flex-div-start-wrap" style="height:50px;cursor:pointer;">
            <span>修改密码</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="0" style>
          <div class="flex-div-end-wrap" @click="userNewPassword" style="height:50px;cursor:pointer;">
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:96%;margin-bottom:10px;float:right;"
      />
      <div style="clear:both:"></div>
      <el-row style="height:50px;line-height:50px;">
        <el-col :span="1" :offset="0" style>
          <div class="flex-div-start-wrap" style="font-size:20px;height:50px;">
            <i class="el-icon-location-outline"></i>
          </div>
        </el-col>
        <el-col :span="22" :offset="0" style>
          <div class="flex-div-start-wrap" style="height:50px;">
            <span>账号安全设置</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="0" style>
          <div class="flex-div-end-wrap" style="height:50px;">
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:96%;margin-bottom:10px;float:right;"
      />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  mounted() {
    this.currentUser = this.$route.params.currentUser;
  },
  methods: {
    userShipAddress() {
      this.$router.push({
        name: "UserShipAddress",
        params: { currentUser: this.currentUser },
      });
    },
    userNewPassword() {
      this.$router.push({
        name: "UserNewPassword",
        params: { currentUser: this.currentUser },
      });
    },
    goBack() {
      this.$router.push({
        name: "Userwzs",
      });
    },
  },
  data() {
    return {
      currentUser: null,
    };
  },
};
</script>
<style>
.user-passcard-header {
  width: 100%;
  background-color: #dddddd;
  position: fixed;
  top: 0px;
  left: 0px;
  z-index: 2;
  height: 80px;
  line-height: 80px;
  font-size: 20px;
}
.user-passcard-main {
  margin-top: 80px;
  background-color: #fff;
  padding-top: 10px;
  padding-left: 3%;
  padding-right: 2%;padding-bottom: 50px;;
}
.user-passcard-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
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