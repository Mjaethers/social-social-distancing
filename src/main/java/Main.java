import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public class Main extends ListenerAdapter{
    public static JDABuilder builder;
    static MessageChannel messageChannel;
    static User user;
    ArrayList<RingOfFire> currentgames = new ArrayList<>();

    public static void main(String[] args) throws LoginException{
        builder = new JDABuilder(AccountType.BOT);
        builder.setToken("Njk3NTA2NDYxNjMwMjAxOTI4.Xo4RkA.d67u55JBO72Jc_QWDHmPCoxIGuc");

        builder.addEventListener(new Main());
        builder.addEventListener(new RingOfFire());
        builder.addEventListener(new Choose());
        builder.addEventListener(new ThumbMaster());
        builder.addEventListener(new Heaven());
        builder.addEventListener(new Mate());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        MessageChannel channel = event.getTextChannel();
        if(event.getMessage().getContentDisplay().toLowerCase().equals("!play ring of fire") && !gameRunning(channel)){
            currentgames.add(new RingOfFire());
            currentgames.get(currentgames.size()-1).setMessageChannel(channel);
        }
    }
    public boolean gameRunning(MessageChannel channel){
        for(RingOfFire r: currentgames){
            if(r.getMessageChannel().equals(channel)){
                return true;
            }
        }
        return false;
    }
}