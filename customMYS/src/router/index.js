import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Appwzs from '@/components/Appwzs'
import Userwzs from '@/components/User/Userwzs'
import UserDetail from '@/components/User/UserDetail'
import UDPost from '@/components/User/UserDetail/post'
import UDFavorites from '@/components/User/UserDetail/favorites'
import UDComment from '@/components/User/UserDetail/comment'
import UserFans from '@/components/User/Fans'
import UserSubscribe from '@/components/User/Subscribe'
import UserForumClass from '@/components/User/ForumClass'
import Upgrade from '@/components/User/ForumClass/Upgrade'
import UpgradeStrategy from '@/components/User/ForumClass/UpgradeStrategy'
import UserGetMiyoubi from '@/components/User/GetMiyoubi'
import MiyoubiTask from '@/components/User/Miyoubi/MiyoubiTask'
import MiyoubiActivity from '@/components/User/Miyoubi/MiyoubiActivity'
import UserExchange from '@/components/User/ExchangeCenter'
import UserCharacterMana from '@/components/User/CharacterMana'
import BindOther from '@/components/User/BindOther/BindOther'
import UserPassCard from '@/components/User/PassCard'
import UserShipAddress from '@/components/User/PassCard/ShipAddress'
import UserShipAddressChange from '@/components/User/PassCard/ShipAddressChange'
import UserNewPassword from '@/components/User/PassCard/NewPassword'
import UserGameCenter from '@/components/User/GameCenter'
import UserSettings from '@/components/User/Settings'
import UserEditUser from '@/components/User/userDetail/EditUser'
import UserAvatarChange from '@/components/User/userDetail/AvatarChange'
import SubscribeUser from '@/components/User/Subscribe/SubscribeUser'
import SubscribeTopic from '@/components/User/Subscribe/SubscribeTopic'
import HomeWzs from '@/components/Home/HomeWzs'
import HomeSearch from "@/components/Home/HomeSearch"
import SearchResultOverrall from "@/components/Home/SearchResult/ResultOverrall"
import SearchResultPost from "@/components/Home/SearchResult/Post"
import SearchResultTopic from "@/components/Home/SearchResult/Topic"
import SearchResultUser from "@/components/Home/SearchResult/User"
import DiscussRegionDabieye from "@/components/Home/DiscussRegion/DiscussRegionDabieye"
import DiscussRegionBBB from "@/components/Home/DiscussRegion/DiscussRegionBBB"
import DiscussRegionBB from "@/components/Home/DiscussRegion/DiscussRegionBB"
import DiscussRegionYuanShen from "@/components/Home/DiscussRegion/DiscussRegionYuanShen"
import DiscussRegionWeiDing from "@/components/Home/DiscussRegion/DiscussRegionWeiDing"
import DabieyeDaily from "@/components/Home/DiscussRegion/Dabieye/daily"
import DabieyeTongRen from "@/components/Home/DiscussRegion/Dabieye/TongRen"
import DabieyeACG from "@/components/Home/DiscussRegion/Dabieye/ACG"
import BbbDeck from "@/components/Home/DiscussRegion/BBB/Deck"
import BbbStrategy from "@/components/Home/DiscussRegion/BBB/Strategy"
import BbbTongRenTu from "@/components/Home/DiscussRegion/BBB/TongRenTu"
import BbbDoujinshi from "@/components/Home/DiscussRegion/BBB/Doujinshi"
import ArticleDetail from "@/components/Home/Article/ArticleDetail"
import ArticleTotalComment from "@/components/Home/Article/ArticleTotalComment"
import CommentOnlyLZ from "@/components/Home/Article/CommentOnlyLZ"
import UserInterface from "@/components/User/UserInterface/UserInterface"
import AddSomething from "@/components/Add/AddSomething"
import PostArticleWzs from "@/components/Add/PostArticleWzs"
import Register from "@/components/Register"
import News from "@/components/News/News"
import NewsFind from "@/components/News/Find"
import NewsSubscribe from "@/components/News/Subscribe"
import Message from "@/components/Message/Message"
import MessageSetting from "@/components/Message/MessageSetting"
import TopicInterface from "@/components/Home/TopicInterface"
import New from "@/components/Home/TopicInterface/New"
import Hot from "@/components/Home/TopicInterface/Hot"
import Excellent from "@/components/Home/TopicInterface/Excellent"
import UserInterfaceSubscribe from "@/components/User/UserInterface/UserInterfaceSubscribe"
import UserInterfaceFans from "@/components/User/UserInterface/UserInterfaceFans"
import UserInterfacePost from "@/components/User/UserInterface/UserInterfacePost"
import UserInterfaceComment from "@/components/User/UserInterface/UserInterfaceComment"
import UserInterfaceFavorites from "@/components/User/UserInterface/UserInterfaceFavorites"
import UISubscribeUser from "@/components/User/UserInterface/UserInterfaceSubscribe/UserInterfaceSubscribeUser"
import UISubscribeTopic from "@/components/User/UserInterface/UserInterfaceSubscribe/UserInterfaceSubscribeTopic"
import BbSchool from "@/components/Home/DiscussRegion/BB/School"
import BbPictureAera from "@/components/Home/DiscussRegion/BB/PictureAera"
import YuanShenTavern from "@/components/Home/DiscussRegion/YuanShen/Tavern"
import YuanShenPictureAera from "@/components/Home/DiscussRegion/YuanShen/PictureAera"
import WeiDingLawForum from "@/components/Home/DiscussRegion/WeiDing/LawForum"
import WeiDingTongrentu from "@/components/Home/DiscussRegion/WeiDing/Tongrentu"
import WeiDingDoushijin from "@/components/Home/DiscussRegion/WeiDing/Doushijin"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Login
    },  {
      path: '/appwzs',
      component: Appwzs,
      name: 'Appwzs',
      iconCls: 'fa fa-hand-o-right',
      hidden: true,
      children: [
        {
          path: "/homeWzs",
          component: HomeWzs,
          name: 'HomeWzs',
          hidden: true
        },
        {
          path: "/addSomething",
          component: AddSomething,
          name: 'AddSomething',
          hidden: true,
        }, {
          path: "/userwzs",
          name: "Userwzs",
          hidden: true,
          component: Userwzs
        }, {
          path: "/news",
          component: News,
          name: 'News',
          hidden: true,
          children: [
            {
              path: "/newsSubscribe",
              component: NewsSubscribe,
              name: "关注",
              hidden: true
            },
            {
              path: "/newsFind",
              component: NewsFind,
              name: "发现",
              hidden: true
            }
          ]
        }, {
          path: "/message",
          component: Message,
          name: 'Message',
          hidden: true,
        }
      ]
    }, {
      path: '/userDetail',
      component: UserDetail,
      name: 'UserDetail',
      hidden: true,
      children: [
        {
          path: "/udPost",
          component: UDPost,
          name: "发帖",
          hidden: true
        },
        {
          path: "/udComment",
          component: UDComment,
          name: "评论",
          hidden: true
        },
        {
          path: "/udFavorites",
          component: UDFavorites,
          name: "收藏",
          hidden: true
        }
      ]
    }, {
      path: '/userFans',
      component: UserFans,
      name: 'UserFans',
      hidden: true
    }, {
      path: "/userSubscribe",
      component: UserSubscribe,
      name: 'UserSubscribe',
      hidden: true,
      children: [
        {
          path: "/subscribeUser",
          component: SubscribeUser,
          name: "用户",
          hidden: true
        },
        {
          path: "/subscribeTopic",
          component: SubscribeTopic,
          name: "话题",
          hidden: true
        }
      ]
    }, {
      path: "/userForumClass",
      component: UserForumClass,
      name: 'UserForumClass',
      hidden: true,
      children: [
        {
          path: "/upgradeStrategy",
          component: UpgradeStrategy,
          name: "升级攻略",
          hidden: true
        }, {
          path: "/upgrade",
          component: Upgrade,
          name: "等级成长",
          hidden: true
        }
      ]
    }, {
      path: "/userGetMiyoubi",
      component: UserGetMiyoubi,
      name: 'UserGetMiyoubi',
      hidden: true,
      children: [
        {
          path: "/miyoubiTask",
          component: MiyoubiTask,
          name: "米游币任务",
          hidden: true
        }, {
          path: "/miyoubiActivity",
          component: MiyoubiActivity,
          name: "米游币活动",
          hidden: true
        }
      ]
    }, {
      path: "/userExchange",
      component: UserExchange,
      name: 'UserExchange',
      hidden: true
    }, {
      path: "/userCharacterMana",
      component: UserCharacterMana,
      name: 'UserCharacterMana',
      hidden: true,
      children: [
        {
          path: "/bindOther",
          component: BindOther,
          name: 'BindOther',
          hidden: true
        }
      ]
    }, {
      path: "/userPassCard",
      component: UserPassCard,
      name: 'UserPassCard',
      hidden: true
    }, {
      path: "/userShipAddress",
      component: UserShipAddress,
      name: 'UserShipAddress',
      hidden: true
    }, {
      path: "/userShipAddressChange",
      component: UserShipAddressChange,
      name: 'UserShipAddressChange',
      hidden: true
    }, {
      path: "/userNewPassword",
      component: UserNewPassword,
      name: 'UserNewPassword',
      hidden: true
    }, {
      path: "/userGameCenter",
      component: UserGameCenter,
      name: 'UserGameCenter',
      hidden: true
    }, {
      path: "/userSettings",
      component: UserSettings,
      name: 'UserSettings',
      hidden: true
    }, {
      path: "/userEditUser",
      component: UserEditUser,
      name: 'UserEditUser',
      hidden: true,
      children: [
        {
          path: "/userAvatarChange",
          component: UserAvatarChange,
          name: 'UserAvatarChange',
          hidden: true
        }
      ]
    }, {
      path: "/homeSearch",
      component: HomeSearch,
      name: 'HomeSearch',
      hidden: true,
      children: [
        {
          path: "/searchResultOverrall",
          component: SearchResultOverrall,
          name: '综合',
          hidden: true
        },
        {
          path: "/searchResultPost",
          component: SearchResultPost,
          name: '帖子',
          hidden: true
        },
        {
          path: "/searchResultTopic",
          component: SearchResultTopic,
          name: '话题',
          hidden: true
        },
        {
          path: "/searchResultUser",
          component: SearchResultUser,
          name: '用户',
          hidden: true
        }
      ]
    }, {
      path: "/discussRegionDabieye",
      component: DiscussRegionDabieye,
      name: 'DiscussRegionDabieye',
      hidden: true,
      children: [
        {
          path: "/dabieyeDaily",
          component: DabieyeDaily,
          name: '日常',
          hidden: true
        }, {
          path: "/dabieyeTongRen",
          component: DabieyeTongRen,
          name: '同人',
          hidden: true
        }, {
          path: "/dabieyeACG",
          component: DabieyeACG,
          name: 'ACG',
          hidden: true
        }
      ]
    }, {
      path: "/discussRegionBBB",
      component: DiscussRegionBBB,
      name: 'DiscussRegionBBB',
      hidden: true,
      children: [
        {
          path: "/bbbDeck",
          component: BbbDeck,
          name: '甲板',
          hidden: true
        }, {
          path: "/bbbStrategy",
          component: BbbStrategy,
          name: '攻略',
          hidden: true
        }, {
          path: "/bbbTongRenTu",
          component: BbbTongRenTu,
          name: '同人图',
          hidden: true
        }, {
          path: "/bbbDoujinshi",
          component: BbbDoujinshi,
          name: '同人文',
          hidden: true
        }
      ]
    }, {
      path: "/discussRegionBB",
      component: DiscussRegionBB,
      name: 'DiscussRegionBB',
      hidden: true,
      children: [
        {
          path: "/bbSchool",
          component: BbSchool,
          name: '学园',
          hidden: true
        }, {
          path: "/bbPictureAera",
          component: BbPictureAera,
          name: '图区',
          hidden: true
        }
      ]
    }, {
      path: "/discussRegionYuanShen",
      component: DiscussRegionYuanShen,
      name: 'DiscussRegionYuanShen',
      hidden: true,
      children: [
        {
          path: "/yuanShenTavern",
          component: YuanShenTavern,
          name: '酒馆',
          hidden: true
        }, {
          path: "/yuanShenPictureAera",
          component: YuanShenPictureAera,
          name: '图区',
          hidden: true
        }
      ]
    }, {
      path: "/discussRegionWeiDing",
      component: DiscussRegionWeiDing,
      name: 'DiscussRegionWeiDing',
      hidden: true,
      children: [
        {
          path: "/weiDingLawForum",
          component: WeiDingLawForum,
          name: '律所',
          hidden: true
        }, {
          path: "/weiDingTongrentu",
          component: WeiDingTongrentu,
          name: '同人图',
          hidden: true
        }, {
          path: "/weiDingDoushijin",
          component: WeiDingDoushijin,
          name: '同人文',
          hidden: true
        }
      ]
    }, {
      path: "/searchResultOverrall",
      component: SearchResultOverrall,
      name: '综合',
      hidden: true
    }, {
      path: "/articleDetail",
      component: ArticleDetail,
      name: 'ArticleDetail',
      hidden: true,
      children: [
        {
          path: "/articleTotalComment",
          component: ArticleTotalComment,
          name: '全部评论',
          hidden: true
        }, {
          path: "/commentOnlyLZ",
          component: CommentOnlyLZ,
          name: '只看楼主',
          hidden: true
        }
      ]
    }, {
      path: "/userInterface/:id",
      component: UserInterface,
      name: 'UserInterface',
      hidden: true,
      children: [
        {
          path: "/uiPost",
          component: UserInterfacePost,
          name: "发帖",
          hidden: true
        },
        {
          path: "/uiComment",
          component: UserInterfaceComment,
          name: "评论",
          hidden: true
        },
        {
          path: "/uiFavorites",
          component: UserInterfaceFavorites,
          name: "收藏",
          hidden: true
        }
      ]
    }, {
      path: "/postArticleWzs",
      component: PostArticleWzs,
      name: 'PostArticleWzs',
      hidden: true,
    }, {
      path: "/register",
      component: Register,
      name: 'Register',
      hidden: true,
    }, {
      path: "/messageSetting",
      component: MessageSetting,
      name: "MessageSetting",
      hidden: true
    }, {
      path: "/topicInterface",
      component: TopicInterface,
      name: "TopicInterface",
      hidden: true,
      children:[
        {
          path: "/new",
          component: New,
          name: "最新",
          hidden: true
        },{
          path: "/hot",
          component: Hot,
          name: "热门",
          hidden: true
        },{
          path: "/excellent",
          component: Excellent,
          name: "精选",
          hidden: true
        }
      ]
    }, {
      path: "/userInterfaceSubscribe",
      component: UserInterfaceSubscribe,
      name: 'UserInterfaceSubscribe',
      hidden: true,
      children: [
        {
          path: "/uiSubscribeUser",
          component: UISubscribeUser,
          name: "用户",
          hidden: true
        },
        {
          path: "/uiSubscribeTopic",
          component: UISubscribeTopic,
          name: "话题",
          hidden: true
        }
      ]
    }, {
      path: "/userInterfaceFans",
      component: UserInterfaceFans,
      name: 'UserInterfaceFans',
      hidden: true,}
  ]
})
