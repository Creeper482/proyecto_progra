public class Avance {
    private String fechaInicio;
    private String fechaFin;
    private String itemAvanzado;
    private String residente;
    private String ejeX;
    private String ejeY;
    private String nPiso;
    private Double cantidad;
    private Double costo;

    public Avance(String fechaInicio, String fechaFin, String itemAvanzado, String residente, String ejeX, String ejeY, String nPiso, double cantidad, double costo){
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.itemAvanzado=itemAvanzado;
        this.residente=residente;
        this.ejeX=ejeX;
        this.ejeY=ejeY;
        this.nPiso=nPiso;
        this.cantidad=cantidad;
        this.costo=costo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getItemAvanzado() {
        return itemAvanzado;
    }

    public void setItemAvanzado(String itemAvanzado) {
        this.itemAvanzado = itemAvanzado;
    }

    public String getResidente() {
        return residente;
    }

    public void setResidente(String residente) {
        this.residente = residente;
    }

    public String getEjeX() {
        return ejeX;
    }

    public void setEjeX(String ejeX) {
        this.ejeX = ejeX;
    }

    public String getEjeY() {
        return ejeY;
    }

    public void setEjeY(String ejeY) {
        this.ejeY = ejeY;
    }

    public String getnPiso() {
        return nPiso;
    }

    public void setnPiso(String nPiso) {
        this.nPiso = nPiso;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
