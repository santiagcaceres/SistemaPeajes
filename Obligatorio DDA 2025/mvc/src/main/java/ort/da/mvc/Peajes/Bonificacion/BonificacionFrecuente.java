package ort.da.mvc.Peajes.Bonificacion;

import ort.da.mvc.Peajes.Exceptions.BonificacionException;

public class BonificacionFrecuente extends Bonificacion{
@Override
    public int calcularDescuento(int monto) throws BonificacionException{
        if (monto>0) {
            return (int) (monto * 0.50);
        }
        throw new BonificacionException("El monto debe ser mayor a 0");
    }

}
