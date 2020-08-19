import java.io.*;
import java.net.*;

public class TCPserver {
    static int count=0;
    static int continuethis =0;
    static String s= new String();
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	private InputStream inStream = null;
	private OutputStream outStream = null;
	public TCPserver() {}
	public void createSocket() {
		try {
			ServerSocket serverSocket = new ServerSocket(4000);
			while(true) {
				socket = serverSocket.accept();
				System.out.println("Connection established. User may book tickets now.");
				inStream = socket.getInputStream();
				outStream = socket.getOutputStream();
				createReadThread();

			}
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}
	public void createReadThread() {
		Thread readThread = new Thread() {
			public void run() {

				while(socket.isConnected() && continuethis ==0)
				{
					try {

						byte[] readBuffer = new byte[200];
						int num = inStream.read(readBuffer);
						byte[] arrayBytes = new byte[num];
						if(num>0)
						{

							System.arraycopy(readBuffer,0,arrayBytes,0,num);
							String receivedMessage = new String(arrayBytes,"UTF-8");
                            System.out.println("The movie chosen is:" +receivedMessage);
                            s = s+ "---------------------------------------\n-----------PVR JUHU-------\n";
                                if(receivedMessage.equals("1"))
                                {
                                s= s+"\nBLACK PANTHER \n";

                                System.out.print(" BLACK PANTHER");
                                }
                                else if(receivedMessage.equals("2"))
                                {
                                    s= s+"\nAVENGERS \n";
                                    System.out.print(" AVENGERS");
                                }

                                else if(receivedMessage.equals("3"))
                                {
                                    s= s+"\nGUARDIANS OF THE GALAXY\n";
                                System.out.print(" GUARDIANS OF THE GALAXY");
                                }
                                else if(receivedMessage.equals("4"))
                                {
                                    s= s+"\nLADY BIRD\n";
                                    System.out.print("LADY BIRD");
                                }

                                else if(receivedMessage.equals("5"))
                                {
                                      s= s+"\nTHE SHAPE OF WATER\n";
                                    System.out.print(" THE SHAPE OF WATER");

                                }
                               else {
                                s="ENTER A VALID MOVIE!";
                               }

                        readBuffer = new byte[200];
                        num = inStream.read(readBuffer);
						if(num>0)
						{
							arrayBytes = new byte[num];
							System.arraycopy(readBuffer,0,arrayBytes,0,num);
                            receivedMessage = new String(arrayBytes,"UTF-8");
                            System.out.println("\nThe TIME SLOT chosen is:");
                            if(receivedMessage.equals("1"))
                                {
                                    System.out.print(" 10:00 am to 12:30 am");
                                    s= s+"10:00 am to 12:30 am \n";
                                }
                                else if(receivedMessage.equals("2"))
                                {
                                    System.out.print(" 2:00 pm to 4:30 pm");
                                    s= s+"2:00 pm to 4:30 pm \n";
                                }
                                else if(receivedMessage.equals("3"))
                                {
                                    System.out.print(" 5:00 pm to 7:30 pm");
                                    s= s+"5:00 pm to 7:30 pm \n";
                                }
                                 else if(receivedMessage.equals("4"))
                                {
                                    System.out.print(" 8:00 pm to 10:30 pm");
                                    s= s+"8:00 pm to 10:30 pm \n";
                                }
                                 else if(receivedMessage.equals("5"))
                                {
                                    System.out.print(" 11:00 pm to 1:30 am");
                                    s= s+"11:00 pm to 1:30 am \n";
                                }
                                else
                                {
                                s= "ENTER A VALID TIME SLOT";
                                }


                        }
                        int plat=0; int gold=0; int silver=0;
                        readBuffer = new byte[200];
                        num = inStream.read(readBuffer);
						if(num>0)
						{
                            arrayBytes = new byte[num];
							System.arraycopy(readBuffer,0,arrayBytes,0,num);
                            receivedMessage = new String(arrayBytes,"UTF-8");
                            System.out.println("\nThe ROW chosen:");
                            if(receivedMessage.length() ==1 && (receivedMessage.charAt(0)== 'E'|| receivedMessage.charAt(0)== 'F'))
                               {
                                   plat=1;
                                   s= s+"\nPLATINUM SEATS ROW\nCOST PER SEAT: Rs.400 \n";
                                System.out.print("PLATINUM SEATS ROW \nCOST PER SEAT = Rs 400");
                               }
                            else if(receivedMessage.length() ==1 && (receivedMessage.charAt(0)== 'D'|| receivedMessage.charAt(0)== 'C'))
                               {
                                   gold=1;
                                   s= s+"\nGOLD SEATS ROW \nCOST PER SEAT: Rs.300 \n";
                                System.out.print("GOLD SEATS ROW \nCOST PER SEAT = Rs 300");
                               }
                                else if(receivedMessage.length() ==1 && (receivedMessage.charAt(0)== 'B'|| receivedMessage.charAt(0)== 'A'))
                               {
                                   silver=1;
                                   s= s+"\nSILVER SEATS ROW \nCOST PER SEAT: Rs.200 \n";
                                System.out.print("SILVER SEATS ROW \nCOST PER SEAT = Rs 200");
                               }
                               else
                               {
                                s= "ENTER A VALID ROW ALPHABET";
                               }

                            }

                        readBuffer = new byte[200];
                        num = inStream.read(readBuffer);
						if(num>0)
						{
                            arrayBytes = new byte[num];
							System.arraycopy(readBuffer,0,arrayBytes,0,num);
                            receivedMessage = new String(arrayBytes,"UTF-8");
                            System.out.println("\nThe SEATS chosen:");
                            String seats[] = receivedMessage.split(" ");
                            System.out.println("NO OF TICKETS SELECTED: "+ seats.length);
                            s=s+" "+ receivedMessage+"\n";
                            if(plat == 1)
                            {
                             System.out.println("TOTAL COST : "+ 400*(seats.length));
                             s= s+ seats.length +" PLATINUM TICKETS \n" +"TOTAL COST: "+ (400*seats.length);
                            }
                             else if(gold == 1)
                            {
                             System.out.println("TOTAL COST : "+ 300*(seats.length));
                             s= s+ seats.length +" GOLD TICKETS \n" +"TOTAL COST: "+ (300*seats.length);
                            }
                            else  if(silver == 1)
                            {
                             System.out.println("TOTAL COST : "+ 200*(seats.length));
                             s= s+ seats.length +" SILVER TICKETS \n" +"TOTAL COST: "+ (200*seats.length);
                            }


						}
                        readBuffer = new byte[200];
                        num = inStream.read(readBuffer);
						if(num>0)
						{
                            arrayBytes = new byte[num];
							System.arraycopy(readBuffer,0,arrayBytes,0,num);
                            receivedMessage = new String(arrayBytes,"UTF-8");
                            if(receivedMessage.equals("1"))
                            System.out.println("\nRESERVATION CONFIRMED!");
                            else{
                                s="RESERVATION CANCELLED,PLEASE TRY AGAIN";
                            }
						}
                            createWriteThread();

                        readBuffer = new byte[200];
                        num = inStream.read(readBuffer);
						if(num>0)
						{
                            arrayBytes = new byte[num];
							System.arraycopy(readBuffer,0,arrayBytes,0,num);
                            receivedMessage = new String(arrayBytes,"UTF-8");
                            if(receivedMessage.equals("NO"))
                            {
                                continuethis =1;
                                System.out.println("Connection ends");
                            }

						}
						}
					}


					catch(SocketException se) {
						System.exit(0);
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				}

		};

		readThread.setPriority(Thread.MAX_PRIORITY);
		readThread.start();


	}

	public void createWriteThread() {
		Thread writeThread = new Thread() {
			public void run() {
				if(socket.isConnected())
				{
					try {
						BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
						String typedMessage = s;
						if(typedMessage!=null && typedMessage.length()>0)
						{
							synchronized(socket) {
								outStream.write(typedMessage.getBytes("UTF-8"));
								//sleep(100);
							}
						}
					}
					//catch(InterruptedException ie) {
						//ie.printStackTrace();
					//}
					catch(IOException e) {
						e.printStackTrace();
					}
				//}
			}


		}
	};
			writeThread.setPriority(Thread.MAX_PRIORITY);
		writeThread.start();

	}



	public static void main(String[] args) throws Exception {
		TCPserver myServer = new TCPserver();
		myServer.createSocket();
	}
}
