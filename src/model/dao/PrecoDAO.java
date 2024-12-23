package model.dao;

import java.util.List;

import model.entities.Preco;

public interface PrecoDAO {
	
	void salvaPreco(Preco preco);
	List<Preco> buscarPorProduto(int produtoId);
	List<Preco> buscarPorLoja(int lojaId);
	
}
