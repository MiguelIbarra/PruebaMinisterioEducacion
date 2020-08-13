package cl.prueba.cnt.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.prueba.cnt.app.entity.Sismo;


@Repository
public interface SismoRepository extends JpaRepository<Sismo,Integer> {

}
