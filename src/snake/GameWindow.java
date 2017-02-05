package snake;

import javax.swing.*;

/**
 * Created by dfomichev on 04.02.2017.
 */
public class GameWindow extends JFrame {

    public GameWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        BaseField field = new BaseField();
        setContentPane(field);
        setTitle("Snake");
        pack();
        setVisible(true);
    }
}
