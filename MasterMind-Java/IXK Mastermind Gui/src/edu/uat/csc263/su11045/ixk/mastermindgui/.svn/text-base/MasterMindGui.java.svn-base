package edu.uat.csc263.su11045.ixk.mastermindgui;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import edu.uat.csc263.su11045.ixk.mastermind.Board;
import edu.uat.csc263.su11045.ixk.observerpattern.Observer;


public class MasterMindGui extends JFrame implements Observer
{
	private static final long serialVersionUID = 1310328089762813264L;
	
	private JTextField codeBoardOne = new JTextField();
	private JTextField codeBoardTwo = new JTextField();
	private JTextField codeBoardThree = new JTextField();
	private JTextField codeBoardFour = new JTextField();
	private JTextField codeBoardFive = new JTextField();
	private JTextField codeBoardSix = new JTextField();
	private JTextField codeBoardSeven = new JTextField();
	private JTextField codeBoardEight = new JTextField();
	
	private JTextField keyBoardOne = new JTextField();
	private JTextField keyBoardTwo = new JTextField();
	private JTextField keyBoardThree = new JTextField();
	private JTextField keyBoardFour = new JTextField();
	private JTextField keyBoardFive = new JTextField();
	private JTextField keyBoardSix = new JTextField();
	private JTextField keyBoardSeven = new JTextField();
	private JTextField keyBoardEight = new JTextField();

	private JTextField guessNumberFeild = new JTextField();

	private JPanel choicePanel = new JPanel();
	private JPanel labelPanel = new JPanel();
	private JPanel boardPanel = new JPanel();
	
	private JMenuBar menu = new JMenuBar();
	private JMenu masterMindMenu = new JMenu("MasterMind");
	
	private static Board gameBoard = new Board();
	
	private int guessNumber = 0;
	private int maxGuess = 8;
	
	private String firstChoice = "red";
	private String secondChoice = "red";
	private String thirdChoice= "red";
	private String fourthChoice = "red";

	
	public static void main(String[] args) {
		MasterMindGui masterMindGui = new MasterMindGui();
		gameBoard.start();
		masterMindGui.setupGUI();
		masterMindGui.setSize(800, 475);
		masterMindGui.setVisible(true);
	}

	private void setupGUI() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		buildComboBox(choicePanel);
		buildButton(choicePanel);
		buildMenu();
		buildLabels();
		buildBoard();
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		boardPanel.setLayout(new GridLayout(18,0));
		container.add(choicePanel , BorderLayout.WEST);
		container.add(labelPanel , BorderLayout.EAST);
		container.add(boardPanel , BorderLayout.NORTH);
		updateGuessNumber();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void buildComboBox(JPanel panel) 
	{
		String choices[] = {"red" , "orange" , "blue" , "purple" , "yellow" , "green"};
	    final JComboBox combo1 = new JComboBox(choices);
	    final JComboBox combo2 = new JComboBox(choices);
	    final JComboBox combo3 = new JComboBox(choices);
	    final JComboBox combo4 = new JComboBox(choices);
	    combo1.addItemListener(new ItemListener()
	    {
	        public void itemStateChanged(ItemEvent ie)
	        {
	          String tempString = (String)combo1.getSelectedItem();
	          firstChoice = tempString;
	        }
	      });
	    combo2.addItemListener(new ItemListener()
	    {
	        public void itemStateChanged(ItemEvent ie)
	        {
	          String tempString = (String)combo2.getSelectedItem();
	          secondChoice = tempString;

	        }
	      });
	    combo3.addItemListener(new ItemListener()
	    {
	        public void itemStateChanged(ItemEvent ie)
	        {
	          String tempString = (String)combo3.getSelectedItem();
	          thirdChoice = tempString;

	        }
	      });
	    combo4.addItemListener(new ItemListener()
	    {
	        public void itemStateChanged(ItemEvent ie)
	        {
	          String tempString = (String)combo4.getSelectedItem();
	          fourthChoice = tempString;

	        }
	      });
	    panel.add(combo1);
	    panel.add(combo2);
	    panel.add(combo3);
	    panel.add(combo4);
	}

	private void buildButton(JPanel panel) 
	{
		JButton addButton = new JButton("Guess");
		panel.add(addButton);
		addButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						guess();
					} 
					catch (Exception e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		);
	}
	
	private void buildLabels()
	{
		JLabel numberOfGuessesLabel = new JLabel("Number of Guesses Left: ");
		labelPanel.add(numberOfGuessesLabel);
		labelPanel.add(guessNumberFeild);
	}
	
	private void buildMenu()
	{
		menu.add(masterMindMenu);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						System.exit(0);
					}
				}
			);
		masterMindMenu.add(exit);
		setJMenuBar(menu);
	}
	
	private void buildBoard()
	{
		boardPanel.add(new JLabel("Guesses:"));

		boardPanel.add(codeBoardEight);
		boardPanel.add(codeBoardSeven);
		boardPanel.add(codeBoardSix);
		boardPanel.add(codeBoardFive);
		boardPanel.add(codeBoardFour);
		boardPanel.add(codeBoardThree);
		boardPanel.add(codeBoardTwo);
		boardPanel.add(codeBoardOne);

		boardPanel.add(new JLabel("KeyPegs:"));
		
		boardPanel.add(keyBoardOne);
		boardPanel.add(keyBoardTwo);
		boardPanel.add(keyBoardThree);
		boardPanel.add(keyBoardFour);
		boardPanel.add(keyBoardFive);
		boardPanel.add(keyBoardSix);
		boardPanel.add(keyBoardSeven);
		boardPanel.add(keyBoardEight);

	}

	private void guess() throws Exception
	{
		List<String> colors = new ArrayList<String>();
		colors.add(firstChoice);
		colors.add(secondChoice);
		colors.add(thirdChoice);
		colors.add(fourthChoice);
		if(guessNumber!=maxGuess)
		{
			if(!gameBoard.getWinningCombo().equals(colors))
			{
			gameBoard.guessGUI(colors);
			guessNumber++;
			update();
			}
			else
			{
				URL imageLocation = new URL("http://crowdfusion.myspacecdn.com/media/2011/03/29/pbj-time.gif");
				JOptionPane.showMessageDialog(null, "You Win YAY!!", "PBJ TIME!",  JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
				System.exit(0);
			}
		}
		else
		{
			URL imageLocation = new URL("http://204.45.110.156/bin/052010/1272964790_glenn-beck-crying.gif");
			JOptionPane.showMessageDialog(null, "You lost, Sorry!", "Gleb Beck cry's for you!",  JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
			System.exit(0);
		}
	}

	public void update() 
	{
		updateBoard();
		updateGuessNumber();
	}

	private void updateGuessNumber()
	{
		String temp = Integer.toString(maxGuess - guessNumber);
		guessNumberFeild.setText(temp);
	}

	private void updateBoard()
	{
		JTextField tempCodeField = (JTextField) boardPanel.getComponent(guessNumber);		
		tempCodeField.setText(firstChoice+"\n"+secondChoice+"\n"+thirdChoice+"\n"+fourthChoice);
		JTextField tempKeyField = (JTextField) boardPanel.getComponent(guessNumber+9);		
		tempKeyField.setText(gameBoard.getCurrentKeyBoard());

		
	}

}
