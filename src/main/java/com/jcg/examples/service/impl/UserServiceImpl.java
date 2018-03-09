package com.jcg.examples.service.impl;

import java.sql.SQLException;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.service.UserService;
import com.jcg.examples.viewBean.UserDetailsBean;

public class UserServiceImpl implements UserService
{

		private UserDao userDao;

		public UserDao getUserDao()
		{
				return this.userDao;
		}

		public void setUserDao(UserDao userDao)
		{
				this.userDao = userDao;
		}

		@Override
		public boolean isValidUser(String username, String password, String linkToken, int otp) throws SQLException
		{
				return userDao.isValidUser(username, password, linkToken, otp);
		}

		@Override
		public UserDetailsBean fetchUserDetails(String username) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.fetchUserDetails(username);
		}

}
