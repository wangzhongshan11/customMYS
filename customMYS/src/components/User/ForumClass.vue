<template>
  <div class="user-forum-wrap">
    <div class="forum-header">
      <el-row>
        <el-col :span="1" :offset="1">
          <div @click="goBack">
            <i class="el-icon-arrow-left" style="cursor:pointer;"></i>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div>
            <span>我的社区等级</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="forum-main">
      <div style="background-color:#fff;margin-bottom:10px;padding-top:30px;">
        <el-row style="height:120px;">
          <el-col :span="24" :offset="0">
            <div class="flex-div-center-wrap" style="height:120px;">
            <div class="lv-icon-wrap">
              <!-- <i class="el-icon-info"></i> -->
              <div class="top"></div>
              <div class="middle"></div>
              <div class="bottom"></div>
              <div class="lv">Lv.{{chosenForum.uForumGrade}}</div>
            </div>
            </div>
          </el-col>
        </el-row>
        <el-row style="padding-top:20px;padding-bottom:20px;">
          <el-col :span="2" :offset="2">
            <span>Lv.{{chosenForum.uForumGrade}}</span>
          </el-col>
          <el-col :span="16">
            <el-progress
              :percentage="expPercentage | fixNumber"
              :stroke-width="15"
              :text-inside="true"
              :show-text="true"
              color="red"
            ></el-progress>
          </el-col>
          <el-col :span="2">
            <span>Lv.{{chosenForum.uForumGrade+1}}</span>
          </el-col>
        </el-row>

        <hr style="background-color:#dddddd;height:1px;border:none;" />

        <el-row style="padding-top:20px;padding-bottom:20px;">
          <el-col :span="6">
            <div>
              <span>
                近日获得经验:
                <font color="red">0</font>
              </span>
            </div>
          </el-col>
          <el-col :span="6" :offset="12">
            <div>
              <span>
                <font color="gray">今日获取明细</font>
              </span>
              <i class="el-icon-arrow-right" style="color: gray;"></i>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="user-forum-menu-wrap">
        <el-menu default-active="0" mode="horizontal" router>
          <template v-for="(item,index) in this.$router.options.routes[5].children">
            <el-menu-item class="user-forum-menu-item" :index="item.path" :key="index">{{item.name}}</el-menu-item>
          </template>
        </el-menu>
      </div>
      <div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import { getRequest } from "../../utils/api";
export default {
  data() {
    return {
      currentUser: "",
      forumname: "",
      chosenForum: "",
      forumGradeExp: "",
      expPercentage: 10,
    };
  },
  methods: {
    goBack() {
      this.$router.push({
        name: this.$store.state.subscribePrePath,
      });
    },
    getForumGradeExp() {
      var _this = this;
      getRequest("/getForumGradeExp").then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "获取经验表失败!" });
          } else {
            _this.forumGradeExp = msg.data;
            console.log(msg.data);
            _this.$store.dispatch("setForumGradeExp", _this.forumGradeExp);
            console.log(_this.forumGradeExp[_this.chosenForum.uForumGrade - 1]);
            
            _this.expPercentage =
              (100 * _this.chosenForum.uForumExperience) /
              _this.forumGradeExp[_this.chosenForum.uForumGrade - 1].exp;
          }
        },
        {
          function(msg) {
            _this.$message({ type: "error", message: "获取经验表失败!" });
          },
        }
      );
    },
  },
  mounted() {
    this.currentUser = this.$store.state.currentUser;
    this.forumname = this.$route.params.forumname;
    console.log(this.forumname);
    
    console.log(this.currentUser);

    this.currentUser.userForums.forEach((item, index) => {
      console.log(item.forumname);
      if (item.forumname == this.forumname) {
        this.chosenForum = item;
        console.log(this.chosenForum);
        
      }
    });
    this.getForumGradeExp();
    // console.log(this.expPercentage | fixNumber);
    document.getElementsByClassName('user-forum-menu-item')[0].click();
  },
};
</script>
<style>
.forum-header {
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
.forum-main {
  margin-top: 80px;
  background-color: #dddddd;
}
.user-forum-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.lv-icon-wrap {
  /* font-size: 60px; */
  width:80px;
  height:80px;
  background-color: #fff;
  position:relative;
}
.lv-icon-wrap .top {
  /* font-size: 60px; */
  width:0;
  height:0;
  border-bottom: 20px #00FF00 solid;
  border-top:none;
  border-left:40px transparent solid;
  border-right:40px transparent solid;
}
.lv-icon-wrap .middle {
  /* font-size: 60px; */
  width:80px;
  height:40px;
  background-color: #00FF00;
}
.lv-icon-wrap .bottom {
  /* font-size: 60px; */
  width:0px;
  height:0px;
  border-top: 20px #00FF00 solid;
  border-bottom:none;
  border-left:40px transparent solid;
  border-right:40px transparent solid;
}
.lv-icon-wrap .lv {
  font-size: 30px;
  color: #fff;
  position: absolute;
  top:20px;
  left:0px;
  width:80px;
  height:40px;
  line-height: 40px;
  text-align: center;
}
.progress-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
}
.user-forum-menu-wrap .el-menu {
  display: flex;
  justify-content: center;
  border-radius: 0px;
}
.el-progress-bar__innerText {
  margin-top: -3px;
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