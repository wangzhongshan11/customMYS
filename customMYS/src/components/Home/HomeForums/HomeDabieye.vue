<template>
  <div class="home-page-tran home-page-tran-search">
    <transition>
      <div
        class="page-container"
        style="background-color:#3399ff;opacity:1;z-index:90;"
        v-show="setChannelIndex"
      >
        <div
          class="brny-refresh"
          style="width:100%;position:absolute;top:-500px;height:100px;z-index:99;"
        >
          <img src="../../../img/布洛尼亚刷新.jpg" alt="wzs" style="width:100px;height:100px;" />
        </div>
        <div
          class="suspension-discuss"
          style="display:none;background-color:#fff;padding-top:15px;padding-bottom:15px;position:fixed;top:100px;z-index:99;width:100%;"
        >
          <el-row
            style="padding-left:4.5%;padding-right:4.5%;width:100%;cursor:pointer;"
            @click="goDiscussRegion()"
          >
            <el-col :span="1" :offset="0">
              <div class="flex-div-start-wrap" @click="goDiscussRegion()">
              <i class="fa fa-bullhorn" aria-hidden="true" style="font-size:25px;"></i>
              </div>
            </el-col>
            <el-col :span="15" :offset="0">
              <div class="flex-div-start-wrap" @click="goDiscussRegion()">
                <span>分享日常，交流ACGN见闻</span>
              </div>
            </el-col>
            <el-col :span="8" :offset="0">
              <div class="flex-div-end-wrap" @click="goDiscussRegion()">
                <span>进入讨论区</span>
                <i class="el-icon-arrow-right"></i>
              </div>
            </el-col>
          </el-row>
        </div>

        <div class="scroll-region">
          <div style="padding-top:150px;background-color:#3399FF;padding-bottom:60px;">
            <div
              class="swiper-container swiper-container-home-dabieye"
              style="padding-bottom:50px;width:90%;"
            >
              <div class="swiper-wrapper">
                <div
                  class="swiper-slide"
                  v-for="(item,index) in homeSwipers"
                  :key="index"
                  style="width:auto;margin-right:10px;"
                >
                  <div>
                    <i class="el-icon-info" style="font-size:60px;"></i>
                  </div>
                  <div style="color:#fff;font-size:15px;margin-top:15px;">
                    <span>{{item}}</span>
                  </div>
                </div>
              </div>
            </div>

            <div
              style="background-color:#fff;margin-left:4.5%;margin-right:4.5%;padding-top:15px;padding-bottom:15px;padding-right:0%;padding-left:0%;border-radius:10px;"
            >
              <el-row @click="goDiscussRegion()" style="cursor:pointer;">
                <el-col :span="2" :offset="1">
                  <div class="flex-div-start-wrap" style="height:70px;" @click="goDiscussRegion()">
                    <i class="fa fa-bullhorn" aria-hidden="true" style="font-size:50px;"></i>
                  </div>
                </el-col>
                <el-col :span="15" :offset="0">
                  <div style="height:70px;padding-top:0px;" @click="goDiscussRegion()">
                    <div class="flex-div-start-wrap" style="font-size:20px;">大别野讨论区</div>
                    <div
                      class="flex-div-start-wrap"
                      style="font-size:15px;padding-top:15px;"
                    >分享日常，交流ACGN见闻</div>
                  </div>
                </el-col>
                <el-col :span="5" :offset="0">
                  <div class="flex-div-end-wrap" style="height:70px;" @click="goDiscussRegion()">
                    <span>进入讨论区</span>
                    <i class="el-icon-arrow-right"></i>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>

          <div
            style="margin-top:-40px;border-radius:10px;background-color:#dddddd;padding-top:20px;"
          >
            <div
              style="margin-bottom:30px;text-align:left;background-color:#fff;border-top-left-radius:10px;border-top-right-radius:10px;"
            >
              <div
                style="padding-left:4.5%;padding-right:4.5%;text-align:left;border-top-left-radius:10px;border-top-right-radius:10px;background-color:#fff;margin-top:-20px;padding-top:20px;padding-bottom:20px;"
              >
                <i class="el-icon-postcard" style="font-size:25px;"></i>
                <span style="font-size:25px;">&nbsp;官方推荐</span>
                <span style="float:right;font-size:15px;">
                  全部
                  <i class="el-icon-arrow-right"></i>
                </span>
              </div>
              <div style="background-color:#fff;padding-bottom:15px;">
                <div
                  style="background-color:#dddddd;border-radius:10px;margin-left:4.5%;margin-right:4.5%;padding-left:1.5%;padding-right:1.5%;padding-top:10px;"
                >
                  <div
                    style="font-size:17px;padding-top:5px;padding-bottom:5px;"
                  >【公告】&nbsp;战线协议-协议战绩异常获取的处理说明</div>
                  <div style="padding-top:5px;padding-bottom:10px;">
                    <span
                      style="font-size:15px;color:#fff;background-color:#0099FF;width:5%;border-radius:3px;padding-left:1%;padding-right:1%;padding-top:1.5px;padding-bottom:1.5px;"
                    >公告</span>
                    <span style="float:right;font-size:15px;color:#969696;">5小时前</span>
                  </div>
                </div>
              </div>
            </div>
            <div
              style="font-size:25px;padding-left:4.5%;padding-right:4.5%;text-align:left;background-color:#fff;margin-top:-20px;padding-top:20px;padding-bottom:5px;"
            >
              <i class="el-icon-postcard"></i>
              <span>&nbsp;推荐</span>
            </div>
            <div
              style="margin-bottom:10px;height:160px;background-color:#fff;padding-top:15px;cursor:pointer;"
              v-for="(item,index) in articles"
              :key="index"
              @click="goArticleDetail(index)"
            >
              <div class="flex-div-start-wrap" style="padding-left:4.5%;padding-right:4.5%;">
                <span
                  style="border-radius:3px;background-color:#99CCFF;color:#0066FF;width:3%;margin-right:5px;"
                >官</span>
                <span style="font-size:20px;">{{item.title}}</span>
              </div>
              <el-row>
                <el-col :span="14" :offset="1" style="height:100px;">
                  <div style="height:100px;">
                    <div class="flex-div-start-wrap" style="margin-top:15px;">
                      <img
                        src="../../../img/米游姬1.jpg"
                        alt="wzs"
                        style="height:25px;width:25px;border-radius:10px;"
                      />
                      <span style="font-size:15px;">&nbsp;{{item.nickname}}</span>
                      <span
                        style="font-size:15px;color:#C0C0C0;height:20px;line-height:20px;"
                      >&nbsp;&nbsp;{{item.editTime | formatDateTime}}</span>
                    </div>
                    <div class="flex-div-start-wrap" style="font-size:15px;margin-top:15px;">
                      <span style="text-align:left;">{{item.summary}}</span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="8" :offset="0">
                  <div style="height:100px;" class="flex-div-end-wrap">
                    <img src="../../../img/吼姆.jpg" alt="wzs" style="height:100px;width:160px;" />
                  </div>
                </el-col>
              </el-row>
              <div style="padding-left:4.5%;padding-right:4.5%;">
                <span style="color:#C0C0C0;font-size:15px;float:left;">167评论·1914点赞</span>
              </div>
            </div>
            <div
              style="margin-bottom:10px;height:160px;background-color:#fff;padding-top:15px;cursor:pointer;"
              @click="goArticleDetail(1)"
            >
              <div class="flex-div-start-wrap" style="padding-left:4.5%;padding-right:4.5%;">
                <span
                  style="border-radius:3px;background-color:#99CCFF;color:#0066FF;width:3%;margin-right:5px;"
                >官</span>
                <span style="font-size:20px;">烈焰焚尽</span>
              </div>
              <el-row>
                <el-col :span="14" :offset="1" style="height:100px;">
                  <div style="height:100px;">
                    <div class="flex-div-start-wrap" style="margin-top:15px;">
                      <img
                        src="../../../img/布洛妮娅.jpg"
                        alt="wzs"
                        style="height:25px;width:25px;border-radius:10px;"
                      />
                      <span style="font-size:15px;">&nbsp;若鸿似鹄</span>
                      <span
                        style="font-size:15px;color:#C0C0C0;height:20px;line-height:20px;"
                      >&nbsp;&nbsp;2020年09月06日</span>
                    </div>
                    <div class="flex-div-start-wrap" style="font-size:15px;margin-top:15px;">
                      <span style="text-align:left;">纵使黑云蔽日，我也要燃烧天空，带你找到回家的路!</span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="8" :offset="0">
                  <div style="height:100px;" class="flex-div-end-wrap">
                    <img src="../../../img/吼姆.jpg" alt="wzs" style="height:100px;width:160px;" />
                  </div>
                </el-col>
              </el-row>
              <div style="padding-left:4.5%;padding-right:4.5%;">
                <span style="color:#C0C0C0;font-size:15px;float:left;">167评论·1914点赞</span>
              </div>
            </div>

            <div style="height:100px;line-height:100px;background-color:#fff;">
              <span>(&nbsp;~&nbsp;￣∇￣)&nbsp;~&nbsp;已经到底啦~</span>
            </div>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>
<script>
import { getRequest } from "../../../utils/api";
import Swiper from "swiper";
import "../../../assets/swiper/swiper-bundle.css";
export default {
  mounted() {
    new Swiper(".swiper-container-home-dabieye", {
      slidesPerView: "5",
      freeMode: false,
      centeredSlides: false,
      observer: true,
      observeParents: true,
    });
    this.loadBlogs("大别野");
    this.articles = this.$store.state.dabieyeArticles;
    console.log("homeDabieye");
    // console.log(this.articles);
  },
  data() {
    return {
      setChannelIndex: true,
      articles: [],
      homeSwipers: ["周刊", "图区", "活动", "日历", "抽抽乐"],
    };
  },
  methods: {
    goDiscussRegion() {
      this.$router.push({
        name: "DiscussRegionDabieye",
      });
    },
    goArticleDetail(index) {
      var _this = this;
      _this.$store.dispatch("setSubscribePrePath", _this.$route.name);
      this.$router.push({
        name: "ArticleDetail",
      });
      // console.log(this.articles[index].id);
      getRequest("/article/" + this.articles[index].id).then(
        (resp) => {
          if (resp.status == 200) {
            // _this.article = resp.data;
            _this.$store.dispatch("setCurrentArticle", resp.data);
            this.$router.push({
              name: "ArticleDetail",
            });
            // console.log(resp.data);
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
    },
    loadBlogs(forumname) {
      var _this = this;
      var _this = this;
      var url = "";
      url = "/admin/article/all" + "?forumname=" + forumname;
      getRequest(url).then(
        (resp) => {
          console.log(resp.status);
          if (resp.status == 200) {
            console.log("=200");
            console.log(resp.data.articles);
            _this.articles = resp.data.articles;
            // _this.totalCount = resp.data.totalCount;
            switch (forumname) {
              case "大别野":
                _this.$store.dispatch("setDabieyeArticles", resp.data.articles);
                break;
              case "崩坏3":
                _this.articles = resp.data.articles;
                _this.$store.dispatch("setBBBArticles", resp.data.articles);
                break;
              case "崩坏学园2":
                _this.$store.dispatch("setBBArticles", resp.data.articles);
                break;
              case "原神":
                _this.$store.dispatch(
                  "setYuanShenArticles",
                  resp.data.articles
                );
                break;
              case "未定事件簿":
                _this.$store.dispatch("setWeiDingArticles", resp.data.articles);
                break;
            }
            console.log(_this.articles);
          } else {
            // console.log("!200");
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        },
        (resp) => {
          if (resp.response.status == 403) {
            // console.log("=403");
            _this.$message({ type: "error", message: resp.response.data });
          } else {
            // console.log("!=403");
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        }
      );
      // console.log(_this.articles);
    },
  },
};
</script>
<style>
.page-container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
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
.home-page-tran .v-enter {
  opacity: 0;
  transform: translateX(-100%);
}
.home-page-tran .v-leave-to {
  opacity: 0;
  transform: translateX(-100%);
  position: absolute;
}
.home-page-tran .v-enter-active,
.home-page-tran .v-leave-active {
  transition: all 0.5s ease;
}

.click-left .v-enter {
  opacity: 0;
  transform: translateX(-100%);
}
.click-left .v-leave-to {
  opacity: 0;
  transform: translateX(100%);
  position: absolute;
}
.click-left .v-enter-active,
.click-left .v-leave-active {
  transition: all 0.2s ease;
}

.click-right .v-enter {
  opacity: 0;
  transform: translateX(100%);
}
.click-right .v-leave-to {
  opacity: 0;
  transform: translateX(-100%);
  position: absolute;
}
.click-right .v-enter-active,
.click-right .v-leave-active {
  transition: all 0.2s ease;
}
</style>