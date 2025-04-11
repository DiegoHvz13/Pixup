package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.Conexion;
import org.DiegoHVZ.jdbc.GenericJdbc;
import org.DiegoHVZ.model.Estado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoJdbcImpl extends Conexion implements GenericJdbc<Estado>
{
    @Override
    public List<Estado> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        Estado estado = null;
        List<Estado> list = null;
        String sql = "select * from TBL_ESTADO";

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
                estado = new Estado();
                estado.setId( resultSet.getInt(1) );
                estado.setNombre( resultSet.getString(2) );
                list.add(estado);
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
    public boolean save(Estado estado) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_ESTADO (NOMBRE) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estado.getNombre());
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
    public boolean update(Estado estado) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_ESTADO SET NOMBRE = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, estado.getNombre());
            preparedStatement.setInt(2, estado.getId());
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
    public boolean delete(Estado estado) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_ESTADO WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, estado.getId());
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
    public Estado findById(Integer id)
    {
        Estado estado = null;
        String query = "SELECT * FROM tbl_estado WHERE ID = ?";
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
                estado = new Estado();
                estado.setId(resultSet.getInt( "ID" ));
                estado.setNombre(resultSet.getString( "NOMBRE" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return estado;
    }
}
