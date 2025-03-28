package ehHeap;

import java.util.Scanner;

public class Solucao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] elementosStr = sc.nextLine().split(" ");
		int[] elementosInt = new int[elementosStr.length];
		for (int i = 0; i < elementosStr.length; i++)
			elementosInt[i] = Integer.parseInt(elementosStr[i]);

		SupostoHeap heap = new SupostoHeap(elementosInt);
		System.out.println(heap.ehHeap());

		sc.close();
	}

}

class SupostoHeap {

	private int[] heap;
	private int ultimo;

	public SupostoHeap(int[] heap) {
		this.heap = heap;
		this.ultimo = this.heap.length - 1;
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

	public boolean ehHeap() {
		if (this.estaVazio())
			return true;

		for (int i = 0; i < this.heap.length; i++) {
			if (ehIndiceValido(esquerdo(i)) && this.heap[esquerdo(i)] > this.heap[i])
				return false;

			if (ehIndiceValido(direito(i)) && this.heap[direito(i)] > this.heap[i])
				return false;
		}

		return true;
	}

	private boolean ehIndiceValido(int index) {
		return index >= 0 && index <= ultimo;
	}

}