import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;


public class WarGameGUI {
    private JPanel contentPane; // main panel
    private JLabel player1DeckLabel, player2DeckLabel;
    private JLabel player1Card, player2Card;
    private JLabel messageLabel;
    private JButton player1PlayButton, player2PlayButton;


    private Game Game;

    public WarGameGUI(Game game){
        this.Game = game;

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome to the War Game!");
        contentPane.add(label, BorderLayout.NORTH);

        JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new GridLayout(3,1));
        player1DeckLabel = new JLabel("Player 1 Deck: 26 cards", SwingConstants.CENTER);
        player1Card = new JLabel("?", SwingConstants.CENTER);
        player1Card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player1PlayButton = new JButton("Play card");
        player1PlayButton.addActionListener(new Player1PlayListener());

        player1Panel.add(player1DeckLabel);
        player1Panel.add(player1Card);
        player1Panel.add(player1PlayButton);


        //player 2
        JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new GridLayout(3, 1));
        player2DeckLabel = new JLabel("Player 2 Deck: 26 cards", SwingConstants.CENTER);
        player2Card = new JLabel("?", SwingConstants.CENTER);
        player2Card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player2PlayButton = new JButton("Play card");
        player2PlayButton.addActionListener(new Player2PlayListener());

        player2Panel.add(player2DeckLabel);
        player2Panel.add(player2Card);
        player2Panel.add(player2PlayButton);


        //center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3,1));
        JLabel versusLabeL = new JLabel("vs", SwingConstants.CENTER);
        messageLabel = new JLabel("It's a war", SwingConstants.CENTER);
        centerPanel.add(player1Card);
        centerPanel.add(versusLabeL);
        centerPanel.add(player2Card);

        contentPane.add(player1Panel, BorderLayout.WEST);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(player2Panel, BorderLayout.EAST);
        contentPane.add(messageLabel, BorderLayout.SOUTH);


    }
    public JPanel getContentPane() {return contentPane;}

    private class Player1PlayListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(!Game.getPlayer1Deck().isEmpty()){
                Cards topCard = Game.getPlayer1Deck().remove(0);
                player1Card.setText(topCard.toString());
                updateDeckLabels();
                checkWinner();
            }
            else
            {
                player1PlayButton.setEnabled(false);
                //disable if deck empty
            }
        }
    }

    private class Player2PlayListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(!Game.getPlayer2Deck().isEmpty())
            {
                Cards topCard = Game.getPlayer2Deck().remove(0);
                player2Card.setText(topCard.toString());
                updateDeckLabels();
                checkWinner();
            }
            else
            {
                player2PlayButton.setEnabled(false);
                //disable
            }
        }
    }

    private void updateDeckLabels(){
        player1DeckLabel.setText("Player 1 Deck: " + Game.getPlayer1Deck().size() + " cards");
        player2DeckLabel.setText("Player 2 Deck: " + Game.getPlayer2Deck().size() + " cards");
    }

    private void checkWinner()
    {
        if(!player1Card.getText().equals("?") && !player2Card.getText().equals("?"))
        {
            int comparable = player1Card.getText().compareTo(player2Card.getText());
            String winner = Game.determineWinner(comparable, "player 1", "player 2");
            messageLabel.setText(winner + " wins!");
        }


        player1Card.setText("?");
        player2Card.setText("?");
    }
}
