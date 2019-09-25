package fighter;

public class Wizard extends ACharacter {
	
	public Wizard(String name) {
		this.name = name;
	}

	@Override
	protected void punch() {
		super.punch();
		this.damage = 5;		
	}

	@Override
	protected void kick() {
		super.kick();
		this.damage = 10;
		
	}

	@Override
	protected void uppercut() {
		super.uppercut();
		this.damage = 15;	
	}
}
