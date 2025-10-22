import model.Adventurer;
import model.Guild;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static List<Guild> getData() {
        ArrayList<Guild> guildList = new ArrayList<>();

        Adventurer a1 = new Adventurer("Aragorn", 87, "Warrior", 1500.0);
        Adventurer a2 = new Adventurer("Legolas", 2931, "Rogue", 1200.0);
        Adventurer a3 = new Adventurer("Gandalf", 2019, "wizard", 2000.0);

        Adventurer a4 = new Adventurer("Salazar", 232, "Rogue", 800.0);
        Adventurer a5 = new Adventurer("Montus", 1220, "Warrior", 1100.0);
        Adventurer a6 = new Adventurer("Zeres", 4420, "wizard", 2500.0);

        guildList.add( new Guild("Windfell", new ArrayList<Adventurer>(List.of(a1,a2,a3))));
        guildList.add( new Guild("Hammerwatch", new ArrayList<Adventurer>(List.of(a4, a5, a6))));

        return guildList;
    }

    public static void main(String[] args) {
        List<Guild> guildData = getData();

        //Print Guild Data
        System.out.println("\n===Print Guild Names:===");
        guildData.forEach(x -> System.out.println(x.getName()));

        System.out.println("\n===Print Adventurer Names:===");
        List<String> adventurerNames = guildData.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .map(Adventurer::getName)
                .collect(Collectors.toList());
        System.out.println(adventurerNames);

        System.out.println("\n===Find Adventurer by skill (Swordsmanship)===");
        List<String> names = listAdventurerNamesWithSkill(guildData, "swordsmanship");
        names.forEach(System.out::println);

        System.out.println("\n===Find Adventurer by role (Wizard)===");
        List<String> namesByRole = listAdventurersByRole(guildData, "wizard");
        namesByRole.forEach(System.out::println);

    }

    /**
     * Find all adventurers by role
     * @param guildData
     * @param role
     * @return
     */
    private static List<String> listAdventurersByRole(List<Guild> guildData, String role) {
        return guildData.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .filter(a -> a.getRole().equalsIgnoreCase(role))
                .map(Adventurer::getName)
                .collect(Collectors.toList());
    }

    /**
     * Find all adventurers with a specific skill
     * @param guild
     * @param skill
     * @return
     */
    private static List<String> listAdventurerNamesWithSkill(List<Guild> guild, String skill) {
        return guild.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .filter(a -> a.getSkills().stream()
                        .anyMatch(s -> s.name().equalsIgnoreCase(skill)))
                .map(Adventurer::getName)
                .collect(Collectors.toList());
    }
}