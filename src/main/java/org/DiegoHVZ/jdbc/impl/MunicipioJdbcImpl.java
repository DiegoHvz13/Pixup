package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.Conexion;
import org.DiegoHVZ.jdbc.GenericJdbc;
import org.DiegoHVZ.model.GeneroMusical;
import org.DiegoHVZ.model.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioJdbcImpl extends Conexion implements GenericJdbc<Municipio>
{
    @Override
    public List<Municipio> findAll()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Municipio municipio = null;
        List<Municipio> list = null;
        String sql = "select * from tbl_municipio";

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
                municipio = new Municipio();
                municipio.setId( resultSet.getInt(1) );
                municipio.setNombre( resultSet.getString(2) );
                list.add(municipio);
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
    public boolean save(Municipio municipio) {
        PreparedStatement preparedStatement = null;
        String query =  "INSERT INTO TBL_MUNICIPIO (NOMBRE, TBL_ESTADO_ID) VALUES (?, ?)";

        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, municipio.getNombre());
            preparedStatement.setInt(2,municipio.getEstado().getId());
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
    public boolean update(Municipio municipio) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_MUNICIPIO SET NOMBRE = ?, TBL_ESTADO_ID = ? WHERE ID = ?";

        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, municipio.getNombre());
            preparedStatement.setInt(2, municipio.getId());
            preparedStatement.setInt(3,municipio.getEstado().getId());
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
    public boolean delete(Municipio municipio) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_MUNICIPIO WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, municipio.getId());
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
    public Municipio findById(Integer id)
    {
        Municipio municipio= null;
        String query = "SELECT * FROM tbl_MUNICIPIO WHERE ID = ?";
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
                municipio = new Municipio();
                municipio.setId(resultSet.getInt( "ID" ));
                municipio.setNombre(resultSet.getString( "NOMBRE" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return municipio;
    }
}
