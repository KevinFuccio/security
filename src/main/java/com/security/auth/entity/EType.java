package com.security.auth.entity;

import java.util.Random;

public enum EType {
	PHONE,DESKTOP,LAPTOP;
	
	public static EType randomType() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
