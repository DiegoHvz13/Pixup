package org.DiegoHVZ.Consola;

import org.DiegoHVZ.negocio.Ejecutable;
import org.DiegoHVZ.ventana.LecturaAccion;

public class ListaCatalogos extends LecturaAccion
{
    public static ListaCatalogos listaCatalogos;
    private ListaCatalogos()
    {
    }
    public static ListaCatalogos getInstance( )
    {
        if(listaCatalogos==null)
        {
            listaCatalogos = new ListaCatalogos();
        }
        return listaCatalogos;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println( ":: Seleccione una opcion ::" );
        System.out.println( "1.- Estado");
        System.out.println( "2.- Municipio");
        System.out.println( "3.- Colonia");
        System.out.println( "4.- Salir");
        System.out.print( "Su opcion > " );
    }
    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 4;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                ejecutable = EstadoCatalogo.getInstance( );
                break;
            case 2:
                ejecutable = MunicipioCatalogo.getInstance( );
                break;
            case 3:
                ejecutable = ColoniaCatalogo.getInstance( );
                break;
        }
        ejecutable.setFlag( true );
        ejecutable.run( );

    }
}

