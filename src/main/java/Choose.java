import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Choose extends ListenerAdapter {
    static boolean iscardplayed = false;
    static String author;
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(iscardplayed && !event.getAuthor().isBot() && event.getAuthor().getName().equals(author) && !event.getMessage().getMentionedMembers().isEmpty() &&!event.getMessage().getContentDisplay().equals("!play ring of fire")){ //ToDo the last and of this if should be removeable
            String mentionedmember = extractNameFromMention(event.getMessage().getMentionedMembers().toString());
            if(mentionedmember.equals("social social distancing")){
                Main.gamechannel.sendMessage("Drinking...").queue();
                Main.gamechannel.sendMessage("Drinking...").queue();
                Main.gamechannel.sendMessage("Drinking...").queue();
                Main.gamechannel.sendMessage("Drinking...").queue();
                Main.gamechannel.sendMessage("Drinking...").queue();
                Main.gamechannel.sendMessage("Drinking...").queue();
                Main.gamechannel.sendMessage("Fuck you " + event.getAuthor().getName()).queue();
                iscardplayed = false;
            } else {
                try{
                    Player.getPlayerById(extractNameFromMention(event.getMessage().getMentionedMembers().get(0).toString())).drink(Main.gamechannel);
                    iscardplayed = false;
                } catch(NullPointerException e){
                    System.out.println(e);
                    Main.gamechannel.sendMessage("couldn't find this player, choose someone in the game").queue();
                }
            }


            //event.getChannel().sendMessage(mentionedmember + ", drink").queue();
            System.out.println(event.getMessage().toString());
        }
    }
    public static void minigame(){
        author = RingOfFire.lastdrawer;
        Main.messageReceivedEvent.getChannel().sendMessage("Choose: " + author + " mention someone who has to drink").queue();
        iscardplayed = true;
        //ToDo: this has to be changed to be that the last person to say !draw is mentioned
        //ToDo: I suggest a lastdrawer variable in main
    }
    public static String extractNameFromMention(String Mention){
        System.out.println(Mention);
        return(Mention.substring(Mention.indexOf("(U:") + 3, Mention.indexOf("(", Mention.indexOf("(U:") + 1)));
    }
}
