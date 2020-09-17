import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Mate extends ListenerAdapter {
    String origin;
    MessageChannel channel;
    User mate;
    boolean choose = false;

    public void setup(MessageChannel messagechannel, String user){
        channel = messagechannel;
        origin = user;
        choose = true;

    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getChannel().equals(channel)){
            if(event.getAuthor().toString().equals("U:social social distancing(697506461630201928)") &&  event.getMessage().toString().contains(origin + " drink")){
                //Mate has to drink
            }
            if(choose){

            }
        }
    }
}
