<template>
  <div class="user-wzs-wrap" style>
    <el-row
      style="background-color:white;padding-left:3%;padding-right:3%; height:90px; padding-top:40px;"
    >
      <el-col :span="2" :offset="22">
        <div class="flex-div-center-wrap" style="height:50px;">
          <!-- <div style="height:40px;width:40px;background-color:#dddddd;border-radius:20px;"> -->
          <div class="qr-wrap">
            <!-- <i class="el-icon-remove-outline"></i> -->
            <div class="qr-b"></div>
            <div class="qr-h"></div>
            <div class="qr-s"></div>
            <div class="qr-z"></div>
            <!-- </div> -->
          </div>
        </div>
      </el-col>
    </el-row>
    <div style="background-color:white;padding-bottom:25px;padding-left:3%;padding-right:3%;">
      <el-row style="padding-bottom:15px;">
        <el-col :span="3">
          <div class="img-wrap" @click="userDetail">
            <img class="user-avatar" :src="currentUser.userface" alt="wzs" style="cursor:pointer;" />
          </div>
        </el-col>
        <el-col :span="6" :offset="1">
          <div>
            <span class="user-name">{{currentUser.nickname}}</span>

            <span class="user-id">通行证ID:{{currentUser.id}}</span>
          </div>
        </el-col>
        <el-col :span="4" :offset="10">
          <div class="zhuye-wrap">
            <span class="zhuye-wrap-span" @click="userDetail" style="cursor:pointer;">
              <font color="white">主页</font>
              <i class="el-icon-arrow-right" style="color: white;"></i>
            </span>
          </div>
        </el-col>
      </el-row>
      <hr />
      <el-row style="padding-top:15px;">
        <el-col :span="4" :offset="2">
          <div @click="userFans" style="cursor:pointer;">
            <span>{{currentUser.fans?currentUser.fans.length:'0'}}</span>
            <br />
            <span>粉丝</span>
          </div>
        </el-col>
        <el-col :span="4" :offset="4">
          <div @click="userSubscribe" style="cursor:pointer;">
            <span>{{currentUser.subscribes?currentUser.subscribes.length:'0'}}</span>
            <br />
            <span>关注</span>
          </div>
        </el-col>
        <el-col :span="4" :offset="4">
          <div>
            <span>0</span>
            <br />
            <span>获赞</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div
      style="background-color:white;margin-top:10px;padding-top:1px;padding-left:3%;padding-right:3%;padding-bottom:15px;"
    >
      <div class="forum-wrap">
        <span>社区等级</span>
      </div>

      <div class="swiper-container swiper-container-userforum" style="margin-top:10px;width:100%;">
        <div class="swiper-wrapper">
          <div
            class="swiper-slide"
            v-for="(item,index) in currentUser.userForums"
            :key="index"
            style="width:auto;margin-right:10px;"
          >
            <div>
              <img
                :src="item.forumface"
                alt="wzs"
                style="height:100px;width:170px;border-radius:10px;cursor:pointer;"
                @click="userForumClass(item.forumname)"
              />
            </div>
            <div style="color:#000000;font-size:15px;margin-top:8px;">
              <span>{{item.forumname}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div
      style="background-color:white;padding-top:5px;margin-top:10px;padding-left:3%;padding-right:3%;"
    >
      <div class="forum-wrap">
        <span>米游币</span>
        <span class="miyoubi-count-wrap">
          <i class="el-icon-star-off" style="margin-top:4px;"></i>
          <span style="margin-left:0px;">200</span>
        </span>
      </div>
      <el-row style="padding-top:10px;margin-top:5px;padding-bottom:20px;margin-bottom:5px;">
        <el-col :span="3" :offset="1" height="42px">
          <div class="miyoubi-wrap-icon" @click="UserGetMiyoubi" style="cursor:pointer;">
            <i class="el-icon-star-off"></i>
          </div>
        </el-col>
        <el-col :span="7">
          <div @click="UserGetMiyoubi" style="height:42px;cursor:pointer;">
            <div class="miyoubi-wrap">
              <div class="miyoubi-exget">
                <div style="margin-top:1px;">今日还可获得90</div>
              </div>
            </div>
            <div class="miyoubi-wrap">
              <span>获取米游币</span>
            </div>
          </div>
        </el-col>
        <el-col :span="2" :offset="0">
          <div
            class="flex-div-center-wrap"
            style="height:42px;font-size:35px;font-weight:50;color:#dddddd;"
          >
            <span style="height:42px;">|</span>
          </div>
        </el-col>
        <el-col :span="3" :offset="1">
          <div class="miyoubi-wrap-icon" @click="userExchange" style="cursor:pointer;">
            <i class="el-icon-star-on"></i>
          </div>
        </el-col>
        <el-col :span="7">
          <div class="miyoubi-wrap" @click="userExchange" style="height: 42px;cursor:pointer;">
            <span>兑换中心</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div
      style="background-color:white;margin-top:10px;margin-bottom:10px;padding-top:20px;padding-left:3%;padding-right:3%;"
    >
      <el-row style="padding-bottom:10px;cursor:pointer;">
        <div @click="userCharacterMana">
          <el-col :span="1">
            <div class="lists-wrap-icon">
              <!-- <i class="el-icon-info"></i> -->
              <i class="fa fa-users" aria-hidden="true"></i>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="lists-wrap">
              <span>角色管理</span>
            </div>
          </el-col>
          <el-col :span="1" :offset="17">
            <div class="lists-wrap-icon">
              <i class="el-icon-arrow-right"></i>
            </div>
          </el-col>
        </div>
      </el-row>
      <hr width="95%" align="right" />
      <el-row style="padding-bottom:10px;padding-top:10px;cursor:pointer;">
        <div @click="userPassCard">
          <el-col :span="1">
            <div class="lists-wrap-icon">
              <!-- <i class="el-icon-info"></i> -->
              <i class="fa fa-clipboard" aria-hidden="true"></i>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="lists-wrap">
              <span>通行证</span>
            </div>
          </el-col>
          <el-col :span="1" :offset="17">
            <div class="lists-wrap-icon">
              <i class="el-icon-arrow-right"></i>
            </div>
          </el-col>
        </div>
      </el-row>
      <hr width="95%" align="right" />
      <el-row style="padding-bottom:10px;padding-top:10px;cursor:pointer;">
        <div @click="userGameCenter">
          <el-col :span="1">
            <div class="lists-wrap-icon">
              <!-- <i class="el-icon-info"></i> -->
              <i class="fa fa-gamepad" aria-hidden="true"></i>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="lists-wrap">
              <span>游戏中心</span>
            </div>
          </el-col>
          <el-col :span="1" :offset="17">
            <div class="lists-wrap-icon">
              <i class="el-icon-arrow-right"></i>
            </div>
          </el-col>
        </div>
      </el-row>
      <hr width="95%" align="right" />
      <el-row
        style="padding-bottom:10px;padding-top:10px;margin-bottom:6px;padding-bottom:20px;cursor:pointer;"
      >
        <el-col :span="1">
          <div class="lists-wrap-icon">
            <i class="el-icon-document"></i>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="lists-wrap">
            <span>问卷调研</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="17">
          <div class="lists-wrap-icon">
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-col>
      </el-row>
    </div>
    <div
      style="background-color:white;padding-bottom:10px;padding-top:10px;margin-bottom:10px;padding-bottom:10px;cursor:pointer;padding-left:3%;padding-right:3%;"
    >
      <el-row style="padding-bottom:10px;padding-top:10px;">
        <el-col :span="1">
          <div class="lists-wrap-icon">
            <!-- <i class="el-icon-info"></i> -->
            <i class="fa fa-vimeo-square" aria-hidden="true"></i>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="lists-wrap">
            <span>认证申请</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="17">
          <div class="lists-wrap-icon">
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-col>
      </el-row>
    </div>
    <div
      style="background-color:white;padding-bottom:10px;padding-top:10px;padding-bottom:10px;padding-left:3%;padding-right:2%;margin-bottom:70px;padding-left:3%;padding-right:3%;"
    >
      <el-row style="padding-bottom:10px;padding-top:10px;cursor:pointer;">
        <div @click="userSettings">
          <el-col :span="1">
            <div class="lists-wrap-icon">
              <i class="el-icon-setting"></i>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="lists-wrap">
              <span>设置</span>
            </div>
          </el-col>
          <el-col :span="1" :offset="17">
            <div class="lists-wrap-icon">
              <i class="el-icon-arrow-right"></i>
            </div>
          </el-col>
        </div>
      </el-row>
    </div>
  </div>
</template>
<script>
import { getRequest } from "../../utils/api";
import Swiper from "swiper";
import "../../assets/swiper/swiper-bundle.css";
export default {
  methods: {
    userDetail() {
      getRequest("/user/" + this.currentUser.id).then(
        (resp) => {
          if (resp.status == 200) {
            this.$store.dispatch("setCurrentUser", resp.data);
            // console.log(resp.data);
            this.$store.dispatch("setSubscribePrePath", this.$route.name);
            this.$router.push({
              name: "UserDetail",
            });
          }
        },
        (resp) => {
          this.$store.dispatch("setSubscribePrePath", this.$route.name);
          this.$router.push({
            name: "UserDetail",
          });
          this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
    },
    userFans() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserFans",
        params: { currentUser: this.currentUser },
      });
    },
    userSubscribe() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserSubscribe",
        params: { currentUser: this.currentUser },
      });
    },
    userForumClass(forumname) {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserForumClass",
        params: { currentUser: this.currentUser, forumname: forumname },
      });
    },
    UserGetMiyoubi() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserGetMiyoubi",
        params: { currentUser: this.currentUser },
      });
    },
    userExchange() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserExchange",
        params: { currentUser: this.currentUser },
      });
    },
    userCharacterMana() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserCharacterMana",
        params: { currentUser: this.currentUser },
      });
    },
    userPassCard() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserPassCard",
        params: { currentUser: this.currentUser },
      });
    },
    userGameCenter() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserGameCenter",
      });
    },
    userSettings() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserSettings",
        params: { currentUser: this.currentUser },
      });
    },
    handleCommand(command) {
      var _this = this;
      if (command == "logout") {
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
      }
    },
  },
  mounted() {
    new Swiper(".swiper-container-userforum", {
      slidesPerView: "4",
      freeMode: true,
      centeredSlides: false,
    });
  },
  created() {
    var _this = this;

    getRequest("/currentUserName").then(
      function (msg) {
        _this.currentUserName = msg.data;
        console.log(_this.currentUserName);
      },
      function (msg) {
        _this.currentUserName = "游客";
      }
    );
    getRequest("/currentUserAvatar").then(
      function (msg) {
        _this.currentUserAvatar = msg.data;
      },
      function (msg) {
        _this.currentUserAvatar = "/img/user_avatar_default.jpg";
      }
    );
    // getRequest("/currentUser").then(
    //   function (msg) {
    //     _this.currentUser = msg.data;
    //     console.log(_this.currentUser);
    //     _this.$store.dispatch("setCurrentUser", msg.data);
    //     console.log("store");
    //     console.log(_this.$store.state.currentUser);
    //   },
    //   function (msg) {
    //     _this.currentUser = "";
    //   }
    // );

    this.currentUser = this.$store.state.currentUser;
    this.$store.state.forums = this.forums;
    console.log(this.currentUser);
    console.log(this.$store.state.currentUser);
  },
  data() {
    return {
      currentUserName: "",
      currentUserAvatar: "/img/user_avatar_default.jpg",
      currentUser: "",
      forums: [
        { forumname: "大别野" },
        { forumname: "崩坏3" },
        { forumname: "崩坏学园2" },
        { forumname: "原神" },
        { forumname: "未定事件簿" },
      ],
    };
  },
};
</script>

<style>
.user-wzs-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.user-container {
  background-color: #dddddd;
}
.qr-wrap {
  margin: 0px;
  width: 50px;
  height: 50px;
  border-radius: 25px;
  background-color: #dddddd;
  position: relative;
}
.qr-wrap .qr-b {
  border: 2px solid #666;
  width: 30px;
  height: 30px;
  border-radius: 3px;
  margin: 8px;
}
.qr-wrap .qr-h {
  background-color: #dddddd;
  width: 36px;
  height: 14px;
  position: absolute;
  left: 7px;
  top: 18px;
}
.qr-wrap .qr-s {
  background-color: #dddddd;
  width: 14px;
  height: 36px;
  position: absolute;
  left: 18px;
  top: 7px;
}
.qr-wrap .qr-z {
  background-color: #666;
  width: 24px;
  height: 2px;
  line-height: 0;
  font-size: 0;
  position: absolute;
  left: 13px;
  top: 24px;
}
.el-icon-remove-outline {
  size: 80px;
}
.img-wrap {
  text-align: center;
  height: 64px;
}
.user-avatar {
  width: 64px;
  height: 64px;
  border-radius: 50%;
}
.user-name {
  font-size: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  margin-top: 5px;
}
.user-id {
  margin-top: 5px;
  font-size: 15px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.zhuye-wrap {
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.zhuye-wrap-span {
  background-color: #3366ff;
  height: 30px;
  width: 70px;
  border-radius: 15px 0 0 15px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.div-break {
  height: 10px;
  background-color: gray;
}
.forum-wrap {
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.miyoubi-count-wrap {
  display: inline-block;
  height: 20.8px;
  line-height: 20.8px;
  border-radius: 10.4px;
  background-color: #ffff66;
  color: #ff6600;
  padding-left: 0px;
  font-size: 15px;
  width: 8%;
  margin-left: 5px;
}
.miyoubi-wrap-icon {
  height: 42px;
  font-size: 50px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.miyoubi-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.miyoubi-exget {
  font-size: 9px;
  background-color: yellow;
  display: inline-block;
  height: 14px;
  line-height: 14px;
  width: 80px;
  border-radius: 7px;
}
.lists-wrap-icon {
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.lists-wrap {
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
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
.page-container-wrap {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>