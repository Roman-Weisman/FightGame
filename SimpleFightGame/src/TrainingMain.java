import fighter.*;

public class TrainingMain {

	public static void main(String[] args) {
		
		FightArena fightArena = new FightArena(new Warrior("Conan the Barbarian"), new Wizard("Merlin the Wizard"));
		fightArena.fight();
	}

}