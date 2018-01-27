package com.nnngu.exception;

/**
 * 重复秒杀异常，不需要我们手动去try catch
 * Created by nnngu
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
