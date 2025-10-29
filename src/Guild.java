import java.util.List;

public class Guild implements Comparable{
    private String name;
    private List<Adventurer> adventurers;

    public Guild(String name){
        this.name = name;
    }

    public Guild(String name, List<Adventurer> adventurers){
        this.name = name;
        this.adventurers = adventurers;
    }

    public String getName() {
        return name;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setName(String name) {
        if (!name.isEmpty()){
            this.name = name;
        }
    }

    public double getAverageAge(){
        int sum = 0;
        for (Adventurer a : adventurers){
            sum += a.getAge();
        }

        double average = (double) sum /adventurers.size();

        return average;
    }

    @Override
    public int compareTo(Object o) {
        int comparison = 0;

        if (o instanceof Guild g){
            comparison = Double.compare(this.getAverageAge(), g.getAverageAge());
        }

        return comparison;
    }

    @Override
    public String toString() {
        return this.name + ": " + adventurers.size() + " members";
    }
}
