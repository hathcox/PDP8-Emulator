package edu.uat.csc263.su11045.ixk.test.mastermind;

import org.junit.Test;

import edu.uat.csc263.su11045.ixk.mastermind.CodeBoard;
import edu.uat.csc263.su11045.ixk.mastermind.CodePeg;
import edu.uat.csc263.su11045.ixk.mastermind.KeyPeg;

import static org.junit.Assert.assertEquals;


public class MastermindTest {
	@Test
	public void testCodeBoardAddCodePeg()
	{
		CodeBoard temp = new CodeBoard();
		assertEquals("Valid Color" , true , temp.addCodePeg("blue"));	
		assertEquals("Not Valid Color" , false , temp.addCodePeg("potato"));	
	}
	@Test
	public void testCodeBoardValidColor()
	{
		CodeBoard temp = new CodeBoard();
		assertEquals("Valid Color" , true , temp.validColor("red"));
		assertEquals("Not Valid Color" , false , temp.validColor("chicken"));
	}
	@Test
	public void testCodePegGetColor()
	{
		CodePeg temp = new CodePeg();
		temp.setColor("red");
		assertEquals("Get the Color Red" , "red" , temp.getColor());
	}
	@Test
	public void testKeyPegGetColor()
	{
		KeyPeg temp = new KeyPeg();
		temp.setColor("white");
		assertEquals("Get the Color White" , "white" , temp.getColor());
	}
}
