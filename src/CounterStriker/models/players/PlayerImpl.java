package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player{
    
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;


    public PlayerImpl(String username, int health, int armor, Gun gun) {
        setUsername(username);
        setHealth(health);
        setArmor(armor);
        setAlive();
        setGun(gun);
    }

    private void setUsername(String username) {
        if(username == null || username.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if(health < 0 ){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setArmor(int armor) {
       if(armor < 0){
           throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
       }
       this.armor = armor;
    }

    private void setAlive() {
       if(this.health > 0){
           isAlive = true;
       }
       else {
           isAlive = false;
       }
    }

    private void setGun(Gun gun) {
        if(gun == null){
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void takeDamage(int points) {
        int difference = this.armor - points;
        if(difference > 0){
            this.armor = this.armor - points;
        }
        else if(difference == 0){
            this.armor = 0;
        }
        else{
            this.armor = 0;
            if(this.health - Math.abs(difference) < 0){
                this.health = 0;
            }
            else {
                this.health -= Math.abs(difference);
            }
            setAlive();
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(System.lineSeparator())
                .append("--Health: ").append(getHealth())
                .append(System.lineSeparator())
                .append("--Armor: ").append(getArmor())
                .append(System.lineSeparator())
                .append("--Gun: ").append(getGun().getName());
        return sb.toString().trim();
    }
}
