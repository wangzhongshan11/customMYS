<template>
  <div class="page-container-wrap" style>
    <el-row style="background-color:#fff;" v-show="false">
      <el-col :span="3" :offset="20">
        <div class="qr-wrap">
          <i class="el-icon-remove-outline"></i>
        </div>
      </el-col>
    </el-row>
    <div>
      <img src="../../../img/布洛妮娅3.jpg" alt="wzs" style="width:100%;" />
    </div>
    <div
      style="position:absolute;top:30px;left:0px;width:100%;text-align:left;height:40px;line-height:40px;font-size:30px;color:#000000;z-index:2;"
    >
      <i class="el-icon-arrow-left" style="margin-left:5%;cursor:pointer;" @click="goback"></i>
    </div>
    <div style="background-color:#dddddd;padding-bottom:25px;">
      <el-row style="padding-bottom:15px;background-color:#fff;">
        <el-col :span="3" :offset="1">
          <div class="img-wrap">
            <img class="user-avatar" :src="user.userface" alt="wzs" style="cursor:pointer;" />
          </div>
        </el-col>
        <el-col :span="12" :offset="0">
          <div>
            <span class="user-name">{{user.nickname}}</span>

            <span class="user-id">通行证ID:{{user.id}}</span>
          </div>
        </el-col>
        <el-col :span="7" :offset="0">
          <div class="zhuye-wrap">
            <span>
              <span
                v-show="false"
                style="margin-right:15px;border:solid 1px #000000;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;line-height:25px;"
              >
                <i class="el-icon-info" style="margin-right:5px;"></i>
              </span>
              <span
                v-if="!haveSubscribedUserIndex"
                style="margin-right:15px;border:solid 1px #000000;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;line-height:25px;display:inline-block;cursor:pointer;"
                @click="subscribeUser()"
              >+&nbsp;关注</span>
              <span
                v-if="haveSubscribedUserIndex"
                style="margin-right:15px;border:solid 1px #000000;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;line-height:25px;display:inline-block;cursor:pointer;"
                @click="subscribeUser()"
              >已关注</span>
            </span>
            <span
              style="margin-right:15px;border:solid 1px #000000;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;line-height:25px;"
            >
              <i class="el-icon-info" style="margin-right:5px;"></i>私信
            </span>
          </div>
        </el-col>
      </el-row>
      <div style="background-color:#fff;">
        <div style="height:30px;line-height:30px;text-align:left;padding-left:7%;">
          <i class="el-icon-info"></i>
          <span style>{{user.userSignature}}</span>
        </div>
        <div
          class="swiper-container"
          style="padding-top:10px;width:80%;margin-left:7%;margin-right:7%;"
        >
          <div class="swiper-wrapper">
            <div
              class="swiper-slide flex-div-center-wrap"
              v-for="(item,index) in forums"
              :key="index"
              style="width:auto;margin-right:15px;"
            >
              <span
                style="cursor:pointer;margin-right:15px;border:solid 1px #dddddd;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;display:inline-block;line-height:25px;"
              >{{item}}&nbsp;Lv{{forumsGrade[index]}}</span>
            </div>
          </div>
        </div>
        <hr style="background-color:#dddddd;height:0.5px;border:none;width:90%;margin-bottom:0px;" />
      </div>
      <el-row
        style="padding-top:20px;padding-bottom:20px;margin-bottom:10px;background-color:#fff;"
      >
        <el-col :span="4" :offset="2">
          <div @click="userInterfaceFans" style="cursor:pointer;">
            <span>{{fansCount}}</span>
            <br />
            <span>粉丝</span>
          </div>
        </el-col>
        <el-col :span="4" :offset="4">
          <div @click="userInterfaceSubscribes" style="cursor:pointer;">
            <span>{{subscribesCount}}</span>
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
      <div
        class="flex-div-around-wrap"
        style="border-bottom: solid 1px #dddddd;background-color:#fff;"
      >
        <el-menu
          class="flex-div-around-wrap"
          default-active="0"
          mode="horizontal"
          router
          style="border-bottom: none;width:70%;"
        >
          <template v-for="(item,index) in this.$router.options.routes[24].children">
            <el-menu-item
              class="user-interface-chiroute"
              :index="item.path"
              :key="index"
              style="font-size:17px;"
            >{{item.name}}</el-menu-item>
          </template>
        </el-menu>
      </div>
      <div>
        <transition>
          <router-view></router-view>
        </transition>
      </div>
    </div>
  </div>
</template>
<script>
import {
  postRequest,
  getRequest,
  putRequest,
  deleteRequest,
} from "../../../utils/api";
import Swiper from "swiper";
import "../../../assets/swiper/swiper-bundle.css";
import { isNotNullORBlank } from "../../../utils/utils";
export default {
  methods: {
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
    goback() {
      this.$router.push({
        name: this.$store.state.subscribePrePath,
      });
    },
    subscribeUser() {
      var _this = this;
      if (
        this.$store.state.currentUser.subscribes.some((item, index) => {
          return item.bid == this.user.id;
        })
      ) {
        console.log("取消关注");
        deleteRequest(
          "/user/cancelSub/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.user.id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "取消关注失败!" });
            } else {
              _this.$message({
                type: msg.data.status,
                message: msg.data.msg,
              });
              var currentUser = _this.$store.state.currentUser;
              currentUser.subscribes.forEach((item, index) => {
                if (item.bid == _this.user.id) {
                  currentUser.subscribes.splice(index, 1);
                }
              });
              _this.$store.dispatch("setCurrentUser", currentUser);
              _this.haveSubscribedUserIndex = false;
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "取消关注失败!" });
            },
          }
        );
      } else {
        console.log("加关注");
        postRequest(
          "/user/sub/" + _this.$store.state.currentUser.id + "/" + _this.user.id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "关注失败!" });
            } else {
              _this.$message({
                type: msg.data.respBean.status,
                message: msg.data.respBean.msg,
              });
              // console.log(msg.data.newUSubscribe);
              var currentUser = _this.$store.state.currentUser;
              currentUser.subscribes.push(msg.data.newUSubscribe);
              _this.$store.dispatch("setCurrentUser", currentUser);
              _this.haveSubscribedUserIndex = true;
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "关注失败!" });
            },
          }
        );
      }
    },
    sameRouterJump() {
      document.getElementsByClassName("user-interface-chiroute")[0].click();
    },
    userInterfaceSubscribes() {
      this.$store.dispatch("setSubscribePrePath", this.$route.path);
      this.$router.push({
        name: "UserInterfaceSubscribe",
      });
    },
    userInterfaceFans() {
      this.$store.dispatch("setSubscribePrePath", this.$route.path);
      this.$router.push({
        name: "UserInterfaceFans",
      });
    },
  },
  mounted() {
    console.log(this.$router.options.routes);

    new Swiper(".swiper-container", {
      slidesPerView: "auto",
    });
    document.getElementsByClassName("user-interface-chiroute")[0].click();
    // console.log(this.$store.state.interfaceUser);
    this.user = this.$store.state.interfaceUser;
    console.log(this.user);
    this.fansCount = this.user.fans ? this.user.fans.length : "0";
    this.subscribesCount = this.user.subscribes
      ? this.user.subscribes.length
      : "0";

    // console.log(isNotNullORBlank(this.forumsGrade));

    this.forumsGrade.forEach((item, index) => {
      if (isNotNullORBlank(this.user.userForums)) {
        this.user.userForums.forEach((item1, index1) => {
          if (item1.forumname == this.forums[index]) {
            this.forumsGrade[index] = item1.uForumGrade;
          }
        });
      }
    });
  },
  data() {
    return {
      user: "",
      fansCount: "",
      subscribesCount: "",
      forums: ["大别墅", "崩坏3", "崩坏学园2", "原神", "未定事件簿"],
      forumsGrade: [0, 0, 0, 0, 0],
      haveSubscribedUserIndex: "",
    };
  },
};
</script>

<style>
.page-container-wrap {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
  overflow-x: hidden;
}
.qr-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-end;
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
  border-radius: 5.6px 5.6px 5.6px 5.6px;
  background-color: #ffff66;
  color: #ff6600;
  padding-left: 0px;
  font-size: 10px;
  width: 8%;
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
  height: 12px;
  width: 70px;
  border-radius: 6px 6px 6px 6px;
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
.flex-div-around-wrap {
  display: flex;
  align-items: center;
  justify-content: space-around;
}
</style>