import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class RingOfFire extends ListenerAdapter {
    boolean setup = true;
    ArrayList<User> players = new ArrayList<>();
    boolean running = false;

    public void start(MessageReceivedEvent event){
        System.out.println("Setup entered");
        event.getChannel().sendMessage("Type 'join' to join. Once all players have joined type 'start'. ").queue();
        running = true;
    }

    public void onEventReceived(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        System.out.println("hello");
        if(running){
            //All code must go here
            if(setup){
                if(event.getMessage().getContentDisplay().toLowerCase().equals("join") && !players.contains(event.getAuthor())){
                    players.add(event.getAuthor());
                    System.out.println(event.getAuthor().toString() + "has joined the game.");
                }
                if(event.getMessage().getContentDisplay().toLowerCase().contains("start")){
                    System.out.println("game is starting...");
                    setup = false;
                }
            }
            else{
                if(event.getMessage().getContentDisplay().toLowerCase().contains("draw")){
                    //Play Game
                    Random r = new Random();
                    switch(r.nextInt(12)){
                        case 0:
                            channel.sendMessage("1 - Waterfall").queue();
                            waterfall(channel);
                        case 1:
                            channel.sendMessage("2 - Choose").queue();
                            channel.sendMessage("Choose: " + event.getMessage().getAuthor().toString() + " mention someone who has to drink").queue();
                            Choose choose = new Choose();
                            choose.setup(event.getMessage().getAuthor(), channel);
                        case 2:
                            channel.sendMessage("3 - Me").queue();
                            channel.sendMessage("Me: " + event.getMessage().getAuthor().toString() + " drink").queue();
                        case 3:
                            channel.sendMessage("4 - Whore").queue();
                            channel.sendMessage("Whore: all the girls drink").queue();
                        case 4:
                            channel.sendMessage("5 - Thumb master").queue();
                            channel.sendMessage("Thumbmaster: " + event.getAuthor().toString() + ", you are the thumb master").queue();
                            ThumbMaster thumbMaster = new ThumbMaster();
                            thumbMaster.setup(channel, event.getAuthor(), players);
                        case 5:
                            channel.sendMessage("6 - Dicks").queue();
                            channel.sendMessage("Dicks: all the boys drink").queue();
                        case 6:
                            channel.sendMessage("7 - Heaven").queue();
                            channel.sendMessage("Heaven: everyone type heaven as fast as they can").queue();
                            Heaven heaven = new Heaven();
                            heaven.setup(channel, players);
                        case 7:
                            channel.sendMessage("8 - Mate").queue();
                            channel.sendMessage(event.getAuthor().toString()+ ", choose a mate").queue();
                        case 8:
                            //9 - rhyme";
                        case 9:
                            //10 - Related Words";
                        case 10:
                            channel.sendMessage("Jack - Make a rule").queue();
                            channel.sendMessage("Make a rule: " + event.getMessage().getAuthor().toString() + "make a new rule").queue();
                        case 11:
                            channel.sendMessage("Queen - Questionmaster").queue();
                            channel.sendMessage("Questionmaster: " + event.getMessage().getAuthor().toString() + " you are now the questionmaster").queue();
                        case 12:
                            channel.sendMessage("King - Everyone drink").queue();
                    }
                }
            }
        }
    }
    public static void waterfall(MessageChannel channel){
        Random rand = new Random();
        channel.sendMessage("Waterfall: no one stop drinking until I stop").queue();
        try{
            sleep(5000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        for(int i =0; i<rand.nextInt(45); i++){
            try{
                sleep(1000);
                channel.sendMessage("Drinking...").queue();
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        }
        channel.sendMessage("Stopped drinking").queue();
    }
}