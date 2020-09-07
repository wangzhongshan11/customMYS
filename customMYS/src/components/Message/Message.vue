<template>
  <div style="height:100%;position:absolute;top:0px;left:0px;width:100%;overflow-y:auto;">
    <div
      style="position:fixed;top:0px;left:0px;width:100%;height:80px;line-height:80px;background-color:#dddddd;font-size:20px;"
    >
      <el-row>
        <el-col :span="20" :offset="2">
          <div style="height:80px;line-height:80px;">
            <span>消息</span>
          </div>
        </el-col>
        <el-col :span="2" :offset="0">
          <div style="height:80px;line-height:80px;">
            <i class="el-icon-setting" @click="goMessageSetting()" style="cursor:pointer;"></i>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="padding-top:100px;padding-left:3%;padding-right:3%;">
      <el-row>
        <el-col :span="8" :offset="0">
          <div>
            <div class="flex-div-center-wrap">
              <div
                class="flex-div-center-wrap"
                style="width:50px;height:50px;border-radius:25px;background-color:#3399ff;line-height:50px;"
              >
                <i class="el-icon-chat-dot-square" style="font-size:30px;color:#fff;"></i>
              </div>
            </div>
            <div style="margin-top:10px;">
              <span>回复我的</span>
            </div>
          </div>
        </el-col>
        <el-col :span="8" :offset="0">
          <div>
            <div class="flex-div-center-wrap">
              <div
                class="flex-div-center-wrap"
                style="width:50px;height:50px;border-radius:25px;background-color:#3399ff;line-height:50px;"
              >
                <i class="fa fa-thumbs-o-up" aria-hidden="true" style="font-size:30px;color:#fff;"></i>
              </div>
            </div>
            <div style="margin-top:10px;">
              <span>收到的赞</span>
            </div>
          </div>
        </el-col>
        <el-col :span="8" :offset="0">
          <div>
            <div class="flex-div-center-wrap">
              <div
                class="flex-div-center-wrap"
                style="width:50px;height:50px;border-radius:25px;background-color:#3399ff;line-height:50px;"
              >
                <i class="el-icon-message-solid" style="font-size:30px;color:#fff;"></i>
              </div>
            </div>
            <div style="margin-top:10px;">
              <span>系统通知</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    goMessageSetting() {
      this.$router.push({ name: "MessageSetting" });
    },
  },
};
</script>
<style>
.flex-div-around-wrap {
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.flex-div-center-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
}
.flex-div-between-wrap {
  display: flex;
  align-items: center;
  justify-content: space-between;
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