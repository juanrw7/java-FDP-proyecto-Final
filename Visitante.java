public class Visitante {
  private String nombre;
  private String correo;

  public Visitante(String nombre, String correo) {
      this.nombre = nombre;
      this.correo = correo;
  }

  public String getNombre() {
      return nombre;
  }

  public String getCorreo() {
      return correo;
  }
}
