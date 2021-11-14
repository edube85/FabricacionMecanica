package com.edusoft.models.service;

import java.util.List;

import com.edusoft.entity.Taller;


public interface ITallerService {
	
	public List<Taller>findAll();
	
	public Taller findOne(Long id);
	
	public void save (Taller taller);
	
	public void delete (Long id);

}
