package hy.entity;

import java.util.List;

public class Order {
	Integer id;
	String no;
	Double price;
	Integer userId;
	List<Item>items;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> itmes) {
		this.items = itmes;
	}
	public Order(Integer id, String no, Double price, Integer userId, List<Item> itmes) {
		this.id = id;
		this.no = no;
		this.price = price;
		this.userId = userId;
		this.items = itmes;
	}
	public Order(){
		
	}
}