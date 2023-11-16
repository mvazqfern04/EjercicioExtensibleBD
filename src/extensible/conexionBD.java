/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extensible;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class conexionBD {

    Connection con;
    Statement sta;

    public void abrirConexion() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pedidos", "root", "mario");
            sta = con.createStatement();
            System.out.println("Entra");
        } catch (SQLException ex) {
            Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
            sta.close();
            System.out.println("Sale");
        } catch (SQLException ex) {
            Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void engadirCliente(Cliente cliente) {
        try {
            String query = "INSERT INTO cliente (idCliente, dni, nome) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, cliente.getIdCliente());
            preparedStatement.setString(2, cliente.getDni());
            preparedStatement.setString(3, cliente.getNome());
            preparedStatement.executeUpdate();
            System.out.println("Cliente añadido: " + cliente.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modificarCliente(Cliente cliente) {
        try {
            String query = "UPDATE cliente SET dni=?, nome=? WHERE idCliente=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, cliente.getDni());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setInt(3, cliente.getIdCliente());
            preparedStatement.executeUpdate();
            System.out.println("Cliente modificado: " + cliente.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarCliente(int idCliente) {
        try {
            String query = "DELETE FROM cliente WHERE idCliente=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idCliente);
            preparedStatement.executeUpdate();
            System.out.println("Cliente eliminado con id: " + idCliente);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void engadirProduto(Produto produto) {
        try {
            String query = "INSERT INTO produto (idProduto, nome, descricion, prezo, foto) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, produto.getIdProduto());
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setDouble(4, produto.getPreco());
            preparedStatement.setString(5, produto.getFoto());
            preparedStatement.executeUpdate();
            System.out.println("Produto añadido: " + produto.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modificarProduto(Produto produto) {
        try {
            String query = "UPDATE produto SET nome=?, descricion=?, prezo=?, foto=? WHERE idProduto=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setString(4, produto.getFoto());
            preparedStatement.setInt(5, produto.getIdProduto());
            preparedStatement.executeUpdate();
            System.out.println("Produto modificado: " + produto.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarProduto(int idProduto) {
        try {
            String query = "DELETE FROM produto WHERE idProduto=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idProduto);
            preparedStatement.executeUpdate();
            System.out.println("Produto eliminado con id: " + idProduto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void engadirPedido(Pedido pedido) {
        try {
            String query = "INSERT INTO pedido (idPedido, idProduto, idCliente, importe) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, pedido.getIdPedido());
            preparedStatement.setInt(2, pedido.getIdProduto());
            preparedStatement.setInt(3, pedido.getIdCliente());
            preparedStatement.setDouble(4, pedido.getImporte());
            preparedStatement.executeUpdate();
            System.out.println("Pedido añadido: " + pedido.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modificarPedido(Pedido pedido) {
        try {
            String query = "UPDATE pedido SET idProduto=?, idCliente=?, importe=? WHERE idPedido=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, pedido.getIdProduto());
            preparedStatement.setInt(2, pedido.getIdCliente());
            preparedStatement.setDouble(3, pedido.getImporte());
            preparedStatement.setInt(4, pedido.getIdPedido());
            preparedStatement.executeUpdate();
            System.out.println("Pedido modificado: " + pedido.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarPedido(int idPedido) {
        try {
            String query = "DELETE FROM pedido WHERE idPedido=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idPedido);
            preparedStatement.executeUpdate();
            System.out.println("Pedido eliminado con id: " + idPedido);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int obtenerIdCliente(String dni, String nome) {
        int idCliente = 0;

        try {
            String query = "SELECT idCliente FROM cliente WHERE dni = ? AND nome = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, dni);
            preparedStatement.setString(2, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idCliente = resultSet.getInt("idCliente");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }

        return idCliente;
    }
    
    public List<Pedido> obtenerPedidosPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();

        try {
            String query = "SELECT * FROM pedido WHERE idCliente = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idCliente);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPedido = resultSet.getInt("idPedido");
                int idProducto = resultSet.getInt("idProducto");
                double importe = resultSet.getDouble("importe");

                Pedido pedido = new Pedido(idPedido, idProducto, idCliente, importe);
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pedidos;
    }
    
    public int contarPedidosConMasDe5Productos() {
        int cantidadPedidos = 0;

        try {
            String query = "SELECT COUNT(*) AS cantidad_pedidos FROM pedido WHERE idProducto > 5";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cantidadPedidos = resultSet.getInt("cantidad_pedidos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cantidadPedidos;
    }

}
