package com.gamecenter.gamecenter.Defines;

public class Defines {
    public static final String  PLATFORM_TITLE	="智戎·未来指挥官";
    public static final String  VERSION="1.0002";

    public static final String  DATE_FORMAT = "yyyy-MM-dd";
    public static final String  TIME_FORMAT = "hh:mm:ss";
    public static final String  DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static final String  PHONE_NUM_REG_EXP ="^[1][123456789][0-9]{9}$";
    public static final String  EMAIL_REG_EXP =	"^[A-Za-z0-9_\\.\\-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";

    public static final String  CLIENT_REQUEST_TYPE_STR = "RequestType";

    // 客户端给服务端
    public static final int REQUEST_TYPE_LOGIN = 1001;										  // 用户登录
    public static final int REQUEST_TYPE_REGIST = 1002;											// 用户注册
    public static final int REQUEST_TYPE_LOAD_SCENARIO_LIST = 1005;								// 更新想定列表
    public static final int REQUEST_TYPE_UPDATE_FRIEND_LIST = 1027;					      // 更新好友列表
    public static final int REQUEST_TYPE_SEND_PRIVATE_MESSAGE = 1029;					  // 发送私聊消息
    public static final int REQUEST_TYPE_UPDATE_GROUP = 1036;								  // 更新群列表
    public static final int REQUEST_TYPE_SCENARIO_INFO = 1039;								// 请求查看想定信息
    public static final int REQUEST_TYPE_SEND_GROUP_MESSAGE = 1051;								// 发送群聊消息
    public static final int REQUEST_TYPE_UPDATE_GROUP_MEMBER = 1054;								// 更新群成员
    public static final int REQUEST_TYPE_FIND_GROUP= 1049;			          //查找群
    /*

    public static final int REQUEST_TYPE_USER_INFO = 1003;											// 用户信息
    public static final int REQUEST_TYPE_LOAD_SERVICE = 1004;										// 更新服务区和房间
    public static final int REQUEST_TYPE_CREAT_ROOM = 1006;										// 创建房间
    public static final int REQUEST_TYPE_LOAD_SCENARIO = 1007;										// 加载想定
    public static final int REQUEST_TYPE_JOIN_ROOM = 1008;											// 加入房间
    public static final int REQUEST_TYPE_LEAVE_ROOM = 1009;										// 离开房间
    public static final int REQUEST_TYPE_ROOM_INFO = 1010;											// 房间信息
    public static final int REQUEST_TYPE_SET_ROOM_PASSWORD = 1011;									// 设置房间密码
    public static final int REQUEST_TYPE_USER_READY = 1012;										// 用户准备
    public static final int REQUEST_TYPE_USER_CANCELREADY = 1013;									// 用户取消准备
    public static final int REQUEST_TYPE_SEND_MESSAGE = 1014;										// 发送消息
    public static final int REQUEST_TYPE_CHOOSE_SIDE = 1015;										// 选择属方
    public static final int REQUEST_TYPE_VERIFY_PWD = 1016;										// 验证房间密码
    public static final int REQUEST_TYPE_CHANGE_ROOMINFO = 1017;									// 修改房间信息
    public static final int REQUEST_TYPE_NETWORK_TIME = 1018;										// 网络延时
    public static final int REQUEST_TYPE_ADD_AI = 1019;											// 添加AI
    public static final int REQUEST_TYPE_IMPORT_SCENARIO = 1020;									// 导入想定
    public static final int REQUEST_TYPE_EXPORT_SCENARIO = 1021;									// 导出想定
    public static final int REQUEST_TYPE_FIND_FRIEND = 1022;										// 查找好友
    public static final int REQUEST_TYPE_ADD_FRIEND = 1023;										// 添加好友
    public static final int REQUEST_TYPE_ADD_FRIEND_REPLY = 1024;									// 添加好友回复
    public static final int REQUEST_TYPE_UPDATE_BLACKFRIEND_LIST = 1026;							// 更新黑名单列表
    public static final int REQUEST_TYPE_INVITATION_JOIN_ROOM = 1028;								// 邀请好友加入房间
    public static final int REQUEST_TYPE_REQUEST_JOIN_ROOM = 1029;									// 请求加入房间
    public static final int REQUEST_TYPE_START_GAME = 1030;										// 启动游戏
    public static final int REQUEST_TYPE_DELETE_FRIEND = 1031;										// 删除好友
    public static final int REQUEST_TYPE_BLACK_FRIEND = 1032;										// 好友拉入黑名单
    public static final int REQUEST_TYPE_CREATE_GROUP = 1033;										// 创建群
    public static final int REQUEST_TYPE_GLOBAL_CHAT = 1035;										// 全局聊天（游戏大厅）
    public static final int REQUEST_TYPE_GET_USER_INFO = 1036;										// 获取用户信息
    public static final int REQUEST_TYPE_GET_SCENARIO_INFO = 1037;									// 获取想定信息
    public static final int REQUEST_TYPE_GET_GROUP_INFO = 1038;									// 获取群信息
    public static final int REQUEST_TYPE_JOIN_FIGHT = 1039;										// 加入对战
    public static final int REQUEST_TYPE_LOGOUT = 1040;											// 用户注销登录
    public static final int REQUEST_TYPE_INVITE_USER_APPOINT_SIDE = 1041;							// 邀请好友加入房间，指定属方
    public static final int REQUEST_TYPE_UPDATE_HISTORYROOM = 1042;								// 更新历史房间列表
    public static final int REQUEST_TYPE_UPDATE_CUSTOMER_LIST = 1043;								// 获取客服列表
    public static final int REQUEST_TYPE_UPDATE_USER_LOGIN_STATUS = 1044;							// 更新用户登录状态
    public static final int REQUEST_TYPE_RELOGIN = 1045;											// 重新连接
    public static final int REQUEST_TYPE_ALL_USER_INFO = 1046;										// 所有用户信息
    public static final int REQUEST_TYPE_FIND_GROUP = 1047;										// 查找群组
    public static final int REQUEST_TYPE_APPLY_GROUP = 1048;										// 申请加群
    public static final int REQUEST_TYPE_UPDATE_GROUP_INFO = 1050;									// 更新群信息
    public static final int REQUEST_TYPE_OFFLINE_MESSAGE = 1051;									// 请求离线消息
    public static final int REQUEST_TYPE_UPDATE_GROUP_MEMBER = 1052;								// 更新群成员
    public static final int REQUEST_TYPE_REPLAY_ADD_GROUP = 1053;									// 回复加群申请结果
    public static final int REQUEST_TYPE_ALL_GROUP_INFO = 1054;									// 所有群信息
    public static final int REQUEST_TYPE_SEND_NOTIFICATION = 1055;									// 发送通知
    public static final int REQUEST_TYPE_REMOVE_USER_FROM_ROOM = 1056;								// 踢人
    public static final int REQUEST_TYPE_CHECK_VERSION = 1057;										// 校验版本
    public static final int REQUEST_TYPE_REPLAYSHOW = 1058;										// 推演复盘
    public static final int REQUEST_TYPE_FRIEND_HISTORY = 1059;									// 好友战绩
    public static final int REQUEST_TYPE_SEND_CUSTOMERSERVICE_MESSAGE = 1060;						// 发送客服消息
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_SOLVE = 1061;								// 客服已解决
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_RECORD = 1062;							// 客服问题记录入库
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_TRANSFER = 1063;							// 客服转交
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_RECEIVE = 1064;							// 客服接收
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_HISTORYMESSAGE = 1065;					// 获取客服历史消息
    public static final int REQUEST_TYPE_USER_INFO_SET = 1066;										// 设置用户信息
    public static final int REQUEST_TYPE_USER_INFO_DELETE = 1067;									// 删除用户信息
    public static final int REQUEST_TYPE_DISBAND_GROUP = 1068;										// 解散群
    public static final int REQUEST_TYPE_SCENARIO_PRIVATETOPUBLIC = 1069;							// 想定私有转公有
    public static final int REQUEST_TYPE_ALL_SERVICEAREA_STRUCTURE = 1070;							// 全部服务区结构
    public static final int REQUEST_TYPE_CLOSE_SERVERAPP = 1071;									// 关闭服务器程序
    public static final int REQUEST_TYPE_GET_AI_SERVERAREA_CONFIG = 1072;							// 获取AI服务区配置
    public static final int REQUEST_TYPE_SET_AI_SERVERAREA_CONFIG = 1073;							// 设置AI服务区配置（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_SET_AI_SCENARIO_CONFIG = 1074;							// 设置AI想定配置（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_SET_AI_USER_CONFIG = 1075;								// 设置AI用户配置（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_REMOVE_AI_SERVERAREA_CONFIG = 1076;						// 删除AI服务区配置
    public static final int REQUEST_TYPE_REMOVE_AI_SCENARIO_CONFIG = 1077;							// 删除AI想定配置
    public static final int REQUEST_TYPE_REMOVE_AI_USER_CONFIG = 1078;								// 删除AI用户配置
    public static final int REQUEST_TYPE_SET_USER_GROUP = 1079;									// 设置用户分组（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_REMOVE_USER_GROUP = 1080;									// 删除用户分组
    public static final int REQUEST_TYPE_MOVE_FRIEND_TO_USER_GROUP = 1081;							// 将好友移动到分组
    public static final int REQUEST_TYPE_GET_ALL_GAME_LIST = 1082;									// 获取全部比赛列表
    public static final int REQUEST_TYPE_CREATE_GAME = 1083;										// 创建比赛
    public static final int REQUEST_TYPE_CHANGE_GAME_STATUS = 1084;								// 改变游戏状态
    public static final int REQUEST_TYPE_CREATE_SERVERAREA = 1085;									// 创建服务区
    public static final int REQUEST_TYPE_MOVE_SERVER_TO_NEW_SERVERAREA = 1086;						// 移动服务器到新服务区
    public static final int REQUEST_TYPE_REMOVE_SCENARIO = 1087;									// 删除想定
    public static final int REQUEST_TYPE_HISTORY_ROOM_INFO = 1088;									// 请求历史房间信息
    public static final int REQUEST_TYPE_HISTORY_ROOM_PLAYAGAIN = 1089;							// 历史房间再玩儿一把
    public static final int REQUEST_TYPE_AUTO_GENERATE_BATTLE = 1090;								// 自动生成对决
    public static final int REQUEST_TYPE_PUBLIC_SCENARIO_PRIVATE = 1091;							// 公有想定私有化
    public static final int REQUEST_TYPE_SET_GROUP_GROUP = 1092;									// 设置群组分组（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_REMOVE_GROUP_GROUP = 1093;								// 删除群组分组
    public static final int REQUEST_TYPE_MOVE_GROUP_TO_GROUP_GROUP = 1094;							// 将群组移动到分组
    public static final int REQUEST_TYPE_SET_SCENARIO_GROUP = 1095;								// 设置想定分组（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_REMOVE_SCENARIO_GROUP = 1096;								// 删除想定分组
    public static final int REQUEST_TYPE_MOVE_SCENARIO_TO_SCENARIO_GROUP = 1097;					// 将想定移动到分组
    public static final int REQUEST_TYPE_GIVE_SCENARIO_TO_USER = 1098;								// 将想定赠予某人
    public static final int REQUEST_TYPE_SHARE_SCENARIO_TO_USER = 1099;							// 将想定共享某人
    public static final int REQUEST_TYPE_INVITE_USER_JOIN_GROUP = 1100;							// 邀请用户加入群组
    public static final int REQUEST_TYPE_REPLAY_INVITE_JOIN_GROUP = 1101;							// 回复邀请用户加入群组
    public static final int REQUEST_TYPE_SHARE_SCENARIO_LIST = 1102;								// 请求共享想定列表
    public static final int REQUEST_TYPE_CREATE_GAME_ROOM = 1103;									// 创建比赛房间
    public static final int REQUEST_TYPE_PRIVATE_TO_PUBLIC_APPLY = 1104;							// 请求私有想定转公有想定申请列表
    public static final int REQUEST_TYPE_CHANGE_USER_INFO = 1105;									// 修改用户信息
    public static final int REQUEST_TYPE_IMPORT_COMPETITION_USER = 1106;							// 导入比赛用户
    public static final int REQUEST_TYPE_GET_SCENARIO_RANKING = 1107;								// 获取想定排名
    public static final int REQUEST_TYPE_GET_GAME_BATTLE = 1108;									// 获取比赛对战表
    */


    // 服务端给客户端 2001
    public static final int REQUEST_TYPE_LOGIN_RESULT = 2001;								// 用户登录结果
    public static final int REQUEST_TYPE_REGIST_RESULT = 2002;									// 用户注册结果
    public static final int REQUEST_TYPE_LOAD_SCENARIO_LIST_RESULT = 2005;				// 更新想定列表结果
    public static final int REQUEST_TYPE_UPDATE_FRIEND_LIST_RESULT = 2030;				// 更新好友列表结果
    public static final int REQUEST_TYPE_SEND_PRIVATE_MESSAGE_RESULT = 2031;			// 发送私聊消息结果
    public static final int REQUEST_TYPE_UPDATE_GROUP_LIST_RESULT = 2041;				// 更新群列表结果
    public static final int REQUEST_TYPE_SCENARIO_INFO_RESULT= 2044;					// 请求查看想定信息
    public static final int REQUEST_TYPE_SEND_GROUP_MESSAGE_RESULT = 2056;				// 发送群消息
    public static final int REQUEST_TYPE_UPDATE_GROUP_MEMBER_RESULT = 2061;			// 更新群成员结果
    public static final int REQUEST_TYPE_FIND_GROUP__RESULT = 2058;			          //查找群
    /*
    public static final int REQUEST_TYPE_USER_INFO_RESULT = 2003;								// 用户信息结果
    public static final int REQUEST_TYPE_LOAD_SERVICE_RESULT = 2004;							// 更新服务区和房间结果
    public static final int REQUEST_TYPE_CREAT_ROOM_RESULT = 2006;								// 创建房间结果
    public static final int REQUEST_TYPE_LOAD_SCENARIO_RESULT = 2007;							// 加载想定结果
    public static final int REQUEST_TYPE_JOIN_ROOM_RESULT = 2008;								// 加入房间结果
    public static final int REQUEST_TYPE_JOIN_ROOM_MESSAGE = 2009;								// 加入房间消息
    public static final int REQUEST_TYPE_LEAVE_ROOM_RESULT = 2010;								// 离开房间结果
    public static final int REQUEST_TYPE_LEAVE_ROOM_MESSAGE = 2011;							// 离开房间消息
    public static final int REQUEST_TYPE_ROOM_INFO_RESULT = 2012;								// 房间信息结果
    public static final int REQUEST_TYPE_SET_ROOM_PASSWORD_RESULT = 2013;						// 设置房间密码结果
    public static final int REQUEST_TYPE_USER_READY_RESULT = 2014;								// 用户准备结果
    public static final int REQUEST_TYPE_SEND_MESSAGE_RESULT = 2015;							// 发送消息结果
    public static final int REQUEST_TYPE_CHOOSE_SIDE_RESULT = 2016;							// 选择属方
    public static final int REQUEST_TYPE_VERIFY_PWD_RESULT = 2017;								// 验证房间密码结果
    public static final int REQUEST_TYPE_CHANGE_ROOMINFO_RESULT = 2018;						// 修改房间信息结果
    public static final int REQUEST_TYPE_ROOM_USER_ELAPSED_TIME_RESULT = 2019;					// 房间用户延迟结果
    public static final int REQUEST_TYPE_ADD_AI_RESULT = 2020;									// 添加AI结果
    public static final int REQUEST_TYPE_IMPORT_SCENARIO_RESULT = 2021;						// 导入想定结果
    public static final int REQUEST_TYPE_EXPORT_SCENARIO_RESULT = 2022;						// 导出想定结果
    public static final int REQUEST_TYPE_SERVER_AREA_CAPACITY_RESULT = 2023;					// 服务区容量结果
    public static final int REQUEST_TYPE_FIND_FRIEND_RESULT = 2024;							// 查找好友结果
    public static final int REQUEST_TYPE_ADD_FRIEND_RESULT = 2025;								// 添加好友结果
    public static final int REQUEST_TYPE_UPDATE_USER_LEVEL = 2026;								// 更新用户等级
    public static final int REQUEST_TYPE_ADD_FRIEND_REPLY_RESULT = 2027;						// 添加好友回复结果
    public static final int REQUEST_TYPE_INVITATION_JOIN_ROOM_RESULT = 2030;					// 邀请好友加入房间结果
    public static final int REQUEST_TYPE_USER_IS_ONLINE = 2031;								// 用户是否在线
    public static final int REQUEST_TYPE_START_GAME_RESULT = 2032;								// 启动游戏结果
    public static final int REQUEST_TYPE_REQUEST_JOIN_ROOM_RESULT = 2033;						// 请求加入房间结果
    public static final int REQUEST_TYPE_REQUEST_JOIN_ROOM_REPLY = 2034;						// 请求加入房间回复
    public static final int REQUEST_TYPE_DELETE_FRIEND_RESULT = 2035;							// 删除好友结果
    public static final int REQUEST_TYPE_NOTIFICATION_MESSAGE = 2036;							// 通知消息
    public static final int REQUEST_TYPE_UPDATE_ROOM_INFO = 2037;								// 更新房间信息
    public static final int REQUEST_TYPE_CREATE_GROUP_RESULT = 2038;							// 创建群结果
    public static final int REQUEST_TYPE_GLOBAL_CHAT_RESULT = 2040;							// 全局聊天（游戏大厅）结果
    public static final int REQUEST_TYPE_GET_USER_INFO_RESULT = 2041;							// 获取用户信息结果
    public static final int REQUEST_TYPE_GET_SCENARIO_INFO_RESULT = 2042;						// 获取想定信息结果
    public static final int REQUEST_TYPE_GET_GROUP_INFO_RESULT = 2043;							// 获取群信息结果
    public static final int REQUEST_TYPE_REQUEST_JOIN_ROOM_REPLY_RES = 2044;					// 加入房间回复结果
    public static final int REQUEST_TYPE_LOGOUT_RESULT = 2045;									// 用户注销登录结果
    public static final int REQUEST_TYPE_UPDATE_BLACKFRIEND_LIST_RESULT = 2046;				// 更新黑名单列表结果
    public static final int REQUEST_TYPE_INVITE_USER_APPOINT_SIDE_RESULT = 2047;				// 邀请好友加入房间，指定属方
    public static final int REQUEST_TYPE_DELETE_BLACKFRIEND_RESULT = 2048;						// 加黑名单好友结果
    public static final int REQUEST_TYPE_UPDATE_CUSTOMER_LIST_RESULT = 2049;					// 获取客服列表结果
    public static final int REQUEST_TYPE_ROOMLEADER_LEAVE_ROOM_RESULT = 2050;					// 房主退房人员全退
    public static final int REQUEST_TYPE_UPDATE_USER_LOGIN_STATUS_RESULT = 2051;				// 更新用户登录状态结果
    public static final int REQUEST_TYPE_CHANGE_GAMECENTER_ROOMINFO_RESULT = 2052;				// 更新大厅房间信息
    public static final int REQUEST_TYPE_ALL_USER_INFO_RESULT = 2053;							// 所有用户信息结果
    public static final int REQUEST_TYPE_UPDATE_GROUP_INFO_RESULT = 2055;						// 更新群信息
    public static final int REQUEST_TYPE_FIND_GROUP_RESULT = 2056;								// 查找群组结果
    public static final int REQUEST_TYPE_APPLY_GROUP_RESULT = 2057;							// 申请加群结果
    public static final int REQUEST_TYPE_APPLY_GROUP_MESSAGE = 2058;							// 处理申请加群的通知
    public static final int REQUEST_TYPE_UPDATE_GROUP_MEMBER_RESULT = 2059;					// 更新群成员结果
    public static final int REQUEST_TYPE_REPLAY_ADD_GROUP_RESULT = 2060;						// 回复加群结果
    public static final int REQUEST_TYPE_ALL_GROUP_INFO_RESULT = 2061;							// 所有群信息结果
    public static final int REQUEST_TYPE_REMOVE_USER_FROM_ROOM_RESULT = 2062;					// 踢人结果
    public static final int REQUEST_TYPE_REMOVE_USER_FROM_ROOM_INFORMATION = 2063;				// 踢人结果
    public static final int REQUEST_TYPE_UPDATE_HISTORYROOM_RESULT = 2064;						// 更新历史房间列表结果
    public static final int REQUEST_TYPE_CHECK_VERSION_RESULT = 2065;							// 校验版本结果
    public static final int REQUEST_TYPE_FRIEND_HISTORY_RESULT = 2066;							// 好友战绩结果
    public static final int REQUEST_TYPE_REPLAYSHOW_RESULT = 2067;								// 推演复盘结果
    public static final int REQUEST_TYPE_UPDATE_ROOM_USER_RESULT = 2068;						// 更新房间用户信息结果
    public static final int REQUEST_TYPE_SEND_CUSTOMERSERVICE_MESSAGE_RESULT = 2069;			// 发送客服消息结果
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_END = 2070;							// 客户服务结束
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_RECORD_RESULT = 2071;					// 客户记录入库结果
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_TRANSFER_RESULT = 2072;				// 客服转交结果
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_RECEIVE_RESULT = 2073;				// 客服接收结果
    public static final int REQUEST_TYPE_CUSTOMERSERVICE_HISTORYMESSAGE_RESULT = 2074;			// 获取客服历史消息结果
    public static final int REQUEST_TYPE_USER_INFO_SET_RESULT = 2075;							// 设置用户信息结果
    public static final int REQUEST_TYPE_USER_INFO_DELETE_RESULT = 2076;						// 删除用户信息结果
    public static final int REQUEST_TYPE_DISBAND_GROUP_RESULT = 2077;							// 解散群结果
    public static final int REQUEST_TYPE_SCENARIO_PRIVATETOPUBLIC_RESULT = 2078;				// 想定私有转公有结果
    public static final int REQUEST_TYPE_ALL_SERVICEAREA_STRUCTURE_RESULTR = 2079;				// 全部服务区结构结果
    public static final int REQUEST_TYPE_GET_AI_SERVERAREA_CONFIG_RESULTR = 2080;				// 获取AI服务区配置结果
    public static final int REQUEST_TYPE_SET_AI_SERVERAREA_CONFIG_RESULTR = 2081;				// 设置AI服务区配置结果
    public static final int REQUEST_TYPE_SET_AI_SCENARIO_CONFIG_RESULTR = 2082;				// 设置AI想定配置结果
    public static final int REQUEST_TYPE_SET_AI_USER_CONFIG_RESULTR = 2083;					// 设置AI用户配置结果
    public static final int REQUEST_TYPE_REMOVE_AI_SERVERAREA_CONFIG_RESULTR = 2084; 				// 删除AI服务区配置结果
    public static final int REQUEST_TYPE_REMOVE_AI_SCENARIO_CONFIG_RESULTR = 2085; 				// 删除AI想定配置结果
    public static final int REQUEST_TYPE_REMOVE_AI_USER_CONFIG_RESULTR = 2086; 					// 删除AI用户配置结果
    public static final int REQUEST_TYPE_SET_USER_GROUP_RESULTR = 2087; 							// 设置用户编组（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_REMOVE_USER_GROUP_RESULTR = 2088; 						// 删除用户编组
    public static final int REQUEST_TYPE_MOVE_FRIEND_TO_USER_GROUP_RESULTR = 2089; 				// 将好友移动到分组结果
    public static final int REQUEST_TYPE_GET_ALL_GAME_LIST_RESULTR = 2090; 						// 获取全部比赛列表结果
    public static final int REQUEST_TYPE_CREATE_GAME_RESULTR = 2091;							// 创建比赛结果
    public static final int REQUEST_TYPE_CHANGE_GAME_STATUS_RESULTR = 2092; 						// 改变比赛状态
    public static final int REQUEST_TYPE_CREATE_SERVERAREA_RESULTR = 2093; 						// 创建服务区结果
    public static final int REQUEST_TYPE_MOVE_SERVER_TO_NEW_SERVERAREA_RESULTR = 2094; 			// 移动服务器到新服务区结果
    public static final int REQUEST_TYPE_REMOVE_SCENARIO_RESULTR = 2095; 							// 删除想定结果
    public static final int REQUEST_TYPE_HISTORY_ROOM_INFO_RESULT = 2096;						// 请求历史房间信息结果
    public static final int REQUEST_TYPE_HISTORY_ROOM_PLAYAGAIN_RESULT = 2097;					// 历史房间再玩儿一把结果
    public static final int REQUEST_TYPE_AUTO_GENERATE_BATTLE_RESULTR = 2098;					// 自动生成对决结果
    public static final int REQUEST_TYPE_PUBLIC_SCENARIO_PRIVATE_RESULTR = 2099;				// 公有想定私有化结果
    public static final int REQUEST_TYPE_SET_GROUP_GROUP_RESULTR = 2100;						// 设置群组分组（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_REMOVE_GROUP_GROUP_RESULTR = 2101;					// 删除群组分组
    public static final int REQUEST_TYPE_MOVE_GROUP_TO_GROUP_GROUP_RESULTR = 2102;				// 将群组移动到分组
    public static final int REQUEST_TYPE_SET_SCENARIO_GROUP_RESULTR = 2103;					// 设置想定分组（存在则更新，不存在则新增）
    public static final int REQUEST_TYPE_REMOVE_SCENARIO_GROUP_RESULTR = 2104;					// 删除想定分组
    public static final int REQUEST_TYPE_MOVE_SCENARIO_TO_SCENARIO_GROUP_RESULTR = 2105;		// 将想定移动到分组
    public static final int REQUEST_TYPE_INVITE_USER_JOIN_GROUP_MSG = 2106;					// 邀请用户加入群组的消息（可能是离线）
    public static final int REQUEST_TYPE_GIVE_SCENARIO_TO_USER_RESULT = 2107;					// 将想定赠予某人结果
    public static final int REQUEST_TYPE_SHARE_SCENARIO_TO_USER_RESULT = 2108;					// 将想定共享某人
    public static final int REQUEST_TYPE_SHARE_SCENARIO_LIST_RESULT = 2109;					// 请求共享想定列表结果
    public static final int REQUEST_TYPE_CREATE_GAME_ROOM_RESULT = 2110;						// 创建比赛房间
    public static final int REQUEST_TYPE_PRIVATE_TO_PUBLIC_APPLY_RESULT = 2111;				// 请求私有想定转公有想定申请列表结果
    public static final int REQUEST_TYPE_CHANGE_USER_INFO_RESULT = 2112;						// 修改用户信息结果
    public static final int REQUEST_TYPE_GET_SCENARIO_RANKING_RESULT = 2113;					// 获取想定排名结果
    public static final int REQUEST_TYPE_GET_GAME_BATTLE_RESULT = 2114;							// 获取比赛对战表结果
    */


    public static final String VERSION_STR =	"VersionStr";

    public static final String SERVER_REQUEST_TYPE_STR						= "协议";

    // 服务端发送给管理端请求
    public static final String REQUEST_TYPE_SERVER_INFO					= "服务器信息";
    public static final String REQUEST_TYPE_GAME_SCORE						= "游戏结果";
    public static final String REQUEST_TYPE_START_SERVER_RESULT			= "启动游戏结果";
    public static final String REQUEST_TYPE_LOG							= "日志";

    // 管理端发送给服务端请求
    public static final String REQUEST_TYPE_START_SERVER					= "启动游戏";
    public static final String REQUEST_TYPE_CLOSE_ROOM						= "关闭游戏";

    public static final String AI_SERVER_REQUEST_TYPE_STR					= "协议";

    // AI服务端发送给管理端请求
    public static final String REQUEST_TYPE_AI_SERVER_INFO					= "AI服务器信息";
    public static final String REQUEST_TYPE_AI_SERVER_INFO_UPDATE			= "更新Ai服务程序";
    public static final String REQUEST_TYPE_START_AI_RESULT				= "启动Ai结果";

    // 管理端发送给AI服务端请求
    public static final String REQUEST_TYPE_START_AI						= "启动AI";
    public static final String REQUEST_TYPE_STOP_AI						= "关闭AI";

    // 公用字段
    public static final String REQUEST_TYPE_RESULT						=	"Result";
    public static final String REQUEST_TYPE_RESULT_TRUE			=		"True";
    public static final String REQUEST_TYPE_RESULT_FALSE			=		"False";
    public static final String REQUEST_TYPE_ERROR_INFO				=		"ErrorInfo";
    public static final String REQUEST_TYPE_ADD_USER_ACCEPT		=		"AddUserAccept";


    public static final String REQUEST_ACCOUNT								= "请求理由";

    // 用户字段
    public static final String USER_INFO=	"UserInfo";
    public static final String USER_ID=		"UserID";
    public static final String USER_NAME=	"UserName";
    public static final String USER_ACCOUNT="UserAccount";
    public static final String USER_PASSWORD="UserPassword";
    public static final String USER_EMAIL=	"UserEmail";
    public static final String USER_SEX=	"UserSex";
    public static final String USER_REGISTER_TIME=	"UserRegisterTime";
    public static final String USER_JOINED_THE_ROOM="UserJoinedTheRoom";
    public static final String USER_SIM_ROLE=		"UserSimRole";
    public static final String USER_PRIVATE_SCENARIO="UserPrivateScenario";
    public static final String USER_VICTORY_COUNT=	"UserVictoryCount";
    public static final String USER_FAILURE_COUNT=	"UserFailureCount";
    public static final String USER_LEVEL=			"UserLevel";
    public static final String USER_GAME_DURATION=	"UserGameDuration";
    public static final String USER_ELAPSED_TIME=	"UserElapsedTime";
    public static final String USER_LIST=			"UserList";
    public static final String USER_BLACKFRIEND_LIST="UserBlackFriendList";
    public static final String USER_FRIEND_LIST=	"UserFriendList";
    public static final String USER_CUSTOMER_LIST=	"UserCustomerList";
    public static final String USER_GROUP_LIST=		"UserGroupList";
    public static final String USER_JURISDICTION=	"UserJurisdiction";
    public static final String USER_GROUPS=			"UserGroups";
    public static final String USER_GROUP_ID=		"UserGroupID";
    public static final String USER_GROUP_NAME=		"UserGroupName";
    public static final String USER_HOLD_CURRENT_ROOM="UserHoldCurrentRoom";
    public static final String USER_LOGIN_STATUS=	"UserLoginStatus";
    public static final String USER_TYPE=			"UserType";
    public static final String USER_GOLD=			"UserGold";
    public static final String USER_SCORE=			"UserScore";
    public static final String USER_HISTORY_ROOM=	"UserHistoryRoom";
    public static final String USER_HISTORY_ROOM_SORT="UserHistoryRoomSort";
    public static final String USER_HISTORY_ROOM_ID="UserHistoryRoomID";
    public static final String USER_HISTORY_ROOM_NAME="UserHistoryRoomName";
    public static final String USER_HISTORY_ROOM_INFO="UserHistoryRoomInfo";
    public static final String USER_READY_STATUS=	"UserReadyStatus";
    public static final String USER_SIDE=			"UserSide";
    public static final String USER_JOIN_TIME=		"JoinRoomTime";
    public static final String USER_LEAVE_TIME=		"LeaveRoomTime";
    public static final String USER_ID_SET=			"UserIDSet";


    // 群字段
    public static final String GROUP_INFO=			"GroupInfo";
    public static final String GROUP_ID=			"GroupID";
    public static final String GROUP_NAME=			"GroupName";
    public static final String GROUP_NOTICE=		"GroupNotice";
    public static final String GROUP_LIST=			"GroupList";
    public static final String GROUP_USER_LIST=	 "UserList";
    public static final String GROUP_USER_ID_LIST=	"GroupUserIDList";
    public static final String GROUP_USER_ROLE_LIST="GroupUserRoleList";
    public static final String GROUP_USER_ROLE=		"GroupUserRole";
    public static final String GROUP_GROUPS=		"GroupGroups";
    public static final String GROUP_GROUP_ID=		"GroupGroupID";
    public static final String GROUP_GROUP_NAME=	"GroupGroupName";
    public static final String GROUP_ID_SET=		"GroupIDSet";


    // 想定字段
    public static final String SCENARIO_INFO=		"ScenarioInfo";
    public static final String SCENARIO_ID=			"ScenarioID";
    public static final String SCENARIO_NAME=		"ScenarioName";
    public static final String SCENARIO_AUTHOR=		"ScenarioAuthor";
    public static final String SCENARIO_DESC=		"ScenarioDesc";
    public static final String SCENARIO_MASTER=		"ScenarioMaster";
    public static final String SCENARIO_CREATE_TIME="ScenarioCreateTime";
    public static final String SCENARIO_STATE=		"ScenarioState";
    public static final String SCENARIO_COMMENTS=	"ScenarioComments";
    public static final String SCENARIO_SIDES=		"ScenarioSides";
    public static final String SCENARIO_SIDE_NAME=	"ScenarioSideName";
    public static final String SCENARIO_SIDE_MAX_COUNT="ScenarioSideMaxCount";
    public static final String SCENARIO_IS_PUBLIC=	"ScenarioIsPublic";
    public static final String SCENARIO_SUPPORT_AI=	"ScenarioSupportAI";
    public static final String SCENARIO_GROUP_ID=	"ScenarioGroupID";
    public static final String SCENARIO_GROUP_NAME=	"ScenarioGroupName";
    public static final String PUBLIC_SCENARIO_GROUPS="PublicScenarioGroups";
    public static final String PRIVATE_SCENARIO_GROUPS="PrivateScenarioGroups";
    public static final String SCENARIO_MODEL=		"ScenarioModel";
    public static final String SCENARIO_LIST=		"ScenarioList";
    public static final String PUBLIC=		"Public";

    public static final String SCENARIO_AI_INFO=	"ScenarioAIInfo";
    public static final String SCENARIO_AI_SIDE_NAME="ScenarioAISideName";
    public static final String SCENARIO_AI_NAME=	"ScenarioAIName";
    public static final String SCENARIO_AI_DESCRIPTION="ScenarioAIDescription";
    public static final String SCENARIO_AI_SELECTED="ScenarioAISelected";
    public static final String SCENARIO_AI_LEVEL=	"ScenarioAILevel";


    // 服务区字段
    public static final String SERVER_AREA_ID=		"ServerAreaID";
    public static final String SERVER_AREA_ROOM_NUM="ServerArearRoomNum";
    public static final String SERVER_AREA_INFO=	"ServerAreaInfo";
    public static final String SERVER_AREA_NAME=	"ServerAreaName";
    public static final String SERVER_AREA_CURRENT_ROOM_COUNT="ServerAreaCurrentRoomCount";
    public static final String SERVER_AREA_MAX_ROOM_COUNT=	"ServerAreaMaxRoomCount";
    public static final String SERVER_AREA_ROOM_LIST="ServerAreaRoomList";
    public static final String SERVER_AREA_CAPACITY_INFO=	"ServerAreaCapacityInfo";
    public static final String SERVER_AREA_LIST_AI=	"AIServerAreaList";


    // 房间字段
    public static final String ROOM_INFO=		"RoomInfo";
    public static final String ROOM_ID=			"RoomID";
    public static final String ROOM_AI_CREATE=	"RoomIsAICreate";
    public static final String ROOM_NAME=		"RoomName";
    public static final String ROOM_LEADER_ID=	"RoomLeaderID";
    public static final String ROOM_SERVER_AREA_NAME="RoomServerAreaName";
    public static final String ROOM_PASSWORD=	"RoomPassword";
    public static final String ROOM_MODEL=		"RoomModel";
    public static final String ROOM_SCENARIO_ID="RoomScenarioID";
    public static final String ROOM_SCENARIO_NAME="RoomScenarioName";
    public static final String ROOM_SCENARIO_DESC="RoomScenarioDesc";
    public static final String ROOM_SIDE_LIST=	"RoomSideList";
    public static final String ROOM_APPID=		"RoomAPPID";
    public static final String ROOM_FED_NAME=	"RoomFedName";
    public static final String ROOM_IP=			"RoomIP";
    public static final String ROOM_PORT=		"RoomPort";
    public static final String ROOM_IS_VALID=	"RoomIsValid";
    public static final String ROOM_USER_NUM=	"RoomUserNum";
    public static final String ROOM_USER_LIST=	"RoomUserList";
    public static final String ROOM_OPEN_DIRECTOR="IsOpenDirector";
    public static final String ROOM_OPEN_VIEWER="IsOpenViewer";
    public static final String ROOM_HAVE_PASSWORD="HavePassword";
    public static final String ROOM_ALLOW_UERJOIN="IsAllowUserJoin";
    public static final String ROOM_DO_NOT_DISTURB="RoomDoNotDisturb";
    public static final String ROOM_USER_ELAPSED_TIME_LIST	="RoomUserElapsedTimeList";
    public static final String ROOM_STATUS=		"RoomStatus";
    public static final String ROOM_SIDE_CURRENT_USER_COUNT="RoomSideCurrentUserCount";
    public static final String ROOM_SIDE_STATUS="RoomSideStatus";
    public static final String ROOM_USER_STATUS_READY						= "已准备";
    public static final String ROOM_USER_STATUS_UN_READY					= "未准备";
    public static final String ROOM_MESSAGE=	"Message";
    public static final String ROOM_DESTORY=	"RoomDestory";
    public static final String ROOM_ISHACECSV=	"IsHaveCSV";
    public static final String ROOM_USER_ROLE=	"RoomUserRole";
    public static final String ROOM_GAME_RESULT="RoomGameResult";
    public static final String ROOM_GOLD_CHANGE="RoomGoldChange";
    public static final String ROOM_CENT_CHANGE="RoomCentChange";
    public static final String SERVICE_ID=		"ServiceID";
    public static final String IS_FROM_DB=		"IsFromDB";
    public static final String USER_FRIEND_STRATEGIC_LIST	="UserFrinedStrategicList"; // 好友战绩列表
    public static final String ROOM_CREATE_TIME="RoomCreateTime";
    public static final String ROOM_BEGINTIME=	"RoomBeginTime";
    public static final String ROOM_ENDTIME=	"RoomEndTime";
    public static final String ROOM_GAME_BEGINTIME="GameBeginTime";
    public static final String ROOM_GAME_ENDTIME="GameEndTime";
    public static final String ROOM_USER_STRATEGIC_LIST="UserStrategicList";


    // 服务器程序字段
    public static final String SERVERAPP_INFO=	"ServerAppInfo";
    public static final String SERVERAPP_ID=	"ServerAppID";
    public static final String SERVERAPP_IP=	"ServerAppIP";
    public static final String SERVERAPP_PORT=	"ServerAppPort";
    public static final String SERVERAPP_FEDERARE="ServerAppFederate";


    // 消息字段
    public static final String PRIVATE_MESSAGE=	"PrivateMessage";
    public static final String GROUP_MESSAGE=	"GroupMessage";
    public static final String SEND_TIME=		"SendTime";
    public static final String SEND_USER_ID=	"SendUserID";
    public static final String RECV_USER_ID=	"ReciveID";
    public static final String MESSAGE_INFO=	"MessageInfo";
    public static final String	NOTIFICATION_TYPE="NotificationType";
    public static final String	NOTIFICATION_RESULT="NotificationResult";
    public static final String	NOTIFICATION_CONTENT				=		"NotificationContent";
    public static final String RECEIVE_NOTIFICATION_TYPE			=		"ReceiveNotificationType";
    public static final String RECEIVE_NOTIFICATION_ITMES			=		"ReceiveNotificationItems";
    public static final String RECEIVE_NOTIFICATION_MESSAGE		=		"ReceiveNotificationMessage";
    public static final String CUSTOMERSERVICE_MESSAGE				=		"CustomerServiceMessage";


    // 通知消息分类
    public static final int NOTIFICATION_TYPE_ADD_GROUP			=		7500;		// 加群消息


    // 端口
    public static final int MOZI_SERVER_AGENT_PORT				=		9000;
    public static final int AI_SERVER_AGENT_PORT		=				9001;
    public static final int MOZI_CLIENT_PLATFORM_PORT		=			8777;


    public static final int LOGOUT_TIMER_MILLISECONDS			=		120000;		// 用户断线后2分钟未登录清空所在房间信息
    public static final int ROOM_CLOSE_TIMER_MILLISECONDS		=		120000;		// 已启动的房间如果有用户异常断线2分钟后如果无用户加入房间，则关闭房间
    public static final int LOGIN_SCORE_TIMER_MILLISECONDS			=	900000;		// 登录成功后计算积分，15分钟1积分

    public static final String DIRECTOR_STR = "导演";
    public static final String AUDIENCE_STR = "观众";
    public static final String	PAGE=			"page";		// 页码
    public static final String	PAGESIZE=		"pagesize";	// 每页个数

    // 报名表信息
    public static final String EXCEL_COMPETITION_USER_LIST		=	"ExcelUserList";
    public static final String EXCEL_COMPETITION_GAMEUSER_LIST	=	"ExcelGameUserList";
    public static final String EXCEL_COMPETITION_GAMETEAM_LIST		="ExcelGameTeamList";
    public static final String COMPETITION_GAME_ID				=	"CompetitionGameID";
    public static final String COMPETITION_GAME_NAME			=	"CompetitionGameName";
    public static final String EXCEL_COMPETITION_SCHOOL		=	"ExcelSchool";
    public static final String EXCEL_COMPETITION_TEAMNUM		=	"ExcelTeamNum";
    public static final String EXCEL_COMPETITION_TEAMNAME		=	"ExcelTeamName";
    public static final String EXCEL_COMPETITION_ISLEADER		=	"ExcelIsLeader";
    public static final String EXCEL_COMPETITION_NAME			=	"ExcelCompetitionName";
    public static final String EXCEL_COMPETITION_STUDYNUM		=	"ExcelStudyNum";
    public static final String EXCEL_COMPETITION_YUANXI		=	"ExcelYuanXi";
    public static final String EXCEL_COMPETITION_PHONE			=	"ExcelPhone";
    public static final String EXCEL_COMPETITION_EMAIL			=	"ExcelEmail";


    // 问题库
    public static final String QUESTION_ID	="QuestionID";
    public static final String QUESTION_TITLE="QuestionTitle";
    public static final String QUESTION_KEYWORD="QuestionKeyword";
    public static final String QUESTION_ANSWER	="QuestionAnswer";

}
