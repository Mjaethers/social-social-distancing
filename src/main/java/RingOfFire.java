import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Random;

public class RingOfFire extends ListenerAdapter{
    boolean join;
    boolean inSetup;
    int currentplayerinarray;
    static String lastdrawer;
    Random random;

    public static ArrayList<String> players = new ArrayList<String>();
    public ArrayList<Integer> cardsPlayed = new ArrayList<Integer>();
    static ArrayList<Card> deck = new ArrayList<Card>();
    //static Card[] deck = new Card[52];
    int randomNumber;

    RingOfFire(){
        join = true;
        inSetup = true;
        currentplayerinarray = 0;
        lastdrawer = Main.gamestarter;
        random = new Random();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("currentplayersinarray: " + currentplayerinarray);
        System.out.println("playernumber: " + Player.playernumber);
        System.out.println(join);
        if(join){
            if(event.getMessage().getContentDisplay().equals("join") && !players.contains(event.getAuthor().getName())){
                System.out.println(event.getAuthor().getName() + " has joined");
                players.add(event.getAuthor().getName());
                new Player(event.getAuthor().getName());
            }
        }
        if(inSetup){
            if(event.getMessage().getContentDisplay().equals("start")){
                Main.gamechannel.sendMessage("Game starting...").queue();
                join = false;
                inSetup = false;
                System.out.println("players: " + Player.getPlayersString());
            }
        }
        if(event.getMessage().getContentDisplay().equals("draw") && !inSetup && event.getAuthor().getName().equals(Player.players.get(currentplayerinarray%Player.playernumber).getPlayerId())){
            lastdrawer = event.getAuthor().getName();
            randomNumber = random.nextInt(520);
            while(cardsPlayed.contains(randomNumber)){
                randomNumber = random.nextInt(520);
            }
            System.out.println(currentplayerinarray);
            Main.gamechannel.sendMessage(deck.get(randomNumber).printCard()).queue();
            deck.get(randomNumber).play();
            cardsPlayed.add(randomNumber);
            System.out.println("cards played: " + cardsPlayed.toString());
            currentplayerinarray++;
            callNextPlayer();
        }
    }

    public void callNextPlayer(){
        Main.gamechannel.sendMessage(Player.players.get(currentplayerinarray%Player.players.size()).getPlayerId() + " is next...").queue();
    }

    public void setup(){
        createDeck(520);
        Main.gamechannel.sendMessage("Type 'join' to participate").queue();
        inSetup = true;
        join = true;
    }
    public static void createDeck(int DeckSize){
        for(int i = 0; i < DeckSize; i++){
            deck.add(new Card(i%13));
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Card c : deck){
            stringBuilder.append(c.cardId);
            stringBuilder.append(",");
        }
        System.out.println("cards: " + stringBuilder.toString());
    }
    /*public Card[] shuffleCards(Card[] cards){
        for(Card c : cards){

        }
    }*/
    /*public static ArrayList<String> players = new ArrayList();
    public ArrayList<Integer> playedCards = new ArrayList();
    public static boolean timePassed;
    int cardNumber;
    private static boolean drawCard;
    static boolean isjoin;
    static Thread timerthread;
    static RingOfFire ringOfFire = new RingOfFire();
    static String lastdrawer;

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println(drawCard);
        if(drawCard && !event.getAuthor().isBot() && event.getMessage().getContentDisplay().equals("draw")){
            lastdrawer = event.getAuthor().getName();
            nextCard(lastdrawer);
            drawCard = true;
        }
        if(isjoin && event.getMessage().getContentDisplay().equals("join") && !players.contains(event.getAuthor().getName())){
            players.add(event.getAuthor().getName());
            System.out.println(players.toString());
        }
    }

    public void run(){
        try{
            Thread.sleep(10000);
            Main.messageReceivedEvent.getChannel().sendMessage("starting game...").queue();
            isjoin = false;
        } catch(InterruptedException e){
            System.out.println(e);
        }
    }

    static Card[] deck = new Card[52];
    public static void createDeck(){
        for(int i = 0; i < deck.length; i++){
            deck[i] = new Card(i%13);
        }
        System.out.println("deck created");
    }
    public void nextCard(String currentPlayer){
        Random random = new Random();
        cardNumber = random.nextInt(52);
        while(playedCards.contains(cardNumber)){
            cardNumber = random.nextInt(52);
        }
        playedCards.add(cardNumber);
        deck[cardNumber].printCard();
        deck[cardNumber].play(currentPlayer);
    }
    public static void play(){
        setup();
        createDeck();
        drawCard = true;
    }

    public static void setup(){
        System.out.println("Setup started");
        Main.gamechannel.sendMessage("Type 'join' to participate (you have 30 seconds)").queue();
        isjoin = true;
        timerthread = new Thread(ringOfFire);
        timerthread.start();
    }*/
}
