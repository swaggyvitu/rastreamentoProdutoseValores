package model.dao;

import java.util.List;

import model.entities.Produto;

public interface ProdutoDAO {
	
	void inserirProduto(Produto produto);
	Produto buscarProduto(int id);
	List<Produto> listarProdutos();
	void atualizaProduto(Produto produto);
	void deletarProduto(int id);

}
