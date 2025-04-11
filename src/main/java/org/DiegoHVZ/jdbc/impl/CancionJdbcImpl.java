package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.Conexion;
import org.DiegoHVZ.jdbc.GenericJdbc;
import org.DiegoHVZ.model.Cancion;
import org.DiegoHVZ.model.Disco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CancionJdbcImpl extends Conexion implements GenericJdbc<Cancion>
{

    @Override
    public List<Cancion> findAll()
    {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Cancion cancion = null;
        List<Cancion> list = null;
        String sql =  "SELECT tbl_cancion.*, tbl_disco.*, tbl_artista.ARTISTA, tbl_disquera.DISQUERA, tbl_genero_musical.GENERO FROM tbl_cancion " +
                "INNER JOIN tbl_disco ON tbl_cancion.tbl_disco_id = tbl_disco.id " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical ON tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id" +
                ";";

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
                cancion = new Cancion();
                cancion.setId( resultSet.getInt(1) );
                cancion.setTitulo( resultSet.getString(2) );
                cancion.setDuracion(resultSet.getFloat(3));
                cancion.setIdDisco(resultSet.getInt(4));
                list.add(cancion);
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
    public boolean save(Cancion cancion) {
        PreparedStatement preparedStatement = null;
        String query =  "INSERT INTO TBL_CANCION (TITULO, DURACION, TBL_DISCO_ID) VALUES (?, ?, ?)";

        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setFloat(2,cancion.getDuracion());
            preparedStatement.setInt(3,cancion.getIdDisco());
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
    public boolean update(Cancion cancion) {
        PreparedStatement preparedStatement = null;
        String query =  "UPDATE TBL_CANCION SET TITULO = ?, DURACION = ?, TBL_DISCO_ID = ? WHERE ID = ?";

        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cancion.getTitulo());
            preparedStatement.setFloat(2,cancion.getDuracion());
            preparedStatement.setInt(3,cancion.getIdDisco());
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
    public boolean delete(Cancion cancion) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_CANCION WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cancion.getId());
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
    public Cancion findById(Integer id)
    {
        Cancion cancion= null;
        String query = "SELECT * FROM tbl_CANCION WHERE ID = ?";
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
                cancion= new Cancion();
                cancion.setId(resultSet.getInt( "ID" ));
                cancion.setTitulo(resultSet.getString( "TITULO" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return cancion;
    }
}
