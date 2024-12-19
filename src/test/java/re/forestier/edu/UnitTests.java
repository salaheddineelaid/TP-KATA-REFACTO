package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.itm_manager.Item;
import re.forestier.edu.rpg.player_manager.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Test de nom du joueur")
    void testPlayerName() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Ajout d'XP au joueur")
    void testAddXp() {
        Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.addXp(5);
        assertThat(p.getXp(), is(5));
    }

    @Test
    @DisplayName("Impossible d'avoir de l'argent négatif")
    void testNegativeMoney() {
        Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            p.removeMoney(200);
        });
    }
    @Test
    @DisplayName("Impossible d'avoir de l'argent négatif")
    void testNegativeMone() {
        Player p = new Archer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            p.removeMoney(200);
        });
    }

    @Test
    @DisplayName("Impossible d'avoir de l'argent négatif")
    void testNegativeMNY() {
        Player p = new Dwarf("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            p.removeMoney(200);
        });
    }
    @Test
    @DisplayName("Impossible d'avoir de l'argent négatif")
    void testNegativeMony() {
        Player p = new Goblin("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            p.removeMoney(200);
        });
    }


    @Test
    @DisplayName("Ajouter de l'argent augmente le total")
    void testAddMoney() {
        Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.addMoney(50);
        assertThat(p.money, is(150));
    }

    @Test
    @DisplayName("Retirer de l'argent du joueur")
    void testRemoveMoney() {
        Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.removeMoney(30);
        assertThat(p.money, is(70));
    }

    @Test
    @DisplayName("Retirer plus d'argent que disponible doit lancer une exception")
    void testRemoveMoreMoneyThanAvailable() {
        Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            p.removeMoney(150);
        });
    }

    @Test
    @DisplayName("Récupérer le niveau basé sur l'XP")
    void testRetrieveLevel() {
        Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.addXp(20);
        assertThat(p.retrieveLevel(), is(2));
    }

    @Test
    @DisplayName("Test si le joueur est KO")
    void testPlayerIsKO() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.currenthealthpoints = 0;

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(0)); // Le joueur reste KO
    }

    @Test
    @DisplayName("Récupération de santé pour le Dwarf avec Holy Elixir")
    void testDwarfHealthWithElixir() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.healthpoints = 5;
        p.currenthealthpoints = 2;
        p.inventory.add(new Item("Holy Elixir", "Restores health", 0.5, 200));

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(6)); // Devrait récupérer 2 points de vie
    }

    @Test
    @DisplayName("Récupération de santé pour le Dwarf sans Holy Elixir")
    void testDwarfHealthWithoutElixir() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.healthpoints = 2;
        p.currenthealthpoints = 1;

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(5)); // Devrait récupérer 1 point de vie
    }

    @Test
    @DisplayName("Récupération de santé pour l'Archer sans Magic Bow")
    void testArcherHealthWithoutBow() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.healthpoints = 6;
        p.currenthealthpoints = 4;

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(5)); // Devrait récupérer 1 point de vie
    }

    @Test
    @DisplayName("La santé du joueur au-dessus de la moitié ne change pas")
    void testPlayerHealthAboveHalf() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.healthpoints = 4;
        p.currenthealthpoints = 3;

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(6)); // Devrait rester à 6
    }

    @Test
    @DisplayName("Joueur sans points de vie reste KO")
    void testPlayerWithNoHealthPoints() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.currenthealthpoints = 0;

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(0)); // Reste KO
    }

    @Test
    @DisplayName("Le Dwarf sans Holy Elixir récupère 1 point")
    void testDwarfWithoutHolyElixir() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.healthpoints = 10;
        p.currenthealthpoints = 3;

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(4)); // Devrait récupérer 1 point
    }

    @Test
    @DisplayName("L'Archer sans Magic Bow et santé en dessous de la moitié")
    void testArcherWithoutMagicBow() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.healthpoints = 10;
        p.currenthealthpoints = 3;

        p.recoverHealth();
        assertThat(p.currenthealthpoints, is(4)); // Devrait récupérer 1 point
    }
}