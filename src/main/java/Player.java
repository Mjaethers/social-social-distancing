import net.dv8tion.jda.core.entities.MessageChannel;

import java.util.ArrayList;

public class Player {
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static int playernumber;

    private String playerid;
    private ArrayList<Player> mates = new ArrayList<Player>();

    Player(String PlayerID){
        playerid = PlayerID;
        players.add(this);
        playernumber = players.size();
    }

    public static Player getPlayerById(String ID){
        for(Player p : players){
            if(p.getPlayerId().equals(ID)){
               return p;
            }
        }
        return null;
    }

    public String getPlayerId(){
        return playerid;
    }

    public void addMate(Player Player){
        mates.add(Player); //may have to sprinkle in some this
    }

    public void addMate(String PlayerID){
        mates.add(getPlayerById(PlayerID));
    }

    public ArrayList<Player> getMatesArray(){
        return mates; //may need some this here too
    }

    public String getMatesString(){
        StringBuilder stringbuilder = new StringBuilder();
        for(Player p : mates){
            stringbuilder.append(p.getPlayerId());
            stringbuilder.append(", ");
        }
        return stringbuilder.toString();
    }

    public static ArrayList<Player> getPlayersArray(){ return players;}

    public static String getPlayersString(){
        StringBuilder stringbuilder = new StringBuilder();
        for(Player p : players){
            stringbuilder.append(p.getPlayerId());
        }
        return stringbuilder.toString();
    }

    public void drink(MessageChannel messageChannel) throws NullPointerException{
        messageChannel.sendMessage(playerid + " drink").queue();
        try{
            for(Player p : mates){
                messageChannel.sendMessage(p.getPlayerId() + ", you are " + playerid + "'s mate, drink").queue();
            }
        } catch(NullPointerException e){
            System.out.println(e);
        }

    }

}
