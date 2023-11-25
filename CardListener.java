import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardListener implements ActionListener {
    private final JButton card;
    private final byte number;

    private final GUI gui;
    public CardListener(JButton card, byte number, GUI gui){
        this.card = card;
        this.number = number;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui.getCombined()>=number && !gui.getFlipped(number-1)) {
            card.setEnabled(false);
            card.setIcon(new ImageIcon("assets/back.jpg"));
            gui.setFlipped(number - 1, true);
            gui.decreaseCombined(number);
            gui.updateNumber();
            gui.possible();
        }
    }


}
