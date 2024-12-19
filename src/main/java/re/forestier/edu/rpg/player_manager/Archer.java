package re.forestier.edu.rpg.player_manager;

import re.forestier.edu.rpg.itm_manager.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Archer extends Player {
    public Archer(String playerName, String avatarName, int money, ArrayList<Item> inventory) {
        super(playerName, avatarName, "ARCHER", money, inventory);
    }

    @Override
    protected HashMap<String, Integer> initializeAbilities() {
        HashMap<String, Integer> abilities = new HashMap<>();
        abilities.put("ATK", 3);
        abilities.put("DEF", 1);
        abilities.put("AGI", 2); // Agility for Archer
        return abilities;
    }

    @Override
    protected HashMap<String, Integer> initializeAbilitiesForLevel(int level) {
        HashMap<String, Integer> abilities = new HashMap<>();
        switch (level) {
            case 1:
                abilities.put("ATK", 1);
                abilities.put("AGI", 1);
                break;
            case 2:
                abilities.put("ATK", 2);
                abilities.put("AGI", 1);
                break;
            case 3:
                abilities.put("ATK", 3);
                abilities.put("AGI", 2);
                break;
            case 4:
                abilities.put("ATK", 3);
                abilities.put("AGI", 3);
                break;
            case 5:
                abilities.put("ATK", 4);
                abilities.put("AGI", 4);
                break;
            default:
                break;
        }
        return abilities;
    }

     @Override
    protected void recoverHealth() {
        if (currenthealthpoints < healthpoints / 2) {
            currenthealthpoints += 1; // Basic recovery

            // Additional recovery if the Archer has a Magic Bow
            if (inventory.contains("Magic Bow")) {
                currenthealthpoints += currenthealthpoints / 8 - 1;
            }
        }
    }

    // Archer-specific methods
}