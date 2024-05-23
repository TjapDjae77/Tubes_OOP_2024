package source.Characters.Zombie;

import java.util.concurrent.ThreadLocalRandom;

public class ZombieFactory {


    public static Zombie createZombie(boolean aquatic){
        if(aquatic) {
            int zombieType = ThreadLocalRandom.current().nextInt(2);
            switch (zombieType) {
                case 0:
                    return new DuckyTubeZombie();

                case 1:
                    return new DolphinRiderZombie();

                default:
                    return null;
            }
        }
        else {
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

}
