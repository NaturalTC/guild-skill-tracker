import enums.Skill;
import model.Adventurer;
import model.Guild;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // # ─── Sample Data ─────────────────────────────────────────────────────────────────────────
    public static List<Guild> getData() {
        ArrayList<Guild> guildList = new ArrayList<>();

        Adventurer a1 = new Adventurer("Aragorn", 15, "Warrior", 1500.0);
        Adventurer a2 = new Adventurer("Legolas", 20, "Rogue", 1200.0);
        Adventurer a3 = new Adventurer("Gandalf", 25, "wizard", 2000.0);

        Adventurer a4 = new Adventurer("Salazar", 55, "Rogue", 800.0);
        Adventurer a5 = new Adventurer("Montus", 63, "Warrior", 1100.0);
        Adventurer a6 = new Adventurer("Zeres", 66, "wizard", 2500.0);

        guildList.add(new Guild("Windfell", new ArrayList<Adventurer>(List.of(a1, a2, a3))));
        guildList.add(new Guild("Hammerwatch", new ArrayList<Adventurer>(List.of(a4, a5, a6))));

        return guildList;
    }

    // # ─── The Main Entry Point ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        List<Guild> guildData = getData();

        // Find Adventurers by Skill
        System.out.println("\n===Find Adventurer by skill (Swordsmanship)===");
        List<String> names = listAdventurerNamesWithSkill(guildData, Skill.SWORDSMANSHIP);
        names.forEach(System.out::println);

        // Find Adventurers by Role
        System.out.println("\n===Find Adventurer by role (Wizard)===");
        listAdventurersByRole(guildData); // return list of Adventurer names with role "Wizard"

        // Find Adventurer with the most Skills
        System.out.println("\n===Find Adventurer with the most skills===");
        var mostSkilledAdventurer = getAdventurerWithMostSkills(guildData);
        System.out.println(mostSkilledAdventurer);

        // Rank Guilds by Average Adventurer Age
        System.out.println("\n===Rank Guilds by Average Adventurer Age===");
        rankGuildsByAverageAdventurerAge(guildData);

        // Map Skill to Adventurer Count
        System.out.println("\n===Map Skill to Adventurer Count===");
        mapSkillToAdventurerCount(guildData);

        // Grant Bonus to Low Earning Adventurers
        System.out.println("\n===Grant Bonus to Low Earning Adventurers===");
        grantBonusToLowEarningAdventurers(guildData);

    }
    // # ─── Methods ─────────────────────────────────────────────────────────────────────────────

    private static List<String> listAdventurerNamesWithSkill(List<Guild> guild, Skill skill) {
        return guild.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .filter(a -> a.getSkills().contains(skill))
                .map(a -> a.getName()) // Lambda Version
                .collect(Collectors.toList());
    }

    private static void listAdventurersByRole(List<Guild> guildData) {
        Map<String, List<Adventurer>> adventurersByRole =
                guildData.stream()
                        .flatMap(g -> g.getAdventurers().stream())
                        .collect(Collectors.groupingBy(a -> a.getRole()));

        adventurersByRole.forEach((role, adventurers) -> {
            System.out.println("Role: " + role);
            adventurers.forEach(a -> System.out.println(" - " + a.getName()));
        });
    }

    private static Adventurer getAdventurerWithMostSkills(List<Guild> guildData) {
        return guildData.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .max(Comparator.comparingInt(a -> a.getSkills().size()))
                .orElse(null);
    }

    private static void rankGuildsByAverageAdventurerAge(List<Guild> guildData) {
        guildData.forEach(g -> {
            var avgAge = (int) Math.round(
                    g.getAdventurers().stream()
                            .mapToInt(Adventurer::getAge)
                            .average()
                            .orElse(0)
            );

            System.out.println("Guild: " + g.getName());
            System.out.println("Average Age: " + avgAge);
        });
    }

    private static void mapSkillToAdventurerCount(List<Guild> guildData) {
        var skillCountMap = guildData.stream() // Stream<Guild>
                .flatMap(g -> g.getAdventurers().stream())  // Stream<Adventurer>
                .flatMap(a -> a.getSkills().stream())  // Stream<Skill>
                .collect(Collectors.groupingBy(                  // Map<String, Long>
                        skill -> skill.name(),              // Key: Skill name | String
                        Collectors.counting()                    // Count adventurers per skill | Long
                ));
        skillCountMap.forEach((skill, count) -> System.out.println(skill + ": " + count)); // Print the skill count map
    }

    private static void grantBonusToLowEarningAdventurers(List<Guild> guildData) {
        guildData.stream()                                         // Stream<Guild>
                .flatMap(g -> g.getAdventurers().stream())
                .forEach(a -> {                          // For each adventurer
                    double finalGold = a.getGoldEarned();
                    if (a.getGoldEarned() < 1000) {
                        finalGold *= 1.2;                          // Apply 20% bonus
                    }
                    System.out.println(a.getName() + ": " + finalGold);
                });
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