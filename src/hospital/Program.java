package hospital;

import java.util.Arrays;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		HospitalQueueManagement hospital = new HospitalQueueManagement();
		int var = 4;
		
		Scanner sc = new Scanner(System.in);
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
					hospital.getPassword(true);	
				}
				else {
					hospital.getPassword(false);
				}
			} else if(var == 2) {
				String next = hospital.next();
				System.out.println(next);
			} else if(var == 3) {
				hospital.drawboard();
			}
			
		} while(var != 4);
	}
}
