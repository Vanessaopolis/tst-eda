package predecessorBST;

import java.util.ArrayList;
import java.util.Scanner;

public class Solucao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] elementosStr = sc.nextLine().split(" ");
		int[] elementosInt = new int[elementosStr.length];
		for (int i = 0; i < elementosStr.length; i++)
			elementosInt[i] = Integer.parseInt(elementosStr[i]);
		int n = sc.nextInt();

		BST bst = new BST();
		for (int e : elementosInt)
			bst.adiciona(e);

		ArrayList<Integer> caminho = new ArrayList<Integer>();
		bst.predecessor(bst.busca(n), caminho);
		System.out.println(caminho.toString());

		sc.close();
	}

}

class BST {

	private Node raiz;

	public BST() {
		this.raiz = null;
	}

	public boolean estaVazia() {
		return this.raiz == null;
	}

	public void adiciona(int valor) {
		if (this.estaVazia()) {
			this.raiz = new Node(valor);
		} else
			this.adiciona(this.raiz, new Node(valor));
	}

	public Node predecessor(Node no, ArrayList<Integer> caminho) {
		if (no == null)
			return null;

		caminho.add(no.valor);
		if (no.esquerdo != null)
			return this.encontraExtremo(no.esquerdo, false, caminho);

		Node atual = no;
		Node aux = no.pai;
		while (aux != null && atual == aux.esquerdo) {
			caminho.add(aux.valor);
			atual = aux;
			aux = aux.pai;
		}

		if (aux != null)
			caminho.add(aux.valor);

		return aux;
	}

	public Node busca(int valor) {
		return busca(this.raiz, valor);
	}

	private Node busca(Node atual, int valor) {
		if (atual == null)
			return null;

		return (valor < atual.valor) ? busca(atual.esquerdo, valor)
				: (valor > atual.valor) ? busca(atual.direito, valor) : atual;
	}

	private Node encontraExtremo(Node atual, boolean ehMin, ArrayList<Integer> caminho) {
		caminho.add(atual.valor);
		if ((ehMin && atual.esquerdo == null) || (!ehMin && atual.direito == null))
			return atual;

		return this.encontraExtremo((ehMin) ? atual.esquerdo : atual.direito, ehMin, caminho);
	}

	private void adiciona(Node atual, Node novoNo) {
		if (novoNo.valor < atual.valor) {
			if (atual.esquerdo == null) {
				atual.esquerdo = novoNo;
				atual.esquerdo.pai = atual;
			} else
				this.adiciona(atual.esquerdo, novoNo);

		} else if (novoNo.valor > atual.valor) {
			if (atual.direito == null) {
				atual.direito = novoNo;
				atual.direito.pai = atual;
			} else
				this.adiciona(atual.direito, novoNo);
		}
	}

}

class Node {

	int valor;
	Node esquerdo;
	Node direito;
	Node pai;

	Node(int valor) {
		this.valor = valor;
	}

	boolean ehFolha() {
		return this.esquerdo == null && this.direito == null;
	}

	boolean temApenasFilhoEsquerdo() {
		return this.esquerdo != null && this.direito == null;
	}

	boolean temApenasFilhoDireito() {
		return this.esquerdo == null && this.direito != null;
	}

}
