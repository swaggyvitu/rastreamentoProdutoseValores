package model.entities;

import java.time.LocalDateTime;

public class Preco {
    private int id; 
    private int produtoId;
    private int lojaId;
    private double preco;
    private LocalDateTime dataInsercao;

    
    public Preco(int id, int produtoId, int lojaId, double preco, LocalDateTime dataInsercao) {
        this.id = id;
        this.produtoId = produtoId;
        this.lojaId = lojaId;
        this.preco = preco;
        this.dataInsercao = dataInsercao;
    }

    public Preco(int produtoId, int lojaId, double preco, LocalDateTime dataInsercao) {
        this.produtoId = produtoId;
        this.lojaId = lojaId;
        this.preco = preco;
        this.dataInsercao = dataInsercao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id; 
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(LocalDateTime dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    @Override
    public String toString() {
        return "\nPreco {" +  
               ",\n  produtoId: " + produtoId + 
               ",\n  lojaId: " + lojaId + 
               ",\n  preco: " + preco + 
               ",\n  dataInsercao: " + dataInsercao + 
               "\n}";
    }

}
