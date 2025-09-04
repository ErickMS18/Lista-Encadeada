package listaencadeada;
import java.util.Scanner;

public class ListaEncadeada {
    private Node lista;

    private static class Node {
        private int informacao;
        private Node proximo;

        public Node() {
            this.proximo = null;
        }

        public int getInformacao() {
            return informacao;
        }

        public void setInformacao(int informacao) {
            this.informacao = informacao;
        }

        public Node getProximo() {
            return proximo;
        }

        public void setProximo(Node proximo) {
            this.proximo = proximo;
        }
    }

    public ListaEncadeada() {
        this.lista = null;
    }

    public boolean vazia() {
        return lista == null;
    }

    private Node erroListaVazia(String metodo) {
        System.out.println("Erro em " + metodo + ": A lista está vazia");
        return null;
    }

    private Node criarNo(int info) {
        Node no = new Node();
        no.setInformacao(info);
        no.setProximo(null);
        return no;
    }

    public void inserir(int informacao) {
        Node no = criarNo(informacao);

        if (vazia()) {
            lista = no;
        } else {
            Node atual = lista;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(no);
        }
    }

    public void inserePrimeiro(int info) {
        Node novoNo = criarNo(info);

        if (vazia()) {
            lista = novoNo;
        } else {
            novoNo.setProximo(lista);
            lista = novoNo;
        }
    }

    public void insereDepois(Node node, int info) {
        if (node == null) {
            System.out.println("Erro: Node é nulo");
            return;
        }

        Node novoNo = criarNo(info);
        novoNo.setProximo(node.getProximo());
        node.setProximo(novoNo);
    }

    public void insereUltimo(int info) {
        inserir(info);
    }

    public void insereOrdenado(int info) {
        Node novoNo = criarNo(info);

        if (vazia() || info < lista.getInformacao()) {
            inserePrimeiro(info);
            return;
        }

        Node atual = lista;
        while (atual.getProximo() != null && atual.getProximo().getInformacao() < info) {
            atual = atual.getProximo();
        }

        novoNo.setProximo(atual.getProximo());
        atual.setProximo(novoNo);
    }

    public Node removePrimeiro() {
        if (vazia()) {
            return erroListaVazia("removePrimeiro");
        }

        Node removido = lista;
        lista = lista.getProximo();
        removido.setProximo(null);
        return removido;
    }

    public Node removeUltimo() {
        if (vazia()) {
            return erroListaVazia("removeUltimo");
        }

        if (lista.getProximo() == null) {
            Node removido = lista;
            lista = null;
            return removido;
        }

        Node anterior = null;
        Node atual = lista;

        while(atual.getProximo() != null) {
            anterior = atual;
            atual = atual.getProximo();
        }

        anterior.setProximo(null);
        return atual;
    }

    public Node remove(int indice) {
        if (vazia()) {
            return erroListaVazia("remove");
        }

        if (indice < 0) {
            System.out.println("Erro: Índice é negativo");
            return null;
        }

        if (indice == 0) {
            return removePrimeiro();
        }

        Node anterior = null;
        Node atual = lista;
        int posicaoAtual = 0;

        while (atual != null && posicaoAtual < indice) {
            anterior = atual;
            atual = atual.getProximo();
            posicaoAtual ++;
        }

        if (atual == null) {
            System.out.println("Erro: Índice " + indice + " fora dos limites da lista");
            return null;
        }

        anterior.setProximo(atual.getProximo());
        atual.setProximo(null);
        return atual;
    }

    public void imprime() {
        if (vazia()) {
            System.out.println("Lista vazia!");
            return;
        }

        Node atual = lista;
        while (atual != null) {
            System.out.print(atual.getInformacao() + " -> ");
            atual = atual.getProximo();
        }
        System.out.println("Null");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEncadeada lista = new ListaEncadeada();

        System.out.println("~~~~*o* EXEMPLOS *o*~~~~");

        System.out.println("Lista vazia: " + lista.vazia());

        lista.inserir(12);
        lista.inserir(18);
        System.out.print("Lista Atual: ");
        lista.imprime();

        lista.inserePrimeiro(10);
        System.out.print("Após inserePrimeiro(10): ");
        lista.imprime();

        lista.insereUltimo(30);
        System.out.print("Após insereUltimo(30): ");
        lista.imprime();

        lista.insereOrdenado(15);
        System.out.print("Após insereOrdenado(15): ");
        lista.imprime();

        System.out.print("Lista completa: ");
        lista.imprime();

        Node removido1 = lista.removePrimeiro();
        System.out.println("Removeu primeiro: " + removido1.getInformacao());
        System.out.print("Lista atual: ");
        lista.imprime();

        Node removido2 = lista.removeUltimo();
        System.out.println("Removeu último: " + removido2.getInformacao());
        System.out.print("Lista atual: ");
        lista.imprime();

        Node removido3 = lista.remove(1);
        System.out.println("Removeu índice 1: " + removido3.getInformacao());
        System.out.print("Lista final: ");
        lista.imprime();

        System.out.println("Lista vazia: " + lista.vazia());

        scanner.close();
    }
}