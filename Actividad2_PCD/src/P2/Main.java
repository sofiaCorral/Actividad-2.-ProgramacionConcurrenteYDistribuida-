package P2;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		Puente monitorPuente = new Puente();
		String dir;

		while (true) {
			
			Random rdm1 = new Random();
			if (rdm1.nextDouble() < 0.5) {
				dir = "Norte";
			} else {
				dir = "Sur";
			}
			Random rdm2 = new Random();
			if (rdm2.nextDouble() < 0.9) {
				new Coche(dir, monitorPuente);
			} else {
				System.out.println("No cruza");
			}
		}
	}
}

