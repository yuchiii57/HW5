import java.util.ArrayList;

public class Player {
	private String name="";
	private int chips;
	private int bet=0;
	private ArrayList<Card> oneRoundCard;

	public  Player(String string, int chips) {
		name =string;
		this.chips=chips;
		
		
	}
	public int make_bet() {
    if(bet<=chips){
    	bet=1;
    }
    else{bet=0;}
		return bet;
	}
	
	
	
	public String get_name() {
		return name;
	}

	public void setOneRoundCard(ArrayList<Card> cards)
	{oneRoundCard=cards;
	}
	public boolean hit_me() 
	{
		boolean i=false;
		if(getTotalValue()<=16)
		{
			i=true;
		}
		return i;
	}
	public int getTotalValue() 
	{
		int sum=0;
		for(int i=0;i< oneRoundCard.size();i++){
			
			if(oneRoundCard.get(i).getRank()>10)
				sum+=10;
			else if(oneRoundCard.get(i).getRank()==1){
				if(sum+10<21){sum+=11;}
				if(sum+10>21){sum+=1;}
			}
			else
			 sum+=oneRoundCard.get(i).getRank();
		}
		return sum;
	}
	public int get_current_chips() {
		
		return chips;
	}
	
	public void increase_chips (int diff) {
		chips+=diff;
	}
	public void say_hello() {
		System.out.println("Hello, I am " + name + "."); 
		System.out.println("I have " + chips + " chips.");
	}
	public boolean hit_me(Table table) {
		// TODO Auto-generated method stub
		return false;
	}


}
