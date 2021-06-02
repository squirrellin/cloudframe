package com.shaolin.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.shaolin.exception.errorEnum.BaseExceptionEnum;
import com.shaolin.util.ExceptionResult;

/**
 * @description: 自定义客户端全局降级熔断
 * @author: duanwei
 * @create: 2020-05-31 14:31
 **/
public class ClientBlockHandler {

    /***
     * 系统超时
     * @param blockException
     * @return
     */
    public static ExceptionResult handlerExceptionTimeOut(BlockException blockException) {
        return new ExceptionResult(BaseExceptionEnum.REQUEST_TIME_OUT.getCode(),BaseExceptionEnum.REQUEST_TIME_OUT.getMsg());
    }


    /***
     * 系统繁忙
     * @param blockException
     * @return
     */
    public static ExceptionResult handlerExceptionBusy(BlockException blockException) {
        return new ExceptionResult(BaseExceptionEnum.SYSTEM_BUSY.getCode(),BaseExceptionEnum.SYSTEM_BUSY.getMsg());
    }
}
