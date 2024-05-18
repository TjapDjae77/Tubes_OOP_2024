package source.Characters.Zombie;
import java.util.ArrayList;
import java.util.List;

public class ZombieList<T extends Zombie> {
    private List<T> zombies;

    public ZombieList() {
        this.zombies = new ArrayList<>();
    }

    public void addZombie(T zombie) {
        zombies.add(zombie);
    }

    public void removeZombie(T zombie) {
        zombies.remove(zombie);
    }

    public List<T> getZombies() {
        return zombies;
    }
    public Zombie getZombie(int index) {
        return zombies.get(index);
    }
}

