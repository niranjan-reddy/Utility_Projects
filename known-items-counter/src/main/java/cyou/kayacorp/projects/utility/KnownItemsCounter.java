package cyou.kayacorp.projects.utility;

/*
 * KnownItemsCounter
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class KnownItemsCounter extends JFrame
        implements KeyListener,
        ActionListener
{
    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");

    static Integer total = 0;
    static Integer previousTotal;
    static Integer currentInput;
    static String message;

    public static void main(String[] args) {

        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        KnownItemsCounter frame = new KnownItemsCounter("Known Items Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        frame.addComponentsToPane();

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {

        JButton button = new JButton("Clear");
        button.addActionListener(this);

        typingArea = new JTextField(25);
        typingArea.addKeyListener(this);

        //Uncomment this if you wish to turn off focus
        //traversal.  The focus subsystem consumes
        //focus traversal keys, such as Tab and Shift Tab.
        //If you uncomment the following line of code, this
        //disables focus traversal and the Tab events will
        //become available to the key event listener.
        //typingArea.setFocusTraversalKeysEnabled(false);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(355, 145));

        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(button, BorderLayout.PAGE_END);
    }

    public KnownItemsCounter(String name) {
        super(name);
    }

    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        displayInfo(e, "KEY INPUT: ");
    }

    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }

    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
        displayArea.setText("");
        typingArea.setText("");

        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
        //Reset numeric values.
        total = 0;
        currentInput = 0;
    }

    //Reset the Text Area
    public void resetDisplayArea(){
        displayArea.setText("");
        typingArea.setText("");
        message = "";
    }

    /*
     * We have to jump through some hoops to avoid
     * trying to print non-printing characters
     * such as Shift.  (Not only do they not print,
     * but if you put them in a String, the characters
     * afterward won't show up in the text area.)
     */
    private void displayInfo(KeyEvent e, String keyStatus){
        resetDisplayArea();

        //total, currentInput
        try{
            currentInput = Integer.parseInt(Character.toString(e.getKeyChar()));
            total += currentInput;
        } catch (NumberFormatException numberFormatException){
            message = "Please Input NUMBERS Only!!!";
        }

        //You should only rely on the key char if the event
        //is a key typed event.
        int id = e.getID();
        String inputChar;
        char c = e.getKeyChar();
        inputChar = "Input Character/Digit = '" + c + "'";

        String preTotal = "Previous Total = " + previousTotal;
        previousTotal = total;

        String curTotal = "TOTAL =  " + total;

        String errorMessage = "Error Message : " + message;

        displayArea.append(keyStatus + newline
                + "    " + inputChar + newline
                + "    " + preTotal + newline
                + newline
                + "    " + curTotal + newline
                + newline
                + "    " + errorMessage + newline);
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }
}