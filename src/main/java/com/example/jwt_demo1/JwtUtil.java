package com.example.jwt_demo1;

import java.security.Key;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateJwt(Employee user) {
//		Claims claims = Jwts.claims().setIssuer(String.valueOf(user.getId()));
//		claims.put("Name ", user.getName());
//		claims.put("Email", user.getEmail());
		return Jwts.builder().setIssuer(String.valueOf(user.getId())).claim("Name ", user.getName())
				.claim("Email", user.getPassword()).signWith(key).compact();
	}
	public boolean verify(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
