package ort.da.mvc.Peajes.Vehiculo;

import ort.da.mvc.Peajes.Propietario.Propietario;

public class Vehiculo {
    private String matricula;
    private String modelo;
    private String color;
    private CategoriaVehiculo categoria;
    private Propietario propietario;

    public Vehiculo(String matricula, String modelo, String color, CategoriaVehiculo categoria, Propietario propietario) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.categoria = categoria;
        this.propietario = propietario;
    }
    
}
