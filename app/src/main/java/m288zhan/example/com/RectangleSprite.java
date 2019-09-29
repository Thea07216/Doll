package m288zhan.example.com;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * A simple demo of how to create a rectangular sprite.
 *
 * Michael Terry & Jeff Avery
 */
public class RectangleSprite extends Sprite {

    public Rect rect = null;
    public Paint p;


    /**
     * Creates a rectangle based at the origin with the specified
     * width and height
     */
    public RectangleSprite(int width, int height,int id) {
        super();
        this.initialize(width, height);
        p = new Paint();
        super.identdrag= 1;
        super.id = id;
    }
    /**
     * Creates a rectangle based at the origin with the specified
     * width, height, and parent
     */
    public RectangleSprite(int width, int height, Sprite parentSprite,int id) {
        super(parentSprite);
        this.initialize(width, height);
        p = new Paint();
        super.identdrag = 1;
        super.id = id;
    }

    private void initialize(int width, int height) {
        rect = new Rect(0, 0, width, height);
    }

    /**
     * Test if our rectangle contains the point specified.
//     */

    protected void drawSprite(Canvas g) {
       // g.setColor(Color.BLACK);
        p.setColor(Color.BLACK);
        g.drawRect(rect, p);
    }

    public String toString() {
        return "RectangleSprite: " + rect;
    }
}

