package Timer;

public class Per implements Runnable{
	Timer t;
	
	public Per(Timer t) {
		this.t = t;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (t) {
				try {
					t.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+" tick");
		}
	}
}
