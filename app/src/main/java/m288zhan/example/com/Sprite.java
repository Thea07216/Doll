package m288zhan.example.com;


import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.View;

import java.util.Vector;

/**
 * A building block for creating your own shapes that can be
 * transformed and that can respond to input. This class is
 * provided as an example; you will likely need to modify it
 * to meet the assignment requirements.
 *
 * Michael Terry & Jeff Avery
 */
public abstract class Sprite {

    /**
     * Tracks our current interaction mode after a mouse-down
     */
    protected enum InteractionMode {
        IDLE,
        DRAGGING,
        SCALING,
        ROTATING
    }

    public Sprite parent = null;                                       // Pointer to our parent
    public Vector<Sprite> children = new Vector<Sprite>();             // Holds all of our children
    public Matrix transform = new Matrix();          // Our transformation matrix
    public PointF lastPoint = null;                                 // Last mouse point
    public InteractionMode interactionMode = InteractionMode.IDLE;   // current state
    public View view;
    public int identdrag = 0;
    public int id;
    public int curdegree = 0;
    public Matrix scalesave = new Matrix();

    public Sprite() {
        this.view = view;
    }

    public Sprite(Sprite parent) {
        if (parent != null) {
            parent.addChild(this);
        }
        this.view = view;
    }
    public void setcurdeg(int diff){
        curdegree = curdegree - diff;
    }

    public void addChild(Sprite s) {
        children.add(s);
        s.setParent(this);
    }
    public Sprite getParent() {
        return parent;
    }
    private void setParent(Sprite s) {
        this.parent = s;
    }

    /**
     * Test whether a point, in world coordinates, is within our sprite.
     */
//    public abstract boolean pointInside(PointF p);

    /**
     * Handles a mouse down event, assuming that the event has already
     * been tested to ensure the mouse point is within our sprite.
     */

//    protected void onTouchEvent(MotionEvent e) {
//        int eventaction = e.getAction();
//        float x = (float) e.getX();
//        float y = (float) e.getY();
//        lastPoint = new PointF(x,y);
//        switch (eventaction) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//        }
//        view.invalidate();
//
//        // tell the View that we handled the event
//    }

    /**
     * Handle mouse drag event, with the assumption that we have already
     * been "selected" as the sprite to interact with.
     * This is a very simple method that only works because we
     * assume that the coordinate system has not been modified
     * by scales or rotations. You will need to modify this method
     * appropriately so it can handle arbitrary transformations.
     */
//    protected void onDragEvent(MotionEvent e) {
//        PointF oldPoint = lastPoint;
//       // PointF newPoint = e.getPoint();
//        float x = (float) e.getX();
//        float y = (float) e.getY();
//        PointF newPoint = new PointF(x,y);
//        switch (interactionMode) {
//            case IDLE:
//                ; // no-op (shouldn't get here)
//                break;
//            case DRAGGING:
//                float x_diff = newPoint.x - oldPoint.x;
//                float y_diff = newPoint.y - oldPoint.y;
//                transform.postTranslate(x_diff, y_diff);
//                break;
//            case ROTATING:
//              //  transform.preRotate(Math.toRadians(15));
//                break;
//            case SCALING:
//               // transform.preScale(1.0, 1.01);
//                break;
//        }
//        // Save our last point, if it's needed next time around
//        lastPoint = newPoint;
//    }

//    protected void onDragEvent(MotionEvent e) {
//        PointF oldPoint = lastPoint;
//        // PointF newPoint = e.getPoint();
//        float x = (float) e.getX();
//        float y = (float) e.getY();
//        PointF newPoint = new PointF(x,y);
//        if(identdrag == 0){
//                ; // no-op (shouldn't get here)
//                break;
//            case DRAGGING:
//                float x_diff = newPoint.x - oldPoint.x;
//                float y_diff = newPoint.y - oldPoint.y;
//                transform.postTranslate(x_diff, y_diff);
//                break;
//            case ROTATING:
//                //  transform.preRotate(Math.toRadians(15));
//                break;
//            case SCALING:
//                // transform.preScale(1.0, 1.01);
//                break;
//        }
//        // Save our last point, if it's needed next time around
//        lastPoint = newPoint;
//    }

//    protected void handleMouseUp(MouseEvent e) {
//        interactionMode = InteractionMode.IDLE;
//        // Do any other interaction handling necessary here
//    }

    /**
     * Locates the sprite that was hit by the given event.
     * You *may* need to modify this method, depending on
     * how you modify other parts of the class.
     *
     * @return The sprite that was hit, or null if no sprite was hit
     */
//    public Sprite getSpriteHit(MotionEvent e) {
//        for (Sprite sprite : children) {
//            Sprite s = sprite.getSpriteHit(e);
//            if (s != null) {
//                return s;
//            }
//        }
//        if (this.pointInside(e.getPoint())) {
//            return this;
//        }
//        return null;
//    }

    /*
     * Important note: How transforms are handled here are only an example. You will
     * likely need to modify this code for it to work for your assignment.
     */

    /**
     * Returns the full transform to this object from the root
     */
    public Matrix getFullTransform() {
        Matrix returnTransform = new Matrix();
        Sprite curSprite = this;
        while (curSprite != null) {
//            if (id == 12 || id == 13) {
//                returnTransform.postConcat(curSprite.getLocal());
//                curSprite = curSprite.getParent();
//            } else {
                returnTransform.postConcat(curSprite.getLocalTransform());
                curSprite = curSprite.getParent();
//            }
        }
        return returnTransform;
    }
//    public Matrix getfootFullTransform() {
//        Matrix returnTransform = new Matrix();
//        Sprite curSprite = this;
//        while (curSprite != null) {
//            if(id == 12 || id == 13) {
//                returnTransform.postConcat(curSprite.getLocal());
//                curSprite = curSprite.getParent();
//            } else {
//                returnTransform.postConcat(curSprite.getFullTransform());
//                curSprite = curSprite.getParent();
//            }
//        }
//        return returnTransform;
//    }

    /**
     * Returns our local transform
     */
    public Matrix getLocalTransform() {


            Matrix m = new Matrix();
            m.set(transform);
            m.preConcat(scalesave);
            return m;

    }
    public Matrix getLocal(){
        Matrix m = new Matrix();
        m.set(transform);
        return m;
    }
    public Matrix getLocalTransformscale() {

        Matrix m = new Matrix();
        m.set(scalesave);
        return m;
    }

    /**
     * Performs an arbitrary transform on this sprite
     */
    public void transform(Matrix t) {
        transform.preConcat(t);
    }

    /**
     * Draws the sprite. This method will call drawSprite after
     * the transform has been set up for this sprite.
     */
    public void draw(Canvas g) {
        Matrix oldTransform = g.getMatrix();

        // Set to our transform
        Canvas g2 = (Canvas) g;
        Matrix currentAT = g.getMatrix();
        /*if (id == 12 || id == 13) {
            currentAT.preConcat(getfootFullTransform());

        } else{*/
            currentAT.preConcat(getFullTransform());
//    }
        // currentAT.concatenate(transform);
        g2.setMatrix(currentAT);
        Canvas c;
        // Draw the sprite (delegated to sub-classes)
        this.drawSprite(g);

        // Restore original transform
        g.setMatrix(oldTransform);

        // Draw children
        for (Sprite sprite : children) {
            sprite.draw(g);
        }
    }

    /**
     * The method that actually does the sprite drawing. This method
     * is called after the transform has been set up in the draw() method.
     * Sub-classes should override this method to perform the drawing.
     */
    protected abstract void drawSprite(Canvas g);
}
