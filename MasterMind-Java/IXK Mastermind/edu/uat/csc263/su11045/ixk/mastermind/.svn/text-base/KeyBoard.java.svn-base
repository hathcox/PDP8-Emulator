package edu.uat.csc263.su11045.ixk.mastermind;

import java.util.*;


public class KeyBoard {
	private List<KeyPeg> keyPegList = new ArrayList<KeyPeg>();
	
	public void calculateKeyPeg(List<String> userColors , List<String> correctColors)
	{
		for(int index = 0; index != userColors.size(); index++)
		{
			if(userColors.get(index).equals(correctColors.get(index)))
			{
				addKeyPeg("Black\n");
				String random = Integer.toString(index);
				userColors.set(index , (random + "a")) ;
				correctColors.set(index, (random + "b"));
			}
		}
		for(int index = 0; index != userColors.size(); index++)
		{
			if(correctColors.contains(userColors.get(index)))
			{
			addKeyPeg("White\n");
			}
		}
	}
	
	public void addKeyPeg(String color)
	{
		KeyPeg peg = new KeyPeg();
		peg.setColor(color);
		keyPegList.add(peg);
	}
	
	public void removeKeyPeg(int index)
	{
		keyPegList.remove(index);
	}
	
	public void clearKeyBoard()
	{
		keyPegList.removeAll(keyPegList);
	}
	
	public List<String> getKeyPegColors()
	{
		List<String> tempList = new ArrayList<String>();
		
		for(int index = 0; index != keyPegList.size(); index++)
		{
			KeyPeg temp = keyPegList.get(index);
			tempList.add(temp.getColor());
		}
		return tempList;
	}
	
	public List<KeyPeg> getKeyPegs()
	{
		return keyPegList;
	}
}
