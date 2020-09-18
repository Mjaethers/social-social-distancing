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
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

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