package P1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

	static int numResources;
	static int freeResources;
	static int numProcess;

	static ArrayList<Consumidor> sharedResources = new ArrayList<>();
	static Semaphore available = new Semaphore(1);

	public static void main(String[] args) throws IOException {

		numResources = 150;
		freeResources = numResources;
		numProcess = 40;

		for (int i = 1; i <= numResources; i++) {
			sharedResources.add(new Consumidor(i));
		}
		for (int j = 1; j <= numProcess; j++) {
			new Productor(j);
		}
	}
}
