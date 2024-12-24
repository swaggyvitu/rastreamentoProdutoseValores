package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.PrecoDAO;
import model.entities.Preco;

public class PrecoDaoJDBC implements PrecoDAO {

	private Connection conn;

	public PrecoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void salvaPreco(Preco preco) {
		String sql = "INSERT INTO Precos (produto_id, loja_id, preco, data_insercao) VALUES (?, ?, ?, ?)";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, preco.getProdutoId());
			st.setInt(2, preco.getLojaId());
			st.setDouble(3, preco.getPreco());
			st.setTimestamp(4, Timestamp.valueOf(preco.getDataInsercao()));

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						int id = rs.getInt(1);
						preco.setId(id);
					}
				}
				System.out.println("Preço salvo com sucesso!");
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public List<Preco> buscarPorProduto(int produtoId) {
		List<Preco> precos = new ArrayList<>();
		String sql = "SELECT * FROM Precos WHERE produto_id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, produtoId);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				precos.add(new Preco(rs.getInt("produto_id"), rs.getInt("loja_id"), rs.getDouble("preco"),
						rs.getTimestamp("data_insercao").toLocalDateTime()));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return precos;
	}

	@Override
	public List<Preco> buscarPorLoja(int lojaId) {
		List<Preco> precos = new ArrayList<>();
		String sql = "SELECT * FROM Precos WHERE loja_id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, lojaId);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				precos.add(new Preco(rs.getInt("produto_id"), rs.getInt("loja_id"), rs.getDouble("preco"),
						rs.getTimestamp("data_insercao").toLocalDateTime()));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

		return precos;
	}

}
