/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangui;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanClass extends JFrame implements ActionListener {

    /**
     * I have left the filePath String empty.Please set the file path of the
     * file according to your Computer's Location, or else the code will not
     * work as expected. Thanks!!
     */
    private String filePath = "";//Setting the file path
    private int CurrentState = 0,
            AlienParts = 0;
    public Random RandomGenerator = new Random();// Random genator for the array
    private char[] RandomWord;//Character Array storing the randomWord
    private String[] WordsArray;
    private char[] guesses;//Character Array storing the guesses
    private String NumberOfGuesses = "", Word = "";
    private JPanel mainPanel, bottomPanel;
    private String[] Valuelist = new String[2];

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getCurrentState() {
        return CurrentState;
    }

    public void setCurrentState(int CurrentState) {
        this.CurrentState = CurrentState;
    }

    public Random getRandomGenerator() {
        return RandomGenerator;
    }

    public void setRandomGenerator(Random RandomGenerator) {
        this.RandomGenerator = RandomGenerator;
    }

    public char[] getRandomWord() {
        return RandomWord;
    }

    public void setRandomWord(char[] RandomWord) {
        this.RandomWord = RandomWord;
    }

    public String[] getWordsArray() {
        return WordsArray;
    }

    public void setWordsArray(String[] WordsArray) {
        this.WordsArray = WordsArray;
    }

    public char[] getGuesses() {
        return guesses;
    }

    public void setGuesses(char[] guesses) {
        this.guesses = guesses;
    }

    public int getAlienParts() {
        return AlienParts;
    }

    public void setAlienParts(int AlienParts) {
        this.AlienParts = AlienParts;
    }

    public String getNumberOfGuesses() {
        return NumberOfGuesses;
    }

    public void setNumberOfGuesses(String NumberOfGuesses) {
        this.NumberOfGuesses = NumberOfGuesses;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String Word) {
        this.Word = Word;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public String[] getValuelist() {
        return Valuelist;
    }

    public void setValuelist(String[] Valuelist) {
        this.Valuelist = Valuelist;
    }

    /**
     * Empty Constructor
     */
    public HangmanClass() {

    }

    /**
     * This is also a Constructor that takes list array as a parameter and then
     * populates this.array(ValueList)
     *
     * @param list
     */
    public HangmanClass(String[] list) {
        // pass title to super class
        super("Hang Man");
        System.arraycopy(list, 0, this.Valuelist, 0, list.length);//Copying one array to another

        // populate word array
        WordsArray = textFile();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(new Dimension(700, 700));
        mainPanel.setBackground(Color.BLACK);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4, 4));
        JButton[] buttons = new JButton[26];
        String[] letters = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C",
            "V", "B", "N", "M"};

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(letters[i]);
            buttons[i].setSize(40, 40);
            buttons[i].setActionCommand(letters[i]);
            buttons[i].addActionListener(this);
            bottomPanel.add(buttons[i]);
        }
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
        play();
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // set the font
        Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 24);
        g.setFont(font);
        g.setColor(Color.WHITE);

        // if user has selected play from menu - start game
        if (CurrentState == 1) {

            CheckGameMessages();
            String result = "";
            for (int i = 0; i < guesses.length; i++) {

                result += guesses[i] + " ";

            }
            g.drawString(result, 300, 175);
            g.drawString("Used Words", 300, 300);
            g.drawString(NumberOfGuesses, 300, 350);
            System.out.println(RandomWord);
            // if user misses a letter - display body parts

            generatehangman(g);

        }
    }

    /**
     * This method takes graphics 'g' as a parameter and then generates alien
     * space ship and then an alien
     *
     * @param g
     */
    private void generatehangman(Graphics g) {
        if (AlienParts >= 1) {

            g.setColor(Color.RED);
            g.fillOval(140, 40, 20, 20);

            if (AlienParts >= 2) {
                // body
                g.setColor(Color.GREEN);
                g.fillOval(100, 50, 100, 70);

            }

            if (AlienParts >= 3) {
                //Alien ship Created
                g.setColor(Color.BLACK);
                g.fillOval(125, 75, 50, 30);

            }
            if (AlienParts >= 4) {
                //road Created
                g.setColor(Color.WHITE);
                g.drawLine(160, 235, 200, 235);
            }
            if (AlienParts >= 5) {
                //Alien created
                //head
                g.setColor(Color.GREEN);
                g.fillOval(160, 160, 40, 40);
                //eyes
                g.setColor(Color.BLACK);
                g.fillOval(185, 170, 10, 10);
                g.fillOval(165, 170, 10, 10);
                //body
                g.setColor(Color.WHITE);
                g.fillOval(173, 199, 15, 30);
                //hands
                g.drawLine(175, 205, 205, 207);
                g.drawLine(175, 205, 155, 207);

                //feet
                g.drawLine(180, 225, 185, 235);
                g.drawLine(180, 225, 175, 235);
            }
        }

    }

    /**
     * This method takes no parameter and checks if the game has been won or
     * lost and respectively, it generates the messages for winning or loosing
     * the game. After the message has been generated, it closes the whole game
     * system in either of the cases
     */
    private void CheckGameMessages() {

        if (winner() && AlienParts < 6) {
            JOptionPane.showMessageDialog(null, "You Have Successfuly Won The Game", "Successfull!!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(-1);
        } else if (AlienParts == 6) {
            JOptionPane.showMessageDialog(null, "You Have Lost The Game", "LOSE!!", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

    }

    /**
     * This method generates a random word and then returns that word
     *
     * @return word (Datatype - String)
     */
    public String getword() {
        WordsArray = textFile();
        int n = WordsArray.length;
        int r = RandomGenerator.nextInt(n);
        String word = WordsArray[r];
        return word;
    }

    /**
     * This method determines whether the guessed word matches Random word and
     * then returns either true or false
     *
     * @return boolean datatype
     */
    public boolean winner() {
        return Arrays.equals(guesses, RandomWord);

    }

    /**
     * This method reads data from a file "words.txt" and then writes that data
     * into Array list which is converted back to a List
     *
     * @return String[]
     */
    public String[] textFile() {

        BufferedReader reader = null;
        ArrayList<String> wordList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String s = " ";
            while ((s = reader.readLine()) != null) {
                wordList.add(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
        return wordList.toArray(new String[wordList.size()]);// converting from arraylist to array 

    }

    /**
     *
     * @param e(Action Event)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if (command.length() == 1 && CurrentState == 1) {
            /**
             * pass action event to letters if the user has pressed play and the
             * event is generated by the Jbutton array (length of the string is
             * one)
             */

            letters(command);
        }

    }

    /**
     * This method receives the data from JButtons and compares it to random
     * word. if it matches, the it is shown or else the user is prompted a
     * message for the wrong word entered
     *
     * @param command
     */
    public void letters(String command) {

        if (Word.contains(command.toLowerCase())) {
            for (int i = 0; i < RandomWord.length; i++) {
                if (command.toLowerCase().charAt(0) == RandomWord[i]) {
                    guesses[i] = command.toLowerCase().charAt(0);
                }
            }
        } else if (!Word.contains(command.toLowerCase())) {
            JOptionPane.showMessageDialog(null, "Sorry " + command + " is a Wrong Guess", "Wrong Guess!!", JOptionPane.ERROR_MESSAGE);
            AlienParts++;
        }
        NumberOfGuesses += command;
        if (AlienParts < 6 && !winner()) {
            NumberOfGuesses += ",";
        }
        repaint();
    }

    /**
     * This method generates the dashes('_') on the Screen so that it is visible
     * to the user
     */
    private void play() {

        // store random word
        CurrentState = 1;
        Word = getword();
        switch (Valuelist[0]) {
            case "Easy":
                while (Word.length() != 4) {
                    Word = getword();
                }
                break;
            case "Normal":
                while (Word.length() != 8) {
                    Word = getword();
                }
                break;
            case "Hard":
                while (Word.length() != 12) {
                    Word = getword();
                }
                break;
            default:
                break;
        }
        RandomWord = Word.toCharArray();
        guesses = new char[RandomWord.length];
        for (int i = 0; i < guesses.length; i++) {//populate the array with dashes first
            guesses[i] = '_';
        }

    }

}
