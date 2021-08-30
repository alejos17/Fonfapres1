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

public class modelCuentas {

    Database database;

    public modelCuentas() {
        this.database = new Database();
    }


    public boolean Crear(clsCuentas cuenta){
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "INSERT INTO cuentas (idcuenta, codigo_asociado, tipocuenta, saldo) VALUES (?, ?, ?, ?);";
            PreparedStatement CrearCuenta = conexion.prepareStatement(query);
            CrearCuenta.setString(1,cuenta.getIdcuenta());
            CrearCuenta.setString(2, cuenta.getCodigo());
            CrearCuenta.setString(3, cuenta.getTipoCuenta());
            CrearCuenta.setInt(4, cuenta.getSaldo());
            int rowsInserted = CrearCuenta.executeUpdate();
            return rowsInserted > 0;   //Retorna True si Rows es mas que 1 osea se inserto en base, condicional implicito.
        }catch (SQLException e){
            return false;
        }
    }

    public boolean Editar(String code, clsCuentas cuenta){
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "UPDATE cuentas SET idcuenta = ?, tipocuenta = ?, saldo = ? WHERE codigo_asociado = ?;";
            System.out.println("Preparando query-.......");
            PreparedStatement EditarCuenta = conexion.prepareStatement(query);
            System.out.println("Prepared Statement listo.....");
            EditarCuenta.setString(1,cuenta.getIdcuenta());
            EditarCuenta.setString(2, cuenta.getTipoCuenta());
            EditarCuenta.setInt(3, cuenta.getSaldo());
            EditarCuenta.setString(15, code);
            int rowsUpdated = EditarCuenta.executeUpdate();
            System.out.println("Query enviado....");
            System.out.println("Campos modificados: "+ rowsUpdated);
            return rowsUpdated > 0;   //Retorna True si Rows es mas que 1 osea se inserto en base, condicional implicito.
        }catch (SQLException e){
            return false;
        }
    }

    public boolean Borrar(String code){
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "DELETE FROM cuentas WHERE codigo_asociado = ?";
            PreparedStatement BorrarCuenta = conexion.prepareStatement(query);
            BorrarCuenta.setString(1, code);
            int rowsUpdated = BorrarCuenta.executeUpdate();
            return rowsUpdated > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public clsCuentas Buscar(String code){
        clsCuentas cuenta1 = null;
        try(Connection conexion = DriverManager.getConnection(database.getUrl())){
            String query = "SELECT * FROM cuentas WHERE codigo_asociado = ?";
            System.out.println("Query de busqueda preaparado......");
            PreparedStatement BuscarCuenta = conexion.prepareStatement(query);
            BuscarCuenta.setString(1, code);
            ResultSet busqueda = BuscarCuenta.executeQuery();
            System.out.println("Capturando resultados........");
            while (busqueda.next()){
                String idCuenta = busqueda.getString(1);
                String codigo = busqueda.getString(2);
                String tipocuenta = busqueda.getString(3);
                int saldo = busqueda.getInt(4);
                cuenta1 = new clsCuentas(idCuenta, codigo, saldo, tipocuenta);
                System.out.println("Objeto Cuenta creado para enviar busqueda.....");
            }
            return cuenta1;
        }catch (SQLException e){
            return cuenta1;
        }
    }


}
