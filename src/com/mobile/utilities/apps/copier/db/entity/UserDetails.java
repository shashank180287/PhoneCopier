package com.mobile.utilities.apps.copier.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@Entity
public class UserDetails {
	   
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Key key;

	    private String mobileNumber;

	    private Text contacts;

	    public Key getKey() {
	        return key;
	    }

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public Text getContacts() {
			return contacts;
		}

		public void setContacts(Text contacts) {
			this.contacts = contacts;
		}

		public String toJSONString() {
			return "{"
			+"\t\"mdn\" : \"" + mobileNumber+"\","
			+"\t\"contacts\" :" + (contacts!=null?contacts.getValue():null)
			+ "}";
		}

}
