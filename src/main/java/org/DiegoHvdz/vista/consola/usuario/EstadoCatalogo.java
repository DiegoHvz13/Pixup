package org.DiegoHvdz.vista.consola.usuario;

import org.DiegoHvdz.sql.GenericSql;
import org.DiegoHvdz.sql.hibernateimpl.EstadoHiberImpl;
import org.DiegoHvdz.model.Estado;
import org.DiegoHvdz.util.ReadUtil;
import org.DiegoHvdz.vista.consola.GestorCatalogos;

public class EstadoCatalogo extends GestorCatalogos<Estado>
{
    private static EstadoCatalogo estadoCatalogo;
    private static final GenericSql<Estado> estadoSql = EstadoHiberImpl.getInstance();

    public static EstadoCatalogo getInstance( )
    {
        if(estadoCatalogo==null)
        {
            estadoCatalogo = new EstadoCatalogo();
        }
        return estadoCatalogo;
    }

    private EstadoCatalogo( )
    {
        super(EstadoHiberImpl.getInstance());
    }

    @Override
    public Estado newT()
    {
        return new Estado();
    }

    @Override
    public boolean processNewT(Estado estado)
    {
        System.out.print("> Teclee el nombre del estado: ");
        estado.setEstado( ReadUtil.read() );
        estadoSql.save(estado);
        return true;
    }

    @Override
    public boolean processEditT(Estado estado)
    {
        System.out.print("> Ingrese el nuevo nombre del estado: ");
        estado.setEstado( ReadUtil.read() );

        estadoSql.update(estado);
        return true;
    }
}


