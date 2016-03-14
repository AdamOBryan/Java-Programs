

//ADAM OBRYAN 
// adamobryan.com   github.com/aob412

/*PROGRAM TO GET ORDER FROM SHOP, CALCULATE TOTALS, AND 
TAKE PAYMENT WITH OR WITHOUT A REWARDS CARD */

import java.util.Scanner;

public class OBryanAsgn2 {
	public static Scanner sc = new Scanner(System.in);
	public static boolean customerHasPieCard = false;
	
	///// GLOBAL VARIABLES - EASY TO CHANGE PRICES IF NEEDED TO
	//// REGULAR PRICES
	public static double chzPizza = 10.00;
	public static double pepPizza = 12.00;
	public static double slicePie = 2.00;
	public static double wholePie = 10.00;
	public static double charm = 50.00;
	//// MEMBER PRICES
	public static double chzPizzaCard = 10.00;
	public static double pepPizzaCard = 10.00;
	public static double slicePieCard = 1.75;
	public static double wholePieCard = 8.00;
	public static double charmCard = 45.00;
	//// TO KEEP TRACK OF QUANTITY
	public static int chzPizzaQ = 0;
	public static int pepPizzaQ = 0;
	public static int incomingSlicePieQ = 0;
	public static int incomingCopy1;
	public static int incomingCopy2;
	public static int slicePieQ = (incomingSlicePieQ % 6);
	public static int wholePieQ = (incomingSlicePieQ / 6);
	public static int charmQ = 0;
	//// TO KEEP TRACK OF PRICE
	public static double chzPizzaPrice = 0;
	public static double pepPizzaPrice = 0;
	public static double slicePiePrice= 0;
	public static double wholePiePrice = 0;
	public static double charmPrice = 0;
	//// TALLY PRICES
	public static double originalPrice = (chzPizzaQ *chzPizza) + (pepPizzaQ * pepPizza) + (slicePieQ * slicePie) 
													+ (wholePieQ *wholePie) + (charmQ * charm);
	public static double subtotal = (chzPizzaQ *chzPizzaPrice) + (pepPizzaQ * pepPizzaPrice) + (slicePieQ * slicePiePrice) 
												+ (wholePieQ *wholePiePrice) + (charmQ * charmPrice);
	public static double discount = originalPrice - subtotal;
	public static double tax = .07 * subtotal;
	public static double total = subtotal  * (1 + tax);
	//////////////////////////// END GLOBAL VARIABLES ////////////////////////////////////

	public static void main(String[] args) {
	//MAIN FUNCTION
		 chzPizzaQ = 0;
		 pepPizzaQ = 0;
		 incomingSlicePieQ = 0;
		 charmQ = 0;		 
		 greeting();
		 havePieCard();
		//// TO KEEP TRACK OF PRICE
			if (customerHasPieCard) {
					chzPizzaPrice = chzPizzaCard;
					pepPizzaPrice = pepPizzaCard;
					slicePiePrice= slicePieCard;
					wholePiePrice = wholePieCard;
					charmPrice = charmCard;
					cardMenu();
					getOrder();
					main(args);
				} 
			else {
					chzPizzaPrice = chzPizza;
					pepPizzaPrice = pepPizza;
					slicePiePrice= slicePie;
					wholePiePrice = wholePie;
					charmPrice = charm;
					regMenu();
					getOrder();
					main(args);
				}
		}

	public static void greeting(){
	////GREETING FUNCTION
		int answer;
		System.out.println("\tAs You Can See, I Don’t Have Eyes!");
		System.out.println("Does There Happen to be Another Customer in Line?");
		System.out.println("       [Please type 1 FOR YES or 2 FOR NO]");
		answer = sc.nextInt();
			switch (answer){
					case 1:
							break;
					case 2:
						System.out.println("Welll, I guess I'm gonna just split then...");
							System.exit(0);	
							break;
					default:
						greeting();
						break;
					}
		} 
	
	public static void havePieCard(){
	////CHECK FOR CARD MEMBER FUNCTION
		int answer;
		System.out.println("\tWelcome to Pies, Pies, and Pis!!");
		System.out.println("Do You Happen to Have One of Our Lovely Pie Cards?");
		System.out.println("      [Please type 1 FOR YES or 2 FOR NO]");
		answer = sc.nextInt();	
			switch (answer){
				case 1:
					customerHasPieCard = true;
						break;
				case 2:
					customerHasPieCard = false;
						break;
				default:
					System.out.println("I only understand with 1 or 2!");
					havePieCard();
					break;
				}			
		} 
	
	public static void regMenu(){
	//// REGULAR MENU
		System.out.println("");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————");
		System.out.println("\t    Pies, Pies, and Pis Menu");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————");
		System.out.println("\t\t Whole Pizzas");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————");
		System.out.printf("\t %-16s  - $%4.2f \n", "#1) Plain Cheese", chzPizza );
		System.out.printf("\t %-16s  - $%4.2f \n", "#2) Pepperoni", pepPizza );
		System.out.println("");
		System.out.println("\t\t World famous Fruit Pies");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————");
		System.out.printf("\t %-16s  - $%4.2f \n", "#3) By the Slice", slicePie );
		System.out.printf("\t %-16s  - $%4.2f \n", "    A Whole Pie", wholePie );
		System.out.println("\t  **(6 slices!)**");
		System.out.println("");
		System.out.println("\t\t 24k Gold Pi Charms ");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————");
		System.out.printf("\t %-16s  - $%4.2f \n", "#4) By the Piece", charm );
		System.out.println("");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————");;
		System.out.println("———————————————————"
				+ "———————————————————" + "———————————");
		System.out.println("");
	}	
	
	public static void cardMenu(){
	///// CARDMEMBER MENU
		System.out.println("");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————————————");
		System.out.println("  \t!!Pies, Pies, and Pis Card Member Menu!!");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————————————");
		System.out.println("   * AS A PIE CARD MEMBER, YOU RECEIVE THESE  PERKS! *");
		System.out.println("\t* PLUS 10% OFF ALL ORDERS OVER $100!! *");
		System.out.println("");
		System.out.println("\t\t      Whole Pizzas");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————————————");
		System.out.printf("\t %-16s  - $%4.2f \n", "#1) Plain Cheese", chzPizzaCard );
		System.out.printf("\t %-16s  - $%4.2f  Usually $%.2f! \n", "#2) Pepperoni", pepPizzaCard, pepPizza );
		System.out.println("");
		System.out.println("\t\t World famous Fruit Pies");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————————————");
		System.out.printf("\t %-16s  - $%5.2f  Usually $%5.2f! \n", "#3) By the Slice", slicePieCard, slicePie );
		System.out.printf("\t %-16s  - $%5.2f  Usually $%.2f! \n", "    A Whole Pie", wholePieCard, wholePie );
		System.out.println("\t  **(6 slices!)**");
		System.out.println("");
		System.out.println("\t\t 24k Gold Pi Charms ");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————————————");
		System.out.printf("\t %-16s  - $%4.2f  Usually $%.2f! \n", "#4) By the Piece", charmCard, charm );
		System.out.println("");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————————————");
		System.out.println("———————————————————" 
				+ "———————————————————" + "———————————————————");
		System.out.println("");
	}
	
	public static void getOrder(){
	//////GET ORDER FUNCTION
		System.out.print("What Would You Like Today?\n[order by item number please!]:");
		int number = sc.nextInt();
			switch (number){
					case 1:
						System.out.print("How many Cheese Pizzas would you like?");
						chzPizzaQ = sc.nextInt();
						break;
					case 2:
						System.out.print("How many Pepperoni Pizzas would you like?");
						pepPizzaQ = sc.nextInt(); 
						break;
					case 3:
						System.out.print("How many Cherry Pie Slices would you like?");
						incomingSlicePieQ = sc.nextInt(); 
						break;
					case 4:
						System.out.print("How many 24k Gold Pi Charms would you like?");
						charmQ = sc.nextInt();
						break;
					default:
						System.out.println("I’m sorry! At our fancy shop, we only respond if you order by item number!");
						getOrder();
						break;
				}
		System.out.println("——————————————————");
		System.out.println("[1]ADD TO ORDER   |"); //GO BACK TO MENU SCREEN
		System.out.println("[2]VIEW ORDER  \t  |"); //GO TO CHECKOUT
		System.out.print( "——————————————————   ");
		number = sc.nextInt();
			switch (number)
				{
					case 1:
						getOrder();
						break;
					case 2:
						orderScreen();
						break;
					default:
						System.out.println("I only understand in 1's or 2's! ");  
						getOrder();
						break;
				}
		}
	
	public static void orderScreen(){	
	///// CURRENT ORDER SCREEN
		total = 0.00;
		incomingCopy1 = incomingSlicePieQ;
		incomingCopy2 = incomingSlicePieQ;
		slicePieQ= (incomingCopy1 % 6);
		wholePieQ = (incomingCopy2 / 6);
		originalPrice = (chzPizzaQ *chzPizza) + (pepPizzaQ * pepPizza) + (slicePieQ * slicePie) 
				+ (wholePieQ *wholePie) + (charmQ * charm);
		subtotal = (chzPizzaQ *chzPizzaPrice) + (pepPizzaQ * pepPizzaPrice) + (slicePieQ * slicePiePrice) 
													+ (wholePieQ *wholePiePrice) + (charmQ * charmPrice);
		discount = originalPrice - subtotal;
		tax = .07 * subtotal;
		total = subtotal + tax;
		System.out.println("");
		System.out.println("");
		System.out.println(" ————————————————————");
		System.out.println("| YOUR CURRENT ORDER |");
		System.out.println("———————————————————" + "———————————————————" + "———————————————————");
		System.out.println("\t Items \t\t   Quantity \t   Cost");
		System.out.println("———————————————————" + "———————————————————" + "———————————————————");
		System.out.printf("%20s \t %6d \t $%7.2f \n",  "Plain Cheese Pizza -",chzPizzaQ, (chzPizzaQ * chzPizzaPrice) );
		System.out.printf("%20s \t %6d \t $%7.2f \n", "Pepperoni Pizza -", pepPizzaQ, ( pepPizzaQ * pepPizzaPrice) );
		System.out.printf("%20s \t %6d \t $%7.2f \n", "Cherry Pie Slice -", slicePieQ, (slicePieQ * slicePiePrice) );
		System.out.printf("%20s \t %6d \t $%7.2f \n", "Whole Cherry Pie -", wholePieQ, (wholePieQ * wholePiePrice) );
		System.out.printf("%20s \t %6d \t $%7.2f \n", "Pi Charm -", charmQ, (charmQ * charmPrice) );
		System.out.println("\t\t\t     ————————————————————");
		if (customerHasPieCard){
			if (total >= 100){
				double subtotal1 = subtotal;
				subtotal = subtotal *.9;
				System.out.printf("\t\t\t\t\t $%7.2f \n", subtotal1 );
				System.out.println("");
				System.out.printf("\t\t\t%15s\t-$%7.2f \n", "10% Off:", (subtotal1-subtotal));
				discount = originalPrice - subtotal;
				tax = .07 * subtotal;
				total = subtotal + tax;
			}
		}
		System.out.printf("\t\t\t%15s\t $%7.2f \n", "Subtotal:", subtotal );
		System.out.printf("\t\t\t%15s\t $%7.2f \n",  "Sales Tax:", tax );
		System.out.println("\t\t\t     ————————————————————");
		System.out.printf("\t\t\t%15s\t $%7.2f \n", "Order Total:", total );
		if (customerHasPieCard){
			System.out.printf("\t\t\t%15s\t $%7.2f! \n", "Card Savings:", discount );  // IF MEMBER
		}
		System.out.println("——————————————————");
		System.out.println("[1]CHANGE ORDER   |"); //GO BACK TO MENU SCREEN
		System.out.println("[2]CHECKOUT  \t  |"); //GO TO CHECKOUT
		System.out.print( "——————————————————   ");
		System.out.println("");
		int number = sc.nextInt();
			switch (number){
					case 1:
						getOrder();
						break;
					case 2:
						checkOut();
						break;
					default:
						System.out.println("I can only read in 1's or 2's!");
						orderScreen();
						break;
				}
		}
	
	public static void checkOut(){
	/////CHECKOUT FUNCTION
		System.out.println("");
		System.out.printf("Alright, You Owe Me $%.2f \n", total );
		System.out.println("How Much Do You Have?:");
		System.out.print("[TYPE AMOUNT]  $");
		double moneyGiven = sc.nextDouble();
		double change = moneyGiven - total ;
			if (moneyGiven < total){
					System.out.println("That’s not cool, I might be blind, but I can count money! I need more than that!");
					checkOut();
				} 
			else {
					System.out.printf("Sweet! Here’s your extra $%.2f back! \n\n", change);
				}
		}
	
}

Status API Training Shop Blog About Pricing
© 2016 GitHub, Inc. Terms Privacy Security Contact Help
