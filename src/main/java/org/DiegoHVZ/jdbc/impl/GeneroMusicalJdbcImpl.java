package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.*;
import org.DiegoHVZ.model.Artista;
import org.DiegoHVZ.model.Estado;
import org.DiegoHVZ.model.GeneroMusical;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalJdbcImpl extends Conexion implements GenericJdbc<GeneroMusical>
{
    public List<GeneroMusical> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        GeneroMusical generoMusical = null;
        List<GeneroMusical> list = null;
        String sql = "select * from tbl_genero_musical";

        try {
            if (!openConnection()) {
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet == null) {
                return null;
            }

            list = new ArrayList<>();

            while (resultSet.next()) {
                generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt(1));
                generoMusical.setGeneroMusical(resultSet.getString(2));
                list.add(generoMusical);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return list;
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean save(GeneroMusical generoMusical) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TBL_GENERO_MUSICAL (GENERO) VALUES ( ? )";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, generoMusical.getGeneroMusical());
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
    public boolean update(GeneroMusical generoMusical) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_GENERO_MUSICAL SET GENERO = ? WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, generoMusical.getGeneroMusical());
            preparedStatement.setInt(2, generoMusical.getId());
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
    public boolean delete(GeneroMusical generoMusical) {
        PreparedStatement preparedStatement = null;
        String queryDeleteDisco = "DELETE FROM tbl_disco WHERE TBL_GENERO_MUSICAL_ID = ?";
        String queryDeleteGeneroMusical = "DELETE FROM TBL_GENERO_MUSICAL WHERE ID = ?";
        int res = 0;

        try {
            if (!openConnection()) {
                System.out.println("error de conexión :c");
                return false;
            }

            // Primero eliminamos los discos asociados
            preparedStatement = connection.prepareStatement(queryDeleteDisco);
            preparedStatement.setInt(1, generoMusical.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // Luego eliminamos el género musical
            preparedStatement = connection.prepareStatement(queryDeleteGeneroMusical);
            preparedStatement.setInt(1, generoMusical.getId());
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
    public GeneroMusical findById(Integer id)
    {
        GeneroMusical generoMusical= null;
        String query = "SELECT * FROM tbl_GENERO_MUSICAL WHERE ID = ?";
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
                generoMusical = new GeneroMusical();
                generoMusical.setId(resultSet.getInt( "ID" ));
                generoMusical.setGeneroMusical(resultSet.getString( "GENERO" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return generoMusical;
    }
}


