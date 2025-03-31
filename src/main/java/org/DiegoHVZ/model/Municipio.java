package org.DiegoHVZ.model;

import java.io.Serializable;

public class Municipio extends Catalogo implements Serializable
{
    private String nombre;

    public Municipio()
    {
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return "Municipio {" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
