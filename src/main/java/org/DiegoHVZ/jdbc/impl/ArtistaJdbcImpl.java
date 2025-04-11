package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.Conexion;
import org.DiegoHVZ.jdbc.GenericJdbc;
import org.DiegoHVZ.model.Artista;
import org.DiegoHVZ.model.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaJdbcImpl extends Conexion implements GenericJdbc<Artista>

{

    @Override
    public List<Artista> findAll()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Artista artista = null;
        List<Artista> list = null;
        String sql = "select * from tbl_artista";

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
                artista = new Artista();
                artista.setId( resultSet.getInt(1) );
                artista.setArtista( resultSet.getString(2) );
                list.add(artista);
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
    public boolean save(Artista artista) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_ARTISTA (ARTISTA) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artista.getArtista());
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
    public boolean update(Artista artista) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_ARTISTA SET ARTISTA = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artista.getArtista());
            preparedStatement.setInt(2, artista.getId());
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
    public boolean delete(Artista artista) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_ARTISTA WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, artista.getId());
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
    public Artista findById(Integer id)
    {
        Artista artista= null;
        String query = "SELECT * FROM tbl_ARTISTA WHERE ID = ?";
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
                artista = new Artista();
                artista.setId(resultSet.getInt( "ID" ));
                artista.setArtista(resultSet.getString( "NOMBRE" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return artista;
    }

}

