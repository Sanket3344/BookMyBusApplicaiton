import java.util.*;
class Bus
{
	static String [] seats = new  String[30];
	static String date = "29/10/2024 9:00pm";
	static String hault = "JM Road Deccan Near QSPIDERS";
	static String [] drops = {"Deccan","Swargate","Katraj","Saswad","Hadapsar","Kharadi","Yerwada"};
	static double [] fares = {0,30,70,120,170,220,300};
	static final String busNumber = "MH12AA1234";
	static double totalColl;
	static Passenger obj;
	static 
	{
		Arrays.fill(seats,"AVAIL");
	}

	static 
	{
		System.out.println("NO REFUNDS");
	}

	public void cnacelTicket(Scanner sc,long contact)
	{
		if(obj != null)
		{
			if(contact == obj.getCont())
			{
				System.out.println("Are uou sure (YES/NO) : ");
				String resp = sc.next();
				if(resp.equalsIgnoreCase("YES"))
				{
					seats[obj.getSeatNumber()-1] = "AVAIL";
					obj = null;
					System.out.println("TICKET CANCELLED");
				}
			}
			else 
			{
				System.out.println("Wrong Contact");
			}
		}
		else 
		{
			System.out.println("BOOK YOUR TICKET FIRST");
		}
	}

	public void displaySeats()
	{
		System.out.println();
		for(int i=0; i<seats.length;i++)
		{
			System.out.print((i+1)+"-> "+seats[i]+"    ");
		}
		System.out.println();
	}

	public void seatBook(Scanner sc)
	{
		System.out.println("Seat Booking Module");
		System.out.println();
		System.out.print("Boarding : ");
		String boarding = sc.next();
		System.out.print("Destination : ");
		String dest = sc.next();

		for(;;)
		{
			displaySeats();
			System.out.print("Seat Number: ");
			int seatNumber = sc.nextInt();

			if(!(seatNumber<1 && seatNumber>30))
			{
				if(seats[seatNumber-1] == "AVAIL")
				{
					seats[seatNumber-1]="BOOKED";
					creatPassenger(sc,boarding,dest,seatNumber);
					break;
				}
				else 
				{
					System.out.println("SEAT OCCUPIED");
				}
			}
			else 
			{
				System.out.println("WRONG SEAT NUMBER ENTERED");
			}
		}
	}

	public void creatPassenger(Scanner sc,String boarding,String dest,int seatNumber)
	{
		System.out.print("Passenger Name: ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("Gender: ");
		String gender = sc.next();
		System.out.print("Contact: ");
		long contact = sc.nextLong();

		obj = new Passenger(name,gender,contact,boarding,dest,seatNumber,0);

		System.out.println();
		System.out.println("Do You want to book ticket (YES/NO) : ");
		String resp = sc.next();
		if(resp.equalsIgnoreCase("YES"))
		{
			payment();			
		}
		else 
		{
			System.out.println("HAPPY DIWALI");
		}
	}

	public void payment()
	{
		double bfare = 0;
		double dfare = 0;
		for(int i=0;i<drops.length;i++)
		{
			if(obj.getBoarding().equalsIgnoreCase(drops[i]))
			{
				bfare = fares[i];
			}
			if(obj.getDest().equalsIgnoreCase(drops[i]))
			{
				dfare = fares[i];
			}
		}
		double ticketPrice = dfare - bfare;

		obj.seatFare(ticketPrice);
		System.out.println("TICKET CONFIRMED & BOOKED");
		System.out.println();
		viewTicket(obj.getCont());
	}

	public void viewTicket(long contact)
	{
		if(obj != null)
		{
			if(contact == obj.getCont())
			{
				System.out.println();
				System.out.println("BOOKING DETATILS");
				System.out.println("Bus Number: "+ busNumber);
				System.out.println("Date & Time: " + date);
				System.out.println("Hault: "+ hault);
				Passenger copy = obj;
				System.out.println("TICKET Fare: "+copy.getFare());
				System.out.println("Seat Number: "+ copy.getSeatNumber());
				System.out.println("Passenger Name: "+ copy.getName());
				System.out.println("Passenger Gender: "+ copy.getGender());
				System.out.println("Passenger Contact: "+ copy.getCont());
				System.out.println("Passenger Boarding: "+ copy.getBoarding());
				System.out.println("Passenger Destination: "+copy.getDest());
				System.out.println();
				System.out.println("HAPPY JOURNEY GO HOME & HAPPY DIWALI");
			}
			else 
			{
				System.out.println("WRONG CONTACT NUMBER");
			}
		}
		else 
		{
			System.out.println("BOOK YOUR TICKET FIRST");
		}
	}
}

class Passenger
{
	private String name;
	private String gender;
	private long contact;
	private String boarding;
	private String dest;
	private int seatNumber;
	private double fare;

	Passenger(String name,String gender,long contact,String boarding,String dest,int seatNumber,double fare)
	{
		this.name = name;
		this.gender = gender;
		this.contact = contact;
		this.boarding = boarding;
		this.dest = dest;
		this.seatNumber = seatNumber;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String newName)
	{
		this.name = newName;
	}

	public String getGender()
	{
		return this.gender;
	}

	public long getCont()
	{
		return this.contact;
	}

	public void setCont(long newCont)
	{
		this.contact = newCont;
	}

	public String getBoarding()
	{
		return this.boarding;
	}

	public void setBoarding(String newBoarding)
	{
		this.boarding = newBoarding;
	}

	public String getDest()
	{
		return this.dest;
	}

	public void setDest(String newDest)
	{
		this.dest = newDest;
	}

	public int getSeatNumber()
	{
		return this.seatNumber;
	}

	public double getFare()
	{
		return this.fare;
	}

	public void seatFare(double newFare)
	{
		this.fare = newFare;
	}
}

class BookMyBusDriver
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to BOOK MY BUS");
		Bus bus = new Bus();

		for(;;)
		{
			System.out.println("1. Book Ticket");
			System.out.println("2. View Ticket");
			System.out.println("3. Cancel Ticket");
			System.out.println("4. Exit");

			System.out.println();
			System.out.print("Option: ");
			int opt = sc.nextInt();

			switch(opt)
			{
				case 1: bus.seatBook(sc); break;
				case 2: {
							System.out.print("Contact Number: ");
							long contact = sc.nextLong();
							bus.viewTicket(contact);
							break;
						}
				case 3: {
							System.out.print("Contact Number: ");
							long contact = sc.nextLong();
							bus.cnacelTicket(sc,contact);
							break;
						}
				case 4: System.exit(0);
				default: System.out.println("Wrong option Entered");
			}
		}	
	}
}