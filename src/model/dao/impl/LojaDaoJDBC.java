package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.LojaDAO;
import model.entities.Loja;

public class LojaDaoJDBC implements LojaDAO {

	private Connection conn;

	public LojaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserirLoja(Loja loja) {
		String sql = "INSERT INTO Lojas (nome, endereco, contato) VALUES (?, ?, ?)";

		try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			st.setString(1, loja.getNome());
			st.setString(2, loja.getEndereco());
			st.setString(3, loja.getContato());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						int id = rs.getInt(1);
						loja.setId(id);
					}
				}
				System.out.println("Loja inserida com sucesso!");
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
    public Loja buscarLoja(int id) {
        String sql = "SELECT * FROM Lojas WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Loja loja = new Loja(rs.getString("nome"), rs.getString("endereco"), rs.getString("contato"));
                    loja.setId(rs.getInt("id"));
                    return loja;
                }
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao buscar loja: " + e.getMessage());
        }
        return null;
    }

	@Override
	public List<Loja> listarLojas() {
		List<Loja> lojas = new ArrayList<>();
		String sql = "SELECT * FROM Lojas ORDER BY nome";
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Loja loja = new Loja();
				loja.setId(rs.getInt("id"));
				loja.setNome(rs.getString("nome"));
				loja.setEndereco(rs.getString("endereco"));
				loja.setContato(rs.getString("contato"));
				lojas.add(loja);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return lojas;
	}

	@Override
	public void atualizaLojas(Loja loja) {
		String sql = "UPDATE Lojas SET " + "nome = ?, endereco = ?, contato = ? " + "WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, loja.getNome());
			st.setString(2, loja.getEndereco());
			st.setString(3, loja.getContato());
			st.setInt(4, loja.getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				 System.out.println("Loja atualizada com sucesso: " + loja.getNome());
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void deletarLoja(int id) {
		if (id <= 0) {
	        throw new IllegalArgumentException("ID inválido para a loja.");
	    }
		
		String sql = "DELETE FROM Lojas WHERE id = ?";
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Loja deletada com sucesso!");
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
