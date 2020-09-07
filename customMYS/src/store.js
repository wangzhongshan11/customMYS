import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const state = {
    currentUser: JSON.parse(sessionStorage.getItem('currentUser')) || '',
    interfaceUser: JSON.parse(sessionStorage.getItem('interfaceUser')) || '',
    interfaceTopic: JSON.parse(sessionStorage.getItem('interfaceTopic')) || '',
    subscribePrePath: JSON.parse(sessionStorage.getItem('subscribePrePath')) || '',
    forumGradeExp:JSON.parse(sessionStorage.getItem('forumGradeExp')) || '',
    currentArticle:JSON.parse(sessionStorage.getItem('currentArticle')) || '',
    dabieyeArticles:JSON.parse(sessionStorage.getItem('dabieyeArticles')) || '',
    BBBArticles:JSON.parse(sessionStorage.getItem('BBBArticles')) || '',
    BBArticles:JSON.parse(sessionStorage.getItem('BBArticles')) || '',
    yuanShenArticles:JSON.parse(sessionStorage.getItem('yuanShenArticles')) || '',
    weiDingArticles:JSON.parse(sessionStorage.getItem('weiDingArticles')) || '',
    plates: JSON.parse(sessionStorage.getItem('plates')) || '',
    forums: JSON.parse(sessionStorage.getItem('forums')) || '',
    ascCommentIndex: JSON.parse(sessionStorage.getItem('ascCommentIndex')) || '',
    searchResult: JSON.parse(sessionStorage.getItem('searchResult')) || '',
    historySearch:JSON.parse(sessionStorage.getItem('historySearch')) || [],
    hotSearch:JSON.parse(sessionStorage.getItem('hotSearch')) || ["全部", "3", "崩", "泳池"],
    articlesOfSubUser:JSON.parse(sessionStorage.getItem('articlesOfSubUser')) || '',
}


const mutations = {
    setCurrentUser(state,currentUser){
        sessionStorage.setItem('currentUser',JSON.stringify(currentUser));
        state.currentUser = currentUser;
    },
    setInterfaceUser(state,interfaceUser){
        sessionStorage.setItem('interfaceUser',JSON.stringify(interfaceUser));
        state.interfaceUser = interfaceUser;
    },
    setSubscribePrePath(state,subscribePrePath) {
        sessionStorage.setItem('subscribePrePath',JSON.stringify(subscribePrePath));
        state.subscribePrePath = subscribePrePath;
    },
    setForumGradeExp(state,forumGradeExp) {
        sessionStorage.setItem('forumGradeExp',JSON.stringify(forumGradeExp));
        state.forumGradeExp = forumGradeExp;
    },
    setCurrentArticle(state,currentArticle) {
        sessionStorage.setItem('currentArticle',JSON.stringify(currentArticle));
        state.currentArticle = currentArticle;
    },
    setDabieyeArticles(state,dabieyeArticles) {
        sessionStorage.setItem('dabieyeArticles',JSON.stringify(dabieyeArticles));
        state.dabieyeArticles = dabieyeArticles;
    },
    setBBBArticles(state,BBBArticles) {
        sessionStorage.setItem('BBBArticles',JSON.stringify(BBBArticles));
        state.BBBArticles = BBBArticles;
    },
    setBBArticles(state,BBArticles) {
        sessionStorage.setItem('BBArticles',JSON.stringify(BBArticles));
        state.BBArticles = BBArticles;
    },
    setYuanShenArticles(state,yuanShenArticles) {
        sessionStorage.setItem('yuanShenArticles',JSON.stringify(yuanShenArticles));
        state.yuanShenArticles = yuanShenArticles;
    },
    setWeiDingArticles(state,weiDingArticles) {
        sessionStorage.setItem('weiDingArticles',JSON.stringify(weiDingArticles));
        state.weiDingArticles = weiDingArticles;
    },
    setPlates(state,plates) {
        sessionStorage.setItem('plates',JSON.stringify(plates));
        state.plates = plates;
    },
    setForums(state,forums) {
        sessionStorage.setItem('forums',JSON.stringify(forums));
        state.forums = forums;
    },
    setAscCommentIndex(state,ascCommentIndex) {
        sessionStorage.setItem('ascCommentIndex',JSON.stringify(ascCommentIndex));
        state.ascCommentIndex = ascCommentIndex;
    },
    setSearchResult(state,searchResult) {
        sessionStorage.setItem('searchResult',JSON.stringify(searchResult));
        state.searchResult = searchResult;
    },
    setHistorySearch(state,historySearch) {
        sessionStorage.setItem('historySearch',JSON.stringify(historySearch));
        state.historySearch = historySearch;
    },
    setInterfaceTopic(state,interfaceTopic) {
        sessionStorage.setItem('interfaceTopic',JSON.stringify(interfaceTopic));
        state.interfaceTopic = interfaceTopic;
    },
    setHotSearch(state,hotSearch) {
        sessionStorage.setItem('hotSearch',JSON.stringify(hotSearch));
        state.hotSearch = hotSearch;
    },
    setArticlesOfSubUser(state,articlesOfSubUser) {
        sessionStorage.setItem('articlesOfSubUser',JSON.stringify(articlesOfSubUser));
        state.articlesOfSubUser = articlesOfSubUser;
    }
}

const actions = {
    setCurrentUser({commit,state},currentUser) {
        commit('setCurrentUser',currentUser);
    },
    setInterfaceUser({commit,state},interfaceUser) {
        commit('setInterfaceUser',interfaceUser);
    },
    setSubscribePrePath({commit,state},subscribePrePath) {
        commit('setSubscribePrePath',subscribePrePath);
    },
    setForumGradeExp({commit,state},forumGradeExp) {
        commit('setForumGradeExp',forumGradeExp);
    },
    setCurrentArticle({commit,state},currentArticle) {
        commit('setCurrentArticle',currentArticle);
    },
    setDabieyeArticles({commit,state},dabieyeArticles) {
        commit('setDabieyeArticles',dabieyeArticles);
    },
    setBBBArticles({commit,state},BBBArticles) {
        commit('setBBBArticles',BBBArticles);
    },
    setBBArticles({commit,state},BBArticles) {
        commit('setBBArticles',BBArticles);
    },
    setYuanShenArticles({commit,state},yuanShenArticles) {
        commit('setYuanShenArticles',yuanShenArticles);
    },
    setWeiDingArticles({commit,state},weiDingArticles) {
        commit('setWeiDingArticles',weiDingArticles);
    },
    setPlates({commit,state},plates) {
        commit('setPlates',plates);
    },
    setForums({commit,state},forums) {
        commit('setForums',forums);
    },
    setAscCommentIndex({commit,state},ascCommentIndex) {
        commit('setAscCommentIndex',ascCommentIndex);
    },
    setSearchResult({commit,state},searchResult) {
        commit('setSearchResult',searchResult);
    }
    ,
    setHistorySearch({commit,state},historySearch) {
        commit('setHistorySearch',historySearch);
    } ,
    setInterfaceTopic({commit,state},interfaceTopic) {
        commit('setInterfaceTopic',interfaceTopic);
    },
    setHotSearch({commit,state},hotSearch) {
        commit('setHotSearch',hotSearch);
    },
    setArticlesOfSubUser({commit,state},articlesOfSubUser) {
        commit('setArticlesOfSubUser',articlesOfSubUser);
    }
}

const getters = {}

export default new Vuex.Store({
    state,
    mutations,
    actions,
    getters
})
