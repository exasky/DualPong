/**
 * 
 */
package com.ups.dualpong.graphic;

/**
 * @author SERIN Kevin
 * 
 */
public class CollisionDetector {
	private int limitRight;
	private int limitLeft;
	private GraphicRacket racket;
	private GraphicBall ball;

	public CollisionDetector(GraphicBall ball, GraphicRacket racket,
			int limitRight, int limitLeft) {
		this.ball = ball;
		this.racket = racket;
		this.limitLeft = limitLeft;
		this.limitRight = limitRight;
	}

	public boolean isCollisionWithRacket() {
		boolean isSameY = (ball.getY() + ball.getRadius()) >= racket.getY();

		boolean isSameX = (ball.getX() >= racket.getX() - racket.getSize())
				&& (ball.getX() <= racket.getX() + racket.getSize());

		return isSameX && isSameY;
	}

	public boolean icCollisionWithBottom() {
		return (ball.getY() >= racket.getY()) && (!isCollisionWithRacket());
	}

	public boolean isCollisionWithLeftLimit() {
		return (ball.getX() - ball.getRadius()) <= limitLeft;
	}

	public boolean isCollisionWithRightLimit() {
		return (ball.getX() + ball.getRadius()) >= limitRight;
	}

	public boolean isCollisionWithTop() {
		return ball.getY() <= 0;
	}

}
