package P1;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Productor extends Thread {

	int id;
	ArrayList<Integer> used;

	public Productor(int i) {
		this.id = i;
		used = new ArrayList<Integer>();
		start();
	}

	void acquireResource() throws InterruptedException {

		Main.available.acquire();
		int resToAcquire = randomNumber(Main.numResources / 4, 1);

		if (resToAcquire <= Main.freeResources) {

			Main.freeResources = Main.freeResources - resToAcquire;
			int consumed = 0;

			for (int i = 0; i < resToAcquire; i++) {
				if (Main.sharedResources.get(i).acquireSemaphoreResource()) {
					consumed = consumed + 1;
					used.add(Main.sharedResources.get(i).id);
				}
			}
			System.out.println("Se han consumido " + resToAcquire + " productos");
		} else {
			System.out.println(
					"No hay tantos productos disponibles");
		}

		Main.available.release();
		System.out.println("Actualmente hay " + Main.freeResources + " productos en el almacen");
	}

	void releaseResource() {

		int resToRelease = randomNumber(used.size() - 1, +1);
		System.out.println("Se envian " + resToRelease + " productos");

		for (int i = resToRelease; i > 0; i--) {

			int indexGet = used.get(0) - 1;
			boolean confirmation = Main.sharedResources.get(indexGet).releaseSemaphoreResource();

			if (confirmation) {
				used.remove(0);
				Main.freeResources = Main.freeResources + 1;
				resToRelease = resToRelease - 1;
			}
		}

		System.out.println("Recursos: " + Main.freeResources);
	}

	@Override
	public void run() {

		while (true) {

			try {
				acquireResource();
			} catch (InterruptedException e) {
				System.out.println("Error en la funcion run");
				e.printStackTrace();
			}

			int t = randomNumber(100, 10);
			try {
				sleep(t);
			} catch (Exception e) {
				System.out.println("Error en la funcion run");
				e.printStackTrace();
			}

			if (used.size() > 0) {
				releaseResource();
			}
		}
	}

	int randomNumber(int n1, int n2) {
		Random rdm = new Random();
		int timing = rdm.nextInt(n1) + n2;
		return timing;
	}

}
