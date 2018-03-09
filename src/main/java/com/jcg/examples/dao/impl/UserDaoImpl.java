package com.jcg.examples.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.viewBean.Transactions;
import com.jcg.examples.viewBean.UserDetailsBean;


/**
 * @author CENTAUR
 */
public class UserDaoImpl implements UserDao
{

		DataSource dataSource;

		public DataSource getDataSource()
		{
				return this.dataSource;
		}

		public void setDataSource(DataSource dataSource)
		{
				this.dataSource = dataSource;
		}

		@Override
		public boolean isValidUser(String username, String password, String linkToken, int otp) throws SQLException
		{
				String query = "Select count(1) from chat_user where username = ? and password = ?";
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next())
					if(resultSet.getInt(1) > 0 && null!=linkToken) {
						String updateQuery = "update chat_user set auth_code="+otp+" where username =? and password = ?";
						PreparedStatement pstmtupdate = dataSource.getConnection().prepareStatement(updateQuery);
						pstmtupdate.setString(1, username);
						pstmtupdate.setString(2, password);
						pstmtupdate.executeUpdate();
						pstmtupdate.close();
						return true;
						
					}
					else {
						return false;
					}
				else
						return false;
		}

		@Override
		public UserDetailsBean fetchUserDetails(String username) throws SQLException {
			// TODO Auto-generated method stub
			UserDetailsBean userDetails = new UserDetailsBean();
			ArrayList<Transactions> tranList = new ArrayList<Transactions>();
			String olbId = null;
			String olbIdQuery = "select olb_id from chat_user where username=?";
			PreparedStatement olbpstmt = dataSource.getConnection().prepareStatement(olbIdQuery);
			olbpstmt.setString(1, username);
			ResultSet olbResultSet = olbpstmt.executeQuery();
			while(olbResultSet.next()) {
				olbId = olbResultSet.getString("olb_id");
			}
			
			String balanceQuery = "select available_bal  from account_balance where olb_id=? and acct_type='Checking'";
			PreparedStatement balpstmt = dataSource.getConnection().prepareStatement(balanceQuery);
			balpstmt.setString(1, olbId);
			ResultSet balResultSet = balpstmt.executeQuery();
			while(balResultSet.next()) {
				userDetails.setAccountBal(balResultSet.getString("available_bal"));
			}
			
			String transactionQuery = " select date,tran_desc,tran_amount from transaction_details where olb_id=?";
			PreparedStatement tranpstmt = dataSource.getConnection().prepareStatement(transactionQuery);
			tranpstmt.setString(1, olbId);
			ResultSet tranResultSet = tranpstmt.executeQuery();
			while(tranResultSet.next()) {
				Transactions tran = new Transactions();
				tran.setTranDate(tranResultSet.getString("date"));
				tran.setTranDesc(tranResultSet.getString("tran_desc"));
				tran.setTranAmount(tranResultSet.getString("tran_amount"));
				tranList.add(tran);
			}
			userDetails.setTransactionDetails(tranList);
			return userDetails;
		}

}
