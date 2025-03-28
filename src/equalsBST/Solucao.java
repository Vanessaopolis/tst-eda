package equalsBST;

import java.util.ArrayList;
import java.util.Scanner;

public class Solucao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] elementosAStr = sc.nextLine().split(" ");
		int[] elementosAInt = new int[elementosAStr.length];
		for (int i = 0; i < elementosAStr.length; i++)
			elementosAInt[i] = Integer.parseInt(elementosAStr[i]);

		String[] elementosBStr = sc.nextLine().split(" ");
		int[] elementosBInt = new int[elementosBStr.length];
		for (int i = 0; i < elementosBStr.length; i++)
			elementosBInt[i] = Integer.parseInt(elementosBStr[i]);

		BST bstA = new BST();
		for (int e : elementosAInt)
			bstA.adiciona(e);

		BST bstB = new BST();
		for (int e : elementosBInt)
			bstB.adiciona(e);

		System.out.println(bstA.equals(bstB));

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

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (!(obj instanceof BST))
			return false;

		BST outra = (BST) obj;
		return this.preOrdem().equals(outra.preOrdem());
	}

	public String preOrdem() {
		ArrayList<Integer> elementos = new ArrayList<Integer>();
		preOrdem(this.raiz, elementos);
		return elementos.toString();
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
