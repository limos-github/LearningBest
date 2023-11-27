package com.limo.lb.apply.rules.content;


import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class BaseConstant {

    public static final String WEIGHT_RANGE_START = "weightRangeStart";

    public static final Long ZEROL = 0L;

    public static final String MINUS_ONE_STR = "-1";

    public static final Integer THIRTY = 30;

    public static final Integer MESSAGE_EFFICIENT_DAYS = 1;
    public static final String VIP_CUSTOMER = "VIP_CUSTOMER";
    public static final String NATION_CUSTOMER = "NATION_CUSTOMER";
    public static final String OTHER_CUSTOMER = "OTHER_CUSTOMER";
    public static final String FOREIGN = "FOREIGN";
    public static final String DOMESTIC = "DOMESTIC";
    public static final String ZB = "zb";
    public static final String FJ = "fj";
    public static final String YZ = "驿站";

    public static final String NATION_CUSTOMER_STR = "600000";

    /**
     * 包牌号规则
     */
    public static final String F = "F";
    public static final String L = "L";
    public static final String E = "E";

    public static final String ONLINE_PAY = "ONLINE_PAY";
    public static final String OFFLINE_PAY = "OFFLINE_PAY";
    public static final String EAST_MALAYSIA = "SABAH,SARAWAK";
    public static final String CASH_BANK = "CASH_BANK";
    public static final String DEVICES_MOBILE = "DEVICES_MOBILE";
    public static final String LOGIN_WHITELIST = "LOGIN_WHITELIST";
    public static final String RECHARGE_BANK = "RECHARGE_BANK";
    public static final String PARAMETER = "parameter";
    public static final String MINUS = "-";
    public static final String PERCENT = "%";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String RETRY_NO = "retryNo";
    public static final String SCAN_TYPE = "scanType";

    public static final String ONE_HUNDRED = "100";

    public static final String EMPTY = "";

    public static final String EN = "EN";

    public static final String CN = "CN";

    public static final String ZH = "ZH";

    public static final String TH = "TH";

    public static final String MY = "MY";

    public static final String MYS = "MYS";

    public static final String BOXCODE = "boxCode";

    public static final String VERSION = "appVersion";

    public static final String MY_AERA_CODE = "60";
    public static final String CODE_ERROR = "100000028";

    public static final String UTF_8 = "UTF-8";
    public static final String EAST_EIGHTH_DISTRICT = "+8";


    public static final String DEFAULT_USE = "DEFAULT_USE";

    public static final String COUNT = "count";
    public static final String PWD_LETTER = "ABCDEFGHIJKMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
    public static final String PWD_NUMBER = "23456789";

    /**
     * 站点编码长度
     */
    public static final Integer STATION_CODE_LENGTH = 6;
    /**
     * 本地文件路径前缀
     */
    public static final String URL_PREFIX = "./file/";

    public static final Long LZERO = 0L;
    public static final Long LONE = 1L;
    public static final Long LTHREE = 3L;

    public static final String STR_ZERO = "0";

    public static final String STR_SIX = "6";
    public static final String STR_SIX_SIX = "66";

    public static final String STR_ZERO_FLOAT = "0.00";

    public static final Long MONTH_NUMBER = 31L;

    public static final Long YEAR_NUMBER = 366L;

    public static final Integer ORDER_STATUS_NONSUPPORT_CANCEL = 100000030;
    public static final Integer JTS_ORDER_NOT_FIND = 100000027;

    public static final Integer ZERO = 0;
    public static final Integer TEN_THOUSAND = 10000;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;
    public static final Integer THREE = 3;
    public static final Integer FOUR = 4;
    public static final Integer FIVE = 5;
    public static final Integer SIX = 6;
    public static final Integer SEVEN = 7;
    public static final Integer EIGHT = 8;
    public static final Integer NINE = 9;
    public static final Integer TEN = 10;
    public static final Integer ELEVEN = 11;
    public static final Integer TWO_FIVE = 250;
    public static final Integer FIFTY = 50;
    public static final Integer M = 1024;

    public static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka shi

    public static final Integer SIXTY_FOUR = 64;

    public static final String AUTH_TOKEN = "authToken";

    public static final String LOGIN_USER = "LOGIN_USER";
    public static final String SYS_CODE = "sysCode";

    public static final String MDC_USER_INFO = "USER_INFO";

    public static final String MDC_LANGUAGE = "MDC_LANGUAGE";

    public static final String CAMERA = "CAMERA";

    public static final String PAYMENT = "PAYMENT";

    public static final String ADMIN = "ADMIN";

    public static final String START = "****";

    public static final String STATION_CODE = "stationCode";

    public static final String ZERO_STR = "000";

    public static final String ID = "id";

    public static final String PARAM_SIGN = "[X]";

    public static final String MY_RT = "MYRT";

    public static final String ORDER_GOT = "GOT";
    /**
     * 校验开关 开
     */
    public static final String ON = "ON";
    /**
     * 校验开关 关
     */
    public static final String OFF = "OFF";

    /**
     * addThUserCode
     */
    public static final String ADD_USER_CODE = "addUserCode";

    public static final String ADD_ORDER_NO_ID = "orderNo";

    public static final String[] DUPLICATE_REMOVAL_BEANS = {"com.yl.platform.handler.GlobalExceptionHandler", "com.yl.platform.config.SwaggerConfig", "RedisSpringDataConfig"};

    /**
     * 寄件码key
     */
    public static final String SENDER_CODE = "senderCode";
    /**
     * 用户编码 ID
     */
    public static final String GOODS_TYPE = "goodsType";
    public static final String KIND = "kind";
    public static final String ORDER_STATUS = "orderStatus";
    public static final String SETTLEMENT_TYPE = "settlementType";
    public static final String PAYMENT_STATUS = "paymentStatus";
    public static final String IS_RECEIPT = "isReceipt";
    public static final String EXPRESS_TYPE = "expressType";
    public static final String TRANSIT_CLAUSE = "transitClause";
    public static final String TRANSPORT_PURPOSE = "transportPurpose";
    public static final String OFFER_FEE_FLAG = "offerFeeFlag";
    public static final String SITE_TYPE = "siteType";
    public static final String ADDRESS_TYPE = "addressType";

    public static final String FAIL = "fail";
    public static final String SUCCESS = "success";

    public static final long DIGEST = 30;

    public static final Integer TWO_HUNDRED = 200;

    public static final Integer ONE_HUNDRED_NUM = 100;

    public static final Integer TWO_THOUSAND = 600;

    public static final Integer ONE_THOUSAND = 1000;

    public static final String[] INCLUDE_BEANS = {"com.yl.tmp.common.handler.GlobalExceptionHandler", "com.yl.tmp.my.nuang.config.TmpSwaggerConfig", "com.yl.platform.redis.config.RedisSpringDataConfig"};

    //    public static final String[] EXCLUDE_BEANS = {"om.yl.platform.config.SwaggerConfig", "com.yl.tmp.common.handler.GlobalExceptionHandler"};
    public static final String[] EXCLUDE_BEANS = {"com.yl.platform.handler.GlobalExceptionHandler", "com.yl.platform.config.SwaggerConfig", "RedisSpringDataConfig"};

    public static final String UID = "upsUser";
    /**
     * 超级管理员用户标记
     */
    public static final Integer SUPER_USER_FLG = 1;

    public static final String CANCEL_ORDER = "CANCEL_ORDER";

    public static final String GOT = "GOT";

    public static final String MYS_BOX_GEO = "mysBoxGeo";

    public static final String MYS_SITE_GEO = "mysSiteGeo";

    /**
     * 各个用户信息的标识
     */
    public static final String STATION_APP = "station_app";

    public static final String WEB = "web";

    public static final String H5 = "H5";

    public static final String JTP = "JTP";

    public static final String SENSITIVE_WORDS = "sensitiveWords";

    public static final String SPACE = " ";

    public static final String EMPTY_SPACE = "";

    public static final Integer ONE_HUNDRED_AND_EIGHTY = 180;

    public static final BigDecimal MAX_LATITUDE = new BigDecimal(90);
    //redis的经纬度有效范围
    public static final BigDecimal EFFECTIVE_LATITUDE = new BigDecimal(85.05112878);
    public static final String EFFECTIVE_LATITUDE_STR = "85.05112878";


    public static final String FROM_APP = "来自柜机端的操作";

    public static final char[] X36 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static final char CHAR_ZERO = '0';

    public static final String CODE = "[Code]";

    public static final String PASSWORD = "[Password]";

    public static final String SYS_GENERATE = "系统生成";

    public static final DateTimeFormatter FORMAT_YM = DateTimeFormatter.ofPattern("yyyy-MM");

    public static final String RM = "RM";

    public static final String PDF = ".pdf";

    public static final String FILE = "file";

    public static final String UPDATE = "修改";

    public static final String INIT_OPERATION = "初始设置";

    public static final String BEFORE_INIT_OPERATION_VALUE = "0,0";

    public static final String FORMAT = "format";

    public static final String JSON = "json";

    public static final String METHOD = "method";

    public static final String V = "v";

    public static final String V_DATA = "1.0";

    public static final String DATA = "data";

    public static final String APP_LOGIN = "app.login";
    public static final String CASH_PAYMENT = "pay.cashPayment";
    public static final String SCAN_RECORD_UPLOAD = "app.scanRecordUpload";
    public static final String APP_EDITWAYBILL = "app.editWaybill";
    public static final String APP_FIND_TRACK = "app.findTrack";
    // 2022.03.17 V1.1.0 寄件柜 付款二维码等
    public static final String ONLINE_SCAN_PAYMENT = "pay.onlineScanPayment";
    public static final String FIND_INVOICE_NO = "app.findInvoiceNo";

    public static final String PUSH_ORDER_PAY_JTS = "app.lockerPayRecord";

    public static final String CM = "cm";

    public static final String RIDE = "*";

    public static final int NOT_AREA_EACH = 100000048;

    public static final int AGENT_AREA_NOT_MATCH = 100000050;

    public static final int NET_SITE_NOT_MATCH = 100000051;
    public static final int CUSTOMER_NOT_FIND = 100000052;
    public static final int CUSTOMER_NOT_MATCH = 100000053;

    public static final int INVALID_STAFF = 100000043;

    public static final String PATH = "path";

    public static final String WAVE_lINE = "~";

    public static final String ZERO_ZERO = ".00";

    public static final String COMMA = ",";

    public static final String CASH_STATUS_PUSH = "现金状态推送";

    public static final String MALAY_NAME = "00339993";

    public static final String SEND_DEDUCTION_PUSH = "寄件扣除推送";

    public static final String BING_RECHARGE_PUSH = "绑定充值状态查询推送";

    public static final String NE = "NE";
    public static final String MS = "MS";

    public static final String PHONE = "15589898989";

    public static final String TRACE_ID = "traceId";
    /**
     * 调整类目
     */
    public static final String ADJUST_CATEGORY = "ADJUST_CATEGORY";

    public static final String DINGTALK_TITLE = "定时任务报错";

    public static final Long DELAY_QUEUE_MAX_MILLIS = 30 * 24 * 60 * 1000L;

    public static final String BOX_MODULE = "BOX_MODULE";

    public static final String BOX_MOVE = "BOX_MOVE";

    public static final String TODAY_R_SCAN = "todayRScan";
    public static final String TODAY_P_SCAN = "todayPScan";
    public static final String SEVEN_R_SCAN = "sevenRScan";
    public static final String SEVEN_P_SCAN = "sevenPScan";

    public static final String TODAY_SR_SCAN = "todaySRScan";
    public static final String SEVEN_SR_SCAN = "sevenSRScan";


    public static final String APPLY_SUCCESS = "申请成功";

    public static final String Void_Invoice = "Void Invoice";

    public static final String Void_Waybill = "Void Waybill";

    /**
     * 在线充值
     */
    public static final String EXPECTION_PREFIX = "1";

    public static final String RECHARGE_RETRY_STRATEGY_CODE = "RECHARGE_RETRY";
    public static final String RECHARGE_RETRY_STRATEGY_KEY = "RETRY";
    public static final String MQ_DELAY = "MQ_DELAY";
    public static final String RECEIPT = "RECEIPT";
    public static final String OPERATING_PLATFORM_FACADE = "operatingPlatformFacade";
    public static final String OPERATING_PLATFORM_SEND_PUSH = "sendPush";
    public static final String OPERATING_PLATFORM_BAGGING = "bagging";
    public static final String OPERATING_PLATFORM_QUESTION_SCAN_UPLOAD = "questionScanUpload";
    public static final String RECHARGE_BANK_BIC_CODE = "BANK_BIC_CODE";

    public static final String BANKCARD_BIND = "绑定银行卡duitnowConsentRegistrationDecoupled";

    public static final String BANKCARD_RECHARGE = "在线充值duitNowAutoDebitDTO";

    public static final String BANKCARD_RECHARGE_QUERY = "交易查询transactionInquiryStatus";

    public static final String BANKCARD_STATUS_QUERY = "查询充值ID状态duitNowConsentEnquiry";

    public static final String BANKCARD_CANCEL = "取消绑定银行卡duitnowConsentTermination";

    public static final String RECHARGE_STATUS_RETYR_FAIL = "retry_query_failed";

    public static final String RECHARGE_STATUS_RETYR_SUCC = "retry_query_success";

    public static final String AM_BANK_TOKEN = "amBankToken";

    public static final String TOKEN_TYPE = "token_type";

    public static final String ACCESS_TOKEN = "access_token";
    public static final String B2B = "b2b";
    public static final String TOP_UP_SCP = "Top Up SCP";
    public static final String TXN_AMOUNT = "txn_amount";
    public static final String MODE = "mode";
    public static final String SIGNATURE = "signature";
    public static final String SELLER_ID = "seller_id";
    public static final String SELLER_ORDER_NO = "seller_order_no";
    public static final String DROP = ".";
    public static final String CN_COMMA = ", ";
    public static final String GATEWAY_TXN_TIME = "gateway_txn_time";
    public static final String GATEWAY_TXN_REF = "gateway_txn_ref";
    public static final String PAYMENT_STATUS_ = "payment_status";
    public static final String BANK_CODE = "bank_code";
    public static final String BUYER_NAME = "buyer_name";
    public static final String TXN_CODE = "txn_code";
    public static final String TXN_MESSAGE = "txn_message";
    public static final String EXTRA_INFO = "extra_info";
    public static final String TXN_CURRENT = "txn_currency";
    public static final String FPX_DETAILS = "fpx_details";
    public static final String EQ = "=";
    public static final String AND = "&";
}
