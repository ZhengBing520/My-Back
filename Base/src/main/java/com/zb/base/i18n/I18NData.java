package com.zb.base.i18n;


import com.zb.base.utils.I18nUtil;

/**
 * Created by Administrator on 2017/7/26.
 */
public class I18NData {
    private String key;
    private Object[] params;

    public I18NData(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    /**
     * 根据参数获取i18n值
     * @param params
     * @return
     */
    public String getValue(Object ... params){
        if(key!=null){
            if(params==null||params.length==0){
                return I18nUtil.getMessage(key);
            }else{
                return I18nUtil.getMessage(key,params);
            }
        }else{
            return null;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static I18NData getI18NData(String key){
        return new I18NData(key);
    }
}
