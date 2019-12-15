package com.zb.base.exception;

/**
 * @version 1.0
 * @desc
 * @date 2019/5/5
 */
public class NotUniqueException extends BaseRuntimeException {

    public NotUniqueException(String message) {
        super(message);
    }

    public NotUniqueException(String message, String code) {
        super(message, code);
    }

    public NotUniqueException(Throwable e) {
        super(e);
    }

    public NotUniqueException(Throwable e, String code) {
        super(e, code);
    }
}
