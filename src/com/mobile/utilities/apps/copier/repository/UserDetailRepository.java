package com.mobile.utilities.apps.copier.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.mobile.utilities.apps.copier.db.entity.UserDetails;
import com.mobile.utilities.apps.copier.db.factory.DBAccessManagerFactory;

public class UserDetailRepository {

	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<UserDetails> find(String query, String[] paramName, Object[] paramValue) {
		try{
			entityManager = getEntityManager();
			Query queryObj = entityManager.createQuery(query);
			if(paramValue!=null)
				for (int i = 0; i < paramValue.length; i++) {
					queryObj.setParameter(paramName[i], paramValue[i]);
				}
			return queryObj.getResultList();
		}finally{
			closeEntityManager();
		}
	}
	
	public List<UserDetails> findAll() {
			return find("select user from UserDetails user", null, null);
	}
	
	public void saveOrUpdate(UserDetails userDetails) {
		try{
			List<UserDetails> userDetailsList = find("select user from UserDetails user where user.mobileNumber = :mdn", new String[]{"mdn"}, new Object[] {userDetails.getMobileNumber()});
			entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			if(userDetailsList!=null && userDetailsList.size()==1){
				userDetailsList.get(0).setContacts(userDetails.getContacts());
				entityManager.persist(userDetailsList.get(0));
			}else{
				entityManager.persist(userDetails);
			}
			entityManager.getTransaction().commit();
		}finally{
			closeEntityManager();
		}
	}
	
	public void delete(UserDetails userDetails) {
		try{
			entityManager = getEntityManager();
			entityManager.clear();
			entityManager.remove(userDetails);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeEntityManager();
		}
	}
	
	public void flushAllData() {
		try{
			entityManager = getEntityManager();
			Query queryObj = entityManager.createQuery("select user from UserDetails user");
			@SuppressWarnings("unchecked")
			List<UserDetails> data = queryObj.getResultList();
			entityManager.clear();
			for (UserDetails useDetails : data) {
				entityManager.remove(useDetails);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeEntityManager();
		}
	}
	
	private void closeEntityManager() {
		entityManager.close();
	}

	private EntityManager getEntityManager(){
		EntityManagerFactory emf = DBAccessManagerFactory.getEntityManagerFactory();
		return emf.createEntityManager();
	}
}
