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

    @Override
    public int compareTo(Object o) {
        int comparison = 0;

        if (o instanceof Guild g){
            comparison = this.getAdventurers().size() - g.getAdventurers().size();
        }

        return comparison;
    }
}
