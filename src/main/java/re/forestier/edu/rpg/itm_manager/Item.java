package re.forestier.edu.rpg.itm_manager;

public class Item {
    private String name;
    private String description;
    private double weight;
    private int value;

    public Item(String name, String description, double weight, int value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}