/** @author ${Tristan}
 * 
 */
package KommunikationPC;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueHandler  {
	
	int anz;
	int anzahlClients;
    ConcurrentLinkedQueue<Byte> queue1;
	ConcurrentLinkedQueue<Byte> queue2;
	ConcurrentLinkedQueue<Byte> queue3;
	ConcurrentLinkedQueue<Byte> queue4;
	QueueHandler queueHandler;
	
	public void setQueueHandler(QueueHandler q){
		this.queueHandler = q;
	}
	
	public QueueHandler getQueueHandler(){
		return queueHandler;
		
	}
	
	
	
	
	public QueueHandler(ConcurrentLinkedQueue<Byte> q1) {                              //Konstruktor des neuen
		
			this.queue1 = q1;
		    this.anzahlClients=1;
		}
	
	public QueueHandler(ConcurrentLinkedQueue<Byte> q1, ConcurrentLinkedQueue<Byte>q2){
		
			this.queue1 = q1;
			this.queue2 = q2;
			this.anzahlClients = 2;
		}
	
	public QueueHandler(ConcurrentLinkedQueue<Byte> q1, ConcurrentLinkedQueue<Byte>q2, ConcurrentLinkedQueue<Byte>q3){
		
			this.queue1 = q1;
			this.queue2 = q2;
			this.queue3 = q3;
			this.anzahlClients = 3;
		}

	public QueueHandler(ConcurrentLinkedQueue<Byte> q1, ConcurrentLinkedQueue<Byte>q2, ConcurrentLinkedQueue<Byte>q3, ConcurrentLinkedQueue<Byte>q4){
	
			this.queue1 = q1;
			this.queue2 = q2;
			this.queue3 = q3;
			this.queue4 = q4;
			this.anzahlClients = 4;
		}
	
	
	public void addToQueue(Byte b){                               //Fügt der Queue ein neues Byte hinzu, dass an den Roboter geschickt werden soll
		
		if(anzahlClients == 1){
			queue1.add(b);
		}
		if(anzahlClients==2){
			queue1.add(b);
			queue2.add(b);
		}
		if(anzahlClients==3){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
		}
		if(anzahlClients==3){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
			queue4.add(b);
		}
	}
	
	public byte getNextQueued(int clientNr){                       //Entnimmt der Queue ein Element nach FIFO-Prinzip
		                                                           
		Byte b = null;
		if(clientNr==1){
			b = queue1.poll();
		}
		else if(clientNr==2){
			b = queue2.poll();
		}
		else if(clientNr==3){
			b = queue3.poll();
		}
		else if(clientNr==4){
		    b = queue4.poll();
		}
		
//		System.out.println("Something has been pulled: "+b);        //just for testing >_>
		if(b!=null){
		return b;
		}
		else{
			return b = 0;
			}
	}
	
	public boolean isQueueEmpty(){
		
		if(queue1.isEmpty()){
			return true;
		}
		return false;
	}
	
	
	public Byte firstByte(ConcurrentLinkedQueue<Byte> q){             //just for testing purposes - peeks first byte of queue
		
		return q.peek();
		
	}
	
	public void add1(){                                               //just for testing purposes >_<
		Byte b = 1;
		if(anzahlClients == 1){
			queue1.add(b);
		}
		if(anzahlClients==2){
			queue1.add(b);
			queue2.add(b);
		}
		if(anzahlClients==3){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
		}
		if(anzahlClients==3){
			queue1.add(b);
			queue2.add(b);
			queue3.add(b);
			queue4.add(b);
		}
	}
}
