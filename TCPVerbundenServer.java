
/**
 * Der Zustand TCPVerbundenServer gilt beim Server nachdem die TCP-Verbindung aufgebaut wurde.
 * <p>
 * Es können in diesem Zustand folgende Methoden ausgeführt werden: <p>
 * Server:
 * <ul>
 * <li> VerbindungsabbauAnfrageIND
 * <li> VerbindungsabbauREQ
 * <li> TextREQ
 * <li> TextAnmeldenIND
 * </ul>
 * <p>
 * Es gibt nur eine Instanz dieser Klasse, die mit dem Singelton-Pattern verwaltet wird.
 * 
 * @author LK
 * @version 2021-11-17
 */
public class TCPVerbundenServer extends ChatAnwendungsschichtZustand
{
    // Klassenvariablen
    private static TCPVerbundenServer singelton;
    
    // Klassenmethode
    /**
     * Gibt das Singelton zurück. Ein Singelton wird beim ersten Aufruf erzeugt.
     * @return Singelton
     */
    public static  TCPVerbundenServer getSingelton(){
        if (singelton == null){
            singelton = new TCPVerbundenServer();
        }
        return singelton;
    }
    // Instanzvariablen     
    // keine
        
    /**
     * Konstruktor für Objekte der Klasse TCPVerbundenClient
     */
    private TCPVerbundenServer()
    {
        super("TCPVerbundenServer");  // Aufruf des Konstruktors der ChatAnwendungsschichtZustand-Klasse
                                      // Der Aufruf der Konstruktormethode des Oberklasse muss in der ersten Zeile des Konstruktors stehen.
    }
    /**
     * VerbindungsabbauAnfrageIND
     */
    public synchronized void VerbindungsabbauAnfrageIND(ChatAnwendungsschicht kontext,ICI ici,SDU sdu) throws ZustandException
    {
        kontext.VerbindungsabbauAnfrageINDDO(ici,sdu);
    }
    /**
     * VerbindungsabbauREQ
     */
    public synchronized void VerbindungsabbauREQ(ChatAnwendungsschicht kontext,ICI ici,SDU sdu) throws ZustandException
    {
        kontext.VerbindungsabbauREQDO(ici,sdu);
        System.out.println("Server: TCPVerbundenServer -> TCPGetrennt");
        kontext.nextState(ici,TCPGetrennt.getSingelton());
    }
    /**
     * TextREQ
     */
    public synchronized void TextREQ(ChatAnwendungsschicht kontext,ICI ici,SDU sdu) throws ZustandException
    {
        kontext.TextREQDO(ici,sdu);
    }
    /**
     * TextAnmeldenIND
     */
    public synchronized void TextAnmeldenIND(ChatAnwendungsschicht kontext,ICI ici,SDU sdu) throws ZustandException
    {
        kontext.TextAnmeldenINDDO(ici,sdu);
    }
}