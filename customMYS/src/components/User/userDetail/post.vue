<template>
  <div style="  padding-left: 3%;padding-right: 3%;padding-bottom: 70px;background-color: #fff;">
    <div v-for="(item,index) in userArticleList" :key="index">
      <div
        style="padding-top:20px;background-color:rgba(255,255,255,1);cursor:pointer;"
        @click="goArticleDetail(item.id)"
      >
        <div style="text-align:left;">
          <span
            style="font-size:30px;color:#dddddd;"
          >{{item.editTime | formatDateTimeCommentDayHour}}</span>
          <span style="font-size:15px;color:#dddddd;">{{item.editTime | formatDateTimeComment}}·</span>
          <span style="font-size:15px;color:#dddddd;">{{item.forumname}}</span>
        </div>
        <div style="text-align:left;margin-top:20px;font-size:20px">
          <span>{{item.title}}</span>
        </div>
        <div style="text-align:left;margin-top:20px;">
          <span>{{item.summary}}</span>
        </div>
        <div>
          <img
            src="../../../img/米游姬.jpg"
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
              <i class="el-icon-view"></i>
              <span>{{item.viewCount}}</span>
            </span>
            <span style="margin-left:12%;">
              <i class="el-icon-chat-line-square"></i>
              <span>{{item.aCommentCount}}</span>
            </span>
            <span style="margin-left:12%;" class="subscribe-article-wrap">
              <!-- <i class="el-icon-info" @click.stop="subscribeArticle(index)" style="cursor:pointer;"></i> -->
              <i class="fa fa-thumbs-o-up" aria-hidden="true" @click.stop="subscribeArticle(index)" style="cursor:pointer;"></i>
              <span
                @click.stop="subscribeArticle(index)"
                style="cursor:pointer;"
              >{{item.aSubscribeCount}}</span>
            </span>
          </span>
        </div>
        <hr style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:1px;" />
      </div>
    </div>
    <div style="padding-top:20px;background-color:rgba(255,255,255,1);">
      <div style="text-align:left;">
        <span style="font-size:30px;color:#dddddd;">21</span>
        <span style="font-size:15px;color:#dddddd;">5月/2019年</span>
        <span style="font-size:15px;color:#dddddd;">·崩坏3</span>
      </div>
      <div style="text-align:left;margin-top:20px;font-size:20px">
        <span>帖子标题</span>
      </div>
      <div style="text-align:left;margin-top:20px;">
        <span>帖子内容</span>
      </div>
      <div>
        <img
          src="../../../img/米游姬.jpg"
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
            <i class="el-icon-view"></i>
            <span>10</span>
          </span>
          <span style="margin-left:12%;">
            <i class="el-icon-chat-line-square"></i>
            <span>10</span>
          </span>
          <span style="margin-left:12%;">
            <!-- <i class="el-icon-info"></i> -->
            <i class="fa fa-thumbs-o-up" aria-hidden="true" @click.stop="subscribeArticle(index)" style="cursor:pointer;"></i>
            <span>10</span>
          </span>
        </span>
      </div>
      <hr style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:1px;" />
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
export default {
  mounted() {
    this.userArticleList = this.$store.state.currentUser.articleList;
    this.$nextTick(() => {
      this.userArticleList.forEach((item, index) => {
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
      userArticleList: "",
    };
  },
  methods: {
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
      console.log(_this.userArticleList[ind]);

      if (
        _this.userArticleList[ind].aSubscribeList.some((item, index) => {
          return item.uid == _this.$store.state.currentUser.id;
        })
      ) {
        deleteRequest(
          "/article/cancelSub/" +
            _this.userArticleList[ind].id +
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
              _this.userArticleList[ind].aSubscribeCount -= 1;
              _this.userArticleList[ind].aSubscribeList.forEach(
                (item, index) => {
                  if (item.uid == _this.$store.state.currentUser.id) {
                    _this.userArticleList[ind].aSubscribeList.splice(index, 1);
                  }
                }
              );
              var interfaceUser = _this.$store.state.interfaceUser;
              interfaceUser.articleList = _this.userArticleList;
              _this.$store.dispatch("setInterfaceUser", interfaceUser);
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
            _this.userArticleList[ind].id +
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
              _this.userArticleList[ind].aSubscribeCount += 1;
              _this.userArticleList[ind].aSubscribeList.push(
                msg.data.newASubscribe
              );
              var interfaceUser = _this.$store.state.interfaceUser;
              interfaceUser.articleList = _this.userArticleList;
              _this.$store.dispatch("setInterfaceUser", interfaceUser);
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

.kkry-wrap {
  font-size: 40px;
}
</style>