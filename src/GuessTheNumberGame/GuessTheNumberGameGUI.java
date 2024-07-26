package GuessTheNumberGame;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

public class GuessTheNumberGameGUI extends JFrame 
{
    private int numberToGuess;
    private int numberOfAttempts;
    private JTextField guessInputField;   
    private JLabel messageLabel;
    private JButton submitButton;
    private JButton restartButton;
    private JPanel inputPanel;
    private final String placeholderText = "Enter your guess here!";
    
    public GuessTheNumberGameGUI()
    {
        //JFrame
        setTitle("GUESS THE NUMBER GAME");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //initialize the label before Reset to avoid NULL
        messageLabel = new JLabel("Guess a number between 1 and 100:", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        messageLabel.setForeground(Color.PINK);
        messageLabel.setBackground(Color.BLACK);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        messageLabel.setOpaque(true); // Make it opaque to display background color
        add(messageLabel, BorderLayout.NORTH);

        //initialize the panel before Reset to avoid NULL
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(30, 150, 280, 150));
        inputPanel.setBackground(Color.PINK);

        guessInputField = new JTextField(placeholderText, 20);
        guessInputField.setForeground(Color.GRAY);
        guessInputField.setAlignmentX(CENTER_ALIGNMENT);

        guessInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (guessInputField.getText().equals(placeholderText))
                {
                    guessInputField.setText("");
                    guessInputField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (guessInputField.getText().isEmpty())
                {
                    guessInputField.setForeground(Color.GRAY);
                    guessInputField.setText(placeholderText);
                }
            }
        });

        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align to the right
        restartButton = new JButton("Restart");
        inputPanel.add(guessInputField);
        inputPanel.add(Box.createVerticalStrut(20)); // Adds vertical spacing
        inputPanel.add(submitButton);
        add(inputPanel, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);

        //after all components set up, initialize the game with Reset
        resetGame();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
           } 
        });

        //makes the frame visible!
        setVisible(true);

        //focuses the frame
        this.requestFocus();
    }

    private void handleGuess()
    {
        try {
            int guess = Integer.parseInt(guessInputField.getText().trim());
            numberOfAttempts++;
            if (guess < numberToGuess)
            {
                messageLabel.setText("Too low! Try again.");
            }
            else if (guess > numberToGuess)
            {
                messageLabel.setText("Too high! Try again.");
            }
            else
            {
                messageLabel.setText("Correct! You guessed it in " + numberOfAttempts + " attempts.");
                guessInputField.setEditable(false);
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number.");
        }
        guessInputField.setText("");
    }

    private void resetGame()
    {
        numberToGuess = new Random().nextInt(100) + 1;
        numberOfAttempts = 0;
        messageLabel.setText("Guess a number between 1 and 100:");
        guessInputField.setText(placeholderText);
        guessInputField.setForeground(Color.GRAY);
        guessInputField.setEditable(true);
    }

    public static void main(String[] args)
    {
        new GuessTheNumberGameGUI();
    }
}
