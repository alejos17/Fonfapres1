package fonfapres.fonfapres1;

import fonfapres.fonfapres1.Controller.*;
import fonfapres.fonfapres1.Classes.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Optional;

public class inicioController implements Initializable {

    controlAsociado controlAsociado = new controlAsociado();

    //Cuadros de Dialogo para mensajes
    //@FXML private Alert VentanaError = new Alert(Alert.AlertType.ERROR);  //Ventana Error
    //@FXML private Alert VentanaInformacion = new Alert(Alert.AlertType.INFORMATION);  //Cuadro de Información
    //@FXML private Alert VentanaWarning = new Alert(Alert.AlertType.WARNING);  //Ventana de Informacion extendida
    //@FXML private Alert VentanaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);  //Ventana de Confirmacion
    //@FXML private TextInputDialog InputTexto = new TextInputDialog();



    //Llaman los objetos desde el diseño.. tal cual el nombre
    @FXML private AnchorPane menuPanel;
    @FXML private AnchorPane asociadoPanel;
    @FXML private AnchorPane cuentasPanel;
    @FXML private AnchorPane registrosPanel;
    @FXML private AnchorPane cajaPanel;

    //Crear los textFields
    @FXML private TextField txtcodigo;
    @FXML private TextField txtcedula;
    @FXML private TextField txtnombre;
    @FXML private TextField txtapellido;
    @FXML private TextField txtfamilia;
    @FXML private TextField txtdireccion;
    @FXML private TextField txtciudad;
    @FXML private TextField txttelefono;
    @FXML private TextField txtcelular;
    @FXML private TextField txtcorreo;
    @FXML private TextField txtcedauth;
    @FXML private TextField txtavalador;



    //Crear Botones
    @FXML private Button btnBuscarAsociado;

    //Crear el combo Box
    @FXML private ComboBox<String> cbPersonAuth;

    //Crear los DatePicker
    @FXML private DatePicker dpFecha_nac;
    @FXML private DatePicker dpFecha_in;
    @FXML private DatePicker dpFecha_out;

    //Crear las Lista Asociados Tabla
    @FXML private TableView<clsAsociado> jListAsociado;
    @FXML private TableColumn<clsAsociado, String> clm_codigo;
    @FXML private TableColumn<clsAsociado, String> clm_cedula;
    @FXML private TableColumn<clsAsociado, String> clm_nombre;
    @FXML private TableColumn<clsAsociado, String> clm_apellido;
    @FXML private TableColumn<clsAsociado, String> clm_celular;



    //Llenar el ComboBOX
    ObservableList<String> comboIDContent = controlAsociado.Listar();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initTextFieldsFiltros();
        this.initListarTablas();
        //TODO implementar el focus en txtcodigo al iniciar el programa.

        //TODO implementar un grupo de RadioButtons para seleccionar: Asociado - Menor - Externo
        //Si se selecciona Asociado - se desactiva la opcion de persona autorizada
        //Si se selecciona menor - se debe seleccionar una persona autorizada para sacar dinero o responsable de la cuenta que es un Asociado ingresado en BD.
        //Si se selecciona externo - se activa persona autorizada.
        //guardar en BD y cambiar el campo cedulaautorizada por tipo de usuario.(Asociado- Menor- Externo).


        //Inicializar el combo box
        cbPersonAuth.setItems(comboIDContent);

        /*Crear grupo de option check para que si selecciono uno se desactive el otro u otros
        Se debe poner fxid a los option check y meterlos al grupo
        @FXML private RadioButton opcion1;
        @FXML private RadioButton opcion2;

        Se agrupan
        ToggleGroup group = new ToggleGroup();
        opcion1.setToggleGroup(group);
        opcion2.setToggleGroup(group);

        Asi quedan en el mismo grupo para una sola selección.

        */
    }

    //@FXML   //Esto es obligatorio si es un metodo protected

    public void btnSalir() {
        Alert VentanaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);  //Ventana de Confirmacion
        VentanaConfirmacion.setTitle("Salir");
        VentanaConfirmacion.setHeaderText(null);
        VentanaConfirmacion.initStyle(StageStyle.UTILITY);
        VentanaConfirmacion.setContentText("¿Realmente desea Salir del programa?");
        Optional<ButtonType> result = VentanaConfirmacion.showAndWait();
        if(result.get() == ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }

    //Cambio de Paneles
    public void onAsociadoButtonClick(MouseEvent event){
        asociadoPanel.setVisible(true);
        cuentasPanel.setVisible(false);
        registrosPanel.setVisible(false);
        cajaPanel.setVisible(false);
    }

    public void onCuentasButtonClick(MouseEvent event){
        asociadoPanel.setVisible(false);
        cuentasPanel.setVisible(true);
        registrosPanel.setVisible(false);
        cajaPanel.setVisible(false);
    }

    public void onRegistrosButtonClick(MouseEvent event){
        asociadoPanel.setVisible(false);
        cuentasPanel.setVisible(false);
        registrosPanel.setVisible(true);
        cajaPanel.setVisible(false);
    }

    public void onCajaButtonClick(MouseEvent event){
        asociadoPanel.setVisible(false);
        cuentasPanel.setVisible(false);
        registrosPanel.setVisible(false);
        cajaPanel.setVisible(true);
    }

    //BOTONES ----
    public void btnCrearAsociado(){
        try{
            String codigo = txtcodigo.getText();
            String cedula = txtcedula.getText();
            String nombre = txtnombre.getText();
            String apellido = txtapellido.getText();
            LocalDate fecha_nac = dpFecha_nac.getValue();
            String familia = txtfamilia.getText();
            String direccion = txtdireccion.getText();
            String ciudad = txtciudad.getText();
            String telefono = txttelefono.getText();
            String celular = txtcelular.getText();
            String correo = txtcorreo.getText();
            LocalDate fecha_in = dpFecha_in.getValue();
            LocalDate fecha_out = dpFecha_out.getValue();
            //TODO agregar persona autorizada
            String cedauth = txtcedauth.getText();
            String avalador = txtavalador.getText();

            if (codigo.equals("") || cedula.equals("") || nombre.equals("") || apellido.equals("") || fecha_nac.toString().equals("") || familia.equals("") || direccion.equals("") ||
                    ciudad.equals("") || telefono.equals("") || celular.equals("") || fecha_in.toString().equals("")) {
                Alert VentanaInformacion = new Alert(Alert.AlertType.INFORMATION);
                VentanaInformacion.setTitle("Información");
                VentanaInformacion.setHeaderText(null);
                VentanaInformacion.setContentText("Existen campos vacíos: Por favor indique la información completa del asociado.");
                VentanaInformacion.initStyle(StageStyle.UTILITY);
                VentanaInformacion.showAndWait();
            }else{
                Alert VentanaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);  //Ventana de Confirmacion
                VentanaConfirmacion.setTitle("Agregar nuevo Asociado");
                VentanaConfirmacion.setHeaderText(null);
                VentanaConfirmacion.initStyle(StageStyle.UTILITY);
                VentanaConfirmacion.setContentText("Vamos a agregar el nuevo Asociado a la Base de Datos, correcto?");
                Optional<ButtonType> result = VentanaConfirmacion.showAndWait();
                if(result.get() == ButtonType.OK){
                    clsAsociado asociado = new clsAsociado(cedula,nombre,apellido,fecha_nac,ciudad,direccion,telefono,celular,correo,codigo,fecha_in,fecha_out,familia,null,cedauth,avalador);
                    controlAsociado.CrearAsociado(asociado);
                    Alert VentanaInformacion = new Alert(Alert.AlertType.INFORMATION);
                    VentanaInformacion.setTitle("Información");
                    VentanaInformacion.setHeaderText(null);
                    VentanaInformacion.setContentText("El nuevo Asociado ha sido guardado con éxito.");
                    VentanaInformacion.initStyle(StageStyle.UTILITY);
                    VentanaInformacion.showAndWait();
                    this.limpiarcampos();
                    this.initListarTablas();
                }
            }
        }catch(Exception e){
            Alert VentanaError = new Alert(Alert.AlertType.ERROR);
            VentanaError.setTitle("Error");
            VentanaError.setHeaderText(null);
            VentanaError.setContentText("Error: Línea 196 - inicioController.java - Indique al desarrollador");
            VentanaError.initStyle(StageStyle.UTILITY);
            VentanaError.showAndWait();
        }
    }


    public void btnBuscarAsociado(){
        String code = txtcodigo.getText();
        clsAsociado asociado = controlAsociado.Buscar(code);
        if(asociado == null){
            Alert VentanaError = new Alert(Alert.AlertType.ERROR);
            VentanaError.setTitle("Error");
            VentanaError.setHeaderText(null);
            VentanaError.setContentText("Error en Búsqueda: El código del asociado no se encuentra en Base de Datos");
            VentanaError.initStyle(StageStyle.UTILITY);
            VentanaError.showAndWait();
            limpiarcampos();
        }else{
            txtcedula.setText(asociado.getCedula());
            txtnombre.setText(asociado.getNombre());
            txtapellido.setText(asociado.getApellido());
            dpFecha_nac.setValue(asociado.getFecha_nac());
            txtfamilia.setText(asociado.getFamilia());
            txtdireccion.setText(asociado.getDireccion());
            txtciudad.setText(asociado.getCiudad());
            txttelefono.setText(asociado.getTelefono());
            txtcelular.setText(asociado.getCelular());
            txtcorreo.setText(asociado.getCorreo());
            dpFecha_in.setValue(asociado.getFecha_in());
            dpFecha_out.setValue(asociado.getFecha_out());
            txtcedauth.setText(asociado.getCedula_rec());
            txtavalador.setText(asociado.getAvalador());
        }
    }

    public void btnEditarAsociado() {
        String code = txtcodigo.getText();

        try{
            String codigo = txtcodigo.getText();
            String cedula = txtcedula.getText();
            String nombre = txtnombre.getText();
            String apellido = txtapellido.getText();
            LocalDate fecha_nac = dpFecha_nac.getValue();
            String familia = txtfamilia.getText();
            String direccion = txtdireccion.getText();
            String ciudad = txtciudad.getText();
            String telefono = txttelefono.getText();
            String celular = txtcelular.getText();
            String correo = txtcorreo.getText();
            LocalDate fecha_in = dpFecha_in.getValue();
            LocalDate fecha_out = dpFecha_out.getValue();
            String cedauth = txtcedauth.getText();
            String avalador = txtavalador.getText();

            if (codigo.equals("") || cedula.equals("") || nombre.equals("") || apellido.equals("") || fecha_nac.toString().equals("") || familia.equals("") || direccion.equals("") ||
                    ciudad.equals("") || telefono.equals("") || celular.equals("") || fecha_in.toString().equals("")) {
                Alert VentanaInformacion = new Alert(Alert.AlertType.INFORMATION);
                VentanaInformacion.setTitle("Información");
                VentanaInformacion.setHeaderText(null);
                VentanaInformacion.setContentText("Existen campos vacíos: Por favor indique la información completa del asociado.");
                VentanaInformacion.initStyle(StageStyle.UTILITY);
                VentanaInformacion.showAndWait();
            }else{
                Alert VentanaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);  //Ventana de Confirmacion
                VentanaConfirmacion.setTitle("Cambiar Información de Asociado");
                VentanaConfirmacion.setHeaderText(null);
                VentanaConfirmacion.initStyle(StageStyle.UTILITY);
                VentanaConfirmacion.setContentText("¿Desea confirmar los cambios del Asociado a la Base de datos?");
                Optional<ButtonType> result = VentanaConfirmacion.showAndWait();
                if(result.get() == ButtonType.OK){
                    clsAsociado asociado = new clsAsociado(cedula,nombre,apellido,fecha_nac,ciudad,direccion,telefono,celular,correo,codigo,fecha_in,fecha_out,familia,null,cedauth,avalador);
                    controlAsociado.EditarAsociado(code,asociado);
                    Alert VentanaInformacion = new Alert(Alert.AlertType.INFORMATION);
                    VentanaInformacion.setTitle("Información");
                    VentanaInformacion.setHeaderText(null);
                    VentanaInformacion.setContentText("El Asociado ha sido modificado con éxito.");
                    VentanaInformacion.initStyle(StageStyle.UTILITY);
                    VentanaInformacion.showAndWait();
                    this.limpiarcampos();
                    this.initListarTablas();
                }
            }
        }catch(Exception e){
            Alert VentanaError = new Alert(Alert.AlertType.ERROR);
            VentanaError.setTitle("Error");
            VentanaError.setHeaderText(null);
            VentanaError.setContentText("Error: Línea 208 - inicioController.java - Indique al desarrollador");
            VentanaError.initStyle(StageStyle.UTILITY);
            VentanaError.showAndWait();
        }

    }


    public void btnBorrarAsociado(){
        try{
            String code = txtcodigo.getText();

            Alert VentanaInformacion = new Alert(Alert.AlertType.INFORMATION);
            VentanaInformacion.setTitle("Información");
            VentanaInformacion.setHeaderText(null);
            VentanaInformacion.setContentText("Se va a eliminar el Asociado: "+txtnombre.getText()+" "+txtapellido.getText());
            VentanaInformacion.initStyle(StageStyle.UTILITY);
            VentanaInformacion.showAndWait();

            Alert VentanaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);  //Ventana de Confirmacion
            VentanaConfirmacion.setTitle("Eliminar un Asociado");
            VentanaConfirmacion.setHeaderText(null);
            VentanaConfirmacion.initStyle(StageStyle.UTILITY);
            VentanaConfirmacion.setContentText("BORRAR un Asociado no se puede deshacer, ¿Desea eliminarlo?");
            Optional<ButtonType> result = VentanaConfirmacion.showAndWait();
            if(result.get() == ButtonType.OK){
                Boolean resultado = controlAsociado.Borrar(code);
                if (resultado){
                    Alert VentanaInformacion2 = new Alert(Alert.AlertType.INFORMATION);
                    VentanaInformacion2.setTitle("Información");
                    VentanaInformacion2.setHeaderText(null);
                    VentanaInformacion2.setContentText("El Asociado ha sido eliminado con éxito.");
                    VentanaInformacion2.initStyle(StageStyle.UTILITY);
                    VentanaInformacion2.showAndWait();
                    this.limpiarcampos();
                    this.initListarTablas();
                }else{
                    Alert VentanaError = new Alert(Alert.AlertType.ERROR);
                    VentanaError.setTitle("Error");
                    VentanaError.setHeaderText(null);
                    VentanaError.setContentText("Error: Sentencia SQL - Devolucion false al borrar - Indique al desarrollador");
                    VentanaError.initStyle(StageStyle.UTILITY);
                    VentanaError.showAndWait();
                }

            }

        }catch(Exception e){
            Alert VentanaError = new Alert(Alert.AlertType.ERROR);
            VentanaError.setTitle("Error");
            VentanaError.setHeaderText(null);
            VentanaError.setContentText("Error: Línea 196 - inicioController.java - Indique al desarrollador");
            VentanaError.initStyle(StageStyle.UTILITY);
            VentanaError.showAndWait();
        }
    }

    public void initListarTablas(){
        //Inicializar la lista de asociados en la tabla jListAsociado Panel Asociados
        jListAsociado.setItems(controlAsociado.ListarTabla());
        clm_codigo.setCellValueFactory(new PropertyValueFactory<clsAsociado, String>("codigo"));
        clm_cedula.setCellValueFactory(new PropertyValueFactory<clsAsociado, String>("cedula"));
        clm_nombre.setCellValueFactory(new PropertyValueFactory<clsAsociado, String>("nombre"));
        clm_apellido.setCellValueFactory(new PropertyValueFactory<clsAsociado, String>("apellido"));
        clm_celular.setCellValueFactory(new PropertyValueFactory<clsAsociado, String>("celular"));

    }

    public void initTextFieldsFiltros(){
        //Inicializar los TextFileds  Letras
        txtnombre.addEventFilter(KeyEvent.ANY, filtroLetras);  //Se aplica el filtroLetras para que solo se escriban letras en ese campos
        txtapellido.addEventFilter(KeyEvent.ANY, filtroLetras);
        txtfamilia.addEventFilter(KeyEvent.ANY, filtroLetras);
        txtciudad.addEventFilter(KeyEvent.ANY, filtroLetras);
        txtavalador.addEventFilter(KeyEvent.ANY, filtroLetras);

        //Iniciar TextField de Numeros
        txtcedula.addEventFilter(KeyEvent.ANY, filtroNumeros);
        txttelefono.addEventFilter(KeyEvent.ANY, filtroNumeros);
        txtcelular.addEventFilter(KeyEvent.ANY, filtroNumeros);
        txtcedauth.addEventFilter(KeyEvent.ANY, filtroNumeros);
    }


    public void limpiarcampos(){
        txtcodigo.setText("");
        txtcedula.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        dpFecha_nac.setValue(null);
        txtfamilia.setText("");
        txtdireccion.setText("");
        txtciudad.setText("");
        txttelefono.setText("");
        txtcelular.setText("");
        txtcorreo.setText("");
        dpFecha_in.setValue(null);
        dpFecha_out.setValue(null);
        txtcedauth.setText("");
        txtavalador.setText("");
    }

    //Evento Handler para controlar lo que se escribe por teclado y que se puede asignar a los TextFields.
    EventHandler<KeyEvent> filtroLetras = new EventHandler<KeyEvent>() {
        //Variable boolean para continuar o parar el proceso
        private boolean continua = false;

        //Se llama evento
        @Override
        public void handle(KeyEvent event) {
            Object temp0 = event.getSource();
            if (continua){
               event.consume();  //Si no hay nada pues se acaba
            }   //Se captura lo escrito por el usuario
            String temp = event.getCode().toString();
            //Si presiona teclas letras min y mayus, si preciona borrar o Shift o Espacio, es permitido
            if (!event.getCode().toString().matches("[a-zA-Z]") && event.getCode() != KeyCode.BACK_SPACE
                    && event.getCode() != KeyCode.SHIFT && event.getCode() != KeyCode.SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    continua = true;
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    continua = false;
                }
            }
        }
    };

    EventHandler<KeyEvent> filtroNumeros = new EventHandler<KeyEvent>() {
        private boolean continua;
        private int cantMax = 10;

        @Override
        public void handle(KeyEvent event) {
            TextField temp = (TextField) event.getSource();

            if (continua) {
                event.consume();
            }

            if (!event.getText().matches("[0-9]") && event.getCode() != KeyCode.BACK_SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    continua = true;
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    continua = false;
                }
            }

            if (temp.getText().length() > cantMax - 1 && event.getCode() != KeyCode.BACK_SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    continua = true;
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    continua = false;
                }
            }

        }
    };


    EventHandler<KeyEvent> filtroFecha = new EventHandler<KeyEvent>() {
        private boolean continua;
        private int cantMax = 10;

        @Override
        public void handle(KeyEvent event) {
            TextField temp = (TextField) event.getSource();

            if (continua) {
                event.consume();
            }

            if (!event.getText().matches("[0-9]") && event.getCode() != KeyCode.BACK_SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    continua = true;
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    continua = false;
                }
            }

            if (temp.getText().length() > cantMax - 1 && event.getCode() != KeyCode.BACK_SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    continua = true;
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    continua = false;
                }
            }

        }
    };

    public void btnpruebafechas(){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate fecha = dpFecha_nac.getValue();
        Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
        controlAsociado.pruebaFechas(fecha);
    }

    public void ListarPrueba(){
        ObservableList<String> lista1 = null;
        lista1 = controlAsociado.Listar();
        System.out.println("Lista es: ");
        System.out.println(lista1);
    }

}