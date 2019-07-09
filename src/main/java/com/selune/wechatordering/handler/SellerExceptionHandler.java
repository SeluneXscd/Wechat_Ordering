package com.selune.wechatordering.handler;

import com.selune.wechatordering.config.ProjectUrlConfig;
import com.selune.wechatordering.exception.SellerAuthorizeException;
import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.utils.ResultVOUtil;
import com.selune.wechatordering.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截异常
 *
 * @Author: Selune
 * @Date: 7/8/19 10:32 PM
 */

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // 拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat("/sell/seller/login"));
    }

    // 异常统一处理
    @ExceptionHandler(value = WeChatOrderException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN) // 定义返回HTTP状态码
    public ResultVO handlerWeChatOrderException(WeChatOrderException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
