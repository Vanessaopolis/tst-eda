package buildHeap;

import java.util.Scanner;

public class Solucao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] elementosStr = sc.nextLine().split(" ");
		int[] elementosInt = new int[elementosStr.length];
		for (int i = 0; i < elementosStr.length; i++)
			elementosInt[i] = Integer.parseInt(elementosStr[i]);

		Heap heap = new Heap(elementosInt);
		System.out.println(heap.toString());

		sc.close();
	}

}

class Heap {

	private int[] heap;
	private int ultimo;

	public Heap(int[] heap) {
		this.heap = heap;
		this.ultimo = this.heap.length - 1;
		this.buildHeap();
	}

	public boolean estaVazio() {
		return this.ultimo == -1;
	}

	public int esquerdo(int indice) {
		if (!this.ehIndiceValido(indice))
			throw new IllegalArgumentException();

		return (2 * indice) + 1;
	}

	public int direito(int indice) {
		if (!this.ehIndiceValido(indice))
			throw new IllegalArgumentException();

		return (2 * indice) + 2;
	}

	public int pai(int indice) {
		if (!this.ehIndiceValido(indice))
			throw new IllegalArgumentException();

		if (indice == 0)
			return -1;

		return (indice - 1) / 2;
	}

	private void buildHeap() {
		for (int i = this.pai(this.ultimo); i >= 0; i--)
			this.heapify(i);
	}

	private void heapify(int indice) {
		if (!ehIndiceValido(indice) || ehFolha(indice))
			return;

		int indiceMax = indiceMax(indice, esquerdo(indice), direito(indice));

		if (indiceMax != indice) {
			troca(indice, indiceMax);
			heapify(indiceMax);
		}
	}

	private int indiceMax(int indice, int esquerdo, int direito) {
		int indiceMax = indice;

		if (ehIndiceValido(esquerdo) && this.heap[esquerdo] > this.heap[indiceMax])
			indiceMax = esquerdo;

		if (ehIndiceValido(direito) && this.heap[direito] > this.heap[indiceMax])
			indiceMax = direito;

		return indiceMax;
	}

	private boolean ehIndiceValido(int index) {
		return index >= 0 && index <= ultimo;
	}

	private boolean ehFolha(int index) {
		return index > pai(ultimo) && index <= ultimo;
	}

	private void troca(int i, int j) {
		int aux = this.heap[i];
		this.heap[i] = this.heap[j];
		this.heap[j] = aux;
	}

	@Override
	public String toString() {
		StringBuilder heapStr = new StringBuilder("[");
		for (int i = 0; i <= this.ultimo; i++) {
			heapStr.append(this.heap[i]);
			if (i < this.ultimo)
				heapStr.append(", ");
		}
		heapStr.append("]");

		return heapStr.toString();
	}

}