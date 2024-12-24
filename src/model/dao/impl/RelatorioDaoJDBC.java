package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.RelatorioDAO;
import model.entities.Preco;

public class RelatorioDaoJDBC implements RelatorioDAO {
	
	private Connection conn;

	public RelatorioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
    @Override
    public List<Preco> compararPrecosEntreLojas(int produtoId) {
        List<Preco> precos = new ArrayList<>();
        String sql = "SELECT * FROM Preco WHERE produto_id = ? ORDER BY preco";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, produtoId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                precos.add(new Preco(
                        rs.getInt("produto_id"),
                        rs.getInt("loja_id"),
                        rs.getDouble("preco"),
                        rs.getTimestamp("data_insercao").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precos;
    }

    @Override
    public List<Preco> evolucaoPrecoProduto(int produtoId) {
        List<Preco> precos = new ArrayList<>();
        String sql = "SELECT * FROM Preco WHERE produto_id = ? ORDER BY data_insercao";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, produtoId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                precos.add(new Preco(
                        rs.getInt("produto_id"),
                        rs.getInt("loja_id"),
                        rs.getDouble("preco"),
                        rs.getTimestamp("data_insercao").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precos;
    }

    @Override
    public List<Preco> filtrarPrecos(int lojaId, double minPreco, double maxPreco) {
        List<Preco> precos = new ArrayList<>();
        String sql = "SELECT * FROM Preco WHERE loja_id = ? AND preco BETWEEN ? AND ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, lojaId);
            st.setDouble(2, minPreco);
            st.setDouble(3, maxPreco);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                precos.add(new Preco(
                        rs.getInt("produto_id"),
                        rs.getInt("loja_id"),
                        rs.getDouble("preco"),
                        rs.getTimestamp("data_insercao").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precos;
    }
}