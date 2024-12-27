package application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import model.dao.impl.LojaDaoJDBC;
import model.dao.impl.PrecoDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
import model.entities.Loja;
import model.entities.Preco;
import model.entities.Produto;

public class GerenciamentoMenu {

    public static void gerenciarLojas(Scanner scanner, LojaDaoJDBC lojaDao) {
        System.out.println("\n--- Gerenciar Lojas ---");
        System.out.println("1. Inserir Loja");
        System.out.println("2. Listar Lojas");
        System.out.println("3. Buscar Loja por ID");
        System.out.println("4. Atualizar Loja");
        System.out.println("5. Deletar Loja");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                System.out.print("Nome: ");
                scanner.nextLine();
                String nome = scanner.nextLine();
                System.out.print("Endereço: ");
                String endereco = scanner.nextLine();
                System.out.print("Contato: ");
                String contato = scanner.nextLine();

                Loja loja = new Loja(nome, endereco, contato);
                lojaDao.inserirLoja(loja);
            }
            case 2 -> {
                List<Loja> lojas = lojaDao.listarLojas();
                lojas.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("ID da Loja: ");
                int id = scanner.nextInt();
                Loja loja = lojaDao.buscarLoja(id);
                System.out.println(loja != null ? loja : "Loja não encontrada.");
            }
            case 4 -> {
                System.out.print("ID da Loja: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo Endereço: ");
                String endereco = scanner.nextLine();
                System.out.print("Novo Contato: ");
                String contato = scanner.nextLine();

                Loja loja = new Loja(nome, endereco, contato);
                loja.setId(id);
                lojaDao.atualizaLojas(loja);
            }
            case 5 -> {
                System.out.print("ID da Loja: ");
                int id = scanner.nextInt();
                lojaDao.deletarLoja(id);
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void gerenciarProdutos(Scanner scanner, ProdutoDaoJDBC produtoDao) {
        System.out.println("\n--- Gerenciar Produtos ---");
        System.out.println("1. Inserir Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Buscar Produto por ID");
        System.out.println("4. Atualizar Produto");
        System.out.println("5. Deletar Produto");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                System.out.print("Nome: ");
                scanner.nextLine();
                String nome = scanner.nextLine();
                System.out.print("Categoria: ");
                String categoria = scanner.nextLine();

                Produto produto = new Produto(nome, categoria);
                produtoDao.inserirProduto(produto);
            }
            case 2 -> {
                List<Produto> produtos = produtoDao.listarProdutos();
                produtos.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("ID do Produto: ");
                int id = scanner.nextInt();
                Produto produto = produtoDao.buscarProduto(id);
                System.out.println(produto != null ? produto : "Produto não encontrado.");
            }
            case 4 -> {
                System.out.print("ID do Produto: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Nova Categoria: ");
                String categoria = scanner.nextLine();

                Produto produto = new Produto(nome, categoria);
                produto.setId(id);
                produtoDao.atualizaProduto(produto);
            }
            case 5 -> {
                System.out.print("ID do Produto: ");
                int id = scanner.nextInt();
                produtoDao.deletarProduto(id);
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void gerenciarPrecos(Scanner scanner, PrecoDaoJDBC precoDao) {
        System.out.println("\n--- Gerenciar Preços ---");
        System.out.println("1. Salvar Preço");
        System.out.println("2. Buscar Preços por Produto");
        System.out.println("3. Buscar Preços por Loja");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                System.out.print("ID do Produto: ");
                int produtoId = scanner.nextInt();
                System.out.print("ID da Loja: ");
                int lojaId = scanner.nextInt();
                System.out.print("Preço: ");
                double preco = scanner.nextDouble();

                Preco precoEntity = new Preco(produtoId, lojaId, preco, LocalDateTime.now());
                precoDao.salvaPreco(precoEntity);
            }
            case 2 -> {
                System.out.print("ID do Produto: ");
                int produtoId = scanner.nextInt();
                List<Preco> precos = precoDao.buscarPorProduto(produtoId);
                precos.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("ID da Loja: ");
                int lojaId = scanner.nextInt();
                List<Preco> precos = precoDao.buscarPorLoja(lojaId);
                precos.forEach(System.out::println);
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}
