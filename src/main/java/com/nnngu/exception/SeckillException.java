package com.nnngu.exception;

/**
 *  秒杀基础的异常
 * Created by nnngu
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
