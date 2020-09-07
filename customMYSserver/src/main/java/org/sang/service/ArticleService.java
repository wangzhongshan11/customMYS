package org.sang.service;

import org.sang.bean.*;
import org.sang.mapper.ArticleMapper;
import org.sang.mapper.ArticleTopicsMapper;
import org.sang.mapper.ForumsMapper;
import org.sang.mapper.TagsMapper;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * Created by wzs on 2020/08/01.
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagsMapper tagsMapper;
    @Autowired
    ArticleTopicsMapper articleTopicsMapper;
    @Autowired
    ForumsMapper forumsMapper;

    public int addNewArticle(Article article) {
        //处理文章摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            //直接截取
            String stripHtml = stripHtml(article.getHtmlContent());
            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
        }
        //添加操作
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (article.getState() == 1) {
            //设置发表日期
            article.setPublishDate(timestamp);
        }
        article.setEditTime(timestamp);
        int i = articleMapper.addNewArticle(article);

        List<ArticleTopic> topicList = article.getArticleTopics();
        Integer result1 = articleTopicsMapper.saveArticleTopics(topicList, Math.toIntExact(article.getId()));
        return i;
    }

    public int addNewArticle1(Article article) {
        //处理文章摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            //直接截取
            String stripHtml = stripHtml(article.getHtmlContent());
            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
        }
        if (article.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            article.setEditTime(timestamp);
            //设置当前用户
            article.setUid(Util.getCurrentUser().getId());
            int i = articleMapper.addNewArticle(article);
            //打标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            //更新
            article.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = articleMapper.updateArticle(article);
            //修改标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        }
    }

    private int addTagsToArticle(String[] dynamicTags, Long aid) {
        //1.删除该文章目前所有的标签
        tagsMapper.deleteTagsByAid(aid);
        //2.将上传上来的标签全部存入数据库
        tagsMapper.saveTags(dynamicTags);
        //3.查询这些标签的id
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签
        int i = tagsMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }

    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    public List<Article> getArticleByState(String forumname) {
//        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
        List<Article> articleList = articleMapper.getArticleByState(forumname, uid);
        for (Article article : articleList) {
            List<ArticleTopic> articleTopicList = articleTopicsMapper.getArticleTopicsByAid(article.getId());
            article.setArticleTopics(articleTopicList);
        }
        return articleList;
    }

//    public List<Article> getArticleByStateByAdmin(Integer page, Integer count,String keywords) {
//        int start = (page - 1) * count;
//        return articleMapper.getArticleByStateByAdmin(start, count,keywords);
//    }

    public int getArticleCountByState(Integer state, Long uid, String keywords) {
        return articleMapper.getArticleCountByState(state, uid, keywords);
    }

    public int updateArticleState(Long[] aids, Integer state) {
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);//放入到回收站中
        }
    }

    public int restoreArticle(Integer articleId) {
        return articleMapper.updateArticleStateById(articleId, 1); // 从回收站还原在原处
    }

    public Article getArticleById(Long aid) {
        Article article = articleMapper.getArticleById(aid);
        List<ArticleTopic> articleTopics = articleTopicsMapper.getArticleTopicsByAid(aid);
        List<Comment> commentList = articleMapper.getArticleComments(aid);
        for (Comment comment : commentList) {
            List<Comment> subCommentList = articleMapper.getSubComments(aid, comment.getId());
            List<CSubscribe> cSubscribeList = articleMapper.getCSubscribesByCid(comment.getId());
            comment.setSubCommentList(subCommentList);
            comment.setSubCommentListCount((long) subCommentList.size());
            comment.setcSubscribeList(cSubscribeList);
            comment.setcSubscribeListCount((long) cSubscribeList.size());
        }
        List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(aid);
        List<AFavorite> aFavoriteList = articleMapper.getArticleFavorites(aid);
        article.setArticleTopics(articleTopics);
        article.setCommentList(commentList);
        article.setaCommentCount((long) commentList.size());
        article.setaSubscribeList(aSubscribeList);
        article.setaSubscribeCount((long) aSubscribeList.size());
        article.setaFavoriteList(aFavoriteList);
        article.setaFavoriateCount((long) aFavoriteList.size());
//        articleMapper.pvIncrement(aid);
        return article;
    }

    public List<Comment> getArticleCommentsDescById(Long aid) {
        List<Comment> commentList = articleMapper.getArticleCommentsDesc(aid);
        for (Comment comment : commentList) {
            List<Comment> subCommentList = articleMapper.getSubComments(aid, comment.getId());
            List<CSubscribe> cSubscribeList = articleMapper.getCSubscribesByCid(comment.getId());
            comment.setSubCommentList(subCommentList);
            comment.setSubCommentListCount((long) subCommentList.size());
            comment.setcSubscribeList(cSubscribeList);
            comment.setcSubscribeListCount((long) cSubscribeList.size());
        }
        return commentList;
    }

    public List<Comment> getArticleCommentsAscById(Long aid) {
        List<Comment> commentList = articleMapper.getArticleCommentsAsc(aid);
        for (Comment comment : commentList) {
            List<Comment> subCommentList = articleMapper.getSubComments(aid, comment.getId());
            List<CSubscribe> cSubscribeList = articleMapper.getCSubscribesByCid(comment.getId());
            comment.setSubCommentList(subCommentList);
            comment.setSubCommentListCount((long) subCommentList.size());
            comment.setcSubscribeList(cSubscribeList);
            comment.setcSubscribeListCount((long) cSubscribeList.size());
        }
        return commentList;
    }

    public List<Plate> getPlatesAll() {
        List<Plate> plateList = articleMapper.getPlatesAll();
        for (Plate plate : plateList) {
            List<Topic> topicList = articleMapper.getPlateTopicsAll(plate.getId());
            plate.setTopicList(topicList);
        }
        return plateList;
    }

    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

    /**
     * 获取最近七天的日期
     *
     * @return
     */
    public List<String> getCategories() {
        return articleMapper.getCategories(Util.getCurrentUser().getId());
    }

    /**
     * 获取最近七天的数据
     *
     * @return
     */
    public List<Integer> getDataStatistics() {
        return articleMapper.getDataStatistics(Util.getCurrentUser().getId());
    }

    public List<Forum> getForumsAll() {
        List<Forum> forumList = forumsMapper.getForumsAll();
        for (Forum forum : forumList) {
            List<Plate> plateList = articleMapper.getPlatesByForumId(forum.getId());
            for (Plate plate : plateList) {
                List<Topic> topicList = articleMapper.getPlateTopicsAll(plate.getId());
                plate.setTopicList(topicList);
            }
            forum.setPlateList(plateList);
        }
        return forumList;
    }

    public Integer updateArticleViewCount(Long aid) {
        Long preViewCount = articleMapper.getArticleViewCountById(aid);
        return articleMapper.updateArticleViewCountById(aid, preViewCount + 1);
    }

    public Integer addNewArticleComment(Comment newArticleComment) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        newArticleComment.setPublishDate(timestamp);
        return articleMapper.addNewArticleComment(newArticleComment);
    }

    public Comment getNewArticleCommentByUid(Long uid) {
        return articleMapper.getNewArticleCommentByUid(uid);
    }

    public Integer cancelSubscribeArticle(Long aid, Long uid) {
        return articleMapper.cancelSubscribeArticle(aid, uid);
    }

    public Integer subscribeArticle(ASubscribe aSubscribe) {
        return articleMapper.subscribeArticle(aSubscribe);
    }

    public ASubscribe getNewASubscribeById(Long uid) {
        return articleMapper.getNewASubscribeById(uid);
    }

    public Integer cancelSubscribeComment(Long aid, Long cid, Long uid) {
        return articleMapper.cancelSubscribeComment(aid, cid, uid);
    }

    public Integer subscribeComment(CSubscribe cSubscribe) {
        return articleMapper.subscribeComment(cSubscribe);
    }

    public CSubscribe getNewCSubscribeById(Long uid) {
        return articleMapper.getNewCSubscribeById(uid);
    }

    public Integer cancelFavoriteArticle(Long aid, Long uid) {
        return articleMapper.cancelFavoriteArticle(aid, uid);
    }

    public Integer favoriteArticle(AFavorite aFavorite) {
        return articleMapper.favoriteArticle(aFavorite);
    }

    public AFavorite getNewAFavoriteById(Long uid) {
        return articleMapper.getNewAFavoriteById(uid);
    }


    public List<Article> searchArticlesByKeywords(String keywords) {
        List<Article> articleList = articleMapper.searchArticlesByKeywords(keywords);
        for (Article article : articleList) {
            List<Comment> commentList = articleMapper.getArticleComments(article.getId());
            List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(article.getId());
            List<AFavorite> aFavoriteList = articleMapper.getArticleFavorites(article.getId());
            article.setaCommentCount((long) commentList.size());
            article.setaSubscribeList(aSubscribeList);
            article.setaSubscribeCount((long) aSubscribeList.size());
            article.setaFavoriateCount((long) aFavoriteList.size());
        }
        return articleMapper.searchArticlesByKeywords(keywords);
    }

    public List<Topic> searchTopicsByKeywords(String keywords) {
        return articleTopicsMapper.searchTopicsByKeywords(keywords);
    }

    public List<Article> getArticleByForumname(String forumname) {
        List<Article> articleList = articleMapper.getArticleByForumname(forumname);
        for (Article article : articleList) {
            List<Comment> commentList = articleMapper.getArticleComments(article.getId());
            List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(article.getId());
            List<AFavorite> aFavoriteList = articleMapper.getArticleFavorites(article.getId());
            article.setaCommentCount((long) commentList.size());
            article.setaSubscribeList(aSubscribeList);
            article.setaSubscribeCount((long) aSubscribeList.size());
            article.setaFavoriateCount((long) aFavoriteList.size());
        }
        return articleList;
    }

    public Article getNewArticleByUid(Long uid) {
        return articleMapper.getNewArticleByUid(uid);
    }

    public Topic getTopicById(Long id) {
        Topic topic = articleTopicsMapper.getTopicById(id);
        List<Article> topicArticleList = articleMapper.getTopicArticlesByTid(id);
        for (Article article : topicArticleList) {
            List<Comment> commentList = articleMapper.getArticleComments(article.getId());
            List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(article.getId());
            List<AFavorite> aFavoriteList = articleMapper.getArticleFavorites(article.getId());
            article.setaCommentCount((long) commentList.size());
            article.setaSubscribeList(aSubscribeList);
            article.setaSubscribeCount((long) aSubscribeList.size());
            article.setaFavoriateCount((long) aFavoriteList.size());
        }
        topic.setTopicArticleList(topicArticleList);
        return topic;
    }

    public List<Article> getHotArticles() {
        return articleMapper.getHotArticles();
    }

    public List<Article> getArticlesOfSubUserByUid(Long id) {
        List<Article> articleList = articleMapper.getArticlesOfSubUserByUid(id);
        for (Article article : articleList) {
            List<Comment> commentList = articleMapper.getArticleComments(article.getId());
            List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(article.getId());
            List<AFavorite> aFavoriteList = articleMapper.getArticleFavorites(article.getId());
            article.setaCommentCount((long) commentList.size());
            article.setaSubscribeCount((long) aSubscribeList.size());
            article.setaSubscribeList(aSubscribeList);
            article.setaFavoriateCount((long) aFavoriteList.size());
        }
        return articleList;
    }

    public List<Article> getArticleByPlatename(String platename) {
        List<Article> articleList = articleMapper.getArticleByPlatename(platename);
        for (Article article : articleList) {
            List<Comment> commentList = articleMapper.getArticleComments(article.getId());
            List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(article.getId());
            List<AFavorite> aFavoriteList = articleMapper.getArticleFavorites(article.getId());
            article.setaCommentCount((long) commentList.size());
            article.setaSubscribeList(aSubscribeList);
            article.setaSubscribeCount((long) aSubscribeList.size());
            article.setaFavoriateCount((long) aFavoriteList.size());
        }
        return articleList;
    }

    public List<Article> getArticleByTopicname(String topicname) {
        List<Article> articleList = articleMapper.getArticleByTopicname(topicname);
        for (Article article : articleList) {
            List<Comment> commentList = articleMapper.getArticleComments(article.getId());
            List<ASubscribe> aSubscribeList = articleMapper.getArticleSubscribes(article.getId());
            List<AFavorite> aFavoriteList = articleMapper.getArticleFavorites(article.getId());
            article.setaCommentCount((long) commentList.size());
            article.setaSubscribeList(aSubscribeList);
            article.setaSubscribeCount((long) aSubscribeList.size());
            article.setaFavoriateCount((long) aFavoriteList.size());
        }
        return articleList;
    }

    public List<Topic> getTopicsByPlatename(String platename) {
        return articleMapper.getTopicsByPlatename(platename);
    }
}
