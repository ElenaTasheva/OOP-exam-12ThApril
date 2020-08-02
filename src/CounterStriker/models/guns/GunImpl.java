package CounterStriker.models.guns;

import CounterStriker.common.ExceptionMessages;

public abstract class GunImpl implements Gun {


    private String name;
    private int bulletsCount;

    public GunImpl(String name, int bulletsCount) {
       setName(name);
       setBulletsCount(bulletsCount);
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_NAME);
        }
        this.name = name;
    }

    private void setBulletsCount(int bulletsCount) {
        if(bulletsCount < 0 ){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    public int fire() {
        return decreaseBullets(0);
    }

    protected int decreaseBullets(int bullets){
     if(this.bulletsCount >= bullets){
     this.bulletsCount -= bullets;
     return bullets;
     }
     else return 0;
    }

}
