import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final String playerid;
    private ArrayList<Player> mates;
    private static ArrayList<Player> players = new ArrayList<Player>();

    Player(String ID){
        playerid = ID;
        mates  = new ArrayList<Player>();
        players.add(this);
    }

    public String getPlayerId(){
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

    public static Player getPlayerByID(String ID){
        for(Player p : players){
            if(p.getPlayerId().equals(ID)){
                return p;
            }
        }
        return null;
    }
}
