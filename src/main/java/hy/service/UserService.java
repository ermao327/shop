package hy.service;

import hy.entity.User;
import hy.exception.ServiceException;

public interface UserService {
	public User login(User user) throws ServiceException;

	public void regist(User u)throws ServiceException;
}
