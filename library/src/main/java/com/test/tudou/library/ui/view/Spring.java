package com.test.tudou.library.ui.view;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by chenupt@gmail.com on 2015/1/31. Description : Draw a head point and foot point.
 */
public class Spring {

  public Paint paint;
  public Path path;

  public Point headPoint;
  public Point footPoint;

  public Spring() {
    init();
  }

  private void init() {

    headPoint = new Point();
    footPoint = new Point();

    path = new Path();

    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    paint.setStrokeWidth(1);
  }

  public void makePath() {

    float headOffsetX = (float) (headPoint.getRadius() * Math.sin(
        Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
    float headOffsetY = (float) (headPoint.getRadius() * Math.cos(
        Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

    float footOffsetX = (float) (footPoint.getRadius() * Math.sin(
        Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
    float footOffsetY = (float) (footPoint.getRadius() * Math.cos(
        Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

    float x1 = headPoint.getX() - headOffsetX;
    float y1 = headPoint.getY() + headOffsetY;

    float x2 = headPoint.getX() + headOffsetX;
    float y2 = headPoint.getY() - headOffsetY;

    float x3 = footPoint.getX() - footOffsetX;
    float y3 = footPoint.getY() + footOffsetY;

    float x4 = footPoint.getX() + footOffsetX;
    float y4 = footPoint.getY() - footOffsetY;

    float anchorX = (footPoint.getX() + headPoint.getX()) / 2;
    float anchorY = (footPoint.getY() + headPoint.getY()) / 2;

    path.reset();
    path.moveTo(x1, y1);
    path.quadTo(anchorX, anchorY, x3, y3);
    path.lineTo(x4, y4);
    path.quadTo(anchorX, anchorY, x2, y2);
    path.lineTo(x1, y1);
  }

  public Point getHeadPoint() {
    return headPoint;
  }

  public Point getFootPoint() {
    return footPoint;
  }

  public void setIndicatorColor(int color) {
    paint.setColor(color);
  }

  public int getIndicatorColor() {
    return paint.getColor();
  }
}
