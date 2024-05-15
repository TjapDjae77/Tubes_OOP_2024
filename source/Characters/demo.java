package source.Characters;
import source.Characters.Plants.Snowpea;
import source.Characters.Zombie.BucketheadZombie;

public class demo {
    public static void main(String[] args) {
        Snowpea Snowpea = new Snowpea();
        BucketheadZombie Zombie = new BucketheadZombie();

        Snowpea.continuousAttack(Zombie);
    }
}
// javac source/Characters/Characters.java source/Characters/Plants/Plants.java source/Characters/Plants/Peashooter.java source/Characters/Zombie/Zombie.java source/Characters/Zombie/NormalZombie.java