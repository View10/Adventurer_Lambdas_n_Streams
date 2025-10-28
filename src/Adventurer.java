import java.util.List;

public class Adventurer implements Comparable{
    private String name;
    private int age;
    private Role role;
    private double goldEarned;
    private List<Skill> skills;

    public Adventurer(String name, int age, Role role, List<Skill> skills){
        this.name = name;
        this.age = age;
        this.role = role;
        this.skills = skills;
        this.goldEarned = 900;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Role getRole() {
        return role;
    }

    public double getGoldEarned() {
        return goldEarned;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setName(String name) {
        if (!name.isEmpty()){
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 15){
            this.age = age;
        }
    }

    public void setSkills(List<Skill> skills) {
        if (skills != null){
            this.skills = skills;
        }
    }

    public Adventurer setGoldEarned(double goldEarned) {
        if (goldEarned >= 0){
            this.goldEarned = goldEarned;
        }
        return this;
    }

    @Override
    public String toString() {
        String info = "Name: " + this.name + "\n" +
                "Age: " + this.age + "\n" +
                "Role: " + this.role + "\n" +
                "Gold: " + this.goldEarned + "\n" +
                "Skills" + this.skills + "\n";

        return info;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
