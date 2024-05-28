package com.DesarrolloWEB_crud.demopostgres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.DesarrolloWEB_crud.demopostgres.entity.Person;



@Repository 
public interface PersonDao extends JpaRepository <Person,Long>{

}
