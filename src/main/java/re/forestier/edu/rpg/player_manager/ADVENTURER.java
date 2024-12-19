package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Adventurer extends Player {
    public Adventurer(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, "ADVENTURER", money, inventory);
    }

    @Override
    protected HashMap<String, Integer> initializeAbilities() {
        HashMap<String, Integer> abilities = new HashMap<>();
        abilities.put("ATK", 3);
        abilities.put("DEF", 1);
        abilities.put("INT", 1); // Intelligence for Adventurer
        return abilities;
    }

    @Override
    protected HashMap<String, Integer> initializeAbilitiesForLevel(int level) {
        HashMap<String, Integer> abilities = new HashMap<>();
        switch (level) {
            case 1:
                abilities.put("ATK", 1);
                abilities.put("DEF", 1);
                abilities.put("INT", 1);
                break;
            case 2:
                abilities.put("ATK", 2);
                abilities.put("DEF", 1);
                abilities.put("INT", 2);
                break;
            case 3:
                abilities.put("ATK", 3);
                abilities.put("DEF", 2);
                abilities.put("INT", 2);
                break;
            case 4:
                abilities.put("ATK", 4);
                abilities.put("DEF", 2);
                abilities.put("INT", 3);
                break;
            case 5:
                abilities.put("ATK", 5);
                abilities.put("DEF", 3);
                abilities.put("INT", 4);
                break;
            default:
                break;
        }
        return abilities;
    }
    @Override
    protected void recoverHealth() {
        if (currentHealthPoints < healthPoints / 2) {
            currentHealthPoints += 2; // Adventurer's recovery
            if (retrieveLevel() < 3) {
                currentHealthPoints -= 1; // Penalty for low level
            }
        }
    }

    // Adventurer-specific methods
}