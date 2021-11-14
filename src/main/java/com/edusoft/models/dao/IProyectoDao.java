package com.edusoft.models.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.edusoft.entity.Proyecto;



public interface IProyectoDao extends JpaRepository<Proyecto,Long>{

	
	//DAOS para sacar las gráficas
	
	  @Query("select new com.edusoft.entity.Proyecto(c.nombreTaller,sum(c.precio)) from Proyecto c group by c.nombreTaller order by c.nombreTaller")
	  public List <Proyecto> nombreDeTaller();
	  
	  @Query("select new com.edusoft.entity.Proyecto(c.nombre,sum(c.precio)) from Proyecto c group by c.nombre order by c.nombre")
	  public List <Proyecto> nombreDeProyecto();
	  
	  @Query("select c.demora,c.solicitud, c.nombreTaller from Proyecto c where c.demora > 0 and c.demora <= 4 and c.fechaRec = null")
	  public List <String> demora();
	 

}
