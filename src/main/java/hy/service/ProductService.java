package hy.service;

import com.github.pagehelper.PageInfo;
import hy.entity.Cart;
import hy.entity.Product;
import hy.exception.ServiceException;

import java.util.List;

public interface ProductService {

	PageInfo<Product> findAllProduct(Integer pageNo) throws ServiceException;

	Product findById(Integer id) throws ServiceException;
	void addToCart(Integer userId, Integer productId, Double price) throws ServiceException;

	PageInfo<Cart> findCartByUserId(Integer userId, Integer pageNo)throws ServiceException;

	void modifyCart(Cart c) throws ServiceException;

	void deleteCart(Integer id, Integer productId) throws ServiceException;

	List<Cart> findAllCartByUserId(Integer userId) throws ServiceException;

	String createOrder(Integer UserId, Double price) throws ServiceException;


}
