package com.edutech.cl.edutech.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edutech.cl.edutech.model.Curso;;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

}
