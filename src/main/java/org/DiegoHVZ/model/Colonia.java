package org.DiegoHVZ.model;

import java.io.Serializable;

public class Colonia extends Catalogo implements Serializable
{
    private String nombre;
    private String cp;

    public Colonia()
    {
    }

    public String getCp() {return cp;}

    public void setCp(String cp) {this.cp = cp;}

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
                "Cp= "+cp + '\''+
                ", id=" + id +
                '}';
    }
}
