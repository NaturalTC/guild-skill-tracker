import model.Adventurer;
import model.Guild;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    // # ─── Sample Data ─────────────────────────────────────────────────────────────────────────
    public static List<Guild> getData() {
        ArrayList<Guild> guildList = new ArrayList<>();

        Adventurer a1 = new Adventurer("Aragorn", 87, "Warrior", 1500.0);
        Adventurer a2 = new Adventurer("Legolas", 2931, "Rogue", 1200.0);
        Adventurer a3 = new Adventurer("Gandalf", 2019, "wizard", 2000.0);

        Adventurer a4 = new Adventurer("Salazar", 232, "Rogue", 800.0);
        Adventurer a5 = new Adventurer("Montus", 1220, "Warrior", 1100.0);
        Adventurer a6 = new Adventurer("Zeres", 4420, "wizard", 2500.0);

        guildList.add(new Guild("Windfell", new ArrayList<Adventurer>(List.of(a1, a2, a3))));
        guildList.add(new Guild("Hammerwatch", new ArrayList<Adventurer>(List.of(a4, a5, a6))));

        return guildList;
    }

    // # ─── The Main Entry Point ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        List<Guild> guildData = getData();

        //Print Guild Data
        System.out.println("\n===Print Guild Names===");
        guildData.forEach(x -> System.out.println(x.getName()));

        System.out.println("\n===Print Adventurer Names===");
        List<String> adventurerNames = guildData.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .map(Adventurer::getName)
                .collect(Collectors.toList());
        System.out.println(adventurerNames);


        // Find Adventurers by Skill
        System.out.println("\n===Find Adventurer by skill (Swordsmanship)===");
        List<String> names = listAdventurerNamesWithSkill(guildData, "swordsmanship");
        names.forEach(System.out::println);

        // Find Adventurers by Role
        System.out.println("\n===Find Adventurer by role (Wizard)===");
        List<String> namesByRole = listAdventurersByRole(guildData, "wizard"); // return list of Adventurer names with role "Wizard"
        namesByRole.forEach(System.out::println); // Print here to separate output logic

        // Rank Guilds by Average Adventurer Age
        System.out.println("\n===Rank Guilds by Average Adventurer Age===");
        List<Guild> rankedGuilds = rankGuildsByAverageAdventurerAge(guildData);
        rankedGuilds.forEach(g -> {
            double avgAge = g.getAdventurers().stream().mapToInt(Adventurer::getAge).average().orElse(0);
            System.out.println(g.getName() + " - Average Age: " + avgAge);
        });

        // Map Skill to Adventurer Count
        System.out.println("\n===Map Skill to Adventurer Count===");
        mapSkillToAdventurerCount(guildData);

        // Grant Bonus to Low Earning Adventurers
        System.out.println("\n===Grant Bonus to Low Earning Adventurers===");
        grantBonusToLowEarningAdventurers(guildData);




    }
    // # ─── Methods ─────────────────────────────────────────────────────────────────────────────
    /**
     * Find all adventurers with a specific skill
     *
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

    /**
     * Find all adventurers by role
     *
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
     * Return Adventurer with the Most Skills can only use collectors.toList(), Collectors.groupingBy(), Collectors.counting()
     *
     * @param guildData
     * @return
     */
//    private static Adventurer getAdventurerWithMostSkills(List<Guild> guildData) {
//        return guildData.stream()
//                .flatMap(g -> g.getAdventurers().stream())
//                .max((a1, a2) -> Integer.compare(a1.getSkills().size(), a2.getSkills().size()))
//                .Collectors.toList()
//                .orElse(null);
//    }

    /**
     * Rank guilds by average adventurer age
     *
     * @param guildData
     * @return
     */
    private static List<Guild> rankGuildsByAverageAdventurerAge(List<Guild> guildData) {
        return guildData.stream()
                .sorted((g1, g2) -> {
                    double avgAge1 = g1.getAdventurers().stream().mapToInt(Adventurer::getAge).average().orElse(0);
                    double avgAge2 = g2.getAdventurers().stream().mapToInt(Adventurer::getAge).average().orElse(0);
                    return Double.compare(avgAge1, avgAge2);
                })
                .collect(Collectors.toList());
    }

    /**
     * Map skill to adventurer count
     * @param guildData
     */
    private static void mapSkillToAdventurerCount(List<Guild> guildData) {
        var skillCountMap = guildData.stream() // Stream<Guild>
                .flatMap(g -> g.getAdventurers().stream()) // Flatten to adventurers | Stream<Adventurer>
                .flatMap(a -> a.getSkills().stream()) // Flatten to skills | Stream<Skill>
                .collect(Collectors.groupingBy(// Group by skill name | Map<String, Long>
                        skill -> skill.name(), // Key: Skill name | String
                        Collectors.counting()// Count adventurers per skill | Long
                ));
        skillCountMap.forEach((skill, count) -> System.out.println(skill + ": " + count)); // Print the skill count map
    }

    /**
     *  Grant 20% bonus to adventurers earning less than 1000 gold
     * @param guildData
     */
    private static void grantBonusToLowEarningAdventurers(List<Guild> guildData) {
        guildData.stream() // Stream<Guild>
                .flatMap(g -> g.getAdventurers().stream()) // Flatten to adventurers | Stream<Adventurer>
                .filter(a -> a.getGoldEarned() < 1000) // Filter adventurers with less than 1000 gold | Steam<Adventurer>
                .forEach(a -> a.setGoldEarned(a.getGoldEarned() * 1.2)); // Grant 20% bonus | void

        // Print updated adventurer list
        guildData.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .forEach(a -> System.out.println(a.getName() + ": " + a.getGoldEarned()));
    }
}

    /*
# ─── Helpful Methods and Classes ────────────────────────────────────────────────────────────────

# ─── Streams & Terminals ────────────────────────────────────────────────────────────────────────

    stream() - Creates a stream from a collection (like a List or Set) so you can process its elements using functional
               operations like filter(), map(), and collect().

    flatMap(...) - Used to flatten nested structures (like a list of lists) into a single stream.
                   Example: when each Guild contains multiple Adventurers, flatMap() combines all adventurers from all
                   guilds into one stream.

    filter(...) - Used to select elements that meet a specific condition (returns only the elements that pass the test).


    map(...) / mapToInt(...) / mapToDouble(...) - Transforms each element of a stream into something else.
                                                  map() → used for general transformations (e.g., get names)
                                                  mapToInt() / mapToDouble() → used for numeric transformations
                                                  (to perform calculations like average or sum)

    collect(...) - A terminal operation that gathers the results of a stream into a collection, map, or summary result.
               Usually used with Collectors.

    max(...) - Finds the maximum element in a stream based on a comparator.

# ─── Comparators ────────────────────────────────────────────────────────────────────────────────

    Comparator.comparingInt(...) - Creates a comparator based on an integer value (e.g., age, skill count).
                                   Useful when sorting or finding min/max.

    Comparator.comparingDouble(...) - Creates a comparator based on a double value (e.g., gold earned, average age).

# ───  Collectors ────────────────────────────────────────────────────────────────────────────────

    Collectors.toList() - Collects elements of a stream into a List.

    Collectors.groupingBy(...) - Groups elements by a specific property and returns a Map where the key is the grouping
                                 criterion and the value is a list of matching elements.

    Collectors.counting() - Counts the number of elements in each group (commonly used with groupingBy).
     */