import snake.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dfomichev on 04.02.2017.
 */
public class Main {

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            new GameWindow();
        });

    }
}
