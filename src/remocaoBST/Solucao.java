package remocaoBST;

import java.util.ArrayList;
import java.util.Scanner;

public class Solucao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] elementosStr = sc.nextLine().split(" ");
		int[] elementosInt = new int[elementosStr.length];
		for (int i = 0; i < elementosStr.length; i++)
			elementosInt[i] = Integer.parseInt(elementosStr[i]);

		String[] removidosStr = sc.nextLine().split(" ");
		int[] removidosInt = new int[removidosStr.length];
		for (int i = 0; i < removidosStr.length; i++)
			removidosInt[i] = Integer.parseInt(removidosStr[i]);

		BST bst = new BST();
		for (int e : elementosInt)
			bst.adiciona(e);

		for (int n : removidosInt) {
			bst.remove(n);
			System.out.println(bst.estaVazia() ? "null" : bst.preOrdem());
		}

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

	public String preOrdem() {
		ArrayList<Integer> elementos = new ArrayList<Integer>();
		preOrdem(this.raiz, elementos);
		return elementos.toString();
	}

	public void remove(int valor) {
		this.remove(this.busca(valor));
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

	public Node sucessor(Node no) {
		if (no == null)
			return null;

		if (no.direito != null)
			return this.encontraExtremo(no.direito, true);

		Node aux = no.pai;
		while (aux != null && aux.valor < no.valor)
			aux = aux.pai;

		return aux;
	}

	private void remove(Node no) {
		if (no == null)
			return;

		if (no.ehFolha()) {
			if (this.raiz == no) {
				this.raiz = null;
			} else {
				if (no.valor < no.pai.valor)
					no.pai.esquerdo = null;
				else
					no.pai.direito = null;
			}

		} else if (no.temApenasFilhoEsquerdo()) {
			if (this.raiz == no) {
				this.raiz = no.esquerdo;
				this.raiz.pai = null;
			} else {
				no.esquerdo.pai = no.pai;
				if (no.valor < no.pai.valor)
					no.pai.esquerdo = no.esquerdo;
				else
					no.pai.direito = no.esquerdo;
			}

		} else if (no.temApenasFilhoDireito()) {
			if (this.raiz == no) {
				this.raiz = no.direito;
				this.raiz.pai = null;
			} else {
				no.direito.pai = no.pai;
				if (no.valor < no.pai.valor)
					no.pai.esquerdo = no.direito;
				else
					no.pai.direito = no.direito;
			}

		} else {
			Node sucessor = this.sucessor(no);
			no.valor = sucessor.valor;
			this.remove(sucessor);
		}
	}

	private Node encontraExtremo(Node atual, boolean ehMin) {
		if ((ehMin && atual.esquerdo == null) || (!ehMin && atual.direito == null))
			return atual;

		return this.encontraExtremo((ehMin) ? atual.esquerdo : atual.direito, ehMin);
	}

	private void preOrdem(Node no, ArrayList<Integer> elementos) {
		if (no == null)
			return;

		elementos.add(no.valor);
		preOrdem(no.esquerdo, elementos);
		preOrdem(no.direito, elementos);
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
