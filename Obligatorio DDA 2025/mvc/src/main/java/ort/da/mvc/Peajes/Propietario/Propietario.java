package ort.da.mvc.Peajes.Propietario;

import java.util.List;

import ort.da.mvc.Peajes.Bonificacion.Bonificacion;
import ort.da.mvc.Peajes.Bonificacion.BonificacionExonerada;
import ort.da.mvc.Peajes.Exceptions.BonificacionException;

public class Propietario {
    private int ci;
    private String password;
    private String nombre;
    private int saldoActual;
    private int saldoMinimo;
    private EstadoPropietario estado;
    private List<Bonificacion> bonificaciones;

//#region getters

    public Object getCi() {
        return ci;
    }
    
    public String getEstado() {
        return estado.toString();
    }

//#endregion    

//#region agregarBonificacion  

    public void agregarBonificacion(Bonificacion bonificacion) throws BonificacionException{ 
        try {
            if (verificarBonificacion(bonificacion)) {
                bonificaciones.add(bonificacion);
            }
        } catch (BonificacionException e) {
            throw e;
        }
    }

    private boolean verificarBonificacion(Bonificacion bonificacion) throws BonificacionException {
        for (Bonificacion b : bonificaciones) {
            try {
                b.verificarPuesto(bonificacion.getPuesto());
            } catch (BonificacionException e) {
                throw e;
            }
        }
        return true;
    }

//#endregion


    public boolean esContrasenaValida(String contrasenia) {
        return this.password.equals(contrasenia);
    }



}
