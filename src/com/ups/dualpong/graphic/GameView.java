/**
 * 
 */
package com.ups.dualpong.graphic;

import com.ups.dualpong.engine.BallEngine;
import com.ups.dualpong.game.Ball;
import com.ups.dualpong.game.Gauge;
import com.ups.dualpong.game.Racket;
import com.ups.dualpong.interactions.InclinationListener;
import com.ups.dualpong.interactions.TouchListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author SERIN Kevin
 *
 */
public class GameView extends ImageView implements TouchListener, InclinationListener {
	private static final int TIME_REFRESH = 10;
	private Handler refreshHandler;
	private Runnable invalidatorRunnable;
	private GraphicBall ball;
	private GraphicGauge gauge;
	private GraphicRacket racket;
	private int limitLeft;
	private int limitRight;
	private boolean isTouching;
	private Integer width = null;
	private Integer height = null;
	private CollisionDetector collisionDetector;
	private boolean ballOnRacket;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.refreshHandler = new Handler();
		this.isTouching = false;
		this.ballOnRacket = false;
		this.invalidatorRunnable = new Runnable() {
			@Override
			public void run() {
				invalidate();
			}
		};
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		this.calculateGauge();
		changeBallPosition();
		
		drawGame(canvas);
		refreshHandler.postDelayed(this.invalidatorRunnable, TIME_REFRESH);
	}
	
	private void calculateGauge() {
		if(!ballOnRacket) {
			if(isTouching) {
				this.gauge.increase();
			} else {
				this.gauge.decrease();
			}
		}
		else {
			this.gauge.bigDecrease();
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {		
		if(this.width == null || this.height == null) {	// first initialization of the screen
			this.width = w;
			this.height = h;
			this.limitLeft = 40;
			this.limitRight = width - 5;
			this.initGraphicalElements();
		}
	}
	
	private void initGraphicalElements() {		
		this.gauge = new GraphicGauge(new Gauge((Gauge.MAX - Gauge.MIN) / 2 + Gauge.MIN));
		this.gauge.setLeft(10);
		this.gauge.setTop(50);
		this.gauge.setRight(30);
		this.gauge.setBottom(height-100);
		this.ball = new GraphicBall(new Ball(width / 2, 10, 0, 0));
		this.ball.setSpeed(5);
		this.ball.setAlpha(-90);
		this.racket = new GraphicRacket(new Racket(width / 2, (int) (0.2*width)));
		this.racket.setY(height-20);
		this.collisionDetector = new CollisionDetector(ball, racket, limitRight, limitLeft);
	}
	
	private void drawGame(Canvas canvas) {
		Paint background = new Paint();
		background.setColor(Color.BLACK);
		
		if(width != null && height != null) {
			canvas.drawRect(0, 0, width, height, background);
			drawBorders(canvas);
		}
		if(this.ball != null) {
			this.ball.drawOnCanvas(canvas);
		}
		if(this.gauge != null) {
			this.gauge.drawOnCanvas(canvas);
		}
		if(this.racket != null) {
			this.racket.drawOnCanvas(canvas);
		}
		
	}
	
	private void drawBorders(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		canvas.drawLine(limitLeft, 0, limitLeft, height, paint);
		canvas.drawLine(limitRight, 0, limitRight, height, paint);
	}
	
	@Override
	public void touchDown() {
		this.isTouching = true;
	}

	@Override
	public void touchUp() {
		this.isTouching = false;
		if(ballOnRacket) {
			this.ballOnRacket = false;
			this.ball.setSpeed(5 + (gauge.getCurrent()*15 / (Gauge.MAX - Gauge.MIN)));
		}
	}

	@Override
	public void inclineLeft(int value) {
		int newX = Math.max(this.racket.getX()-value, limitLeft + (this.racket.getSize()/2));
		this.racket.setX(newX);
	}

	@Override
	public void inclineRight(int value) {
		int newX = Math.min(this.racket.getX()+value, limitRight - (this.racket.getSize()/2));
		this.racket.setX(newX);
	}
	
	private void changeBallPosition() {
		if(!ballOnRacket) {
		
			float alpha;
			boolean collision = false;
			if(collisionDetector.isCollisionWithRacket()) {
				alpha = BallEngine.getNewAngleOnRacketBounce(ball.getX(), racket.getX(), racket.getX());
				ball.setAlpha(alpha);	
				collision = true;
				
				if(isTouching) {
					this.ballOnRacket = true;
				}
				else {
					this.calculateGauge();
				}
			}
			
			if(collisionDetector.isCollisionWithLeftLimit() || collisionDetector.isCollisionWithRightLimit()) {
				alpha = BallEngine.getNewAngleOnWallBounce(ball.getAlpha());
				ball.setAlpha(alpha);
				collision = true;
			}
			
			if(collisionDetector.isCollisionWithTop()) {
				alpha = BallEngine.getNewAngleOnTopWallBounce(ball.getAlpha());
				ball.setAlpha(alpha);
			}
			
			int[] pos = BallEngine.getNextPosition(ball.getX(), ball.getY(), ball.getAlpha(), ball.getSpeed());
			int x = pos[0];
			int y = pos[1];
			if(collision) {
				if(x <= limitLeft+ball.getRadius())
					x = limitLeft+ball.getRadius()+1;
				if(x >= limitRight-ball.getRadius())
					x = limitRight-ball.getRadius()+1;
			}
			
			ball.setX(x);
			ball.setY(y);
		}
	}
	
}
