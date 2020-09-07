<template>
  <div class="user-setting-wrap">
    <div class="user-setting-header">
      <el-row>
        <el-col :span="1" :offset="1">
          <div @click="goback" style="cursor:pointer;">
            <i class="el-icon-arrow-left"></i>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div>
            <span>设置</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div
      style="background-color:white;margin-top:80px;margin-bottom:10px;padding-left:3%;padding-right:2%;"
    >
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="4" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>新消息通知</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="19">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="4" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>隐私</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="19">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>私信屏蔽名单</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>黑名单</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="background-color:white;margin-bottom:10px;padding-left:3%;padding-right:2%;">
      <el-row style="margin-top:10px;">
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>关于米游社</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>账号与安全</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>清除缓存</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <span style="color:#dddddd;">0.00M</span>
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>建议与反馈</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
      />
      <el-row>
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>检查更新</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
    </div>
    <div
      @click="logout"
      style="padding-left:3%;padding-right:2%;background-color:#fff;cursor:pointer;"
    >
      <el-row style>
        <el-col :span="5" :offset="0">
          <div class="flex-div-start-wrap" style="height:60px;">
            <span>退出登录</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="18">
          <div class="flex-div-end-wrap" style="height:60px;">
            <i class="el-icon-arrow-right" style="color:#dddddd;"></i>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import { getRequest } from "../../utils/api";
export default {
  methods: {
    logout() {
      var _this = this;
      this.$confirm("注销登录吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(
        function () {
          getRequest("/logout");
          _this.currentUserName = "游客";
          _this.$router.replace({ path: "/" });
        },
        function () {
          //取消
        }
      );
    },
    goback() {
      this.$router.push({
        name: "Userwzs",
      });
    },
  },
};
</script>
<style>
.user-setting-header {
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
.user-setting-main {
  margin-top: 80px;
  background-color: #fff;
  padding-top: 0px;
  padding-left: 3%;
  padding-right: 2%;
}
.user-setting-wrap {
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
</style>