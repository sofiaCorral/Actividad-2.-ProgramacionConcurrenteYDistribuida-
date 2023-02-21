package P2;
import java.util.Random;

public class Coche extends Thread {

	private enum STATE {
		ESPERANDO, CRUZANDO, TERMINADO
	}

	private STATE state;
	private String direccion;
	private int id;
	private static int numCoches;
	private Puente monitorPuente;

	public Coche(String dir, Puente m) {
		id = numCoches++;
		state = STATE.ESPERANDO;
		direccion = dir;
		monitorPuente = m;
		start();
	}

	private void cocheEsperando() {

		System.out.println("Coche con matricula " + id + " esperando para cruzar (" + direccion + ").");

		monitorPuente.esperar(id, direccion);
		state = Coche.STATE.CRUZANDO;

		System.out.println("Coche con matricula " + id + " ha empezado a cruzar (" + direccion + ").");
	}

	private void cocheCruzando() {

		Random rdm = new Random();
		int crossingTime = rdm.nextInt(250 - 50 + 1) + 50;

		try {
			sleep(crossingTime);
		} catch (InterruptedException e) {
			System.out.println("Error en la funcion cocheCruzando");
			e.printStackTrace();
		}

		monitorPuente.finalizarPuente(id, direccion);
		state = Coche.STATE.TERMINADO;
		System.out.println("Coche con matricula " + id + " ha terminado de cruzar (" + direccion + ").");
		

	}

	@Override
	public void run() {

		while (state != Coche.STATE.TERMINADO) {

			switch (state) {

			case ESPERANDO:
				cocheEsperando();
				break;
			case CRUZANDO:
				cocheCruzando();
				break;
			default:
				break;

			}
		}
	}
}
