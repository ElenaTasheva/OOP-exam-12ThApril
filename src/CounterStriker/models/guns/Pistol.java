package CounterStriker.models.guns;

public class Pistol extends GunImpl {

    private static final int FIRE_BULLET = 1;



    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    protected int decreaseBullets(int bullets) {
        return super.decreaseBullets(FIRE_BULLET);
    }
}
