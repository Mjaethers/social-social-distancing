import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static String gamestarter;
    public static MessageChannel gamechannel;

    public static MessageReceivedEvent messageReceivedEvent; //this is new
    public static JDABuilder builder;
    public static void main(String[] args) throws LoginException {
        builder = new JDABuilder(AccountType.BOT);
        String token = "Njk3NTA2NDYxNjMwMjAxOTI4.Xo4RkA.d67u55JBO72Jc_QWDHmPCoxIGuc";
        builder.setToken(token);
        builder.addEventListener(new Rhyme());
        builder.addEventListener(new Main());
        builder.addEventListener(new ThumbMaster());
        builder.addEventListener(new Heaven());
        builder.addEventListener(new Choose());
        builder.addEventListener(new Waterfall());
        builder.addEventListener(new WordAssociation());
        builder.addEventListener(new Mate());
        builder.addEventListener(new RingOfFire());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        messageReceivedEvent = event; //this is new

        String message = event.getMessage().getContentDisplay();

        if(message.equals("!play ring of fire")){
            gamestarter = event.getAuthor().getName();
            gamechannel = event.getChannel();
            System.out.println("ring of fire");
            RingOfFire ringOfFire = new RingOfFire();
            ringOfFire.setup();
        }
        if(message.equals("!play mate")){
            RingOfFire.lastdrawer = event.getAuthor().getName();
            gamechannel = event.getChannel();
            gamestarter = event.getAuthor().getName();
            new Player(gamestarter);
            Mate.minigame();
        }
        if(message.equals("!drink")){
            gamechannel = event.getChannel();
            gamestarter = event.getAuthor().getName();
            Player player = Player.getPlayerById(gamestarter);
            player.drink(gamechannel);
        }
        event.getGuild().getController();
    }
}
