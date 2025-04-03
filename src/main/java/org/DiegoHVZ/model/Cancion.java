package org.DiegoHVZ.model;

public class Cancion extends Catalogo
{
   private String titulo;
   private float duracion;
   private int idDisco;

    public Cancion() {}

    public Cancion(String titulo, float duracion, int idDisco)
    {
        this.titulo = titulo;
        this.duracion = duracion;
        this.idDisco = idDisco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", idDisco=" + idDisco +
                ", id=" + id +
                '}';
    }
}
