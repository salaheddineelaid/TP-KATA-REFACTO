package re.forestier.edu.rpg;

import re.forestier.edu.rpg.player_manager.Player;

public class Affichage {

    public static String afficherJoueur(Player player) {
        final String[] finalString = {"Joueur " + player.avatarName + " joué par " + player.playerName};
        finalString[0] += "\nNiveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\nCapacités :";
        player.abilities.forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });
        finalString[0] += "\n\nInventaire :";
        player.inventory.forEach(item -> {
            finalString[0] += "\n   " + item.getName();
        });

        return finalString[0];
    }

    public static String afficherJoueurMarkdown(Player player) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("# Joueur ").append(player.avatarName).append(" joué par ").append(player.playerName).append("\n");
        markdown.append("## Niveau : ").append(player.retrieveLevel()).append(" (XP totale : ").append(player.xp).append(")\n\n");

        markdown.append("**Capacités :**\n");
        player.abilities.forEach((name, level) -> {
            markdown.append("* ").append(name).append(" : ").append(level).append("\n");
        });

        markdown.append("\n**Inventaire :**\n");
        player.inventory.forEach(item -> {
            markdown.append("* ").append(item.getName()).append("\n");
        });

        return markdown.toString();
    }

}
