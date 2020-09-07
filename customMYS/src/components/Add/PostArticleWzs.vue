<template>
  <div class="page-container-wrap">
    <el-row style="padding-bottom:30px;padding-top:30px;font-size:20px;background-color:#dddddd;">
      <el-col :span="3" :offset="1">
        <div class="flex-div-start-wrap" style="height:30px;cursor:pointer;" @click="cancelEdit()">
          <i class="el-icon-arrow-left"></i>
        </div>
      </el-col>
      <el-col :span="16" :offset="0">
        <div class="flex-div-center-wrap" style="height:30px;">
          <span>发帖</span>
        </div>
      </el-col>
      <el-col :span="3" :offset="0">
        <div class="flex-div-end-wrap" style="height:30px;">
          <span
            @click="saveBlog(1)"
            style="font-size:15px;cursor:pointer;background-color:#3399ff;color:#dddddd;border-radius:4px;width:40%;border:solid 1px #dddddd;height:30px;line-height:30px;width:60px;"
          >发布</span>
        </div>
      </el-col>
    </el-row>
    <div style="background-color:#fff;">
      <div
        class="flex-div-start-wrap select-plate-topic"
        style="height:60px;padding-left:3%;padding-right:2%;"
      >
        <span style="height:40px;line-height:40px;width:120px;">发布到：</span>
        <!-- <el-input placeholder="（选择板块和话题获得更多浏览！）"></el-input> -->
        <el-select
          v-model="article.pid"
          placeholder="请选择文章板块"
          style="width: 200px;"
          @change="selectedPlateChange(article.pid)"
        >
          <el-option-group v-for="(item,index) in forums" :key="index" :label="item.forumname">
            <!-- <el-option v-for="item in plates" :key="item.id" :label="item.platename" :value="item.id"></el-option> -->
            <el-option
              v-for="(item1) in item.plateList"
              :key="item1.platename"
              :label="item1.platename"
              :value="item1.id"
            ></el-option>
          </el-option-group>
        </el-select>
        <!-- <i class="el-icon-arrow-right"></i> -->
        <el-input v-model="article.title" placeholder="标题（必填，不超过30字）" style></el-input>
      </div>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:92%;margin-bottom:0px;margin-top:0px;"
      />
      <div class="title-input-wrap" style="height:60px;line-height:60px;">
        <!-- <el-input placeholder="标题（必填，不超过30字）"></el-input> -->
        <!-- <el-checkbox-group v-model="checkedTopics" @change="changeCheckedTopics()"> -->
        <el-checkbox-group v-model="article.articleTopics" @change="changeCheckedTopics()">
          <el-checkbox v-for="(item,index) in topics" :label="item" :key="index">{{item.topicname}}</el-checkbox>
        </el-checkbox-group>
      </div>
      <hr
        style="background-color:#dddddd;height:0.5px;border:none;width:92%;margin-bottom:0px;margin-top:0px;"
      />
      <div id="editor" style="padding-left:3%;padding-right:2%;">
        <mavon-editor
          style="height: 100%;width: 100%;"
          ref="md"
          @imgAdd="imgAdd"
          @imgDel="imgDel"
          v-model="article.mdContent"
        ></mavon-editor>
      </div>
    </div>
  </div>
</template>
<script>
import { postRequest } from "../../utils/api";
import { putRequest } from "../../utils/api";
import { deleteRequest } from "../../utils/api";
import { getRequest } from "../../utils/api";
import { uploadFileRequest } from "../../utils/api";
// Local Registration
import { mavonEditor } from "mavon-editor";
// 可以通过 mavonEditor.markdownIt 获取解析器markdown-it对象
import "mavon-editor/dist/css/index.css";
import { isNotNullORBlank } from "../../utils/utils";

export default {
  mounted: function () {
    console.log(this.$store.state.forums);
    this.forums = this.$store.state.forums;
    // this.forums.forEach((item,index) => {
    //   item.forEach((item1,index1) => {
    //     this.plates.push(item1);
    //   })
    // });
  },
  components: {
    mavonEditor,
  },
  methods: {
    cancelEdit() {
      this.$router.push(this.$store.state.subscribePrePath);
    },
    saveBlog(state) {
      if (!isNotNullORBlank(this.article.title, this.article.mdContent)) {
        this.$message({ type: "error", message: "数据不能为空!" });
        return;
      }
      this.article.htmlContent = this.$refs.md.d_render;
      this.article.state = state;
      this.article.uid = this.$store.state.currentUser.id;
      var _this = this;
      // console.log(this.article);
      postRequest("/article/new/article", {
        article: JSON.stringify(this.article),
      }).then(
        (resp) => {
          console.log(resp);
          if (resp.status == 200) {
            // _this.article.id = resp.data.msg;
            _this.$message({
              type: "success",
              message: "发表成功!",
            });
            // console.log(bus);
            // window.bus.$emit("reloadAllArticles", "msg");
            console.log(resp.status);
            
            console.log(resp.data.newArticle);
            
            _this.$store.dispatch("setCurrentArticle", resp.data.newArticle);
            _this.$router.push({name:"ArticleDetail"});
          }
        },
        (resp) => {
          _this.$message({
            type: "error",
            message: "文章发表失败!",
          });
        }
      );
    },
    selectedPlateChange(pid) {
      this.article.articleTopics = [];
      this.forums.forEach((item, index) => {
        item.plateList.forEach((item1, index1) => {
          if (item1.id == pid) {
            this.topics = item1.topicList;
            console.log(this.forums);
            console.log(item1.topicList);
            console.log(this.topics);
          }
        });
      });
      console.log(this.article.pid);
    },
    changeCheckedTopics() {
      console.log(this.article.articleTopics);
    },
    imgAdd(pos, $file) {
      console.log(pos);
      console.log($file);
      var _this = this;
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append("image", $file);
      uploadFileRequest("/article/uploadimg", formdata).then((resp) => {
        var json = resp.data;
        if (json.status == "success") {
          //            _this.$refs.md.$imgUpdateByUrl(pos, json.msg)
          _this.$refs.md.$imglst2Url([[pos, json.msg]]);
        } else {
          _this.$message({ type: json.status, message: json.msg });
        }
      });
    },
    imgDel(pos) {},
    loadAllArticles() {
      this.loadBlogs("大别野");
      this.loadBlogs("崩坏3");
      this.loadBlogs("崩坏学园2");
      this.loadBlogs("原神");
      this.loadBlogs("未定事件簿");
    },
    loadBlogs(forumname) {
      var _this = this;
      var url = "";
      url = "/admin/article/all" + "?forumname=" + forumname;
      getRequest(url).then(
        (resp) => {
          if (resp.status == 200) {
            console.log("=200");
            // console.log(resp.data.articles);
            switch (forumname) {
              case "大别野":
                this.$store.dispatch("setDabieyeArticles", resp.data.articles);
                break;
              case "崩坏3":
                this.$store.dispatch("setBBBArticles", resp.data.articles);
                break;
              case "崩坏学园2":
                this.$store.dispatch("setBBArticles", resp.data.articles);
                break;
              case "原神":
                this.$store.dispatch("setYuanShenArticles", resp.data.articles);
                break;
              case "未定事件簿":
                this.$store.dispatch("setWeiDingArticles", resp.data.articles);
                break;
            }
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
  },
  data() {
    return {
      forums: [{ platename: "..." }],
      plates: [],
      topics: [],
      checkedTopics: [],
      article: {
        // id: "-1",
        dynamicTags: [],
        title: "",
        mdContent: "",
        cid: "",
        state: "",
        articleTopics: [],
        uid: "",
        pid: "",
      },
    };
  },
};
</script>
<style>
.select-plate-topic .el-input__inner {
  background-color: transparent;
  border: 0;
}
.el-select-group__title {
  color: #000000;
  font-weight: bolder;
  font-size: 15px;
}
.el-select-group .el-select-dropdown__item {
  padding-left: 40px;
}
.title-input-wrap .el-input__inner {
  background-color: transparent;
  border: 0;
}
.select-plate-topic .el-input__inner {
  /* text-align: center; */
  font-weight: bolder;
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
.page-container-wrap {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>
