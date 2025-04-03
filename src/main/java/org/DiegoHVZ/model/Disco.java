package org.DiegoHVZ.model;

public class Disco extends Catalogo
{
    private String titulo;
    private float precio;
    private int existencia;
    private float descuento;
    private String fechaLanzamiento;
    private String imagen;
    private int idDisquera;
    private int idArtista;
    private int idGeneroMusical;


    public Disco() {}

    public Disco(String titulo, float precio, int existencia, float descuento, String fechaLanzamiento, String imagen, int idDisquera, int idArtista, int idGeneroMusical) {
        this.titulo = titulo;
        this.precio = precio;
        this.existencia = existencia;
        this.descuento = descuento;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagen = imagen;
        this.idDisquera = idDisquera;
        this.idArtista = idArtista;
        this.idGeneroMusical = idGeneroMusical;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdDisquera() {
        return idDisquera;
    }

    public void setIdDisquera(int idDisquera) {
        this.idDisquera = idDisquera;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public int getIdGeneroMusical() {
        return idGeneroMusical;
    }

    public void setIdGeneroMusical(int idGeneroMusical) {
        this.idGeneroMusical = idGeneroMusical;
    }


    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                ", descuento=" + descuento +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idDisquera=" + idDisquera +
                ", idArtista=" + idArtista +
                ", idGeneroMusical=" + idGeneroMusical +
                '}';
    }

}
