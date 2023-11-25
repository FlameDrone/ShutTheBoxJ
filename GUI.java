import javax.swing.*;
import java.awt.Font;
import java.util.Random;

public class GUI extends JFrame {
    private final JLabel diceLabel1;
    private final JLabel diceLabel2;
    private final ImageIcon[] diceStates;
    private final Random rand;
    private byte combined = 0;

    boolean[] flipped;
    private final JLabel number;

    public GUI(String title){
        super(title);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 100, 1200, 800);
        JPanel panel = new JPanel();

        this.rand = new Random();

        JButton rollButton = new JButton(new ImageIcon("assets/Roll.jpg"));
        rollButton.setBounds(520,670,200,60);
        panel.add(rollButton);
        rollButton.addActionListener(e -> {
            if(combined<=0){
                rollTheDice();
            }
        });

        this.diceStates = new ImageIcon[7];
        this.diceStates[0] = new ImageIcon("assets/EmptyDice.jpg");
        for(int i=1; i<diceStates.length; i++){
            this.diceStates[i] = new ImageIcon("assets/Dice"+i+".jpg");
        }

        this.diceLabel1 = new JLabel(this.diceStates[0]);
        this.diceLabel1.setBounds(320,640,100,100);
        panel.add(diceLabel1);

        this.diceLabel2 = new JLabel(this.diceStates[0]);
        this.diceLabel2.setBounds(820,640,100,100);
        panel.add(diceLabel2);

        JButton[] cards = new JButton[9];
        this.flipped = new boolean[9];
        for(int i=1; i<=9; i++){
            cards[i-1] = new JButton(new ImageIcon("assets/"+i+".jpg"));
            cards[i-1].setBounds(40+100*i,20,60,100);
            cards[i-1].addActionListener(new CardListener(cards[i-1], (byte) i, this));
            panel.add(cards[i-1]);
            flipped[i-1] = false;
        }

        this.number = new JLabel("0");
        this.number.setBounds(540,300,200,200);
        this.number.setFont(new Font("Number",Font.BOLD,160));
        panel.add(this.number);

        setContentPane(panel);
        panel.setLayout(null);
        this.setVisible(true);
    }

    public void rollTheDice() {
        byte d1 = (byte) (rand.nextInt(6) + 1);
        byte d2 = (byte) (rand.nextInt(6) + 1);
        this.diceLabel1.setIcon(this.diceStates[d1]);
        this.diceLabel2.setIcon(this.diceStates[d2]);
        this.combined = (byte) (d1 + d2);
        this.number.setText(String.valueOf(combined));
        possible();
    }

    public void possible(){
        if(combined == 0){
            return;
        }
        for(int i = Math.min(8,combined-1); i>-1; i--){
            if(!flipped[i]){
                return;
            }
        }
        this.number.setText("-1");
    }

    public byte getCombined(){
        return combined;
    }

    public void decreaseCombined(byte decrease){
        combined-=decrease;
    }

    public boolean getFlipped(int index) {
        return flipped[index];
    }

    public void setFlipped(int index, boolean value){
        flipped[index] = value;
    }

    public void updateNumber(){
        this.number.setText(String.valueOf(combined));
    }
}
