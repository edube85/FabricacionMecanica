package com.edusoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusoft.entity.Estado;
import com.edusoft.models.dao.IEstadoDao;



@Service
public class EstadoServiceImpl implements IEstadoService{
	
	
	@Autowired
	private IEstadoDao estadoDao;
	

	@Override
	public List<Estado> findAll() {
		// TODO Auto-generated method stub
		return (List<Estado>) estadoDao.findAll();
	}

}
