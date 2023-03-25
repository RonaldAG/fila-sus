package hospital;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HospitalQueueManagement {
	private String[] queue;
	private int last;
	private int next;
	private int quantidadePacientes;
	private Random random;

	public HospitalQueueManagement(){
		random = new Random();
		queue = filaInicial();
	}
	
	// required methods
	public String getPassword(boolean preferencial) {
		int number = random.nextInt(100, 1000);
		
		boolean test = true;
		while(test) {
			
			test = false;
			for(int i = 0; i < quantidadePacientes; i++) {
				if(number == Integer.parseInt(queue[i].substring(0, 3))) {
					test = true;
					number = random.nextInt(100, 1000);
					break;
				}
			}
		}
		
		String password;
		
		
		//Verifica se é preferencial
		if(preferencial) {
			password =  number + "P";
		}
		else {
			password =  number + "N";		
		}
		
		//Pega o proximo
		
		
		
		enfileirar(password);
		
		return password;
	}
	
	
	public void drawboard() {
		String lastNames = "-", nextName = " - ";
		
		if(next != -1) {
			nextName = queue[next];
			int end = Math.max(0, next - 3);
			
			for(int i = next - 1; i >= end; i--) {
				lastNames += queue[i] + "-";
			}
		}
		
		System.out.printf("|----------------------------|\n");
		System.out.printf("| Próximo: %s             |\n", nextName);
		System.out.printf("| Próximas senhas:           |\n");
		System.out.printf("| %s |\n", lastNames);
		System.out.printf("|----------------------------|\n");
		
	}


	public String next() {
		if(next == queue.length) {
			return "";
		}
		
		String element = queue[next];
		
		if(element == null) {
			return queue[next - 1];
		}
		
		next++;
		return element;
	}
	
	//Queue methods
	
	
	private String[] filaInicial() {
		return new String[5];
	}
	
	private void dobraCapacidade() {
		if(quantidadePacientes == queue.length) {
			String[] newArray = new String[queue.length * 2];
			
			for(int i = 0; i < queue.length; i++) {
				newArray[i] = queue[i];
			}
			
			queue = newArray;
		}
	}
	
	private void enfileirar(String element) {
		//Verifica se precisa aumentar a capacidade
		dobraCapacidade();
		boolean hasN = false;
		
		if(quantidadePacientes == 0) {
			queue[0] = element;
		} else if(element.endsWith("P")) {
			for(int i = next; i < quantidadePacientes; i++) {
				if(queue[i].endsWith("N")) {
					reorganizaFila(i);
					queue[i] = element;
					hasN = true;
					break;
				}
			}
			
			//Verifica se não tem ninguém Normal na fila
			if(!hasN) {
				queue[quantidadePacientes] = element;
			}
		} else if(element.endsWith("N")) {
			queue[quantidadePacientes] = element;		
		}
		
		
		quantidadePacientes++;
	}
	
	//Metodo para reorganizar a fila
	private void reorganizaFila(int posicaoInicial) {
		for(int i = quantidadePacientes - 1; i >= posicaoInicial; i--) {
			queue[i+1] = queue[i]; 
		}
	}
	
	public int length() {
		return queue.length;
	}
}
