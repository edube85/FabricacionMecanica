package com.edusoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edusoft.entity.Taller;
import com.edusoft.models.dao.ITallerDao;


@Service
public class TallerServiceImpl implements ITallerService{
	
	@Autowired
	private ITallerDao tallerDao;
	
	

	@Override
	@Transactional(readOnly=true)
	public List<Taller> findAll() {
		// TODO Auto-generated method stub
		return (List<Taller>) tallerDao.findAll();
	}



	@Override
	public Taller findOne(Long id) {
		// TODO Auto-generated method stub
		return tallerDao.findById(id).orElse(null);
	}



	@Override
	@Transactional
	public void save(Taller taller) {
		// TODO Auto-generated method stub
		tallerDao.save(taller);
	}



	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tallerDao.deleteById(id);
	}

	

}
