package re.forestier.edu.rpg.player_manager;

import re.forestier.edu.rpg.itm_manager.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract  class Player {
    public String playerName;
    public String avatarName;
    private String avatarClass;

    public Integer money;
    private Float __real_money__;


    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    public int xp;
    protected double maxWeight; // Poids maximal que le joueur peut porter
    protected double currentWeight; // Poids actuel dans l'inventaire


    public HashMap<String, Integer> abilities;
    public ArrayList<Item> inventory;
     public Player(String playerName, String avatarName, String avatarClass, int money, double maxWeight, ArrayList<Item> inventory) {
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.level = 1; // Start at level 1
        this.xp = 0; // Start with 0 XP
        this.abilities = initializeAbilities();
        this.currenthealthpoints = 100;
        this.healthpoints=100;
        this.maxWeight = maxWeight;

         this.currentWeight = 0;
    }

    public Player(String playerName, String avatarName, String avatarClass, int money, ArrayList<Item> inventory) {
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.level = 1; // Start at level 1
        this.xp = 0; // Start with 0 XP
        this.abilities = initializeAbilities();
        this.currenthealthpoints = 100;
        this.healthpoints=100;
        this.maxWeight = 50;

        this.currentWeight = 0;
    }


    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money = Integer.parseInt(money.toString()) - amount;
    }

    public void addItem(Item item) throws IllegalArgumentException {
        if (currentWeight + item.getWeight() > maxWeight) {
            throw new IllegalArgumentException("Cannot add item: weight limit exceeded!");
        }
        inventory.add(item);
        currentWeight += item.getWeight();
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
        Item[] objectList = {
                new Item("Lookout Ring", "A magical ring that enhances vision.", 0.5, 100),
                new Item("Scroll of Stupidity", "A scroll that confuses enemies.", 0.2, 50),
                new Item("Draupnir", "A magical ring that multiplies wealth.", 0.3, 200),
                new Item("Magic Charm", "A charm that grants luck.", 0.1, 75),
                new Item("Rune Staff of Curse", "A staff that curses enemies.", 1.0, 150),
                new Item("Combat Edge", "A blade that sharpens fighting skills.", 0.8, 125),
                new Item("Holy Elixir", "An elixir that restores health.", 0.4, 200)
        };
        Random random = new Random();
        addItem(objectList[random.nextInt(objectList.length)]);
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
        if (currenthealthpoints <= 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        // Call the subclass-specific recovery method
        recoverHealth();

        // Cap current health points to maximum health points
        if (currenthealthpoints > healthpoints) {
            currenthealthpoints = healthpoints;
        }
    }

    protected abstract void recoverHealth();

    

}