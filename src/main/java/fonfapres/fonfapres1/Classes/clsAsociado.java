package fonfapres.fonfapres1.Classes;

import java.time.LocalDate;

public class clsAsociado extends clsPersona {

    private String codigo;
    private LocalDate fecha_in;
    private LocalDate fecha_out;
    private String familia;
    private String persona_rec;
    private String cedula_rec;
    private String avalador;

    public clsAsociado(String cedula, String nombre, String apellido, LocalDate fecha_nac, String ciudad, String direccion, String telefono, String celular, String correo, String codigo, LocalDate fecha_in, LocalDate fecha_out, String familia, String persona_rec, String cedula_rec, String avalador) {
        super(cedula, nombre, apellido, fecha_nac, ciudad, direccion, telefono, celular, correo);
        this.codigo = codigo;
        this.fecha_in = fecha_in;
        this.fecha_out = fecha_out;
        this.familia = familia;
        this.persona_rec = persona_rec;
        this.cedula_rec = cedula_rec;
        this.avalador = avalador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha_in() {
        return fecha_in;
    }

    public void setFecha_in(LocalDate fecha_in) {
        this.fecha_in = fecha_in;
    }

    public LocalDate getFecha_out() {
        return fecha_out;
    }

    public void setFecha_out(LocalDate fecha_out) {
        this.fecha_out = fecha_out;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getPersona_rec() {
        return persona_rec;
    }

    public void setPersona_rec(String persona_rec) {
        this.persona_rec = persona_rec;
    }

    public String getCedula_rec() {
        return cedula_rec;
    }

    public void setCedula_rec(String cedula_rec) {
        this.cedula_rec = cedula_rec;
    }

    public String getAvalador() {
        return avalador;
    }

    public void setAvalador(String avalador) {
        this.avalador = avalador;
    }
}
