package Util;

import org.DiegoHVdoc.Model.Colonia;

public enum EleccionEnum
{
    ESTADO(1),
    MUNICIPIO(2),
    COLONIA(3),
    SALIR(4),
    OPCION_ERRONEA(5);
    private Integer id;

    EleccionEnum(Integer id) {this.id = id;}

    public Integer getId() {return id;}

    public static  EleccionEnum getEleccionEnumById(Integer id)
    {
        switch (id)
        {
            case 1:
                return ESTADO;
            case 2:
                return MUNICIPIO;
            case 3:
                return COLONIA;
            case 4:
                return SALIR;
            case 5:
            default:
                return OPCION_ERRONEA;
        }
    }


}
