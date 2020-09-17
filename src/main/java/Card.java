import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
/*import sun.plugin2.message.Message;

import javax.xml.bind.Marshaller;
import java.awt.*;*/

public class Card extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

    }

    int cardId;
    public Card(int id){
        cardId = id;
    }
    public int getId(){
        return cardId;
    }
    public String printCard(){
        switch(cardId){
            case 0:
                return "1 - Waterfall";
            case 1:
                return "2 - Choose";
            case 2:
                return "3 - Me";
            case 3:
                return "4 - Whore";
            case 4:
                return "5 - Thumb master";
            case 5:
                return "6 - Dicks";
            case 6:
                return "7 - Heaven";
            case 7:
                return "8 - mate";
            case 8:
                return "9 - rhyme";
            case 9:
                return "10 - Related Words";
            case 10:
                return "Jack - Make a rule";
            case 11:
                return "Queen - Questionmaster";
            case 12:
                return "King - Everyone drink";
            default:
                return "Error";
        }
    }
    public void play(){
        switch(cardId){
            case 0:
                Waterfall.minigame();
                break;
            case 1:
                Choose.minigame();
                break;
            case 2:
                Main.gamechannel.sendMessage("Me: " + RingOfFire.lastdrawer + " drink").queue();
                break;
            case 3:
                Main.gamechannel.sendMessage("Whores: all girls drink").queue();
                break;
            case 4:
                ThumbMaster.minigame();
                break;
            case 5:
                Main.gamechannel.sendMessage("Dicks: all the boys drink").queue();
                break;
            case 6:
                Heaven.minigame();
                break;
            case 7:
                Mate.minigame();
                break;
            case 8:
                System.out.println("card9 started");
                Rhyme.minigame();
                break;
            case 9:
                System.out.println("card10 started");
                WordAssociation.minigame();
                break;
            //Main.messageReceivedEvent.getMember().getVoiceState().getAudioChannel(); // I have no idea why this was useful but there you go, it finds the voice channel that the user whos started the game is in
            case 10:
                Main.gamechannel.sendMessage("Make a rule: " + RingOfFire.lastdrawer + " make a new rule").queue();
                break;
            case 11:
                Main.gamechannel.sendMessage("Questionmaster: " + RingOfFire.lastdrawer + " you are the questionmaster").queue();
                break;
            case 12:
                Main.gamechannel.sendMessage("king! everyone drink!").queue();
                break;
        }
    }
}
