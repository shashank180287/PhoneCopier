package com.mobile.utilities.apps.copier.service;

import com.google.appengine.api.datastore.Text;
import com.mobile.utilities.apps.copier.db.entity.UserDetails;
import com.mobile.utilities.apps.copier.db.manager.DBOperationManager;

public class UserDataService {
	
	private DBOperationManager dbOperationManager;
	
	public UserDataService() {
		dbOperationManager = new DBOperationManager();
	}
	
	public void storeUserContactsList(String mobileNumber, String contactsList){
		UserDetails user = dbOperationManager.findUserDetailsByMDN(mobileNumber);
		if(user==null){
			user = new UserDetails();
			user.setMobileNumber(mobileNumber);
		}
		user.setContacts(new Text(contactsList));
		dbOperationManager.saveOrUpdateUserDetails(user);
	}
	
	public void appendUserContactsList(String mobileNumber, String contactsList){
		if(contactsList!=null && contactsList.length()>2){
			UserDetails user = dbOperationManager.findUserDetailsByMDN(mobileNumber);
			if(user==null){
				user = new UserDetails();
				user.setMobileNumber(mobileNumber);
			}
			String existingContactList = user.getContacts()!=null? user.getContacts().getValue():null;
			contactsList = (existingContactList!=null && existingContactList.contains("]"))? existingContactList.substring(0, existingContactList.length()-1)+","+contactsList.substring(1):contactsList;
			user.setContacts(new Text(contactsList));
			dbOperationManager.saveOrUpdateUserDetails(user);
		}
	}
	
	public UserDetails fetchUserDetails(String mobileNumber){
		return dbOperationManager.findUserDetailsByMDN(mobileNumber);
	}
	
	public void deleteAllUserDetails() {
		dbOperationManager.flushAllUserDetails();
	}
}
