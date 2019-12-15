 package com.zb.base.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

 /**
  * @author GuoKun
  * @version 1.0
  * @create_date 2019/6/4 15:32
  */
 public class JsonMessageDefine {

     /**
      * 正常情况
      */
     public final static String SUCCESS = "SUCCESS";

     /**
      * 传入参数错误
      */
     public final static String PARAM_ERROR = "PARAM_ERROR";//,"传入错误参数或参数不全");

     /**
      * 非法参数
      */
     public final static String ILLEGAL_PARAM = "ILLEGAL_PARAM";

     /**
      * api错误
      */
     public final static String API_ERROR = "API_ERROR" ;

     /**
      * 没有符合条件的数据
      */
     public final static String NO_DATA = "NO_DATA";//,"没有符合条件的数据");

     /**
      * 执行出未知异常（正常情况不出现，调试时出现）
      */
     public final static String OP_FAILED = "OP_FAILED";

     /**
      * 无访问权限
      */
     public final static String AUTH_DENIED = "AUTH_DENIED";//,"无访问权限此资源");

     /**
      * 未登录
      */
     public static final String NO_LOGIN = "NO_LOGIN";

     /**
      * 会话过期
      */
     public static final String SESSION_TIMEOUT = "SESSION_TIMEOUT";

     /**
      * 账号过期
      */
     public static final String USER_TIMEOUT = "USER_TIMEOUT";

     /**
      * 账号已更改，请重新登录
      */
     public static final String ACCOUNT_MODIFIED = "ACCOUNT_MODIFIED";

     /**
      * 账号已被禁用，退出登录
      */
     public static final String ACCOUNT_DISABLED = "ACCOUNT_DISABLED";

     /**
      * 默认车辆已被修改，请重新登录
      */
     public static final String DEFAULT_VIN_CHANGED = "DEFAULT_VIN_CHANGED";

     /**
      * 用户名或密码错误
      */
     public static final String LOGIN_ERROR = "LOGIN_ERROR";

     /**
      * 登录错误次数提醒
      */
     public static final String LOGIN_ERROR_REMINDER = "LOGIN_ERROR_REMINDER";

     /**
      * 账号无权限，请联系管理员
      */
     public static final String NO_PERMISSION_ERROR = "NO_PERMISSION_ERROR";


     /**
      * 用户名已存在
      */
     public static final String USER_EXIST = "USER_EXIST";

     /**
      * 服务到期
      */
     public static final String SERVICE_TIMEOUT = "SERVICE_TIMEOUT";

     /**
      * 用户已被禁用
      */
     public static final String USER_DISABLED = "USER_DISABLED";

     /**
      * 密码被重置
      */
     public static final String RESET_PASSWORD = "RESET_PASSWORD" ;

     /**
      * 旧密码错误
      */
     public static final String OLD_PASSWORD_ERROR = "OLD_PASSWORD_ERROR";

     /**
      * 名称已存在
      */
     public static final String UNIQUE_NAME_EXIST = "UNIQUE_NAME_EXIST";
     /**
      * 车型名称已存在
      */
     public static final String UNIQUE_VEHICLE_MODEL_NAME_EXIST = "UNIQUE_VEHICLE_MODEL_NAME_EXIST";
     /**
      * 车机供应商名称不允许重复
      */
     public static final String SUPPLIER_NAME_EXIST = "SUPPLIER_NAME_EXIST";

     /**
      * 车机供应商名称不允许重复
      */
     public static final String SUPPLIER_CODE_EXIST = "SUPPLIER_CODE_EXIST";

     /**
      * 编码已存在
      */
     public static final String UNIQUE_CODE_EXIST = "UNIQUE_CODE_EXIST";
     /**
      * IP已存在
      */
     public static final String UNIQUE_IP_EXIST = "UNIQUE_IP_EXIST";

     /**
      * 批量删除错误
      */
     public static final String BATCH_DELETE_ERROR = "BATCH_DELETE_ERROR";

     /**
      * 已关联数据，不能删除
      */
     public static final String USED_DELETE_ERROR = "USED_DELETE_ERROR";

     /**
      * 机构下无人，将该用户作为负责人
      */
     public static final String NO_USER = "NO_USER";

     /**
      * 机构负责人，不能删除
      */
     public static final String ORG_ADMIN_DELETE_ERROR = "ORG_ADMIN_DELETE_ERROR";


     /**
      * 4S店用户不能分配到车厂
      */
     public static final String SHOP_USER_TO_FACTORY = "SHOP_USER_FACTORY";

     /**
      * 4S店端用户不允许登录车厂端
      */
     public static final String SHOP_NOT_ALLOW_LOGIN_FACTORY = "SHOP_NOT_ALLOW_LOGIN_FACTORY";

     /**
      * 车厂端用户不允许登录4S店
      */
     public static final String FACTORY_NOT_ALLOW_LOGIN_SHOP = "FACTORY_NOT_ALLOW_LOGIN_SHOP";


     /**
      * 车厂用户不能分配到4S店
      */
     public static final String FACTORY_USER_TO_SHOP = "FACTORY_USER_SHOP";

     /**
      * 上级机构已被删除
      */
     public static final String PARENT_ORG_NOT_EXIST = "PARENT_ORG_NOT_EXIST";

     /**
      * 首次登录
      */
     public static final String FIRST_TIME_LOGIN = "FIRST_TIME_LOGIN";

     /**
      * app登录设备ID错误
      */
     public static final String DEVICE_ID_ERROR = "DEVICE_ID_ERROR";

     /**
      * 版本号已存在
      */
     public static final String VERSION_NUMBER_EXIST = "VERSION_NUMBER_EXIST";

     /**
      * 版本名称已存在
      */
     public static final String VERSION_NAME_EXIST = "VERSION_NAME_EXIST";

     /**
      * 超过最大条数限制
      */
     public static final String MAX_PAGE_SIZE_ERROR = "MAX_PAGE_SIZE_ERROR";

     /**
      * 预约时间必须晚于当天时间
      */
     public static final String APPOINTMENT_TIME_NEED_LATER_THAN_TODAY = "APPOINTMENT_TIME_NEED_LATER_THAN_TODAY";

     /**
      * 预约时间必须晚于8点的时间
      */
     public static final String APPOINTMENT_TIME_NEED_LATER_THAN_EIGHT_CLOCK = "APPOINTMENT_TIME_NEED_LATER_THAN_EIGHT_CLOCK";

     /**
      * 已存在待确认的预约，无法新增预约
      */
     public static final String PROCESSING_APPOINTMENT_EXIST = "PROCESSING_APPOINTMENT_EXIST";


     /**
      * 设备编码不存在
      */
     public static final String DEVICE_CODE_INEXISTENCE = "DEVICE_CODE_INEXISTENCE";

     /**
      * 设备编码已绑定车辆
      */
     public static final String DEVICE_CODE_BOUND_VEHICLE = "DEVICE_CODE_BOUND_VEHICLE";

     /**
      * 车辆不存在
      */
     public static final String VEHICLE_INEXISTENCE = "VEHICLE_INEXISTENCE";

     /**
      * 车主已绑定车辆
      */
     public static final String OWNER_BOUND_VEHICLE = "OWNER_BOUND_VEHICLE";

     /**
      * 车辆未出售
      */
     public static final String VEHICLE_NO_SALE = "VEHICLE_NO_SALE";

     /**
      * 车主不存在
      */
     public static final String OWNER_INEXISTENCE = "OWNER_INEXISTENCE";

     /**
      * 车辆存在绑定关系
      */
     public static final String VEHICLE_EXIST_BINDING = "VEHICLE_EXIST_BINDING";

     /**
      * 车辆已经存在绑定关系，车主绑定异常
      */
     public static final String VEHICLE_EXIST_BINDING_ERROR = "VEHICLE_EXIST_BINDING_ERROR";

     /**
      * 车辆不存在绑定关系
      */
     public static final String VEHICLE_INEXISTENCE_BINDING = "VEHICLE_INEXISTENCE_BINDING";

     /**
      * 角色名不符合规则
      */
     public static final String ROLE_NAME_NONCONFORMITY = "ROLE_NAME_NONCONFORMITY";

     /**
      * 角色不存在
      */
     public static final String ROLE_INEXISTENCE = "ROLE_INEXISTENCE";

     /**
      * 管理员角色不允许修改
      */
     public static final String ADMIN_CAN_NOT_MODIFY = "ADMIN_CAN_NOT_MODIFY";

     /**
      * 角色名已存在
      */
     public static final String ROLE_NAME_EXIST = "ROLE_NAME_EXIST";

     /**
      * 该角色已被使用，所属机构不能更改
      */
     public static final String ROLE_USED_ORG_CAN_NOT_MODIFY = "ROLE_USED_ORG_CAN_NOT_MODIFY";

     /**
      * 机构管理员角色不能删除
      */
     public static final String ORG_ADMIN_ROLE_CAN_NOT_DELETE = "ORG_ADMIN_ROLE_CAN_NOT_DELETE";

     /**
      * root用户不允许登录此系统
      */
     public static final String ROOT_USER_CAN_NOT_LOGIN = "ROOT_USER_CAN_NOT_LOGIN";

     /**
      * 用户不允许登录此系统
      */
     public static final String USER_CAN_NOT_LOGIN = "USER_CAN_NOT_LOGIN";

     /**
      * 不能创建root用户
      */
     public static final String CAN_NOT_CREATE_ROOT_USER = "CAN_NOT_CREATE_ROOT_USER";

     /**
      * 无权修改root账户的信息
      */
     public static final String CAN_NOT_MODIFY_ROOT_INFO = "CAN_NOT_MODIFY_ROOT_INFO";

     /**
      * 用户的角色已存在
      */
     public static final String USER_ROLE_EXIST = "USER_ROLE_EXIST";

     /**
      * 不能添加机构管理员的角色
      */
     public static final String CAN_NOT_ADD_ORG_ADMIN_ROLE = "CAN_NOT_ADD_ORG_ADMIN_ROLE";

     /**
      * 设备编号不能为空
      */
     public static final String DEVICE_CODE_CAN_NOT_NULL = "DEVICE_CODE_CAN_NOT_NULL";

     /**
      * iccid不能为空
      */
     public static final String ICCID_CAN_NOT_NULL = "ICCID_CAN_NOT_NULL";

     /**
      * 设备编号已存在
      */
     public static final String DEVICE_CODE_EXIST = "DEVICE_CODE_EXIST";

     /**
      * iccid已存在
      */
     public static final String ICCID_EXIST = "ICCID_EXIST";

     /**
      * 只支持excel表格
      */
     public static final String ONLY_SUPPORT_EXCEL = "ONLY_SUPPORT_EXCEL";

     /**
      * 模板不存在
      */
     public static final String TEMPLATE_INEXISTENCE = "TEMPLATE_INEXISTENCE";

     /**
      * HTTP请求方式错误
      */
     public static final String HTTP_REQUEST_ERROR = "HTTP_REQUEST_ERROR";

     /**
      * 机构名已存在
      */
     public static final String ORG_NAME_EXIST = "ORG_NAME_EXIST";

     /**
      * 4S店下不允许添加子机构
      */
     public static final String SHOPS_CAN_NOT_ADD_ORG = "SHOPS_CAN_NOT_ADD_ORG";

     /**
      * 该机构有下级机构，不能删除
      */
     public static final String AGENCY_HAS_SUBORDINATE_BODYS_DELETE_ERROR = "AGENCY_HAS_SUBORDINATE_BODYS_DELETE_ERROR";

     /**
      * 该机构有人员，不能删除
      */
     public static final String AGENCY_HAS_PEOPLE_DELETE_ERROR = "AGENCY_HAS_PEOPLE_DELETE_ERROR";

     /**
      * 该机构有角色，不能删除
      */
     public static final String AGENCY_HAS_ROLE_DELETE_ERROR = "AGENCY_HAS_ROLE_DELETE_ERROR";

     /**
      * 该机构有车辆，不能删除
      */
     public static final String AGENCY_HAS_VEHICLES_DELETE_ERROR = "AGENCY_HAS_VEHICLES_DELETE_ERROR";

     /**
      * 该机构有设备，不能删除
      */
     public static final String AGENCY_HAS_DEVICE_DELETE_ERROR = "AGENCY_HAS_DEVICE_DELETE_ERROR";

     /**
      * 该机构有车主，不能删除
      */
     public static final String AGENCY_HAS_OWNERS_DELETE_ERROR = "AGENCY_HAS_OWNERS_DELETE_ERROR";

     /**
      * 该手机号不存在
      */
     public static final String MOBILE_INEXISTENCE = "MOBILE_INEXISTENCE";

     /**
      * 该手机号已注册
      */
     public static final String MOBILE_REGISTERED = "MOBILE_REGISTERED";

     /**
      * 该手机号绑定的证件号已被注册
      */
     public static final String CERT_CODE_REGISTERED = "CERT_CODE_REGISTERED";

     /**
      * 短信验证码已失效
      */
     public static final String VERIFICATION_CODE_LOST_EFFICACY = "VERIFICATION_CODE_LOST_EFFICACY";

     /**
      * 短信验证码重复发送
      */
     public static final String VERIFICATION_CODE_REPEAT_SEND = "VERIFICATION_CODE_REPEAT_SEND" ;

     /**
      * 短信验证码错误
      */
     public static final String VERIFICATION_CODE_ERROR = "VERIFICATION_CODE_ERROR";

     /**
      * 注册失败
      */
     public static final String REGISTER_FAILURE = "REGISTER_FAILURE";

     /**
      * 用户未注册
      */
     public static final String USER_UNREGISTERED = "USER_UNREGISTERED" ;

     /**
      * 账号不存在
      */
     public static final String ACCOUNT_INEXISTENCE = "ACCOUNT_INEXISTENCE";

     /**
      * 用户不存在
      */
     public static final String USER_INEXISTENCE = "USER_INEXISTENCE";

     /**
      * 手机号未注册，请前往4S店注册
      */
     public static final String UNREGISTERED_OF_4S = "UNREGISTERED_OF_4S" ;

     /**
      * 帐号未注册，请注册
      */
     public static final String USER_UNREGISTERED_REGISTERED = "USER_UNREGISTERED_REGISTERED";

     /**
      * 手机号已经被使用
      */
     public static final String MOBILE_USED = "MOBILE_USED";

     /**
      * iccid不存在
      */
     public static final String ICCID_INEXISTENCE = "ICCID_INEXISTENCE";

     /**
      * 该环境不允许实名认证
      */
     public static final String RNR_NOT_ENABLE = "RNR_NOT_ENABLE";

     /**
      * 不能重复认证
      */
     public static final String CAN_NOT_REPEAT_CERTIFICATION = "CAN_NOT_REPEAT_CERTIFICATION";

     /**
      * 未申请实名认证
      */
     public static final String NO_AUTHENTICATION = "NO_AUTHENTICATION" ;
     /**
      * 正在认证中
      */
     public static final String AUTHENTICATIONING = "AUTHENTICATIONING" ;

     /**
      * 认证失败
      */
     public static final String AUTHENTICATION_FAIL = "AUTHENTICATION_FAIL" ;

     /**
      * 认证成功
      */
     public static final String AUTHENTICATION_SUCCESS = "AUTHENTICATION_SUCCESS" ;


     /**
      * 资料错误
      */
     public static final String DATA_SUBMIT_ERROR = "DATA_SUBMIT_ERROR";

     /**
      * 资料提交失败
      */
     public static final String DATA_SUBMIT_FAILED = "DATA_SUBMIT_FAILED";


     /**
      * 不能重复退款
      */
     public static final String CAN_NOT_REPEAT_REFUND = "CAN_NOT_REPEAT_REFUND";

     /**
      * 微信支付错误
      */
     public static final String WEIXIN_PAY_ERROR = "WEIXIN_PAY_ERROR";

     /**
      * 微信退款错误
      */
     public static final String WEIXIN_REFUND_ERROR = "WEIXIN_REFUND_ERROR";

     /**
      * 订单不支持退款
      */
     public static final String ORDER_NOT_SUPPORT_REFUND = "ORDER_NOT_SUPPORT_REFUND" ;

     /**
      * 无权删除root账户的信息
      */
     public static final String CAN_NOT_DELETE_ROOT_INFO = "CAN_NOT_DELETE_ROOT_INFO";

     /**
      * 没有该支付方式
      */
     public static final String NO_THIS_PAY_WAY = "NO_THIS_PAY_WAY";

     /**
      * 没有该流量套餐
      */
     public static final String NO_THIS_FLOW_PACKAGE = "NO_THIS_FLOW_PACKAGE";

     /**
      * 暂不支持此支付方式
      */
     public static final String TEMPORARY_DOES_NOT_SUPPORT_THIS_PAY_WAY = "TEMPORARY_DOES_NOT_SUPPORT_THIS_PAY_WAY";

     /**
      * 订单编号不存在
      */
     public static final String ORDER_NUMBER_INEXISTENCE = "ORDER_NUMBER_INEXISTENCE";

     /**
      * 该订单未支付
      */
     public static final String ORDER_NO_PAY = "ORDER_NO_PAY";

     /**
      * 订单支付关闭
      */
     public static final String ORDER_PAY_CLOSED = "ORDER_PAY_CLOSED" ;

     /**
      * 订单已退款
      */
     public static final String ORDER_REFUND = "ORDER_REFUND" ;

     /**
      * 订单已支付
      */
     public static final String ORDER_PAY_SUCCESS = "ORDER_PAY_SUCCESS" ;

     /**
      * 订单关闭错误
      */
     public static final String ORDER_CLOSED_ERROR = "ORDER_CLOSED_ERROR" ;

     /**
      * 管理员权限无法编辑
      */
     public static final String ADMIN_ROLE_CAN_NOT_EDIT = "ADMIN_ROLE_CAN_NOT_EDIT";

     /**
      * 车辆没有绑定设备
      */
     public static final String VIN_NO_BINDING_DEVICE = "VIN_NO_BINDING_DEVICE";

     /**
      * 账号未绑定车辆，请前往4S店绑定
      */
     public static final String ACCOUNT_NO_BINDING_VEHICLE = "ACCOUNT_NO_BINDING_VEHICLE";

     /**
      * 机构的负责人不能删除
      */
     public static final String ORG_LEADER_CAN_NOT_DELETE = "ORG_LEADER_CAN_NOT_DELETE";

     /**
      * 机构的联系人不能删除
      */
     public static final String ORG_CONTACT_CAN_NOT_DELETE = "ORG_CONTACT_CAN_NOT_DELETE";

     /**
      * 车厂用户不能分配4S店的角色
      */
     public static final String PLAT_USER_CAN_NOT_ALLOCATION_SHOP = "PLAT_USER_CAN_NOT_ALLOCATION_SHOP";

     /**
      * 4S用户不能分配车厂的角色
      */
     public static final String SHOP_USER_CAN_NOT_ALLOCATION_PLAT = "SHOP_USER_CAN_NOT_ALLOCATION_PLAT";

     /**
      * 版本已存在
      */
     public static final String VERSION_ALREADY_EXISTS = "VERSION_ALREADY_EXISTS";
     /**
      * 版本已存在
      */
     public static final String VERSION_NAME_ALREADY_EXISTS = "VERSION_NAME_ALREADY_EXISTS";

     /**
      * 产地全称已存在
      */
     public static final String PRODUCER_FULL_NAME_ALREADY_EXISTS = "PRODUCER_FULL_NAME_ALREADY_EXISTS";

     /**
      * 产地简称已存在
      */
     public static final String PRODUCER_SIMPLE_NAME_ALREADY_EXISTS = "PRODUCER_SIMPLE_NAME_ALREADY_EXISTS";

     /**
      * 上传文件最大限制500M
      */
     public static final String UPLOAD_FILE_MAX_SIZE_LIMIT_ERROR = "UPLOAD_FILE_MAX_SIZE_LIMIT_ERROR";

     /**
      * 上传身份证照最大限制500KB
      */
     public static final String UPLOAD_IDENTITY_MAX_SIZE_LIMIT = "UPLOAD_IDENTITY_MAX_SIZE_LIMIT";

     /**
      * 上传失败
      */
     public static final String UPLOAD_ERROR = "UPLOAD_ERROR";

     /**
      * 添加超过限制
      */
     public static final String ADD_OVERLIMIT = "ADD_OVERLIMIT";

     /**
      * 车辆不在线
      */
     public static final String VEHICLE_OFFLINE = "VEHICLE_OFFLINE";

     /**
      * 发送消息失败
      */
     public static final String SEND_MSG_ERROR = "SEND_MSG_ERROR";

     /**
      * 没有检测数据
      */
     public static final String NO_DETECTION_DATA = "NO_DETECTION_DATA";

     /**
      * 故障检测失败
      */
     public static final String FAULT_DETECTION_ERROR = "FAULT_DETECTION_ERROR";

     /**
      * 故障检测中
      */
     public static final String FAULT_DETECTIONING = "FAULT_DETECTIONING";

     /**
      * 手机号或证件号已存在
      */
     public static final String MOBILE_OR_IDCARD_EXISTS = "MOBILE_OR_IDCARD_EXISTS" ;
     /**
      * 手机号已存在
      */
     public static final String MOBILE_EXISTS = "MOBILE_EXISTS" ;
     /**
      * 证件号已存在
      */
     public static final String IDCARD_EXISTS = "IDCARD_EXISTS" ;

     /**
      * 长时间未登录
      */
     public static final String LONG_TIME_NO_LOGIN = "LONG_TIME_NO_LOGIN" ;

     /**
      * 预约已经取消
      */
     public static final String ALREADY_CANCELED = "ALREADY_CANCELED";


     /**
      * 新闻
      */
     public static final String DEALER_NEWS = "DEALER_NEWS"; //经销商新闻
     public static final String MODELS_NEWS = "MODELS_NEWS"; //车型新闻
     public static final String MEDIA_EVALUATION = "MEDIA_EVALUATION"; //媒体评测
     public static final String FOCUS_TOPIC = "FOCUS_TOPIC"; //焦点专题
     public static final String PUBLIC_BENEFIT = "PUBLIC_BENEFIT"; //公益善行

     /**
      * 车架号错误
      */
     public static final String VIN_ERROR = "VIN_ERROR";

     /**
      * 车辆不需要唤醒
      */
     public static final String VIN_AWAKEN_NO_NEED_ERROR = "VIN_AWAKEN_NO_NEED_ERROR";

     /**
      * 车辆唤醒超过最大次数
      */
     public static final String VIN_AWAKEN_MAX_TIMES_ERROR = "VIN_AWAKEN_MAX_TIMES_ERROR";

     /**
      * 负责人的所属机构类型与该机构类型不匹配
      */
     public static final String ADMIN_ORG_TYPE_MATCH_ERROR = "ADMIN_ORG_TYPE_MATCH_ERROR";

     /**
      * 用户属于多个机构管理员
      *    ---主要应用4S店提示
      */
     public static final String USER_BELONG_MORE_ORG = "USER_BELONG_MORE_ORG" ;

     /**
      * 用户已经被踢出
      */
     public static final String USER_KICKED_OUT = "USER_KICKED_OUT" ;

     /**
      * 登录失败多次，账户锁定10分钟
      */
     public static final String LOGIN_FAILED_MULTIPLE_TIMES = "LOGIN_FAILED_MULTIPLE_TIMES" ;

     /**
      * ICCID 未激活
      */
     public static final String ICCID_INACTIVE = "ICCID_INACTIVE" ;

     /**
      * 支付失败
      */
     public static final String PAYMENT_FAIL = "PAYMENT_FAIL" ;

     /** AMSP 接口定义 **/
     /**
      * 实名制信息查询失败,请重试
      */
     public static final String REAL_NAME_SYSTEM_QUERY_ERROR = "REAL_NAME_SYSTEM_QUERY_ERROR" ;
     /**
      * 当前车载设备号正在实名中,系统在您实名通过后自动帮您充值
      */
     public static final String REAL_NAME_IN_CERTIFICATION = "REAL_NAME_IN_CERTIFICATION" ;
     /**
      * 当前车载设备号未实名,系统在您实名通过后自动帮您充值
      */
     public static final String REAL_NAME_NO_CERTIFICATION = "REAL_NAME_NO_CERTIFICATION" ;
     /**
      * 您的充值信息已提交，我们会尽快为您充值
      */
     public static final String RECHARGE_INFORMATION_SUBMIT = "RECHARGE_INFORMATION_SUBMIT" ;

     /**
      * 订单已充值过
      */
     public static final String ORDER_RECHARGED = "ORDER_RECHARGED";

     /**
      * 该套餐充值需先实名，请先进行实名认证
      */
     public static final String NEED_REAL_NAME_CERTIFICATION_FIRST = "NEED_REAL_NAME_CERTIFICATION_FIRST";

     /**
      * 充值出现异常
      */
     public static final String RECHARGE_FAILED = "RECHARGE_FAILED";

     /**
      * 时间戳已经过期！请确保50秒内发起请求！
      */
     public static final String TIMESTAMP_TIMEOUT = "TIMESTAMP_TIMEOUT" ;
     /**
      * 代理id必须存在！
      */
     public static final String AGENTID_INEXISTENCE = "AGENTID_INEXISTENCE" ;
     /**
      * 加密字符串必须存在！
      */
     public static final String ENCRYPTED_STRING_INEXISTENCE = "ENCRYPTED_STRING_INEXISTENCE" ;
     /**
      * 加密字符串无效
      */
     public static final String ENCRYPTED_STRING_INVALID = "ENCRYPTED_STRING_INVALID" ;
     /**
      * 无效的代理id！
      */
     public static final String AGENTID_INVALID = "AGENTID_INVALID" ;
     /**
      * 时间戳参数必须存在！
      */
     public static final String TIMESTAMP_INEXISTENCE = "TIMESTAMP_INEXISTENCE" ;
     /**
      * 该充值卡已经被充值激活过，不能重复使用！
      */
     public static final String RECHARGE_CARD_IS_ACTIVATED = "RECHARGE_CARD_IS_ACTIVATED" ;
     /**
      * 充值卡卡号必须填写！
      */
     public static final String RECHARGE_CARD_NUMBER_INEXISTENCE = "RECHARGE_CARD_NUMBER_INEXISTENCE" ;
     /**
      * 充值卡密码必须填写！
      */
     public static final String RECHARGE_CARD_PASSWORD_INEXISTENCE = "RECHARGE_CARD_PASSWORD_INEXISTENCE" ;
     /**
      * 该充值卡已经失效！
      */
     public static final String RECHARGE_CARD_INVALID = "RECHARGE_CARD_INVALID" ;
     /**
      * 充值卡密码无效，密码不正确！
      */
     public static final String RECHARGE_CARD_PASSWORD_ERROR = "RECHARGE_CARD_PASSWORD_ERROR" ;
     /**
      * 该充值卡号无效，没有找到该充值卡数据！
      */
     public static final String RECHARGE_CARD_NUMBER_ERROR = "RECHARGE_CARD_NUMBER_ERROR" ;
     /**
      * 该充值卡密码当天错误次数达到10次，充值卡暂时被锁定一天，隔天自动解封！
      */
     public static final String RECHARGE_CARD_LOCKED = "RECHARGE_CARD_LOCKED" ;
     /**
      * 该充值卡不能充值该ICCID！
      */
     public static final String RECHARGE_CARD_CAN_NOT_RECHARGE_THIS_ICCID = "RECHARGE_CARD_CAN_NOT_RECHARGE_THIS_ICCID" ;
     /**
      * 其它
      */
     public static final String OTHER = "OTHER" ;

     /**
      * 查询超时
      */
     public static final String QUERY_TIME_OUT = "QUERY_TIME_OUT" ;

     /**
      * 活动已到期
      */
     public static final String ACTIVITY_EXPIRED = "ACTIVITY_EXPIRED";

     /**
      * 活动已终止
      */
     public static final String ACTIVITY_DISCONTINUE = "ACTIVITY_DISCONTINUE";

     /**
      * 设备更换绑定失败
      */
     public static final String DEVICE_CHANGE_BANDING_ERROR = "DEVICE_CHANGE_BANDING_ERROR" ;


     /**
      * 参数版本已存在
      */
     public static final String PARAM_VERSION_EXIST = "PARAM_VERSION_EXIST";

     /**
      * 参数版本已被使用，无法删除
      */
     public static final String PARAM_VERSION_DEL_IS_USED = "PARAM_VERSION_DEL_IS_USED";

     /**
      * 参数版本已被使用，无法更新
      */
     public static final String PARAM_VERSION_UPDATE_IS_USED = "PARAM_VERSION_UPDATE_IS_USED";

     /**
      * 广告上名称已存在
      */
     public static final String ADVERTISEMENT_NAME_EXIST = "ADVERTISEMENT_NAME_EXIST";

     /**
      * 该角色已被引用，无法删除
      */
     public static final String ROLE_REFERENCED = "ROLE_REFERENCED";
     /**
      * 品牌名称已存在
      */
     public static final String UNIQUE_SERIES_NAME_EXIST = "UNIQUE_SERIES_NAME_EXIST";
     /**
      * 调用第三方接口返回信息
      */
     public static final String THIRD_PARTY_INTERFACE = "THIRD_PARTY_INTERFACE";
     /**
      * T-Box供应商名称已存在
      */
     public static final String UNIQUE_SUPPLIER_NAME_EXIST = "UNIQUE_SUPPLIER_NAME_EXIST";
     /**
      * 内容超出
      */
     public static final String  CONTENT_EXCEEDED = "CONTENT_EXCEEDED";

     /**
      * 报警字段已存在
      */
     public static final String ALARM_PROPERTY_CODE_EXIST = "ALARM_PROPERTY_CODE_EXIST";

     /**
      *  证件号码不正确
      */
     public static final String CERT_CODE_ERROR = "CERT_CODE_ERROR";

     /**
      * 车牌号已被其他车辆绑定
      */
     public static final String PLATE_NO_BIND_ERROR = "PLATE_NO_BIND_ERROR";

     /**
      * 报警参数版本已存在
      */
     public static final String ALARM_PARAMETER_VERSION_EXIST = "ALARM_PARAMETER_VERSION_EXIST";

     /**
      * 报警参数版本已下发
      */
     public static final String ALARM_PARAMETER_VERSION_IS_USED = "ALARM_PARAMETER_VERSION_IS_USED";

     /**
      * 报警参数版本已禁用
      */
     public static final String ALARM_PARAMETER_VERSION_IS_DISABLE = "ALARM_PARAMETER_VERSION_IS_DISABLE";

     /**
      * vin码不存在
      */
     public static final String ALARM_EXCEL_VIN_NOT_EXIST = "ALARM_EXCEL_VIN_NOT_EXIST";

     /**
      * vin码重复
      */
     public static final String ALARM_EXCEL_DUPLICATED_VIN = "ALARM_EXCEL_DUPLICATED_VIN";

     /**
      * vin与品牌不符
      */
     public static final String ALARM_EXCEL_VIN_NOT_MATCH_SERIES = "ALARM_EXCEL_VIN_NOT_MATCH_SERIES";

     /**
      * vin与年款不符
      */
     public static final String ALARM_EXCEL_VIN_NOT_MATCH_YEAR = "ALARM_EXCEL_VIN_NOT_MATCH_YEAR";

     /**
      * vin与车型不符
      */
     public static final String ALARM_EXCEL_VIN_NOT_MATCH_MODEL = "ALARM_EXCEL_VIN_NOT_MATCH_MODEL";

     /**
      * vin码不存在
      */
     public static final String OTA_EXCEL_VIN_NOT_EXIST = "OTA_EXCEL_VIN_NOT_EXIST";

     /**
      * vin码重复
      */
     public static final String OTA_EXCEL_DUPLICATED_VIN = "OTA_EXCEL_DUPLICATED_VIN";

     /**
      * vin与车系不符
      */
     public static final String OTA_EXCEL_VIN_NOT_MATCH_SERIES = "OTA_EXCEL_VIN_NOT_MATCH_SERIES";

     /**
      * vin与年款不符
      */
     public static final String OTA_EXCEL_VIN_NOT_MATCH_YEAR = "OTA_EXCEL_VIN_NOT_MATCH_YEAR";

     /**
      * vin与车型不符
      */
     public static final String OTA_EXCEL_VIN_NOT_MATCH_MODEL = "OTA_EXCEL_VIN_NOT_MATCH_MODEL";

     /**
      * 车辆未绑定设备
      */
     public static final String OTA_EXCEL_VIN_NOT_BIND_DEVICE = "OTA_EXCEL_VIN_NOT_BIND_DEVICE";

     /**
      * 车辆与协议不符
      */
     public static final String OTA_EXCEL_VIN_NOT_MATCH_PROTOCOL = "OTA_EXCEL_VIN_NOT_MATCH_PROTOCOL";

     /**
      * 设备与供应商不符
      */
     public static final String OTA_EXCEL_VIN_NOT_MATCH_SUPPLIER = "OTA_EXCEL_VIN_NOT_MATCH_SUPPLIER";

     /**
      * 车辆信息异常
      */
     public static final String OTA_EXCEL_VIN_VEHICLE_ABNORMAL = "OTA_EXCEL_VIN_VEHICLE_ABNORMAL";

     /**
      * vin码信息不存在
      */
     public static final String DEVICE_TBOX_PARAM_SETTING_VIN_NOT_EXIST= "DEVICE_TBOX_PARAM_SETTING_VIN_NOT_EXIST";

     /**
      * tbox生产厂商不匹配
      */
     public static final String DEVICE_TBOX_PARAM_SETTING_DEVICE_NOT_MATCH_SUPPLIER= "DEVICE_TBOX_PARAM_SETTING_DEVICE_NOT_MATCH_SUPPLIER";

     /**
      * 车系不匹配
      */
     public static final String DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_SERIES= "DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_SERIES";

     /**
      * 年款不匹配
      */
     public static final String DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_YEAR= "DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_YEAR";

     /**
      * 车型不匹配
      */
     public static final String DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_MODEL= "DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_MODEL";


     /**
      * vin码重复
      */
     public static final String DEVICE_TBOX_PARAM_SETTING_EXCEL_DUPLICATED_VIN= "DEVICE_TBOX_PARAM_SETTING_EXCEL_DUPLICATED_VIN";

     /**
      * 车辆不存在
      */
     public static final String OWNER_VEHICLE_NOT_EXIST= "OWNER_VEHICLE_NOT_EXIST";

     /**
      * 车牌无需修改
      */
     public static final String OWNER_PLANT_NO_IS_SAME= "OWNER_PLANT_NO_IS_SAME";

     /**
      * 资讯不可编辑
      */
     public static final String NEWS_NOT_UPDATABLE = "NEWS_NOT_UPDATABLE";

     /**
      * 协议已被使用，无法更新
      */
     public static final String PROTOCOL_UPDATE_IS_USED = "PROTOCOL_UPDATE_IS_USED";

     /**
      * 文件名已存在
      */
     public static final String OTA_FILE_NAME_NOT_UNIQUE = "OTA_FILE_NAME_NOT_UNIQUE";

     /**
      * 版本名称已存在
      */
     public static final String OTA_VERSION_NAME_NOT_UNIQUE = "OTA_VERSION_NAME_NOT_UNIQUE";

     /**
      * 协议已被使用，无法删除
      */
     public static final String PROTOCOL_DEL_IS_USED = "PROTOCOL_DEL_IS_USED";

     /**
      * OTA文件已过期
      */
     public final static String OTA_FILE_IS_ABANDON = "OTA_FILE_IS_ABANDON";

     /**
      * OTA文件已被强制终止
      */
     public final static String OTA_VERSION_IS_FORCE_ABANDON = "OTA_VERSION_IS_FORCE_ABANDON";

     /**
      * OTA任务开始时间超过有效期
      */
     public final static String OTA_TASK_START_DATE_ERROR = "OTA_TASK_START_DATE_ERROR";

     /**
      * OTA任务开始时间超过有效期
      */
     public final static String OTA_TASK_START_DATE_TOO_EARLY = "OTA_TASK_START_DATE_TOO_EARLY";

     /**
      * OTA任务结束时间超过有效期
      */
     public final static String OTA_TASK_END_DATE_ERROR = "OTA_TASK_END_DATE_ERROR";
     /***********************产线*************************/

     /**
      * 不能重复扫描
      */
     public static final String NOT_REPEAT_DETECTION = "NOT_REPEAT_DETECTION" ;

     /**
      * 车辆未被激活
      */
     public static final String VEHICLE_NO_ACTIVED = "VEHICLE_NO_ACTIVED" ;

     /**
      * 车辆已被销售
      */
     public static final String VEHICLE_SOLD = "VEHICLE_SOLD" ;

     /**
      * 车辆未激活，无需线上更换设备
      */
     public static final String VEHICLE_INACTIVE = "DEVICE_INACTIVE" ;

     /***********************产线*************************/

     /**
      * OTA任务已经执行不可删除
      */
     public final static String OTA_TASK_IS_USED_CAN_NOT_DEL = "OTA_TASK_IS_USED_CAN_NOT_DEL";

     /**
      * OTA任务已经执行不可更新
      */
     public final static String OTA_TASK_IS_USED_CAN_NOT_UPDATE = "OTA_TASK_IS_USED_CAN_NOT_UPDATE";

     /**
      * OTA任务名称重复
      */
     public final static String OTA_TASK_NAME_IS_NOT_UNIQUE = "OTA_TASK_NAME_IS_NOT_UNIQUE";

     /**
      * OTA版本已被使用不可删除
      */
     public final static String OTA_VERSION_IS_USED_CAN_NOT_DEL = "OTA_VERSION_IS_USED_CAN_NOT_DEL";

     /**
      * OTA升级状态异常
      */
     public final static String OTA_STATUS_IS_ABNORMAL = "OTA_STATUS_IS_ABNORMAL";

     /**
      * OTA升级状态异常
      */
     public final static String UPGRADE_VERSION_IS_USED = "UPGRADE_VERSION_IS_USED";

     /**
      * 车辆不属于4S店
      */
     public final static String VEHICLE_NOT_BELONG_TO_SHOP = "VEHICLE_NOT_BELONG_TO_SHOP";


     /**
      * 车辆未实名认证
      */
     public final static String VEHICLE_NOT_AUTHENTICATED = "VEHICLE_NOT_AUTHENTICATED";

     /**
      * 车辆实名认证中，无法解绑
      */
     public final static String VEHICLE_AUTHENTICATING = "VEHICLE_AUTHENTICATING";


     /**
      * 车辆未绑定该车主
      */
     public final static String VEHICLE_NOT_BIND_THE_OWNER = "VEHICLE_NOT_BIND_THE_OWNER";

     /**
      * 车辆已绑定车主
      */
     public final static String VEHICLE_BIND_OWNER = "VEHICLE_BIND_OWNER";

     /**
      * 接口TOKEN过期
      */
     public final static String API_TOKEN_EXPIRED ="API_TOKEN_EXPIRED";

     /**
      * 车卡绑定关系不存在
      */
     public final static String VEHICLE_CARD_RELATIONSHIP_NOT_EXISTS="VEHICLE_CARD_RELATIONSHIP_NOT_EXISTS";

     /**
      * 未找到需解绑的数据
      */
     public final static String NOT_FIND_DATA_TO_UNBIND="NOT_FIND_DATA_TO_UNBIND";

     /**
      * 非法请求
      */
     public final static String ILLEGAL_REQUEST = "ILLEGAL_REQUEST" ;

     /**
      * 时间最大跨度不能超过7天
      */
     public final static String TIME_MAX_SEVEN_DAYS = "TIME_MAX_SEVEN_DAYS" ;

     /**
      * 请切换车辆进行解绑
      */
     public final static String NEED_SWITCH_VEHICLE = "NEED_SWITCH_VEHICLE" ;

     /**
      * 信息错误，更换失败
      */
     public final static String CHANGE_FAILURE = "CHANGE_FAILURE" ;

     /**
      * 该时间段不可预约
      */
     public final static String APPOINTMENT_TIME_ERROR = "APPOINTMENT_TIME_ERROR";

     /**
      * 不能预约相同时间
      */
     public final static String SAME_APPOINTMENT_TIME = "SAME_APPOINTMENT_TIME";

     /**
      * 车主姓名不正确
      */
     public final static String OWNER_NAME_ERROR = "OWNER_NAME_ERROR" ;

     /**
      * 新手机号已被使用
      */
     public final static String NEW_MOBILE_USED = "NEW_MOBILE_USED" ;

     /**
      * 原手机号不正确
      */
     public final static String OLD_MOBILE_ERROR = "OLD_MOBILE_ERROR" ;

     /**
      * 设备故障
      */
     public final static String DEVICE_FAULT = "DEVICE_FAULT" ;

     /**
      * 车辆未解绑
      */
     public final static String VEHICLE_NO_UNBIND = "VEHICLE_NO_UNBIND" ;

     /**
      * 工单已发送
      */
     public final static String WORK_NUMBER_ISSEND = "WORK_NUMBER_ISSEND" ;

     /**
      * 车辆未与T-BOX绑定，该车未激活，请扫描TBOX的SN号
      */
     public final static String VEHICLE_NO_BIND_DEVICE = "VEHICLE_NO_BIND_DEVICE" ;

     /**
      * T-BOX已解绑，重新点火激活和检查流程
      */
     public final static String VEHICLE_UNBIND_DETECTION = "VEHICLE_UNBIND_DETECTION" ;

     /**
      * T-BOX已被记录为故障件，进行线下T-BOX换件后，重新点火进行激活和检查流程
      */
     public final static String MARK_FAULT_PART = "MARK_FAULT_PART" ;

     /**
      * 车辆已从产线下线，无法检测
      */
     public final static String VEHICLE_OFF_PRODUCTION_LINE = "VEHICLE_OFF_PRODUCTION_LINE" ;

     /**
      * 正在实名认证，无法解绑
      */
     public final static String IN_REAL_NAME_NOT_UNBIND = "IN_REAL_NAME_NOT_UNBIND" ;

     /**
      * 紧急联系人不允许设置自己
      */
     public final static String EMERGENCY_CONTACTS_NOT_ALLOWED = "EMERGENCY_CONTACTS_NOT_ALLOWED" ;


     /**
      * 图片验证码已失效
      */
     public static final String IMG_VERIFICATION_CODE_LOST_EFFICACY = "IMG_VERIFICATION_CODE_LOST_EFFICACY";

     /**
      * 图片验证码重复发送
      */
     public static final String IMG_VERIFICATION_CODE_REPEAT_SEND = "IMG_VERIFICATION_CODE_REPEAT_SEND" ;

     /**
      * 图片验证码错误
      */
     public static final String IMG_VERIFICATION_CODE_ERROR = "IMG_VERIFICATION_CODE_ERROR";

     /**
      * 新手机号码与原手机号码一致
      */
     public static final String NEW_MOBILE_SAME_OLD = "NEW_MOBILE_SAME_OLD" ;

     /**
      * 已参加活动，不能重复参加
      */
     public static final String ACTIVITY_JOINED = "ACTIVITY_JOINED";


     /**
      * 实名认证中，无法更换T-BOX
      */
     public static final String AUTHENTICATIONING_NO_CHANGE = "AUTHENTICATIONING_NO_CHANGE" ;

     /**
      * 不能重复实名认证
      */
     public static final String AUTHENTICATION_EXIST = "AUTHENTICATION_EXIST" ;

     /**
      * 实名认证失败（联通智网）
      */
     public static final String UNICOM_AUTHENTICATION_ERROR = "UNICOM_AUTHENTICATION_ERROR" ;

     /**
      * 查询失败（菱悦会）
      */
     public static final String LYH_QUERY_FAILED = "LYH_QUERY_FAILED";

     private static Map<String, String> messageMap = new ConcurrentHashMap<>();

     static {
         messageMap.put(SUCCESS, "");
         messageMap.put(PARAM_ERROR, "param.error");
         messageMap.put(ILLEGAL_PARAM, "illegal.param");
         messageMap.put(API_ERROR,"api.error") ;
         messageMap.put(NO_DATA, "no.data");
         messageMap.put(AUTH_DENIED, "auth.denied");
         messageMap.put(NO_LOGIN, "no.login");
         messageMap.put(SESSION_TIMEOUT, "session.timeout");
         messageMap.put(LOGIN_ERROR, "login.error");
         messageMap.put(USER_TIMEOUT, "user.timeout");
         messageMap.put(ACCOUNT_MODIFIED, "account.modified");
         messageMap.put(ACCOUNT_DISABLED, "account.disabled");
         messageMap.put(DEFAULT_VIN_CHANGED, "default.vin.changed");
         messageMap.put(USER_EXIST, "user.exist");
         messageMap.put(OLD_PASSWORD_ERROR, "old.password.error");
         messageMap.put(SERVICE_TIMEOUT, "service.timeout");
         messageMap.put(USER_DISABLED, "user.disabled");
         messageMap.put(RESET_PASSWORD, "reset.password") ;
         messageMap.put(UNIQUE_NAME_EXIST,"unique.name.exist") ;
         messageMap.put(UNIQUE_VEHICLE_MODEL_NAME_EXIST, "unique.vehicle.model.name.exist");
         messageMap.put(SUPPLIER_NAME_EXIST, "supplier.name.exist");
         messageMap.put(SUPPLIER_CODE_EXIST, "supplier.code.exist");
         messageMap.put(UNIQUE_CODE_EXIST, "unique.code.exist");
         messageMap.put(UNIQUE_IP_EXIST, "unique.ip.exist");
         messageMap.put(UPLOAD_FILE_MAX_SIZE_LIMIT_ERROR, "upload.file.max.size.limit.error");
         messageMap.put(UPLOAD_IDENTITY_MAX_SIZE_LIMIT, "upload.identity.max.size.limit");
         messageMap.put(BATCH_DELETE_ERROR, "batch.delete.error");
         messageMap.put(USED_DELETE_ERROR, "used.delete.error");
         messageMap.put(NO_USER, "no.user");
         messageMap.put(ORG_ADMIN_DELETE_ERROR, "org.admin.delete.error");
         messageMap.put(SHOP_USER_TO_FACTORY, "shop.user.to.factory");
         messageMap.put(SHOP_NOT_ALLOW_LOGIN_FACTORY,"shop.not.allow.login.factory") ;
         messageMap.put(FACTORY_NOT_ALLOW_LOGIN_SHOP,"factory.not.allow.login.shop") ;
         messageMap.put(FACTORY_USER_TO_SHOP, "factory.user.to.shop");
         messageMap.put(VERSION_NUMBER_EXIST, "version.number.exist");
         messageMap.put(MAX_PAGE_SIZE_ERROR, "max.page.size.error");
         messageMap.put(APPOINTMENT_TIME_NEED_LATER_THAN_TODAY, "appointment.time.need.later.than.today");
         messageMap.put(APPOINTMENT_TIME_NEED_LATER_THAN_EIGHT_CLOCK, "appointment.time.need.later.than.eight.clock");
         messageMap.put(DEVICE_CODE_INEXISTENCE, "device.code.inexistence");
         messageMap.put(DEVICE_CODE_BOUND_VEHICLE, "device.code.bound.vehicle");
         messageMap.put(VEHICLE_INEXISTENCE, "vehicle.inexistence");
         messageMap.put(OWNER_BOUND_VEHICLE, "owner.bound.vehicle");
         messageMap.put(VEHICLE_NO_SALE, "vehicle.no.sale");
         messageMap.put(OWNER_INEXISTENCE, "owner.inexistence");
         messageMap.put(VEHICLE_EXIST_BINDING, "vehicle.exist.binding");
         messageMap.put(VEHICLE_EXIST_BINDING_ERROR, "vehicle.exist.binding.error");
         messageMap.put(VEHICLE_INEXISTENCE_BINDING, "vehicle.inexistence.binding");
         messageMap.put(ROLE_NAME_NONCONFORMITY, "role.name.nonconformity");
         messageMap.put(ROLE_INEXISTENCE, "role.inexistence");
         messageMap.put(ADMIN_CAN_NOT_MODIFY, "admin.can.not.modify");
         messageMap.put(ROLE_NAME_EXIST, "role.name.exist");
         messageMap.put(ROLE_USED_ORG_CAN_NOT_MODIFY, "role.used.org.can.not.modify");
         messageMap.put(ORG_ADMIN_ROLE_CAN_NOT_DELETE, "org.admin.role.can.not.delete");
         messageMap.put(PARENT_ORG_NOT_EXIST,"parent.org.not.exist") ;
         messageMap.put(ROOT_USER_CAN_NOT_LOGIN, "root.user.can.not.login");
         messageMap.put(USER_CAN_NOT_LOGIN, "user.can.not.login");
         messageMap.put(CAN_NOT_CREATE_ROOT_USER, "can.not.create.root.user");
         messageMap.put(CAN_NOT_MODIFY_ROOT_INFO, "can.not.modify.root.info");
         messageMap.put(USER_ROLE_EXIST, "user.role.exist");
         messageMap.put(CAN_NOT_ADD_ORG_ADMIN_ROLE, "can.not.add.org.admin.role");
         messageMap.put(UPLOAD_ERROR, "upload.error");
         messageMap.put(DEVICE_CODE_CAN_NOT_NULL, "device.code.can.not.null");
         messageMap.put(ICCID_CAN_NOT_NULL, "iccid.can.not.null");
         messageMap.put(DEVICE_CODE_EXIST, "device.code.exist");
         messageMap.put(ICCID_EXIST, "iccid.exist");
         messageMap.put(ONLY_SUPPORT_EXCEL, "only.support.excel");
         messageMap.put(TEMPLATE_INEXISTENCE, "template.inexistence");
         messageMap.put(HTTP_REQUEST_ERROR, "http.request.error");
         messageMap.put(ORG_NAME_EXIST, "org.name.exist");
         messageMap.put(SHOPS_CAN_NOT_ADD_ORG, "shops.can.not.add.org");
         messageMap.put(AGENCY_HAS_SUBORDINATE_BODYS_DELETE_ERROR, "agency.has.subordinate.bodys.delete.error");
         messageMap.put(AGENCY_HAS_PEOPLE_DELETE_ERROR, "agency.has.people.delete.error");
         messageMap.put(AGENCY_HAS_ROLE_DELETE_ERROR, "agency.has.role.delete.error");
         messageMap.put(AGENCY_HAS_VEHICLES_DELETE_ERROR, "agency.has.vehicles.delete.error");
         messageMap.put(AGENCY_HAS_DEVICE_DELETE_ERROR, "agency.has.device.delete.error");
         messageMap.put(AGENCY_HAS_OWNERS_DELETE_ERROR, "agency.has.owners.delete.error");
         messageMap.put(DEVICE_ID_ERROR, "device.id.error");
         messageMap.put(MOBILE_INEXISTENCE, "mobile.inexistence");
         messageMap.put(MOBILE_REGISTERED, "mobile.registered");
         messageMap.put(CERT_CODE_REGISTERED, "cert.code.registered");
         messageMap.put(VERIFICATION_CODE_LOST_EFFICACY, "verification.code.lost.efficacy");
         messageMap.put(VERIFICATION_CODE_REPEAT_SEND,"verification.code.repeat.send") ;
         messageMap.put(VERIFICATION_CODE_ERROR, "verification.code.error");
         messageMap.put(REGISTER_FAILURE, "register.failure");
         messageMap.put(ACCOUNT_INEXISTENCE, "account.inexistence");
         messageMap.put(USER_INEXISTENCE, "user.inexistence");
         messageMap.put(UNREGISTERED_OF_4S,"unregistered.of.4s");
         messageMap.put(USER_UNREGISTERED, "user.unregistered");
         messageMap.put(ICCID_INEXISTENCE, "iccid.inexistence");
         messageMap.put(RNR_NOT_ENABLE, "rnr.not.enable");
         messageMap.put(CAN_NOT_REPEAT_CERTIFICATION, "can.not.repeat.certification");
         messageMap.put(CAN_NOT_REPEAT_REFUND, "can.not.repeat.refund");
         messageMap.put(WEIXIN_PAY_ERROR, "weixin.pay.error");
         messageMap.put(WEIXIN_REFUND_ERROR, "weixin.refund.error");
         messageMap.put(CAN_NOT_DELETE_ROOT_INFO, "can.not.delete.root.info");
         messageMap.put(NO_THIS_PAY_WAY, "no.this.pay.way");
         messageMap.put(NO_THIS_FLOW_PACKAGE, "no.this.flow.package");
         messageMap.put(TEMPORARY_DOES_NOT_SUPPORT_THIS_PAY_WAY, "temporary.does.not.support.this.pay.way");
         messageMap.put(ORDER_NUMBER_INEXISTENCE, "order.number.inexistence");
         messageMap.put(ORDER_NO_PAY, "order.no.pay");
         messageMap.put(ADMIN_ROLE_CAN_NOT_EDIT, "admin.role.can.not.edit");
         messageMap.put(VIN_NO_BINDING_DEVICE, "vin.no.binding.device");
         messageMap.put(ACCOUNT_NO_BINDING_VEHICLE,"account.no.binding.vehicle") ;
         messageMap.put(ORG_LEADER_CAN_NOT_DELETE, "org.leader.can.not.delete");
         messageMap.put(ORG_CONTACT_CAN_NOT_DELETE, "org.contact.can.not.delete");
         messageMap.put(PLAT_USER_CAN_NOT_ALLOCATION_SHOP, "plat.user.can.not.allocation.shop");
         messageMap.put(SHOP_USER_CAN_NOT_ALLOCATION_PLAT, "shop.user.can.not.allocation.plat");
         messageMap.put(VERSION_ALREADY_EXISTS, "version.already.exists");
         messageMap.put(VERSION_NAME_ALREADY_EXISTS, "version.name.already.exists");
         messageMap.put(PRODUCER_FULL_NAME_ALREADY_EXISTS, "producer.full.name.already.exists");
         messageMap.put(PRODUCER_SIMPLE_NAME_ALREADY_EXISTS, "producer.simple.name.already.exists");
         messageMap.put(DEALER_NEWS, "news.list.items.dealer.news");
         messageMap.put(MODELS_NEWS, "news.list.items.models.news");
         messageMap.put(MEDIA_EVALUATION, "news.list.items.media.evaluation");
         messageMap.put(FOCUS_TOPIC, "news.list.items.focus.topic");
         messageMap.put(PUBLIC_BENEFIT, "news.list.items.public.benefit");
         messageMap.put(ADD_OVERLIMIT, "add.overlimit");
         messageMap.put(VEHICLE_OFFLINE, "vehicle.offline");
         messageMap.put(SEND_MSG_ERROR, "send.msg.error");
         messageMap.put(NO_DETECTION_DATA, "no.detection.data");
         messageMap.put(FAULT_DETECTION_ERROR, "fault.detection.error");
         messageMap.put(FAULT_DETECTIONING, "fault.detectioning");
         messageMap.put(VIN_ERROR, "vin.error");
         messageMap.put(VIN_AWAKEN_MAX_TIMES_ERROR, "vin.awaken.max.times.error");
         messageMap.put(VIN_AWAKEN_NO_NEED_ERROR, "vin.awaken.no.need.error");
         messageMap.put(MOBILE_OR_IDCARD_EXISTS,"mobile.or.idcard.exists") ;
         messageMap.put(MOBILE_EXISTS,"mobile.exists") ;
         messageMap.put(IDCARD_EXISTS,"idcard.exists") ;
         messageMap.put(LONG_TIME_NO_LOGIN,"long.time.no.login") ;
         messageMap.put(ADMIN_ORG_TYPE_MATCH_ERROR,"admin.org.type.match.error");
         messageMap.put(LOGIN_FAILED_MULTIPLE_TIMES,"login.failed.multiple.times");
         messageMap.put(USER_BELONG_MORE_ORG,"user.belong.more.org") ;
         messageMap.put(USER_KICKED_OUT,"user.kicked.out") ;
         messageMap.put(REAL_NAME_SYSTEM_QUERY_ERROR,"real.name.system.query.error") ;
         messageMap.put(REAL_NAME_IN_CERTIFICATION,"real.name.in.certification") ;
         messageMap.put(REAL_NAME_NO_CERTIFICATION,"real.name.no.certification") ;
         messageMap.put(RECHARGE_INFORMATION_SUBMIT,"recharge.information.submit") ;
         messageMap.put(ORDER_RECHARGED,"order.recharged");
         messageMap.put(NEED_REAL_NAME_CERTIFICATION_FIRST,"need.real.name.certification.first");
         messageMap.put(RECHARGE_FAILED,"recharge.failed");
         messageMap.put(TIMESTAMP_TIMEOUT,"timestamp.timeout") ;
         messageMap.put(AGENTID_INEXISTENCE,"agentid.inexistence") ;
         messageMap.put(ENCRYPTED_STRING_INEXISTENCE,"encrypted.string.inexistence") ;
         messageMap.put(ENCRYPTED_STRING_INVALID,"encrypted.string.invalid") ;
         messageMap.put(AGENTID_INVALID,"agentid.invalid") ;
         messageMap.put(TIMESTAMP_INEXISTENCE,"timestamp.inexistence") ;
         messageMap.put(RECHARGE_CARD_IS_ACTIVATED,"recharge.card.is.activated") ;
         messageMap.put(RECHARGE_CARD_NUMBER_INEXISTENCE,"recharge.card.number.inexistence") ;
         messageMap.put(RECHARGE_CARD_PASSWORD_INEXISTENCE,"recharge.card.password.inexistence") ;
         messageMap.put(RECHARGE_CARD_INVALID,"recharge.card.invalid") ;
         messageMap.put(RECHARGE_CARD_PASSWORD_ERROR,"recharge.card.password.error") ;
         messageMap.put(RECHARGE_CARD_NUMBER_ERROR,"recharge.card.number.error") ;
         messageMap.put(RECHARGE_CARD_LOCKED,"recharge.card.locked") ;
         messageMap.put(RECHARGE_CARD_CAN_NOT_RECHARGE_THIS_ICCID,"recharge.card.can.not.recharge.this.iccid") ;
         messageMap.put(OTHER,"other");
         messageMap.put(ICCID_INACTIVE,"iccid.inactive") ;
         messageMap.put(PAYMENT_FAIL,"payment.fail") ;
         messageMap.put(ORDER_PAY_CLOSED,"order.pay.closed") ;
         messageMap.put(ORDER_REFUND,"order.refund") ;
         messageMap.put(ORDER_PAY_SUCCESS,"order.pay.success") ;
         messageMap.put(ORDER_CLOSED_ERROR,"order.closed.error") ;
         messageMap.put(NO_AUTHENTICATION,"no.authentication") ;
         messageMap.put(AUTHENTICATIONING,"authenticationing") ;
         messageMap.put(AUTHENTICATION_FAIL,"authentication.fail") ;
         messageMap.put(AUTHENTICATION_SUCCESS,"authentication.success") ;
         messageMap.put(USER_UNREGISTERED_REGISTERED,"user.unregistered.registered") ;
         messageMap.put(MOBILE_USED,"mobile.used") ;
         messageMap.put(ORDER_NOT_SUPPORT_REFUND,"order.not.support.refund") ;
         messageMap.put(QUERY_TIME_OUT,"query.time.out") ;
         messageMap.put(ACTIVITY_EXPIRED,"activity.expired") ;
         messageMap.put(ACTIVITY_DISCONTINUE,"activity.discontinue") ;
         messageMap.put(PARAM_VERSION_EXIST,"param.version.exist") ;
         messageMap.put(PARAM_VERSION_DEL_IS_USED,"param.version.del.is.used") ;
         messageMap.put(PARAM_VERSION_UPDATE_IS_USED,"param.version.update.is.used") ;
         messageMap.put(ADVERTISEMENT_NAME_EXIST,"advertisement.name.exist");
         messageMap.put(DEVICE_CHANGE_BANDING_ERROR,"device.change.banding.error") ;
         messageMap.put(ROLE_REFERENCED,"role.referenced") ;
         messageMap.put(LOGIN_ERROR_REMINDER,"login.error.reminder");
         messageMap.put(NO_PERMISSION_ERROR,"no.permission.error");
         messageMap.put(UNIQUE_SERIES_NAME_EXIST,"unique.series.name.exist");
         messageMap.put(ALREADY_CANCELED,"already.canceled");
         messageMap.put(PROCESSING_APPOINTMENT_EXIST,"processing.appointment.exist");
         messageMap.put(THIRD_PARTY_INTERFACE,"third.party.interface");
         messageMap.put(DATA_SUBMIT_ERROR,"data.submit.error");
         messageMap.put(UNIQUE_SUPPLIER_NAME_EXIST, "unique.supplier.name.exist");
         messageMap.put(CONTENT_EXCEEDED, "content.exceeded");
         messageMap.put(DATA_SUBMIT_FAILED, "data.submit.failed");
         messageMap.put(ALARM_PROPERTY_CODE_EXIST, "alarm.property.code.exist");
         messageMap.put(CERT_CODE_ERROR, "cert.code.error");
         messageMap.put(PLATE_NO_BIND_ERROR, "plate.no.bind.error");
         messageMap.put(ALARM_PARAMETER_VERSION_EXIST, "alarm.parameter.version.exist");
         messageMap.put(ALARM_PARAMETER_VERSION_IS_USED, "alarm.parameter.version.is.used");
         messageMap.put(ALARM_PARAMETER_VERSION_IS_DISABLE, "alarm.parameter.version.is.disable");
         messageMap.put(ALARM_EXCEL_VIN_NOT_EXIST, "alarm.excel.vin.not.exist");
         messageMap.put(ALARM_EXCEL_DUPLICATED_VIN, "alarm.excel.duplicated.vin");
         messageMap.put(ALARM_EXCEL_VIN_NOT_MATCH_SERIES, "alarm.excel.vin.not.match.series");
         messageMap.put(ALARM_EXCEL_VIN_NOT_MATCH_YEAR, "alarm.excel.vin.not.match.year");
         messageMap.put(ALARM_EXCEL_VIN_NOT_MATCH_MODEL, "alarm.excel.vin.not.match.model");
         messageMap.put(OTA_EXCEL_VIN_NOT_EXIST, "ota.excel.vin.not.exist");
         messageMap.put(OTA_EXCEL_DUPLICATED_VIN, "ota.excel.duplicated.vin");
         messageMap.put(OTA_EXCEL_VIN_NOT_MATCH_SERIES, "ota.excel.vin.not.match.series");
         messageMap.put(OTA_EXCEL_VIN_NOT_MATCH_YEAR, "ota.excel.vin.not.match.year");
         messageMap.put(OTA_EXCEL_VIN_NOT_MATCH_MODEL, "ota.excel.vin.not.match.model");
         messageMap.put(OTA_EXCEL_VIN_NOT_BIND_DEVICE, "ota.excel.vin.not.bind.device");
         messageMap.put(OTA_EXCEL_VIN_NOT_MATCH_PROTOCOL, "ota.excel.vin.not.match.protocol");
         messageMap.put(OTA_EXCEL_VIN_NOT_MATCH_SUPPLIER, "ota.excel.vin.not.match.supplier");
         messageMap.put(OTA_EXCEL_VIN_VEHICLE_ABNORMAL, "ota.excel.vin.vehicle.abnormal");
         messageMap.put(DEVICE_TBOX_PARAM_SETTING_VIN_NOT_EXIST, "device.tbox.param.setting.vin.not.exist");
         messageMap.put(DEVICE_TBOX_PARAM_SETTING_DEVICE_NOT_MATCH_SUPPLIER, "device.tbox.param.setting.device.not.match.supplier");
         messageMap.put(DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_SERIES, "device.tbox.param.setting.vin.not.match.series");
         messageMap.put(DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_YEAR, "device.tbox.param.setting.vin.not.match.year");
         messageMap.put(DEVICE_TBOX_PARAM_SETTING_VIN_NOT_MATCH_MODEL, "device.tbox.param.setting.vin.not.match.model");
         messageMap.put(DEVICE_TBOX_PARAM_SETTING_EXCEL_DUPLICATED_VIN, "device.tbox.param.setting.excel.duplicated.vin");
         messageMap.put(NEWS_NOT_UPDATABLE, "news.not.updatable");
         messageMap.put(OWNER_VEHICLE_NOT_EXIST, "owner.vehicle.not.exist");
         messageMap.put(OWNER_PLANT_NO_IS_SAME, "owner.plant.no.is.same");
         messageMap.put(PROTOCOL_UPDATE_IS_USED, "protocol.update.is.used");
         messageMap.put(PROTOCOL_DEL_IS_USED, "protocol.del.is.used");
         messageMap.put(OTA_FILE_NAME_NOT_UNIQUE, "ota.file.name.not.unique");
         messageMap.put(OTA_VERSION_NAME_NOT_UNIQUE, "ota.version.name.not.unique");
         messageMap.put(OTA_FILE_IS_ABANDON, "ota.file.is.abandon");
         messageMap.put(OTA_TASK_START_DATE_ERROR, "ota.task.start.date.error");
         messageMap.put(OTA_TASK_START_DATE_TOO_EARLY, "ota.task.start.date.too.early");
         messageMap.put(OTA_TASK_END_DATE_ERROR, "ota.task.end.date.error");
         messageMap.put(NOT_REPEAT_DETECTION,"not.repeat.detection") ;
         messageMap.put(VEHICLE_NO_ACTIVED,"vehicle.no.actived") ;
         messageMap.put(VEHICLE_SOLD,"vehicle.sold") ;
         messageMap.put(VEHICLE_INACTIVE,"vehicle.inactive") ;
         messageMap.put(OTA_TASK_IS_USED_CAN_NOT_DEL, "ota.task.is.used.can.not.del");
         messageMap.put(OTA_TASK_IS_USED_CAN_NOT_UPDATE, "ota.task.is.used.can.not.update");
         messageMap.put(OTA_TASK_NAME_IS_NOT_UNIQUE, "ota.task.name.is.not.unique");
         messageMap.put(OTA_VERSION_IS_USED_CAN_NOT_DEL, "ota.version.is.used.can.not.del");
         messageMap.put(OTA_STATUS_IS_ABNORMAL, "ota.status.is.abnormal");
         messageMap.put(UPGRADE_VERSION_IS_USED, "upgrade.version.is.used");
         messageMap.put(VEHICLE_NOT_BELONG_TO_SHOP,"vehicle.not.belong.to.shop");
         messageMap.put(VEHICLE_NOT_AUTHENTICATED,"vehicle.not.authenticated");
         messageMap.put(VEHICLE_AUTHENTICATING,"vehicle.authenticating");
         messageMap.put(VEHICLE_NOT_BIND_THE_OWNER,"vehicle.not.bind.the.owner");
         messageMap.put(VEHICLE_BIND_OWNER,"vehicle.bind.owner");
         messageMap.put(API_TOKEN_EXPIRED,"api.token.expired");
         messageMap.put(VEHICLE_CARD_RELATIONSHIP_NOT_EXISTS,"vehicle.card.relationship.not.exists");
         messageMap.put(NOT_FIND_DATA_TO_UNBIND,"not.find.data.to.unbind");
         messageMap.put(ILLEGAL_REQUEST,"illegal.request") ;
         messageMap.put(TIME_MAX_SEVEN_DAYS,"time.max.seven.days") ;
         messageMap.put(NEED_SWITCH_VEHICLE,"need.switch.vehicle") ;
         messageMap.put(CHANGE_FAILURE,"change.failure") ;
         messageMap.put(APPOINTMENT_TIME_ERROR,"appointment.time.error") ;
         messageMap.put(SAME_APPOINTMENT_TIME,"same.appointment.time") ;
         messageMap.put(OWNER_NAME_ERROR, "owner.name.error") ;
         messageMap.put(NEW_MOBILE_USED, "new.mobile.used") ;
         messageMap.put(OLD_MOBILE_ERROR, "old.mobile.error") ;
         messageMap.put(DEVICE_FAULT,"device.fault") ;
         messageMap.put(VEHICLE_NO_UNBIND,"vehicle.no.unbind") ;
         messageMap.put(WORK_NUMBER_ISSEND,"work.number.issend") ;
         messageMap.put(VEHICLE_NO_BIND_DEVICE,"vehicle.no.bind.device") ;
         messageMap.put(VEHICLE_UNBIND_DETECTION,"vehicle.unbind.detection") ;
         messageMap.put(MARK_FAULT_PART,"mark.fault.part") ;
         messageMap.put(VEHICLE_OFF_PRODUCTION_LINE,"vehicle.off.production.line") ;
         messageMap.put(IN_REAL_NAME_NOT_UNBIND,"in.real.name.not.unbind") ;
         messageMap.put(EMERGENCY_CONTACTS_NOT_ALLOWED,"emergency.contacts.not.allowed");
         messageMap.put(IMG_VERIFICATION_CODE_LOST_EFFICACY,"img.verification.code.lost.efficacy") ;
         messageMap.put(IMG_VERIFICATION_CODE_REPEAT_SEND,"img.verification.code.repeat.send") ;
         messageMap.put(IMG_VERIFICATION_CODE_ERROR,"img.verification.code.error") ;
         messageMap.put(NEW_MOBILE_SAME_OLD,"new.mobile.same.old") ;
         messageMap.put(ACTIVITY_JOINED,"activity.joined") ;
         messageMap.put(VERSION_NAME_EXIST, "version.name.exist");
         messageMap.put(AUTHENTICATIONING_NO_CHANGE,"authenticationing.no.change") ;
         messageMap.put(AUTHENTICATION_EXIST,"authentication.exist") ;
         messageMap.put(UNICOM_AUTHENTICATION_ERROR,"unicom.authentication.error") ;
         messageMap.put(OTA_VERSION_IS_FORCE_ABANDON,"ota.version.is.force.abandon") ;
         messageMap.put(LYH_QUERY_FAILED,"lyh.query.failed") ;

     }

     public static String getMessage(String code) {
         return messageMap.get(code);
     }


 }
