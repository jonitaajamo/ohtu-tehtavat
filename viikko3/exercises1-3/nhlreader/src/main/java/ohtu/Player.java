
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public Player(String name, String nationality, String team, int goals, int assists) {
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
    }

    String getName() {
        return name;
    }

    String getNationality() {
        return nationality;
    }

    int getPoints() {
        return goals + assists;
    }

    @Override
    public int compareTo(Player player) {
        return this.getPoints() - player.getPoints();
    }

    @Override
    public String toString() {
        String tabs = "\t";
        if(name.length() < 16) {
            tabs += "\t";
        }
        return name + tabs + team + "\t " + goals + " + " + " " + assists + " = " + (goals + assists);
    }
      
}
