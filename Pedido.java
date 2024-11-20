public class Pedido {
private String alimento;
private int cantidad;

public Pedido(String alimento, int cantidad) {
  this.alimento = alimento;
  this.cantidad = cantidad;
}

public String getAlimento() {
  return alimento;
}

public int getCantidad() {
  return cantidad;
}
}
