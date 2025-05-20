package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a simple calculator with a graphical user interface (GUI).
 */
public class SimpleCalculatorUI extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton;
    private double firstNumber, secondNumber;
    private String operator;

    /**
     * Constructor to initialize the calculator UI.
     */
    public SimpleCalculatorUI() {
        setTitle("Simple Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        addButton = new JButton("+");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        subtractButton = new JButton("-");
        subtractButton.addActionListener(this);
        buttonPanel.add(subtractButton);

        multiplyButton = new JButton("*");
        multiplyButton.addActionListener(this);
        buttonPanel.add(multiplyButton);

        divideButton = new JButton("/");
        divideButton.addActionListener(this);
        buttonPanel.add(divideButton);

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Performs the arithmetic operation based on the selected operator.
     */
    private void performOperation() {
        switch (operator) {
            case "+":
                inputField.setText(String.valueOf(firstNumber + secondNumber));
                break;
            case "-":
                inputField.setText(String.valueOf(firstNumber - secondNumber));
                break;
            case "*":
                inputField.setText(String.valueOf(firstNumber * secondNumber));
                break;
            case "/":
                if (secondNumber != 0) {
                    inputField.setText(String.valueOf(firstNumber / secondNumber));
                } else {
                    inputField.setText("Error: Division by zero");
                }
                break;
        }
    }

    /**
     * Handles button clicks and performs the corresponding actions.
     *
     * @param e The ActionEvent representing the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            operator = "+";
            firstNumber = Double.parseDouble(inputField.getText());
            inputField.setText("");
        } else if (e.getSource() == subtractButton) {
            operator = "-";
            firstNumber = Double.parseDouble(inputField.getText());
            inputField.setText("");
        } else if (e.getSource() == multiplyButton) {
            operator = "*";
            firstNumber = Double.parseDouble(inputField.getText());
            inputField.setText("");
        } else if (e.getSource() == divideButton) {
            operator = "/";
            firstNumber = Double.parseDouble(inputField.getText());
            inputField.setText("");
        } else if (e.getSource() == equalsButton) {
            secondNumber = Double.parseDouble(inputField.getText());
            performOperation();
        }
    }

    /**
     * Main method to start the calculator application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleCalculatorUI();
            }
        });
    }
}