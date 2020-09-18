import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final User playerid;
    private ArrayList<Player> mates;
    private static ArrayList<Player> players = new ArrayList<Player>();

    Player(User ID){
        playerid = ID;
        mates  = new ArrayList<Player>();
        players.add(this);
        System.out.println("new player: " + playerid);
    }

    public User getPlayerId(){
        return playerid;
    }

    public ArrayList<Player> getMates(){
        return mates;
    }

    public boolean hasMate(Player Player){
        return mates.contains(Player);
    }

    public void addMate(Player Player){
        if(!this.hasMate(Player)){ //makes sure the two players are not already mates- prevents infinite recursive loop
            mates.add(Player);
            Player.addMate(this);
        }
    }

    public String getDrinkText(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.playerid.getName());
        for(Player p : this.mates){
            stringBuilder.append(", and ")
                         .append(p.getPlayerId().getName());
        }
        stringBuilder.append("drink");
        return stringBuilder.toString();
    }

    public static ArrayList<Player> getPlayers(){
        return players;
    }

    public static Player getPlayerByID(User ID){
        for(Player p : players){
            if(p.getPlayerId().equals(ID)){
                return p;
            }
        }
        return null;
    }

    public static boolean isPlayer(User ID){
        return (getPlayerByID(ID) != null);
    }
}
