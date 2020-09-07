<template>
  <div style="height:100%;position:absolute;top:0px;left:0px;width:100%;overflow-y:auto;">
    <div
      class="news-menu-wrap"
      style="position:fixed;top:0px;left:0px;width:100%;height:80px;line-height:80px;z-index:2;"
    >
      <el-menu default-active="0" mode="horizontal" router style="height:80px;">
        <template v-for="(item,index) in this.$router.options.routes[1].children[3].children">
          <el-menu-item class="news-menu-item-wrap" :index="item.path" :key="index">{{item.name}}</el-menu-item>
        </template>
      </el-menu>
    </div>
    <div style="padding-top:80px;padding-bottom:80px;">
      <router-view></router-view>
    </div>

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
        document.getElementsByClassName('news-menu-item-wrap')[1].click();
        this.getArticlesOfSubUser(this.$store.state.currentUser.id);
    },
    methods: {
      getArticlesOfSubUser(id) {
      var _this = this;
      getRequest("/article/subUser/" + id).then(
        (resp) => {
          if (resp.status == 200) {
            // _this.articlesOfSubUser = resp.data;
            console.log(resp.data);
            _this.$store.dispatch('setArticlesOfSubUser',resp.data);
          } else {
            _this.$message({ type: "error", message: "获取文章失败" });
          }
        },
        (resp) => {
          _this.$message({ type: "error", message: "获取文章失败" });
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
.news-menu-wrap .el-menu {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #dddddd;
}
.news-menu-wrap .el-menu-item {
  margin-left: 5%;
  margin-right: 5%;
  font-size: 20px;
}
.news-menu-wrap .el-menu--horizontal .el-menu-item:focus,
.el-menu--horizontal .el-menu-item:hover {
  background-color: #dddddd;
}
</style>