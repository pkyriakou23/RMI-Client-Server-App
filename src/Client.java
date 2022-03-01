import java.rmi.registry.*;

public class Client
{
    public static void main(String[] args) {

        try {
                    Registry rmiRegistry = LocateRegistry.getRegistry(Integer.parseInt(args[1]));
                            Messaging stub = (Messaging) rmiRegistry.lookup("messaging");
                            if(args[2].equals("1"))
                            {
                                long temp = stub.createAccount(args[3]);
                                if (temp == -1)
                                    System.out.println("Sorry, the user already exists");
                                else if(temp == 1)
                                    System.out.println("Invalid Username");
                                    else
                                        System.out.println(temp);
                            }
                            if(args[2].equals("2"))
                            {
                                String[] temp = stub.showAccount( Long.parseLong(args[3]));
                                for (String s: temp)
                                    System.out.println(s);
                            }
                            if (args[2].equals("3"))
                                System.out.println(stub.sentMessage(Long.parseLong(args[3]),args[4],args[5]));

                            if (args[2].equals("4"))
                            {
                                String[] temp = stub.showInbox(Long.parseLong(args[3]));
                                for (String s: temp)
                                    System.out.println(s);
                            }
                            if (args[2].equals("5"))
                                System.out.println(stub.readMessage(Long.parseLong(args[3]),Integer.parseInt(args[4])));
                            if (args[2].equals("6"))
                                System.out.println(stub.deleteMessage(Long.parseLong(args[3]),Integer.parseInt(args[4])));
                            



        } catch (Exception e) { System.out.println("can not connect with server"); }
    }

}

