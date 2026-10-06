package com.chema.tienda.relojes.controllers;

import com.chema.tienda.relojes.model.Reloj;
import com.chema.tienda.relojes.servicios.ServicioRelojes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/")
public class ControladorRelojes {

    @Autowired
    private ServicioRelojes servicioRelojes;

    @RequestMapping("gestionarRelojes")
    public String gestionarRelojes (Model model) {

        model.addAttribute("relojes", servicioRelojes.obtenerRelojes());
        return "admin/gestionar_productos";
    }

    @RequestMapping("registrarReloj")
    public String registrarReloj (Model model) {

        Reloj nuevo = new Reloj();
        nuevo.setPrecio(1);
        model.addAttribute("nuevo", nuevo);

        return "admin/registro_producto";
    }

    @RequestMapping("guardarReloj")
    public String guardarReloj(
            @ModelAttribute("nuevo")
            @Valid
            Reloj nuevoReloj,
            BindingResult resultadoValidacion,
            Model model) {
        System.out.println("Recibido para registrar: " + nuevoReloj);

        if (resultadoValidacion.hasErrors()) {
            resultadoValidacion.getAllErrors().forEach(e -> System.out.println("ERROR: " + e));
            return "admin/registro_producto";
        }

        servicioRelojes.registrarReloj(nuevoReloj);
        return ("admin/registro_producto_ok");
    }


    @RequestMapping("borrarReloj")
    public String borrarReloj(@RequestParam("id") Long id, Model model) throws Exception {

        //Repaso: Model es una variable donde meto lo que quiera que le llegue
        // a la vista indicada en el return
        System.out.println("id recibida para borrar registro: " + id);
        servicioRelojes.borrarReloj(id);

        return gestionarRelojes(model);
    }


    @RequestMapping("editarReloj")
    public String editarReloj(@RequestParam("id") Long id, Model model){

        model.addAttribute("relojEditar",
                servicioRelojes.obtenerRelojPorId(id));

        return "admin/editar_producto";

    }


    @RequestMapping("guardarCambiosReloj")
    public String guardarCambiosReloj(
            @ModelAttribute("relojEditar") // th:object del form
            @Valid // para validar con las reglas puestas en @ en Reloj.java
            Reloj relojEditar, // Objeto que contiene lo introducido en el form
            BindingResult resultadoValidacion, //
            Model model)
            throws Exception{ // model en estos metodos es para meterle lo que queramos que le llegue a la vista devuelta en el return

        if(resultadoValidacion.hasErrors()){
            return "admin/editar_producto";
        }else {
            servicioRelojes.guardarCambiosReloj(relojEditar);
            return gestionarRelojes(model);
        }

    }

}
