class Items {
    private static int contadorId = 1;

    private int Id;
    private String nombreItem;
    private Double PU;
    private String unidades;


    public Items(String nombreItem, double PU, String unidades) {
        this.Id = contadorId++;
        this.nombreItem = nombreItem;
        this.PU = PU;
        this.unidades = unidades;

    }

    public int getId() {
        return Id;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public Double getPU() {
        return PU;
    }

    public String getUnidades() {
        return unidades;
    }

    @Override
    public String toString() {
        return "Item{" +
                "Id=" + Id +
                ", nombreItem='" + nombreItem + '\'' +
                ", precioUnitario=" + PU +
                ", unidades=" + unidades +
                '}';
    }
}