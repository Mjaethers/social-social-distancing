import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Random;

public class Rhyme extends ListenerAdapter implements Runnable {
    static String[] startingwords = new String[]{"cheese", "cow", "hat", "car", "toilet", "animal", "radio", "phone", "bread", "food", "cat", "toy", "dog", "pet", "house", "door", "truck", "cold", "orange", "canada", "blond", "tooth", "funny", "shark", "moustache", "crisp", "air", "wood", "fire", "snow", "watch", "gold", "shoe", "dress", "crab", "suit", "eye", "leather", "ring", "chain", "hand"};
    static String lastword;
    static String newword;
    static Random rand = new Random();
    static boolean iscardplayed = false;
    static Rhyme card9 = new Rhyme();
    static Thread timerthread;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(iscardplayed && !event.getAuthor().isBot() && !event.getMessage().getContentDisplay().equals("draw")){ //ToDo the last and of this if should be removeable
            timerthread.interrupt();
            newword = event.getMessage().getContentDisplay();
            RelatedWord relatedWord = new RelatedWord(lastword);
            if(relatedWord.isRhymeWith(newword) && !RelatedWord.usedwords.contains(newword)){
                Main.messageReceivedEvent.getChannel().sendMessage("rhymes").queue();
                lastword = newword;
                timerthread = new Thread(card9);
                timerthread.start();
            } else {
                Main.messageReceivedEvent.getChannel().sendMessage("doesn't rhyme, " + event.getAuthor().getName() + " drink").queue();
                Player.getPlayerById(event.getAuthor().getName()).drink(Main.gamechannel);
                iscardplayed = false;
                RelatedWord.usedwords.clear();
                System.out.println("non rhyming word: " + newword);
                timerthread.interrupt();
            }
        }
    }

    public static void minigame(){
        lastword = startingwords[rand.nextInt(startingwords.length)];
        Main.messageReceivedEvent.getChannel().sendMessage("Playing rhyme").queue();
        Main.messageReceivedEvent.getChannel().sendMessage(lastword).queue();
        iscardplayed = true;
        timerthread = new Thread(card9);
        timerthread.start();
    }

    public void run(){
        try{
            Thread.sleep(10000);
            Main.messageReceivedEvent.getChannel().sendMessage("time up").queue();
            iscardplayed = false;
        } catch(InterruptedException e){
            System.out.println(e);
        }
    }
}
