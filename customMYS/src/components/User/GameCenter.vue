
<template>
  <div class="game-center-wrap">
    <div class="game-center-header">
      <el-row>
        <el-col :span="1" :offset="1">
          <div>
            <i class="el-icon-arrow-left" @click="goBack" style="cursor:pointer;"></i>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div>
            <span>游戏中心</span>
          </div>
        </el-col>
        <el-col :span="4" :offset="5">
          <div>
            <span style="font-size:12px;color:#4b4b4b;">预约历史</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <hr
      style="background-color:#dddddd;height:0.5px;border:none;width:91%;margin-bottom:0px;margin-top:0px;"
    />
    <div class="game-center-main">
      <el-row style="height:110px;">
        <el-col :span="3">
          <div class="flex-div-start-wrap game-avatar-wrap" style="height:110px;">
            <img src="../../img/未定事件簿.jpg" alt="wzs" />
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height:110px;">
            <div class="flex-div-start-wrap" style="padding-top:20px;">
              <span>未定事件簿</span>
            </div>
            <div class="flex-div-start-wrap" style="margin-top:20px;">
              <span style="font-size:10px;color:#969696;">你是我心动的唯一真相</span>
            </div>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div class="flex-div-end-wrap" style="height:110px;cursor:pointer;" @click="weiding">
            <span class="download-wrap">下载</span>
          </div>
        </el-col>
      </el-row>
      <hr style="margin-left:13%;" />
      <el-row style="height:110px;">
        <el-col :span="3">
          <div class="flex-div-start-wrap game-avatar-wrap" style="height:110px;">
            <img src="../../img/原神.jpg" alt="wzs" />
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height:110px;">
            <div class="flex-div-start-wrap" style="padding-top:20px;">
              <span>原神</span>
            </div>
            <div class="flex-div-start-wrap" style="margin-top:10px;">
              <span style="font-size:10px;color:#969696;">踏入充满冒险的神奇世界</span>
            </div>
            <div class="flex-div-start-wrap" style="font-size:10px;color:#ff6600;margin-top:10px;">
              <span>347511</span>
              <span>&nbsp;人已预约</span>
            </div>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div class="flex-div-end-wrap" style="height:110px;cursor:pointer;" @click="yuanshen">
            <span class="download-wrap">预约</span>
          </div>
        </el-col>
      </el-row>
      <hr style="margin-left:13%;" />
      <el-row style="height:110px;">
        <el-col :span="3">
          <div class="flex-div-start-wrap game-avatar-wrap" style="height:110px;">
            <img src="../../img/崩坏三.jpg" alt="wzs" />
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height:110px;">
            <div class="flex-div-start-wrap" style="padding-top:20px;">
              <span>崩坏3</span>
            </div>
            <div class="flex-div-start-wrap" style="margin-top:20px;">
              <span style="font-size:10px;color:#969696;">为世界上所有的美好而战</span>
            </div>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div class="flex-div-end-wrap" style="height:110px;cursor:pointer;" @click="bbb">
            <span class="download-wrap">下载</span>
          </div>
        </el-col>
      </el-row>
      <hr style="margin-left:13%;" />
      <el-row style="height:110px;">
        <el-col :span="3">
          <div class="flex-div-start-wrap game-avatar-wrap" style="height:110px;">
            <img src="../../img/崩坏学园2.jpg" alt="wzs" />
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height:110px;">
            <div class="flex-div-start-wrap" style="padding-top:20px;">
              <span>崩坏学园2</span>
            </div>
            <div class="flex-div-start-wrap" style="margin-top:20px;">
              <span style="font-size:10px;color:#969696;">与少女们的末世物语</span>
            </div>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div class="flex-div-end-wrap" style="height:110px;cursor:pointer;" @click="bb">
            <span class="download-wrap">下载</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    weiding() {
      window.location.href = "https://wd.mihoyo.com/fab/bdsem";
    },
    yuanshen() {
      window.location.href = "https://www.yuanshen.com/?stay=1#/";
    },
    bbb() {
      window.location.href = "https://bh3.mihoyo.com/";
    },
    bb() {
      window.location.href = "https://www.benghuai.com/";
    },
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>
<style>
.game-center-header {
  width: 100%;
  background-color: #fff;
  position: fixed;
  top: 0px;
  left: 0px;
  z-index: 2;
  height: 80px;
  line-height: 80px;
  font-size: 20px;
}
.game-center-main {
  margin-top: 80px;
  background-color: #fff;
  padding-top: 0px;
  padding-left: 3%;
  padding-right: 2%;
}
.game-center-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.game-avatar-wrap {
  border-radius: 10px;
}
.game-avatar-wrap img {
  border-radius: 10px;
  height: 80px;
  width: 80px;
}
.download-wrap {
  border-radius: 4px 4px 4px 4px;
  background-color: #3366ff;
  color: white;
  padding: 2px;
  width: 50%;
  margin-right: 10px;
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