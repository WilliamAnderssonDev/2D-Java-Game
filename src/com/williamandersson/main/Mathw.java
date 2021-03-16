package com.williamandersson.main;

public class Mathw
{
	public static int clamp(int val, int min, int max)
	{
		if(val >= max)
		{
			return val = max;
		}
		else if(val <= min)
		{
			return val = min;
		}
		else
		{
			return val;
		}
	}
}
