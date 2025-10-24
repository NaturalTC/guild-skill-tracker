package model;

import enums.Skill;

import java.util.ArrayList;
import java.util.List;

public class Adventurer implements Comparable<Adventurer> {

    // Attributes
    private String name;
    private int age;
    private String role;
    private double goldEarned;
    private List<Skill> skills;

    // Constructor - I should probably handle the role verification in a different way
    public Adventurer(String name, int age, String role, double goldEarned) {
        this.name = name;
        this.age = age;
        this.role = role.strip().toLowerCase();
        this.goldEarned = goldEarned;
        setSkills(skills);

    }

    // Methods
    @Override
    public int compareTo(Adventurer previous) {
        return Double.compare(previous.goldEarned, this.goldEarned);
    }

    // Get Skills
    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public double getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(double goldEarned) {
        this.goldEarned = goldEarned;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = new ArrayList<>(skills);
        switch (role) {
            case "warrior":
                this.skills.add(Skill.SWORDSMANSHIP);
                this.skills.add(Skill.BLACKSMITHING);
                this.skills.add(Skill.HORSEMANSHIP);
                this.skills.add(Skill.ARCHERY);
                break;
            case "rogue":
                this.skills.add(Skill.THIEVERY);
                this.skills.add(Skill.STEALTH);
                this.skills.add(Skill.MEMECRAFTING);
                break;
            case "wizard":
                this.skills.add(Skill.HEALING);
                this.skills.add(Skill.NECROMANCY);
                this.skills.add(Skill.RUNECRAFTING);
                break;
            default:
                // keep provided skills
                break;
        }
    }
    // toString Method
    public String toString() {
        return "Name: " + name +
                "\nAge: " + age +
                "\nRole: " + role +
                "\nGold Earned: " + goldEarned +
                "\nSkills: " + skills;
    }
}
