import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Choose extends ListenerAdapter{
    User chooser;
    MessageChannel channel;
    boolean listening = false;

    public void setup(User user, MessageChannel messagechannel){
        chooser = user;
        channel = messagechannel;
        listening = true;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getChannel().equals(channel) && event.getMessage().getAuthor().equals(chooser) && !event.getMessage().getMentionedMembers().isEmpty() && listening){
            channel.sendMessage( event.getMessage().getMentionedMembers().get(0).getNickname() + " drink").queue();
            listening = false;
        }
    }
}
