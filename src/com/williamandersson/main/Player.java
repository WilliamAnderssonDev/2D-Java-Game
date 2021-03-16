package com.williamandersson.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject
{
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		x = Mathw.clamp(x, 0, Game.WIDTH - 49);
		y = Mathw.clamp(y, 0, Game.HEIGHT - 72);
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
	
}
