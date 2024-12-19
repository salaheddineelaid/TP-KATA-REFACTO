package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
public class Dwarf extends Player {
    public Dwarf(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, "DWARF", money, inventory);
    }

    @Override
    protected HashMap<String, Integer> initializeAbilities() {
        HashMap<String, Integer> abilities = new HashMap<>();
        abilities.put("ATK", 4);
        abilities.put("DEF", 2);
        abilities.put("ALC", 3); // Alchemy for Dwarf
        return abilities;
    }

    @Override
    protected HashMap<String, Integer> initializeAbilitiesForLevel(int level) {
        HashMap<String, Integer> abilities = new HashMap<>();
        switch (level) {
            case 1:
                abilities.put("ATK", 1);
                abilities.put("DEF", 1);
                abilities.put("ALC", 1);
                break;
            case 2:
                abilities.put("ATK", 2);
                abilities.put("DEF", 2);
                abilities.put("ALC", 2);
                break;
            case 3:
                abilities.put("ATK", 3);
                abilities.put("DEF", 2);
                abilities.put("ALC", 3);
                break;
            case 4:
                abilities.put("ATK", 4);
                abilities.put("DEF", 3);
                abilities.put("ALC", 4);
                break;
            case 5:
                abilities.put("ATK", 5);
                abilities.put("DEF", 4);
                abilities.put("ALC", 5);
                break;
            default:
                break;
        }
        return abilities;
    }
    @Override
    protected void recoverHealth() {
        if (currentHealthPoints < healthPoints / 2) {
            if (inventory.contains("Holy Elixir")) {
                currentHealthPoints += 1; // Recover with Holy Elixir
            }
            currentHealthPoints += 1; // Basic recovery for Dwarf
        }
    }

    // Dwarf-specific methods
}
