package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.*;

import java.util.List;


/**
 * Created by wzs on 2020/08/01.
 */
@Mapper
public interface UserMapper {

    User loadUserByUsername(@Param("username") String username);

    long reg(User user);

    int updateUserEmail(@Param("email") String email, @Param("id") Long id);

    List<User> getUserByNickname(@Param("nickname") String nickname);

    List<Role> getAllRole();

    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);

    int deleteUserById(Long uid);

    int deleteUserRolesByUid(Long id);

    int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);

    User getUserById(@Param("id") Long id);

    Integer editUserById(Long id,String nickname,Integer userSex,String userSignature,String userface);

    User getUserByUsername(String username);

    Integer resetPassword(Long id, String password);

    List<ForumGradeExp> getForumGradeExp();

    List<User> searchUsersByKeywords(@Param(value = "keywords") String keywords);

    List<Topic> getSubscribeTopicsByUsername(String username);

    Integer cancelSubscribeTopic(Long uid, Long tid);

    Integer subscribeTopic(Long uid, Long tid);

    Integer registerUser(User registerUser);

    Integer signInForum(Long uid, String forumname);
}
