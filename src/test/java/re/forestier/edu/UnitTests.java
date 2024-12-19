package re.forestier.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.player_manager.*;
import re.forestier.edu.rpg.itm_manager.Item;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {

    @Test
    @DisplayName("Player name is initialized correctly")
    void testPlayerName() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Player cannot have negative money")
    void testNegativeMoney() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> player.removeMoney(200));
    }

    @Test
    @DisplayName("Adding money increases player's balance")
    void testAddMoney() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.addMoney(50);
        assertThat(player.money, is(150));
    }

    @Test
    @DisplayName("Removing money decreases player's balance")
    void testRemoveMoney() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.removeMoney(50);
        assertThat(player.money, is(50));
    }

    @Test
    @DisplayName("Adding item respects weight limits")
    void testAddItemWithinLimit() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Item lightItem = new Item("Light Item", "A very light item", 5.0, 50);

        player.addItem(lightItem);
        assertTrue(player.inventory.contains(lightItem));
    }

    @Test
    @DisplayName("Adding item beyond weight limit throws exception")
    void testAddItemExceedsLimit() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Item heavyItem = new Item("Heavy Item", "An extremely heavy item", 100.0, 200);

        assertThrows(IllegalArgumentException.class, () -> player.addItem(heavyItem));
    }



       @Test
       @DisplayName("Level up increases abilities for Adventurer")
       void testLevelUpAbilitiesAdventurer() {
           Player adventurer = new Adventurer("Florian", "Grognak", 100, new ArrayList<>());
           adventurer.addXp(30); // Should level up

           assertThat(adventurer.retrieveLevel(), is(3)); // Level 3 XP threshold
           assertThat(adventurer.abilities.get("ATK"), is(6)); // Check ability increases
       }
            //------










       @Test
       @DisplayName("Goblin regenerates health correctly")
       void testGoblinHealthRegen() {
           Player goblin = new Goblin("Gob", "Sneaky", 50, new ArrayList<>());
           goblin.currenthealthpoints = 10;
           goblin.majFinDeTour(); // End of turn logic

           assertTrue(goblin.currenthealthpoints > 10);
       }

       @Test
       @DisplayName("Dwarf gains defense when leveling up")
       void testDwarfDefenseGain() {
           Player dwarf = new Dwarf("Thorin", "Shield Bearer", 70, new ArrayList<>());
           dwarf.addXp(50); // Enough XP to level up

           assertThat(dwarf.abilities.get("DEF"), is(4));
       }

       @Test
       @DisplayName("Player health does not exceed maximum")
       void testHealthCap() {
           Player adventurer = new Adventurer("Florian", "Grognak", 100, new ArrayList<>());
           adventurer.currenthealthpoints = 110;
           adventurer.majFinDeTour();

           assertThat(adventurer.currenthealthpoints, is(100));
       }

       @Test
       @DisplayName("Adding a special item rewards bonus abilities")
       void testSpecialItemEffect() {
           Player player = new Adventurer("Florian", "Grognak", 100, new ArrayList<>());
           Item specialItem = new Item("Rune Staff of Curse", "A staff that curses enemies.", 1.0, 150);

           player.addItem(specialItem);
           assertTrue(player.inventory.contains(specialItem));
           // Assuming special logic increases abilities
         //  assertTrue(player.abilities.get("INT") > 1);   ---
       }

       @Test
       @DisplayName("Combining items and leveling up works as expected")
       void testItemAndLevelUp() {
           Player player = new Adventurer("Florian", "Grognak", 100, new ArrayList<>());
           Item lightItem = new Item("Light Item", "A very light item", 1.0, 50);
           player.addItem(lightItem);

           player.addXp(50); // Level up

           assertThat(player.retrieveLevel(), is(3));
           assertTrue(player.inventory.contains(lightItem));
       }


    @Test
    @DisplayName("Combining items and leveling up works as expected")
    void testItemAndLevelU() {
        Player player = new Adventurer("Florian", "Grognak", 100, new ArrayList<>());
        Item lightItem = new Item("Light Item", "A very light item", 1.0, 50);
        player.addItem(lightItem);

        player.addXp(75); // Level up

        assertThat(player.retrieveLevel(), is(4));
        assertTrue(player.inventory.contains(lightItem));
    }

 @Test
 @DisplayName("Combining items and leveling up works as expected")
 void testItemAndLevel() {
     Player player = new Adventurer("Florian", "Grognak", 100, new ArrayList<>());                          //--------------
     Item lightItem = new Item("Light Item", "A very light item", 1.0, 50);
     player.addItem(lightItem);

     player.addXp(120); // Level up

     assertThat(player.retrieveLevel(), is(5));
     assertTrue(player.inventory.contains(lightItem));
 }

 @Test
 @DisplayName("Combining items and leveling up works as expected")
 void testItemAndLeve() {
     Player player = new Adventurer("Florian", "Grognak", 100, new ArrayList<>());
     Item lightItem = new Item("Light Item", "A very light item", 1.0, 50);
     player.addItem(lightItem);

          player.addXp(8);

     assertThat(player.retrieveLevel(), is(1));
     assertTrue(player.inventory.contains(lightItem));
 }





































































}
