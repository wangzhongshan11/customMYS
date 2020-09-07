<template>
  <div class="add-somthing-movie-wrap">
    <transition>
      <div
        style="width:100%;position:fixed;left:0px;bottom:0px;z-index:99;opacity:1;background-color:#3399FF;height:300px;border-top-left-radius:10px;border-top-right-radius:10px;"
        v-show="addSomethingIndex"
      >
        <div class="flex-div-around-wrap" style="margin-top:50px;">
          <div style="cursor:pointer;" @click="postArticleWzs()">
            <div>
              <i class="el-icon-edit-outline" style="font-size:100px;"></i>
            </div>
            <div style="margin-top:10px;">
              <span style="font-size:15px;">帖子</span>
            </div>
            <div>
              <span style="font-size:12px;color:#dddddd;">讨论&nbsp;分析&nbsp;攻略&nbsp;水日常</span>
            </div>
          </div>
          <div style="cursor:pointer;">
            <div>
              <i class="el-icon-picture-outline" style="font-size:100px;"></i>
            </div>
            <div style="margin-top:10px;">
              <span style="font-size:15px;">图片</span>
            </div>
            <div>
              <span style="font-size:12px;color:#dddddd;">绘画&nbsp;COS&nbsp;手工&nbsp;表情包</span>
            </div>
          </div>
        </div>
        <div style="margin-top:40px;">
          <i
            class="el-icon-close close-add-something"
            style="font-size:40px;color:#dddddd;cursor:pointer;"
            @click="closeAddSomething()"
          ></i>
        </div>
      </div>
    </transition>
  </div>
</template>
<script>
import { getRequest } from "../../utils/api";
export default {
  data() {
    return {
      addSomethingIndex: false,
    };
  },
  methods: {
    closeAddSomething() {
      this.addSomethingIndex = false;
    },
    postArticleWzs() {
      // getRequest("/article/plate/all").then(
      //   (resp) => {
      //     console.log(resp.status);
      //     if (resp.status == 200) {
      //       console.log("=200");
      //       console.log(resp.data);
      //       this.$store.dispatch("setPlates", resp.data);
      //     } else {
      //       console.log("!200");
      //       _this.$message({ type: "error", message: "数据加载失败!" });
      //     }
      //   },
      //   (resp) => {
      //     _this.$message({ type: "error", message: "数据加载失败!" });
      //   }
      // );
      getRequest("/article/forum/all").then(
        (resp) => {
          console.log(resp.status);
          if (resp.status == 200) {
            console.log("=200");
            console.log(resp.data);
            this.$store.dispatch("setForums", resp.data);
            this.$store.dispatch("setSubscribePrePath", this.$route.name);
            this.$router.push({ name: "PostArticleWzs" });
          } else {
            console.log("!200");
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        },
        (resp) => {this.$router.push({ name: "PostArticleWzs" });
          _this.$message({ type: "error", message: "数据加载失败!" });
        }
      );
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
.add-somthing-movie-wrap .v-enter {
  opacity: 0;
  transform: translateY(100%);
}
.add-somthing-movie-wrap .v-leave-to {
  opacity: 0;
  transform: translateY(100%);
  position: absolute;
}
.add-somthing-movie-wrap .v-enter-active,
.add-somthing-movie-wrap .v-leave-active {
  transition: all 0.2s ease;
}
</style>