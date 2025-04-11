package org.DiegoHVZ.Consola;

import org.DiegoHVZ.jdbc.impl.MunicipioJdbcImpl;
import org.DiegoHVZ.model.Municipio;
import org.DiegoHVZ.util.ReadUtil;

import java.io.File;

public class MunicipioCatalogo extends Catalogos<Municipio>
{
    public static MunicipioCatalogo municipioCatalogo;
    private MunicipioCatalogo( )
    {
        super(new MunicipioJdbcImpl());
    }

    public static MunicipioCatalogo getInstance( )
    {
        if(municipioCatalogo==null)
        {
            municipioCatalogo = new MunicipioCatalogo();
        }
        return municipioCatalogo;
    }

    @Override
    public Municipio newT()
    {
        return new Municipio( );
    }

    @Override
    public boolean processNewT(Municipio municipio)
    {
        System.out.print("> Teclee el nombre del Municipio: " );
        municipio.setNombre( ReadUtil.read( ) );
        return true;
    }

    @Override
    public void processEditT(Municipio municipio)
    {
        System.out.print("> ID del Municipio: " + municipio.getId( ) );
        System.out.print("> Municipio en edición: " + municipio.getNombre( ) );
        System.out.print("> Teclee el nuevo nombre del municipio: " );
        municipio.setNombre( ReadUtil.read( ) );
    }

   /* @Override
    public File getFile() {
        return new File( "./Municipio.object" );
    }
*/
}
