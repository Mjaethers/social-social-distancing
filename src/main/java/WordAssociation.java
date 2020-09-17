import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Random;

public class WordAssociation extends ListenerAdapter implements Runnable{
    static String[] startingwords = new String[]{"cheese", "cow", "hat", "car", "toilet", "animal", "radio", "phone", "bread", "food", "cat", "toy", "dog", "pet", "house", "door", "truck", "cold"};
    static String lastword;
    static String newword;
    static Random rand = new Random();
    static boolean iscardp10layed = false;
    static WordAssociation card10 = new WordAssociation();
    static Thread timerthread;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(iscardp10layed && !event.getAuthor().isBot() && !event.getMessage().getContentDisplay().equals("draw")){ //ToDo the last and of this if should be removeable
            timerthread.interrupt();
            newword = event.getMessage().getContentDisplay();
            RelatedWord relatedWord = new RelatedWord(lastword);
            if(relatedWord.isRelatedTo(newword) && !RelatedWord.usedwords.contains(newword)){
                Main.messageReceivedEvent.getChannel().sendMessage("related").queue();
                lastword = newword;
                timerthread = new Thread(card10);
                timerthread.start();
            } else {
                Main.messageReceivedEvent.getChannel().sendMessage("not related, " + event.getAuthor().getName() + " drink").queue();
                Player.getPlayerById(event.getAuthor().getName()).drink(Main.gamechannel);
                iscardp10layed = false;
                RelatedWord.usedwords.clear();
                System.out.println("non related word: " + newword);
                timerthread.interrupt();
            }
        }
    }

    public static void minigame(){
        lastword = startingwords[rand.nextInt(startingwords.length)];
        Main.messageReceivedEvent.getChannel().sendMessage("Playing associated words").queue();
        Main.messageReceivedEvent.getChannel().sendMessage(lastword).queue();
        iscardp10layed = true;
        timerthread = new Thread(card10);
        timerthread.start();
    }

    public void run(){
        try{
            Thread.sleep(10000);
            Main.messageReceivedEvent.getChannel().sendMessage("time up").queue();
            iscardp10layed = false;
        } catch(InterruptedException e){
            System.out.println(e);
        }
    }

}
