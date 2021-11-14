package com.edusoft.models.service;

import java.util.List;

import com.edusoft.entity.Proyecto;

public interface IProyectoService {
	
	public List<Proyecto>findAll();
	
	public void save (Proyecto proyecto);
	
	public Proyecto findOne(Long id);
	
	public void delete (Long id);
	
	public List<Proyecto> nombreDeTaller();
	
	public List<Proyecto> nombreDeProyecto();
	
	public List <String> demora();
	
	public void  borrarTodo();
	
}
