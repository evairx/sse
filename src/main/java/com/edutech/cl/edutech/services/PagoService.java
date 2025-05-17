package com.edutech.cl.edutech.services;
import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.repository.PagoRepository;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService { 

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> getPagos(){

        return pagoRepository.findAll();

    }

    public Pago getPagoId(Integer id){

        return pagoRepository.findById(id).orElse(null);

    }

    public Pago crearPago(Pago pago){

        return pagoRepository.save(pago);

    }

    public void eliminarPago(Integer id){

        pagoRepository.deleteById(id);

    }

    public Pago updatePago(Integer id, Pago pagoUpdate){

        Pago pagoExist = pagoRepository.findById(id).orElse(null);

        if(pagoExist == null){

            return null;

        }
        

        pagoExist.setFecha_pago(pagoUpdate.getFecha_pago());
        pagoExist.setFecha_prox_pago(pagoUpdate.getFecha_prox_pago());
        pagoExist.setStatus_pago(pagoUpdate.getStatus_pago());
        pagoExist.setUsuario(pagoUpdate.getUsuario());

        return pagoRepository.save(pagoExist);

    }
    
}
