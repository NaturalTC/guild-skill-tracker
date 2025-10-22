import model.Adventurer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Adventurer's Guild!");

        List<Adventurer> adventurers = new ArrayList<>();

        // Add adventurers to the list (example)
        adventurers.add(new Adventurer("Aragorn", 87, "Warrior",
                1500.0));
        adventurers.add(new Adventurer("Legolas", 2931, "Rogue",
                1200.0));
        adventurers.add(new Adventurer("Gandalf", 2019, "Mage",
                2000.0));

        for (Adventurer adv : adventurers) {
            System.out.println("Adventurer: " + adv.getName() + ", Role: " + adv.getRole() +
                    ", Gold Earned: " + adv.getGoldEarned() + ", Skills: " + adv.getSkills());
        }
    }


}