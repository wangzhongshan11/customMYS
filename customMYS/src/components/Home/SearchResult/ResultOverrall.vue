<template>
  <div style="background-color:#dddddd;">
    <div style="background-color:#fff;padding-left:3%;padding-right:2%;">
      <div
        style="text-align:left;font-weight:bold;font-size:20px;padding-top:20px;padding-bottom:10px;;"
      >
        相关用户
        <span
          style="float:right;font-size:15px;color:#dddddd;font-weight:normal;cursor:pointer;"
          @click="allSearchResultUser"
        >
          <span>全部</span>
          <i class="el-icon-arrow-right"></i>
        </span>
      </div>
      <div
        class="swiper-container swiper-container-result-overrall"
        style="padding-top:15px;padding-bottom:15px;width:100%;"
      >
        <div class="swiper-wrapper">
          <div
            class="swiper-slide"
            v-for="(item,index) in searchResult.userList"
            :key="index"
            style="width:auto;margin-right:0px;"
            @click="goUserInterface(item.id)"
          >
            <div>
              <img
                :src="item.userface"
                alt="wzs"
                style="height:60px;width:60px;border-radius:30px;cursor:pointer;"
              />
            </div>
            <div style="color:#000000;font-size:15px;margin-top:8px;">
              <span>{{item.nickname}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div style="background-color:#fff;margin-top:10px;padding-left:3%;padding-right:2%;">
      <div
        style="text-align:left;font-weight:bold;font-size:20px;padding-top:20px;padding-bottom:10px;;"
      >
        相关话题
        <span
          style="float:right;font-size:15px;color:#dddddd;font-weight:normal;cursor:pointer;"
          @click="allSearchResultTopic"
        >
          <span>全部</span>
          <i class="el-icon-arrow-right"></i>
        </span>
      </div>
      <div>
        <table cellspacing="15" style="width:100%;">
          <tr>
            <td
              style="background-color:#fff;width:40%;border-radius:4px;height:40px;cursor:pointer;crusor:pointer;"
              v-if="searchResultRelateTopic[0]"
              @click="goTopicInterface(searchResultRelateTopic[0].id)"
            >
              <span style="background-color:#3399ff;color:#fff;width:20px;float:left;">#</span>
              <span style="float:left;padding-left:5%;">{{searchResultRelateTopic[0].topicname}}</span>
            </td>
            <td
              style="background-color:#fff;width:40%;border-radius:4px;height:40px;cursor:pointer;crusor:pointer;"
              v-if="searchResultRelateTopic[1]"
              @click="goTopicInterface(searchResultRelateTopic[1].id)"
            >
              <span style="background-color:#3399ff;color:#fff;width:20px;float:left;">#</span>
              <span style="float:left;padding-left:5%;">{{searchResultRelateTopic[1].topicname}}</span>
            </td>
          </tr>
          <tr>
            <td
              style="background-color:#fff;width:40%;border-radius:4px;height:40px;cursor:pointer;crusor:pointer;"
              v-if="searchResultRelateTopic[2]"
              @click="goTopicInterface(searchResultRelateTopic[2].id)"
            >
              <span style="background-color:#3399ff;color:#fff;width:20px;float:left;">#</span>
              <span style="float:left;padding-left:5%;">{{searchResultRelateTopic[2].topicname}}</span>
            </td>
            <td
              style="background-color:#fff;width:40%;border-radius:4px;height:40px;cursor:pointer;crusor:pointer;"
              v-if="searchResultRelateTopic[3]" @click="goTopicInterface(searchResultRelateTopic[3].id)"
            >
              <span style="background-color:#3399ff;color:#fff;width:20px;float:left;">#</span>
              <span style="float:left;padding-left:5%;">{{searchResultRelateTopic[3].topicname}}</span>
            </td>
          </tr>
          <tr>
            <td
              style="background-color:#fff;width:40%;border-radius:4px;height:40px;cursor:pointer;crusor:pointer;"
              v-if="searchResultRelateTopic[4]" @click="goTopicInterface(searchResultRelateTopic[4].id)"
            >
              <span style="background-color:#3399ff;color:#fff;width:20px;float:left;">#</span>
              <span style="float:left;padding-left:5%;">{{searchResultRelateTopic[4].topicname}}</span>
            </td>
            <td
              style="background-color:#fff;width:40%;border-radius:4px;height:40px;cursor:pointer;crusor:pointer;"
              v-if="searchResultRelateTopic[5]" @click="goTopicInterface(searchResultRelateTopic[5].id)"
            >
              <span style="background-color:#3399ff;color:#fff;width:20px;float:left;">#</span>
              <span style="float:left;padding-left:5%;">{{searchResultRelateTopic[5].topicname}}</span>
            </td>
          </tr>
        </table>
      </div>
    </div>

    <div
      style="background-color:#fff;padding-left:3%;padding-right:2%;margin-top:10px;padding-bottom:60px;"
    >
      <div
        style="text-align:left;font-weight:bold;font-size:20px;padding-top:20px;padding-bottom:10px;;"
      >
        相关帖子
        <span
          style="float:right;font-size:15px;color:#dddddd;font-weight:normal;cursor:pointer;"
          @click="allSearchResultPost"
        >
          <span>全部</span>
          <i class="el-icon-arrow-right"></i>
        </span>
      </div>
      <div
        style="margin-top:15px;"
        v-for="(itema,indexa) in searchResult.articleList"
        :key="indexa+'article'"
      >
        <el-row style="width:100%;text-align:left;">
          <el-col :span="2" :offset="0">
            <img
              :src="itema.userface"
              alt="wzs"
              style="width:40px;height:40px;border-radius:20px;cursor:pointer;"
              @click="goUserInterface(itema.uid)"
            />
          </el-col>
          <el-col :span="8" :offset="0">
            <div
              style="height:40px;padding-left:2%;margin-left:-20px;cursor:pointer;"
              @click="goUserInterface(itema.uid)"
            >
              <div style="text-align:left;margin-top:-3px;margin-bottom:5px;">
                {{itema.nickname}}
                <i class="el-icon-info"></i>
              </div>
              <div style="color:#dddddd;">
                <span>{{itema.editTime | formatDateTime}}·</span>
                <span>{{itema.forumname}}</span>
              </div>
            </div>
          </el-col>
        </el-row>
        <div @click="goArticleDetail(itema.id)" style="cursor:pointer;">
          <div style="text-align:left;margin-top:20px;font-size:20px">
            <span>{{itema.title}}</span>
          </div>
          <div style="text-align:left;margin-top:20px;">
            <span>{{itema.summary}}</span>
          </div>

          <div></div>
          <div
            style="text-align:right;margin-top:15px;color:#dddddd;font-size:12px;height:23px;margin-bottom:15px;"
          >
            <span
              style="background-color:#dddddd;border-radius:4px;color:#000000;float:left;height:20px;width:15%;text-align:center;padding-top:3px;"
            >明明是我先水的!</span>
            <span class="flex-div-end-wrap" style="height:23px;">
              <span style="margin-left:12%;">
                <i class="el-icon-view"></i>
                <span>{{itema.viewCount}}</span>
              </span>
              <span style="margin-left:12%;">
                <i class="el-icon-chat-line-square"></i>
                <span>{{itema.aCommentCount}}</span>
              </span>
              <span style="margin-left:12%;" class="subscribe-article-wrap">
                <!-- <i class="el-icon-info" @click.stop="subscribeArticle(indexa)" style="cursor:pointer;"></i> -->
                <i class="fa fa-thumbs-o-up" aria-hidden="true" @click.stop="subscribeArticle(indexa)" style="cursor:pointer;"></i>
                <span @click.stop="subscribeArticle(indexa)" style="cursor:pointer;">{{itema.aSubscribeCount}}</span>
              </span>
            </span>
          </div>
        </div>
        <hr style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;" />
      </div>
    </div>
    <div style="background-color:#fff;"></div>
  </div>
</template>
<script>
import Swiper from "swiper";
import "../../../assets/swiper/swiper-bundle.css";
import {
  postRequest,
  getRequest,
  putRequest,
  deleteRequest,
} from "../../../utils/api";
export default {
  created() {
    this.searchResult = this.$store.state.searchResult;
    this.searchResultRelateTopic = this.searchResult.topicList;
  },
  mounted() {
    new Swiper(".swiper-container-result-overrall", {
      slidesPerView: "6",
      freeMode: true,
      centeredSlides: false,
      observer: true,
      observeParents: true,
    });
    this.$nextTick(() => {
      this.searchResult.articleList.forEach((item, index) => {
        if (
          item.aSubscribeList !== null &&
          item.aSubscribeList.some((item1, index1) => {
            return item1.uid == this.$store.state.currentUser.id;
          })
        ) {
          document.getElementsByClassName("subscribe-article-wrap")[
            index
          ].style.color = "#ff9900";
        }
      });
      ;
    });
  },
  data() {
    return {
      searchResult: "",
      searchResultUser6: [
        "莉莉娅",
        "萝莎莉娅",
        "布洛妮娅",
        "希儿",
        "杏",
        "可可利亚",
        "琪亚娜",
        "芽衣",
        "布洛妮娅",
      ],
      searchResultRelateTopic: [
        "布洛妮娅生日2019",
        "布洛妮娅生日2020",
        "布洛妮娅",
        "理之律者",
        "许愿！理之律者！",
      ],
    };
  },
  methods: {
    allSearchResultUser() {
      // this.$router.push("/searchResultUser");
      // console.log(this.$parent);
      this.$parent.goWhichResult(3);
    },
    allSearchResultPost() {
      // this.$router.push("/searchResultPost");
      this.$parent.goWhichResult(1);
    },
    allSearchResultTopic() {
      // this.$router.push("/searchResultPost");
      this.$parent.goWhichResult(2);
    },
    loadUserById(id) {
      getRequest("/user/" + id).then(
        (resp) => {
          if (resp.status == 200) {
            this.$store.dispatch("setInterfaceUser", resp.data);
            // console.log(resp.data);
          }
        },
        (resp) => {
          this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
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
    goArticleDetail(id) {
      var _this = this;
      _this.$store.dispatch("setSubscribePrePath", _this.$route.name);
      getRequest("/article/" + id).then(
        (resp) => {
          if (resp.status == 200) {
            // _this.article = resp.data;
            _this.$store.dispatch("setCurrentArticle", resp.data);
            this.$router.push({
              name: "ArticleDetail",
            });
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
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
    subscribeArticle(ind) {
      var _this = this;
      var subscribeButton = document.getElementsByClassName(
        "subscribe-article-wrap"
      )[ind];
      console.log(_this.searchResult.articleList[ind]);

      if (
        _this.searchResult.articleList[ind].aSubscribeList.some((item, index) => {
          return item.uid == _this.$store.state.currentUser.id;
        })
      ) {
        deleteRequest(
          "/article/cancelSub/" +
            _this.searchResult.articleList[ind].id +
            "/" +
            _this.$store.state.currentUser.id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "取消点赞失败!" });
            } else {
              _this.$message({
                type: msg.data.status,
                message: msg.data.msg,
              });
              subscribeButton.style.color = "#dddddd";
              _this.searchResult.articleList[ind].aSubscribeCount -= 1;
              _this.searchResult.articleList[ind].aSubscribeList.forEach(
                (item, index) => {
                  if (item.uid == _this.$store.state.currentUser.id) {
                    _this.searchResult.articleList[ind].aSubscribeList.splice(index, 1);
                  }
                }
              );
              var searchResult = _this.$store.state.searchResult;
              searchResult.articleList = _this.searchResult.articleList;
              _this.$store.dispatch("setSearchResult", searchResult);
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "取消点赞失败!" });
            },
          }
        );
      } else {
        postRequest(
          "/article/sub/" +
            _this.searchResult.articleList[ind].id +
            "/" +
            _this.$store.state.currentUser.id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "点赞失败!" });
            } else {
              _this.$message({
                type: msg.data.respBean.status,
                message: msg.data.respBean.msg,
              });
              subscribeButton.style.color = "#FF9900";
              // console.log(msg.data.newASubscribe);
              _this.searchResult.articleList[ind].aSubscribeCount += 1;
              _this.searchResult.articleList[ind].aSubscribeList.push(
                msg.data.newASubscribe
              );
              var searchResult = _this.$store.state.searchResult;
              searchResult.articleList = _this.searchResult.articleList;
              _this.$store.dispatch("setSearchResult", searchResult);
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "点赞失败!" });
            },
          }
        );
      }
    },
  },
};
</script>
<style>
.flex-div-around-wrap {
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.flex-div-end-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
</style>