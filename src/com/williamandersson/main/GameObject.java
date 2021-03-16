package com.williamandersson.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject
{
	protected int x, y; // Coordinates
	protected ID id; // What type of object the GameObject is
	protected Tag tag; // Give object a specific tag
	protected int velX, velY; // Speed
	
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x) { this.x = x; }
	
	public void setY(int y) { this.y = y; }
	
	public int getX() { return x; }
	
	public int getY() { return y; }
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setID(ID id) { this.id = id; }
	
	public ID getID() { return id; }
	
	public void setTag(Tag tag) { this.tag = tag; }
	
	public Tag getTag() { return tag; }
	
	public void setVelX(int velX) { this.velX = velX; }
	
	public void setVelY(int velY) { this.velY = velY; }
	
	public int getVelX() { return velX; }
	
	public int getVelY() { return velY; }
	
}