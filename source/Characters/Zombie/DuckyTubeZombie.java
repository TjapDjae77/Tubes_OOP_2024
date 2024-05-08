package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class DuckyTubeZombie extends Zombie {
    private boolean hasDuckyTube;

    public DuckyTubeZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Ducky Tube Zombie", 100, damage, 1, 100, position, true, speed);
    }

    public boolean getHasDuckyTube() {
        return hasDuckyTube;
    }

    public void setHasDuckyTube(boolean hasDuckyTube) {
        this.hasDuckyTube = hasDuckyTube;
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}
