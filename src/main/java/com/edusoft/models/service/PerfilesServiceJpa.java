package com.edusoft.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusoft.models.dao.IPerfilDao;
import com.edusoft.entity.Role;

@Service
public class PerfilesServiceJpa implements IPerfilesService{
	
	@Autowired
	private IPerfilDao perfilRepo;
	
	

	@Override
	public void guardar(Role role) {
		perfilRepo.save(role);
		
	}

}
