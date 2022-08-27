package hy.dao;

import hy.entity.Cart;
import hy.entity.Item;
import hy.entity.Order;
import hy.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

	List<Product> selectAll();

	Product selectById(Integer id);

	Cart selectItemFromCartByUserIdAndProductId(@Param("userid") Integer userId, @Param("productid") Integer productId);

	List<Cart> selectCartByUserId(Integer userId);

	void deleteCart(@Param("userId") Integer userId, @Param("productId") Integer productId);

	Integer insertOrder(Order o);

	void insertIntoCart(Cart c);

	void updateCart(Cart c);

	void insertIntoItem(Item i);

}
