package com.mobile.utilities.apps.copier.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobile.utilities.apps.copier.service.UserDataService;

@SuppressWarnings("serial")
public class PhoneDataStoreController extends HttpServlet {
	
	private UserDataService userDataService = new UserDataService();
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		String mobileNumber = req.getParameter("mdn");
		Map<String, String> params = req.getParameterMap();
		for (String param : params.keySet()) {
			if(!param.equalsIgnoreCase("mdn")){
				switch (param) {
				case "contacts":
					String contacts = req.getParameter(param);
					userDataService.storeUserContactsList(mobileNumber, contacts);
					resp.getWriter().println("User Contact details updated successfully");
					break;

				default:
					break;
				}
			}
		}

	}
}
