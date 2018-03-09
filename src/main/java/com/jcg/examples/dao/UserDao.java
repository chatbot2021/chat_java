package com.jcg.examples.dao;

import java.sql.SQLException;

import com.jcg.examples.viewBean.UserDetailsBean;

/**
 * @author CENTAUR
 * This interface will be used to communicate with the
 * Database
 */
public interface UserDao
{
		public boolean isValidUser(String username, String password, String linkToken, int otp) throws SQLException;

		public UserDetailsBean fetchUserDetails(String username) throws SQLException;
}
