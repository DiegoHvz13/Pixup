package org.DiegoHVZ.Consola;

import org.DiegoHVZ.model.Estado;
import org.DiegoHVZ.util.ReadUtil;

import java.io.File;

public class EstadoCatalogo extends Catalogos<Estado>
{
    public static EstadoCatalogo estadoCatalogo;
    private EstadoCatalogo( )
    {
        super();
    }

    public static EstadoCatalogo getInstance( )
    {
        if(estadoCatalogo==null)
        {
            estadoCatalogo = new EstadoCatalogo();
        }
        return estadoCatalogo;
    }

    @Override
    public Estado newT()
    {
        return new Estado( );
    }

    @Override
    public boolean processNewT(Estado estado)
    {
        System.out.print("> Teclee el nombre del estado: " );
        estado.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Estado estado)
    {
        System.out.print("> ID del estado: " + estado.getId( ) );
        System.out.print("> Estado en edición: " + estado.getNombre( ) );
        System.out.print("> Teclee el nuevo nombre del estado: " );
        estado.setNombre( ReadUtil.read( ) );
    }

    @Override
    public File getFile() {
        return new File( "./Estado.object" );
    }

}

