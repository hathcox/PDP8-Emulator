package edu.uat.csc263.su11045.ixk.mastermind;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import edu.uat.csc263.su11045.ixk.observerpattern.Observable;
import edu.uat.csc263.su11045.ixk.observerpattern.Observer;

public class Board implements Observable
{
	List <Observer> observers = new ArrayList <Observer>();
	private List<GuessBoard> game = new ArrayList<GuessBoard>();
	private List<CodePeg> winningCombo = new ArrayList<CodePeg>();
	private List<CodePeg> currentGuess = new ArrayList<CodePeg>();
	private List<String> possibleColors = new ArrayList<String>();
	private int numberOfGuesses = 8;
	
	public void start()
	{
		addGuessBoard(8);
		createCombination(4);
	}
	
	public void guess()
	{
		if(numberOfGuesses >= 0)
		{
			GuessBoard tempGuessBoard = game.get(numberOfGuesses - 1);
			tempGuessBoard.buildCodeBoard();
			CodeBoard tempCodeBoard = tempGuessBoard.getCodeBoard();
			KeyBoard tempKeyBoard = tempGuessBoard.getKeyBoard();
			currentGuess = tempCodeBoard.getCodePegs();
			tempKeyBoard.calculateKeyPeg(currentGuess() , getWinningCombo());
			numberOfGuesses--;
		}
	}
	public void guessGUI(List<String> colors)
	{
		GuessBoard tempGuessBoard = game.get(numberOfGuesses - 1);
		tempGuessBoard.buildCodeBoardGUI(colors);
		CodeBoard tempCodeBoard = tempGuessBoard.getCodeBoard();
		KeyBoard tempKeyBoard = tempGuessBoard.getKeyBoard();
		currentGuess = tempCodeBoard.getCodePegs();
		tempKeyBoard.calculateKeyPeg(currentGuess() , getWinningCombo());
		numberOfGuesses--;
	}
	
	public List<String> getWinningCombo()
	{
		List<String> tempList = new ArrayList<String>();
		for(int index = 0; index != winningCombo.size(); index++ )
		{
			CodePeg peg = winningCombo.get(index);
			tempList.add(peg.getColor());
		}
		return  tempList;
	}
	
	public void printWin()
	{
		for(int i = 0; i!= 4; i++)
		{	
			CodePeg peg = winningCombo.get(i);
			System.out.print(peg.getColor()+ " ");
		}
		System.out.println();
	}
	
	public List<String> currentGuess()
	{
		List<String> tempList = new ArrayList<String>();
		for(int index = 0; index != currentGuess.size(); index++ )
		{
			CodePeg peg = currentGuess.get(index);
			tempList.add(peg.getColor());
		}
		return  tempList;
	}
	
	public void addGuessBoard(int numberOfTrys)
	{
		for(int index = 0; index != numberOfTrys; index++)
		{
			GuessBoard guess = new GuessBoard();
			game.add(guess);
		}
	}
	
	public String getCurrentKeyBoard()
	{
		GuessBoard tempGuessBoard = game.get(numberOfGuesses);
		KeyBoard tempKeyBoard = tempGuessBoard.getKeyBoard();
		List<String> tempList = tempKeyBoard.getKeyPegColors();
		String tempString = "";
		for(int index = 0; index!= tempList.size(); index++)
		{
			tempString += tempList.get(index);
		}
		return tempString;
	}
	
	public void clearGuessBoard()
	{
		game.removeAll(game);
	}
	
	private void createCombination(int numberOfCodePegs)
	{
		fillColors();
		for (int pegs = numberOfCodePegs; pegs !=0; pegs--)
		{
			winningCombo.add(randomCodePeg());
			}
	}
	private void fillColors()
	{
		possibleColors.add("blue");
		possibleColors.add("red");
		possibleColors.add("yellow");
		possibleColors.add("green");
		possibleColors.add("purple");
		possibleColors.add("orange");
	}
	private CodePeg randomCodePeg()
	{
		CodePeg peg = new CodePeg();
		Random generator = new Random();
		int colorIndex = generator.nextInt(6); 
		String randomColor = possibleColors.get(colorIndex);
		peg.setColor(randomColor);
		return peg;
	}

	public void addObserver(Observer observer) 
	{
		observers.add(observer);	
	}

	public void updateObservers() 
	{
		for (Observer observer : observers)
		{
			observer.update();
		}
	}
}
