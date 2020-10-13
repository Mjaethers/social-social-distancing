import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class Main extends ListenerAdapter{
    public static JDABuilder builder;
    HashMap<MessageChannel, RingOfFire> RunningGames = new HashMap<>();

    public static void main(String[] args) throws LoginException{
        builder = new JDABuilder(AccountType.BOT);
        builder.setToken("Njk3NTA2NDYxNjMwMjAxOTI4.Xo4RkA.d67u55JBO72Jc_QWDHmPCoxIGuc");

        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getMessage().getContentDisplay().toLowerCase().equals("!play ring of fire")){
            RunningGames.put(event.getChannel(), new RingOfFire(event));
        }
        else if (RunningGames.containsKey(event.getChannel())){
            RunningGames.get(event.getChannel()).onEventReceived(event);
        }
        if(event.getMessage().getContentDisplay().toLowerCase().equals("!rules")){
            event.getChannel().sendMessage(rules()).queue();
        }
    }
    public String rules(){
        String ruleset = "ToDo";
        return ruleset;
    }

}
