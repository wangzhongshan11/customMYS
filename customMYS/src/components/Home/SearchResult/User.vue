<template>
  <div style="background-color:#dddddd;padding-top:0px;">
    <div v-for="(item,index) in searchResultUserList" :key="index" style="background-color:#fff;">
      <div style="padding-left:3%;padding-right:2%;">
        <el-row>
          <el-col :span="2" :offset="0">
            <div
              class="flex-div-start-wrap"
              style="height:100px;cursor:pointer;"
              @click="goUserInterface(item.id)"
            >
              <img
                class="user-avatar"
                :src="item.userface"
                alt="wzs"
                style="cursor:pointer;height:50px;width:50px;border-radius:25px;"
              />
            </div>
          </el-col>
          <el-col :span="19" :offset="0">
            <div
              style="height:100px;padding-left:10px;padding-top:25px;margin-bottom:-25px;cursor:pointer;"
              @click="goUserInterface(item.id)"
            >
              <div class="flex-div-start-wrap">
                <span>{{item.nickname}}</span>
              </div>
              <div class="flex-div-start-wrap" style="margin-top:10px;">
                <span style="font-size:14px;color:#646464;">{{item.userSignature}}</span>
              </div>
            </div>
          </el-col>
          <el-col :span="3" :offset="0">
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
      </div>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:88%;margin-bottom:0px;float:right;"
      />
      <div style="clear:both;"></div>
    </div>
    <div style="padding-bottom:100%;padding-top:20px;">
      <i class="el-icon-info"></i>
      <span>&nbsp;已经到底啦~</span>
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
import { isNotNullORBlank } from "../../../utils/utils";
export default {
  mounted() {
    this.searchResultUserList = this.$store.state.searchResult.userList
      ? this.$store.state.searchResult.userList
      : "";
    this.$nextTick(() => {
      this.searchResultUserList.forEach((item, index) => {
        console.log(index);
        if (
          this.$store.state.currentUser.subscribes.some(
            (item1, index1) => {
              return item1.id == item.id;
            }
          )
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
    });
  },
  data() {
    return {
      searchResultUserList: "",
    };
  },
  methods: {
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
          return item.bid == this.searchResultUserList[ind].id;
        })
      ) {
        console.log("取消关注");
        deleteRequest(
          "/user/cancelSub/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.searchResultUserList[ind].id
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
                if (item.bid == _this.searchResultUserList[ind].id) {
                  currentUser.subscribes.splice(index, 1);
                }
              });
              _this.$store.dispatch("setCurrentUser", currentUser);
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
            _this.searchResultUserList[ind].id
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
  },
};
</script>
<style>
</style>