package hy.controller;

import com.github.pagehelper.PageInfo;
import hy.constant.Constant;
import hy.entity.Cart;
import hy.entity.Product;
import hy.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import hy.service.ProductService;
import hy.util.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService ps;
	@RequestMapping("/findAllProduct")
	@ResponseBody
	public AjaxResult findAllProduct(Integer pageNo, HttpSession session) {
		AjaxResult result = new AjaxResult(true, "查询成功", null);
		try {
			PageInfo<Product> info = ps.findAllProduct(pageNo);
			result.setObj(info);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/modifyCart")
	@ResponseBody
	public void modifyCart(HttpServletRequest request, Cart c)
	{
		System.out.println("modifyCart");

		User u  = (User) request.getSession().getAttribute(Constant.SESSION_USER);
		try{
			if(u==null){
				System.out.println("用户没登录");
			}else{
				c.setUserId(u.getId());
				ps.modifyCart(c);
			}
		}catch(Exception e){
			System.out.println("修改购物车失败");
		}
		
	}
	@RequestMapping("/deleteCart")
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) 
	{
		System.out.println("deleteCart");
		Integer productId = Integer.valueOf(request.getParameter("productId"));
		User u  = (User) request.getSession().getAttribute(Constant.SESSION_USER);
		try{
			if(u==null){
				System.out.println("用户没登录");
			}else{
				ps.deleteCart(u.getId(),productId);
			}
		}catch(Exception e){
			System.out.println("删除购物车失败");
		}
		
	}
	
	@RequestMapping("/deleteAllCart")
	public void deleteAllCart(HttpServletRequest request, HttpServletResponse response) 
	{
		System.out.println("deleteAllCart");
		User u  = (User) request.getSession().getAttribute(Constant.SESSION_USER);
		try{
			if(u==null){
				System.out.println("用户没登录");
			}else{
				ps.deleteCart(u.getId(),null);
			}
		}catch(Exception e){
			System.out.println("删除购物车失败");
		}
		
	}
	@PostMapping("/addCart")
	@ResponseBody
	public AjaxResult addCart(Product p,HttpSession session) {
		AjaxResult rs = new AjaxResult();
		User u  = (User) session.getAttribute(Constant.SESSION_USER);
		try{
			if(u==null){
				System.out.println("用户没登录");
				rs.setMsg("用户没登录");
				return rs;
			}
			ps.addToCart(u.getId(),p.getId(),p.getPrice());
		}catch(Exception e){
			System.out.println("添加购物车失败");
			rs.setMsg("添加购物车失败");
			return rs;
		}
		rs.setMsg("添加购物车成功");
		rs.setSuccess(true);
		return rs;
	}
	
	@RequestMapping("/findCart")
	@ResponseBody	
	public AjaxResult findCart(HttpServletRequest request)
	{
		AjaxResult result = new AjaxResult(true, "查询成功", null);
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		User u = (User)request.getSession().getAttribute(Constant.SESSION_USER);
		try {
			PageInfo<Cart> info = ps.findCartByUserId(u.getId(),pageNo);
			System.out.println("controller-cart:"+info);
			result.setObj(info);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/total")
	@ResponseBody	
	public AjaxResult findTotalPrice(HttpSession session)
	{
		User u = (User)session.getAttribute(Constant.SESSION_USER);
		Double totalPrice = 0.0;
		List<Cart>list = null;
		try {
			list = ps.findAllCartByUserId(u.getId());
			for (Cart cart : list) {
				totalPrice+=cart.getPrice();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AjaxResult rs = new AjaxResult(true,"查询成功",totalPrice);
		return rs;
	}
	
	@PostMapping("/createOrder")
	@ResponseBody
	public AjaxResult createOrder(HttpSession session,Double sumPrice) {
		User u  = (User) session.getAttribute(Constant.SESSION_USER);
		AjaxResult rs = new AjaxResult();
		try{
			if(u==null){
				System.out.println("用户没登录");
			}
			String orderId = ps.createOrder(u.getId(),sumPrice);
			session.setAttribute("no", orderId);
			System.out.println("创建订单成功");
			rs.setSuccess(true);
			rs.setMsg("创建订单成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("创建订单失败");
			rs.setMsg("创建订单失败");
		}
		return rs;
	}
	@GetMapping("/getOrder")
	@ResponseBody
	public AjaxResult getOrder(HttpSession session) {
		User u  = (User) session.getAttribute(Constant.SESSION_USER);
		AjaxResult rs = new AjaxResult();
		try{
			if(u==null){
				System.out.println("用户没登录");
				return rs;
			}
			rs.setSuccess(true);
			rs.setObj(session.getAttribute("no"));
			rs.setMsg("获取订单成功");
		}catch(Exception e){
			e.printStackTrace();
			rs.setMsg("获取订单失败");
		}
		return rs;
	}
}
