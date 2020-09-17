import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

public class Heaven extends ListenerAdapter {
    MessageChannel channel;
    ArrayList<User> players = new ArrayList<>();
    ArrayList<User> unrespondedplayers;
    boolean playing = false;

    public void setup(MessageChannel messagechannel, ArrayList<User> listofplayers){
        channel = messagechannel;
        players = listofplayers;
        unrespondedplayers = new ArrayList<>();
        unrespondedplayers.addAll(players);
        playing = true;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getChannel().equals(channel) && playing){
            if(unrespondedplayers.size() > 1 && unrespondedplayers.contains(event.getAuthor())){
                unrespondedplayers.remove(event.getAuthor());
            }
            else{
                playing = false;
                channel.sendMessage(unrespondedplayers.get(0).toString() + " was the last to respond").queue();
                channel.sendMessage(unrespondedplayers.get(0).toString() + " drink!").queue();
            }
        }
    }
}
