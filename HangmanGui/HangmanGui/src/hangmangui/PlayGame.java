/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author haide
 */
public class PlayGame extends JFrame implements ActionListener {

    private JButton playButton, exitButton;
    private JLabel welcomeMessage, OptionsMessage, InformatioMessage;
    private JPanel mainPanel;
    private JRadioButton Easy, Normal, Hard, Option1, Option2;
    String[] valueArray = new String[2];
//    private HangmanClass object = new HangmanClass();
    private int count = 0;

    public PlayGame() {

        super("Play Game");

        mainPanel = new JPanel();
        mainPanel.setSize(new Dimension(400, 500));
        mainPanel.setBackground(Color.MAGENTA);
        mainPanel.setLayout(null);

        welcomeMessage = new JLabel();
        welcomeMessage.setText("Welcome To Hangman Game");
        //setting bounds for the welcome message on the JFrame
        welcomeMessage.setBounds(100, 20, 200, 50);
        mainPanel.add(welcomeMessage);

        OptionsMessage = new JLabel();
        OptionsMessage.setText("Select Your Difficulty Level");
        OptionsMessage.setBounds(welcomeMessage.getX(), welcomeMessage.getHeight() + welcomeMessage.getY() + 5, welcomeMessage.getWidth(), 50);
        mainPanel.add(OptionsMessage);

        Easy = new JRadioButton();
        Easy.setText("Easy");
        Easy.setBounds(OptionsMessage.getX() - 50, OptionsMessage.getHeight() + OptionsMessage.getY() + 20, 80, 30);

        Normal = new JRadioButton();
        Normal.setText("Normal");
        Normal.setBounds(Easy.getWidth() + Easy.getX() + 10, OptionsMessage.getHeight() + OptionsMessage.getY() + 20, 80, 30);

        Hard = new JRadioButton();
        Hard.setText("Hard");
        Hard.setBounds(Normal.getWidth() + Normal.getX() + 10, OptionsMessage.getHeight() + OptionsMessage.getY() + 20, 80, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(Easy);
        group.add(Normal);
        group.add(Hard);
        //Adding the ActionListener on the Radio Buttons
        Easy.addActionListener(this);
        Normal.addActionListener(this);
        Hard.addActionListener(this);

        mainPanel.add(Easy);
        mainPanel.add(Normal);
        mainPanel.add(Hard);

        InformatioMessage = new JLabel();
        String text = "<html>Dear User, You have two options to play the game<br/><br/>       1) Guess the word as a whole(Here you only 1 Chance to guess the word)<br/><br/>       2) Guess the word in Parts</html>";
        InformatioMessage.setText(text);
        InformatioMessage.setBounds(Easy.getX(), Easy.getY() + Easy.getHeight() + 5, 300, 150);
        mainPanel.add(InformatioMessage);

        Option1 = new JRadioButton();
        Option1.setText("Option - 1");
        Option1.setBounds(InformatioMessage.getX() + 30, InformatioMessage.getY() + InformatioMessage.getHeight() + 5, 80, 30);

        Option2 = new JRadioButton();
        Option2.setText("Option - 2");
        Option2.setBounds(Option1.getWidth() + Option1.getX() + 40, InformatioMessage.getY() + InformatioMessage.getHeight() + 5, 80, 30);

        ButtonGroup group2 = new ButtonGroup();
        group2.add(Option1);
        group2.add(Option2);

        Option1.addActionListener(this);
        Option2.addActionListener(this);

        mainPanel.add(Option1);
        mainPanel.add(Option2);

        playButton = new JButton();
        playButton.setText("Start Game!");
        playButton.setBounds(Option1.getX() + 30, Option1.getY() + Option1.getHeight() + 20, 150, 30);
        playButton.addActionListener(this);

        mainPanel.add(playButton);

        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(playButton)) {
            if ((valueArray[0] == null) || (valueArray[1] == null)) {
                JOptionPane.showMessageDialog(null, "Pls fill the options First! ", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                if (valueArray[1].equals("Option - 1")) {
                    SecondHangmanClass obj = new SecondHangmanClass();

                } else {
                    HangmanClass obj = new HangmanClass(valueArray);
                }

            }
        } else {
            try {
                String text = ae.getActionCommand();
                ///    System.out.println("Count: " + count);
                valueArray[count] = text;
                count++;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "<html>Options are already Selected<br/>Please Click the Start Game! Button</html>", "Warning", JOptionPane.WARNING_MESSAGE);

            }

        }

    }

}
