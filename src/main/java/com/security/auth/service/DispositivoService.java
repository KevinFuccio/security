package com.security.auth.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.security.auth.entity.Dispositivo;
import com.security.auth.entity.ERole;
import com.security.auth.entity.EState;
import com.security.auth.entity.User;
import com.security.auth.exception.ResourceNotFoundException;
import com.security.auth.repository.DispositivoRepository;

@Service
public class DispositivoService {
	
	@Autowired
	DispositivoRepository dispositivo;
	@Autowired
	@Qualifier("randomDispositivo")
	private ObjectProvider<Dispositivo> dispositivoRandom;
	
	public void createDispositivoRandom() {
		Dispositivo d = dispositivoRandom.getObject();
		if(d.getUtente() == null && d.getStato() != EState.ASSIGNED) {
			
			dispositivo.save(d);
		}
	}
	public void assegnaDispositivi(List<User> u) {
		List<Dispositivo> dispositivi = dispositivo.findAll();
//		System.out.println(u);
		
		List<Dispositivo> dispositiviFiltrati = dispositivi.stream().filter(e-> e.getStato().equals(EState.AVAILABLE)).collect(Collectors.toList());
//		System.out.println(dispositiviFiltrati);
//		getRandom(u);
		dispositiviFiltrati.forEach(e-> e.setUtente(getRandom(u)));
		dispositiviFiltrati.forEach(e-> e.setStato(EState.ASSIGNED));
		System.out.println(dispositiviFiltrati);
		dispositivo.saveAll(dispositivi);
		
	}
	private User getRandom(List<User> u) {
	    Random rnd = new Random();
	 
	    List<User> userFiltrati = u.stream().filter(e-> e.getRoles().stream().anyMatch(f->f.getRoleName().equals(ERole.ROLE_USER))).collect(Collectors.toList());
	    
	    User randomItem = userFiltrati.get(rnd.nextInt(userFiltrati.size()));
	    
	    return randomItem;
	}
	public String rimuoviUtenteDalDispositivo(Long id) {
		Dispositivo d= dispositivo.findById(id).get();
		if(d.getStato().equals(EState.ASSIGNED)) {
			d.setUtente(null);
			d.setStato(EState.AVAILABLE);
			dispositivo.save(d);			
		}else if(!d.getStato().equals(EState.ASSIGNED))
			return "elemento in manutenzione o non disponibile";
		return "dispositivo di nuovo disponibile!";
	}

}
