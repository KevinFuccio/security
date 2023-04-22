package com.security.auth.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.security.auth.entity.Dispositivo;
import com.security.auth.entity.EState;
import com.security.auth.entity.EType;

@Configuration
public class DispositivoConfiguration {

	@Bean("randomDispositivo")
	@Scope("prototype")
	public Dispositivo createNewDispositivo() {
		Dispositivo d =  new Dispositivo();
		d.setTipologia(EType.randomType());
		d.setStato(EState.randomState());
		return d;
	}
	
	
}
