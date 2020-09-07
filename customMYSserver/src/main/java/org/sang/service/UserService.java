package org.sang.service;

import org.sang.bean.*;
import org.sang.mapper.*;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wzs on 2020/08/01.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesMapper rolesMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SubscribesMapper subscribesMapper;
    @Autowired
    ForumsMapper forumsMapper;
    @Autowired
    GameCharactersMapper gameCharactersMapper;
    @Autowired
    ShipAddressesMapper shipAddressesMapper;
    @Autowired
    ArticleTopicsMapper articleTopicsMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            //避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            return new User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> roles = rolesMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return new User();
        }
        List<Subscribe> subscribes = subscribesMapper.getSubscribesByUsername(username);
        List<Subscribe> fans = subscribesMapper.getFansByUsername(username);
        List<UserForum> userForums = forumsMapper.getForumsByUsername(username);
        List<GameCharacter> gameCharacters = gameCharactersMapper.getGameCharactersByUsername(username);
        List<ShipAddress> shipAddresses = shipAddressesMapper.getShipAddressesByUsername(username);
        List<Topic> subscribeTopicList = userMapper.getSubscribeTopicsByUsername(username);
        user.setSubscribes(subscribes);
        user.setFans(fans);
        user.setUserForums(userForums);
        user.setGameCharacters(gameCharacters);
        user.setShipAddresses(shipAddresses);
        user.setSubscribeTopics(subscribeTopicList);
        return user;
    }
    /**
     * @param user
     * @return 0表示成功
     * 1表示用户名重复
     * 2表示失败
     */
    public int reg(User user) {
        User loadUserByUsername = userMapper.loadUserByUsername(user.getUsername());
        if (loadUserByUsername != null) {
            return 1;
        }
        //插入用户,插入之前先对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);//用户可用
        long result = userMapper.reg(user);
        //配置用户的角色，默认都是普通用户
        String[] roles = new String[]{"2"};
        int i = rolesMapper.addRoles(roles, user.getId());
        boolean b = i == roles.length && result == 1;
        if (b) {
            return 0;
        } else {
            return 2;
        }
    }

    public int updateUserEmail(String email) {
        return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
    }

    public List<User> getUserByNickname(String nickname) {
        List<User> list = userMapper.getUserByNickname(nickname);
        return list;
    }

    public List<Role> getAllRole() {
        return userMapper.getAllRole();
    }

    public int updateUserEnabled(Boolean enabled, Long uid) {
        return userMapper.updateUserEnabled(enabled, uid);
    }

    public int deleteUserById(Long uid) {
        return userMapper.deleteUserById(uid);
    }

    public int updateUserRoles(Long[] rids, Long id) {
        int i = userMapper.deleteUserRolesByUid(id);
        return userMapper.setUserRoles(rids, id);
    }

    public User getUserById(Long id) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            return new User();
        }
        List<Subscribe> subscribes = subscribesMapper.getSubscribesById(id);
        List<Subscribe> fans = subscribesMapper.getFansById(id);
        List<UserForum> userForums = forumsMapper.getForumsById(id);
        List<Article> articleList = articleMapper.getArticlesByUid(id);
        for (Article article : articleList) {
            List<Comment> commentList = articleMapper.getArticleComments(article.getId());
            article.setaCommentCount((long) commentList.size());
            List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(article.getId());
            article.setaSubscribeList(aSubscribeList);
            article.setaSubscribeCount((long) aSubscribeList.size());
        }
        List<Comment> commentList = articleMapper.getCommentsByUid(id);
        List<Article> aSubscribeList = articleMapper.getSubscribeArticlesByUid(id);
        for (Article article : aSubscribeList) {
            List<Comment> commentList1 = articleMapper.getArticleComments(article.getId());
            article.setaCommentCount((long) commentList1.size());
            List<ASubscribe> aSubscribeList1 = articleMapper.getArticleSubscribes(article.getId());
            article.setaSubscribeCount((long) aSubscribeList1.size());
            article.setaSubscribeList(aSubscribeList1);
        }
        user.setSubscribes(subscribes);
        user.setFans(fans);
        user.setUserForums(userForums);
        user.setArticleList(articleList);
        user.setCommentList(commentList);
        user.setaSubscribeList(aSubscribeList);
        return user;
    }

    public Integer editUserById(User editUser) {
        return userMapper.editUserById(editUser.getId(),editUser.getNickname(),editUser.getUserSex(),editUser.getUserSignature(),editUser.getUserface());
    }


    public Integer bindOtherGameChara(GameCharacter gameCharacter) {
        return gameCharactersMapper.bindOtherGameChara(gameCharacter);
    }

    public Integer newShipAddress(ShipAddress shipAddress) {
        return shipAddressesMapper.newShipAddress(shipAddress);
    }

    public Integer changeDefaultAddress(Integer changeIndex) {
        Integer noDefaultAddress = shipAddressesMapper.noDefaultAddress();
        return shipAddressesMapper.changeDefaultAddress(changeIndex);
    }

    public Integer editChosenShipAddress(ShipAddress shipAddress) {
        return shipAddressesMapper.editChosenShipAddress(shipAddress);
    }

    public Integer deleteChosenShipAddress(Integer deleteIndex) {
        return shipAddressesMapper.deleteChosenShipAddress(deleteIndex);
    }

    public Integer resetPassword(Long id, String password) {
        return userMapper.resetPassword(id,passwordEncoder.encode(password));
    }

    public Integer changeDefaultChara(Integer changeIndex) {
        Integer noDefaultChara = gameCharactersMapper.noDefaultChara();
        return gameCharactersMapper.changeDefaultChara(changeIndex);
    }

    public List<ForumGradeExp> getForumGradeExp() {
        return userMapper.getForumGradeExp();
    }

    public Integer cancelSubscribeUser(Long aid, Long bid) {
        return subscribesMapper.cancelSubscribeUser(aid,bid);
    }

    public Integer subscribeUser(Subscribe subscribe) {
        return subscribesMapper.subscribeUser(subscribe);
    }

    public Subscribe getNewUSubscribeById(Long aid) {
        return subscribesMapper.getNewUSubscribeById(aid);
    }

    public List<User> searchUsersByKeywords(String keywords) {
        return userMapper.searchUsersByKeywords(keywords);
    }

    public Integer cancelSubscribeTopic(Long uid, Long tid) {
        return userMapper.cancelSubscribeTopic( uid,  tid);
    }

    public Integer subscribeTopic(Long uid, Long tid) {
        return userMapper.subscribeTopic( uid,  tid);
    }

    public Integer registerUser(User registerUser) {
        return userMapper.registerUser(registerUser);
    }

    public Integer signInForum(Long uid, String forumname) {
        return userMapper.signInForum(uid,forumname);
    }
}
