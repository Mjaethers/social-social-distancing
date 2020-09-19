import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class RingOfFire extends ListenerAdapter {
    boolean setup = true;
    public boolean heaven = false;
    public User mater = null;
    ArrayList<User> players = new ArrayList<>();
    ArrayList unrespondedplayers = new ArrayList<>();
    MessageChannel channel;

    public RingOfFire(MessageReceivedEvent event){
        System.out.println("Setup entered");
        event.getChannel().sendMessage("Type 'join' to join. Once all players have joined type 'start'. ").queue();
    }

    public void onEventReceived(MessageReceivedEvent event){
        channel = event.getChannel();
        if(setup){
            if(event.getMessage().getContentDisplay().toLowerCase().equals("join") && !Player.isPlayer(event.getAuthor())){
                players.add(event.getAuthor());
                new Player(event.getAuthor());
                System.out.println(event.getAuthor().getName() + " has joined the game.");
            }
            if(event.getMessage().getContentDisplay().toLowerCase().contains("start") && !event.getAuthor().isBot()){
                System.out.println("game is starting...");
                unrespondedplayers.addAll(players);
                setup = false;
            }
        }
        if(heaven){
            heaven(event);
        }
        if(mater != null){
            mate(event);
        }
        else{
            if(event.getMessage().getContentDisplay().toLowerCase().contains("draw") && !event.getAuthor().isBot()){
                Random r = new Random();
                int number = 7;//r.nextInt(12);
                System.out.println(number);
                switch(number){
                    case 0:
                        channel.sendMessage("1 - Waterfall").queue();
                        waterfall(channel);
                        break;
                    case 1:
                        channel.sendMessage("2 - Choose").queue();
                        channel.sendMessage("Choose: " + event.getMessage().getAuthor().getName() + " mention someone who has to drink").queue();
                        Choose choose = new Choose();
                        choose.setup(event.getMessage().getAuthor(), channel);
                        break;
                    case 2:
                        channel.sendMessage("3 - Me").queue();
                        channel.sendMessage("Me: " + event.getMessage().getAuthor().getName() + " drink").queue();
                        break;
                    case 3:
                        channel.sendMessage("4 - Whore").queue();
                        channel.sendMessage("Whore: all the girls drink").queue();
                        break;
                    case 4:
                        channel.sendMessage("5 - Thumb master").queue();
                        channel.sendMessage("Thumbmaster: " + event.getAuthor().getName() + ", you are the thumb master").queue();
                        ThumbMaster thumbMaster = new ThumbMaster();
                        thumbMaster.setup(channel, event.getAuthor(), players);
                        break;
                    case 5:
                        channel.sendMessage("6 - Dicks").queue();
                        channel.sendMessage("Dicks: all the boys drink").queue();
                        break;
                    case 6:
                        channel.sendMessage("7 - Heaven").queue();
                        channel.sendMessage("Heaven: everyone type heaven as fast as they can").queue();
                        heaven = true;
                        break;
                    case 7:
                        channel.sendMessage("8 - Mate").queue();
                        channel.sendMessage("Mate: " + event.getAuthor().getName() + " choose a mate by mentioning them").queue();
                        mater = event.getAuthor();
                        break;
                    case 8:
                        //9 - rhyme";
                        break;
                    case 9:
                        //10 - Related Words";
                        break;
                    case 10:
                        channel.sendMessage("Jack - Make a rule").queue();
                        channel.sendMessage("Make a rule: " + event.getMessage().getAuthor().getName() + "make a new rule").queue();
                        break;
                    case 11:
                        channel.sendMessage("Queen - Questionmaster").queue();
                        channel.sendMessage("Questionmaster: " + event.getMessage().getAuthor().getName() + " you are now the questionmaster").queue();
                        break;
                    case 12:
                        channel.sendMessage("King - Everyone drink").queue();
                        break;
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

    public void heaven(MessageReceivedEvent event){
        if(unrespondedplayers.size() > 1 && unrespondedplayers.contains(event.getAuthor())){
            unrespondedplayers.remove(event.getAuthor());
        }
        else{
            heaven = false;
            channel.sendMessage(unrespondedplayers.get(0).toString()+ " was the last to respond").queue();
            channel.sendMessage(unrespondedplayers.get(0).toString() + " drink!").queue();
        }
    }

    public void mate(MessageReceivedEvent event){
        if(event.getAuthor().equals(mater) && !event.getMessage().getMentionedMembers().isEmpty()){
            event.getChannel().sendMessage(mater.getName() + ", you are now mates with: " + event.getMessage().getMentionedMembers().get(0).getEffectiveName()).queue();
            event.getChannel().sendMessage(Player.getPlayerByID(mater).getDrinkText()).queue();
        }

    }
}