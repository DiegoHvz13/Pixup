package org.DiegoHvdz.vista.consola.disco;
import org.DiegoHvdz.sql.GenericSql;
import org.DiegoHvdz.model.Disquera;
import org.DiegoHvdz.sql.hibernateimpl.DisqueraHiberImpl;
import org.DiegoHvdz.util.ReadUtil;
import org.DiegoHvdz.vista.consola.GestorCatalogos;

public class DisqueraCatalogo extends GestorCatalogos<Disquera>
{
    private static DisqueraCatalogo disqueraCatalogo;
    private static final GenericSql<Disquera> disqueraSql = DisqueraHiberImpl.getInstance();

    private DisqueraCatalogo()
    {
        super(DisqueraHiberImpl.getInstance());
    }

    public static DisqueraCatalogo getInstance()
    {
        if(disqueraCatalogo==null)
        {
            disqueraCatalogo = new DisqueraCatalogo();
        }
        return disqueraCatalogo;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera)
    {
        System.out.print("> Ingrese el nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );
        disqueraSql.save(disquera);
        return true;
    }

    @Override
    public boolean processEditT(Disquera disquera)
    {
        System.out.print("> Ingrese el nuevo nombre de la disquera: ");
        disquera.setDisquera( ReadUtil.read() );

        disqueraSql.update(disquera);
        return true;
    }
}
