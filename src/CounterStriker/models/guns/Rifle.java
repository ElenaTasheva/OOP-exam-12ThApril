package CounterStriker.models.guns;

public class Rifle extends GunImpl {
    private static final int FIRE_BULLET = 10;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }


    @Override
    protected int decreaseBullets(int bullets) {
        return super.decreaseBullets(FIRE_BULLET);
    }
}
