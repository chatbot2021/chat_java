package com.jcg.examples.delegate;

import java.sql.SQLException;

import com.jcg.examples.service.UserService;
import com.jcg.examples.viewBean.UserDetailsBean;

public class LoginDelegate
{
		private UserService userService;

		public UserService getUserService()
		{
				return this.userService;
		}

		public void setUserService(UserService userService)
		{
				this.userService = userService;
		}

		public boolean isValidUser(String username, String password, String linkToken, int otp) throws SQLException
    {
		    return userService.isValidUser(username, password, linkToken, otp);
    }

		public UserDetailsBean fetchUserDetails(String username) throws SQLException {
			// TODO Auto-generated method stub
			return userService.fetchUserDetails(username);
		}

		
}
