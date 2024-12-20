package com.employee.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String SECRET="";
// private static final String SECRET = "96E8F66B6F687A520C6D99940C186C2770DB6A6417D98BCF880AAA8186D76B5C"

	public JwtService() {
		
		//generating key
		try
		{  
			//code to generate key with algorithm SHA256  with inbuild method generateKey
			KeyGenerator keygen=KeyGenerator.getInstance("HmacSHA256"); 
			SecretKey key=keygen.generateKey();
			SECRET=Base64.getEncoder().encodeToString(key.getEncoded());
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public String generateToken(String username) {
		
		Map<String,Object>claims=new HashMap<String, Object>();
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
				.and()
				.signWith(getkey())
				.compact()
				;
	}
	
	private SecretKey getkey()
	{
		byte[] decodedkey=Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodedkey);
		
	}

	public String extractUsername(String token) {
		
		return extractClaim(token,Claims::getSubject);
	}

	private<T> T extractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parser()
				.verifyWith(getkey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
				
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName=extractUsername(token);
		
		return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}

}
