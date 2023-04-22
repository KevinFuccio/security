package com.security.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.auth.entity.Dispositivo;
import com.security.auth.entity.EType;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>{
	List<Dispositivo> findByTipologia(EType tipo);
}
