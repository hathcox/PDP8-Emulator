package edu.uat.csc263.su11045.ixk.mastermindconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.uat.csc263.su11045.ixk.mastermind.*;

public class Console {

	/**
	 * MasterMind
	 */
	public static void main(String[] args) 
	{
		System.out.println("Welcome to.....");
		greeting();
		run();
		System.out.println("MasterMind is now closing!");		
	}

	public static void fireworks()
	{
		System.out.println("                                 .''.");
		System.out.println("       .\'\'.             *\'\'*    :_\\/_:     . ");
		System.out.println("      :_\\/_:   .    .:.*_\\/_*   : /\\ :  .\'.:.\'.");
		System.out.println("  .\'\'.: /\\ : _\\(/_  \':\'* /\\ *  : \'..\'.  -=:o:=-");
		System.out.println(" :_\\/_:\'.:::. /)\\*\'\'*  .|.* \'.\\\'/.\'_\\(/_\'.\':\'.\'");
		System.out.println(" : /\\ : :::::  \'*_\\/_* | |  -= o =- /)\\    \'  *");
		System.out.println("  \'..\'  \':::\'   * /\\ * |\'|  .\'/.\\\'.  \'._____");
		System.out.println("      *        __*..* |  |     :      |.   |\' .---\"|");
		System.out.println("       _*   .-\'   \'-. |  |     .--\'|  ||   | _|    |");
		System.out.println("    .-\'|  _.|  |    ||   \'-__  |   |  |    ||      |");
		System.out.println("    |\' | |.    |    ||       | |   |  |    ||      |");
		System.out.println(" ___|  \'-\'     \'    \"\"       \'-\'   \'-.\'    \'`      |____");
		System.out.println("          ! !  Congrats  You  Win ! !");
	}
	
	public static void greeting()
	{
		System.out.println("  __  __           _             __  __ _           _  ");
		System.out.println(" |  \\/  |         | |           |  \\/  (_)         | | ");
		System.out.println(" | \\  / | __ _ ___| |_  ___ _ __| \\  / |_ _ __   __| | ");
		System.out.println(" | |\\/| |/ _` / __| __|/ _ \\ '__| |\\/| | | '_ \\ / _` | ");
		System.out.println(" | |  | | (_| \\__ \\ |_|  __/ |  | |  | | | | | | (_| | ");
		System.out.println(" |_|  |_|\\__,_|___/\\__|\\___|_|  |_|  |_|_|_| |_|\\__,_| ");
		System.out.println("                                      By: Iggy Krajci");
	}
	
    public static String Input(String question)
    {
            BufferedReader reader; 
            String response = "null";
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(question);
            try
            {
                response = reader.readLine(); 
            }
            catch (IOException ioe)
            {
                System.out.println("An unexpected error occured.");
            }
              return response;
        }
	
	public static void run()
	{
		boolean gameNotFinished = true;
		while(gameNotFinished)
		{
			String UserInput = Input("Valid Options are: NewGame , Stats , Exit\n");
			if(UserInput.equalsIgnoreCase("newgame"))
			{
				newGame();			
			}
			if(UserInput.equalsIgnoreCase("stats"))
			{
				//stats();
				System.out.println("Not yet implemented, sorry!");
			}
			if(UserInput.equalsIgnoreCase("exit"))
			{
				gameNotFinished = false;
			}
			else
			{
				System.out.println("Please Enter a Valid Command!");
			}
		}
	}
	
	public static void newGame()
	{
		Board newGame = new Board();
		newGame.start();
		newGame.guess();
		while (!newGame.getWinningCombo().equals(newGame.currentGuess()))
		{
		System.out.println("Sorry thats the wrong combination");
		System.out.println("The KeyPeg response is: ");
		System.out.println(newGame.getCurrentKeyBoard());
		newGame.guess();
		}
		fireworks();
	}

	
	
	
	
	
}