/**
 * 
 */
package com.ups.dualpong.graphic;

import com.ups.dualpong.game.Ball;
import com.ups.dualpong.game.Gauge;
import com.ups.dualpong.game.Racket;
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
public class GameView extends ImageView implements TouchListener {
	private static final int TIME_REFRESH = 50;
	private Handler refreshHandler;
	private Runnable invalidatorRunnable;
	private GraphicBall ball;
	private GraphicGauge gauge;
	private GraphicRacket racket;
	private boolean isTouching;
	private Integer width = null;
	private Integer height = null;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.refreshHandler = new Handler();
		this.isTouching = false;
		this.invalidatorRunnable = new Runnable() {
			@Override
			public void run() {
				invalidate();
			}
		};
		
		//this.initGraphicalElements();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(isTouching) {
			this.gauge.increase();
		} else {
			this.gauge.decrease();
		}
		
		drawGame(canvas);
		refreshHandler.postDelayed(this.invalidatorRunnable, TIME_REFRESH);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {		
		if(this.width == null || this.height == null) {	// first initialization of the screen
			this.width = w;
			this.height = h;
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
		this.racket = new GraphicRacket(new Racket(width / 2, (int) (0.2*width)));
		this.racket.setY(height-20);
	}
	
	private void drawGame(Canvas canvas) {
		Paint background = new Paint();
		background.setColor(Color.BLACK);
		if(width != null && height != null) {
			canvas.drawRect(0, 0, width, height, background);
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
	
	@Override
	public void touchDown() {
		this.isTouching = true;
	}

	@Override
	public void touchUp() {
		this.isTouching = false;
	}
	
}
