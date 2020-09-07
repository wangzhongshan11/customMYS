<template>
  <div
    class="page-container-user-detail"
    style="background-color:white;height:100%;position:absolute;top:0px;left:0px;width:100%;overflow-y:auto;"
  >
    <div
      class="user-detail-header"
      style="position:fixed;top:0px;left:0px;width:100%;height:80px;line-height:80px;background-color:rgba(255,255,255,0);z-index:2;"
    >
      <el-row>
        <el-col :span="1" :offset="1">
          <div class="flex-div-start-wrap" style="height:80px;line-height:80px;">
            <i
              class="el-icon-arrow-left"
              style="cursor:pointer;color:#fff;cursor:pointer;font-size:25px;"
              @click="goback()"
            ></i>
          </div>
        </el-col>
        <el-col :span="20" :offset="0">
          <div
            class="flex-div-center-wrap"
            style="height:80px;line-height:80px;color:rgba(0,0,0,1);"
          >
            <img
              class="user-message-header1"
              :src="currentUser.userface"
              alt="wzs"
              v-show="false"
              style="width:30px;height:30px;border-radius:15px;"
            />

            <span
              class="user-message-header2"
              style="font-size:15px;margin-left:5px;height:80px;"
              v-show="false"
            >{{currentUser.nickname}}</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class>
      <div class="xier-wrap">
        <div style="color:#fff;font-size:25px;"></div>
      </div>
      <el-card
        class="user-detail-card"
        style="margin-top:-50px;margin-left:10%;margin-right:10%;"
        :body-style="{padding:'0px'}"
      >
        <div>
          <el-row style="background-color:white;border-radius: 4px 4px 4px 4px;">
            <el-col :span="8" :offset="1">
              <div class="user-avatar1">
                <img class="user-avatar" :src="currentUser.userface" alt="wzs" />
              </div>
              <div class="user-avatar1">
                <span
                  style="font-size:18px;padding-left:0px;margin-top:-20px;"
                >{{currentUser.nickname}}</span>
              </div>
            </el-col>
            <el-col :span="4" :offset="10">
              <div class="user-wrap-col1">
                <span class="sig-edit-wrap" @click="userEditUser" style="cursor:pointer;">
                  <font color="white">编辑</font>
                </span>
              </div>
            </el-col>
          </el-row>
          <el-row style="background-color:white;;border-radius: 4px;padding-top:0px;">
            <el-col :span="23" :offset="1">
              <div class="sig-wrap">
                <i class="el-icon-info"></i>
                <span
                  style="cursor:pointer;"
                  @click="signatureAlert"
                >&nbsp;{{currentUser.userSignature}}</span>
              </div>
            </el-col>
          </el-row>
          <el-row class="fensi-row" style="background-color:white;;border-radius: 4px 4px 4px 4px;">
            <el-col :span="4" :offset="2">
              <div @click="userFans" style="cursor:pointer;">
                <span>0</span>
                <br />
                <span>粉丝</span>
              </div>
            </el-col>
            <el-col :span="4" :offset="4">
              <div @click="userSubscribe" style="cursor:pointer;">
                <span>0</span>
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
      </el-card>

      <div style="background-color:#dddddd;margin-top:0px;">
        <el-row
          style="margin-top:20px;margin-bottom:10px;padding-bottom:10px;background-color:#fff;padding-bottom:15px;"
        >
          <div
            class="swiper-container swiper-container-userforum-userdetail"
            style="margin-top:10px;width:80%;"
          >
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
        </el-row>
        <div
          class="flex-div-around-wrap"
          style="border-bottom: solid 1px #dddddd;background-color:#fff;"
        >
          <el-menu
            class="flex-div-around-wrap"
            mode="horizontal"
            router
            style="border-bottom: none;width:70%;"
          >
            <template v-for="(item,index) in this.$router.options.routes[2].children">
              <el-menu-item class="ud-router-wrap" :index="item.path" :key="index" style="font-size:17px;">{{item.name}}</el-menu-item>
            </template>
          </el-menu>
        </div>
      </div>
      <div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import { getRequest } from "../../utils/api";
import Swiper from "swiper";
import "../../assets/swiper/swiper-bundle.css";
export default {
  mounted() {
    new Swiper(".swiper-container-userforum-userdetail", {
      slidesPerView: "3",
      freeMode: true,
      centeredSlides: false,
      observer: true,
      observeParents: true,
    });
    document.getElementsByClassName(
      "page-container-user-detail"
    )[0].onscroll = this.onscrollF;
    // this.currentUser = this.$route.params.currentUser;
    // this.forums = this.$route.params.forums;
    this.forums = this.$store.state.forums;
    this.currentUser = this.$store.state.currentUser;
    console.log(this.currentUser);
    document.getElementsByClassName('ud-router-wrap')[0].click();
  },
  data() {
    return {
      currentUser: "",
      forums: "",
    };
  },
  methods: {
    goback() {
      this.$router.push({
        name: "Userwzs",
      });
    },
    userEditUser() {
      // this.$store.state.subscribePrePath = this.$route.name;
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserEditUser",
        params: { currentUser: this.currentUser },
      });
    },
    signatureAlert() {
      alert(this.currentUser.userSignature);
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
      console.log(forumname);

      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserForumClass",
        params: { currentUser: this.currentUser, forumname: forumname },
      });
    },
    onscrollF() {
      var _this = this;
      var dy = document.getElementsByClassName("page-container-user-detail")[0]
        .scrollTop;
      var userMessageHeader1 = document.getElementsByClassName(
        "user-message-header1"
      )[0];
      var userMessageHeader2 = document.getElementsByClassName(
        "user-message-header2"
      )[0];
      var userDetailHeader = document.getElementsByClassName(
        "user-detail-header"
      )[0];
      // console.log(dy);

      var menuColorOpacity = 0 + (dy / 270) * 1;
      if (menuColorOpacity > 1) {
        menuColorOpacity = 1;
      }
      if (menuColorOpacity < 0) {
        menuColorOpacity = 0;
      }
      userDetailHeader.style.backgroundColor =
        "rgba(255,255,255," + menuColorOpacity + ")";
      if (dy > 270) {
        userMessageHeader1.style.display = "block";
        userMessageHeader2.style.display = "block";
      } else {
        userMessageHeader1.style.display = "none";
        userMessageHeader2.style.display = "none";
      }
    },
    handleSelect(key, keyPath) {
      this.$router.push(key);
    },
  },
};
</script>
<style>
.xier-wrap {
  padding: 0px;
  background: url(../../img/希儿3.jpg) no-repeat 0px 0px;
  background-size: cover;
  /* margin-top: -30px; */
  height: 350px;
}
/* .user-detail-header {
  background: url(../img/希儿32.jpg) no-repeat 0px 0px;
  background-size: cover;
} */
.user-wrap-col1 {
  width: 80px;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.user-avatar {
  width: 64px;
  height: 64px;
  border-radius: 50%;
}
.user-avatar1 {
  margin-top: -15px;
  width: 180px;
  height: 80px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.sig-edit-wrap {
  background-color: #3366ff;
  display: inline-block;
  font-size: 12px;
  height: 30px;
  width: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 3px 3px 3px 3px;
}
.sig-wrap {
  margin-top: -15px;
  margin-bottom: 15px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.fensi-row {
  padding-top: 10px;
}
.el-card {
  overflow: visible;
  padding-bottom: 10px;
}
.router-link-active {
  border-bottom: 3px solid #3366ff;
  color: black;
}
a {
  text-decoration: none;
  color: black;
}
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
</style>