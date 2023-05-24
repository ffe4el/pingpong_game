package main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	int x, y;
	int w, h;
	int dx, dy;
	
	public Ball() {
		x = 350; y = 250;
		w = 25; h = 25;
		dx = -4; dy = 4;
	}

	public void move() {
		x += dx;
		y += dy;
		if (y < 10 || y > 440)
			dy *= -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, w, h);
	}
	
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}
}
