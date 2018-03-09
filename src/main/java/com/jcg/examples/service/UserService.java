/**
 *
 */
package com.jcg.examples.service;

import java.sql.SQLException;

import com.jcg.examples.viewBean.UserDetailsBean;

/**
 * @author CENTAUR
 *
 */
public interface UserService
{
		public boolean isValidUser(String username, String password, String linkToken, int otp) throws SQLException;

		public UserDetailsBean fetchUserDetails(String username) throws SQLException;
}
