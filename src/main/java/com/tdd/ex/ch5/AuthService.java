package com.tdd.ex.ch5;

public class AuthService {
	public void authenticate(String id, String pw) {
		if(id == null) throw new IllegalArgumentException("id");
		if(pw == null) throw new IllegalArgumentException("password");
	}

}
