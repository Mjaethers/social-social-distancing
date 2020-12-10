import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class RingOfFire{
    boolean setup = true;
    public boolean heaven = false;
    boolean listeningchoose = false;
    public User mater = null;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Player> unrespondedplayers = new ArrayList<>();
    MessageChannel channel;
    User chooser;
    int playerturn;

    public RingOfFire(MessageReceivedEvent event){
        System.out.println("Setup entered");
        event.getChannel().sendMessage("Type 'join' to join. Once all players have joined type 'start'. ").queue();
        event.getChannel().sendMessage("View the rules by typing !rules").queue();
        playerturn = 0;
    }

    public void onMessageReceived(MessageReceivedEvent event){
        if(!event.getAuthor().isBot()){
            channel = event.getChannel();
            if(setup){
                Player p;
                if(event.getMessage().getContentDisplay().toLowerCase().equals("join") && !players.contains(p = new Player(event.getAuthor(), this))){
                    players.add(p);
                    System.out.println(event.getAuthor().getName() + " has joined the game.");
                }
                if(event.getMessage().getContentDisplay().toLowerCase().contains("start") && !event.getAuthor().isBot()){
                    System.out.println("game is starting...");
                    channel.sendMessage("It is is " + players.get(playerturn%(players.size())).getName() + " turn to draw.").queue();
                    unrespondedplayers.addAll(players);
                    setup = false;
                }
            }
            if(heaven){
                heaven(event);
            }
            if(listeningchoose){
                choose(event, chooser);
                listeningchoose = false;
            }
            if(mater != null){
                mate(event);
            }
            else{
                for(Player p: players){
                    System.out.println(p.toString());
                    System.out.println(players.size());
                }
                if(event.getMessage().getContentDisplay().toLowerCase().contains("draw") && event.getAuthor().equals(players.get(playerturn%(players.size())).getUserFromPlayer())){
                    Random r = new Random();
                    switch(r.nextInt(12)){
                        case 0:
                            channel.sendMessage("1 - Waterfall").queue();
                            waterfall(channel);
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 1:
                            channel.sendMessage("2 - Choose").queue();
                            channel.sendMessage("Choose: " + event.getMessage().getAuthor().getName() + " mention someone who has to drink").queue();
                            chooser = event.getAuthor();
                            listeningchoose = true;
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 2:
                            channel.sendMessage("3 - Me").queue();
                            channel.sendMessage("Me: " + Player.getPlayerFromUser(event.getMessage().getAuthor(), this).getDrinkText()).queue();
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 3:
                            channel.sendMessage("4 - Whore").queue();
                            channel.sendMessage("Whore: all the girls drink").queue();
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 4:
                            channel.sendMessage("5 - Thumb master").queue();
                            channel.sendMessage("Thumbmaster: " + event.getAuthor().getName() + ", you are the thumb master").queue();
                            ThumbMaster thumbMaster = new ThumbMaster();
                            thumbMaster.setup(channel, event.getAuthor(), Player.getUsersInLobby(this));
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 5:
                            channel.sendMessage("6 - Dicks").queue();
                            channel.sendMessage("Dicks: all the boys drink").queue();
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 6:
                            channel.sendMessage("7 - Heaven").queue();
                            channel.sendMessage("Heaven: everyone type heaven as fast as they can").queue();
                            heaven = true;
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 7:
                            channel.sendMessage("8 - Mate").queue();
                            channel.sendMessage("Mate: " + event.getAuthor().getName() + " choose a mate by mentioning them").queue();
                            mater = event.getAuthor();
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 8:
                            //9 - rhyme";
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 9:
                            //10 - Related Words";
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 10:
                            channel.sendMessage("Jack - Make a rule").queue();
                            channel.sendMessage("Make a rule: " + event.getMessage().getAuthor().getName() + " make a new rule").queue();
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();;
                            break;
                        case 11:
                            channel.sendMessage("Queen - Questionmaster").queue();
                            channel.sendMessage("Questionmaster: " + event.getMessage().getAuthor().getName() + " you are now the questionmaster").queue();
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                        case 12:
                            channel.sendMessage("King - Everyone drink").queue();
                            channel.sendMessage("It is is " + players.get(++playerturn%(players.size())).getName() + " turn to draw.").queue();
                            break;
                    }
                }
            }
        }
    }
    public static void waterfall(MessageChannel channel){
        Random rand = new Random();
        channel.sendMessage("Waterfall: no one stop drinking until I stop. Start when I do (in 5s)").queue();
        try{
            sleep(5000);
        }
        catch(InterruptedException e){
            System.out.println(e.toString());
        }
        channel.sendMessage("Start!").queue();
        for(int i =0; i<rand.nextInt(45); i++){
            try{
                sleep(1000);
                channel.sendMessage("Drinking...").queue();
            }
            catch(InterruptedException e){
                System.out.println(e.toString());
            }
        }
        channel.sendMessage("Stopped drinking").queue();
    }

    public void heaven(MessageReceivedEvent event){
        if(unrespondedplayers.size() > 1 && unrespondedplayers.contains(Player.getPlayerFromUser(event.getAuthor(), this))){
            unrespondedplayers.remove(Player.getPlayerFromUser(event.getAuthor(), this));
        }
        else{
            heaven = false;
            channel.sendMessage(unrespondedplayers.get(0).getUserFromPlayer().getName()+ " was the last to respond").queue();
            channel.sendMessage(unrespondedplayers.get(0).getDrinkText()).queue();
            unrespondedplayers.clear();
            unrespondedplayers.addAll(players);
        }
    }

    public void mate(MessageReceivedEvent event){
        if(event.getAuthor().equals(mater) && players.contains(Player.getPlayerFromUser(event.getMessage().getMentionedUsers().get(0), this))){
            Player.getPlayerFromUser(mater, this).addMate(Player.getPlayerFromUser(event.getMessage().getMentionedUsers().get(0), this));
            event.getChannel().sendMessage(mater.getName() + ", you are now mates with: " + Player.getPlayerFromUser(mater, this).getMatesString()).queue();
            mater = null;
        }
    }
    public void choose(MessageReceivedEvent event, User chooser){
        if(event.getMessage().getAuthor().equals(chooser) && !event.getMessage().getMentionedMembers().isEmpty()){

            event.getChannel().sendMessage(Player.getPlayerFromUser(event.getMessage().getMentionedUsers().get(0), this).getDrinkText()).queue();
        }
    }
}