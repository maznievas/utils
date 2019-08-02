package com.stellium.bigdig.stellium1.view.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.stellium.bigdig.stellium1.R;

public class RoundedCornersImageView extends android.support.v7.widget.AppCompatImageView {
  private final Paint restorePaint = new Paint();
  private final Paint maskXferPaint = new Paint();
  private final Paint canvasPaint = new Paint();

  private final Rect bounds = new Rect();
  private final RectF boundsf = new RectF();
  private float radius;

  public RoundedCornersImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs);
  }

  public RoundedCornersImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public RoundedCornersImageView(Context context) {
    super(context);
    init(null);
  }

  private void init(AttributeSet attrs) {
    canvasPaint.setAntiAlias(true);
    canvasPaint.setColor(Color.argb(255, 255, 255, 255));
    restorePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
    maskXferPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
    TypedArray attrsArray = getContext().obtainStyledAttributes(attrs,R.styleable.RoundedCornersImageView);
    if(attrsArray==null)
      radius = getResources().getDimension(R.dimen.corners_radius_default);
    else {
//      float radiusPx = attrsArray.getDimension(R.styleable.RoundedCornersImageView_corner_radius, getResources().getDimension(R.dimen.corners_radius_default));
//      radius = convertPixelsToDp(radiusPx, getContext());
//      Log.d("radius", "radius: " + radius);
      radius = attrsArray.getDimension(R.styleable.RoundedCornersImageView_corner_radius, getResources().getDimension(R.dimen.corners_radius_default));
      attrsArray.recycle();
    }
    setLayerType(View.LAYER_TYPE_HARDWARE, restorePaint);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.getClipBounds(bounds);
    boundsf.set(bounds);

    super.onDraw(canvas);

    canvas.saveLayer(boundsf, maskXferPaint, Canvas.ALL_SAVE_FLAG);
    canvas.drawARGB(0, 0, 0, 0);
    canvas.drawRoundRect(boundsf, radius, radius, canvasPaint);

    canvas.restore();
  }

  public static float convertPixelsToDp(float px, Context context){
    return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }
}