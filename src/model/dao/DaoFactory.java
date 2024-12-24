package model.dao;

import db.DB;
import model.dao.impl.LojaDaoJDBC;
import model.dao.impl.PrecoDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
import model.dao.impl.RelatorioDaoJDBC;

public class DaoFactory {
	
	public static LojaDAO createLojaDao() {
		return new LojaDaoJDBC(DB.getConnection());
	}
	
	public static PrecoDAO createPrecoDao() {
		return new PrecoDaoJDBC(DB.getConnection());
	}
	
	public static ProdutoDAO createProdutoDao() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}
	
	public static RelatorioDAO createRelatorioDao() {
		return new RelatorioDaoJDBC(DB.getConnection());
	}
	
}
