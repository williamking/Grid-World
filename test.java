import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class test
{
    private static void showGUI()
    {
       JFrame frame = new JFrame("HelloWorld");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	   //Layout
	   Container ct = frame.getContentPane();
	   ct.setLayout(new GridLayout(2, 5, 2, 2));

	   //Close Action
       SignAction signAct = new SignAction();
       add.addActionListener(signAct);
       subs.addActionListener(signAct);
       div.addActionListener(signAct);
       cross.addActionListener(signAct);
       CalAction cal = new CalAction();
       calculate.addActionListener(cal);

	   //Add button
	   ct.add(num1);
	   ct.add(sign);
	   ct.add(num2);
	   ct.add(equal);
	   ct.add(result);
	   ct.add(add);
	   ct.add(subs);
	   ct.add(cross);
	   ct.add(div);
	   ct.add(calculate);

	   //show
	   frame.pack();
	   frame.setVisible(true);
    }

	public static void main(String[] args) {
		Runnable tr = new Runnable() {
			public void run() {
				showGUI();
			}
		};
		javax.swing.SwingUtilities.invokeLater(tr);
	}

	private static class SignAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
		    String signChar;
			JButton btn = (JButton)event.getSource();
			signChar = btn.getText();
		    sign.setText(signChar);	
		}
	}

	private static class CalAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String n1 = num1.getText();
			String n2 = num2.getText();
			float a = Float.parseFloat(n1);
			float b = Float.parseFloat(n2);
			String calSign = sign.getText();
			if (calSign == "+") {
				a += b;
			}
			if (calSign == "-") {
				a -= b;
			}
			if (calSign == "*") {
				a *= b;
			}
			if (calSign == "/") {
				a /= b;
			}
			DecimalFormat round = new DecimalFormat(".00");
			String resultText = round.format(a);
			result.setText(resultText);
		}
	}

       //Input
	private static JTextField num1 = new JTextField();
	private static JTextField num2 = new JTextField();

	   //Button
	private static JButton add = new JButton("+");
    private static JButton subs= new JButton("-");
    private static JButton cross = new JButton("*");
	private static JButton div = new JButton("/");
	private static JButton calculate = new JButton("OK");

	   //Sign and Result Field
	private static JLabel sign = new JLabel();
	private static JLabel result = new JLabel();
	private static JLabel equal = new JLabel("=", JLabel.CENTER);
}
