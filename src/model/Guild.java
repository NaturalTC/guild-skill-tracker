package model;

/*
-	String name
-	List<Adventurer> adventurers
-	Constructor, Getters, and toString() method.
-	Implement the Comparable interface
 */

import java.util.ArrayList;
import java.util.List;

public class Guild implements Comparable<Guild> {
    private String name;
    private List<Adventurer> adventurers;

    public Guild(String name) {
        this.name = name;
        this.adventurers = new ArrayList<>();
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
