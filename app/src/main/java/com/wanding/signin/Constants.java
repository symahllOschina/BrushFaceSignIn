package com.wanding.signin;

/**
 *  常量帮助类
 */
public class Constants {


    /**
     * 支付类型选择
     * 微信 = "010"，
     * 支付宝 = "020"，
     * 银联二维码 = "060"，
     * 刷卡 = "040"，
     * 翼支付 = "050"
     */
    public static final String PAY_TYPE_010WX = "010";
    public static final String PAY_TYPE_020ALI = "020";
    public static final String PAY_TYPE_040BANK = "040";
    public static final String PAY_TYPE_050BEST = "050";
    public static final String PAY_TYPE_060UNIONPAY = "060";
    /**
     * API公共参数值
     */
    public static final String PAY_TYPE_WX = "WX";
    public static final String PAY_TYPE_ALI = "ALI";

    /**
     * API公共参数
     */
    public static final String PARAMS_NONCE_STR = "nonce_str";
    public static final String PARAMS_SIGN = "sign";
    public static final String PARAMS_SIGN_TYPE = "sign_type";
    public static final String PARAMS_TERM_TRANSACTION_SN = "term_transaction_sn";
    public static final String PARAMS_OPENID = "openid";
    public static final String PARAMS_FACE_CODE = "face_code";
    public static final String PARAMS_PAY_TYPE = "pay_type";
    public static final String PARAMS_BODY = "body";
    public static final String PARAMS_GOODS_TAG = "goods_tag";
    public static final String PARAMS_DETAIL = "detail";
    public static final String PARAMS_ATTACH = "attach";

    /**
     * 微信人脸SDK公共参数
     */
    public static final String RETURN_CODE = "return_code";
    public static final String RETURN_SUCCESS = "SUCCESS";
    public static final String RETURN_FAILE = "SUCCESS";
    public static final String RETURN_MSG = "return_msg";

    public static final String PARAMS_FACE_AUTHTYPE = "face_authtype";
    public static final String PARAMS_FACE_CODE_TYPE = "face_code_type";
    public static final String PARAMS_APPID = "appid";
    public static final String PARAMS_SUB_APPID = "sub_appid";
    public static final String PARAMS_MCH_ID = "mch_id";
    public static final String PARAMS_MCH_NO = "mch_no";
    public static final String PARAMS_MCH_NAME = "mch_name";
    public static final String PARAMS_TERM_NO = "term_no";
    public static final String PARAMS_SUB_MCH_ID = "sub_mch_id";
    public static final String PARAMS_STORE_ID = "store_id";
    public static final String PARAMS_AUTHINFO = "authinfo";
    public static final String PARAMS_AUTH_MODE = "auth_mode";
    public static final String PARAMS_OUT_TRADE_NO = "out_trade_no";
    public static final String PARAMS_TOTAL_FEE = "total_fee";
    public static final String PARAMS_TELEPHONE = "telephone";
    public static final String PARAMS_ASK_FACE_PERMIT = "ask_face_permit";
    public static final String PARAMS_ASK_RET_PAGE = "ask_ret_page";

    public static final String PARAMS_REPORT_ITEM = "item";
    public static final String PARAMS_REPORT_ITEMVALUE = "item_value";

    public static final String PARAMS_REPORT_MCH_ID = "mch_id";
    public static final String PARAMS_REPORT_SUT_MCH_ID = "sub_mch_id";
    public static final String PARAMS_REPORT_OUT_TRADE_NO = "out_trade_no";
    public static final String PARAMS_BANNER_STATE = "banner_state";

    /**
     * 佳博打印机参数
     */
    public static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    public static final int MESSAGE_UPDATE_PARAMETER = 0x009;

    /**
     *  佳博打印机连接状态
     *  PRINT_DEV_STATE_CONNECTED = "连接成功"
     *  PRINT_DEV_STATE_DISCONNECT = "未连接"
     */
    public static final String SHARE_DEV_FILENAME = "printing";
    public static final String SHARE_DEV_STATE_CONNECT_KEY = "connectSate";
    public static final String SHARE_DEV_STATE_CONNECTED = "connected";
    public static final String SHARE_DEV_STATE_DISCONNECT = "disconnect";
}
