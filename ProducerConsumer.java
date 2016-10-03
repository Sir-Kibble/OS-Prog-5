class pc_monitor { 
	int n; 
	
	synchronized void produce(int n) { 
		this.n = n; 
		System.out.println("Produced: " + n);
	}
	
	synchronized int consume() { 
		System.out.println("Consumed: " + n); 
		return n; 
	}	  
}//end_of_monitor

class Producer implements Runnable { 
	pc_monitor m; 
	
	Producer(pc_monitor m) { 
		this.m = m; 
		new Thread(this, "Producer").start(); 
	} 
	
	public void run() { 
		int i = 0; 
		while(i< 10){ 
			m.produce(i++); 
		} 
	}	 
}

class Consumer implements Runnable { 
	pc_monitor m; 
	
	Consumer(pc_monitor m) { 
		this.m = m; 
		new Thread(this, "Consumer").start(); 
	} 
	
	public void run() { 
		int i = 10;
		while(i>0){
			m.consume(); 
			i--;
		} 
	} 
}

public class ProducerConsumer { 
	public static void main(String args[]) { 
		pc_monitor mon = new pc_monitor(); 
		new Producer(mon); 
		new Consumer(mon);  
	} 
}

