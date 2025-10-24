package ort.da.mvc.Peajes.Propietario.Servicio;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import ort.da.mvc.Peajes.Bonificacion.BonificacionExonerada;
import ort.da.mvc.Peajes.Bonificacion.BonificacionFrecuente;
import ort.da.mvc.Peajes.Bonificacion.BonificacionTrabajdor;
import ort.da.mvc.Peajes.Exceptions.BonificacionException;
import ort.da.mvc.Peajes.Exceptions.PropietarioException;
import ort.da.mvc.Peajes.Propietario.Propietario;

public class ServicioPropietario {

    private List<Propietario> propietarios;

//#region login

    public Propietario login(int ci, String contrasenia) throws PropietarioException {
        for (Propietario p : propietarios) {
            if (p.getCi().equals(ci) && p.esContrasenaValida(contrasenia)) {
                return p;
            }
        }
        throw new PropietarioException("Acceso denegado");
    }

//#endregion  

}
