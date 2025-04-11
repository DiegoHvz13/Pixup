package org.DiegoHVZ.jdbc.impl;

import org.DiegoHVZ.jdbc.*;
import org.DiegoHVZ.model.Cancion;
import org.DiegoHVZ.model.Disco;
import org.DiegoHVZ.model.Municipio;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscoJdbcImpl extends Conexion implements GenericJdbc<Disco>
{

    @Override
    public List<Disco> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Disco disco = null;
        List<Disco> list = null;
        String sql = "SELECT tbl_disco.*, tbl_artista.ARTISTA AS ARTISTA, tbl_disquera.DISQUERA AS DISQUERA, tbl_genero_musical.GENERO AS GENERO " +
                "FROM tbl_disco " +
                "INNER JOIN tbl_artista ON tbl_disco.tbl_artista_id = tbl_artista.id " +
                "INNER JOIN tbl_disquera ON tbl_disco.tbl_disquera_id = tbl_disquera.id " +
                "INNER JOIN tbl_genero_musical ON tbl_disco.tbl_genero_musical_id = tbl_genero_musical.id" + ";";

        try {
            if (!openConnection()) {
                System.out.println("Error al abrir la conexión.");
                return null;
            }

            if (connection == null || connection.isClosed()) {
                System.out.println("La conexión es nula o está cerrada.");
                return null;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet == null) {
                System.out.println("El resultado de la consulta es nulo.");
                return null;
            }

            list = new ArrayList<>();

            while (resultSet.next()) {
                disco = new Disco();
                disco.setId(resultSet.getInt(1));
                disco.setTitulo(resultSet.getString(2));
                disco.setImagen(resultSet.getString(3));
                disco.setExistencia(resultSet.getInt(4));
                disco.setDescuento(resultSet.getFloat(5));
                disco.setPrecio(resultSet.getFloat(6));
                disco.setIdArtista(resultSet.getInt(7));
                disco.setIdGeneroMusical(resultSet.getInt(8));
                disco.setIdDisquera(resultSet.getInt(9));

                Date date = resultSet.getDate(10);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String fechaFormateada = simpleDateFormat.format(date);

                disco.setFechaLanzamiento(fechaFormateada);

                list.add(disco);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public boolean save(Disco disco) {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO TBL_DISCO (TITULO, PRECIO, EXISTENCIA, DESCUENTO, FECHA_LANZAMIENTO, IMAGEN, TBL_ARTISTA_ID, TBL_DISQUERA_ID, TBL_GENERO_MUSICAL_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setFloat(2,disco.getPrecio());
            preparedStatement.setFloat(3,disco.getDescuento());
            preparedStatement.setString(4,disco.getFechaLanzamiento());
            preparedStatement.setInt(5,disco.getExistencia());
            preparedStatement.setString(6,disco.getImagen());
            preparedStatement.setInt(7,disco.getIdDisquera());
            preparedStatement.setInt(8,disco.getIdArtista());
            preparedStatement.setInt(9,disco.getIdGeneroMusical());
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
    public boolean update(Disco disco) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE TBL_DISCO SET TITULO = ?, PRECIO = ?, EXISTENCIA = ?, DESCUENTO = ?, FECHA_LANZAMIENTO = ?, IMAGEN = ?, TBL_ARTISTA_ID = ?, TBL_DISQUERA_ID = ?, TBL_GENERO_MUSICAL_ID = ? WHERE ID = ?";

        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setInt(2, disco.getId());
            preparedStatement.setFloat(3,disco.getPrecio());
            preparedStatement.setFloat(4,disco.getDescuento());
            preparedStatement.setString(5,disco.getFechaLanzamiento());
            preparedStatement.setInt(6,disco.getExistencia());
            preparedStatement.setString(7,disco.getImagen());
            preparedStatement.setInt(8,disco.getIdDisquera());
            preparedStatement.setInt(9,disco.getIdArtista());
            preparedStatement.setInt(10,disco.getIdGeneroMusical());
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
    public boolean delete(Disco disco) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TBL_DISCO WHERE ID = ?";
        int res = 0;

        try
        {
            if( !openConnection() )
            {
                System.out.println("error de conexión :c");
                return false;
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, disco.getId());
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
    public Disco findById(Integer id)
    {
        Disco disco= null;
        String query = "SELECT * FROM tbl_DISCO WHERE ID = ?";
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
                disco= new Disco();
                disco.setId(resultSet.getInt( "ID" ));
                disco.setTitulo(resultSet.getString( "TITULO" ));
            }

            preparedStatement.close();
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return disco;
    }

}
