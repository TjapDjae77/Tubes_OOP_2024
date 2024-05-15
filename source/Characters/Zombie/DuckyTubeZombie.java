package source.Characters.Zombie;

public class DuckyTubeZombie extends Zombie {
    private boolean hasDuckyTube;

    public DuckyTubeZombie(String name, int health, int damage, int attack_speed, int attack_damage, boolean is_aquatic, int speed) {
        super("Ducky Tube Zombie", 100, 100, 1, true, speed);
    }

    public boolean getHasDuckyTube() {
        return hasDuckyTube;
    }

    public void setHasDuckyTube(boolean hasDuckyTube) {
        this.hasDuckyTube = hasDuckyTube;
    }
}
