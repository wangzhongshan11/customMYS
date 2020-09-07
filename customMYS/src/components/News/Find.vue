<template>
  <div>
    <transition>
      <div style="margin-top:15px;background-color:#dddddd;">
        <div style="background-color:#fff;padding-left:3%;padding-right:3%;">
          <div style="height:40px;line-height:40px;">
            <span style="color:#000000;font-size:20px;font-weight:bold;float:left;">关注话题有更新</span>
            <span
              style="color:#dddddd;font-size:15px;float:right;cursor:pointer;"
              @click="goAllSubscribeTopics"
            >
              全部
              <i class="el-icon-arrow-right"></i>
            </span>
          </div>
          <div
            class="swiper-container swiper-container-news-find"
            style="margin-top:10px;width:100%;padding-top:10px;"
          >
            <div class="swiper-wrapper">
              <div
                class="swiper-slide"
                v-for="(item,index) in currentUser.subscribeTopics"
                :key="index"
                style="width:auto;margin-right:10px;cursor:pointer;"
                @click="goTopicInterface(item.id)"
              >
                <div>
                  <img
                    :src="item.topicface"
                    alt="wzs"
                    style="height:80px;width:80px;border-radius:10px;"
                  />
                </div>
                <div style="color:#000000;font-size:15px;margin-top:8px;">
                  <span>{{item.topicname}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div style="padding-top:30px;background-color:#fff;padding-left:3%;padding-right:3%;">
          <div style="height:40px;line-height:40px;">
            <span style="color:#000000;font-size:20px;font-weight:bold;float:left;">全社热帖榜</span>
            <span style="color:#dddddd;font-size:15px;float:right;">
              全部
              <i class="el-icon-arrow-right"></i>
            </span>
          </div>
          <div style="font-size:15px;color:#000000;">
            <div
              v-for="(item,index) in hotArticles"
              :key="index"
              @click="goArticleDetail(item.id)"
              style="cursor:pointer;"
            >
              <el-row style="height:60px;line-height:60px;">
                <el-col :span="1" :offset="0">
                  <div>
                    <span style>{{index + 1}}</span>
                  </div>
                </el-col>
                <el-col :span="20" :offset="0">
                  <div class="flex-div-start-wrap">
                    <span style>{{item.title}}</span>
                  </div>
                </el-col>
                <el-col :span="3" :offset="0">
                  <div style="color:#dddddd;" class="flex-div-end-wrap">
                    <i class="el-icon-view"></i>
                    <span style="margin-left:5px;">{{item.viewCount}}</span>
                  </div>
                </el-col>
              </el-row>
              <hr
                style="background-color:#dddddd;height:0.5px;border:none;width:850%;margin-bottom:0px;margin-top:0px;float:left;"
              />
            </div>
            <div>
              <el-row style="height:60px;line-height:60px;">
                <el-col :span="1" :offset="0">
                  <div>
                    <span style>1</span>
                  </div>
                </el-col>
                <el-col :span="20" :offset="0">
                  <div class="flex-div-start-wrap">
                    <span style>【讨论有奖】豆日常行为--为...</span>
                  </div>
                </el-col>
                <el-col :span="3" :offset="0">
                  <div style="color:#dddddd;" class="flex-div-end-wrap">
                    <i class="el-icon-view"></i>
                    <span style="margin-left:5px;">1000万</span>
                  </div>
                </el-col>
              </el-row>
              <hr
                style="background-color:#dddddd;height:0.5px;border:none;width:850%;margin-bottom:0px;margin-top:0px;float:left;"
              />
            </div>
          </div>
        </div>
        <div
          style="margin-top:10px;background-color:#fff;padding-left:3%;padding-right:3%;padding-top:15px;"
        >
          <div style="height:40px;line-height:40px;">
            <span style="color:#000000;font-size:20px;font-weight:bold;float:left;">全社热搜</span>
          </div>
          <div style="padding-top:5px;">
            <table cellspacing="15" style="width:100%;">
              <tr>
                <td
                  style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;cursor:pointer;"
                  v-show="hotSearch[0]"
                  @click="beginHotSearch(0)"
                >
                  <span
                    style="float:left;border-radius:2px;color:#FF6600;background-color:#FFCC99;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                  >1</span>
                  <span style="float:left;padding-left:5%;">{{hotSearch[0]}}</span>
                </td>
                <td
                  style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;cursor:pointer;"
                  v-show="hotSearch[1]"
                  @click="beginHotSearch(1)"
                >
                  <span
                    style="float:left;border-radius:2px;color:#FF6600;background-color:#FFCC99;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                  >2</span>
                  <span style="float:left;padding-left:5%;">{{hotSearch[1]}}</span>
                </td>
              </tr>
              <tr>
                <td
                  style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;cursor:pointer;"
                  v-show="hotSearch[2]"
                  @click="beginHotSearch(2)"
                >
                  <span
                    style="float:left;border-radius:2px;color:#FF6600;background-color:#FFCC99;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                  >3</span>
                  <span style="float:left;padding-left:5%;">{{hotSearch[2]}}</span>
                </td>
                <td
                  style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;"
                  v-show="hotSearch[3]"
                  @click="beginHotSearch(3)"
                >
                  <span
                    style="float:left;border-radius:2px;color:#969696;background-color:#dddddd;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                  >4</span>
                  <span style="float:left;padding-left:5%;">{{hotSearch[3]}}</span>
                </td>
              </tr>
            </table>
          </div>
        </div>

        <div
          style="background-color:#fff;padding-left:3%;padding-right:3%;padding-top:15px;margin-top:10px;padding-bottom:10px;"
        >
          <div style="height:40px;line-height:40px;">
            <span style="color:#000000;font-size:20px;font-weight:bold;float:left;">全部频道</span>
          </div>
          <div
            style="padding-top:15px;cursor:pointer;"
            v-for="(item,index) in currentUser.userForums"
            :key="index"
            @click="goDiscussRegion(index)"
          >
            <el-row>
              <el-col :span="3" :offset="0">
                <div>
                  <img
                    :src="item.forumface"
                    alt="wzs"
                    style="height:60px;width:60px;border-radius:5px;float:left;"
                  />
                </div>
              </el-col>
              <el-col :span="19" :offset="0">
                <div>
                  <div class="flex-div-start-wrap" style="height:30px;line-height:30px;">
                    <span style="font-size:15px;color:#000000;">{{item.forumname}}</span>
                  </div>
                  <div class="flex-div-start-wrap" style="height:30px;line-height:30px;">
                    <span style="font-size:12px;color:#000000;">6626人讨论</span>
                  </div>
                </div>
              </el-col>
              <el-col :span="2" :offset="0">
                <div class="flex-div-end-wrap" style="color:#dddddd;font-size:15px;">
                  <i class="el-icon-arrow-right"></i>
                </div>
              </el-col>
            </el-row>
            <div
              style="background-color:#dddddd;border-radius:4px;height:25px;line-height:25px;text-align:left;margin-top:15px;padding-left:2%;"
            >
              <i class="fa fa-fire" aria-hidden="true" style="color:#FF9900;"></i>
              <span style="font-size:15px;color:#000000;margin-left:5px;">舰长副本零掉落，舰长补给全保底！</span>
            </div>
            <hr
              style="background-color:#dddddd;height:0.5px;border:none;width:850%;margin-bottom:0px;margin-top:15px;"
            />
          </div>
          <div style="padding-top:15px;">
            <el-row>
              <el-col :span="3" :offset="0">
                <div>
                  <img
                    src="../../img/米游姬.jpg"
                    alt="wzs"
                    style="height:60px;width:60px;border-radius:5px;float:left;"
                  />
                </div>
              </el-col>
              <el-col :span="19" :offset="0">
                <div>
                  <div class="flex-div-start-wrap" style="height:30px;line-height:30px;">
                    <span style="font-size:15px;color:#000000;">崩坏3</span>
                  </div>
                  <div class="flex-div-start-wrap" style="height:30px;line-height:30px;">
                    <span style="font-size:12px;color:#000000;">6626人讨论</span>
                  </div>
                </div>
              </el-col>
              <el-col :span="2" :offset="0">
                <div class="flex-div-end-wrap" style="color:#dddddd;font-size:15px;">
                  <i class="el-icon-arrow-right"></i>
                </div>
              </el-col>
            </el-row>
            <div
              style="background-color:#dddddd;border-radius:4px;height:25px;line-height:25px;text-align:left;margin-top:15px;padding-left:2%;"
            >
              <!-- <i class="el-icon-info"></i> -->
              <i class="fa fa-fire" aria-hidden="true" style="color:#FF9900;"></i>
              <span style="font-size:15px;color:#000000;margin-left:5px;">舰长副本零掉落，舰长补给全保底！</span>
            </div>
            <hr
              style="background-color:#dddddd;height:0.5px;border:none;width:850%;margin-bottom:0px;margin-top:15px;"
            />
          </div>
        </div>
        <div
          style="height:100px;line-height:100px;padding-left:3%;padding-right:3%;background-color:#fff"
        >
          <span>(~￣∇￣)~已经到底啦~</span>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import Swiper from "swiper";
import "../../assets/swiper/swiper-bundle.css";
import {
  postRequest,
  getRequest,
  putRequest,
  deleteRequest,
} from "../../utils/api";
import { isNotNullORBlank } from "../../utils/utils";
export default {
  mounted() {
    new Swiper(".swiper-container-news-find", {
      slidesPerView: "6",
      freeMode: true,
      centeredSlides: false,
      observer: true,
      observeParents: true,
    });
    this.getHotArticle();
    this.currentUser = this.$store.state.currentUser;
    this.hotSearch = this.$store.state.hotSearch;
    console.log(this.currentUser);
  },
  data() {
    return {
      bbbSwiperImg: [
        "吼姆.jpg",
        "希儿3.jpg",
        "琪亚娜圣诞节.jpg",
        "吼姆.jpg",
        "希儿3.jpg",
        "琪亚娜圣诞节.jpg",
        "吼姆.jpg",
        "希儿3.jpg",
        "琪亚娜圣诞节.jpg",
        "吼姆.jpg",
        "希儿3.jpg",
        "琪亚娜圣诞节.jpg",
      ],
      hotSearch: ["全部", "3", "崩", "泳池"],
      currentUser: "",
      hotArticles: "",
    };
  },
  methods: {
    beginHotSearch(index) {
      this.$store.dispatch("setHotSearch", this.hotSearch);
      this.$router.push({
        name: "HomeSearch",
        params: { hotSearchIndex: index + 1 },
      });
      console.log(index);
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
    goAllSubscribeTopics() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserSubscribe",
        params: { from: "newsFind" },
      });
    },
    getHotArticle() {
      var _this = this;
      getRequest("/article/hot").then(
        (resp) => {
          if (resp.status == 200) {
            console.log("200");
            if (isNotNullORBlank(resp.data) & (resp.data.length > 5)) {
              _this.hotArticles = resp.data.slice(0, 5);
            } else {
              _this.hotArticles = resp.data;
            }
          } else {
            _this.$message({ type: "error", message: "文章获取失败" });
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "文章获取失败" });
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
    goDiscussRegion(index) {
      this.$router.push({name:"HomeWzs",params:{whichForumIndex:index + 1}});
    }
  },
};
</script>
<style>
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