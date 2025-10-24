package ort.da.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ort.da.mvc.Peajes.Administrador.Administrador;
import ort.da.mvc.Peajes.Administrador.Servicio.ServicioAdministrador;
import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Bonificacion.BonificacionExonerada;
import ort.da.mvc.Peajes.Bonificacion.BonificacionFrecuente;
import ort.da.mvc.Peajes.Exceptions.PropietarioException;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;
import ort.da.mvc.Peajes.Peaje.Servicio.ServicioPeaje;
import ort.da.mvc.Peajes.Propietario.EstadoPropietario;
import ort.da.mvc.Peajes.Propietario.Propietario;
import ort.da.mvc.Peajes.Propietario.Servicio.ServicioPropietario;
import ort.da.mvc.Peajes.Tarifa.Tarifa;
import ort.da.mvc.Peajes.Vehiculo.CategoriaVehiculo;
import ort.da.mvc.Peajes.Vehiculo.Vehiculo;
import ort.da.mvc.Peajes.Vehiculo.Servicio.ServicioVehiculo;

public class Fachada {

//#region Singleton & Servicios

    private static Fachada instancia;
    private final ServicioPropietario servicioPropietario;
    private final ServicioPeaje servicioPeaje;
    private final ServicioVehiculo servicioVehiculo;
    private final ServicioAdministrador servicioAdministrador;

    private Fachada(ServicioAdministrador servicioAdministrador,
                          ServicioPropietario servicioPropietario,
                          ServicioVehiculo servicioVehiculo,
                          ServicioPeaje servicioPeaje) {
        this.servicioPeaje = servicioPeaje;
        this.servicioAdministrador = servicioAdministrador;
        this.servicioPropietario = servicioPropietario;
        this.servicioVehiculo = servicioVehiculo;
    }

    private Fachada() {
        this.servicioPeaje = new ServicioPeaje();
        this.servicioAdministrador = new ServicioAdministrador();
        this.servicioPropietario = new ServicioPropietario();
        this.servicioVehiculo = new ServicioVehiculo();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public ServicioPropietario getServicioPropietario() {
        return servicioPropietario;
    }

    public ServicioPeaje getServicioPeaje() {
        return servicioPeaje;
    }

    public ServicioVehiculo getServicioVehiculo() {
        return servicioVehiculo;
    }

    public ServicioAdministrador getServicioAdministrador() {
        return servicioAdministrador;
    }


//#endregion


//#region Propietario

    //#region login

    public Propietario login(int ci, String contrasenia) throws PropietarioException {
        return servicioPropietario.login(ci, contrasenia);
    }

    //#endregion

//#endregion

}
