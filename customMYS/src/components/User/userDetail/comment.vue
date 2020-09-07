<template>
  <div class="ud-comment-wrap">

    <div style="padding-top:20px;cursor:pointer;" v-for="(item,index) in userCommentList" :key="index" @click="goArticleDetail(item.aid)">
      <div style="text-align:left;">
        <span style="font-size:30px;color:#dddddd;">{{item.publishDate | formatDateTimeCommentDayHour}}</span>
        <span style="font-size:12px;color:#dddddd;">{{item.publishDate | formatDateTimeComment}}·</span>
        <span style="font-size:12px;color:#dddddd;">{{item.forumname}}</span>
      </div>
      <div style="text-align:left;height:60px;line-height:60px;">
        <span style="font-size:20px;color:#000000;">{{item.content}}</span>
      </div>
      <div
        style="text-align:left;border-radius:5px;background-color:#dddddd;line-height:30px;padding-left:2%;padding-right:2%;padding-top:10px;padding-bottom:10px;"
      >
        <span
          style="width:100%;font-size:15px;color:#000000;display:block;white-space:pre-wrap;overflow:hidden;"
        >回复帖子：{{item.articleTitle}}</span>
      </div>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:90%;margin-bottom:1px;margin-top:20px;"
      />
    </div>
    <div style="padding-top:20px;">
      <div style="text-align:left;">
        <span style="font-size:30px;color:#dddddd;">21</span>
        <span style="font-size:12px;color:#dddddd;">5月/2019年</span>
        <span style="font-size:12px;color:#dddddd;">·崩坏3</span>
      </div>
      <div style="text-align:left;height:60px;line-height:60px;">
        <span style="font-size:20px;color:#000000;">评论内容</span>
      </div>
      <div
      
        style="text-align:left;border-radius:5px;background-color:#dddddd;line-height:30px;padding-left:2%;padding-right:2%;padding-top:10px;padding-bottom:10px;"
      >
        <span
          style="width:100%;font-size:15px;color:#000000;display:block;white-space:pre-wrap;overflow:hidden;"
        >回复帖子：帖子标题white-space -- 通过HTML文档的源代码的排版方式控制页面显示文本的排版方式取值: normal | pre | nowrap | pre-wrap | pre-line | inheritnormal: 正常无变化(默认处理方式.文本自动处理换行.假如抵达容器边界内容会转到下一行)pre: 保持HTML源代码的空格与换行,等同与pre标签nowrap: 强制文本在一行,除非遇到br换行标签pre-wrap: 同pre属性,但是遇到超出容器范围的时候会自动换行pre-line: 同pre属性,但是遇到连续空格会被看作一个空格</span>
      </div>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:90%;margin-bottom:1px;margin-top:20px;"
      />
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
      this.userCommentList = this.$store.state.currentUser.commentList;
    },
  data() {

    return {
      userCommentList:''
    }
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
  },
};
</script>
<style>
.ud-comment-wrap {
  padding-left: 3%;
  padding-right: 3%;
  padding-bottom: 70px;
  background-color: #fff;
}
.kkry-wrap {
  font-size: 40px;
}
</style>