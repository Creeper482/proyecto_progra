public class Proyectos {
    private String nombreProyecto;
    private String empresa;
    private String codProyecto;
    private String geolocalizacion;
    private String directorProyecto;
    private int rniDirector;
    private String cuce;

    public Proyectos(String nombreProyecto, String empresa, String codProyecto, String geolocalizacion, String directorProyecto, int rniDirector, String cuce){
        this.nombreProyecto=nombreProyecto;
        this.empresa=empresa;
        this.codProyecto=codProyecto;
        this.geolocalizacion=geolocalizacion;
        this.directorProyecto=directorProyecto;
        this.rniDirector=rniDirector;
        this.cuce=cuce;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public String getDirectorProyecto() {
        return directorProyecto;
    }

    public void setDirectorProyecto(String directorProyecto) {
        this.directorProyecto = directorProyecto;
    }

    public int getRniDirector() {
        return rniDirector;
    }

    public void setRniDirector(int rniDirector) {
        this.rniDirector = rniDirector;
    }

    public String getCuce() {
        return cuce;
    }

    public void setCuce(String cuce) {
        this.cuce = cuce;
    }
}
