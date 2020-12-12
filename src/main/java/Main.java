import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class Main extends ListenerAdapter {
    HashMap<MessageChannel, RingOfFire> RunningGames = new HashMap<>();

    public static void main(String[] args) throws LoginException{
        /*JDABuilder builder = JDABuilder.createDefault(args[0]);
        builder.setActivity(Activity.playing("Ring of Fire"));
        builder.build();*/

        JDA jda = JDABuilder.createDefault(System.getenv("DISCORD_TOKEN"))
                .addEventListeners(new Main(), new ThumbMaster())
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getMessage().getContentDisplay().toLowerCase().equals("!play ring of fire")){
            RunningGames.put(event.getChannel(), new RingOfFire(event));
        }
        else if (RunningGames.containsKey(event.getChannel())){
            RunningGames.get(event.getChannel()).onMessageReceived(event);
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
