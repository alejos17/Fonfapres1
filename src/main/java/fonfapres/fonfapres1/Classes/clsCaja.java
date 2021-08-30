package fonfapres.fonfapres1.Classes;

public class clsCaja {

    private String codigo;
    private String nRecibo;
    private String fecha;
    private String concepto;
    private int valor;

    public clsCaja(String codigo, String nRecibo, String fecha, String concepto, int valor) {
        this.codigo = codigo;
        this.nRecibo = nRecibo;
        this.fecha = fecha;
        this.concepto = concepto;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getnRecibo() {
        return nRecibo;
    }

    public void setnRecibo(String nRecibo) {
        this.nRecibo = nRecibo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
