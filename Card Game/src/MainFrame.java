import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class MainFrame extends JFrame {
    JLabel score;
    static JComboBox<String> comboBox;
    static ButtonGroup group;
    JRadioButton club;
    JRadioButton spade;
    JRadioButton diamond;
    JRadioButton heart;
    JButton click;
    JLabel computerlabel;
    JLabel yourlabel;
    static ImageIcon player;
    static JLabel playericon;
    JLabel computericon;
    ImageIcon computer;
    int scoreNo = 10;
    JButton restart;

    public MainFrame() throws HeadlessException {
        super("Card Game");
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //Score Label
        score = new JLabel();
        score.setText("Your Score: " + scoreNo);
        score.setFont(new Font("TimesNewRoman",Font.BOLD,20));
        score.setForeground(Color.BLUE);

        //Check Button
        click = new JButton();
        click.setText("Check!");
        click.setPreferredSize(new Dimension(130,25));

        click.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedItem() != null && group.getSelection().getActionCommand() != null){
                    compcardChoose();
                }
            }
        });


        //Labels
        yourlabel = new JLabel("Player's Card");
        computerlabel = new JLabel("Computer's Card");

        //Player card
        player = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cards/back.jpg")));
        playericon = new JLabel(player);

        //Computer card
        computer = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cards/back.jpg")));
        computericon = new JLabel(computer);

        //Restart Button
        restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restarter();
            }
        });


        //Score
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(10,0,0,0);
        add(score, gc);

        //Combobox and RadioButtons
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(0,0,0,0);
        add(panel(), gc);

        //Player
        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(0,0,130,0);
        add(playericon, gc);

        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(0,0,100,0);
        add(yourlabel, gc);

        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(0,180,130,0);
        add(computericon, gc);

        //Computer
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(0,180,100,0);
        add(computerlabel, gc);

        //Click Button
        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(0,65,65,0);
        add(click, gc);

        //Restrart Button
        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        gc.insets = new Insets(0,0,10,0);
        add(restart, gc);


        getContentPane().setBackground(Color.GRAY);
        setSize(400,400);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
    private JPanel radio(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2,2,10,10));
        group = new ButtonGroup();

        //RadioBox
        club = new JRadioButton("Club");
        spade = new JRadioButton("Spade");
        diamond = new JRadioButton("Diamond");
        heart = new JRadioButton("Heart");

        club.setActionCommand("c");
        spade.setActionCommand("s");
        diamond.setActionCommand("d");
        heart.setActionCommand("h");

        group.add(club);
        group.add(spade);
        group.add(diamond);
        group.add(heart);

        club.addActionListener(new ButtonListener() {});
        spade.addActionListener(new ButtonListener(){});
        diamond.addActionListener(new ButtonListener(){});
        heart.addActionListener(new ButtonListener(){});

        jPanel.add(club);
        jPanel.add(spade);
        jPanel.add(diamond);
        jPanel.add(heart);

        jPanel.setBackground(Color.GRAY);

        return jPanel;
    }
    private JPanel panel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //ComboBox
        String[] card = {"ace","jack","queen","king","2","3","4","5","6","7","8","9","10"};
        comboBox = new JComboBox<>();

        for (int i = 0; i < card.length; i++){
            comboBox.insertItemAt(card[i],i);
            comboBox.setActionCommand(card[i]);
        }

        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(new ButtonListener() {});

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(50,0,0,65);
        add(radio(), gc);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(50,50,0,0);
        add(comboBox, gc);

        return panel;
    }
    static void cardChoose(){
        String tempCard ="/cards/" +comboBox.getSelectedItem() + group.getSelection().getActionCommand()+".jpg";

        player = new ImageIcon(Objects.requireNonNull(MainFrame.class.getResource(tempCard)));
        playericon.setIcon(player);

    }
    private void compcardChoose(){
        Random random = new Random();
        String[] card = {"ace","jack","queen","king","2","3","4","5","6","7","8","9","10"};
        String[] temp = {"c","d","h","s"};
        int i = random.nextInt(card.length);
        int j = random.nextInt(temp.length);
        String tempCard ="/cards/" +card[i] + temp[j]+".jpg";

        computer = new ImageIcon(Objects.requireNonNull(getClass().getResource(tempCard)));
        computericon.setIcon(computer);

        if (Objects.equals(comboBox.getSelectedItem(), card[i]) && group.getSelection().getActionCommand().equals(temp[j])){
            scoreNo += 10;
            scoreUpdater();
        }else if(group.getSelection().getActionCommand().equals(temp[j])){
            scoreNo += 3;
            scoreUpdater();
        }else if(Objects.equals(comboBox.getSelectedItem(), card[i])){
            scoreNo += 5;
            scoreUpdater();
        }else{
            scoreNo -= 1;
            scoreUpdater();
        }
        if (scoreNo >= 25){
            score.setText("Player Wins");
        } else if (scoreNo <= 0) {
            score.setText("Player Loses");
        }

    }
    private void scoreUpdater(){
        score.setText("Your Score: " + scoreNo);
    }

    private void restarter(){
        scoreNo = 10;
        scoreUpdater();
        ImageIcon back = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cards/back.jpg")));;
        playericon.setIcon(back);
        computericon.setIcon(back);
        group.clearSelection();
        comboBox.setSelectedIndex(0);
    }

}