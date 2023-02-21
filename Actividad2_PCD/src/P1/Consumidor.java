package P1;
import java.util.concurrent.Semaphore;

public class Consumidor {

	Semaphore state;
	int id;

	public Consumidor(int i) {
		this.state = new Semaphore(1);
		this.id = i;
	}

	boolean releaseSemaphoreResource() {
		try {
			state.release();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	boolean acquireSemaphoreResource() {
		return state.tryAcquire();
	}
}
