/**  
 * Project Name:Dev  
 * File Name:CostListController.java  
 * Package Name:com.gqxie.controller  
 * Date:2017年6月24日上午9:47:04  
 * Copyright (c) 2017, xie.coder@gmail.com All Rights Reserved.  
 *  
*/

package com.gqxie.controller;
/**  
 * ClassName:CostListController <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年6月24日 上午9:47:04 <br/>  
 * @author   xie  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gqxie.dao.IUserDao;
import com.gqxie.entity.User;

@RequestMapping("/user")
@Controller
public class LoginController
{
    @Resource
    private IUserDao userDao;

    private Logger   logger = Logger.getLogger(LoginController.class);

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping("/login.do")
    public ModelAndView execute(HttpServletRequest request)
    {
        logger.warn("login.do begin...");
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDao.verify(account, password);
        String msg = null == user ? "用户名或密码错误" : "登录成功，欢迎你：" + user.getNickName();
        ModelAndView mav = new ModelAndView();
        String view = null == user ? "login" : "success";
        mav.setViewName(view);
        mav.getModel().put("msg", msg);
        logger.warn("login.do end...");
        return mav;
    }

    /**
     * 通过userID查询指定用户
     * @param request
     * @return
     */
    @RequestMapping("getUserByUserID")
    @ResponseBody
    private Object getUserByUserID(HttpServletRequest request)
    {
        Long userID = Long.valueOf(request.getParameter("userID"));
        User user = userDao.findByUserID(userID);
        return user;
    }

    /**
     * 查询所有用户
     * @param request
     * @return
     */
    @RequestMapping("findAll")
    @ResponseBody
    private Object findAll(HttpServletRequest request)
    {
        List<User> list = userDao.findAll();
        return list;
    }

}