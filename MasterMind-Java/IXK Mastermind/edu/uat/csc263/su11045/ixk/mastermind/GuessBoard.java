package edu.uat.csc263.su11045.ixk.mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class GuessBoard {
	private CodeBoard codeBoard = new CodeBoard();
	private KeyBoard keyBoard = new KeyBoard();
	private int pegNumber = 4;
	
	public void setPegNumber(int newNumber)
	{
		pegNumber = newNumber;
	}
	
	public void buildCodeBoard()
	{
		for(int index = 0; index != pegNumber; index++)
		{
			System.out.println("Valid colors are: red blue yellow orange green purple");
			boolean allowedColor = false;
			while(!allowedColor)
			{
				String color = Input("Please enter a color: ");
				allowedColor = codeBoard.addCodePeg(color);
			}
		}
	}
	
	public void buildCodeBoardGUI(List<String> colors)
	{
		for (String color : colors) 
		{
			codeBoard.addCodePeg(color);
		}
	}
	
	public CodeBoard getCodeBoard(){
		return codeBoard;
	}
	
	public KeyBoard getKeyBoard()
	{
		return keyBoard;
	}
	
    private static String Input(String question){
        BufferedReader reader; 
        String response = "null";
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(question);
        try{
            response = reader.readLine(); 
          }
          catch (IOException ioe){
            System.out.println("An unexpected error occured.");
        }
          return response;
    }
}
