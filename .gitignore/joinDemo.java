//Synchronizaton
//join()

package lesson4;
public class joinDemo {
	private static int count = 0 ;
	//synchronized- thread t2 has to wait until thread t1 completes increament 
	public static synchronized void inccount(){
		count++ ;
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){

			public void run() {
				for(int i=0 ; i<10000 ; i++){
					//count++;
					inccount();
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			public void run() {
			for(int i = 0 ; i<10000 ; i++ )	
				//count++ ;
				inccount();
			}
			
		}) ;
		
		t1.start();
		t2.start();
		
		try {
			t1.join(); //in java join method can be used to pause the current thread 
			t2.join(); // execution until unless the specified thread is dead
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("value : " + count);

	}

}
