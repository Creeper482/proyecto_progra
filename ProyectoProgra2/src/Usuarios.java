public class Usuarios {

    private String nombre;
    private String apellido;
    private String correo;
    private int RNI;
    private String password;

public Usuarios (String nombre, String apellido, String correo, int RNI, String password) {

    this.nombre = nombre;
    this.apellido = apellido;
    this.correo= correo;
    this.RNI = RNI;
    this.password= password;


    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public int getRNI() {
        return RNI;
    }

    public String getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", RNI=" + RNI +
                '}';
}
}
