package com.chema.tienda.relojes.servicios;

import com.chema.tienda.relojes.model.Reloj;
import jakarta.validation.Valid;

import java.util.List;

public interface ServicioRelojes {

    void registrarReloj(Reloj r);

    List<Reloj> obtenerRelojes();

    Reloj obtenerRelojPorId(long id);

    void borrarReloj(long id) throws Exception;

    void guardarCambiosReloj(Reloj relojEditar) throws Exception;
}
