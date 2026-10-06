package com.chema.tienda.relojes.serviciosImpl;

import com.chema.tienda.relojes.model.Reloj;
import com.chema.tienda.relojes.servicios.ServicioRelojes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class ServicioRelojesImplJPA implements ServicioRelojes {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void registrarReloj(Reloj r) {

        try {
            r.setImagenPortada(r.getFotoSubida().getBytes());
        } catch (IOException e) {
            System.out.println("NO SE PUDO PROCESAR LA FOTO: " + e.getMessage());
            throw new RuntimeException(e);
        }
        entityManager.persist(r);

    }

    @Override
    public List<Reloj> obtenerRelojes() {
        return entityManager.createQuery("select r from Reloj r").getResultList();
    }

    @Override
    public Reloj obtenerRelojPorId(long id) {
        return entityManager.find(Reloj.class, id);
    }

    @Override
    public void borrarReloj(long id) throws Exception {

        Reloj relojAborrar = entityManager.find(Reloj.class, id);
        if (relojAborrar != null) {
            entityManager.remove(relojAborrar);
        } else {
            throw new Exception("id no encontrada");
        }

    }

    @Override
    public void guardarCambiosReloj(Reloj relojEditar) throws Exception {

        if (relojEditar.getFotoSubida().getSize() == 0) {
            Reloj original =
                    entityManager.find(Reloj.class, relojEditar.getId());
            relojEditar.setImagenPortada(original.getImagenPortada());
        } else {
            relojEditar.setImagenPortada(
                    relojEditar.getFotoSubida().getBytes()
            );
        }

        // Merge actualiza el registro si ya existe
        // si no existe lo crea
        entityManager.merge(relojEditar);

    }

}

