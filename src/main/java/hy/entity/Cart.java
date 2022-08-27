package hy.entity;

//层与层之间传递的对象，叫TO
public class Cart {
	/*User是persistant object，也就是po对象，一般用于dao层，
	 * Cart没有对应数据库中的某张表，属于view和service之间使用的一种对象，
	 * 叫value object,也就是vo对象*/
	Integer id;
	Integer userId;
	Integer productId;
	Integer num;
	String productName;
	Double price;//单价
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Cart(){
		
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", num=" + num + ", productName=" + productName
				+ ", price=" + price + "]";
	}
	
	
	
}
