<template>
  <div class="page-container-wrap" style="background-color:#dddddd;">
    <el-row
      style="padding-bottom:20px;padding-top:20px;font-size:20px;background-color:#dddddd;position:fixed;top:0px;left:0px;z-index:2;width:98%;"
    >
      <el-col :span="1" :offset="1">
        <div class="flex-div-start-wrap" style="height:40px;cursor:pointer;" @click="goback">
          <i class="el-icon-arrow-left"></i>
        </div>
      </el-col>

      <el-col :span="20" :offset="0">
        <div class="flex-div-around-wrap" style="height:40px;cursor:pointer;">
          <span v-show="false" class="article-detail-slide-show">
            <span class="flex-div-around-wrap">
              <img
                :src="currentArticle.userface"
                alt="wzs"
                style="width:40px;height:40px;border-radius:20px"
              />
              <span style="font-size:12px;margin-left:5px;">{{currentArticle.nickname}}</span>
            </span>
          </span>
          <span class="article-detail-slide-disappear">{{currentArticle.forumname}}</span>
          <span
            v-if="!haveSubscribedPosterIndex"
            class="article-detail-slide-show"
            style="font-size:15px;cursor:pointer;background-color:#fff;border-radius:3px;width:10%;border:solid 1px #000000;"
            @click="subscribeUser()"
          >+&nbsp;关注</span>
          <span
            v-if="haveSubscribedPosterIndex"
            class="article-detail-slide-show"
            style="font-size:15px;cursor:pointer;background-color:#fff;border-radius:3px;width:10%;border:solid 1px #000000;"
            @click="subscribeUser()"
          >已关注</span>
        </div>
      </el-col>
      <el-col :span="1" :offset="0">
        <div class="flex-div-start-wrap" style="height:40px;">
          <i class="el-icon-more"></i>
        </div>
      </el-col>
    </el-row>
    <div style="margin-bottom:60px;">
      <div style="background-color:#fff;margin-bottom:10px;margin-top:80px;">
        <el-row style="padding-bottom:30px;padding-top:30px;font-size:20px;background-color:#fff;">
          <el-col :span="2" :offset="1">
            <div
              class="flex-div-start-wrap"
              style="height:40px;cursor:pointer;"
              @click="goUserInterface()"
            >
              <img
                :src="currentArticle.userface"
                alt="wzs"
                style="width:40px;height:40px;border-radius:20px"
              />
            </div>
          </el-col>
          <el-col :span="16" :offset="0">
            <div class="flex-div-start-wrap" style="height:40px;cursor:pointer;margin-left:-2%;">
              <span style="font-size:12px;">{{currentArticle.nickname}}&nbsp;</span>
              <i class="el-icon-info" style="font-size:12px;"></i>
            </div>
          </el-col>
          <el-col :span="4" :offset="0">
            <div class="flex-div-end-wrap" style="height:40px;cursor:pointer;">
              <span
                v-if="!haveSubscribedPosterIndex"
                style="font-size:15px;cursor:pointer;background-color:#fff;border-radius:3px;width:50%;border:solid 1px #000000;"
                @click="subscribeUser()"
              >+&nbsp;关注</span>
              <span
                v-if="haveSubscribedPosterIndex"
                style="font-size:15px;cursor:pointer;background-color:#fff;border-radius:3px;width:50%;border:solid 1px #000000;"
                @click="subscribeUser()"
              >已关注</span>
            </div>
          </el-col>
        </el-row>
        <div style="padding-left:5%;padding-right:2%;padding-bottom:5px;">
          <div>
            <span style="font-size:20px;float:left;">{{currentArticle.title}}</span>
          </div>
          <div style="clear:both"></div>
          <div style="margin-top:15px;margin-bottom:10px;">
            <span
              style="font-size:12px;color:#dddddd;"
            >——&nbsp;文章发表：{{currentArticle.publishDate | formatDateTime}}&nbsp;|&nbsp;最后编辑：{{currentArticle.editTime | formatDateTime}}&nbsp;——</span>
          </div>
          <div style="font-size:15px;">
            <div style="float:left;text-align:left;" v-html="currentArticle.htmlContent"></div>

            <div>
              <img
                src="../../../img/米游姬.jpg"
                alt="wzs"
                style="height:400px;border-radius:5px;float:left;"
              />
            </div>
          </div>
          <div style="clear:both"></div>
          <div style="font-size:12px;color:#dddddd;margin-top:40px;margin-bottom:15px;">
            <span style="float:left;">
              <i class="el-icon-view"></i>
              <span>帖子浏览数:{{currentArticle.viewCount}}</span>
            </span>
          </div>
          <div style="clear:both;"></div>
          <div class="flex-div-start-wrap" style="margin-top:20px;margin-bottom:10px;">
            <div class="swiper-container" style="margin-top:10px;width:100%;margin-left:0px;">
              <div class="swiper-wrapper">
                <div
                  class="swiper-slide flex-div-center-wrap"
                  v-for="(item,index) in articleRelateCategory"
                  :key="index"
                  style="width:auto;margin-right:5px;"
                >
                  <span
                    class="swiper-slide-daily"
                    style="background-color:#dddddd;cursor:pointer;margin-right:15px;border:solid 1px #dddddd;border-radius:5px;padding-left:10px;padding-right:10px;font-size:13px;height:25px;display:inline-block;line-height:25px;"
                  >{{item}}</span>
                </div>
              </div>
            </div>
            <div class="flex-div-center-wrap" style="margin-top:10px;margin-left:-4%;height:25px;">
              <i class="el-icon-arrow-down" style="color:#dddddd;font-size:15px;"></i>
            </div>
          </div>
        </div>
      </div>
      <div style="width:100%;background-color:#fff;">
        <div class="flex-div-start-wrap" style="border-bottom: solid 1px #dddddd;">
          <el-menu
            class="flex-div-around-wrap"
            default-active="0"
            mode="horizontal"
            router
            style="border-bottom: none;width:40%;"
          >
            <template v-for="(item,index) in this.$router.options.routes[23].children">
              <el-menu-item
                :index="item.path"
                :key="index"
                style="font-size:17px;"
                class="bbb-routes-wrap"
              >{{item.name}}</el-menu-item>
            </template>
          </el-menu>
          <span
            style="font-size:15px;color:#dddddd;height:60px;line-height:60px;width:40%;margin-left:15%;"
          >
            <span
              style="padding-left:10%;padding-right:10%;cursor:pointer;"
              class="order-comment-hot"
            >热门</span>
            <span>|</span>
            <span
              style="padding-left:10%;padding-right:10%;cursor:pointer;"
              class="order-comment-asc"
            >正序</span>
            <span>|</span>
            <span
              style="padding-left:10%;padding-right:10%;cursor:pointer;"
              class="order-comment-desc"
            >倒序</span>
          </span>
        </div>
        <div>
          <router-view></router-view>
        </div>
      </div>
    </div>

    <el-row
      style="color:#000000;position:fixed;left:0px;width:98.8%;bottom:0px;background-color:#dddddd;height:60px;padding-top:10px;padding-left:5%;padding-right:2%;"
    >
      <el-col :span="18" :offset="0">
        <div class="flex-div-around-wrap">
          <el-input
            v-model="newArticleComment.content"
            placeholder="我有话要说..."
            style="border-radius:4px;"
          ></el-input>
          <span
            @click="newArticleCommentM()"
            style="font-size:15px;cursor:pointer;background-color:#3399ff;color:#dddddd;border-radius:4px;width:40%;border:solid 1px #dddddd;height:35px;line-height:35px;width:60px;margin-left:15px;"
          >发布</span>
        </div>
      </el-col>
      <el-col :span="1" :offset="1">
        <div style="height:40px;text-align:center;">
          <div>
            <i class="el-icon-chat-line-square"></i>
          </div>
          <div>
            <span>{{commentListCount}}</span>
          </div>
        </div>
      </el-col>
      <el-col :span="1" :offset="1">
        <div
          class="subscribe-article-wrap"
          style="height:40px;text-align:center;cursor:pointer;"
          @click="subscribeArticle()"
        >
          <div>
            <!-- <i class="el-icon-info"></i> -->
            <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
          </div>
          <div>
            <span>{{aSubscribeListCount}}</span>
          </div>
        </div>
      </el-col>
      <el-col :span="1" :offset="1">
        <div
          class="favorite-article-wrap"
          style="height:40px;text-align:center;cursor:pointer;"
          @click="favoriteArticle()"
        >
          <div>
            <i class="el-icon-star-off"></i>
          </div>
          <div>
            <span>{{aFavoriteListCount}}</span>
          </div>
        </div>
      </el-col>
    </el-row>
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
import { isNotNullORBlank } from "../../../utils/utils";
export default {
  mounted() {
    new Swiper(".swiper-container", {
      slidesPerView: "auto",
    });
    this.currentArticle = this.$store.state.currentArticle;
    console.log(this.currentArticle);
    console.log(this.$store.state.currentUser);
    if (
      this.$store.state.currentUser.subscribes.some((item, index) => {
        return item.bid == this.currentArticle.uid;
      })
    ) {
      this.haveSubscribedPosterIndex = true;
    }
    console.log(
      this.currentArticle.commentList
        ? this.currentArticle.commentList.length
        : "0"
    );

    this.commentListCount = this.currentArticle.commentList
      ? this.currentArticle.commentList.length
      : "0";
    this.aSubscribeListCount = this.currentArticle.aSubscribeList
      ? this.currentArticle.aSubscribeList.length
      : "0";
    this.aFavoriteListCount = this.currentArticle.aFavoriteList
      ? this.currentArticle.aFavoriteList.length
      : "0";
    // this.originCommentList = this.$store.state.currentArticle.commentList;
    this.updateViewCount(this.currentArticle.id);
    // console.log(document.getElementsByClassName("subscribe-article-wrap")[0]);
    if (
      this.currentArticle.aSubscribeList !== null &&
      this.currentArticle.aSubscribeList.some((item, index) => {
        return item.uid == this.$store.state.currentUser.id;
      })
    ) {
      document.getElementsByClassName("subscribe-article-wrap")[0].style.color =
        "#ff9900";
    }
    if (
      this.currentArticle.aFavoriteList !== null &&
      this.currentArticle.aFavoriteList.some((item, index) => {
        return item.uid == this.$store.state.currentUser.id;
      })
    ) {
      document.getElementsByClassName("favorite-article-wrap")[0].style.color =
        "#ff9900";
    }
    document.getElementsByClassName(
      "page-container-wrap"
    )[0].onscroll = this.onmousewheel;
    document.getElementsByClassName("bbb-routes-wrap")[0].click();
  },
  methods: {
    loadUserById() {
      getRequest("/user/" + this.currentArticle.uid).then(
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
    goback() {
      this.$router.push({
        name: this.$store.state.subscribePrePath,
      });
    },
    onmousewheel(e) {
      var dy = document.getElementsByClassName("page-container-wrap")[0]
        .scrollTop;
      console.log(dy);
      var shows = Array.from(
        document.getElementsByClassName("article-detail-slide-show")
      );
      var disappears = Array.from(
        document.getElementsByClassName("article-detail-slide-disappear")
      );
      if (dy > 80) {
        shows.forEach((item, index) => {
          shows[index].style.display = "block";
        });
        disappears.forEach((item, index) => {
          disappears[index].style.display = "none";
        });
      } else if (dy <= 80) {
        shows.forEach((item, index) => {
          shows[index].style.display = "none";
        });
        disappears.forEach((item, index) => {
          disappears[index].style.display = "block";
        });
      }
    },
    goUserInterface() {
      this.loadUserById();
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "UserInterface",
      });
    },
    updateViewCount(aid) {
      var _this = this;
      putRequest("/article/viewCount", { aid: aid }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "浏览更新失败!" });
          } else {
            _this.$message({ type: msg.data.status, message: msg.data.msg });
            _this.currentArticle.viewCount = _this.currentArticle.viewCount + 1;
            _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            // console.log({type: msg.data.status, message:msg.data.msg});
          }
        },
        {
          function(msg) {
            _this.$message({ type: "error", message: "浏览更新失败!" });
          },
        }
      );
    },
    newArticleCommentM() {
      if (!isNotNullORBlank(this.newArticleComment.content)) {
        this.$message({ type: "error", message: "评论不能为空!" });
        return;
      }
      this.newArticleComment.aid = this.currentArticle.id;
      this.newArticleComment.uid = this.$store.state.currentUser.id;
      var _this = this;
      postRequest("/article/new/articleComment", {
        newArticleComment: JSON.stringify(_this.newArticleComment),
      }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "发表评论失败!" });
          } else {
            _this.$message({
              type: msg.data.respBean.status,
              message: msg.data.respBean.msg,
            });
            // console.log(msg.data.newArticleComment);
            _this.newArticleComment.content = "";
            _this.currentArticle.commentList.push(msg.data.newArticleComment);
            _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            // console.log({type: msg.data.status, message:msg.data.msg});
          }
        },
        {
          function(msg) {
            _this.$message({ type: "error", message: "发表评论失败!" });
          },
        }
      );
    },
    subscribeArticle() {
      var _this = this;
      var subscribeButton = document.getElementsByClassName(
        "subscribe-article-wrap"
      )[0];
      // console.log(
      //   _this.currentArticle.aSubscribeList.some((item, index) => {
      //     return item.uid == _this.$store.state.currentUser.id;
      //   })
      // );
      if (
        _this.currentArticle.aSubscribeList.some((item, index) => {
          return item.uid == _this.$store.state.currentUser.id;
        })
      ) {
        deleteRequest(
          "/article/cancelSub/" +
            _this.currentArticle.id +
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
              subscribeButton.style.color = "#000000";
              _this.currentArticle.aSubscribeList.forEach((item, index) => {
                if (item.uid == _this.$store.state.currentUser.id) {
                  _this.currentArticle.aSubscribeList.splice(index, 1);
                }
              });
              _this.aSubscribeListCount =
                _this.currentArticle.aSubscribeList.length;
              _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
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
            _this.currentArticle.id +
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
              _this.currentArticle.aSubscribeList.push(msg.data.newASubscribe);
              _this.aSubscribeListCount =
                _this.currentArticle.aSubscribeList.length;
              _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
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
    favoriteArticle() {
      var _this = this;
      var favoriteButton = document.getElementsByClassName(
        "favorite-article-wrap"
      )[0];
      // console.log(
      //   _this.currentArticle.aFavoriteList.some((item, index) => {
      //     return item.uid == _this.$store.state.currentUser.id;
      //   })
      // );
      if (
        _this.currentArticle.aFavoriteList.some((item, index) => {
          return item.uid == _this.$store.state.currentUser.id;
        })
      ) {
        deleteRequest(
          "/article/cancelFav/" +
            _this.currentArticle.id +
            "/" +
            _this.$store.state.currentUser.id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "取消收藏失败!" });
            } else {
              _this.$message({
                type: msg.data.status,
                message: msg.data.msg,
              });
              favoriteButton.style.color = "#000000";
              _this.currentArticle.aFavoriteList.forEach((item, index) => {
                if (item.uid == _this.$store.state.currentUser.id) {
                  _this.currentArticle.aFavoriteList.splice(index, 1);
                }
              });
              _this.aFavoriteListCount =
                _this.currentArticle.aFavoriteList.length;
              _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "取消收藏失败!" });
            },
          }
        );
      } else {
        postRequest(
          "/article/fav/" +
            _this.currentArticle.id +
            "/" +
            _this.$store.state.currentUser.id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "收藏失败!" });
            } else {
              _this.$message({
                type: msg.data.respBean.status,
                message: msg.data.respBean.msg,
              });
              favoriteButton.style.color = "#FF9900";
              // console.log(msg.data.newAFavorite);
              _this.currentArticle.aFavoriteList.push(msg.data.newAFavorite);
              _this.aFavoriteListCount =
                _this.currentArticle.aFavoriteList.length;
              _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "收藏失败!" });
            },
          }
        );
      }
    },
    subscribeUser() {
      // this.haveSubscribedPosterIndex = true;
      var _this = this;
      // var subscribeButton = document.getElementsByClassName(
      //   "subscribe-article-wrap"
      // )[0];
      // console.log(
      //   _this.currentArticle.aSubscribeList.some((item, index) => {
      //     return item.uid == _this.$store.state.currentUser.id;
      //   })
      // );
      if (
        this.$store.state.currentUser.subscribes.some((item, index) => {
          return item.bid == this.currentArticle.uid;
        })
      ) {
        console.log("取消关注");
        deleteRequest(
          "/user/cancelSub/" +
            _this.$store.state.currentUser.id +
            "/" +
            _this.currentArticle.uid
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "取消关注失败!" });
            } else {
              _this.$message({
                type: msg.data.status,
                message: msg.data.msg,
              });
              // subscribeButton.style.color = "#000000";
              var currentUser = _this.$store.state.currentUser;
              currentUser.subscribes.forEach((item, index) => {
                if (item.bid == _this.currentArticle.uid) {
                  currentUser.subscribes.splice(index, 1);
                }
              });
              _this.$store.dispatch("setCurrentUser", currentUser);
              _this.haveSubscribedPosterIndex = false;
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
            _this.currentArticle.uid
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "关注失败!" });
            } else {
              _this.$message({
                type: msg.data.respBean.status,
                message: msg.data.respBean.msg,
              });
              // subscribeButton.style.color = "#FF9900";
              // console.log(msg.data.newUSubscribe);
              var currentUser = _this.$store.state.currentUser;
              currentUser.subscribes.push(msg.data.newUSubscribe);
              _this.$store.dispatch("setCurrentUser", currentUser);
              _this.haveSubscribedPosterIndex = true;
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
    cancelSubscribeUser() {
      // console.log('取消关注');
      // this.haveSubscribedPosterIndex = false;
    },
  },
  data() {
    return {
      articleRelateCategory: ["明明是我先水", "每日崩坏", "明明是我先水"],
      currentArticle: "",
      haveSubscribedPosterIndex: false,
      newArticleComment: {
        aid: "",
        content: "",
        parentId: "-1",
        uid: "",
      },
      originCommentList: "",
      commentListCount: "",
      aFavoriteListCount: "",
      aSubscribeListCount: "",
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
.title-input-wrap .el-input__inner {
  background-color: #fff;
  border: 0;
}
.select-plate-topic .el-input__inner {
  /* text-align: center; */
  font-weight: bolder;
}
.new-comment-to-comment .v-enter {
  opacity: 0;
  transform: translateY(100%);
}
.new-comment-to-comment .v-leave-to {
  opacity: 0;
  transform: translateY(100%);
  position: absolute;
}
.new-comment-to-comment .v-enter-active,
.new-comment-to-comment .v-leave-active {
  transition: all 0.5s ease;
}
</style>