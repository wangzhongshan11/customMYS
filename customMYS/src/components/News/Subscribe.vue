<template>
  <div>
    <transition>
      <div style="padding-left:3%;padding-right:3%;">
        <div style="margin-top:20px;" v-for="(item,index) in articlesOfSubUser" :key="index">
          <el-row style="width:100%;text-align:left;">
            <el-col :span="2" :offset="0">
              <img
                :src="item.userface"
                alt="wzs"
                style="weight:40px;height:40px;border-radius:20px;cursor:pointer;"
                @click="goUserInterface(item.uid)"
              />
            </el-col>
            <el-col :span="22" :offset="0">
              <div
                style="height:40px;padding-left:2%;margin-left:-20px;cursor:pointer;"
                @click="goUserInterface(item.uid)"
              >
                <div style="text-align:left;margin-top:-3px;margin-bottom:5px;">
                  {{item.nickname}}
                  <i class="el-icon-info"></i>
                </div>
                <div style="color:#dddddd;">
                  <span>{{item.editTime | formatDateTime}}</span>
                </div>
              </div>
            </el-col>
          </el-row>
          <div @click="goArticleDetail(item.id)" style="cursor:pointer;">
            <div style="text-align:left;margin-top:20px;font-size:20px">
              <span>{{item.title}}</span>
            </div>
            <div style="text-align:left;margin-top:20px;">
              <span>{{item.summary}}</span>
            </div>
            <div>
              <img
                src="../../img/米游姬.jpg"
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
                <span style="margin-left:12%;" class="subscribe-article-wrap">
                  <i
                    class="el-icon-info"
                    @click.stop="subscribeArticle(index)"
                    style="cursor:pointer;"
                  ></i>
                  <span
                    @click.stop="subscribeArticle(index)"
                    style="cursor:pointer;"
                  >{{item.aSubscribeCount}}</span>
                </span>
              </span>
            </div>
            <div style="height:20px;line-height:20px;font-size:12px;">
              <span style="float:right;">
                <span style>来自频道</span>
                <i class="el-icon-info" style="color:#3399ff;"></i>
                <span style="color:#3399ff;">{{item.forumname}}</span>
              </span>
            </div>
          </div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;"
          />
        </div>
        <div style="margin-top:10px;">
          <el-row style="width:100%;text-align:left;">
            <el-col :span="2" :offset="0">
              <img
                src="../../img/崩坏三.jpg"
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
              src="../../img/米游姬.jpg"
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
          <div style="height:20px;line-height:20px;font-size:12px;">
            <span style="float:right;">
              <span style>来自频道</span>
              <i class="el-icon-info" style="color:#3399ff;"></i>
              <span style="color:#3399ff;">大别野</span>
            </span>
          </div>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;"
          />
        </div>
        <div style="height:100px;line-height:100px;">
          <span>(~￣∇￣)~已经到底啦~</span>
        </div>
      </div>
    </transition>
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
    // this.currentUser = this.$store.state.currentUser;
    this.articlesOfSubUser = this.$store.state.articlesOfSubUser;
    this.$nextTick(() => {
      this.articlesOfSubUser.forEach((item, index) => {
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
    });
  },
  data() {
    return {
      articlesOfSubUser: "",
      currentUser: "",
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
    subscribeArticle(ind) {
      var _this = this;
      var subscribeButton = document.getElementsByClassName(
        "subscribe-article-wrap"
      )[ind];
      // console.log(_this.articlesOfSubUser[ind]);

      if (
        isNotNullORBlank(_this.articlesOfSubUser[ind].aSubscribeList) &&
        _this.articlesOfSubUser[ind].aSubscribeList.some((item, index) => {
          return item.uid == _this.$store.state.currentUser.id;
        })
      ) {
        deleteRequest(
          "/article/cancelSub/" +
            _this.articlesOfSubUser[ind].id +
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
              _this.articlesOfSubUser[ind].aSubscribeCount -= 1;
              _this.articlesOfSubUser[ind].aSubscribeList.forEach(
                (item, index) => {
                  if (item.uid == _this.$store.state.currentUser.id) {
                    _this.articlesOfSubUser[ind].aSubscribeList.splice(
                      index,
                      1
                    );
                  }
                }
              );
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
            _this.articlesOfSubUser[ind].id +
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
              _this.articlesOfSubUser[ind].aSubscribeCount += 1;
              _this.articlesOfSubUser[ind].aSubscribeList.push(
                msg.data.newASubscribe
              );
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
</style>