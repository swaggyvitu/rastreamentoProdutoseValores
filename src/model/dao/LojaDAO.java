package model.dao;

import java.util.List;

import model.entities.Loja;

public interface LojaDAO {
	
	void inserirLoja(Loja loja);
	Loja buscarLoja(int id);
	List<Loja> listarLojas();
	void atualizaLojas(Loja loja);
	void deletarLoja(int id);
	
}
