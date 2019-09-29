package m288zhan.example.com;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.Vector;

public class mycanvas extends View {

    Paint paint;
    private Vector<Sprite> sprites = new Vector<Sprite>(); // All sprites we're managing
    private Sprite interactiveSprite = null; // Sprite with which user is interacting
    Context context;
    Sprite target = null;
    PointF lastPoint = null;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    Matrix trackscale = new Matrix();
    Matrix updatescale = new Matrix();
    Boolean scaling =false;


    public void reset(){
        sprites = new Vector<Sprite>(); // All sprites we're managing
        interactiveSprite = null; // Sprite with which user is interacting
        target = null;
        lastPoint = null;
    }

    public mycanvas(Context context, AttributeSet ast){
        super(context,ast);
        this.context = context;
        paint = new Paint();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());


    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
//            if (detector.isInProgress()) {
                mScaleFactor *= detector.getScaleFactor();
//                System.out.println(); detector.getCurrentSpanX();
                // Don't let the object get too small or too large.
//                System.out.println("init Scaling testetstest :  " + mScaleFactor);

                mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 1.5f));
                //mScaleFactor = 0.1F;
//                System.out.println("Scaling testetstest :  " + mScaleFactor);
                scaling = true;
                //invalidate();
//            }
            return true;
        }
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector){
            scaling = true;
            return true;
        }
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            scaling = false;
            mScaleFactor = 1.0f;
//            System.out.println("onScaleEnd !!!!!!!!!!!!!!!!!!!!!!!!!1");
            if(target != null) {
                target.scalesave = updatescale;
            }

        }
    }

    public boolean Checkpoints(PointF pf){
        for (Sprite sprite : sprites) {
            if(sprite.identdrag == 0){
                OvalSprite ovalRect = (OvalSprite) sprite;
                Matrix temp = new Matrix();
                if(pointInside(pf,ovalRect)){
//                    System.out.println("seting target");
                    target = ovalRect;
                    return true;
                }
            } else {
                RectangleSprite  RecSprite = (RectangleSprite) sprite;
                //if(RecSprite.rect.contains((int)pf.x,(int)pf.y)) {
                if(pointInsideforrect(pf,RecSprite)){
                    target = RecSprite;
//                    System.out.println("seting target");
//                    System.out.println("222222");
                    return true;
                }
            }
        }
//        System.out.println("no seting target");
        return false;
    }

    public boolean pointInside(PointF p,OvalSprite sp) {
        Matrix fullTransform = sp.getFullTransform();
        Matrix inverseTransform = new Matrix();
        if(fullTransform.invert(inverseTransform)) {
            float[] pp = {p.x,p.y};
            inverseTransform.mapPoints(pp);
            OvalSprite osp = (OvalSprite) sp;
            return osp.rect.contains(pp[0], pp[1]);
        }
        return true;
    }


    public boolean pointInsideforrect(PointF p,RectangleSprite sp) {
        Matrix fullTransform = sp.getFullTransform();
        Matrix inverseTransform = new Matrix();
        if(fullTransform.invert(inverseTransform)) {
            float[] pp = {p.x,p.y};
            inverseTransform.mapPoints(pp);
            return sp.rect.contains((int)pp[0], (int)pp[1]);
        }
        return true;
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF oldPoint = lastPoint;
        PointF newPoint = new PointF(event.getX(),event.getY());
        int x = (int) event.getX();
        int y = (int) event.getY();
        mScaleDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN ):
                Checkpoints(newPoint);
                lastPoint = newPoint;
                break;
            case MotionEvent.ACTION_MOVE:
                if ((event.getPointerCount() == 2  &&target != null && target.id <= 11 && target.id >= 8)){
//                    System.out.println("scaling");
                    trackscale = target.getLocalTransformscale();
                    target.scalesave.preScale(1f, mScaleFactor,((OvalSprite) target).rect.width()/2,0);
                    updatescale = target.getLocalTransformscale();
                    invalidate();
                    return true;
                } else if(event.getPointerCount() == 1 && target != null) {
//                    System.out.println("rotating ratating");
                    if (target != null) {
                        if (target.identdrag == 0 && pointInside(newPoint,(OvalSprite) target)) {
                            oldPoint = lastPoint;
                            newPoint = new PointF(event.getX(), event.getY());
                            PointF oldPointdiff = new PointF(oldPoint.x, oldPoint.y);
                            PointF newPointdiff = new PointF(newPoint.x, newPoint.y);
                            Matrix fullTransform = target.getFullTransform();
                            Matrix inverseTransform = new Matrix();
                            OvalSprite tempsprite = (OvalSprite) target;
                            RectF temprect = tempsprite.rect;
                            if (fullTransform.invert(inverseTransform)) {
//                            System.out.println("hdhdhdhdhdhdhhdhd");
                                float[] pp = {oldPoint.x, oldPoint.y};
                                inverseTransform.mapPoints(pp);
                                Matrix mm = new Matrix();
                                fullTransform.invert(mm);
                                float[] ppnew = {newPoint.x, newPoint.y};
                                mm.mapPoints(ppnew);
                                // oldPointdiff = oldPoint;
                                oldPointdiff = new PointF(pp[0], pp[1]);
                                newPointdiff = new PointF(ppnew[0], ppnew[1]);
//                            System.out.println("oddPointdiff : " + oldPointdiff.x  + " " + oldPointdiff.y);
//                            System.out.println("newPointdiff : " + newPointdiff.x  + " " + newPointdiff.y);
                            }
                            double distold = 0;
                            double distnew = 0;
                            double cos_up = 0;
                            distold = Math.sqrt(oldPointdiff.x * oldPointdiff.x + oldPointdiff.y * oldPointdiff.y);
                            distnew = Math.sqrt(newPointdiff.x * newPointdiff.x + newPointdiff.y * newPointdiff.y);
                            cos_up = (oldPointdiff.x * newPointdiff.x) + (oldPointdiff.y * newPointdiff.y);
                            double cosvalue = cos_up / (distold * distnew);
                            if (cosvalue > 1) {
                                cosvalue = 1;
                            } else if (cosvalue < -1) {
                                cosvalue = -1;
                            }
                            double acosvalue = Math.toDegrees(Math.acos(cosvalue));
                            if (target.id == 1) {
                                if (oldPointdiff.x < newPointdiff.x) {
//                                    System.out.println("limit angle : " + ((OvalSprite) target).limitangle);
//                                    System.out.println("target curdegree : " + target.curdegree);
                                    if (target.curdegree + (acosvalue*2) <= ((OvalSprite) target).limitangle) {
                                        target.curdegree = (int) (target.curdegree + (acosvalue*2));
                                        target.transform.preRotate((float) ((acosvalue*2)), temprect.width() / 2, temprect.height());
                                    } else {
                                        target.curdegree = ((OvalSprite) target).limitangle;
                                    }
                                }
                                else {
                                    if (target.curdegree - ((acosvalue*2)) >= ((-1) * ((OvalSprite) target).limitangle)) {
                                        target.curdegree = (int) (target.curdegree - (acosvalue*2));
                                        target.transform.preRotate((float) ((acosvalue*2)) * (-1), temprect.width() / 2, temprect.height());
                                    } else {
                                        target.curdegree = (-1) * ((OvalSprite) target).limitangle;
                                    }
                                }
                            } else{
                                if (target.id <= 7 && target.id >= 2) {
                                    if (oldPointdiff.x > newPointdiff.x) {
//                                        System.out.println("limit angle : " + ((OvalSprite) target).limitangle);
//                                        System.out.println("target curdegree : " + target.curdegree);
                                        if (target.curdegree + (acosvalue * 2) <= ((OvalSprite) target).limitangle) {
                                            target.curdegree = (int) (target.curdegree + acosvalue*2);
                                            target.transform.preRotate((float) (acosvalue*2), temprect.width() / 2, 0);
                                        } else {
                                            target.curdegree = ((OvalSprite) target).limitangle;
                                        }
                                    } else {
                                        if (target.curdegree - ((acosvalue*2)) >= ((-1) * ((OvalSprite) target).limitangle)) {
                                            target.curdegree = (int) (target.curdegree - (acosvalue*2));
                                            target.transform.preRotate((float) (acosvalue*2) * (-1), temprect.width() / 2, 0);
                                        } else {
                                            target.curdegree = (-1) * ((OvalSprite) target).limitangle;
                                        }
                                    }
                                } else if (target.id <= 13 && target.id >= 8) {

                                    if (oldPointdiff.x > newPointdiff.x) {
//                                        System.out.println("limit angle : " + ((OvalSprite) target).limitangle);
//                                        System.out.println("target curdegree : " + target.curdegree);
                                        if (target.curdegree + ((acosvalue*2)) <= ((OvalSprite) target).limitangle) {
                                            target.curdegree = (int) (target.curdegree + (acosvalue*2));
                                            target.transform.preRotate((float) ((acosvalue*2)), temprect.width() / 2, 0);
                                        } else {
                                            target.curdegree = ((OvalSprite) target).limitangle;
                                        }
                                    }
                                    else {
                                        if (target.curdegree - ((acosvalue*2)) >= ((-1) * ((OvalSprite) target).limitangle)) {
                                            target.curdegree = (int) (target.curdegree - (acosvalue*2));
                                            target.transform.preRotate((float) ((acosvalue*2)) * (-1), temprect.width() / 2, 0);
                                        } else {
                                            target.curdegree = (-1) * ((OvalSprite) target).limitangle;
                                        }
                                    }

                                }
//                                target.transform.preRotate((float) (acosvalue * 2), temprect.width() / 2, 0);
                            }
                            lastPoint = newPoint;
                            invalidate();
                        } else if (target.identdrag == 1 && pointInsideforrect(newPoint, (RectangleSprite) target)) {
                            oldPoint = lastPoint;
                            newPoint = new PointF(event.getX(), event.getY());
                            float x_diff = newPoint.x - oldPoint.x;
                            float y_diff = newPoint.y - oldPoint.y;
                            target.transform.postTranslate(x_diff, y_diff);
                            lastPoint = newPoint;
                            invalidate();
                        }
                        lastPoint = newPoint;
                        return true;
                    }
                }

            case MotionEvent.ACTION_UP:
                //target.transform =
//                System.out.println("ACTION_UP !!!!!!!!!!!!!!!!!!!!!!!!!1");
                target = null;
        }
        return true;
    }

    public void addSprite(Sprite s) {
        sprites.add(s);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);


        for (Sprite sprite : sprites) {
            sprite.draw((Canvas) canvas);
        }

        if(target != null && scaling &&target.id <= 11 && target.id >= 8) {
            target.scalesave = trackscale;
//            System.out.println("set matrix back!!!!1");
        }
    }
}
