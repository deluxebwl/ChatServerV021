import javax.swing.*;
import java.awt.Color;

/**
 * In einem Eingabefenster kann ein Zeile für die Chatkonferenz eingegeben werden.
 * Nach dem Abschicken wird diese an alle anderen Konferenzteilnehmer verteilt.
 * 
 * Da ein Schließen des Fensters die Zeichenkette "null" sendet, wurde die Ausgabe von "null" abgfangen.
 * "null" wird nicht dargestellt (Groß- und Kleinschreibung wird nicht beachtet).
 * 
 * @author LK
 * @version 2021-09-30
 */
public class Eingabefenster extends Thread {

    // Instanzvariable
    private JFrame fenster = new JFrame();

    private boolean isActive = true; // true, solange das Eingabefenster aktiv ist

    private ChatClient client;  // Client, an den alle Texte weitergereicht werden

    public Eingabefenster(ChatClient client) {
        this.client = client;

    }

    /**
     * Zeigt den Dialog an.
     * @return eingegebener Text oder "null", wenn das Fenster mit Cancel geschlossen wird
     */
    public String ask(){
        String text=JOptionPane.showInputDialog(fenster,"Eingabe");
        return text;

    }

    /**
     * Diese Methode nicht aufrufen. Sie wird über start() gestartet (s. Thread).
     * <p>
     * <b>Wiederholtes Anzeigen des Dialogfensters</b>
     */
    @Override
    public void run() { 
        System.out.println("Eingabefenster-Thread gestartet.");
        
        // solange die Eingabe aktiv sein soll
        while(isActive){
            
            String antwort = ask(); // ruf den Dialog auf
            
            // wenn es eine gültige antwort gibt
            if (antwort != null && !antwort.equalsIgnoreCase("null")){
                client.send(antwort); // schicke die antwort an den client
            }
        }
        
        // die Schleife wird verlassen, wenn das Eingabefenster nicht mehr aktiv ist
        
        System.out.println("Eingabefenster wurde beendet.");
    }

    /**
     * Schließt das Eingabefenster.
     */
    public void close(){
        System.out.println("Eingabefenster: close().");
        isActive = false;
        fenster.dispose(); // schließe das Fenster

    } 
}