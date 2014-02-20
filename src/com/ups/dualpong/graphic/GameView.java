/**
 * 
 */
package com.ups.dualpong.graphic;

import com.ups.dualpong.game.Ball;
import com.ups.dualpong.game.Gauge;
import com.ups.dualpong.game.Racket;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author SERIN Kevin
 *
 */
public class GameView extends ImageView {
	private static final int TIME_REFRESH = 50;
	private Handler refreshHandler;
	private Runnable invalidatorRunnable;
	private GraphicBall ball;
	private GraphicGauge gauge;
	private GraphicRacket racket;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.refreshHandler = new Handler();
		this.invalidatorRunnable = new Runnable() {
			@Override
			public void run() {
				invalidate();
			}
		};
		
		this.ball = new GraphicBall(new Ball(10, 10, 0, 0));
		this.gauge = new GraphicGauge(new Gauge());
		this.racket = new GraphicRacket(new Racket(50, 10));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		drawGame(canvas);
		refreshHandler.postDelayed(this.invalidatorRunnable, TIME_REFRESH);
	}
	
	private void drawGame(Canvas canvas) {
		this.ball.drawOnCanvas(canvas);
		this.gauge.drawOnCanvas(canvas);
		this.racket.drawOnCanvas(canvas);
	}
	
}
