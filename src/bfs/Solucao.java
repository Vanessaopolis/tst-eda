package bfs;

import java.util.LinkedList;
import java.util.Scanner;

public class Solucao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] elementosStr = sc.nextLine().split(" ");
		int[] elementosInt = new int[elementosStr.length];
		for (int i = 0; i < elementosStr.length; i++)
			elementosInt[i] = Integer.parseInt(elementosStr[i]);

		BST bst = new BST();
		for (int e : elementosInt)
			bst.adiciona(e);

		System.out.println(bst.bfs());

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

	public String bfs() {
		if (this.estaVazia())
			return "";

		StringBuilder resultado = new StringBuilder();
		LinkedList<Node> fila = new LinkedList<>();
		fila.add(this.raiz);

		while (!fila.isEmpty()) {
			Node atual = fila.removeFirst();
			resultado.append(atual.valor).append(" ");

			if (atual.esquerdo != null)
				fila.add(atual.esquerdo);

			if (atual.direito != null)
				fila.add(atual.direito);
		}

		return resultado.toString().trim();
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
