package model;

import java.util.ArrayList;
import java.util.List;

public class Guild implements Comparable<Guild> {
    private String name;
    private List<Adventurer> adventurers;

    public Guild(String name, List<Adventurer> adventurers) {
        this.name = name;
        this.adventurers = new ArrayList<>(adventurers);
    }

    public String getName() {
        return name;
    }

    public List<Adventurer> getAdventurers() {
        return new ArrayList<>(adventurers);
    }

    @Override
    public String toString() {
        return "Guild{" +
                "name='" + name + '\'' +
                ", adventurers=" + adventurers +
                '}';
    }

    @Override
    public int compareTo(Guild previous) {
        return Integer.compare(previous.adventurers.size(), this.adventurers.size());
    }
}
