package com.chema.tienda.relojes.controladoresREST;


import com.chema.tienda.relojes.model.Reloj;
import com.chema.tienda.relojes.servicios.ServicioRelojes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("restRelojes/")
public class RelojesREST {

    @Autowired
    private ServicioRelojes servicioRelojes;

    @RequestMapping("listar")
    public List<Reloj> listar () {
        return servicioRelojes.obtenerRelojes();
    }

    @RequestMapping("obtener-reloj")
    public Reloj obtenerReloj(@RequestParam("id") Long id) {

        return servicioRelojes.obtenerRelojPorId(id);
    }

}
