package hy.service.impl;

import hy.dao.UserDao;
import hy.entity.User;
import hy.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hy.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    UserDao dao;
	@Override
	public User login(User user) throws ServiceException {
		User u = dao.selectByLoginnameAndPassword(user);
		if(u == null){
			throw new ServiceException("用户名/密码错误");
		}
		return u;
	}
	@Override
	public void regist(User u) throws ServiceException {
		User user = dao.selectByLoginnameAndPassword(u);
		if(user == null){
			dao.insertUser(u);
		}else{
			throw new ServiceException("用户名已成功注册");
		}
	}
}
