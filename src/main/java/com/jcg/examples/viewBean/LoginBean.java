package com.jcg.examples.viewBean;

public class LoginBean
{
		private String username;

		private String password;
		
		private String token;
		
		private String state;
		
		private String redirectURI;
		
		
		
		

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getRedirectURI() {
			return redirectURI;
		}

		public void setRedirectURI(String redirectURI) {
			this.redirectURI = redirectURI;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getPassword()
		{
				return this.password;
		}

		public String getUsername()
		{
				return this.username;
		}

		public void setUsername(String username)
		{
				this.username = username;
		}

		public void setPassword(String password)
		{
				this.password = password;
		}


}
