package org.DiegoHVdoc.Model;

public class domicilio
{
    private Integer id;
    private String calle;
    private String numExterior;
    private String numInterior;
    private Integer idUsuario;
    private Integer idColonia;
    private Integer idTipoDomicilio;
    private Colonia colonia;

    public domicilio()
    {
    }

    public domicilio(Integer id, String calle, String numExterior, String numInterior, Integer idUsuario, Integer idColonia, Integer idTipoDomicilio, Colonia colonia) {
        this.id = id;
        this.calle = calle;
        this.numExterior = numExterior;
        this.numInterior = numInterior;
        this.idUsuario = idUsuario;
        this.idColonia = idColonia;
        this.idTipoDomicilio = idTipoDomicilio;
        this.colonia = colonia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumExterior() {
        return numExterior;
    }

    public void setNumExterior(String numExterior) {
        this.numExterior = numExterior;
    }

    public String getNumInterior() {
        return numInterior;
    }

    public void setNumInterior(String numInterior) {
        this.numInterior = numInterior;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public Integer getIdTipoDomicilio() {
        return idTipoDomicilio;
    }

    public void setIdTipoDomicilio(Integer idTipoDomicilio) {
        this.idTipoDomicilio = idTipoDomicilio;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }
}
