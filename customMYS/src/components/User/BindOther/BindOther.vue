<template>
  <div>
      <div style="display:flex;justify-content:flex-start;margin-bottom:10px;">
    <span style="font-size:10px;">|请填写想要绑定的角色信息</span>
    <span style="color:#969696;font-size:10px;">(官方服角色自动绑定)</span>
      </div>
    <el-form ref="newCharForm" :model="newCharForm">
      <el-form-item>
        <el-select v-model="newCharForm.game" placeholder="崩坏三" style="width:100%;">
          <el-option value="崩坏三"></el-option>
          <el-option value="崩坏学园二"></el-option>
          <el-option value="未定事件簿"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="newCharForm.channel" placeholder="请选择渠道" style="width:100%;">
          <el-option value="华为"></el-option>
          <el-option value="酷派"></el-option>
          <el-option value="联想"></el-option>
          <el-option value="oppo"></el-option>
          <el-option value="vivo"></el-option>
          <el-option value="金立"></el-option>
          <el-option value="魅族"></el-option>
          <el-option value="豌豆荚"></el-option>
          <el-option value="九游"></el-option>
          <el-option value="小米"></el-option>
          <el-option value="360"></el-option>
          <el-option value="应用宝服"></el-option>
          <el-option value="B站服"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-input v-model="newCharForm.uid" placeholder="请输入角色uid"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="newCharForm.grade" placeholder="请输入角色等级"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="newCharForm.verCode" placeholder="请输入验证码"></el-input>
      </el-form-item>
      <el-form-item>
          <el-button type="primary" @click="submitForm('newCharForm')" size="medium">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      newCharForm: {
        game: null,
        channel: null,
        uid: null,
        grade: null,
        verCode: null
      }
    };
  },
  methods: {
      submitForm(fromName) {
          
      }
  },
};
</script>
<style>

</style>