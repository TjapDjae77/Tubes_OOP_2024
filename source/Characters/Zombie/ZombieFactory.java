package source.Characters.Zombie;

import java.util.concurrent.ThreadLocalRandom;

public class ZombieFactory {


    public static Zombie createZombie(boolean aquatic){
        if(aquatic) {
            int zombieType = ThreadLocalRandom.current().nextInt(2);
            switch (zombieType) {
                case 0:
                    return new DolphinRiderZombie();

                case 1:
                    return new DuckyTubeZombie();

                default:
                    return null;
            }
        }
        else {
            int zombieType = ThreadLocalRandom.current().nextInt(8);
            switch (zombieType) {
                case 0:
                    return new PoleVaultingZombie();
                case 1:
                    return new ConeheadZombie();
                case 2:
                    return new BucketheadZombie();
                case 3:
                    return new ScreenDoorZombie();
                case 4:
                    return new NewspaperZombie();
                case 5:
                    return new FootballZombie();
                case 6:
                    return new NormalZombie();
                case 7:
                    return new JackInTheBoxZombie();

                default:
                    return new DuckyTubeZombie();
            }
        }
    }

}
