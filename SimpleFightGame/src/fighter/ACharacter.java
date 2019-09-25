package fighter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ACharacter extends Thread{
	
	short life = 100;
	String name;
	public static boolean GAME_OVER = false;
	int damage = 0;
	
	public static FighterStatistics fighterStatistics = new FighterStatistics();
	public static final Lock lock = new ReentrantLock();
	
	
	/*public boolean isItTimeForFuryMode() {
		double rand = Math.random();	
		return (rand >0.0 || rand <=0.8) ?  true : false;
	}*/

	protected synchronized void causeDamage() {
		if(lock.tryLock()) {
		synchronized(fighterStatistics) {
			try {
				this.attack();
				fighterStatistics.setDamage(this.damage);
				fighterStatistics.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}}
		else {
			try {
				this.wait(10);
				if
				(Math.random()>0.8) {
					dodge();
				} else {
					takeDamage();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	protected void dodge() {
		synchronized(fighterStatistics) {
			System.out.println(this.name + ": --- DODGED ---");
			fighterStatistics.notify();
		}
	}

	protected void takeDamage() {
		synchronized(fighterStatistics) {
			this.life -= fighterStatistics.getDamage();
			
			if(this.life<=0) {
				GAME_OVER = true;
			}
			
			System.out.println(this.name + ": is taking damage. Life -> " + this.life);
			
			
			fighterStatistics.notify();
			if(!GAME_OVER) {
				try {
					this.wait(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//System.out.println("notify " + this.name);
		}
	}
	
	private void attack() {
		double rand = Math.random();
		if(rand >= 0.0 && rand < 0.3) {
			this.punch();
		} else if(rand >= 0.3 && rand <=0.4) {
			this.uppercut();
		} else {
			this.kick();
		}
	}
	
	protected void punch() {
		System.out.println(this.name + ": punch");
	}
	protected void kick() {
		System.out.println(this.name + ": kick");
	}
	protected void uppercut() {
		System.out.println(this.name + ": BOOM UPPERCUT");
	}
	
	public void run() {
		while(!GAME_OVER) {
			if(Math.random()>0.5) {
				causeDamage();
			}
		}
		if(this.life > 0) {
			System.out.println("\n" + this.name + " WON");
		} else {
			System.out.println("\n" + this.name + " LOST");
		}
	}
}
