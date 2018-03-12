//create thread by implementing Runnable Interface

package lesson2;
class MyClass implements Runnable{

	@Override
	public void run() {
		for(int i = 0 ; i<10 ;i++){
			System.out.println(Thread.currentThread().getId() +" Value " + i);
		}
		//super.run();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
public class multithreadingRunnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new MyClass());
		Thread t2 = new Thread(new MyClass());
		t1.start();
		t2.start();

	}

}
