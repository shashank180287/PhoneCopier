package com.mobile.utilities.apps.copier.db.manager;

import java.util.List;

import com.mobile.utilities.apps.copier.db.entity.UserDetails;
import com.mobile.utilities.apps.copier.repository.UserDetailRepository;

public class DBOperationManager {

	private UserDetailRepository userDetailRepository;
	
	public DBOperationManager() {
		userDetailRepository = new UserDetailRepository();
	}
	
	public UserDetails findUserDetailsByMDN(String mobileNumber) {
		List<UserDetails> users = userDetailRepository.find("select user from UserDetails user where user.mobileNumber = :mdn", new String[]{"mdn"}, new Object[] {mobileNumber});
		if(users!=null && users.size()==1)
			return users.get(0);
		return null;
	}

	public void saveOrUpdateUserDetails(UserDetails userDetails) {
		userDetailRepository.saveOrUpdate(userDetails);		
	}

	public List<UserDetails> findAllUserDetails() {
		 return userDetailRepository.findAll();		
	}
	
	public void deleteUserDetail(UserDetails userDetails) {
		userDetailRepository.delete(userDetails);
	}

	public void flushAllUserDetails() {
		userDetailRepository.flushAllData();
	}
}
