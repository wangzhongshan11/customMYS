<template>
  <div>
    <div v-for="(item,index) in totalCommentList" :key="index">
      <el-row style="padding-bottom:30px;padding-top:30px;font-size:20px;background-color:#fff;">
        <el-col :span="2" :offset="1">
          <div class="flex-div-start-wrap" style="height:30px;cursor:pointer;">
            <img :src="item.userface" alt="wzs" style="width:40px;height:40px;border-radius:20px;" />
          </div>
        </el-col>
        <el-col :span="16" :offset="0">
          <div style="margin-left:-2%;">
            <div class="flex-div-start-wrap" style="cursor:pointer;font-size:12px;">
              <span style="font-size:12px;">{{item.nickname}}</span>
              <i class="el-icon-info" style="margin-left:5px;"></i>
              <span
                v-show="(item.uid == currentArticle.uid)"
                style="color:#fff;background-color:#ff6600;border-radius:3px;margin-left:5px;width:30px;"
              >楼主</span>
            </div>
            <div class="flex-div-start-wrap" style="cursor:pointer;">
              <span
                style="font-size:12px;"
              >{{ascCommentIndex?(index+1):(totalCommentListCount-index)}}F</span>
            </div>
          </div>
        </el-col>

        <el-col :span="4" :offset="0">
          <div class="flex-div-end-wrap" style="height:30px;cursor:pointer;color:#dddddd;">
            <i class="el-icon-more"></i>
          </div>
        </el-col>
      </el-row>
      <div style="padding-left:11%;padding-right:2%">
        <div style="float:left">{{item.content}}</div>
        <div style="clear:both;"></div>

        <div
          style="background-color:#dddddd;padding-left:2%;padding-right:8%;border-radius:5px;margin-top:15px;margin-bottom:10px;height:auto;"
        >
          <div
            class="flex-div-start-wrap sub-comment-list"
            v-for="(item1,index1) in item.subCommentList"
            :key="index1 + 'sub'"
            v-show="index1<3"
          >
            <div style="height:30px;line-height:30px;">
              <span style="color:#3399ff;">{{item1.nickname}}</span>
              <span
                v-show="(item1.uid == currentArticle.uid)"
                style="color:#fff;background-color:#ff6600;border-radius:3px;width:30px;font-size:12px;margin-left:5px;margin-right:5px;height:16px;line-height:16px;"
              >楼主</span>
              <span style="color:#3399ff;">:</span>
              <span style="margin-left:5px;">{{item1.content}}</span>
            </div>
          </div>
          <div
            class="flex-div-start-wrap show-more-sub-comment"
            style="font-size:15px;height:30px;line-height:30px;cursor:pointer;"
            v-show="item.subCommentListCount>3"
            @click="showMoreSubComment(index)"
          >
            <span>全部{{item.subCommentListCount}}条评论</span>
            <i class="el-icon-arrow-right"></i>
          </div>
        </div>
        <el-row style="font-size:15px;color:#000000;">
          <el-col :span="8" :offset="0">
            <div class="flex-div-start-wrap" style="height:30px;cursor:pointer;">
              <span>{{item.publishDate | formatDateTime}}</span>
            </div>
          </el-col>
          <el-col :span="2" :offset="12">
            <div class="flex-div-end-wrap" style="cursor:pointer;">
              <i class="el-icon-chat-line-square" @click="openCommentToCommentM(index)"></i>
              <span style="margin-left:5px;" @click="openCommentToCommentM(index)">回复</span>
            </div>
          </el-col>
          <el-col :span="2" :offset="0">
            <div class="flex-div-end-wrap subscribe-to-comment" style="cursor:pointer;">
              <!-- <i class="el-icon-info" @click="subscribeComment(index)"></i> -->
              <i class="fa fa-thumbs-o-up" aria-hidden="true" @click="subscribeComment(index)"></i>
              <span
                style="margin-left:5px;"
                @click="subscribeComment(index)"
              >{{item.cSubscribeListCount}}</span>
            </div>
          </el-col>
        </el-row>
      </div>
      <hr style="background-color:#dddddd;height:0.5px;border:none;width:85%;margin-left:11%;" />
      <div style="clear:both;"></div>
    </div>
    <div
      class="new-comment-to-comment"
      v-show="false"
      style="position:fixed;padding-left:5%;padding-right:2%;bottom:60px;left:0px;width:91.8%;height:110px;background-color:#dddddd;border-top-left-radius:10px;border-top-right-radius:10px;"
    >
      <transition>
        <div style>
          <div style="height:50px;line-height:50px;">
            <span style="float:left;height:50px;line-height:50px;">
              <span style="font-size:12px;">回复</span>
              <span style="font-size:15px;" class="comment-to-whose-comment"></span>
            </span>
            <i
              class="el-icon-close"
              style="float:right;height:50px;line-height:50px;"
              @click="closeCommentToCommentM()"
            ></i>
          </div>
          <div style="clear:both;"></div>
          <div class="flex-div-around-wrap" style="height:60px;line-height:60px;">
            <el-input
              v-model="newCommentToComment.content"
              placeholder="我有话要说..."
              style="border-radius:4px;"
            ></el-input>
            <span
              @click="newCommentToCommentM()"
              style="font-size:15px;cursor:pointer;background-color:#3399ff;color:#dddddd;border-radius:4px;width:40%;border:solid 1px #dddddd;height:35px;line-height:35px;width:60px;margin-left:15px;"
            >发布</span>
          </div>
        </div>
      </transition>
    </div>
    <div style="height:100px;line-height:100px;">
      <span>(~￣∇￣)~已经到底啦~</span>
    </div>
  </div>
</template>
<script>
import {
  deleteRequest,
  postRequest,
  getRequest,
  putRequest,
} from "../../../utils/api";
import { isNotNullORBlank } from "../../../utils/utils";
export default {
  mounted() {
    this.currentArticle = this.$store.state.currentArticle;
    this.totalCommentList = this.$store.state.currentArticle.commentList;
    this.totalCommentListCount = this.currentArticle.commentList
      ? this.currentArticle.commentList.length
      : "0";
    // console.log(this.$store.state.currentArticle.commentList);
    // console.log(document.getElementsByClassName("order-comment-asc")[0]);
    document
      .getElementsByClassName("order-comment-asc")[0]
      .addEventListener("click", this.orderCommentAsc);
    document
      .getElementsByClassName("order-comment-desc")[0]
      .addEventListener("click", this.orderCommentDesc);
    document
      .getElementsByClassName("order-comment-hot")[0]
      .addEventListener("click", this.orderCommentHot);
    document.getElementsByClassName("order-comment-asc")[0].click();

    this.$nextTick(function () {
      if (this.totalCommentList !== null) {
        this.totalCommentList.forEach((item, index) => {
          if (
            item.cSubscribeList !== null ||
            item.cSubscribeList !== undefined
          ) {
            if (
              item.cSubscribeList.some((item1, index1) => {
                return item1.uid == this.$store.state.currentUser.id;
              })
            ) {
              document.getElementsByClassName("subscribe-to-comment")[
                index
              ].style.color = "#ff9900";
            }
          }
        });
      }
    });
  },
  data() {
    return {
      totalCommentList: [],
      newCommentToComment: {
        aid: "",
        content: "",
        parentId: "-1",
        uid: "",
      },
      commentToCommentIndex: "",
      currentArticle: "",
      ascCommentIndex: "true",
      totalCommentListCount: "",
    };
  },
  methods: {
    openCommentToCommentM(ind) {
      document.getElementsByClassName(
        "new-comment-to-comment"
      )[0].style.display = "block";
      document.getElementsByClassName(
        "comment-to-whose-comment"
      )[0].innerHTML = this.totalCommentList[ind].nickname;
      this.commentToCommentIndex = ind;
    },
    closeCommentToCommentM() {
      document.getElementsByClassName(
        "new-comment-to-comment"
      )[0].style.display = "none";
    },
    newCommentToCommentM() {
      if (!isNotNullORBlank(this.newCommentToComment.content)) {
        this.$message({ type: "error", message: "评论不能为空!" });
        return;
      }
      var ind = this.commentToCommentIndex;
      // console.log(this.layerCommentList[ind]);
      this.newCommentToComment.aid = this.$store.state.currentArticle.id;
      this.newCommentToComment.uid = this.$store.state.currentUser.id;
      this.newCommentToComment.parentId = this.totalCommentList[ind].id;
      var _this = this;
      // console.log(this.newCommentToComment);
      postRequest("/article/new/commetToComment", {
        newCommentToComment: JSON.stringify(_this.newCommentToComment),
      }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "发表评论失败!" });
          } else {
            _this.$message({
              type: msg.data.respBean.status,
              message: msg.data.respBean.msg,
            });
            // console.log(msg.data.newCommentToComment);
            _this.newCommentToComment.content = "";
            var currentArticleC = _this.$store.state.currentArticle;
            currentArticleC.commentList.forEach((item, index) => {
              if (item.id == msg.data.newCommentToComment.parentId) {
                if (currentArticleC.commentList[index].subCommentList == null) {
                  currentArticleC.commentList[
                    index
                  ].subCommentList = new Array();
                  currentArticleC.commentList[index].subCommentList.push(
                    msg.data.newCommentToComment
                  );
                } else {
                  currentArticleC.commentList[index].subCommentList.push(
                    msg.data.newCommentToComment
                  );
                }
                currentArticleC.commentList[index].subCommentListCount =
                  currentArticleC.commentList[index].subCommentListCount + 1;
              }
            });
            // currentArticleC.commentList.push(msg.data.newCommentToComment);
            _this.$store.dispatch("setCurrentArticle", currentArticleC);
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
    showMoreSubComment(index) {
      var moreSubComments = Array.from(
        document.getElementsByClassName("sub-comment-list")
      );
      moreSubComments.forEach((item, index) => {
        moreSubComments[index].style.display = "block";
        moreSubComments[index].style.textAlign = "left";
      });
      document.getElementsByClassName("show-more-sub-comment")[
        index
      ].style.display = "none";
      // console.log(
      //   document.getElementsByClassName("show-more-sub-comment")[index]
      // );
    },
    orderCommentHot() {
      var _this = this;
      this.ascCommentIndex = true;
      // this.totalCommentList = this.$store.state.currentArticle.commentList;
      // console.log(this.totalCommentList);
      getRequest("/article/" + this.currentArticle.id + "/ascComments").then(
        (resp) => {
          if (resp.status == 200) {
            _this.totalCommentList = resp.data;
            _this.currentArticle.commentList = resp.data;
            _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            // console.log(this.totalCommentList);
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );

      document.getElementsByClassName("order-comment-asc")[0].style.color =
        "#dddddd";
      document.getElementsByClassName("order-comment-desc")[0].style.color =
        "#dddddd";
      document.getElementsByClassName("order-comment-hot")[0].style.color =
        "#000000";
    },
    orderCommentAsc() {
      var _this = this;
      this.ascCommentIndex = true;
      // this.totalCommentList = this.$store.state.currentArticle.commentList;
      // console.log(this.totalCommentList);
      getRequest("/article/" + this.currentArticle.id + "/ascComments").then(
        (resp) => {
          if (resp.status == 200) {
            _this.totalCommentList = resp.data;
            _this.currentArticle.commentList = resp.data;
            _this.totalCommentListCount =
              _this.currentArticle.commentList.length;
            _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            // console.log(this.totalCommentList);
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
      document.getElementsByClassName("order-comment-asc")[0].style.color =
        "#000000";
      document.getElementsByClassName("order-comment-desc")[0].style.color =
        "#dddddd";
      document.getElementsByClassName("order-comment-hot")[0].style.color =
        "#dddddd";
    },
    orderCommentDesc() {
      var _this = this;
      this.ascCommentIndex = false;
      getRequest("/article/" + this.currentArticle.id + "/descComments").then(
        (resp) => {
          if (resp.status == 200) {
            _this.totalCommentList = resp.data;
            _this.currentArticle.commentList = resp.data;
            _this.totalCommentListCount =
              _this.currentArticle.commentList.length;
            _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            // console.log(this.totalCommentList);
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
      document.getElementsByClassName("order-comment-asc")[0].style.color =
        "#dddddd";
      document.getElementsByClassName("order-comment-desc")[0].style.color =
        "#000000";
      document.getElementsByClassName("order-comment-hot")[0].style.color =
        "#dddddd";
    },
    subscribeComment(index) {
      var _this = this;
      var subscribeCButton = document.getElementsByClassName(
        "subscribe-to-comment"
      )[index];
      // console.log(
      //   _this.currentArticle.commentList[index].cSubscribeList.some((item, index1) => {
      //     return item.uid == _this.$store.state.currentUser.id;
      //   })
      // );
      // console.log(_this.totalCommentList[index].id);

      if (
        _this.currentArticle.commentList[index].cSubscribeList.some(
          (item, index1) => {
            return item.uid == _this.$store.state.currentUser.id;
          }
        )
      ) {
        deleteRequest(
          "/article/cancelSubComment/" +
            _this.currentArticle.id +
            "/" +
            _this.totalCommentList[index].id +
            "/" +
            _this.$store.state.currentUser.id
        ).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "取消评论点赞失败!" });
            } else {
              _this.$message({
                type: msg.data.status,
                message: msg.data.msg,
              });
              subscribeCButton.style.color = "#000000";
              _this.currentArticle.commentList[index].cSubscribeList.forEach(
                (item, index2) => {
                  if (item.uid == _this.$store.state.currentUser.id) {
                    _this.currentArticle.commentList[
                      index
                    ].cSubscribeList.splice(index2, 1);
                    _this.currentArticle.commentList[
                      index
                    ].cSubscribeListCount =
                      _this.currentArticle.commentList[
                        index
                      ].cSubscribeList.length;
                  }
                }
              );
              _this.$store.dispatch("setCurrentArticle", _this.currentArticle);
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "取消评论点赞失败!" });
            },
          }
        );
      } else {
        postRequest(
          "/article/subComment/" +
            _this.currentArticle.id +
            "/" +
            _this.totalCommentList[index].id +
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
              subscribeCButton.style.color = "#FF9900";
              // console.log(msg.data.newCSubscribe);
              _this.currentArticle.commentList[index].cSubscribeList.push(
                msg.data.newCSubscribe
              );
              _this.currentArticle.commentList[index].cSubscribeListCount =
                _this.currentArticle.commentList[index].cSubscribeList.length;
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
  },
};
</script>
<style>
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