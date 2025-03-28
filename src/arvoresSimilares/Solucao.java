package arvoresSimilares;

import java.util.Scanner;

public class Solucao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tamanho = sc.nextInt();
		sc.nextLine();
		BST bstA = new BST();
		BST bstB = new BST();

		for (int i = 0; i < tamanho; i++) {
			int elementoA = sc.nextInt();
			bstA.adiciona(elementoA);
		}

		for (int i = 0; i < tamanho; i++) {
			int elementoB = sc.nextInt();
			bstB.adiciona(elementoB);
		}

		System.out.println((bstA.saoSimilares(bstB)) ? "Arvores similares." : "Arvores com estruturas diferentes.");

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

	public boolean saoSimilares(BST outra) {
		return this.saoSimilares(this.raiz, outra.raiz);
	}

	private boolean saoSimilares(Node noA, Node noB) {
		if (noA == null && noB == null)
			return true;

		if (noA == null || noB == null)
			return false;

		return this.saoSimilares(noA.esquerdo, noB.esquerdo) && this.saoSimilares(noA.direito, noB.direito);
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
