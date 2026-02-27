import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame implements ActionListener {

    JTextField display;

    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    String[] buttonText = {
            "7", "8", "9", "/", "√",
            "4", "5", "6", "*", "x²",
            "1", "2", "3", "-", "log",
            "0", ".", "=", "+", "Clear all",
            "sin", "cos", "tan", "(", ")"
    };

    JButton[] buttons = new JButton[25];

    // Colors
    Color bgColor = new Color(20, 20, 20);
    Color btnColor = new Color(40, 40, 40);
    Color hoverColor = new Color(60, 60, 60);
    Color operatorColor = new Color(255, 140, 0);
    Color textColor = Color.WHITE;

    public ScientificCalculator() {

        // Frame
        setTitle("Scientific Calculator by Salam , Naeem and Danish");
        setSize(420, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(bgColor);

        // Display
        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(20, 20, 20));
        display.setForeground(Color.GREEN);
        display.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(operatorColor, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        add(display, BorderLayout.NORTH);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 8, 8));
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Buttons
        for (int i = 0; i < buttonText.length; i++) {

            buttons[i] = new JButton(buttonText[i]);

            styleButton(buttons[i]);

            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Button Styling
    private void styleButton(JButton btn) {

        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(btnColor);
        btn.setForeground(textColor);
        btn.setFocusPainted(false);

        btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Operator buttons
        if ("/ * - + = Clear all √ x² log sin cos tan".contains(btn.getText())) {
            btn.setBackground(operatorColor);
            btn.setForeground(Color.BLACK);
        }

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent e) {

                if ("/ * - + = Clear all √ x² log sin cos tan".contains(btn.getText())) {
                    btn.setBackground(operatorColor);
                } else {
                    btn.setBackground(btnColor);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        // Numbers
        if ((cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') || cmd.equals(".")) {
            display.setText(display.getText() + cmd);
        }

        // Clear
        else if (cmd.equals("Clear all")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }

        // Operators
        else if (cmd.equals("+") || cmd.equals("-")
                || cmd.equals("*") || cmd.equals("/")) {

            num1 = Double.parseDouble(display.getText());
            operator = cmd;
            display.setText("");
        }

        // Equals
        else if (cmd.equals("=")) {

            num2 = Double.parseDouble(display.getText());

            switch (operator) {

                case "+":
                    result = num1 + num2;
                    break;

                case "-":
                    result = num1 - num2;
                    break;

                case "*":
                    result = num1 * num2;
                    break;

                case "/":
                    if (num2 != 0)
                        result = num1 / num2;
                    else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            operator = "";
        }

        // Square Root
        else if (cmd.equals("√")) {
            double x = Double.parseDouble(display.getText());
            display.setText(String.valueOf(Math.sqrt(x)));
        }

        // Square
        else if (cmd.equals("x²")) {
            double x = Double.parseDouble(display.getText());
            display.setText(String.valueOf(x * x));
        }

        // Log
        else if (cmd.equals("log")) {
            double x = Double.parseDouble(display.getText());
            display.setText(String.valueOf(Math.log10(x)));
        }

        // Sin
        else if (cmd.equals("sin")) {
            double x = Double.parseDouble(display.getText());
            display.setText(String.valueOf(Math.sin(Math.toRadians(x))));
        }

        // Cos
        else if (cmd.equals("cos")) {
            double x = Double.parseDouble(display.getText());
            display.setText(String.valueOf(Math.cos(Math.toRadians(x))));
        }

        // Tan
        else if (cmd.equals("tan")) {
            double x = Double.parseDouble(display.getText());
            display.setText(String.valueOf(Math.tan(Math.toRadians(x))));
        }

        // Brackets
        else if (cmd.equals("(") || cmd.equals(")")) {
            display.setText(display.getText() + cmd);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ScientificCalculator();
        });
    }
}
