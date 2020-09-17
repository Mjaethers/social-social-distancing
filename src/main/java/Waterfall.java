import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Waterfall extends ListenerAdapter {
    static Random rand = new Random();

    public static void minigame(){
        Main.gamechannel.sendMessage("Waterfall: no one stop drinking until I stop").queue();
        try{sleep(5000);} catch(Exception e){System.out.println(e);}
        for(int i =0; i<rand.nextInt(45); i++){
            try{
                sleep(1000);
                Main.messageReceivedEvent.getChannel().sendMessage("Drinking...").queue();
            } catch(InterruptedException e){
                System.out.println(e);
            }
        }
        Main.messageReceivedEvent.getChannel().sendMessage("Stopped drinking").queue();
    }
}
