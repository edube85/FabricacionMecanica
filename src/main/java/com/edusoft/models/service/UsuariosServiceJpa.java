package com.edusoft.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edusoft.models.dao.IUsuarioDao;
import com.edusoft.entity.Usuario;

@Service
public class UsuariosServiceJpa implements IUsuariosService {

	@Autowired
	private IUsuarioDao usuariosRepo;

	@Override
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);

	}

	@Override
	public void eliminar(Long idUsuario) {
		usuariosRepo.deleteById(idUsuario);

	}

	@Override
	public List<Usuario> buscarTodos() {

		return (List<Usuario>) usuariosRepo.findAll();
	}

	@Override
	public Usuario buscarPorId(Long idUsuario) {
		Optional<Usuario> optional = usuariosRepo.findById(idUsuario);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Usuario buscarPorUsername(String username) {

		return usuariosRepo.findByUsername(username);
	}

	@Transactional
	@Override
	public int bloquear(long idUsuario) {
		int rows = usuariosRepo.lock(idUsuario);
		return rows;
	}

	@Transactional
	@Override
	public int activar(long idUsuario) {
		int rows = usuariosRepo.unlock(idUsuario);
		return rows;
	}
	
	@Transactional
	@Override
	public int permisos(long idUsuario) {
		int rows = usuariosRepo.permisos(idUsuario);
		return rows;
	}
	
	@Transactional
	@Override
	public int cambioRole(long idUsuario) {
		int rows = usuariosRepo.cambioRole(idUsuario);
		return rows;
	}
	
	@Transactional
	@Override
	public int quitarPermisos(long idUsuario) {
		int rows = usuariosRepo.quitarPermisos(idUsuario);
		
		return rows;
	}
	
	@Transactional
	@Override
	public int roleUsuario(long idUsuario) {
		int rows = usuariosRepo.roleUsuario(idUsuario);
		
		return rows;
	}

}
