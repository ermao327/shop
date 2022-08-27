package hy.controller;


import hy.constant.Constant;
import hy.entity.User;
import hy.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import hy.service.UserService;
import hy.util.AjaxResult;
import hy.util.CommonUtil;
import hy.util.CommonUtil.MyImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResult login(HttpServletRequest request,User u, HttpServletResponse response) {
		AjaxResult result = new AjaxResult();
		String code =  request.getParameter("code");
		String localCode = (String) request.getSession().getAttribute("code");
		if(localCode ==null || !localCode.equals(code)){
			result.setMsg("验证码错误");
			//return result;
		}

		System.out.println("controller:"+u);
		try {
			User user = userService.login(u);
			request.getSession().setAttribute(Constant.SESSION_USER, user);
			result.setSuccess(true);
		}catch (ServiceException e) {
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@GetMapping("/isLogin")
	public AjaxResult isLogin(HttpSession session){
		Object obj = session.getAttribute(Constant.SESSION_USER);
		return new AjaxResult(obj!=null,null,obj);
	}
	
	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("UserController.getCode()");
		MyImage m = CommonUtil.getImage(null, 4, true, true);
		System.out.println("code=" + m.getCode());
		request.getSession().setAttribute("code", m.getCode());
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(m.getImage(), "JPEG", responseOutputStream);
		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
	}
	
	//退出登录
	@RequestMapping("/logout")
	@ResponseBody
	public AjaxResult loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new AjaxResult(true,null,null);
	}	
	
	
	@RequestMapping("/regist")		
	public String regist(HttpServletRequest request,User u, HttpServletResponse response) {
		String code =  request.getParameter("code");
		String localCode = (String) request.getSession().getAttribute("code");
		if(localCode ==null || !localCode.equals(code)){
			System.out.println("验证码不正确");
			return "/showLogin.do";
		}
		System.out.println("userController:"+u);
		try {
			userService.regist(u);
			request.getSession().setAttribute(Constant.SESSION_USER, u);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
		return "/showLogin";
	}
}
