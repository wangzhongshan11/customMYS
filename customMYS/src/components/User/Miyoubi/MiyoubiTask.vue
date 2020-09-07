<template>
  <div style="padding-left:3%;padding-right:2%;">
    <el-row>
      <div style="display:flex; justify-content:flex-start;padding-top:30px;">
        <span style="font:bold;">每日任务</span>
      </div>
      <div style="display:flex; justify-content:flex-start;padding-top:5px;padding-bottom:10px;">
        <span style="font-size: 5px;">今日获取米游币上限 0/110</span>
      </div>
    </el-row>
    <el-row style="margin-top: 10px; ">
      <el-col :span="2" :offset="0">
        <div style="display:flex; align-items:center; justify-content:flex-start;">
          <span style="font-size:40px;">
            <i class="fa fa-check-square-o" aria-hidden="true"></i>
          </span>
        </div>
      </el-col>
      <el-col :span="8" :offset="0">
        <div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;">社区签到</span>
            <span style="font-size:5px; margin-left:7px;cursor:pointer;" @click="rewareRules">
              奖励规则
              <i class="el-icon-info" style="cursor:pointer;" @click="rewareRules"></i>
            </span>
          </div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;color:#FF6600;">+30米游币</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="7">
        <div>
          <div style="display:flex; justify-content:flex-end;">
            <span>0/1</span>
          </div>
          <div>
            <el-progress
              :percentage="10"
              :stroke-width="15"
              :show-text="false"
              style="width:40%;float:right;"
            ></el-progress>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-top: 10px; ">
      <el-col :span="2" :offset="0">
        <div style="display:flex; align-items:center; justify-content:flex-start;">
          <span style="font-size:40px;">
            <i class="el-icon-tickets"></i>
          </span>
        </div>
      </el-col>
      <el-col :span="8" :offset="0">
        <div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;">浏览3个帖子</span>
          </div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;color:#FF6600;">+30米游币</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="7">
        <div>
          <div style="display:flex; justify-content:flex-end;">
            <span>0/1</span>
          </div>
          <div>
            <el-progress
              :percentage="10"
              :stroke-width="15"
              :show-text="false"
              style="width:40%;float:right;"
            ></el-progress>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-top: 10px; ">
      <el-col :span="2" :offset="0">
        <div style="display:flex; align-items:center; justify-content:flex-start;">
          <span style="font-size:40px;">
            <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
          </span>
        </div>
      </el-col>
      <el-col :span="8" :offset="0">
        <div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;">完成5次点赞</span>
          </div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;color:#FF6600;">+30米游币</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="7">
        <div>
          <div style="display:flex; justify-content:flex-end;">
            <span>0/1</span>
          </div>
          <div>
            <el-progress
              :percentage="10"
              :stroke-width="15"
              :show-text="false"
              style="width:40%;float:right;"
            ></el-progress>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-top: 10px; ">
      <el-col :span="2" :offset="0">
        <div style="display:flex; align-items:center; justify-content:flex-start;">
          <span style="font-size:40px;">
           <i class="fa fa-share-square" aria-hidden="true"></i>
          </span>
        </div>
      </el-col>
      <el-col :span="8" :offset="0">
        <div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;">分享帖子</span>
          </div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;color:#FF6600;">+30米游币</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="7">
        <div>
          <div style="display:flex; justify-content:flex-end;">
            <span>0/1</span>
          </div>
          <div>
            <el-progress
              :percentage="10"
              :stroke-width="15"
              :show-text="false"
              style="width:40%;float:right;"
            ></el-progress>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-top:10px;">
      <div style="display:flex; justify-content:flex-start;padding-top:30px;padding-bottom:10px;">
        <span style="font:bold;">更多任务</span>
      </div>
    </el-row>

    <el-row style="margin-top: 10px; ">
      <el-col :span="2" :offset="0">
        <div style="display:flex; align-items:center; justify-content:flex-start;">
          <span style="font-size:40px;">
            <i class="fa fa-gamepad" aria-hidden="true"></i>
          </span>
        </div>
      </el-col>
      <el-col :span="8" :offset="0">
        <div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;">绑定游戏角色</span>
          </div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;color:#FF6600;">+30米游币</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="7">
        <div>
          <div style="height:41.6px;display:flex; align-items:center; justify-content:flex-end;">
            <span class="already-complete">已完成</span>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-top: 10px; ">
      <el-col :span="2" :offset="0">
        <div style="display:flex; align-items:center; justify-content:flex-start;">
          <span style="font-size:40px;">
            <i class="el-icon-s-custom"></i>
          </span>
        </div>
      </el-col>
      <el-col :span="8" :offset="0">
        <div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;">完成实名认证</span>
          </div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;color:#FF6600;">+30米游币</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="7">
        <div>
          <div style="height:41.6px;display:flex; align-items:center; justify-content:flex-end;">
            <span class="already-complete">已完成</span>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-top: 10px; ">
      <el-col :span="2" :offset="0">
        <div style="display:flex; align-items:center; justify-content:flex-start;">
          <span style="font-size:40px;">
            <i class="el-icon-edit"></i>
          </span>
        </div>
      </el-col>
      <el-col :span="8" :offset="0">
        <div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;">修改个性签名</span>
          </div>
          <div style="display:flex; align-items:center; justify-content:flex-start;">
            <span style="margin-left:7px;color:#FF6600;">+30米游币</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="7">
        <div>
          <div style="height:41.6px;display:flex; align-items:center; justify-content:flex-end;">
            <span class="go-complete">去完成</span>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  methods: {
    rewareRules() {
      this.$alert(
        '<hr><div style="display:flex;justify-content:flex-start;margin-top:20px;"><span style="margin-left: 0px;">普通签到</span><span style="margin-left:230px;color:#FF9900;">+30米游币</span></div><div style="display:flex;justify-content:flex-start;margin-top:20px;"><span style="margin-left: 0px;">连续签到满3天</span><span style="margin-left:194px;color:#FF9900;">+40米游币</span></div><div style="display:flex;justify-content:flex-start;margin-top:20px;"><span style="margin-left: 0px;">连续签到满5天</span><span style="margin-left:194px;color:#FF9900;">+30米游币</span></div><div style="display:flex;justify-content:flex-start;margin-top:17px;"><span style="color:#969696">*任意社区签到均有效</span></div>',
        "奖励规则",
        {
          dangerouslyUseHTMLString: true,
          center: true,
          showConfirmButton: false,
        }
      );
    },
  },
};
</script>
<style>
.already-complete {
  border-radius: 0.5em 0.5em 0.5em 0.5em;
  background-color: #969696;
  color: white;
  padding: 5px;
}

.go-complete {
  border-radius: 0.5em 0.5em 0.5em 0.5em;
  color: #3399ff;
  border-style: solid;
  border-width: 1px;
  border-color: #3399ff;
  padding: 3.5px;
}
</style>