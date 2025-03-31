package org.DiegoHVZ.Consola;

import org.DiegoHVZ.negocio.Ejecutable;
import org.DiegoHVZ.ventana.LecturaAccion;

public class Consola extends LecturaAccion
{
    private static Consola consola;

    private Consola()
    {
    }

    public static Consola getInstance( )
    {
        if(consola==null)
        {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println(":: Seleccione una opcion ::");
        System.out.println("1.-Catálogos");
        System.out.println("2.-Salir");
        System.out.print("Su opcion > ");
    }

    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 2;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        if(opcion==1)
        {
            ejecutable = ListaCatalogos.getInstance( );
        }
        ejecutable.setFlag( true );
        ejecutable.run( );
    }

}

