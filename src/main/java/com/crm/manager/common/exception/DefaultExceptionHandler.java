package com.crm.manager.common.exception;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crm.manager.common.JsonResult;

/**
 * Description:自定义统一错误控制器
 *
 * @author Jin
 * @create 2017-04-09
 **/
@ControllerAdvice
public class DefaultExceptionHandler {


   /* *//**
     * 接口异常
     * @param e
     * @return
     *//*
    @ExceptionHandler({ApiException.class})
    @ResponseBody
    public JsonResult ApiException(ApiException e){
        return JsonResult.failure(e.getCode(), e.getMessage());
    }

    *//**
     * 系统异常
     * @param e
     * @return
     *//*
    @ExceptionHandler({SystemException.class})
    public ModelAndView  SystemException(Exception e,HttpServletRequest request,HttpServletResponse response){

            if (ControllerUtil.isAjaxRequest(request)) {
                ControllerUtil.renderErrorJson(e.getMessage(),response);
                return null;
            } else {
                return this.renderErrorView(500,"HTTP-Internal Server Error",e.getMessage());
            }
    }


    *//**
     * 统一错误系统
     * @param e
     * @return
     *//*
    @ExceptionHandler({NullPointerException.class})
    public ModelAndView DefaultException(Exception e,HttpServletRequest request,HttpServletResponse response) {
        if (ControllerUtil.isAjaxRequest(request)) {
              输出JSON 
            ControllerUtil.renderErrorJson(e.getMessage(),response);
            return null;
        } else {
            return this.renderErrorView(500,"HTTP-Internal Server Error",e.getMessage());
        }
    }

    *//**
     *访问异常
     *//*
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public ModelAndView authenticationException(Exception e,HttpServletRequest request, HttpServletResponse response) {
        if (ControllerUtil.isAjaxRequest(request)) {
              输出JSON 
            ControllerUtil.renderTimeoutJson("对不起，请登录后再访问！",response);
            return null;
        } else {
            return this.renderErrorView(401,"Authentication Failed",e.getMessage());
        }
    }

    *//**
     * 权限异常
     *//*
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ModelAndView authorizationException(Exception e,HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ControllerUtil.isAjaxRequest(request)) {
             输出JSON 
           ControllerUtil.renderErrorJson("对不起，您没有此权限",response);
           return null;
        } else {
            return this.renderErrorView(401,"Unauthorized",e.getMessage());
        }

    }

    private ModelAndView renderErrorView(Integer errorCode,String error,String message){
        ModelAndView view =new ModelAndView("error");
        view.addObject("status",errorCode);
        view.addObject("error",error);
        view.addObject("message",message);
        view.addObject("timestamp",new Date());
        return view;
    }*/

}
