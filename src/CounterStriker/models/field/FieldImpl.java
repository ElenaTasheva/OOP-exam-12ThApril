package CounterStriker.models.field;

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FieldImpl implements Field {
    private List<Player> terrorist = new ArrayList<>();
    private List<Player> counterTerrorists = new ArrayList<>();
    private int terroristHealth;
    private int counterTerroristHealth;


    @Override
    public String start(Collection<Player> players) {
        separateTeams(players);
        while (!timeToBreak()) {
            terroristAttack(terrorist, counterTerrorists);
            terroristAttack(counterTerrorists,terrorist);
        }
        return getMessage();

    }

    private String getMessage() {
        if(terroristHealth > 0){
            return "Terrorist wins!";
        }
        return "Counter Terrorist wins!";
    }

    private boolean timeToBreak() {
        terroristHealth = terrorist.stream().mapToInt(Player::getHealth).sum();
        counterTerroristHealth = counterTerrorists.stream().mapToInt(Player::getHealth).sum();
        return (terroristHealth <= 0 || counterTerroristHealth <= 0);

    }

    private void separateTeams(Collection<Player> players) {
        for (Player player : players) {
            if(player.getClass().getSimpleName().equals("Terrorist")){
                terrorist.add(player);
            }
            else counterTerrorists.add(player);
        }
    }


    // the last test is checking the value of the Armors;
    private void terroristAttack(List<Player> attackers, List<Player> defenders) {
        for (Player player : attackers) {
            if(player.isAlive()){
                for (Player counterTerrorist : defenders) {
                    if(!timeToBreak()) {
                     int bullets = player.getGun().fire();
                        if (counterTerrorist.isAlive()) {
                            counterTerrorist.takeDamage(bullets);
                        }
                    }
                    else{
                        break;
                    }
                }
            }
            }
    }
    }
