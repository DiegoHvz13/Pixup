package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.Conexion;
import org.DiegoHVZ.jdbc.GenericJdbc;
import org.DiegoHVZ.model.Colonia;
import org.DiegoHVZ.model.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColoniaJdbcImpl extends Conexion implements GenericJdbc<Colonia>
{

    @Override
    public List<Colonia> findAll()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Colonia colonia = null;
        List<Colonia> list = null;
        String sql = "select * from tbl_colonia";

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
                colonia = new Colonia();
                colonia.setId( resultSet.getInt(1) );
                colonia.setNombre( resultSet.getString(2) );
                colonia.setCp(resultSet.getString(3));
                list.add(colonia);
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
    public boolean save(Colonia colonia) {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO TBL_COLONIA (NOMBRE, CP, TBL_MUNICIPIO_ID) VALUES (?, ?, ?)";

        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, colonia.getNombre());
            preparedStatement.setInt(2,colonia.getMunicipio().getId());
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
    public boolean update(Colonia colonia) {
        PreparedStatement preparedStatement = null;
        String query ="UPDATE TBL_COLONIA SET NOMBRE = ?, CP = ?, TBL_MUNICIPIO_ID = ? WHERE ID = ?";

        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, colonia.getNombre());
            preparedStatement.setInt(2, colonia.getId());
            preparedStatement.setInt(3,colonia.getMunicipio().getId());
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
    public boolean delete(Colonia colonia) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_COLONIA WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, colonia.getId());
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
    public Colonia findById(Integer id)
    {
        Colonia colonia= null;
        String query = "SELECT * FROM TBL_COLONIA WHERE ID = ?";
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
                colonia = new Colonia();
                colonia.setId(resultSet.getInt( "ID" ));
                colonia.setNombre(resultSet.getString( "NOMBRE" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return colonia;
    }

}

