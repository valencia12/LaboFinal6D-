package modelo;

/**
 *
 * @author LN710Q
 */
public class Estudiante {
   private int id;
   private String carnet;
   private String nombre;
   private String apellido;
   private int edad;
   private String universidad;
   private boolean estado;

    public Estudiante(int id, String carnet, String nombre, String apellido, int edad, String universidad, boolean estado) {
        this.id = id;
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.universidad = universidad;
        this.estado = estado;
    }

    public Estudiante(String carnet, String nombre, String apellido, int edad, String universidad, boolean estado) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.universidad = universidad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id=" + id + ", carnet=" + carnet + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", universidad=" + universidad + ", estado=" + estado + '}';
    }
   
}