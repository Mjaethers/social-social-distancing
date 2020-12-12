import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Player {
    private final User playerid;
    private ArrayList<Player> mates;
    private static HashMap<RingOfFire, ArrayList<Player>> lobbies = new HashMap<>();

    Player(User ID, RingOfFire Game){
        if(lobbies.get(Game) == null){
            lobbies.put(Game, new ArrayList<Player>());
        }
        lobbies.get(Game).add(this);
        playerid = ID;
        mates  = new ArrayList<Player>();
        System.out.println("new player: " + playerid);
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof Player){
            return (((Player) object).getUserFromPlayer() == this.getUserFromPlayer());
        }
        return false;
    }

    public static Player getPlayerFromUser(User User, RingOfFire Game){
        for(Player p : lobbies.get(Game)){
            if(p.getUserFromPlayer().equals(User)){
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Player> getPlayersInLobby(RingOfFire Game){
        return lobbies.get(Game);
    }

    public static ArrayList<User> getUsersInLobby(RingOfFire Game){
        ArrayList<User> users = new ArrayList<>();
        for(Player p : lobbies.get(Game)){
            users.add(p.getUserFromPlayer());
        }
        return users;
    }

    public User getUserFromPlayer(){
        return playerid;
    }

    public ArrayList<Player> getMates(){
        return mates;
    }
    public String getMatesString(){
        StringBuilder sb = new StringBuilder();
        for(Player m: mates){
            sb.append(m.getName());
            sb.append(", ");
        }
        sb.delete(sb.length()-2, sb.length()-1);
        return sb.toString();
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
    public String getName(){
        return this.getUserFromPlayer().getName();
    }

    public String getDrinkText(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.playerid.getName());
        for(Player p : this.mates){
            stringBuilder.append(", and ")
                         .append(p.getUserFromPlayer().getName());
        }
        stringBuilder.append(" drink");
        return stringBuilder.toString();
    }
}
