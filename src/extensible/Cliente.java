package extensible;


public class Cliente {
    private int idCliente;
    private String dni;
    private String nome;

    public Cliente(int idCliente, String dni, String nome) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nome = nome;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", dni='" + dni + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}


