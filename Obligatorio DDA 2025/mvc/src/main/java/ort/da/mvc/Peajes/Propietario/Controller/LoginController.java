package ort.da.mvc.Peajes.Propietario.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Exceptions.PropietarioException;
import ort.da.mvc.Peajes.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Respuesta;

public class LoginController {

    @PostMapping("/Propietariologin")
    public List<Respuesta> login(HttpSession sesionHttp, @RequestParam int ci, @RequestParam String contrasenia) throws PropietarioException {

        Propietario usuarioLogueado  = Fachada.getInstancia().login(ci, contrasenia);
        sesionHttp.setAttribute("propietario", usuarioLogueado);
        return Respuesta.lista(new Respuesta("loginExitoso", "menu.html"));
    }

}
