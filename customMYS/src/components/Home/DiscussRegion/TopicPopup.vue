<template>
  <div
    class="topic-popup"
    v-show="popupIndex"
    style="position:absolute;top:153px;left:0px;width:100%;background-color:#F8F8FF;z-index:2;"
  >
    <div style="height:40px;margin-left:8%;margin-right:8.2%;">
      <span style="float:left;background-color:#F8F8FF;line-height:40px;font-size:12px;">请选择分类</span>
      <i
        class="el-icon-arrow-up"
        style="float:right;color:#dddddd;font-size:15px;line-height:40px;"
        @click="showtopic"
      ></i>
    </div>
    <hr
      style="background-color:#dddddd;height:0.5px;border:none;width:100%;margin-bottom:0px;margin-top:-10px;"
    />
    <div style="margin-left:6%;margin-right:6%;">
      <table cellspacing="15" style="width:100%;font-size:13px;">
        <tr>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[0]"
            @click="clickTopicPop(0)"
          >
            <span style="padding-left:5%;">{{topic[0].topicname}}</span>
          </td>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[1]"
             @click="clickTopicPop(1)"
          >
            <span style="padding-left:5%;">{{topic[1].topicname}}</span>
          </td>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[2]"
             @click="clickTopicPop(2)"
          >
            <span style="padding-left:5%;">{{topic[2].topicname}}</span>
          </td>
        </tr>
        <tr>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[3]" @click="clickTopicPop(3)"
          >
            <span style="padding-left:5%;">{{topic[3].topicname}}</span>
          </td>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[4]" @click="clickTopicPop(4)"
          >
            <span style="padding-left:5%;">{{topic[4].topicname}}</span>
          </td>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[5]" @click="clickTopicPop(5)"
          >
            <span style="padding-left:5%;">{{topic[5].topicname}}</span>
          </td>
        </tr>
        <tr>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[6]" @click="clickTopicPop(6)"
          >
            <span style="padding-left:5%;">{{topic[6].topicname}}</span>
          </td>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[7]" @click="clickTopicPop(7)"
          >
            <span style="padding-left:5%;">{{topic[7].topicname}}</span>
          </td>
          <td
            class="swiper-slide-pop"
            style="background-color:#dddddd;width:2%;border-radius:4px;height:40px;cursor:pointer;"
            v-if="topic[8]" @click="clickTopicPop(8)"
          >
            <span style="padding-left:5%;">{{topic[8].topicname}}</span>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
export default {
  mounted() {
  },
  updated() {},
  watch: {
    topic() {
      // console.log(this.topic);
    }
  },
  data() {
    return {
      popupIndex: false,
      chosenTopicIndex: 0,
    };
  },
  methods: {
    showtopic() {
      this.popupIndex = false;
    },
    clickTopicPop(ind) {
      this.$parent.clickTopic(ind);
    }
  },
  props: ["topic"],
};
</script>
<style>
</style>