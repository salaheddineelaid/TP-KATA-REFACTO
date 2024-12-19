package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;
    private Float __real_money__;


    public int leve/*  */l;
    public int healthpoints;
    public int currenthealthpoints;
    public int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
     public Player(String playerName, String avatarName, String avatarClass, int money, ArrayList<String> inventory) {
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.level = 1; // Start at level 1
        this.xp = 0; // Start with 0 XP
        this.abilities = initializeAbilities();
    }


    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money = Integer.parseInt(money.toString()) - amount;
    }
    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
    }
    
    protected abstract HashMap<String, Integer> initializeAbilities();

    public void addXp(int xp) {
        this.xp += xp;
        // Level up logic
        int newLevel = retrieveLevel();
        if (newLevel > level) {
            level = newLevel;
            gainAbility();
            gainItem();
        }
    }

    private void gainItem() {
        String[] objectList = {
            "Lookout Ring", "Scroll of Stupidity", "Draupnir",
            "Magic Charm", "Rune Staff of Curse", "Combat Edge", "Holy Elixir"
        };
        Random random = new Random();
        inventory.add(objectList[random.nextInt(objectList.length)]);
    }

    protected void gainAbility() {
        HashMap<String, Integer> newAbilities = initializeAbilitiesForLevel(level);
        abilities.forEach((ability, value) -> abilities.put(ability, abilities.get(ability) + newAbilities.getOrDefault(ability, 0)));
    }

    protected abstract HashMap<String, Integer> initializeAbilitiesForLevel(int level);

    public int retrieveLevel() {
        // Level calculation logic based on xp
        if (xp < 10) return 1;
        else if (xp < 27) return 2;
        else if (xp < 57) return 3;
        else if (xp < 111) return 4;
        return 5;
    }
    public int getXp() {
        return this.xp;
    }

    public void majFinDeTour() {
        if (currentHealthPoints <= 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        // Call the subclass-specific recovery method
        recoverHealth();

        // Cap current health points to maximum health points
        if (currentHealthPoints > healthPoints) {
            currentHealthPoints = healthPoints;
        }
    }

    protected abstract void recoverHealth();

    

}