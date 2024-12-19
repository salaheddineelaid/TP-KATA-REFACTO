package re.forestier.edu.rpg.player_manager;

import re.forestier.edu.rpg.itm_manager.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Goblin extends Player {
    public Goblin(String playerName, String avatarName, int money, ArrayList<Item> inventory) {
        super(playerName, avatarName, "GOBLIN", money, inventory);
    }

    @Override
    protected HashMap<String, Integer> initializeAbilities() {
        HashMap<String, Integer> abilities = new HashMap<>();
        abilities.put("ATK", 2);
        abilities.put("DEF", 0);
        abilities.put("INT", 2);
        abilities.put("ALC", 1);
        return abilities;
    }

    @Override
    protected HashMap<String, Integer> initializeAbilitiesForLevel(int level) {
        HashMap<String, Integer> abilities = new HashMap<>();
        switch (level) {
            case 1:
                abilities.put("ATK", 2);
                abilities.put("INT", 2);
                abilities.put("ALC", 1);
                break;
            case 2:
                abilities.put("ATK", 3);
                abilities.put("ALC", 4);
                break;
            case 3:
                abilities.put("VIS", 1);
                break;
            case 4:
                abilities.put("DEF", 1);
                break;
            case 5:
                abilities.put("DEF", 2);
                abilities.put("ATK", 4);
                break;
            default:
                break;
        }
        return abilities;
    }

    @Override
    protected void recoverHealth() {
        if (currenthealthpoints < healthpoints / 2) {
            currenthealthpoints += 1;
        }
    }


}