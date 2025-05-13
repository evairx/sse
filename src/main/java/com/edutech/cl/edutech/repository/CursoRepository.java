package com.edutech.cl.edutech.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.edutech.cl.edutech.model.Curso;;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

    @Query("SELECT c FROM CURSO c WHERE c.id_curso = :id")
    Optional<Curso> findById(Integer id);

}
