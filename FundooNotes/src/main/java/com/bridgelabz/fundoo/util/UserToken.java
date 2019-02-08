package com.bridgelabz.fundoo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.fundoo.model.User;

public class UserToken {
	
	private static String TOKEN_SECRET="gh2we43jue";
	public static String generateToken(long id) throws Exception
	{
		try {
			Algorithm algorithm= Algorithm.HMAC256(TOKEN_SECRET);
			String token=JWT.create()
							.withClaim("ID", id)
							.sign(algorithm);
			return token;		
		}
		catch(Exception exception)
		{
			throw new Exception("Token Not Generated");
		}
	}
	
	public static long tokenVerify(String token)throws Exception	
	{
		long userid;
		try {
			Verification verification=JWT.require(Algorithm.HMAC256(UserToken.TOKEN_SECRET));
			JWTVerifier jwtverifier=verification.build();
			DecodedJWT decodedjwt=jwtverifier.verify(token);
			Claim claim=decodedjwt.getClaim("ID");
			userid=claim.asLong();	
			System.out.println(userid);
		}
		catch(Exception exception)
		{
			throw new Exception("Token Not Verified");
		}
		
			return userid;
	}

	public String getBody(User user) throws Exception {
		return ("http://localhost:8080/verify/"+UserToken.generateToken(user.getId()));
	}
}
