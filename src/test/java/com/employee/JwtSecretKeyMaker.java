package com.employee;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

public class JwtSecretKeyMaker {

	@Test
	public void generateKey()
	{
	//   SecretKey key=Jwts.SIG.HS512.key().build();
      SecretKey key=Jwts.SIG.HS256.key().build();
	  String encodedkey=DatatypeConverter.printHexBinary(key.getEncoded());
	  System.out.printf("\nKEY =[%s]\n",encodedkey);
	}
	
}
