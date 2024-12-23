package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.ProdutoDAO;
import model.entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDAO {
	
	private Connection conn;

	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserirProduto(Produto produto) {
		String sql = "INSERT Produtos (nome, categoria) VALUES (?, ?)";

		try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			st.setString(1, produto.getNome());
			st.setString(2, produto.getCategoria());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						int id = rs.getInt(1);
						produto.setId(id);
					}
				}
				System.out.println("Produto inserido com sucesso!");
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Produto buscarProduto(int id) {
		 String sql = "SELECT * FROM Produtos WHERE id = ?";
	        try (PreparedStatement st = conn.prepareStatement(sql)) {
	            st.setInt(1, id);

	            try (ResultSet rs = st.executeQuery()) {
	                if (rs.next()) {
	                    Produto produto = new Produto(rs.getString("nome"), rs.getString("categoria"));
	                    produto.setId(rs.getInt("id"));
	                    return produto;
	                }
	            }
	        } catch (SQLException e) {
	            throw new DbException("Erro ao buscar produto: " + e.getMessage());
	        }
	        return null;
	}

	@Override
	public List<Produto> listarProdutos() {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT * FROM Produtos ORDER BY nome";
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setCategoria(rs.getString("categoria"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return produtos;
	}

	@Override
	public void atualizaProduto(Produto produto) {
		String sql = "UPDATE Produtos SET " + "nome = ?, categoria = ? " + "WHERE id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setString(1, produto.getNome());
			st.setString(2, produto.getCategoria());
			st.setInt(3, produto.getId());
			
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				 System.out.println("Produto atualizado com sucesso: " + produto.getNome());
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void deletarProduto(int id) {
	    if (id <= 0) {
	        throw new IllegalArgumentException("ID inválido para o produto.");
	    }

	    String sql = "DELETE FROM Produtos WHERE id = ?";
	    try (PreparedStatement st = conn.prepareStatement(sql)) {
	        st.setInt(1, id);

	        int rowsAffected = st.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Produto com ID " + id + " deletado com sucesso!");
	        } else {
	            throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
	        }
	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

	
}
