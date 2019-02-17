package com.bridgelabz.fundoo.util;

import com.bridgelabz.fundoo.model.User;

public class Utility {
	
	public static String getBody(User user, String link) throws Exception {
		return "192.168.0.84:8080/"+link+UserToken.generateToken(user.getId());
	}
}
