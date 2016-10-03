public class BoundedBuffer
{
  public static void main(String [] args)
  {
     Buffer b = new Buffer(6);
     Producer p = new Producer(b);
     Consumer c = new Consumer(b);

     p.start();
     c.start();
  }
}

class Buffer {
   private char [] buffer;
   private int count = 0, in = 0, out = 0;

   Buffer(int size)
   {
      buffer = new char[size];
   }
 
   public synchronized void Produce(char c) {
     while(count == buffer.length) ;
     System.out.println("Producing " + c + " ...");
     buffer[in] = c; 
     in = (in + 1) % buffer.length; 
     count++;
	  System.out.println("Production of " + c + " is done!");
   }
    
   public synchronized char Consume() {
     while (count == 0) ;
     char c = buffer[out]; 
     out = (out + 1) % buffer.length;
     count--;
     System.out.println("Consuming " + c + " ...");
     return c;
   }
}
     

class Producer extends Thread {
   private Buffer buff;
     
   Producer(Buffer b) { buff = b; }
	
   public void run() {
     for(int i = 0; i < 10; i++) {
        buff.Produce((char)('A'+ i%26 )); }
   }
}    

class Consumer extends Thread {
   private Buffer buffer;
   
   Consumer(Buffer b) { buffer = b; }
   public void run() {
     for(int i = 0; i < 10; i++) {
        buffer.Consume(); }
   }
}