package ort.da.mvc.Peajes.Propietario;

public abstract class EstadoPropietario {
    private String estado;

    public EstadoPropietario(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return estado;
    }
}
