package com.edutech.cl.edutech.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edutech.cl.edutech.model.Pago;;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{

}
