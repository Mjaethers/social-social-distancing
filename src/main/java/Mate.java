import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Mate extends ListenerAdapter {
    static boolean ismateplayed = false;
    public static boolean assigned;
    public String drinkingMate;
    public static String drinker;

    public static void minigame(){
        drinker = RingOfFire.lastdrawer;
        assigned = false;
        Main.gamechannel.sendMessage(drinker + " choose who has to drink when you drink").queue();

        ismateplayed = true;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(ismateplayed && event.getAuthor().getName().equals(drinker)){
            System.out.println("players: " + Player.players);
            if(Player.players.contains(Player.getPlayerById(Choose.extractNameFromMention(event.getMessage().getMentionedMembers().get(0).toString())))){
                try{
                    Player.getPlayerById(event.getAuthor().getName()).addMate(Choose.extractNameFromMention(event.getMessage().getMentionedMembers().get(0).toString()));
                    System.out.println("attempt to add mate: " + Choose.extractNameFromMention(event.getMessage().getMentionedMembers().get(0).toString()));
                    Main.gamechannel.sendMessage(event.getAuthor().getName() + ", you are now mates with " + Player.getPlayerById(event.getAuthor().getName()).getMatesString()).queue();
                    ismateplayed = false;
                } catch(NullPointerException e){
                    System.out.println(e);
                    Main.gamechannel.sendMessage("nullpointerException").queue();
                }
            } else {
                Main.gamechannel.sendMessage("invalid mate, choose someone in the game").queue();
            }


        }

        /*if(ismatelistening){
            System.out.println("listening for mate");
            if(!assigned && event.getAuthor().toString().equals(drinker)){
                drinkingMate = event.getMessage().getMentionedMembers().get(0).toString();
                Player.getPlayerById(event.getAuthor().getName()).addMate(Player.getPlayerById(event.getMessage().getMentionedMembers().get(0).toString()));
                Main.gamechannel.sendMessage(drinker + " you are now mates with " + Player.getPlayerById(drinker).getMates().toString()).queue();
                assigned = true;
                System.out.println("added mate");
            }
            if(event.getAuthor().toString().equals("social social distancing") && event.getMessage().getContentDisplay().contains(drinker) && event.getMessage().getContentDisplay().contains("drink")){
                Main.gamechannel.sendMessage(drinkingMate + ", drink").queue();
            }
        }*/
    }
}

