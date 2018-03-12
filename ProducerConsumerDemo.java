//producer won’t try to add data into the sharedList if it’s full 
// and that the consumer won’t try to remove data from an empty sharedList
// by using wait() and notify() method.


package lesson10;
import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo {

	public static void main(String[] args) {
		List<Integer> sharedList = new ArrayList<Integer>();
		
		 Thread t1 = new Thread(new Producer(sharedList));
		 Thread t2 = new Thread(new Consumer(sharedList));
		 
		 t1.start();
		 t2.start();

	}

}

class Producer implements Runnable{ 
	 List<Integer> sharedList = null ;
	 final int MAX_SIZE = 5 ;
	 private int i =0 ;
	
	public Producer(List<Integer> sharedList) {
		super();
		this.sharedList = sharedList;
	}
	public void run() {
		while(true){
			try {
				produce(i++);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void produce(int i) throws InterruptedException{
		synchronized (sharedList) {
			while(sharedList.size() == MAX_SIZE){
				System.out.println("Shared list is full...waiting for consumer to consume");
				sharedList.wait();
			}
		}
		synchronized (sharedList) {
			sharedList.add(i);
			System.out.println("produce produced elements" + i);
			Thread.sleep(100);
			sharedList.notify(); //if there is not any thread to consume ...consumer will notify producer to produce
		}
	}
}

class Consumer implements Runnable{
 List<Integer> sharedList = null ;
	
	public Consumer(List<Integer> sharedList) {
	super();
	this.sharedList = sharedList;
}

	public void run() { 
		while(true){ 
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public  void consume() throws InterruptedException {
		synchronized (sharedList) {
			while(sharedList.isEmpty()){
				System.out.println("shared list is empty...waiting for producer to produce the objects");
				sharedList.wait();
			}
			
		}
		 synchronized (sharedList) {
			 Thread.sleep(1000);
			 System.out.println("consume the elements" + sharedList.remove(0));
			sharedList.notify();
		}
		
	}
	
}
