package ort.da.mvc.Peajes.Propietario.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Exceptions.PropietarioException;
import ort.da.mvc.Peajes.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Respuesta;

public class PropietarioController {

     @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "propietario",required=false) Propietario p){

        if (p == null) {
             return Respuesta.lista(new Respuesta("Acceso denegado", "login.html"));
        }
        
        if (p.getEstado() == "deshabilitado") {
            return Respuesta.lista(new Respuesta("Usuario deshabilitado, no puede ingresar al sistema", "login.html"));
            
        }
        
        return Respuesta.lista(new Respuesta("usuarioAutenticado", "menu.html"));
        
    }

}
