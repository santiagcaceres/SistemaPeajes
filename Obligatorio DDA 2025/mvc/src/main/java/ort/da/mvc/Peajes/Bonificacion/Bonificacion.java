package ort.da.mvc.Peajes.Bonificacion;

import ort.da.mvc.Peajes.Exceptions.BonificacionException;
import ort.da.mvc.Peajes.Peaje.PuestoPeaje;

public abstract class Bonificacion {
    private String nombre;
    private PuestoPeaje puestoPeaje;

    public abstract int calcularDescuento(int monto) throws BonificacionException;

//#region verificarPuesto

    public void verificarPuesto(PuestoPeaje p) throws BonificacionException {
        if(!puestoPeaje.equals(p)) {
            throw new BonificacionException("La bonificacion no aplica para este puesto de peaje.");
        }

    }

//#endregion


//#region getters

    public PuestoPeaje getPuesto() {
        return puestoPeaje;
    } 

//#endregion

    //PREGUNTAR SI SE PUEDEN HACER METODOS CON CUERPO AUNQUE LA CLASE SEA ABSTRACTA


}