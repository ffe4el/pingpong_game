package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


class MyPanel extends JPanel implements KeyListener{
	Ball ball;
	Racket racket_player, racket_com;
	
	public void initialize() {
		this.addKeyListener(this);
		this.requestFocus();
		this.setFocusable(true);
		
		ball = new Ball();
		racket_player = new Racket(15,250,30,80,15,Color.green, "me");
//		racket_player = new Racket(15,250,30,480,10,Color.pink, "me");
		racket_com = new Racket(670,250,30,80,15,Color.red, "you");
		
		MyThread t = new MyThread();
		t.start();
	}
	
	public void checkCollision() {
		// player1가 공을 잡음
		if((ball.x >= racket_player.x && ball.x <= racket_player.x + racket_player.w) &&
			(ball.y >= racket_player.y && ball.y <= racket_player.y + racket_player.h )) {
			ball.dx *= -1;
			ball.x = racket_player.x + racket_player.w;
		}
		
		// player2가 공을 잡음
		if((ball.x >= racket_com.x && ball.x <= racket_com.x + racket_com.w) &&
				(ball.y >= racket_com.y && ball.y <= racket_com.y + racket_com.h )) {
			ball.dx *= -1;
			ball.x = racket_com.x - racket_com.w;
		}
	}
	
	public void win() {
		if(ball.x < racket_player.x - racket_player.w) {
			ball = new Ball();
			racket_com.score++;
		}
		if(ball.x > racket_com.x + racket_com.w) {
			ball = new Ball();
			racket_player.score++;
		}
	}

	//속도변환하는 함수
	public void ChangeValue(){
		if(racket_com.score > 10){
			racket_com.speed = 7;
			racket_com.color = Color.magenta;
		}
		if(racket_com.score > 15){
			racket_com.speed = 3;
			racket_com.color = Color.ORANGE;
			racket_com.h = 50;
		}
		if(racket_player.score > 10) {
			racket_player.speed = 7;
			racket_player.color = Color.cyan;
		}
		if(racket_player.score > 15) {
			racket_player.speed = 3;
			racket_player.color = Color.yellow;
			racket_player.h = 50;
		}
	}
	
	class MyThread extends Thread {
		public void run() {
			while (true) {
				ball.move();
//				racket_com.comMove(ball);
				checkCollision();
				win();
				ChangeValue();
				
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//Player1
		if (e.getKeyCode() == KeyEvent.VK_W) {
			racket_player.move(-5);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			racket_player.move(5);
		}

		//Player2
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			racket_com.move(-5);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			racket_com.move(5);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(45, 0, 624, 450); // game board
		ball.draw(g);
		racket_player.draw(g);
		racket_com.draw(g);
		g.setColor(Color.pink);
		g.drawString(racket_player.score + "", 5, 20);
		g.drawString(racket_com.score + "", 680, 20);
		g.drawString("[Player1: W/S] vs [Player2: Up/Down]", 200, 500);
	}
}

public class GameFrame extends JFrame {
	MyPanel myPanel;
	
	public GameFrame() {
		setTitle("Ping Pong Game");
		setSize(730, 550);
		setLocation(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myPanel = new MyPanel();
		add(myPanel);
		myPanel.initialize();
		
		setVisible(true);
	}
}

