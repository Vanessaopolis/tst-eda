package elementosMaioresBST;

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

		System.out.println(bst.preOrdem());
		System.out.println(bst.contaElementosMaiores(n));

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

	public int contaElementosMaiores(int n) {
		if (this.estaVazia())
			throw new RuntimeException();

		return this.contaElementosMaiores(n, this.raiz);
	}

	private int contaElementosMaiores(int n, Node no) {
		if (no == null)
			return 0;

		if (no.valor > n)
			return 1 + tamanho(no.direito) + contaElementosMaiores(n, no.esquerdo);
		else
			return contaElementosMaiores(n, no.direito);
	}

	private int tamanho(Node no) {
		if (no == null)
			return 0;

		return 1 + tamanho(no.esquerdo) + tamanho(no.direito);
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
