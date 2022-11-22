package OtherClasses;
import ScreenClasses.ScreenMain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.lang.InterruptedException;

public class Main {
    public static void main (String[] args) {
        ScreenMain MainForm;
        try {
            MainForm = new ScreenMain(null, true);
            MainForm.setVisible(true);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        System.exit(0);
    }
}
