import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a modern calculator with a graphical user interface (GUI).
 */
public class ModernCalculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private double firstNumber;
    private String operator;

    /**
     * Constructor to initialize the calculator GUI.
     */
    public ModernCalculator() {
        setTitle("Modern Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");

        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this);
            buttonPanel.add(operatorButtons[i]);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Performs the arithmetic operation based on the selected operator.
     *
     * @param operator The operator selected by the user.
     * @param num1     The first number.
     * @param num2     The second number.
     * @return The result of the arithmetic operation.
     */
    private double performOperation(String operator, double num1, double num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
            default:
                throw new IllegalArgumentException("Invalid operator.");
        }
    }

    /**
     * Handles the button click events.
     *
     * @param e The ActionEvent object representing the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String buttonText = button.getText();

            if (buttonText.matches("[0-9]")) {
                displayField.setText(displayField.getText() + buttonText);
            } else if (buttonText.matches("[+\\-*/]")) {
                firstNumber = Double.parseDouble(displayField.getText());
                operator = buttonText;
                displayField.setText("");
            } else if (buttonText.equals("=")) {
                double secondNumber = Double.parseDouble(displayField.getText());
                double result = performOperation(operator, firstNumber, secondNumber);
                displayField.setText(String.valueOf(result));
            } else if (buttonText.equals("C")) {
                displayField.setText("");
            }
        }
    }

    /**
     * Main method to start the calculator application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernCalculator());
    }
}