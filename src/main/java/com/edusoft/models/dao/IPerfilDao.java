package com.edusoft.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.edusoft.entity.Role;

public interface IPerfilDao extends CrudRepository<Role, Long> {

}
