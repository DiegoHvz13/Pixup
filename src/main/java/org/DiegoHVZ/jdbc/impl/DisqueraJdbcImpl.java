package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.Conexion;
import org.DiegoHVZ.jdbc.GenericJdbc;
import org.DiegoHVZ.model.Disquera;
import org.DiegoHVZ.model.GeneroMusical;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion implements GenericJdbc<Disquera>
{

    @Override
    public List<Disquera> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        Disquera disquera = null;
        List<Disquera> list = null;
        String sql = "select * from tbl_disquera";

        try
        {
            if (!openConnection())
            {
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( sql );
            if (resultSet==null)
            {
                return null;
            }

            list = new ArrayList<>( );

            while( resultSet.next() )
            {
                disquera = new Disquera();
                disquera.setId( resultSet.getInt(1) );
                disquera.setDisquera( resultSet.getString(2) );
                list.add(disquera);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return list;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Disquera disquera) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_DISQUERA (DISQUERA) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disquera.getDisquera());
            res= preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();

            return res==1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Disquera disquera) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_DISQUERA SET DISQUERA = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disquera.getDisquera());
            preparedStatement.setInt(2, disquera.getId());
            res= preparedStatement.executeUpdate();
            preparedStatement.close();
            closeConnection();

            return res==1;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Disquera disquera) {
        PreparedStatement preparedStatement = null;
        String queryDeleteDiscos = "DELETE FROM tbl_disco WHERE TBL_DISQUERA_ID = ?";
        String queryDeleteDisquera = "DELETE FROM TBL_DISQUERA WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("error de conexión :c");
                return false;
            }

            // Eliminar discos asociados primero
            preparedStatement = connection.prepareStatement(queryDeleteDiscos);
            preparedStatement.setInt(1, disquera.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // Luego eliminar la disquera
            preparedStatement = connection.prepareStatement(queryDeleteDisquera);
            preparedStatement.setInt(1, disquera.getId());
            res = preparedStatement.executeUpdate();
            preparedStatement.close();

            closeConnection();

            return res == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Disquera findById(Integer id)
    {
        Disquera disquera= null;
        String query = "SELECT * FROM tbl_DISQUERA WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            if( !openConnection() )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                disquera = new Disquera();
                disquera.setId(resultSet.getInt( "ID" ));
                disquera.setDisquera(resultSet.getString( "DISQUERA" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return disquera;
    }
}

