package model.dao;

import java.util.List;

import model.entities.Preco;

public interface RelatorioDAO {

	List<Preco> compararPrecosEntreLojas(int produtoId);

	List<Preco> evolucaoPrecoProduto(int produtoId);

	List<Preco> filtrarPrecos(int lojaId, double minPreco, double maxPreco);

}
