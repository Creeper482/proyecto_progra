public class Usuarios {

    private String nombre;
    private String apellido;
    private String correo;
    private int CI;
    private String password;

    public Usuarios (String nombre, String apellido, String correo, int CI, String password) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.correo= correo;
        this.CI = this.CI;
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

    public int getCI() {
        return CI;
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
                ", CI=" + CI +
                '}';
    }
}