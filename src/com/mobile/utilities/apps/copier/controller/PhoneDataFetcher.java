package com.mobile.utilities.apps.copier.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobile.utilities.apps.copier.db.entity.UserDetails;
import com.mobile.utilities.apps.copier.service.UserDataService;

@SuppressWarnings("serial")
public class PhoneDataFetcher extends HttpServlet {
	
	private UserDataService userDataService = new UserDataService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String mobileNumber = req.getParameter("mdn");
		UserDetails userDetail = userDataService.fetchUserDetails(mobileNumber);
		if(userDetail!=null)
			resp.getWriter().println(userDetail.toJSONString());
		//User details for 5555215554 with id UserDetails(5741031244955648) and contacts as [{"contact":"(976) 542-1134(H)","name":"Test1"},{"contact":"1 234-567-654(M),(079) 009-8765(H)","name":"Test1"}]
	}
}
