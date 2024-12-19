package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.*;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.UpdatePlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    void testUpdate() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 5);
        assertThat(p.getXp(), is(5));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    ////////////////////////////////////////

    @Test
@DisplayName("Constructor with valid avatar")
void testConstructorWithValidAvatar() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    assertThat(p.Avatar_name, is("Grognak le barbare"));
    assertThat(p.getAvatarClass(), is("ADVENTURER"));
}

@Test
@DisplayName("Constructor with invalid avatar class")
void testConstructorWithInvalidAvatarClass() {
    player p = new player("Florian", "Grognak le barbare", "WARRIOR", 100, new ArrayList<>());
    assertThat(p.getAvatarClass(), is(nullValue()));  // Assuming the class is not set for invalid avatar class
}

@Test
@DisplayName("Adding money to player increases total money")
void testAddMoney() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    p.addMoney(50);  // Adding 50 to the player's money
    assertThat(p.money, is(150));  // Total money should now be 150
}

@Test
@DisplayName("Removing money from player")
void testRemoveMoney() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    p.removeMoney(30);
    assertThat(p.money, is(70));
}

@Test
@DisplayName("Removing more money than available should throw exception")
void testRemoveMoreMoneyThanAvailable() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        p.removeMoney(150);
    });
}

@Test
@DisplayName("Retrieve level based on XP")
void testRetrieveLevel() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    p.xp = 20;  // Setting XP to 20 for this test
    assertThat(p.retrieveLevel(), is(2));  // Level should be 2 based on the XP
}

@Test
@DisplayName("Retrieve level 3 based on XP")
void testRetrieveLevel3() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    p.xp = 56;  // Setting XP to 57 for this test
    assertThat(p.retrieveLevel(), is(3));  // Should return level 3
}

@Test
@DisplayName("Retrieve level 4 based on XP")
void testRetrieveLevel4() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    p.xp = 110;  // Setting XP to 100 for this test, which is between 57 and 111
    assertThat(p.retrieveLevel(), is(4));  // Should return level 4
}

@Test
@DisplayName("Retrieve level 5 based on XP")
void testRetrieveLevel5() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    p.xp = 111;  // Setting XP to 150, which is greater than 111
    assertThat(p.retrieveLevel(), is(5));  // Should return level 5
}

@Test
@DisplayName("Initial abilities based on avatar class")
void testInitialAbilities() {
    player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
    assertThat(p.abilities, is(notNullValue()));
}

/////////////////////////////////////////

@Test
    @DisplayName("Player is KO")
    void testPlayerIsKO() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 0;

        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(0)); // Le joueur reste KO
    }

    @Test
    @DisplayName("Player health below half for DWARF with Holy Elixir")
    void testDwarfHealthBelowHalfWithElixir() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.healthpoints = 10;
        p.currenthealthpoints = 4;
        p.inventory.add("Holy Elixir");

        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(6)); // Devrait récupérer 2 points de vie
    }

    @Test
    @DisplayName("Player health below half for DWARF without Holy Elixir")
    void testDwarfHealthBelowHalfWithoutElixir() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.healthpoints = 10;
        p.currenthealthpoints = 4;

        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(5)); // Devrait récupérer 1 point de vie
    }



    @Test
    @DisplayName("Player health below half for ARCHER without Magic Bow")
    void testArcherHealthBelowHalfWithoutBow() {
        player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p.healthpoints = 10;
        p.currenthealthpoints = 4;

        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(5)); // Devrait récupérer 1 point de vie
    }

    @Test
    @DisplayName("Player health above half")
    void testPlayerHealthAboveHalf() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.healthpoints = 10;
        p.currenthealthpoints = 6;

        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(6)); // Devrait rester à 6
    }

  
    /*@Test
    @DisplayName("Player health equal to max health")
    void testPlayerHealthAtMax() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.healthpoints = 5;
        p.currenthealthpoints = 20;

        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(10)); // Devrait rester à 10
    }*/


//////////////////////////////////////////////

@Test
@DisplayName("Player with no health points remains KO")
void testPlayerWithNoHealthPoints() {
    player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
    p.currenthealthpoints = 0; // Le joueur commence KO

    UpdatePlayer.majFinDeTour(p);
    assertThat(p.currenthealthpoints, is(0)); // Reste KO
}


@Test
@DisplayName("DWARF without Holy Elixir recovers 1 point")
void testDwarfWithoutHolyElixir() {
    player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
    p.healthpoints = 10;
    p.currenthealthpoints = 3; // Moins de la moitié

    UpdatePlayer.majFinDeTour(p);
    assertThat(p.currenthealthpoints, is(4)); // Devrait récupérer 1 point
}

@Test
@DisplayName("ARCHER without Magic Bow and health below half")
void testArcherWithoutMagicBow() {
    player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
    p.healthpoints = 10;
    p.currenthealthpoints = 3; // Moins de la moitié

    UpdatePlayer.majFinDeTour(p);
    assertThat(p.currenthealthpoints, is(4)); // Devrait récupérer 1 point
}

////////////////////////////////////////


/*@Test
    public void testAfficherJoueurAvecAucuneCapaciteEtInventaire() {
        // Création d'un joueur sans capacités ni objets dans l'inventaire
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.xp = 50;
        p.abilities = new HashMap<>();
        p.setLevel(1); // Assurez-vous que cette méthode existe

        String expectedOutput = "Joueur Grognak le barbare joué par Florian\n" +
                                "Niveau : 1 (XP totale : 50)\n\n" +
                                "Capacités :\n\n" +
                                "Inventaire :\n";

        String actualOutput = Affichage.afficherJoueur(p);
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void testAfficherJoueurAvecCapacitesEtInventaire() {
        // Création d'un joueur avec des capacités et des objets dans l'inventaire
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.xp = 100;
        p.abilities = new HashMap<>();
        p.abilities.put("Force", 3);
        p.abilities.put("Agilité", 2);
        p.inventory.add("Épée");
        p.inventory.add("Bouclier");
        p.setLevel(2); // Assurez-vous que cette méthode existe

        String expectedOutput = "Joueur Grognak le barbare joué par Florian\n" +
                                "Niveau : 2 (XP totale : 100)\n\n" +
                                "Capacités :\n" +
                                "   Force : 3\n" +
                                "   Agilité : 2\n\n" +
                                "Inventaire :\n" +
                                "   Épée\n" +
                                "   Bouclier\n";

        String actualOutput = Affichage.afficherJoueur(p);
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void testAfficherJoueurAvecCapacitesSeules() {
        // Création d'un joueur avec des capacités mais sans objets dans l'inventaire
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.xp = 75;
        p.abilities = new HashMap<>();
        p.abilities.put("Intelligence", 5);
        p.setLevel(3); // Assurez-vous que cette méthode existe

        String expectedOutput = "Joueur Grognak le barbare joué par Florian\n" +
                                "Niveau : 3 (XP totale : 75)\n\n" +
                                "Capacités :\n" +
                                "   Intelligence : 5\n\n" +
                                "Inventaire :\n";

        String actualOutput = Affichage.afficherJoueur(p);
        assertThat(actualOutput, is(expectedOutput));
    }

    @Test
    public void testAfficherJoueurAvecInventaireSeul() {
        // Création d'un joueur sans capacités mais avec des objets dans l'inventaire
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.xp = 30;
        p.abilities = new HashMap<>();
        p.inventory.add("Potion de soin");
        p.setLevel(1); // Assurez-vous que cette méthode existe

        String expectedOutput = "Joueur Grognak le barbare joué par Florian\n" +
                                "Niveau : 1 (XP totale : 30)\n\n" +
                                "Capacités :\n\n" +
                                "Inventaire :\n" +
                                "   Potion de soin\n";

        String actualOutput = Affichage.afficherJoueur(p);
        assertThat(actualOutput, is(expectedOutput));
    }*/




}
