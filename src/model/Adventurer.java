package model;

import enums.Skill;

import java.util.ArrayList;
import java.util.List;

public class Adventurer implements Comparable<Adventurer>
{

    // Attributes
    private String name;
    private int age;
    private String role;
    private double goldEarned;
    private List<Skill> skills;

    // Constructor
    public Adventurer(String name, int age, String role, double goldEarned) {
        this.name = name;
        this.age = age;
        this.role = role.strip().toLowerCase();
        this.goldEarned = goldEarned;
        this.skills = new  ArrayList<>();
        assignRoleSkills();

    }

    // Getters
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

    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    // Helper Method
    public void assignRoleSkills()
    {
        switch (role)
        {
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
                break;
        }
    }

    // Compare Amount of Gold
    @Override
    public int compareTo(Adventurer other) {
        return Double.compare(this.goldEarned, other.goldEarned);
    }

    public String toString()
    {
        return "Name: " + name +
                "\nAge: " + age +
                "\nRole: " + role +
                "\nGold Earned: " + goldEarned +
                "\nSkills: " + skills;
    }
}
