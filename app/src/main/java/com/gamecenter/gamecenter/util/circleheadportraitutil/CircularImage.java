package com.gamecenter.gamecenter.util.circleheadportraitutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

public class CircularImage extends MaskedImage {

	public CircularImage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	public CircularImage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public CircularImage(Context context) {
		super(context);
	}

	@Override
	public Bitmap createMask() {
		  int i = getWidth();
	        int j = getHeight();
	        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
	        Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
	        Canvas localCanvas = new Canvas(localBitmap);
	        Paint localPaint = new Paint(1);
	        localPaint.setColor(Color.WHITE);
	        float f1 = getWidth();
	        float f2 = getHeight();
	        RectF localRectF = new RectF(0.0F, 0.0F, f1, f2);
	        localCanvas.drawOval(localRectF, localPaint);
		return null;
	}

}
