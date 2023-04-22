package com.security.auth.entity;

import java.util.Random;

public enum EState {
	AVAILABLE,ASSIGNED,MAINTENANCE,DECOMMISSIONED;
	
	public static EState randomState() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
