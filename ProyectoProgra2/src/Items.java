public class Items {
    private int idItem;
    private String nItem;
    private Double PU;
    private String unidades;
    private String creadoPor;

    public Items(int idItem,String nItem, double PU, String unidades, String creadoPor){
        this.idItem=idItem;
        this.nItem=nItem;
        this.PU=PU;
        this.unidades=unidades;
        this.creadoPor=creadoPor;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getItem() {
        return nItem;
    }

    public void setItem(String item) {
        nItem = item;
    }

    public Double getPU() {
        return PU;
    }

    public void setPU(Double PU) {
        this.PU = PU;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
}
