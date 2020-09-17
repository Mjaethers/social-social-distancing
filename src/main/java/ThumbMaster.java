import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

public class ThumbMaster extends ListenerAdapter {
    public static String thumbmasterid;
    public static ArrayList<String> notrespondedplayers = new ArrayList<String>();
    boolean isthumbmaster = false;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().getName().equals(thumbmasterid) && event.getMessage().getContentDisplay().equals("thumb") && !isthumbmaster){
            notrespondedplayers.clear();
            notrespondedplayers.addAll(RingOfFire.players);
            notrespondedplayers.remove(thumbmasterid);
            Main.gamechannel.sendMessage("thumb").queue();
            isthumbmaster = true;
        }
        if(isthumbmaster && event.getMessage().getContentDisplay().equals("thumb")){
            notrespondedplayers.remove(event.getAuthor().getName());
            if(notrespondedplayers.size() == 1){
                Main.gamechannel.sendMessage(notrespondedplayers.get(0) + " is the last").queue();
                Player.getPlayerById(notrespondedplayers.get(0)).drink(Main.gamechannel);
                isthumbmaster = false;
            }
        }
    }
    public static void minigame(){
        Main.gamechannel.sendMessage("Thumbmaster: " + RingOfFire.lastdrawer + ", you are the thumb master").queue(); //ToDo: this has to be changed to last person to call !draw
        thumbmasterid = RingOfFire.lastdrawer; //ToDo: same as above
    }
}
