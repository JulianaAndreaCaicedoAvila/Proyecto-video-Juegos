package com.DesarrolloWEB_crud.demopostgres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.DesarrolloWEB_crud.demopostgres.entity.Prestamo;

@Repository
public interface ProcesoDao extends JpaRepository<Prestamo, Long> {

}
