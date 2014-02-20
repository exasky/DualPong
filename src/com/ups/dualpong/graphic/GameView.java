/**
 * 
 */
package com.ups.dualpong.graphic;

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
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.refreshHandler = new Handler();
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
		
		refreshHandler.postDelayed(this.invalidatorRunnable, TIME_REFRESH);
	}
	
}
