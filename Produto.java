import java.util.Scanner;

public class Produto {
    private int codigo;
    private String nome;
    private int quantidade;
    private String tipo;
    private double valor;

    public Produto(int codigo) {
        this.codigo = codigo;
        this.nome = null;
        this.quantidade = 0;
        this.tipo = null;
        this.valor = 0.0;
    }

    public Produto(int codigo, String nome) {
        this(codigo);
        this.nome = nome;
    }

    public Produto(int codigo, String nome, int quantidade) {
        this(codigo, nome);
        this.quantidade = quantidade;
    }

    public Produto(int codigo, String nome, int quantidade, String tipo, double valor) {
        this(codigo, nome, quantidade);
        this.tipo = tipo;
        this.valor = valor;
    }

    public String vender(int quantidadeVendida) {
        if (quantidadeVendida > this.quantidade) {
            return "Estoque insuficiente para vender " + quantidadeVendida + " unidades.";
        } else {
            this.quantidade -= quantidadeVendida;
            double total = quantidadeVendida * this.valor;
            return "Venda de " + quantidadeVendida + " unidades realizada. Total: R$ " + String.format("%.2f", total);
        }
    }

    public void comprar(int quantidade, double novoValor) {
        this.quantidade += quantidade;
        this.valor = novoValor;
    }

    public void comprar(int quantidade) {
        this.quantidade += quantidade;
    }

    public void inserir(String nome, int quantidade, String tipo, double valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                ", Nome: " + nome +
                ", Quantidade: " + quantidade +
                ", Tipo: " + tipo +
                ", Valor: R$ " + String.format("%.2f", valor);
    }

    public boolean igual(Produto outro) {
        if (outro == null) return false;

        boolean nomeIgual = (this.nome == null && outro.nome == null) ||
                (this.nome != null && this.nome.equals(outro.nome));
        boolean tipoIgual = (this.tipo == null && outro.tipo == null) ||
                (this.tipo != null && this.tipo.equals(outro.tipo));

        return nomeIgual && tipoIgual;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Criando Produto 1 (código 1):");
        Produto p1 = new Produto(1);
        System.out.println("Insira os dados para Produto 1:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        p1.inserir(nome, quantidade, tipo, valor);

        Produto p2 = new Produto(2, "Jogo Master");
        Produto p3 = new Produto(3, "Jogo Quebra-Cabeça", 30);
        Produto p4 = new Produto(4, "Jogo Batalha Naval", 10, "jogo", 100.00);

        testarProduto(p1, scanner);
        testarProduto(p2, scanner);
        testarProduto(p3, scanner);
        testarProduto(p4, scanner);

        System.out.println("\nComparando p1 e p2: " + p1.igual(p2));
        System.out.println("Comparando p4 e p4: " + p4.igual(p4));

        scanner.close();
    }

    private static void testarProduto(Produto p, Scanner scanner) {
        System.out.println("\nTestando operações para " + p);

        System.out.print("Quantidade a vender: ");
        int qtdVender = scanner.nextInt();
        scanner.nextLine();
        System.out.println(p.vender(qtdVender));

        System.out.print("Quantidade a comprar (com novo valor): ");
        int qtdComprarComValor = scanner.nextInt();
        System.out.print("Novo valor: ");
        double novoValor = scanner.nextDouble();
        scanner.nextLine();
        p.comprar(qtdComprarComValor, novoValor);
        System.out.println("Após comprar com novo valor: " + p);

        System.out.print("Quantidade a comprar (sem alterar valor): ");
        int qtdComprar = scanner.nextInt();
        scanner.nextLine();
        p.comprar(qtdComprar);
        System.out.println("Após comprar sem alterar valor: " + p);

        System.out.println("Consulta: " + p);
    }
}