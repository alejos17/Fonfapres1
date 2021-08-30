package fonfapres.fonfapres1.Controller;

import fonfapres.fonfapres1.Classes.*;
import fonfapres.fonfapres1.Model.*;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import javax.swing.*;
import java.time.LocalDate;

public class controlCuentas {

    private modelCuentas modelCuentas;

    public controlCuentas() {
        this.modelCuentas = new modelCuentas();
    }


    public boolean CrearAsociado(clsCuentas cuenta) {
        try{
            this.modelCuentas.Crear(cuenta);
            return true;
        }catch(Exception e){
            return false;
        }
    }



    //TODO Crear diseño para incorporar en la pestaña Cuentas: Aportes (Saldo y fecha del ultimo aporte, sacar historial en excel de aportes)
    //TODO Crear diseño Creditos (Lista con los crèditos del usuario activos y al seleccionar mostrar el detalle del credito).
    //TODO Crear diseño Ahorros (Si tiene ahorros en una cuenta a parte y cuanto tiene y que ahorros ha hecho).


}
