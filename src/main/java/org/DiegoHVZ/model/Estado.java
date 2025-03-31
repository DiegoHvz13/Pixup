package org.DiegoHVZ.model;

import java.io.Serializable;

public class Estado extends Catalogo implements Serializable
{
    private String nombre;

    public Estado()
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
        return "Estado {" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}

