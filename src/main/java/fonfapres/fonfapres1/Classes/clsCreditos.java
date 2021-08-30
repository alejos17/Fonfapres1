package fonfapres.fonfapres1.Classes;

public class clsCreditos {

    private String codigo;
    private String idCredito;
    private double interes;
    private double seguro;
    private int cuotas;
    private double cuota;
    private double intCuota;
    private double capital;
    private double intereses;

    public clsCreditos(String codigo, String idCredito, double interes, double seguro, int cuotas, double cuota, double intCuota, double capital, double intereses) {
        this.codigo = codigo;
        this.idCredito = idCredito;
        this.interes = interes;
        this.seguro = seguro;
        this.cuotas = cuotas;
        this.cuota = cuota;
        this.intCuota = intCuota;
        this.capital = capital;
        this.intereses = intereses;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(String idCredito) {
        this.idCredito = idCredito;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getSeguro() {
        return seguro;
    }

    public void setSeguro(double seguro) {
        this.seguro = seguro;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public double getIntCuota() {
        return intCuota;
    }

    public void setIntCuota(double intCuota) {
        this.intCuota = intCuota;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }
}
