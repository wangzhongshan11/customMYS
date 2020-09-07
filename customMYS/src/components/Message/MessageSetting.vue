<template>
  <div style="height:100%;position:absolute;top:0px;left:0px;width:100%;overflow-y:hidden;">
    <div
      style="position:fixed;top:0px;left:0px;width:100%;height:80px;line-height:80px;background-color:#dddddd;font-size:20px;"
    >
      <el-row>
        <el-col :span="2" :offset="0">
          <div style="height:80px;line-height:80px;">
            <i class="el-icon-arrow-left" @click="goback()" style="cursor:pointer;"></i>
          </div>
        </el-col>
        <el-col :span="20" :offset="0">
          <div style="height:80px;line-height:80px;">
            <span>新消息通知</span>
          </div>
        </el-col>
      </el-row>
      <hr
        style="background-color:#969696;height:0.5px;border:none;width:100%;margin-bottom:10px;margin-top:-30px;"
      />
    </div>

    <div
      style="background-color:#dddddd;text-align:left;padding-left:3%;padding-right:3%;padding-top:80px;"
    >
      <div style="font-size:12px;height:30px;line-height:30px;">有以下消息通知我</div>
    </div>
    <div style="padding-left:3%;padding-right:3%;">
      <div>
        <div class="message-setting-switch-wrap" style="height:60px;line-height:60px;">
          <span style="float:left;">有人给我发送私信</span>
          <el-switch
            v-model="messageToMe"
            :active-value="1"
            :inactive-value="0"
            active-color="#3399ff"
            inactive-color="#dddddd"
            style="float:right;height:60px;line-height:60px;width:60px;"
            width="60"
          ></el-switch>
          <div style="clear:both;"></div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
          />
        </div>
        <div class="message-setting-switch-wrap" style="height:60px;line-height:60px;">
          <span style="float:left;">有人评论或回复我</span>
          <el-switch
            v-model="commentToMe"
            :active-value="1"
            :inactive-value="0"
            active-color="#3399ff"
            inactive-color="#dddddd"
            style="float:right;height:60px;line-height:60px;width:60px;"
            width="60"
          ></el-switch>
          <div style="clear:both;"></div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
          />
        </div>
        <div class="message-setting-switch-wrap" style="height:60px;line-height:60px;">
          <span style="float:left;">有人给我点赞</span>
          <el-switch
            v-model="subscribeToMe"
            :active-value="1"
            :inactive-value="0"
            active-color="#3399ff"
            inactive-color="#dddddd"
            style="float:right;height:60px;line-height:60px;width:60px;"
            width="60"
          ></el-switch>
          <div style="clear:both;"></div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
          />
        </div>
        <div class="message-setting-switch-wrap" style="height:60px;line-height:60px;">
          <span style="float:left;">有人关注了我</span>
          <el-switch
            v-model="subscribeUserToMe"
            :active-value="1"
            :inactive-value="0"
            active-color="#3399ff"
            inactive-color="#dddddd"
            style="float:right;height:60px;line-height:60px;width:60px;"
            width="60"
          ></el-switch>
          <div style="clear:both;"></div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
          />
        </div>
        <div class="message-setting-switch-wrap" style="height:60px;line-height:60px;">
          <span style="float:left;">系统通知</span>
          <el-switch
            v-model="systemMessageToMe"
            :active-value="1"
            :inactive-value="0"
            active-color="#3399ff"
            inactive-color="#dddddd"
            style="float:right;height:60px;line-height:60px;width:60px;"
            width="60"
          ></el-switch>
          <div style="clear:both;"></div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
          />
        </div>
      </div>
    </div>

    <div>
    <div
      style="background-color:#dddddd;text-align:left;padding-left:3%;padding-right:3%;"
    >
      <div style="font-size:12px;height:30px;line-height:30px;">陌生人私信过滤</div>
    </div>
      <div style="padding-left:3%;padding-right:3%;">
        <div class="message-setting-switch-wrap" style="height:60px;line-height:60px;">
          <span style="float:left;">不接受未关注人私信</span>
          <el-switch
            v-model="strangerMessageToMe"
            :active-value="1"
            :inactive-value="0"
            active-color="#3399ff"
            inactive-color="#dddddd"
            style="float:right;height:60px;line-height:60px;width:60px;"
            width="60"
          ></el-switch>
          <div style="clear:both;"></div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
          />
        </div>
        <div class="message-setting-switch-wrap" style="height:60px;line-height:60px;">
          <span style="float:left;">合并未关注人私信</span>
          <el-switch
            v-model="combineStrangerMessageToMe"
            :active-value="1"
            :inactive-value="0"
            active-color="#3399ff"
            inactive-color="#dddddd"
            style="float:right;height:60px;line-height:60px;width:60px;"
            width="60"
          ></el-switch>
          <div style="clear:both;"></div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:0px;"
          />
        </div>
      </div>
              <div style="font-size:12px;color#000000;text-align:left;padding-top:10px;height:300px;padding-left:3%;padding-right:3%;background-color:#dddddd;">
          <span>开启合并后，将不通知未关注人私信</span>
        </div>
    </div>
  </div>
</template>
<script>
export default {
  mounted() {},
  methods: {
    goback() {
      this.$router.push({
        name: "Message",
      });
    },
  },
  data() {
    return {
      messageToMe: 0,
      commentToMe: 1,
      subscribeToMe: 1,
      subscribeUserToMe: 1,
      systemMessageToMe: 1,
      strangerMessageToMe: 1,
      combineStrangerMessageToMe: 1,
    };
  },
};
</script>
<style>
.message-setting-switch-wrap .el-switch__core {
  height: 30px;
  border-radius: 15px;
}
.message-setting-switch-wrap .el-switch__core:after {
  width: 26px;
  height: 26px;
  margin-left: -1px;
}
.message-setting-switch-wrap .is-checked .el-switch__core:after {
  /* transform: translate3d(30px, 0px, 0px) !important; */
  margin-left: -25px;
}


/* .message-setting-switch-wrap element.style {
    transform: translate3d(30px, 0px, 0px);
} */
</style>