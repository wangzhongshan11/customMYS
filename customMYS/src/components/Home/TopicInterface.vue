<template>
  <div>
    <div
      class="topic-interface-bg-img"
      style="position:fixed;top:0px;left:0px;width:100%;height:250px;"
    ></div>
    <div
      class="topic-interface-bg-img1"
      style="position:fixed;top:0px;left:0px;width:100%;height:80px;line-height:80px;background-color:rgba(255,255,255,1);font-size:20px;z-index:2;"
    >
      <el-row>
        <el-col :span="2" :offset="1">
          <div class="flex-div-start-wrap" style="height:80px;line-height:80px;">
            <i
              class="el-icon-arrow-left"
              style="cursor:pointer;color:#fff;cursor:pointer;font-size:25px;"
              @click="goback()"
            ></i>
          </div>
        </el-col>
        <el-col :span="18" :offset="0">
          <div style="height:80px;line-height:80px;color:rgba(255,255,255,0);">
            <span class="topic-name-span">{{topic.topicname}}</span>
          </div>
        </el-col>
        <el-col :span="2" :offset="0">
          <div
            class="subscribe-span"
            v-show="false"
            style="height:80px;line-height:80px;color:#fff;"
          >
            <span
              v-if="haveSubscribedTopicIndex"
              style="font-size:15px;cursor:pointer;background-color:rgba(221,221,221,0.5);border-radius:3px;width:90%;color:#fff;display:inline-block;height:25px;line-height:25px;cursor:pointer;"
              @click="subscribeTopic()"
            >已关注</span>

            <span
              v-if="!haveSubscribedTopicIndex"
              style="font-size:15px;cursor:pointer;background-color:rgba(221,221,221,0.5);border-radius:3px;width:90%;color:#fff;display:inline-block;height:25px;line-height:25px;cursor:pointer;"
              @click="subscribeTopic()"
            >+&nbsp;关注</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="page-container-topic-interface">
      <el-row style="margin-top:75px;height:170px;padding-top:50px;">
        <el-col :span="3" :offset="1">
          <div class="flex-div-start-wrap">
            <img
              :src="topic.topicface"
              alt="wzs"
              style="height:80px;width:80px;border-radius:5px;float:left;"
            />
          </div>
        </el-col>
        <el-col :span="17" :offset="0">
          <div>
            <div class="flex-div-start-wrap" style="height:40px;line-height:40px;">
              <span style="font-size:15px;color:#000000;color:#fff;">{{topic.topicname}}</span>
            </div>
            <div class="flex-div-start-wrap" style="height:40px;line-height:40px;">
              <span style="font-size:12px;color:#000000;color:#fff;">{{topic.topicSignature}}</span>
            </div>
          </div>
        </el-col>
        <el-col :span="2" :offset="0">
          <div class="flex-div-center-wrap" style="color:#dddddd;font-size:15px;">
            <span
              v-if="haveSubscribedTopicIndex"
              style="font-size:15px;cursor:pointer;background-color:rgba(221,221,221,0.5);border-radius:3px;width:90%;color:#fff;display:inline-block;height:25px;line-height:25px;cursor:pointer;"
              @click="subscribeTopic()"
            >已关注</span>
            <span
              v-if="!haveSubscribedTopicIndex"
              style="font-size:15px;cursor:pointer;background-color:rgba(221,221,221,0.5);border-radius:3px;width:90%;color:#fff;display:inline-block;height:25px;line-height:25px;cursor:pointer;"
              @click="subscribeTopic()"
            >+&nbsp;关注</span>
          </div>
        </el-col>
      </el-row>
      <div style="clear:both;"></div>
      <div
        class="topic-interface-menu-wrap"
        style="border-top-left-radius:5px;border-top-right-radius:5px;"
      >
        <el-menu default-active="0" mode="horizontal" router style="height:60px;">
          <template v-for="(item,index) in this.$router.options.routes[28].children">
            <el-menu-item
              class="topic-interface-menu-item-wrap"
              :index="item.path"
              :key="index"
            >{{item.name}}</el-menu-item>
          </template>
        </el-menu>
      </div>
      <div
        class="topic-interface-router-view"
        style="padding-left:3%;padding-right:3%;background-color:rgba(255,255,255,1);"
      >
        <router-view></router-view>
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
} from "../../utils/api";
export default {
  mounted() {
    // console.log(this.$router.options.routes);
    this.topic = this.$store.state.interfaceTopic;
    console.log(this.topic);

    document
      .getElementsByClassName("topic-interface-menu-item-wrap")[0]
      .click();
    console.log(this.$route.params.preRouterPath);
    this.preRouterPath = this.$route.params.preRouterPath;
    document.getElementsByClassName(
      "page-container-topic-interface"
    )[0].onscroll = this.onscrollF;
  },
  methods: {
    goback() {
      // console.log(this.$route.params.preRouterPath);
      // this.$router.push({ name: "发现" });
      this.$router.push({name:this.$store.state.subscribePrePath});
    },
    onscrollF() {
      var dy = document.getElementsByClassName(
        "page-container-topic-interface"
      )[0].scrollTop;
      var menuWrap = document.getElementsByClassName(
        "topic-interface-menu-wrap"
      )[0];
      //   console.log(dy);
      var menuColorOpacity = 0 + (dy / 165) * 1;
      if (menuColorOpacity > 1) {
        menuColorOpacity = 1;
      }
      if (menuColorOpacity < 0) {
        menuColorOpacity = 0;
      }
      document.getElementsByClassName("topic-name-span")[0].style.color =
        "rgba(255,255,255," + menuColorOpacity + ")";
      if (dy >= 70) {
        document.getElementsByClassName("subscribe-span")[0].style.display =
          "block";
      } else {
        document.getElementsByClassName("subscribe-span")[0].style.display =
          "none";
      }
      if (dy >= 165) {
        menuWrap.style.position = "fixed";
        menuWrap.style.top = "80px";
        menuWrap.style.left = "0px";
        menuWrap.style.width = "98%";
        menuWrap.style.zIndex = "2";
        menuWrap.classList.add("suspension-havenot-radius");
        document.getElementsByClassName(
          "topic-interface-router-view"
        )[0].style.marginTop = "61px";
      } else {
        menuWrap.style.position = "";
        menuWrap.style.width = "100%";
        menuWrap.classList.add("suspension-have-radius");
        document.getElementsByClassName(
          "topic-interface-router-view"
        )[0].style.marginTop = "0px";
      }
    },
    subscribeTopic() {
      var _this = this;
      if (
        this.$store.state.currentUser.subscribeTopics.some((item1, index1) => {
          return item1.id == this.topic.id;
        })
      ) {
        console.log("取消关注");
        deleteRequest(
          "/user/cancelSub/topic/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.topic.id
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
                if (item1.id == _this.topic.id) {
                  currentUser.subscribeTopics.splice(index1, 1);
                }
              });
              _this.$store.dispatch("setCurrentUser", currentUser);
              // _this.haveSubscribedTopicIndex[index] = false;
              _this.haveSubscribedTopicIndex = false;
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
            _this.topic.id
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
              currentUser.subscribeTopics.push(_this.topic);
              _this.$store.dispatch("setCurrentUser", currentUser);
              // _this.haveSubscribedTopicIndex[index] = true;
              _this.haveSubscribedTopicIndex = true;
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
  data() {
    return {
      preRouterPath: "",
      topic: "",
      haveSubscribedTopicIndex: true,
    };
  },
};
</script>
<style>
.page-container-topic-interface {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.topic-interface-bg-img {
  background: url(../../img/希儿3.jpg) no-repeat 0px 0px;
  background-size: cover;
}
.topic-interface-bg-img1 {
  background: url(../../img/希儿31.jpg) no-repeat 0px 0px;
  background-size: cover;
}
.topic-interface-menu-wrap .el-menu {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fff;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
.topic-interface-menu-wrap .el-menu-item {
  margin-left: 5%;
  margin-right: 5%;
  font-size: 20px;
}
/* .topic-interface-menu-wrap .el-menu--horizontal .el-menu-item:focus,
.el-menu--horizontal .el-menu-item:hover {
  background-color: #dddddd;
} */
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
.flex-div-between-wrap {
  display: flex;
  align-items: center;
  justify-content: space-between;
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
.suspension-have-radius {
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
.suspension-havenot-radius {
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
}
</style>