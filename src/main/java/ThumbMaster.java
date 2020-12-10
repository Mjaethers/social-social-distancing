
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class ThumbMaster extends ListenerAdapter {
    MessageChannel channel;
    User master;
    ArrayList<User> players = new ArrayList<>();
    ArrayList<User> unrespondedplayers;
    boolean gamerunning = false;

    public void setup(MessageChannel messageChannel, User user, ArrayList<User> listofplayers){
        channel = messageChannel;
        master = user;
        players = listofplayers;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getChannel().equals(channel)){
            if (event.getAuthor().equals(master) && event.getMessage().toString().toLowerCase().equals("thumb")){
                unrespondedplayers = new ArrayList<>();
                unrespondedplayers.addAll(players);
                unrespondedplayers.remove(master);
                gamerunning = true;
            }
            if(gamerunning){
                if(unrespondedplayers.size() > 1){
                    if(unrespondedplayers.contains(event.getMessage().getAuthor()) && event.getMessage().toString().toLowerCase().equals("thumb")){
                        unrespondedplayers.remove(event.getAuthor());
                    }
                }
                else{
                    gamerunning = false;
                    channel.sendMessage(unrespondedplayers.get(0) + " was last").queue();
                    channel.sendMessage(unrespondedplayers.get(0) + " drink!").queue();
                }
            }
        }
    }
}
