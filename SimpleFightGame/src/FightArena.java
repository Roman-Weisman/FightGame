import fighter.ACharacter;

public class FightArena {
	ACharacter character1;
	ACharacter character2;
	
	
	public FightArena(ACharacter a, ACharacter b)  {
		character1 = a;
		character2 = b;
	}
	
	public void fight() {
		character1.start();
		character2.start();
	}

}
