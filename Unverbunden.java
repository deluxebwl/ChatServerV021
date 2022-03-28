
/**
 * Der Zustand Unverbunden gilt bevor die TCP-Verbindung aufgebaut wurde.
 * <p>
 * Es können in diesem Zustand folgende Methoden ausgeführt werden: <p>
 * Server:
 * <ul>
 * <li> ListenREQ
 * <li> ServerListenerReturn
 * </ul>
 * <p>
 * Client:
 * <ul>
 * <li> VerbindungsaufbauREQ
 * </ul>
 * <p>
 * Es gibt nur eine Instanz dieser Klasse, die mit dem Singelton-Pattern verwaltet wird.
 * 
 * @author LK
 * @version 2021-11-17
 */
public class Unverbunden extends ChatAnwendungsschichtZustand
{
    // Klassenvariablen
    private static Unverbunden singelton;
    
    // Klassenmethode
    /**
     * Gibt das Singelton zurück. Ein Singelton wird beim ersten Aufruf erzeugt.
     * @return Singelton
     */
    public static  Unverbunden getSingelton(){
        if (singelton == null){
            singelton = new Unverbunden();
        }
        return singelton;
    }
    // Instanzvariablen     
    // keine

    /**
     * Konstruktor für Objekte der Klasse Unverbunden
     */
    private Unverbunden()
    {
        super("Unverbunden"); // Aufruf des Konstruktors der ChatAnwendungsschichtZustand-Klasse
                              // Der Aufruf der Konstruktormethode des Oberklasse muss in der ersten Zeile des Konstruktors stehen.
    }
    
    /**
     * ListenREQ
     */
    public synchronized void ListenREQ(ChatAnwendungsschicht kontext, ICI ici,SDU sdu) throws ZustandException
    {
        kontext.ListenREQDO(ici,sdu);

    }
    /**
     * VerbindungsaufbauREQ
     */
    public  synchronized void VerbindungsaufbauREQ(ChatAnwendungsschicht kontext,ICI ici,SDU sdu) throws ZustandException
    {
        kontext.VerbindungsaufbauREQDO(ici,sdu);
        System.out.println("Client: Unverbunden -> TCPVerbundenClient");
        kontext.nextState(ici,TCPVerbundenClient.getSingelton());
    }

    /**
     * ServerListenerReturn
     */
    public synchronized void ServerListenerReturn(ChatAnwendungsschicht kontext,ICI ici,SDU sdu) throws ZustandException
    {
        kontext.ServerListenerReturnDO(ici,sdu);
        System.out.println("Server: Unverbunden -> TCPVerbundenServer");
        kontext.nextState(ici,TCPVerbundenServer.getSingelton());
    }
    
}
