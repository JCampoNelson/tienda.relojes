package com.chema.tienda.relojes.controladorImagen;

import com.chema.tienda.relojes.servicios.ServicioRelojes;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ControladorImagen {

    @Autowired
    private ServicioRelojes servicioRelojes;

    @RequestMapping("mostrar_imagen")
    public void mostrar_imagen (@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        byte[] info = servicioRelojes.obtenerRelojPorId(id).getImagenPortada();

        if (info == null) {
            return;
        }

        //Emitir info (array bytes imagen)
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/webp");

        response.getOutputStream().write(info);
        response.getOutputStream().close();

    }

}
