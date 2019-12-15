package com.zb.base.define;


import com.zb.base.i18n.I18NData;
import com.zb.base.message.Message;

/**
 * Created by Administrator on 2017/9/28.
 */
public class MessageDefine {
    public final static Message SUCCESS_SAVE = Message.getMessage(I18NData.getI18NData("COMMON.SAVE_SUCCESSED"));
    public final static Message SUCCESS_DELETE = Message.getMessage(I18NData.getI18NData("COMMON.DELETE_SUCCESSED"));
    public final static Message SUCCESS_UPDATE = Message.getMessage(I18NData.getI18NData("COMMON.UPDATE_SUCCESSED"));
    public final static Message SUCCESS_EXPORT = Message.getMessage(I18NData.getI18NData("COMMON.EXPORT_SUCCESSED"));
}
