package com.edusoft.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.edusoft.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	
	public Usuario findByUsername(String username);
	
	@Modifying
	@Query("UPDATE Usuario u SET u.enabled=0 WHERE u.id = :paramIdUsuario")
	int lock(@Param("paramIdUsuario")long idUsuario);
	
	@Modifying
	@Query("UPDATE Usuario u SET u.enabled=1 WHERE u.id = :paramIdUsuario")
	int unlock(@Param("paramIdUsuario")long idUsuario);
	
	@Modifying
	@Query("UPDATE Role u SET u.authority='ROLE_ADMIN' WHERE u.id = :paramIdUsuario")
	int permisos(@Param("paramIdUsuario")long idUsuario);
	
	@Modifying
	@Query("UPDATE Usuario u SET u.authority='ADMINISTRADOR' WHERE u.id = :paramIdUsuario")
	int cambioRole(@Param("paramIdUsuario")long idUsuario);
	
	@Modifying
	@Query("UPDATE Role u SET u.authority='ROLE_USER' WHERE u.id = :paramIdUsuario")
	int quitarPermisos(@Param("paramIdUsuario")long idUsuario);
	
	@Modifying
	@Query("UPDATE Usuario u SET u.authority='USUARIO' WHERE u.id = :paramIdUsuario")
	int roleUsuario(@Param("paramIdUsuario")long idUsuario);

}
