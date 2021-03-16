package com.williamandersson.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = -189064781151406456L;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = (int) Math.round((WIDTH / (16.0 / 9.0))); // Calculate width to get 16:9 aspect ratio
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	
	public Game()
	{
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		r = new Random();
		
		new Window(WIDTH, HEIGHT, "2D Java Game", this);
		
		hud = new HUD();
		
		//Add objects
		handler.addObject(new Player((WIDTH/2)-32, (HEIGHT/2)-32, ID.Player));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 48), r.nextInt(HEIGHT - 64), ID.BasicEnemy));	
	}

	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int deltaFrames = 0, tempFPS = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			
			if(running)
			{
				render();
			}
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				tempFPS = frames - deltaFrames;
				deltaFrames = frames;
				timer += 1000;
				//System.out.println("FPS: " + tempFPS);
			}
		}
		
		stop();
	}
	
	private void tick()
	{
		handler.tick();
		hud.tick();
	}
	
	private void render()
	{
		BufferStrategy  bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[])
	{
		new Game();
	}
}
