package com.jcg.examples.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jcg.examples.delegate.LoginDelegate;
import com.jcg.examples.viewBean.LoginBean;
import com.jcg.examples.viewBean.Model;
import com.jcg.examples.viewBean.UserDetailsBean;


@Controller
public class LoginController
{
	private static Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginDelegate loginDelegate;

	


	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
	{
		ModelAndView model = new ModelAndView("login");
		//LoginBean loginBean = new LoginBean();
		loginBean.setToken(request.getParameter("account_linking_token"));
		System.out.println("loginBean"+loginBean.getToken());
		model.addObject("loginBean", loginBean);
		return model;
	}
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
	{
		String LinkToken=loginBean.getToken();//request.getParameter("account_linking_token");
		String alexa_state = loginBean.getState();
		System.out.println("alexa_state"+alexa_state);
		String FBFlag = "N";
		String AlexaFlag = "N";
		request.setAttribute("LinkToken", LinkToken);
		request.setAttribute("state", alexa_state);
		
		System.out.println("Linktoken"+LinkToken);
		System.out.println("uri"+request.getParameter("redirect_uri"));
		int otp = gen();
		if(null != LinkToken && LinkToken!="") {
			FBFlag = "Y";

		}
		
		if(null != alexa_state && alexa_state !=""){
			AlexaFlag = "Y";
		}
		request.setAttribute("FBFlag", FBFlag);
		request.setAttribute("AlexaFlag", AlexaFlag);
		ModelAndView model= null;
		try
		{
			boolean isValidUser = loginDelegate.isValidUser(loginBean.getUsername(), loginBean.getPassword(),LinkToken,otp);

			if(isValidUser)
			{
				System.out.println("User Login Successful");
				request.setAttribute("loggedInUser", loginBean.getUsername());
				request.setAttribute("authCode", otp);
				String url = loginBean.getRedirectURI()+"#state="+alexa_state+"&access_token="+otp+"&token_type=Bearer";
				request.setAttribute("url", url);
				System.out.println(AlexaFlag+url);
				model = new ModelAndView("welcome");
				if(FBFlag.equalsIgnoreCase("N") && AlexaFlag.equalsIgnoreCase("N")){
					UserDetailsBean userDetails = loginDelegate.fetchUserDetails(loginBean.getUsername());								
					model.addObject("userDetails", userDetails);
				}
			}
			else
			{
				model = new ModelAndView("login");
				request.setAttribute("message", "Invalid credentials!!");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value="/alexalogin",method=RequestMethod.GET)
	public ModelAndView alexaLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
	{
		ModelAndView model = new ModelAndView("login");
		loginBean.setState(request.getParameter("state"));
		loginBean.setRedirectURI(request.getParameter("redirect_uri"));
		System.out.println();
		System.out.println(request.getParameter("redirect_uri"));
		model.addObject("loginBean", loginBean);
		return model;
	}
	
	@RequestMapping(value="/loginTapco",method=RequestMethod.POST)
	public @ResponseBody String loginTapco(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String output = "";
		int otp = gen();
		
		try {
			boolean isValidUser = loginDelegate.isValidUser(username, password,"abc",otp);
			if(isValidUser)
			{
				output = String.valueOf(otp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return output;
	}
	
	
	@RequestMapping(value="/tapco",method=RequestMethod.GET)
	public ModelAndView tapco(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
	{
		ModelAndView model = new ModelAndView("tapco");
		
		return model;
	}
	
	@RequestMapping(value="/insite",method=RequestMethod.GET)
	public ModelAndView insite(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
	{
		ModelAndView model = new ModelAndView("insight");
		
		return model;
	}

	public static int gen() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	

	





}
