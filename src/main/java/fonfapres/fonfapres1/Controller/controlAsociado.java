package fonfapres.fonfapres1.Controller;

import fonfapres.fonfapres1.Classes.*;
import fonfapres.fonfapres1.Model.*;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import javax.swing.*;
import java.time.LocalDate;


public class controlAsociado {

    private modelAsociado modelAsociado;

    public controlAsociado() {
        this.modelAsociado = new modelAsociado();
    }

    public boolean CrearAsociado(clsAsociado asociado) {
        try{
            this.modelAsociado.Crear(asociado);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean EditarAsociado(String code, clsAsociado asociado){
        try{
            this.modelAsociado.Editar(code, asociado);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //Metodo para crear una lista de Asociados en el ComboBox
    public ObservableList<String> Listar(){
        ObservableList<String> lista = null;
        try{
            lista = this.modelAsociado.Listar();
            return lista;
        }catch(Exception e){
            return null;
        }
    }

    //Metodo para crear una lista de Asociados en La Tabla
    public ObservableList<clsAsociado> ListarTabla(){
        ObservableList<clsAsociado> lista = null;
        try{
            lista = this.modelAsociado.ListarTabla();
            return lista;
        }catch(Exception e){
            return null;
        }
    }

    //Metodo para buscar un asociado en la base de datos
    public clsAsociado Buscar(String code){
        clsAsociado asociado = null;
        try{
            asociado = this.modelAsociado.Buscar(code);
            return asociado;
        }catch(Exception e){
            return null;
        }
    }

    public boolean Borrar(String code){
        try{
            this.modelAsociado.Borrar(code);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public void pruebaFechas(LocalDate fecha){
        try{
            this.modelAsociado.pruebaFechas(fecha);
        }catch(Exception e){
            System.out.println("Error: Al llamar modelAsociado.pruebafechas, no pasa.");
        }
    }

}
