import java.text.SimpleDateFormat;
import java.util.Date;

public class Avance {
    private Date fechaInicio;
    private Date fechaFin;
    private Items itemAvanzado;
    private Double cantidad;
    private Double costo;

    public Avance(Date fechaInicio, Date fechaFin, Items itemAvanzado, double cantidad){
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.itemAvanzado=itemAvanzado;
        this.cantidad=cantidad;
        this.costo=this.cantidad*this.itemAvanzado.getPU();
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Items getItemAvanzado() {
        return itemAvanzado;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public String getNombreDelItemAvanzado() {
        return itemAvanzado.getNombreItem();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Avance del Item: " + getNombreDelItemAvanzado() +
                " | Fecha Inicio: " + sdf.format(fechaInicio) +
                " | Fecha Fin: " + sdf.format(fechaFin) +
                " | Cantidad: " + cantidad +
                " | Costo: " + costo;
    }
}
