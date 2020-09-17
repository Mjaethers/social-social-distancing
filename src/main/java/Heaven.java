import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

public class Heaven extends ListenerAdapter {
    static String wordtotype;
    static boolean iscardplayed = false;
    static ArrayList<String> notrespondedplayers = new ArrayList<String>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(iscardplayed && event.getMessage().getContentDisplay().equals("heaven")){
            notrespondedplayers.remove(event.getAuthor().getName());
            if(notrespondedplayers.size() == 1){
                Main.gamechannel.sendMessage(notrespondedplayers.get(0) + " is the last").queue();
                Player.getPlayerById(notrespondedplayers.get(0)).drink(Main.gamechannel);
                iscardplayed = false;
            }
        }
    }

    public static void minigame(){
        wordtotype = "heaven";
        Main.gamechannel.sendMessage("heaven: everyone type heaven as fast as they can").queue();
        Main.gamechannel.sendMessage("heaven").queue();
        notrespondedplayers.clear();
        notrespondedplayers.addAll(RingOfFire.players);
        iscardplayed = true;
    }
    public static void minigame(String Word){
        wordtotype = Word;
        Main.gamechannel.sendMessage("type " + wordtotype + " as fast as you can").queue();
        Main.gamechannel.sendMessage(wordtotype).queue();
        notrespondedplayers.clear();
        //ToDo
    }
}
