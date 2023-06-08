package com.edusoft.models.service;

import java.util.List;

import com.edusoft.entity.Usuario;

public interface IUsuariosService {
	
	void guardar(Usuario usuario);
	void eliminar(Long idUsuario);
	List<Usuario>buscarTodos();
	Usuario buscarPorId(Long idUsuario);
	Usuario buscarPorUsername(String username);
	int bloquear(long idUsuario);
	int activar(long idUsuario);
	int permisos(long idUsuario);
	int quitarPermisos(long idUsuario);
	int cambioRole(long idUsuario);
	int roleUsuario(long idUsuario);

}
