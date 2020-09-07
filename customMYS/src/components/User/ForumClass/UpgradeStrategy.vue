<template>
  <div style="background-color:#fff;padding-left:3%;padding-right:2%;">
    <el-row style="padding-top:25px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">社区签到</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:0.5px;border:none;" />
    <el-row style="padding-top:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">发主贴</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:1px;border:none;" />
    <el-row style="padding-top:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">发评论</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:1px;border:none;" />
    <el-row style="padding-top:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">点赞</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:1px;border:none;" />
    <el-row style="padding-top:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">收藏</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:0.5px;border:none;" />
    <el-row style="padding-top:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">被点赞</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:1px;border:none;" />
    <el-row style="padding-top:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">被回复</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:1px;border:none;" />
    <el-row style="padding-top:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">被收藏</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:0.5px;border:none;" />
    <el-row style="padding-top:10px;padding-bottom:10px;">
      <el-col :span="8" :offset="0">
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="3">被加精</font>
          </span>
        </div>
        <div style="display:flex; justify-content: flex-start;">
          <span style="margin-left: 15px;">
            <font size="2">经验值</font>
            <font size="2" color="red">+5</font>
            <font size="2">,</font>
            <font size="2">每日获取1次</font>
          </span>
        </div>
      </el-col>
      <el-col :span="6" :offset="6">
        <div style="height: 40px; margin-top: 12.5px">
          <el-progress :percentage="10" :stroke-width="15" :show-text="false"></el-progress>
        </div>
      </el-col>
      <el-col :span="3" :offset="1">
        <div style="height: 40px; display:flex; align-items: center; justify-content: flex-end;">
          <span style="margin-right: 15px;">
            <font size="2">0/1次</font>
          </span>
        </div>
      </el-col>
    </el-row>
    <hr style="background-color:#dddddd;height:1px;border:none;" />
  </div>
</template>
<script>
export default {};
</script>
<style>
</style>