<template>
  <div class="user-fans-wrap">
    <div class="fans-header">
      <el-row style="width:100%;">
        <el-col :span="1" :offset="1">
          <div @click="goBack">
            <i class="el-icon-arrow-left" style="cursor:pointer;"></i>
          </div>
        </el-col>
        <el-col :span="2" :offset="9">
          <div>
            <span>粉丝</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="fans-main">
      <div style="background-color:#fff;">
        <div v-for="(item,index) in currentUser.fans" :key="index">
          <el-row>
            <el-col :span="2" :offset="0">
              <div
                class="flex-div-end-wrap"
                style="height:100px;cursor:pointer;"
                @click="goUserInterface(item.aid)"
              >
                <img
                  class="user-avatar"
                  :src="item.userface"
                  alt="wzs"
                  style="cursor:pointer;height:50px;width:50px;border-radius:25px;"
                />
              </div>
            </el-col>
            <el-col :span="20" :offset="0">
              <div
                style="height:100px;padding-left:10px;padding-top:25px;margin-bottom:-25px;cursor:pointer;"
                @click="goUserInterface(item.aid)"
              >
                <div class="flex-div-start-wrap">
                  <span>{{item.username}}</span>
                </div>
                <div class="flex-div-start-wrap" style="margin-top:10px;">
                  <span style="font-size:14px;color:#646464;">{{item.userSignature}}</span>
                </div>
              </div>
            </el-col>
            <el-col :span="2" :offset="0">
              <div class="flex-div-start-wrap" style="height:100px;">
                <span
                  class="subscribe-span-wrap"
                  style="border:solid 1px #000000;border-radius:5px;width:70px;cursor:pointer;"
                  @click="subscribeUser(index)"
                >+&nbsp;关注</span>
                <span
                  class="cancel-subscribe-span-wrap"
                  style="border:solid 1px #000000;border-radius:5px;width:70px;cursor:pointer;"
                  @click="subscribeUser(index)"
                >已关注</span>
              </div>
            </el-col>
          </el-row>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:91%;margin-bottom:10px;float:right;"
          />
          <div style="clear:both;"></div>
        </div>
        <div>
          <el-row>
            <el-col :span="2" :offset="0">
              <div class="flex-div-end-wrap" style="height:100px;cursor:pointer;">
                <img
                  class="user-avatar"
                  src="../../img/布洛妮娅.jpg"
                  alt="wzs"
                  style="cursor:pointer;height:50px;width:50px;border-radius:25px;"
                />
              </div>
            </el-col>
            <el-col :span="20" :offset="0">
              <div
                style="height:100px;padding-left:10px;padding-top:25px;margin-bottom:-25px;cursor:pointer;"
              >
                <div class="flex-div-start-wrap">
                  <span>若鸿似鹄</span>
                </div>
                <div class="flex-div-start-wrap" style="margin-top:10px;">
                  <span style="font-size:14px;color:#646464;">系统原装签名，给每一位小可爱~</span>
                </div>
              </div>
            </el-col>
            <el-col :span="2" :offset="0">
              <div class="flex-div-start-wrap" style="height:100px;">
                <span
                  class="subscribe-span-wrap"
                  style="border:solid 1px #000000;border-radius:5px;width:70px;cursor:pointer;"
                >+&nbsp;关注</span>

              </div>
            </el-col>
          </el-row>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:91%;margin-bottom:10px;float:right;"
          />
          <div style="clear:both;"></div>
        </div>
      </div>
    </div>
    <div style="font-size:15px;padding-top:40px;">
      <span>(&nbsp;~&nbsp;￣∇￣)&nbsp;~&nbsp;已经到底啦~</span>
    </div>
  </div>
</template>
<script>
import {
  postRequest,
  getRequest,
  putRequest,
  deleteRequest,
} from "../../utils/api";
import { isNotNullORBlank } from "../../utils/utils";
export default {
  mounted() {
    this.currentUser = this.$store.state.currentUser;
    this.$nextTick(() => {
      if (isNotNullORBlank(this.currentUser.fans)) {
        this.currentUser.fans.forEach((item, index) => {
          console.log(index);
          if (
            this.$store.state.currentUser.subscribes.some((item1, index1) => {
              return item1.bid == item.aid;
            })
          ) {
            document.getElementsByClassName("subscribe-span-wrap")[
              index
            ].style.display = "none";
            document.getElementsByClassName("cancel-subscribe-span-wrap")[
              index
            ].style.display = "block";
          } else {
            document.getElementsByClassName("subscribe-span-wrap")[
              index
            ].style.display = "block";
            document.getElementsByClassName("cancel-subscribe-span-wrap")[
              index
            ].style.display = "none";
          }
        });
      }
    });
  },
  methods: {
    goBack() {
      this.$router.push({
        name: this.$store.state.subscribePrePath,
      });
    },
    subscribeUser(ind) {
      var _this = this;
      var cancelSubscribeButton = document.getElementsByClassName(
        "cancel-subscribe-span-wrap"
      )[ind];
      var subscribeButton = document.getElementsByClassName(
        "subscribe-span-wrap"
      )[ind];
      if (
        this.$store.state.currentUser.subscribes.some((item, index) => {
          return item.bid == this.currentUser.fans[ind].aid;
        })
      ) {
        console.log("取消关注");
        deleteRequest(
          "/user/cancelSub/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.currentUser.fans[ind].aid
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "取消关注失败!" });
            } else {
              _this.$message({
                type: msg.data.status,
                message: msg.data.msg,
              });
              var currentUser1 = _this.$store.state.currentUser;
              currentUser1.subscribes.forEach((item, index) => {
                if (item.bid == _this.currentUser.fans[ind].aid) {
                  currentUser1.subscribes.splice(index, 1);
                }
              });
              _this.$store.dispatch("setCurrentUser", currentUser1);
              subscribeButton.style.display = "block";
              cancelSubscribeButton.style.display = "none";
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
          "/user/sub/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.currentUser.fans[ind].aid
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
              var currentUser1 = _this.$store.state.currentUser;
              currentUser1.subscribes.push(msg.data.newUSubscribe);
              _this.$store.dispatch("setCurrentUser", currentUser1);
              subscribeButton.style.display = "none";
              cancelSubscribeButton.style.display = "block";
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
    goUserInterface(id) {
      var _this = this;
      getRequest("/user/" + id).then(
        (resp) => {
          if (resp.status == 200) {
            console.log(id);

            _this.$store.dispatch("setInterfaceUser", resp.data);
            console.log(resp.data);
            _this.$store.dispatch("setSubscribePrePath", _this.$route.name);
            _this.$router.push({
              name: "UserInterface",
            });
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
    },
  },
  data() {
    return {
      currentUser: "",
    };
  },
};
</script>
<style>
.fans-header {
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
.fans-main {
  margin-top: 80px;
  background-color: #fff;
  padding-top: 0px;
  padding-left: 3%;
  padding-right: 2%;
}
.user-fans-wrap {
  background-color: #dddddd;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.fan-span-wrap {
  border-radius: 4px;
  width: 90%;
  border: 1px solid #646464;
  color: #646464;
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