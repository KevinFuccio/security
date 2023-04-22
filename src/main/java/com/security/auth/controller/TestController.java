package com.security.auth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.auth.entity.Dispositivo;
import com.security.auth.entity.EType;
import com.security.auth.repository.DispositivoRepository;
import com.security.auth.repository.UserRepository;
import com.security.auth.service.DispositivoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired DispositivoRepository dispositivoRepository;
	@Autowired UserRepository userRepository;
	@Autowired DispositivoService dispositivoService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/auth")
	@PreAuthorize("isAuthenticated()")
	public String autenticatedAccess() {
		return "Autenticated Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	
	//trova tutti i dispositivi
	@GetMapping("/admin/dispositivi")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> dispositivi() {
		return new ResponseEntity<>(dispositivoRepository.findAll(),HttpStatus.OK);
	}
	
	//trova tutti i dispositivi dandogli il tipo di dispositivo
	@GetMapping("/admin/dispositivi/{tipo}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> dispositiviFiltrati(@PathVariable EType tipo) {
		return new ResponseEntity<>(dispositivoRepository.findByTipologia(tipo),HttpStatus.OK);
	}
	
	//trova tutti gli utenti
	@GetMapping("/admin/utenti")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> utenti() {
		return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
	}
	
	//trova un utente dandogli l'id
	@GetMapping("/admin/utenti/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> utenteFiltrato(@PathVariable Long id) {
		
		return new ResponseEntity<>(userRepository.findById(id),HttpStatus.OK);
	}
	@GetMapping("/admin/dispositivo/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> rimozioneUtenteDaDispositivo(@PathVariable Long id) {
		
		return new ResponseEntity<>(dispositivoService.rimuoviUtenteDalDispositivo(id),HttpStatus.OK);
	}
}
