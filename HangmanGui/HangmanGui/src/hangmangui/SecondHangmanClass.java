/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmangui;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondHangmanClass extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private String[] ArrayOfWords;
    private Random radomWordGenerator = new Random();
    private JTextField textField;
    private JLabel hintLabel1, hintLabel2;
    private JButton submit, clear;
    private String Word;

    public SecondHangmanClass() {
        generateWord();
        mainPanel = new JPanel();
        mainPanel.setSize(new Dimension(700, 700));
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(200, 580, 300, 30);
        mainPanel.add(textField);

        hintLabel1 = new JLabel();
        hintLabel2 = new JLabel();

        String[] textarray = getHint();

        hintLabel1.setText("Hint ===========> First Word : " + textarray[0]);
        hintLabel2.setText("Hint ===========> Last Word : " + textarray[1]);
        hintLabel1.setBounds(250, 500, 200, 20);
        hintLabel2.setBounds(hintLabel1.getX(), hintLabel1.getY() + hintLabel1.getHeight() + 10, 200, 20);
        mainPanel.add(hintLabel1);
        mainPanel.add(hintLabel2);

        submit = new JButton("Submit");
        submit.setBounds(textField.getX() + 30, textField.getHeight() + textField.getY() + 5, 100, 30);
        clear = new JButton("Clear");
        clear.setBounds(submit.getX() + submit.getWidth() + 30, textField.getHeight() + textField.getY() + 5, 100, 30);

        mainPanel.add(clear);
        mainPanel.add(submit);
        submit.addActionListener(this);
        clear.addActionListener(this);

        this.add(mainPanel);
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public String[] getArrayOfWords() {
        return ArrayOfWords;
    }

    public void setArrayOfWords(String[] ArrayOfWords) {
        this.ArrayOfWords = ArrayOfWords;
    }

    public Random getRadomWordGenerator() {
        return radomWordGenerator;
    }

    public void setRadomWordGenerator(Random radomWordGenerator) {
        this.radomWordGenerator = radomWordGenerator;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JLabel getHintLabel1() {
        return hintLabel1;
    }

    public void setHintLabel1(JLabel hintLabel1) {
        this.hintLabel1 = hintLabel1;
    }

    public JLabel getHintLabel2() {
        return hintLabel2;
    }

    public void setHintLabel2(JLabel hintLabel2) {
        this.hintLabel2 = hintLabel2;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

    public JButton getClear() {
        return clear;
    }

    public void setClear(JButton clear) {
        this.clear = clear;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String Word) {
        this.Word = Word;
    }

    // generate a random word and return via char array
    public String generateWord() {
        ArrayOfWords = readFromText();

        int number = ArrayOfWords.length;
        int random = radomWordGenerator.nextInt(number);
        Word = ArrayOfWords[random];

        return Word;
    }

    /**
     *
     * @param g
     */
    public String[] getHint() {
        System.out.println("hint word : " + Word);
        String[] hintlist = new String[2];
        hintlist[0] = String.valueOf(Word.charAt(0));
        hintlist[1] = String.valueOf(Word.charAt(Word.length() - 1));
        return hintlist;
    }

    public String[] readFromText() {
        BufferedReader reader = null;
        HangmanClass obj = new HangmanClass();
        String filePath = obj.getFilePath();

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

        return wordList.toArray(new String[wordList.size()]);

    }

    /**
     * This module checks of the user has win the game or not. If user wins, it
     * prints out the message in both the cases, i.e. Winning the game or
     * Loosing the game. After winning or loosing the game, it exits the whole
     * game.
     */
    public void gameWin() {
        if (CheckOption(textField.getText())) {
            JOptionPane.showMessageDialog(null, "You have Entered The Correct Option Succcessfully!!!!!!", "You have Won", JOptionPane.INFORMATION_MESSAGE);
            System.exit(-1);
        } else {
            JOptionPane.showConfirmDialog(null, "Yor have Entered incorrect Option", "Sorry", JOptionPane.WARNING_MESSAGE);
            System.exit(-1);
        }
    }

    /**
     * This method checks if the text from the user entered that is passed onto
     * this function's Parameter, is equal to the word guessed or not!
     *
     * @param text datatype(String)
     * @return boolean
     */
    public boolean CheckOption(String text) {
        System.out.println(Word);
        return text.equalsIgnoreCase(Word);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(submit)) {
            gameWin();
        } else if (ae.getSource().equals(clear)) {
            textField.setText("");
        }
    }

}
