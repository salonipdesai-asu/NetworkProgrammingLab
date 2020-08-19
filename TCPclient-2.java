import java.io.*;
import java.net.*;
import java.util.*;

public class TCPclient {
    static int userid=1;
    static int redo=0;

    static int seatr11[][]=new int[6][5];
    static int seatr12[][]=new int[6][5];
    static int seatr13[][]=new int[6][5];
    static int seatr14[][]=new int[6][5];
    static int seatr15[][]=new int[6][5];

    static int seatr21[][]= new int[6][5];
    static int seatr32[][]=new int[6][5];
    static int seatr43[][]=new int[6][5];
    static int seatr54[][]=new int[6][5];


    static String movie=new String();
    static String timeslot = new String();
	private static Socket socket = null;
	private static InputStream inStream = null;
	private  static OutputStream outStream = null;
	public TCPclient() {}
	public void createSocket() {
		try {

			socket = new Socket("localhost",4000);
			System.out.println("WELCOME TO PVR JUHU! BOOK MOVIE TICKETS NOW. \n");
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();
			createReadThread();
			createWriteThread();
		}
		catch (UnknownHostException u) {
			u.printStackTrace();
		}
		catch (IOException io) {
			io.printStackTrace();
		}

	}
	public void createReadThread() {
		Thread readThread = new Thread() {
			public void run() {
				while(socket.isConnected())
				{
					try {
						byte[] readBuffer = new byte[200];
						int num = inStream.read(readBuffer);
						if(num>0)
						{
							byte[] arrayBytes = new byte[num];
							System.arraycopy(readBuffer,0,arrayBytes,0,num);
							String receivedMessage = new String(arrayBytes,"UTF-8");
							System.out.println("YOUR TICKET AND PAYMENT: \n"+receivedMessage);
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


       				while(socket.isConnected() && redo==0)
				{
				     System.out.println("ENTER YOUR CHOICE: \n1)Black Panther \n2)Avengers \n3)Guardians of the galaxy \n4)Lady Bird \n5)The Shape of Water");
					try {
						BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
						//sleep(100);
						String typedMessage = inputReader.readLine();
						movie= typedMessage;
						if(typedMessage!=null && typedMessage.length()>0)
						{
							synchronized(socket) {
								outStream.write(typedMessage.getBytes("UTF-8"));
								//sleep(100);
							}
						}
                        if(movie.equals("1"))
						System.out.println("-------SCREEN A-------\nPICK A TIME SLOT: \n1)10:00 AM \n2)2:00 PM \n3)5:00 PM \n4)8:00 PM \n5)11:00 PM");
						else if(movie.equals("2"))
                            System.out.println("-------SCREEN B-------\nONLY ONE SLOT: 1)10:00 AM");
                        else if(movie.equals("3"))
                            System.out.println("-------SCREEN B-------\nONLY ONE SLOT: 2)2:00 PM");
                        else if(movie.equals("4"))
                            System.out.println("-------SCREEN B-------\nONLY ONE SLOT: 4)5:00 PM");
                        else if(movie.equals("5"))
                            System.out.println("-------SCREEN B-------\nONLY ONE SLOT: 5)8:00 PM");
                        else{}



                        typedMessage = inputReader.readLine();
                        timeslot= typedMessage;
						if(typedMessage!=null && typedMessage.length()>0)
						{
							synchronized(socket) {
								outStream.write(typedMessage.getBytes("UTF-8"));
								sleep(100);
							}
						}

						System.out.println("PICK YOUR ROW: F|E|D|C|B|A\n-----------\n\nF1 F2 F3 F4 F5 \nE1 E2 E3 E4 E5 \nD1 D2 D3 D4 D5 \nC1 C2 C3 C4 C5 \nB1 B2 B3 B4 B5 \nA1 A2 A3 A4 A5 \n---SCREEN---\n");
						typedMessage = inputReader.readLine();
						char row[] = typedMessage.toCharArray();
						if(typedMessage!=null && typedMessage.length()>0)
						{
							synchronized(socket) {
								outStream.write(typedMessage.getBytes("UTF-8"));
								//sleep(100);
							}
						}

                        char a ='F';
                        String check = movie+timeslot;
						System.out.println("PICK YOUR SEATS:\n RES----> ALREADY RESERVED\n");
						switch(check)
						{
                        case "11":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr11[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "12":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr12[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "13":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr13[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "14":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr14[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "15":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr15[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "21":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr21[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "32":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr32[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "43":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr43[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;

                        case "54":
						for(int i=0; i<6;i++)
                        {

                         for(int j=0;j<5;j++)
                        {
                             if(seatr54[i][j]==0)
                                System.out.print((char)(a-i) +" "+(j+1)+ "  ");
                             else
                                System.out.print("RES  ");
                        }
                        System.out.println();
                        }
                        break;


                        }//END OF SWITCH
						typedMessage = inputReader.readLine();
						int h= row[0]-65;

						switch (check)
						{

                        case "11":
                        if(typedMessage.contains("1"))
                        {
                          seatr11[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr11[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr11[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr11[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr11[5-h][4]=1;
                        }

                        break;

                        case "12":
                        if(typedMessage.contains("1"))
                        {
                          seatr12[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr12[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr12[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr12[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr12[5-h][4]=1;
                        }

                        break;

                        case "13":
                        if(typedMessage.contains("1"))
                        {
                          seatr13[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr13[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr13[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr13[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr13[5-h][4]=1;
                        }

                        break;

                        case "14":
                        if(typedMessage.contains("1"))
                        {
                          seatr14[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr14[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr14[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr14[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr14[5-h][4]=1;
                        }

                        break;

                        case "15":
                        if(typedMessage.contains("1"))
                        {
                          seatr15[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr15[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr15[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr15[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr15[5-h][4]=1;
                        }

                        break;

                       case "21":
                        if(typedMessage.contains("1"))
                        {
                          seatr21[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr21[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr21[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr21[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr21[5-h][4]=1;
                        }

                        break;

                        case "32":
                        if(typedMessage.contains("1"))
                        {
                          seatr32[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr32[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr32[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr32[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr32[5-h][4]=1;
                        }

                        break;

                        case "43":
                        if(typedMessage.contains("1"))
                        {
                          seatr43[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr43[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr43[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr43[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr43[5-h][4]=1;
                        }

                        break;

                        case "54":
                        if(typedMessage.contains("1"))
                        {
                          seatr54[5-h][0]=1;
                        }
                        if(typedMessage.contains("2"))
                        {
                            seatr54[5-h][1]=1;
                        }
                         if(typedMessage.contains("3"))
                        {
                            seatr54[5-h][2]=1;
                        }
                        if(typedMessage.contains("4"))
                        {
                            seatr54[5-h][3]=1;
                        }
                         if(typedMessage.contains("5"))
                        {
                            seatr54[5-h][4]=1;
                        }

                        break;




						}
						if(typedMessage!=null && typedMessage.length()>0)
						{
							synchronized(socket) {
								outStream.write(typedMessage.getBytes("UTF-8"));
								//sleep(100);
							}
						}
						System.out.println("CONFIRM YOUR RESERVATION 1)YES 2)NO");
						typedMessage = inputReader.readLine();
						if(typedMessage!=null && typedMessage.length()>0)
						{
							synchronized(socket) {
								outStream.write(typedMessage.getBytes("UTF-8"));
								sleep(100);
							}
						}
                        System.out.println("Would you like to continue enter CONTINUE else enter NO");
                        typedMessage = inputReader.readLine();
                        if(typedMessage.equals("NO"))
                        {
                          redo = 1;
                        }
						if(typedMessage!=null && typedMessage.length()>0)
						{
							synchronized(socket) {
								outStream.write(typedMessage.getBytes("UTF-8"));
								sleep(100);
							}
						}



					}
					catch(InterruptedException ie) {
						ie.printStackTrace();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		writeThread.setPriority(Thread.MAX_PRIORITY);
		writeThread.start();
	}



	public static void main(String[] args) throws Exception {
		TCPclient myClient = new TCPclient();
		myClient.createSocket();





	}
}
