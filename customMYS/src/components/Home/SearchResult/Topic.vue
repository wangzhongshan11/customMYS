<template>
  <div style="background-color:#dddddd;padding-top:0px;">
    <div
      style="background-color:#fff;padding-left:3%;padding-right:2%;"
      v-for="(item,index) in searchTopicList"
      :key="index"
    >
      <div>
        <el-row>
          <el-col :span="3" :offset="0">
            <div class="flex-div-start-wrap" style="height:100px;">
              <img
                class="user-avatar"
                :src="item.topicface"
                alt="wzs"
                style="cursor:pointer;height:80px;width:80px;border-radius:10px;cursor:pointer;"
                @click="goTopicInterface(item.id)"
              />
            </div>
          </el-col>
          <el-col :span="18" :offset="0">
            <div style="height:100px;padding-left:10px;padding-top:15px;margin-bottom:-25px;cursor:pointer;" @click="goTopicInterface(item.id)">
              <div class="flex-div-start-wrap" style="margin-top:-5px;">
                <span>{{item.topicname}}</span>
              </div>
              <div class="flex-div-start-wrap" style="margin-top:10px;">
                <span style="font-size:14px;color:#646464;">{{item.topicSignature}}</span>
              </div>
              <div
                class="flex-div-start-wrap"
                style="margin-top:10px;font-size:14px;color:#dddddd;"
              >
                <span style="height:20px;line-height:20px;">{{item.viewCount}}</span>
                <span style="height:20px;line-height:20px;">&nbsp;浏览&nbsp;/&nbsp;</span>
                <span style="height:20px;line-height:20px;">86</span>
                <span style="height:20px;line-height:20px;">&nbsp;讨论</span>
              </div>
            </div>
          </el-col>
          <el-col :span="3" :offset="0">
            <div class="flex-div-start-wrap" style="height:100px;">
              <span
                class="subscribe-span-wrap"
                style="border:solid 1px #000000;border-radius:5px;width:70px;cursor:pointer;"
                @click="subscribeTopic(index)"
              >+&nbsp;关注</span>
              <span
                class="cancel-subscribe-span-wrap"
                style="border:solid 1px #000000;border-radius:5px;width:70px;cursor:pointer;"
                @click="subscribeTopic(index)"
              >已关注</span>
            </div>
          </el-col>
        </el-row>
        <hr
          style="background-color:#dddddd;height:0.5px;border:none;width:86%;margin-bottom:10px;float:right;"
        />
        <div style="clear:both;"></div>
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
import { isNotNullORBlank } from "../../../utils/utils";
export default {
  mounted() {
    this.searchTopicList = this.$store.state.searchResult.topicList;
    // console.log(this.searchTopicList);
    this.$nextTick(() => {
      console.log("nextTick");
      if (isNotNullORBlank(this.searchTopicList)) {
      this.searchTopicList.forEach((item, index) => {
        console.log(index);

        if (
          this.$store.state.currentUser.subscribeTopics.some(
            (item1, index1) => {
              return item1.id == item.id;
            }
          )
        ) {
          // this.haveSubscribedTopicIndex[index] = true;
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
      });}
    });
  },
  data() {
    return {
      searchTopicList: "",
      haveSubscribedTopicIndex: new Array(50),
    };
  },
  methods: {
    subscribeTopic(index) {
      var _this = this;
            var cancelSubscribeButton = document.getElementsByClassName(
        "cancel-subscribe-span-wrap"
      )[index];
      var subscribeButton = document.getElementsByClassName(
        "subscribe-span-wrap"
      )[index];
      if (
        this.$store.state.currentUser.subscribeTopics.some((item1, index1) => {
          return item1.id == this.searchTopicList[index].id;
        })
      ) {
        console.log("取消关注");
        deleteRequest(
          "/user/cancelSub/topic/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.searchTopicList[index].id
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
              currentUser.subscribeTopics.forEach((item1, index1) => {
                if (item1.id == _this.searchTopicList[index].id) {
                  currentUser.subscribeTopics.splice(index1, 1);
                }
              });
              _this.$store.dispatch("setCurrentUser", currentUser);
              // _this.haveSubscribedTopicIndex[index] = false;
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
          "/user/sub/topic/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.searchTopicList[index].id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "关注失败!" });
            } else {
              _this.$message({
                type: msg.data.status,
                message: msg.data.msg,
              });
              var currentUser = _this.$store.state.currentUser;
              currentUser.subscribeTopics.push(_this.searchTopicList[index]);
              _this.$store.dispatch("setCurrentUser", currentUser);
              // _this.haveSubscribedTopicIndex[index] = true;
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
    goTopicInterface(id) {
      var _this = this;
      getRequest("/article/topic/" + id).then(
        (resp) => {
          if (resp.status == 200) {
            console.log(id);

            _this.$store.dispatch("setInterfaceTopic", resp.data);
            console.log(resp.data);
            _this.$store.dispatch("setSubscribePrePath", _this.$route.name);
            _this.$router.push({
              name: "TopicInterface",
            });
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
    },
  },
};
</script>
<style>
</style>