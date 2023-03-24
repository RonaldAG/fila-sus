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
	private Scanner sc =  new Scanner(System.in);
	
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
		int var = 4;
		do {
			System.out.println("(1) Retirar nova senha\n"
					+ "(2) Próximo.\r\n"
					+ "(3) Exibir Quadro\r\n"
					+ "(4) Sair");
			var = sc.nextInt();
			
			if(var == 1) {
				System.out.print("Preferencial? y/n ");
				String preferencial = sc.next();
				
				if(preferencial.equals("y")) {
					getPassword(true);	
				}
				else {
					getPassword(false);
				}
			}
			
			System.out.println(Arrays.toString(queue));
			
		} while(var != 4);
		
		
	}


	public String next() {
		if(next == queue.length) {
			return "";
		}
		
		String element = queue[next];
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
					System.out.println(queue[i]);
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
			System.out.println(queue[i]);
			queue[i+1] = queue[i]; 
		}
	}
	
	public int length() {
		return queue.length;
	}
}
