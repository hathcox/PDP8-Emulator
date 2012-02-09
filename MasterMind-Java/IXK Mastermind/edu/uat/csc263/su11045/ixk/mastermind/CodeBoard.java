package edu.uat.csc263.su11045.ixk.mastermind;

import java.util.*;


public class CodeBoard {
	private List<CodePeg> codePegList = new ArrayList<CodePeg>();
	
	public boolean addCodePeg(String color)
	{
		CodePeg peg = new CodePeg();
		if(validColor(color))
		{
			peg.setColor(color);
			codePegList.add(peg);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void removeKeyPeg(int index)
	{
		codePegList.remove(index);
	}
	
	public void clearKeyBoard()
	{
		codePegList.removeAll(codePegList);
	}
	
	public List<String> getCodePegColors()
	{
		List<String> tempList = new ArrayList<String>();
		
		for(int index = 0; index != codePegList.size(); index++)
		{
			CodePeg temp = codePegList.get(index);
			tempList.add(temp.getColor());
		}
		return tempList;
	}
	
	public List<CodePeg> getCodePegs()
	{
		return codePegList;
	}
	
	public boolean validColor(String colorToBeTested)
	{
		boolean finalValue = false;
		if (colorToBeTested.equalsIgnoreCase("blue"))
		{
			finalValue = true;
		}
		if (colorToBeTested.equalsIgnoreCase("red"))
		{
			finalValue = true;
		}
		if (colorToBeTested.equalsIgnoreCase("yellow"))
		{
			finalValue = true;
		}
		if (colorToBeTested.equalsIgnoreCase("orange"))
		{
			finalValue = true;
		}
		if (colorToBeTested.equalsIgnoreCase("green"))
		{
			finalValue = true;
		}
		if (colorToBeTested.equalsIgnoreCase("purple"))
		{
			finalValue = true;
		}
		return finalValue;
	}
}
