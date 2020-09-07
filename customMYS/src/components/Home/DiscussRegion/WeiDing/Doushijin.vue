<template>
  <div>
    <topicPopup ref="dailyPopupIndex" :topic="weidingDoushijinTopics"></topicPopup>
    <div
      class="flex-div-start-wrap scroll-gradual-suspension2"
      style="background-color:#fff;"
      v-show="false"
    >
      <div
        class="swiper-container"
        style="margin-top:10px;width:80%;margin-left:7%;margin-right:7%;"
      >
        <div class="swiper-wrapper">
          <div
            class="swiper-slide flex-div-center-wrap"
            v-for="(item,index) in weidingDoushijinTopics"
            :key="index"
            style="width:auto;margin-right:0px;"
            @click="clickTopicSus(index)"
          >
            <span
              class="swiper-slide-doushijin-suspension"
              style="cursor:pointer;margin-right:15px;border:solid 1px #dddddd;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;display:inline-block;line-height:25px;"
            >{{item.topicname}}</span>
          </div>
        </div>
      </div>
      <div
        class="flex-div-center-wrap"
        style="margin-top:10px;margin-left:-4%;height:25px;"
        @click="showDailyTopic"
      >
        <i class="el-icon-arrow-down" style="color:#dddddd;font-size:15px;"></i>
      </div>
    </div>
    <div class="flex-div-start-wrap">
      <div
        class="swiper-container"
        style="margin-top:10px;width:80%;margin-left:7%;margin-right:7%;"
      >
        <div class="swiper-wrapper">
          <div
            class="swiper-slide flex-div-center-wrap"
            v-for="(item,index) in weidingDoushijinTopics"
            :key="index"
            style="width:auto;margin-right:15px;"
            @click="clickTopic(index)"
          >
            <span
              class="swiper-slide-doushijin"
              style="cursor:pointer;margin-right:15px;border:solid 1px #dddddd;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;display:inline-block;line-height:25px;"
            >{{item.topicname}}</span>
          </div>
        </div>
      </div>
      <div
        class="flex-div-center-wrap"
        style="margin-top:10px;margin-left:-4%;height:25px;"
        @click="showDailyTopic"
      >
        <i class="el-icon-arrow-down" style="color:#dddddd;font-size:15px;"></i>
      </div>
    </div>
    <hr style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;" />
    <div>
      <div
        style="font-size:12px;color:#dddddd;padding-left:3%;padding-right:3%;margin-bottom:15px;margin-bottom:20px;"
      >
        <span style="float:left;">筛选条件</span>
        <span style="float:right;">
          <span>最新回复</span>
          <i class="el-icon-arrow-down"></i>
        </span>
      </div>
      <div style="clear:both;"></div>
      <div style="padding-left:3%;padding-right:3%;margin-top:20px;width:100%;">
        <div style="height:30px;line-height:30px;float:left;">
          <span style="background-color:#3399ff;color:#fff;border-radius:4px;">置顶</span>
          <span>【新人导航】米游社大别野不完全使用指南</span>
        </div>
        <div style="clear:both;"></div>
        <div style="height:30px;line-height:30px;float:left;">
          <span style="background-color:#3399ff;color:#fff;border-radius:4px;">置顶</span>
          <span>书香米游社-读书月活动第三期：艺术周来袭************************************************************</span>
        </div>
        <div style="clear:both;"></div>
        <div style="height:30px;line-height:30px;float:left;">
          <span style="background-color:#3399ff;color:#fff;border-radius:4px;">置顶</span>
          <span>夏日大作战-米游兔的清凉冒险（第四期）...</span>
        </div>
      </div>
    </div>
    <div style="clear:both;"></div>
    <hr style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;" />
    <div style="padding-left:3%;padding-right:3%;margin-top:15px;">
      <div
        v-for="(item,index) in articles"
        :key="index"
        @click="goArticleDetail(index)"
        style="cursor:pointer;margin-top:15px;"
      >
        <el-row style="width:100%;text-align:left;">
          <el-col :span="2" :offset="0">
            <img
              :src="item.userface"
              alt="wzs"
              style="width:40px;height:40px;border-radius:20px;cursor:pointer;"
              @click.stop="goUserInterface(item.uid)"
            />
          </el-col>
          <el-col :span="22" :offset="0">
            <div
              style="height:40px;padding-left:2%;margin-left:-20px;cursor:pointer;"
              @click.stop="goUserInterface(item.uid)"
            >
              <div style="text-align:left;margin-top:-3px;margin-bottom:5px;">
                {{item.nickname}}
                <i class="el-icon-info"></i>
              </div>
              <div style="color:#dddddd;">
                <span>{{item.editTime | formatDateTime}}·</span>
                <span>{{item.forumname}}</span>
              </div>
            </div>
          </el-col>
        </el-row>
        <div style="text-align:left;margin-top:20px;font-size:20px">
          <span>{{item.title}}</span>
        </div>
        <div style="text-align:left;margin-top:20px;">
          <div style="text-align:left" v-html="item.htmlContent"></div>
        </div>
        <div>
          <img
            src="../../../../img/米游姬.jpg"
            alt="wzs"
            style="height:400px;border-radius:5px;float:left;"
          />
        </div>

        <div style="clear:both;"></div>
        <div
          style="text-align:right;margin-top:15px;color:#dddddd;font-size:12px;height:23px;margin-bottom:15px;"
        >
          <span
            style="background-color:#dddddd;border-radius:4px;color:#000000;float:left;height:20px;width:15%;text-align:center;padding-top:3px;"
          >明明是我先水的!</span>
          <span class="flex-div-end-wrap" style="height:23px;">
            <span style="margin-left:12%;">
              <i class="el-icon-info"></i>
              <span>{{item.viewCount}}</span>
            </span>
            <span style="margin-left:12%;">
              <i class="el-icon-info"></i>
              <span>{{item.aCommentCount}}</span>
            </span>
            <span style="margin-left:12%;" class="subscribe-article-wrap-doushijin">
              <i class="el-icon-info" @click.stop="subscribeArticle(index)" style="cursor:pointer;"></i>
              <span
                @click.stop="subscribeArticle(index)"
                style="cursor:pointer;"
              >{{item.aSubscribeCount}}</span>
            </span>
          </span>
        </div>
        <hr style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;" />
      </div>
      <div>
        <el-row style="width:100%;text-align:left;">
          <el-col :span="2" :offset="0">
            <img
              src="../../../../img/崩坏三.jpg"
              alt="wzs"
              style="weight:40px;height:40px;border-radius:20px;"
            />
          </el-col>
          <el-col :span="22" :offset="0">
            <div style="height:40px;padding-left:2%;margin-left:-20px;">
              <div style="text-align:left;margin-top:-3px;margin-bottom:5px;">
                用户姓名
                <i class="el-icon-info"></i>
              </div>
              <div style="color:#dddddd;">
                <span>2018-11-19·</span>
                <span>崩坏三</span>
              </div>
            </div>
          </el-col>
        </el-row>
        <div style="text-align:left;margin-top:20px;font-size:20px">
          <span>帖子标题</span>
        </div>
        <div style="text-align:left;margin-top:20px;">
          <span>帖子内容</span>
        </div>
        <div>
          <img
            src="../../../../img/米游姬.jpg"
            alt="wzs"
            style="height:400px;border-radius:5px;float:left;"
          />
        </div>

        <div style="clear:both;"></div>
        <div
          style="text-align:right;margin-top:15px;color:#dddddd;font-size:12px;height:23px;margin-bottom:15px;"
        >
          <span
            style="background-color:#dddddd;border-radius:4px;color:#000000;float:left;height:20px;width:15%;text-align:center;padding-top:3px;"
          >明明是我先水的!</span>
          <span class="flex-div-end-wrap" style="height:23px;">
            <span style="margin-left:12%;">
              <i class="el-icon-info"></i>
              <span>10</span>
            </span>
            <span style="margin-left:12%;">
              <i class="el-icon-info"></i>
              <span>10</span>
            </span>
            <span style="margin-left:12%;">
              <i class="el-icon-info"></i>
              <span>10</span>
            </span>
          </span>
        </div>
        <hr style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;" />
      </div>
    </div>
  </div>
</template>
<script>
import Swiper from "swiper";
import "../../../../assets/swiper/swiper-bundle.css";
import topicPopup from "../TopicPopup";
import {
  postRequest,
  getRequest,
  putRequest,
  deleteRequest,
} from "../../../../utils/api";
import { isNotNullORBlank } from "../../../../utils/utils";
export default {
  components: {
    topicPopup,
  },
  mounted() {
    this.loadPlateBlogs("同人文");
    this.loadPlateTopics("同人文");
    new Swiper(".swiper-container", {
      slidesPerView: "auto",
      observer: true,
    });
    // this.$nextTick(() => {
    //   this.initArticles();
    // });
  },
  data() {
    return {
      weidingDoushijinTopics: [
        // "全部",
        // "泳池",
        // "生活情感",
        // "暑期管理大师",
        // "米游社建议&反馈",
        // "家有萌主",
        // "雨天随手拍",
        // "全部",
        // "泳池",
        // "生活情感",
        // "暑期管理大师",
        // "米游社建议&反馈",
        // "家有萌主",
        // "雨天随手拍",
      ],
      articles: [],
      chosenTopicIndex: 0,
    };
  },
  updated() {
    this.$nextTick(() => {
      this.iniSubscribeButton();
    });
  },
  watch: {
    // articles() {
    //   this.iniSubscribeButton();
    // }
  },
  methods: {
    iniSubscribeButton() {
      if (isNotNullORBlank(this.articles)) {
        this.articles.forEach((item, index) => {
          console.log(
            item.aSubscribeList.some((item1, index1) => {
              return item1.uid == this.$store.state.currentUser.id;
            })
          );

          if (
            isNotNullORBlank(item.aSubscribeList) &&
            item.aSubscribeList.some((item1, index1) => {
              return item1.uid == this.$store.state.currentUser.id;
            })
          ) {
            console.log(index);
            console.log(
              document.getElementsByClassName("subscribe-article-wrap-doushijin")
            );

            document.getElementsByClassName("subscribe-article-wrap-doushijin")[
              index
            ].style.color = "#ff9900";
          }
        });
      }
      if (this.chosenTopicIndex == 0) {
        document.getElementsByClassName(
          "swiper-slide-doushijin"
        )[0].style.backgroundColor = "#000000";
        document.getElementsByClassName("swiper-slide-doushijin")[0].style.color =
          "#fff";
        document.getElementsByClassName(
          "swiper-slide-doushijin-suspension"
        )[0].style.backgroundColor = "#000000";
        document.getElementsByClassName(
          "swiper-slide-doushijin-suspension"
        )[0].style.color = "#fff";

        document.getElementsByClassName(
          "swiper-slide-pop"
        )[0].style.backgroundColor = "#000000";
        document.getElementsByClassName("swiper-slide-pop")[0].style.color =
          "#fff";
      }
    },
    clickTopic(ind) {
      var _this = this;
      if (this.weidingDoushijinTopics[ind].topicname !== "全部") {
        this.loadTopicBlogs(this.weidingDoushijinTopics[ind].topicname);
        this.chosenTopicIndex = ind;
      } else {
        this.loadPlateBlogs("同人文");
      }

      var swiperSlideDailys = Array.from(
        document.getElementsByClassName("swiper-slide-doushijin")
      );
      var swiperSlideDailySuspensions = Array.from(
        document.getElementsByClassName("swiper-slide-doushijin-suspension")
      );
      var popSlides = Array.from(
        document.getElementsByClassName("swiper-slide-pop")
      );
      swiperSlideDailys.forEach((item, index) => {
        if (index == ind) {
          swiperSlideDailys[index].style.backgroundColor = "#000000";
          swiperSlideDailys[index].style.color = "#fff";
          swiperSlideDailySuspensions[index].style.backgroundColor = "#000000";
          swiperSlideDailySuspensions[index].style.color = "#fff";
        } else if (index !== ind) {
          swiperSlideDailys[index].style.backgroundColor = "#fff";
          swiperSlideDailys[index].style.color = "#000000";
          swiperSlideDailySuspensions[index].style.backgroundColor = "#fff";
          swiperSlideDailySuspensions[index].style.color = "#000000";
        }
      });
      popSlides.forEach((item, index) => {
        if (index == ind) {
          popSlides[index].style.backgroundColor = "#000000";
          popSlides[index].style.color = "#fff";
        } else if (index !== ind) {
          popSlides[index].style.backgroundColor = "rgb(221, 221, 221)";
          popSlides[index].style.color = "#000000";
        }
      });
    },

    clickTopicSus(ind) {
      this.clickTopic(ind);
    },

    showDailyTopic() {
      this.$refs.dailyPopupIndex.popupIndex = true;
    },
    initArticles() {
      var stateArticles = this.$store.state.dabieyeArticles;
      if (isNotNullORBlank(stateArticles)) {
        stateArticles.forEach((item, index) => {
          var _this = this;
          if (stateArticles[index].platename == "同人文") {
            _this.articles.push(stateArticles[index]);
          }
        });
      }
    },
    goArticleDetail(index) {
      var _this = this;
      _this.$store.dispatch("setSubscribePrePath", _this.$route.name);
      getRequest("/article/" + this.articles[index].id).then(
        (resp) => {
          if (resp.status == 200) {
            // _this.article = resp.data;
            _this.$store.dispatch("setCurrentArticle", resp.data);
            console.log(resp.data);
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );

      this.$router.push({
        name: "ArticleDetail",
      });
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
    subscribeArticle(ind) {
      var _this = this;
      var subscribeButton = document.getElementsByClassName(
        "subscribe-article-wrap-doushijin"
      )[ind];
      console.log(_this.articles[ind]);

      if (
        _this.articles[ind].aSubscribeList.some((item, index) => {
          return item.uid == _this.$store.state.currentUser.id;
        })
      ) {
        deleteRequest(
          "/article/cancelSub/" +
            _this.articles[ind].id +
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
              _this.articles[ind].aSubscribeCount -= 1;
              _this.articles[ind].aSubscribeList.forEach((item, index) => {
                if (item.uid == _this.$store.state.currentUser.id) {
                  _this.articles[ind].aSubscribeList.splice(index, 1);
                }
              });
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
            _this.articles[ind].id +
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
              _this.articles[ind].aSubscribeCount += 1;
              _this.articles[ind].aSubscribeList.push(msg.data.newASubscribe);
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
    loadPlateBlogs(platename) {
      var _this = this;
      var url = "";
      url = "/admin/article/all/plate" + "?platename=" + platename;
      getRequest(url).then(
        (resp) => {
          if (resp.status == 200) {
            // console.log("=200");
            _this.articles = resp.data.articles;
            // console.log(_this.articles);
            // _this.$nextTick(() => {
            //   _this.iniSubscribeButton();
            // });
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        },
        (resp) => {
          if (resp.response.status == 403) {
            _this.$message({ type: "error", message: resp.response.data });
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        }
      );
    },
    loadTopicBlogs(topicname) {
      var _this = this;
      var url = "";
      url = "/admin/article/all/topic" + "?topicname=" + topicname;
      getRequest(url).then(
        (resp) => {
          // console.log(resp.status);
          if (resp.status == 200) {
            // console.log("=200");
            // console.log(resp.data.articles);
            _this.articles = resp.data.articles;
            // console.log(_this.articles);
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        },
        (resp) => {
          if (resp.response.status == 403) {
            _this.$message({ type: "error", message: resp.response.data });
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        }
      );
    },
    loadPlateTopics(platename) {
      var _this = this;
      var _this = this;
      var url = "";
      url = "/admin/all/topic" + "?platename=" + platename;
      getRequest(url).then(
        (resp) => {
          // console.log(resp.status);
          if (resp.status == 200) {
            // console.log("=200");
            // console.log(resp.data);
            _this.weidingDoushijinTopics = resp.data;
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        },
        (resp) => {
          if (resp.response.status == 403) {
            _this.$message({ type: "error", message: resp.response.data });
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        }
      );
    },
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
</style>