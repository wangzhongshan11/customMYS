package org.sang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sang.bean.*;
import org.sang.service.UserService;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wzs on 2020/08/01.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/currentUser")
    public User currentUser(String username) {
        return userService.getUserByUsername(username);
    }

    @RequestMapping("/currentUserName")
    public String currentUserName() {
        return Util.getCurrentUser().getNickname();
    }

    @RequestMapping("/currentUserId")
    public Long currentUserId() {
        return Util.getCurrentUser().getId();
    }

    @RequestMapping("/currentUserEmail")
    public String currentUserEmail() {
        return Util.getCurrentUser().getEmail();
    }

    @RequestMapping("/currentUserAvatar")
    public String currentUserAvatar() {
        return Util.getCurrentUser().getUserface();
    }

    @RequestMapping("/isAdmin")
    public Boolean isAdmin() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/updateUserEmail",method = RequestMethod.PUT)
    public RespBean updateUserEmail(String email) {
        if (userService.updateUserEmail(email) == 1) {
            return new RespBean("success", "开启成功!");
        }
        return new RespBean("error", "开启失败!");
    }
    @RequestMapping(value = "/editUser",method = RequestMethod.PUT)
    public RespBean editUserById(String editUser) {
        User editUser1 = null;
        try {
            editUser1 = new ObjectMapper().readValue(editUser, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer result = userService.editUserById(editUser1);
        if (userService.editUserById(editUser1) == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }

    @RequestMapping(value = "/bindOtherGameChara",method = RequestMethod.POST)
    public RespBean bindOtherGameChara(String newCharaForm) {
        GameCharacter gameCharacter1 = null;
        try {
            gameCharacter1 = new ObjectMapper().readValue(newCharaForm, GameCharacter.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Integer result = userService.bindOtherGameChara(gameCharacter1);
        if (userService.bindOtherGameChara(gameCharacter1) == 1) {
            return new RespBean("success","绑定成功！");
        }
        return new RespBean("error","绑定失败！");
    }

    @RequestMapping(value = "/changeDefaultChara",method = RequestMethod.PUT)
    public RespBean changeDefaultChara(String changeIndex) {
        Integer changeIndex1 = Integer.valueOf(changeIndex);
        if (userService.changeDefaultChara(changeIndex1) == 1) {
            return new RespBean("success","设置成功！");
        }
        return new RespBean("error","设置失败！");

    }

    @RequestMapping(value = "/newShipAddress",method = RequestMethod.POST)
    public RespBean newShipAddress(String newShipAddr) {
        ShipAddress shipAddress = null;
        try {
            shipAddress = new ObjectMapper().readValue(newShipAddr,ShipAddress.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(userService.newShipAddress(shipAddress) == 1){
            return new RespBean("success","添加成功！");
        }
        return new RespBean("error","添加失败！");
    }
    @RequestMapping(value = "/changeDefaultAddress",method = RequestMethod.PUT)
    public RespBean changeDefaultAddress(String changeIndex) {
        Integer changeIndex1 = Integer.valueOf(changeIndex);
        if(userService.changeDefaultAddress(changeIndex1) == 1){
            return new RespBean("success","设置成功！");
        }
        return new RespBean("error","设置失败！");
    }

    @RequestMapping(value = "editChosenShipAddress",method = RequestMethod.PUT)
    public RespBean editChosenShipAddress(String newShipAddr) {
        ShipAddress shipAddress = null;
        try {
            shipAddress = new ObjectMapper().readValue(newShipAddr,ShipAddress.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(userService.editChosenShipAddress(shipAddress) == 1){
            return new RespBean("success","修改成功！");
        }
        return new RespBean("error","修改失败！");
    }
    @RequestMapping(value = "/deleteChosenShipAddress",method = RequestMethod.DELETE)
    public RespBean deleteChosenShipAddress(String deleteIndex) {
        Integer deleteIndex1 = Integer.valueOf(deleteIndex);
        if(userService.deleteChosenShipAddress(deleteIndex1) == 1){
            return new RespBean("success","删除成功！");
        }
        return new RespBean("error","删除失败！");
    }
    @RequestMapping(value = "/resetPassword",method = RequestMethod.PUT)
    public RespBean resetPassword(String id,String password) {
        Long id1 = Long.valueOf(id);
        if(userService.resetPassword(id1,password) == 1){
            return new RespBean("success","修改成功！");
        }
        return new RespBean("error","修改失败！");
    }
    @RequestMapping(value = "/getForumGradeExp",method = RequestMethod.GET)
    public List<ForumGradeExp> getForumGradeExp() {
        return userService.getForumGradeExp();
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    public User getArticleById(@PathVariable Long uid) {
        return userService.getUserById(uid);
    }


    @RequestMapping(value = "/user/cancelSub/{aid}/{bid}", method = RequestMethod.DELETE)
    public RespBean cancelSubscribeUser(@PathVariable Long aid, @PathVariable Long bid) {
        if (userService.cancelSubscribeUser(aid, bid) == 1) {
            return new RespBean("success", "取消关注成功!");
        } else {
            return new RespBean("error", "取消关注失败!");
        }
    }

    @RequestMapping(value = "/user/sub/{aid}/{bid}", method = RequestMethod.POST)
    public Map<Object, Object> subscribeUser(@PathVariable Long aid, @PathVariable Long bid) {
        Map<Object, Object> resMap = new HashMap<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subscribe subscribe = new Subscribe();
        subscribe.setAid(aid);
        subscribe.setBid(bid);
        subscribe.setSubscribeDate(timestamp);
        Integer result = userService.subscribeUser(subscribe);
        if (result == 1) {
            Subscribe newUSubscribe = userService.getNewUSubscribeById(aid);
            resMap.put("newUSubscribe", newUSubscribe);
            resMap.put("respBean", new RespBean("success", "关注成功!"));
        } else {
            resMap.put("respBean", new RespBean("success", "关注失败!"));
        }
        return resMap;
    }
    @RequestMapping(value = "/user/cancelSub/topic/{uid}/{tid}", method = RequestMethod.DELETE)
    public RespBean cancelSubscribeTopic(@PathVariable Long uid,@PathVariable Long tid) {
        if (userService.cancelSubscribeTopic(uid,tid) == 1) {
            return new RespBean("success", "取消关注成功!");
        } else {
            return new RespBean("error", "取消关注失败!");
        }
    }

    @RequestMapping(value = "/user/sub/topic/{uid}/{tid}", method = RequestMethod.POST)
    public RespBean subscribeTopic(@PathVariable Long uid,@PathVariable Long tid) {
        Integer result = userService.subscribeTopic(uid,tid);
        if (result == 1) {
            return new RespBean("success", "关注成功!");
        } else {
            return new RespBean("success", "关注成功!");
        }
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public RespBean registerUser(String registerUser) {
        User registerUser1 = new User();
        try {
            registerUser1 = new ObjectMapper().readValue(registerUser,User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        registerUser1.setRegTime(timestamp);
        registerUser1.setNickname(registerUser1.getUsername());
        Integer result = userService.registerUser(registerUser1);
        if (result == 1) {
            return new RespBean("success", "注册成功!");
        } else {
            return new RespBean("success", "注册失败!");
        }
    }
    @RequestMapping(value = "/user/signIn/{uid}/{forumname}", method = RequestMethod.PUT)
    public RespBean signInForum(@PathVariable Long uid, @PathVariable String forumname) {
        if (userService.signInForum(uid, forumname) == 1) {
            return new RespBean("success", "签到成功!");
        } else {
            return new RespBean("error", "签到失败!");
        }
    }
}
