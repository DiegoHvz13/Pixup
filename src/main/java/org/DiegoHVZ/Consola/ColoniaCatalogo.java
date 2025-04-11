package org.DiegoHVZ.Consola;

import org.DiegoHVZ.jdbc.impl.ColoniaJdbcImpl;
import org.DiegoHVZ.model.Colonia;
import org.DiegoHVZ.util.ReadUtil;

import java.io.File;

public class ColoniaCatalogo extends Catalogos<Colonia>
{
    public static ColoniaCatalogo coloniaCatalogo;
    private ColoniaCatalogo( )
    {
        super(new ColoniaJdbcImpl());
    }

    public static ColoniaCatalogo getInstance( )
    {
        if(coloniaCatalogo==null)
        {
            coloniaCatalogo = new ColoniaCatalogo();
        }
        return coloniaCatalogo;
    }

    @Override
    public Colonia newT()
    {
        return new Colonia( );
    }

    @Override
    public boolean processNewT(Colonia colonia)
    {
        System.out.print("> Teclee el nombre de la colonia: " );
        colonia.setNombre( ReadUtil.read( ) );
        System.out.println("> Teclee el cp de la colonia: ");
        colonia.setCp(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Colonia colonia)
    {
        System.out.print("> ID de la colonia: " + colonia.getId( ) );
        System.out.print("> Colonia en edición: " + colonia.getNombre( ) );
        System.out.print("> Teclee el nuevo nombre de la colonia: " );
        colonia.setNombre( ReadUtil.read( ) );
        System.out.print("> Teclee el nuevo cp de la colonia: ");
        colonia.setCp( ReadUtil.read( ) );
    }

    /*@Override
    public File getFile() {
        return new File( "./Colonia.object" );
    }
*/
}
