package source.Characters.Zombie;

import java.util.concurrent.ThreadLocalRandom;

public class ZombieFactory {


    public static Zombie createZombie(){
        int zombieType = ThreadLocalRandom.current().nextInt(3);
        switch (zombieType) {
            case 0:
                return new NormalZombie();

            case 1:
                return new ConeheadZombie();

            case 2:
                return new BucketheadZombie();

            default:
                return new DuckyTubeZombie();
        }
    }

}
