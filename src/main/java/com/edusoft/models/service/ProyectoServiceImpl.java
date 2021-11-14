package com.edusoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edusoft.entity.Proyecto;
import com.edusoft.models.dao.IProyectoDao;


@Service
public class ProyectoServiceImpl implements IProyectoService{

	@Autowired
	private IProyectoDao proyectoDao;
	
	

	@Override
	@Transactional(readOnly=true)
	public List<Proyecto> findAll() {
		// TODO Auto-generated method stub
		return (List<Proyecto>) proyectoDao.findAll();
	}



	@Override
	public void save(Proyecto proyecto) {
		// TODO Auto-generated method stub
		proyectoDao.save(proyecto);
	}



	@Override
	public Proyecto findOne(Long id) {
		// TODO Auto-generated method stub
		return proyectoDao.findById(id).orElse(null);
	}



	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		proyectoDao.deleteById(id);
	}



	@Override
	public List<Proyecto> nombreDeTaller() {
		// TODO Auto-generated method stub
		return proyectoDao.nombreDeTaller();
	}


	@Override
	public List<Proyecto> nombreDeProyecto() {
		// TODO Auto-generated method stub
		return proyectoDao.nombreDeProyecto();
	}



	@Override
	public List<String> demora() {
		// TODO Auto-generated method stub
		return proyectoDao.demora();
	}



	@Override
	public void borrarTodo() {
		// TODO Auto-generated method stub
		 proyectoDao.deleteAll();
	}




	
	
	
	/*@Override
	public List<Proyecto> precios() {
		// TODO Auto-generated method stub
		return proyectoDao.precios();
	}*/



	
	
	
}
