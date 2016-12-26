import java.util.ArrayList;

public class Table {
	static final int MAXPLAYER = 4;
	private deck deck;
	private Player[] allPlayer;
	private Dealer dealer;
	private int[] pos_betArray=new int[MAXPLAYER];
	
	Table(int nDeck){
		allPlayer=new Player[MAXPLAYER];
		deck= new deck(nDeck);
		
	}
	public void set_player(int pos,Player p){
		allPlayer[pos]=p;
		
	}
	public Player[] get_player(){
		return allPlayer;
	}
	public void set_dealer(Dealer d){
		Dealer dealer=d;
	}
	public Card get_face_up_card_of_dealer(){
		Card DealersOpenCard = dealer.getOneRoundCard().get(1);
		return DealersOpenCard;
	}
	private void ask_each_player_about_bets(){
		int pos=0;
		for(Player players:allPlayer){
			ArrayList<Card> playersCard= new ArrayList<Card>();
			players.say_hello();
			pos_betArray[pos] = players.make_bet();
			pos++;
		}}
	private void distribute_cards_to_dealer_and_players() {
		for(Player players:allPlayer){
			ArrayList<Card> playersCards = new ArrayList<Card>();
			playersCards.add(deck.getOneCard(true));
			playersCards.add(deck.getOneCard(true));
			players.setOneRoundCard(playersCards);}
		ArrayList<Card> dealersCards = new ArrayList<Card>();
		dealersCards.add(deck.getOneCard(false));
		dealersCards.add(deck.getOneCard(true));
		dealer.setOneRoundCard(dealersCards);
		System.out.print("Dealer's face up card is :");
		get_face_up_card_of_dealer().PrintCard();
	}
	private void ask_each_player_about_hits() {
		for (int pos = 0; pos < MAXPLAYER; pos++) {
			System.out.println(allPlayer[pos].get_name() + "'s cards now: ");
			allPlayer[pos].printAllCard();
			while (allPlayer[pos].hit_me(this) && allPlayer[pos].getTotalValue() <= 21) {
				ArrayList<Card> newOneRroundCard = allPlayer[pos].getOneRoundCard();
				newOneRroundCard.add(deck.getOneCard(true));
				System.out.println("Hit! " + allPlayer[pos].get_name() + "'s cards now: ");
				allPlayer[pos].setOneRoundCard(newOneRroundCard);
				allPlayer[pos].printAllCard();
			}
			System.out.println((allPlayer[pos].getTotalValue() > 21 ? "" : "Pass hit!\n") + allPlayer[pos].get_name()
					+ "'s hit is over!");
		}
	}
	private void calculate_chips() {
		int dealersTotalvalue = dealer.getTotalValue()>21?0:dealer.getTotalValue(), pos = 0;
		System.out.print("Dealer's card value is " + dealer.getTotalValue() + " ,Cards:");
		dealer.printAllCard();
		for (Player players : allPlayer) {
			int playersTotalvalue = players.getTotalValue()>21?0:players.getTotalValue();
			System.out.print(players.get_name() + " card value is " + players.getTotalValue());
			if (playersTotalvalue > dealersTotalvalue) {
				System.out.print(",Get " + pos_betArray[pos] + " Chips, the Chips now is: ");
				players.increase_chips(pos_betArray[pos]);
				System.out.println(players.get_current_chips());
			} else if (playersTotalvalue < dealersTotalvalue) {
				System.out.print(", Loss " + pos_betArray[pos] + " Chips, the Chips now is: ");
				players.increase_chips(-pos_betArray[pos]);
				System.out.println(players.get_current_chips());
			} else {
				System.out.println(",chips have no change! The Chips now is: " + players.get_current_chips());
			}
		}
	}
	public int[] get_palyers_bet() {
		
		return pos_betArray;
	}

	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_each_player_about_hits() ;
		calculate_chips();
	}}
	
	
	
	

