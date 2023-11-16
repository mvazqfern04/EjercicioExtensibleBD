package extensible;


public class Pedido {
    private int idPedido;
    private int idProduto;
    private int idCliente;
    private double importe;

    public Pedido(int idPedido, int idProduto, int idCliente, double importe) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.idCliente = idCliente;
        this.importe = importe;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idProduto=" + idProduto +
                ", idCliente=" + idCliente +
                ", importe=" + importe +
                '}';
    }
}
