package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import main.GameFrame.*;
public class Racket {
	int x;
	int y;
	int w;
	int h;
	int speed;
	Color color;
	String name;
	int score;
	
	public Racket(int x, int y, int w, int h, int speed, Color color, String name) {
		this.x = x; this.y = y;
		this.w = w; this.h = h;
		this.speed = speed;
		this.color = color;
		this.name = name;
		this.score = 0;
	}
	
	public void move(int direction) {
//		double cspeed = ChangeValue();

		if (y+(direction*speed) > 375) { // limit for down bound
			y = 371;
		}
		else if (y+(direction*speed) < 10) { // limit for up bound
			y = 0;	
		}
		else {

			y += (direction*speed);
		}
	}
	
	public void comMove(Ball ball) {	
		int gap = ball.getY() - (y+h/2);
		Random rand = new Random();
		int randNum = rand.nextInt(1,9);	
		if(randNum != 3) {
			if(gap < 0) { // The ball is above the racket.
				if(gap*(-1) > speed) y -= speed;
				else y += gap;
			}
			else { // The ball is below the racket.
				if(gap > speed) y += speed;
				else y += gap;
			}
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.setFont(new Font("default", Font.PLAIN, 20));
		g.drawString(name, x+1, y+h/2);
	}
}
