

public class Archer extends Player {
    public Archer(String playerName, String avatarName, int money, ArrayList<String> inventory) {
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
        if (currentHealthPoints < healthPoints / 2) {
            currentHealthPoints += 1; // Basic recovery

            // Additional recovery if the Archer has a Magic Bow
            if (inventory.contains("Magic Bow")) {
                currentHealthPoints += currentHealthPoints / 8 - 1;
            }
        }
    }

    // Archer-specific methods
}