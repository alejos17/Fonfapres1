package fonfapres.fonfapres1.Model;

import fonfapres.fonfapres1.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javax.swing.DefaultListModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class modelAsociado {

    Database database;

    public modelAsociado() {
        this.database = new Database();
    }

    //Convertir de String a Date
    public Date convertFecha(String fecha){
        try {
            //En este caso buscará en el String dia/mes/año
            DateFormat fechac = new SimpleDateFormat("dd/MM/yyyy");
            return fechac.parse(fecha);

        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public String convertFechaTimeStamp(String fecha){
        String formatofecha = "YYYY-MM-dd HH:mm:ss";
        String fecha2;
        SimpleDateFormat fechanewformat = new SimpleDateFormat(formatofecha);
        return fecha2 = fechanewformat.format(fecha);
    }

    //Convertir de Date a String con formato
    public String convertFechaString(Date fecha){
        String fechanew;
        try {
            //En este caso buscará en el String dia/mes/año
            SimpleDateFormat fechac = new SimpleDateFormat("dd/MM/yyyy");
            return fechanew = fechac.format(fecha);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    //Preparar fecha para la base de datos
    public Timestamp fechaSQL(Date fecha){
        return new Timestamp(fecha.getTime());
    }

    //Recibir fecha de la base de datos y volverla Date
    public Date fechaDate(Timestamp fecha){return new Date(fecha.getTime());}

    //Alta de nuevo asociado en la BD
    public boolean Crear(clsAsociado asociado){
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "INSERT INTO asociado (cedula, codigo, nombre, apellido, fecha_nac, ciudad, direccion, telefono, celular, correo, fecha_in, familia) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement CrearAsociado = conexion.prepareStatement(query);
            CrearAsociado.setString(1,asociado.getCedula());
            CrearAsociado.setString(2, asociado.getCodigo());
            CrearAsociado.setString(3, asociado.getNombre());
            CrearAsociado.setString(4, asociado.getApellido());
            Timestamp fechaSQL = Timestamp.valueOf(asociado.getFecha_nac().atStartOfDay()); //El Date se pasa a formato SQL para enviar a la base de datos
            CrearAsociado.setTimestamp(5, fechaSQL);
            CrearAsociado.setString(6, asociado.getCiudad());
            CrearAsociado.setString(7, asociado.getDireccion());
            CrearAsociado.setString(8, asociado.getTelefono());
            CrearAsociado.setString(9, asociado.getCelular());
            CrearAsociado.setString(10, asociado.getCorreo());
            Timestamp fechaSQL1 = Timestamp.valueOf(asociado.getFecha_in().atStartOfDay());  //Capturo fecha en String y se pasa a formato
            CrearAsociado.setTimestamp(11, fechaSQL1);
            CrearAsociado.setString(12, asociado.getFamilia());
            int rowsInserted = CrearAsociado.executeUpdate();
            return rowsInserted > 0;   //Retorna True si Rows es mas que 1 osea se inserto en base, condicional implicito.
        }catch (SQLException e){
            return false;
        }
    }

    public boolean Editar(String codigo, clsAsociado asociado){
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "UPDATE asociado SET cedula = ?, nombre = ?, apellido = ?, fecha_nac = ?, ciudad = ?, direccion = ?, telefono = ?, celular = ?, correo = ?, fecha_in = ?, fecha_out = ?, familia = ?, persona_rec = ?, cedula_rec = ?, avalador = ? WHERE codigo = ?;";
            System.out.println("Preparando query-.......");
            PreparedStatement EditarAsociado = conexion.prepareStatement(query);
            System.out.println("Prepared Statement listo.....");
            EditarAsociado.setString(1,asociado.getCedula());
            EditarAsociado.setString(2, asociado.getNombre());
            EditarAsociado.setString(3, asociado.getApellido());
            Timestamp fechaSQL = Timestamp.valueOf(asociado.getFecha_nac().atStartOfDay());  //El Date se pasa a formato SQL para enviar a la base de datos
            System.out.println("Fecha SQL: "+fechaSQL);
            EditarAsociado.setTimestamp(4, fechaSQL);
            EditarAsociado.setString(5, asociado.getCiudad());
            EditarAsociado.setString(6, asociado.getDireccion());
            EditarAsociado.setString(7, asociado.getTelefono());
            EditarAsociado.setString(8, asociado.getCelular());
            EditarAsociado.setString(9, asociado.getCorreo());
            System.out.println("Cambiar otras FECHAS");
            Timestamp fechaSQL1 = Timestamp.valueOf(asociado.getFecha_in().atStartOfDay());
            EditarAsociado.setTimestamp(10, fechaSQL1);
            Timestamp fechaSQL2 = Timestamp.valueOf(asociado.getFecha_out().atStartOfDay());
            EditarAsociado.setTimestamp(11, fechaSQL2);
            EditarAsociado.setString(12, asociado.getFamilia());
            EditarAsociado.setString(13, asociado.getPersona_rec());
            EditarAsociado.setString(14, asociado.getCedula_rec());
            EditarAsociado.setString(15, asociado.getAvalador());
            EditarAsociado.setString(16, codigo);
            int rowsUpdated = EditarAsociado.executeUpdate();
            System.out.println("Query enviado....");
            System.out.println("Campos modificados: "+ rowsUpdated);
            return rowsUpdated > 0;   //Retorna True si Rows es mas que 1 osea se inserto en base, condicional implicito.
        }catch (SQLException e){
            return false;
        }
    }

    public boolean Borrar(String codigo){
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "DELETE FROM asociado WHERE codigo = ?";
            PreparedStatement BorrarAsociado = conexion.prepareStatement(query);
            BorrarAsociado.setString(1, codigo);
            int rowsUpdated = BorrarAsociado.executeUpdate();
            return rowsUpdated > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public clsAsociado Buscar(String code){
        clsAsociado asociado1 = null;
        LocalDate fecha_out = null;
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "SELECT * FROM asociado WHERE codigo = ?";
            System.out.println("Query de busqueda preaparado......");
            PreparedStatement BuscarAsociado = conexion.prepareStatement(query);
            BuscarAsociado.setString(1, code);
            ResultSet busqueda = BuscarAsociado.executeQuery();
            System.out.println("Capturando resultados........");
            while (busqueda.next()){
                String cedula = busqueda.getString(1);
                String codigo = busqueda.getString(2);
                String nombre = busqueda.getString(3);
                String apellido = busqueda.getString(4);
                Timestamp fecha_nac1 = busqueda.getTimestamp(5);  //Recibo fecha en formato SQL
                LocalDate fecha_nac = fecha_nac1.toLocalDateTime().toLocalDate();
                System.out.println("Fecha Nac capturada correctamente.....");
                String ciudad = busqueda.getString(6);
                String direccion = busqueda.getString(7);
                String telefono = busqueda.getString(8);
                String celular = busqueda.getString(9);
                String correo = busqueda.getString(10);
                System.out.println("Capturando fecha");
                Timestamp fecha_in1 = busqueda.getTimestamp(11);
                LocalDate fecha_in = fecha_in1.toLocalDateTime().toLocalDate();
                System.out.println("Fecha IN capturada......");
                Timestamp fecha_out1 = busqueda.getTimestamp(12);
                if(fecha_out1 != null){
                    fecha_out = fecha_out1.toLocalDateTime().toLocalDate();
                }
                System.out.println("Fechas correctas");
                String familia = busqueda.getString(13);
                String persona_rec = busqueda.getString(14);
                String cedula_rec = busqueda.getString(15);
                String avalador = busqueda.getString(16);
                asociado1 = new clsAsociado(cedula, nombre, apellido, fecha_nac, ciudad, direccion, telefono, celular, correo, codigo, fecha_in, fecha_out, familia, persona_rec, cedula_rec, avalador);
                System.out.println("Objeto Asociado creado para enviar busqueda.....");
            }
            return asociado1;
        }catch (SQLException e){
            return asociado1;
        }
    }

    //Listar para el combo Box en String
    public ObservableList<String> Listar(){
        LinkedList<clsAsociado> asociadoList = new LinkedList<>();
        //Crear Tabla para grabar datos y enviar toda la tabla
        ObservableList<String> lista = FXCollections.observableArrayList();

        System.out.println("Metodo listar - OK");
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "SELECT * FROM asociado";
            System.out.println("Preparacion Query");
            PreparedStatement Lista = conexion.prepareStatement(query);
            ResultSet RLista = Lista.executeQuery();
            System.out.println("Entrar a Base OK");
            while (RLista.next()){
                String codigo = RLista.getString(2);
                String cedula = RLista.getString(1);
                String nombre = RLista.getString(3);
                String apellido = RLista.getString(4);
                String celular = RLista.getString(9);
                clsAsociado asociado2 = new clsAsociado(cedula, nombre, apellido, null, null,null,null,celular,null,codigo,null,null,null,null,null,null);
                asociadoList.add(asociado2);
            }
        }catch(SQLException e){
            System.out.println("Error al conectar a BD");
            return null;
        }

        int index = 0;
        System.out.println("Crear Lista - OK");
        for(clsAsociado asociado : asociadoList){
            String data = asociado.getCodigo() + " - " + asociado.getCedula() + " - " + asociado.getNombre() + " - " + asociado.getApellido() + " -- " + asociado.getCelular();
            lista.add(data);

            //lista.getItems().add(data);
            index++;
        }
        return lista;

    }

    //Listar para el TableView en Objeto
    public ObservableList<clsAsociado> ListarTabla(){
        ObservableList<clsAsociado> lista = FXCollections.observableArrayList();
        System.out.println("Metodo listar - OK");
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "SELECT * FROM asociado ORDER BY codigo";
            System.out.println("Preparacion Query");
            PreparedStatement Lista = conexion.prepareStatement(query);
            ResultSet RLista = Lista.executeQuery();
            System.out.println("Entrar a Base OK");
            while (RLista.next()){
                String cedula = RLista.getString(1);
                String codigo = RLista.getString(2);
                String nombre = RLista.getString(3);
                String apellido = RLista.getString(4);
                String celular = RLista.getString(9);
                clsAsociado asociado2 = new clsAsociado(cedula, nombre, apellido, null, null,null,null,celular,null,codigo,null,null,null,null,null,null);
                lista.add(asociado2);
                System.out.println(asociado2.getCodigo());
            }
        }catch(SQLException e){
            System.out.println("Error al conectar a BD");
            return null;
        }
        return lista;
    }


    public void pruebaFechas(LocalDate fecha){
        String fecha_nacimiento = null;
        Date fecha_nac2 = null;
        /*
        try{
            System.out.println("Paso1");
            System.out.println("Conversion de Fechas.......");
            System.out.println("Fecha String Limpio: "+fecha);
            fecha_nacimiento = convertFechaTimeStamp(fecha); //Capturo fecha en String y se pasa a formato Date
            System.out.println("Fecha String Formato: "+fecha_nacimiento);
            System.out.println("Fin Paso1");
            System.out.println("=======================================================");
        }catch(Exception e){
            System.out.println("----ERROR-------");
            System.out.println(e.getMessage());
            System.out.println("----ERROR-------");
        }

        try{
            System.out.println("Paso2");
            System.out.println("De String Formateado a Date");
            fecha_nac2 = convertFecha(fecha_nacimiento);
            System.out.println("Fecha Date: "+fecha_nac2);
            System.out.println("Fin paso2");
            System.out.println("=======================================================");
        }catch(Exception e){
            System.out.println("----ERROR-------");
            System.out.println(e.getMessage());
            System.out.println("----ERROR-------");
        }*/

        try{
            System.out.println("Paso3");
            System.out.println("De Date a Timestamp");
            //Timestamp fechaSQL = this.fechaSQL(fecha);   //El Date se pasa a formato SQL para enviar a la base de datos
            Timestamp fechaSQL = Timestamp.valueOf(fecha.atStartOfDay());
            System.out.println("Fecha SQL: "+fechaSQL);
            System.out.println("Fin Paso 3");
            System.out.println("=======================================================");
        }catch(Exception e){
            System.out.println("----ERROR-------");
            System.out.println(e.getMessage());
            System.out.println("----ERROR-------");
        }

    }


}
