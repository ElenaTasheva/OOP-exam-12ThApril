package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        guns = new GunRepository();
        players = new PlayerRepository();
        field = new FieldImpl();
    }



    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun = checkType(type,name,bulletsCount);
        guns.add(gun);
        return String.format("Successfully added gun %s.", name);
    }

    private Gun checkType(String type, String name, int bulletsCount) {
        Gun gun;
        if(type.equals("Pistol")){
            gun = new Pistol(name,bulletsCount);
        }
        else if(type.equals("Rifle")){
            gun = new Rifle(name, bulletsCount);
        }
        else{
            throw new IllegalArgumentException("Invalid gun type!");
        }
        return gun;
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Player player = checkTypeOfPlayer(type, username,health,armor,gunName);
        players.add(player);
        return String.format("Successfully added player %s.", username);
    }

    private Player checkTypeOfPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = guns.findByName(gunName);
        if(gun == null){
            throw new NullPointerException (ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }
        switch (type){
            case "Terrorist":
                return new Terrorist(username,health,armor,gun);
            case "CounterTerrorist":
                return new CounterTerrorist(username,health,armor,gun);
            default:throw  new IllegalArgumentException("Invalid player type!");
        }
    }

    @Override
    public String startGame() {
        return field.start(players.getModels());
    }

    @Override
    public String report() {
         return sortCollection();
    }


    // even with incorrect sorting the tests except the last one passed;

    private String sortCollection() {
        Comparator<Player> byClassName = Comparator.comparing((p -> p.getClass().getSimpleName()));
        Comparator<Player> byHealth = Comparator.comparingInt(Player::getHealth);
        Comparator<Player> byUserName = Comparator.comparing(Player::getUsername);
        return players.getModels().stream().sorted(byHealth).sorted(byUserName).sorted(byClassName)
                .map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator()));


    }

    }

