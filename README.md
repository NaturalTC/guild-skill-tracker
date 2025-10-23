# Assignment: Lambda and Stream Processing of a Guild / Skill Tracker

## 1. Create a Skill Enum
Enum values:  
SWORDSMANSHIP, ARCHERY, THIEVERY, HORSEMANSHIP, STEALTH, HEALING, NECROMANCY, BLACKSMITHING, RUNECRAFTING, MEMECRAFTING

---

## 2. Create an Adventurer Class

### Attributes:
- String name
- int age
- String role (e.g., "Warrior", "Wizard", "Rogue")
- double goldEarned (total gold earned from quests)
- List<Skill> skills

### Requirements:
- Constructor
- Getters
- toString() method
- Implement the Comparable interface

---

## 3. Create a Guild Class

### Attributes:
- String name
- List<Adventurer> adventurers

### Requirements:
- Constructor
- Getters
- toString() method
- Implement the Comparable interface

---

## 4. Create the Stream Processing Methods

### 4.1 Filter Adventurers by Skill
Write a method that takes a Skill as input and returns a flat list of all adventurers across guilds who possess that skill.

### 4.2 Group Adventurers by Role
Write a method that groups adventurers across all guilds by their roles (e.g., "Wizard", "Rogue") and prints the groups.

### 4.3 Find the Adventurer with the Most Skills
Write a method to find the adventurer who possesses the largest number of skills.

### 4.4 Rank Guilds by Average Adventurer Age
Write a method that ranks guilds in ascending order of their average adventurer age.

### 4.5 Create a Skill-Wise Adventurer Count Map
Write a method that creates and prints a map where the keys are the names of the skills and the values are the number of adventurers proficient in that skill.

### 4.6 Bonus Gold Event
Write a method that grants a 20% bonus gold to all adventurers who have earned less than 1000 gold and prints the updated adventurer list.

---

## Helpful Methods and Classes

### Streams & Terminals
- stream()
- flatMap(...)
- filter(...)
- map(...) / mapToInt(...) / mapToDouble(...)
- collect(...)
- max(...)

### Comparators
- Comparator.comparingInt(...)
- Comparator.comparingDouble(...)

### Collectors
- Collectors.toList()
- Collectors.groupingBy(...)
- Collectors.counting()
