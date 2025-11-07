package model;

import java.util.ArrayList;
import java.util.List;


public class Guild implements Comparable<Guild>
{
    // Attributes
    private String name;
    private List<Adventurer> adventurers;

    // Constructor
    public Guild(String name, List<Adventurer> adventurers) {
        this.name = name;
        this.adventurers = new ArrayList<>(adventurers);
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Adventurer> getAdventurers() {
        return new ArrayList<>(adventurers);
    }

    // Compares size of each guild
    @Override
    public int compareTo(Guild other) {
        return Integer.compare(this.adventurers.size(), other.adventurers.size());
    }

    @Override
    public String toString() {
        return "Guild: " + name +
                "\nAdventurers: " + adventurers;
    }
}
