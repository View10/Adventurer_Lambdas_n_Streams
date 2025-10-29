import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<Guild> getGuilds(){
        ArrayList<Guild> guilds = new ArrayList<>();

        Adventurer a1 = new Adventurer("Kevin", 21, Role.WIZARD, List.of(Skill.HEALING, Skill.NECROMANCY, Skill.RUNECRAFTING, Skill.HORSEMANSHIP, Skill.BLACKSMITHING));
        a1.setGoldEarned(a1.getGoldEarned()+200);
        Adventurer a2 = new Adventurer("Glaxon", 1330, Role.WARRIOR, List.of(Skill.HEALING, Skill.SWORDSMANSHIP, Skill.HORSEMANSHIP));
        Adventurer a3 = new Adventurer("Cennig", 323, Role.ARCHER, List.of(Skill.ARCHERY, Skill.HORSEMANSHIP, Skill.STEALTH, Skill.THIEVERY));

        Adventurer a4 = new Adventurer("Salazar", 232, Role.DRUID, List.of(Skill.HEALING, Skill.NECROMANCY, Skill.RUNECRAFTING, Skill.MEMECRAFTING));
        Adventurer a5 = new Adventurer("Montus", 1220, Role.WIZARD, List.of(Skill.HORSEMANSHIP, Skill.RUNECRAFTING, Skill.MEMECRAFTING, Skill.THIEVERY));
        Adventurer a6 = new Adventurer("Zeres", 4420, Role.ROGUE, List.of(Skill.HORSEMANSHIP, Skill.BLACKSMITHING, Skill.THIEVERY, Skill.STEALTH));

        guilds.add( new Guild("Roradrin", new ArrayList<>(List.of(a1,a2,a3))));
        guilds.add( new Guild("Windfell", new ArrayList<>(List.of(a4, a5, a6))));

        return guilds;
    }

    public static List<Adventurer> filterAdventuresBySkill(List<Guild> guilds, Skill skill){
        return guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .filter(a -> a.getSkills().contains(skill))
                .collect(Collectors.toList());
    }

    public static Map<Role, List<Adventurer>> groupAdventurersByRole(List<Guild> guilds){
        return guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .collect(Collectors.groupingBy(Adventurer::getRole));
    }

    public static Optional<Adventurer> getMostSkilledAdventurer(List<Guild> guilds){
        return guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .max(Comparator.comparingInt(a -> a.getSkills().size()));
    }

    public static Map<Guild, Double> rankGuilds(List<Guild> guilds){
        return guilds.stream()
                .sorted(Comparator.comparingDouble(Guild::getAverageAge))
                .collect(Collectors.toMap(g -> g, Guild::getAverageAge));
    }

    public static void getNumberOfAdventurersWithEachSkill(List<Guild> guilds){
        System.out.println(guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .flatMap(a -> a.getSkills().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting())));
    }

    public static void give20PercentGoldBonus(List<Guild> guilds){
        List<Adventurer> adventurersWith20PercentBonus = guilds.stream()
                .flatMap(g -> g.getAdventurers().stream())
                .filter(a -> a.getGoldEarned() < 1000)
                .map(a -> a.setGoldEarned(a.getGoldEarned() + (a.getGoldEarned())*0.2))
                .toList();

        System.out.println(adventurersWith20PercentBonus);
    }


    public static void main(String[] args) {
        List<Guild> guilds = getGuilds();

        List<Adventurer> filteredListBySkills = filterAdventuresBySkill(guilds, Skill.NECROMANCY);
        Map<Role, List<Adventurer>> groupOfAdventurers = groupAdventurersByRole(guilds);
        Optional<Adventurer> mostSkilledAdventurer = getMostSkilledAdventurer(guilds);
        Map<Guild, Double> averageAgePerGuild = rankGuilds(guilds);

        //Listing the adventurers with the necromancy skill
        for (Adventurer a : filteredListBySkills){
            System.out.println(a);
        }

        //Listing all adventurers separated by groups
        groupOfAdventurers.forEach((r, a) -> {
            System.out.println(r + ": " + a.stream().map(Adventurer::getName).toList());
        });

        //Printing most skilled adventurer
        System.out.println("\nThe Adventurer with most skills is " + mostSkilledAdventurer.get().getName() + " with " + mostSkilledAdventurer.get().getSkills().size() + " skills");

        //prints the guilds and their average age in ascendant order
        averageAgePerGuild.forEach((g, a) -> {
            System.out.println(g + ". Average Age: " + a);
        });

        //Prints the number of adventurers with for skill
        getNumberOfAdventurersWithEachSkill(guilds);

        //Prints a list of adventurers that benefited from the 20% gold bonus
        give20PercentGoldBonus(guilds);
    }
}
