package application;

import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import model.dao.impl.LojaDaoJDBC;
import model.dao.impl.PrecoDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;

public class Program {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in); Connection conn = DB.getConnection()) {
            LojaDaoJDBC lojaDao = new LojaDaoJDBC(conn);
            ProdutoDaoJDBC produtoDao = new ProdutoDaoJDBC(conn);
            PrecoDaoJDBC precoDao = new PrecoDaoJDBC(conn);

            int opcao;
            do {
                System.out.println("\n--- Menu Principal ---");
                System.out.println("1. Gerenciar Lojas");
                System.out.println("2. Gerenciar Produtos");
                System.out.println("3. Gerenciar Preços");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1 -> GerenciamentoMenu.gerenciarLojas(scanner, lojaDao);
                    case 2 -> GerenciamentoMenu.gerenciarProdutos(scanner, produtoDao);
                    case 3 -> GerenciamentoMenu.gerenciarPrecos(scanner, precoDao);
                    case 0 -> System.out.println("Encerrando o programa.");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
