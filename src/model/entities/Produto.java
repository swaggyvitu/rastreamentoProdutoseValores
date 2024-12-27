package model.entities;

public class Produto {
	private int id;
	private String nome;
	private String categoria;
	
	public Produto() {}

	public Produto(String nome, String categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	@Override
	public String toString() {
	    return String.format("Produto {\n" +
	                         "  ID: %d\n" +
	                         "  Nome: %s\n" +
	                         "  Categoria: %s\n" +
	                         "}", id, nome, categoria);
	}

}
