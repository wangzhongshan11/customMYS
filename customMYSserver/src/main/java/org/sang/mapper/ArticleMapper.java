package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.*;

import java.util.List;


/**
 * Created by wzs on 2020/08/01.
 */
@Mapper
public interface ArticleMapper {
    int addNewArticle(Article article);

    int updateArticle(Article article);

    List<Article> getArticleByState(String forumname, @Param("uid") Long uid);

//    List<Article> getArticleByStateByAdmin(@Param("start") int start, @Param("count") Integer count, @Param("keywords") String keywords);

    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);

    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);

    int updateArticleStateById(@Param("articleId") Integer articleId, @Param("state") Integer state);

    int deleteArticleById(@Param("aids") Long[] aids);

    Article getArticleById(Long aid);

    void pvIncrement(Long aid);

    //INSERT INTO pv(countDate,pv,uid) SELECT NOW(),SUM(pageView),uid FROM article GROUP BY uid
    void pvStatisticsPerDay();

    List<String> getCategories(Long uid);

    List<Integer> getDataStatistics(Long uid);

    List<Topic> getPlateTopicsAll(Long pid);

    List<Plate> getPlatesAll();

    List<Plate> getPlatesByForumId(Long fid);

    List<Comment> getArticleComments(Long aid);

    List<ASubscribe> getArticleSubscribes(Long aid);

    List<AFavorite> getArticleFavorites(Long aid);

    Integer updateArticleViewCountById(Long aid, Long preViewCount);

    Long getArticleViewCountById(Long aid);

    Integer addNewArticleComment(Comment newArticleComment);

    Comment getNewArticleCommentByUid(Long uid);

    List<Comment> getSubComments(Long aid, Long parentId);

    List<Comment> getArticleCommentsDesc(Long aid);

    Integer cancelSubscribeArticle(Long aid,Long uid);

    Integer subscribeArticle(ASubscribe aSubscribe);

    ASubscribe getNewASubscribeById(Long id);

    Integer cancelFavoriteArticle(Long aid, Long uid);

    Integer favoriteArticle(AFavorite aFavorite);

    AFavorite getNewAFavoriteById(Long uid);

    List<CSubscribe> getCSubscribesByCid(Long id);

    Integer cancelSubscribeComment(Long aid, Long cid, Long uid);

    Integer subscribeComment(CSubscribe cSubscribe);

    CSubscribe getNewCSubscribeById(Long uid);

    List<Comment> getArticleCommentsAsc(Long aid);

    List<Article> searchArticlesByKeywords(@Param(value = "keywords") String keywords);

    List<Article> getArticleByForumname(String forumname);

    Article getNewArticleByUid(Long uid);

    List<Article> getSubscribeArticlesByUid(Long id);

    List<Comment> getCommentsByUid(Long id);

    List<Article> getArticlesByUid(Long id);

    List<Article> getTopicArticlesByTid(Long id);

    List<Article> getHotArticles();

    List<Article> getArticlesOfSubUserByUid(Long id);

    List<Article> getArticleByPlatename(String platename);

    List<Article> getArticleByTopicname(String topicname);

    List<Topic> getTopicsByPlatename(String platename);
}
