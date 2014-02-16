package com.mobile.utilities.apps.copier.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobile.utilities.apps.copier.service.UserDataService;

@SuppressWarnings("serial")
public class PhoneDataFlushController extends HttpServlet {

	private UserDataService userDataService = new UserDataService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		userDataService.deleteAllUserDetails();
		resp.getWriter().println("Data Formatting Completed Successfully");
	}
}
