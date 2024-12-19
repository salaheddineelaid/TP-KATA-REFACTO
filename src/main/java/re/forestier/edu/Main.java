package re.forestier.edu;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player_manager.Adventurer;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Adventurer ad = new Adventurer("A","B",100,new ArrayList<>());
        System.out.println(Affichage.afficherJoueur(ad));




        System.out.println(Affichage.afficherJoueurMarkdown(ad));

        System.out.println("------------------");


    }
}