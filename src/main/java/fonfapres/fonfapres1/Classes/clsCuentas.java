package fonfapres.fonfapres1.Classes;

public class clsCuentas {

    private String idcuenta;
    private String codigo;
    private int saldo;
    private String tipoCuenta;

    public clsCuentas(String idcuenta, String codigo, int saldo, String tipoCuenta) {
        this.idcuenta = idcuenta;
        this.codigo = codigo;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(String idcuenta) {
        this.idcuenta = idcuenta;
    }
}
