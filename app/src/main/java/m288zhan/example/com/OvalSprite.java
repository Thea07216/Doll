package m288zhan.example.com;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class OvalSprite extends Sprite {

    public RectF rect = null;
    public Paint p;
    public int limitangle = 0;

    /**
     * Creates a rectangle based at the origin with the specified
     * width and height
     */
    public OvalSprite(int width, int height,int limitangle,int id) {
        super();
        this.initialize(width, height);
        this.limitangle = limitangle;
        p = new Paint();
        super.identdrag = 0;
        super.id = id;
    }
    /**
     * Creates a rectangle based at the origin with the specified
     * width, height, and parent
     */
    public OvalSprite(int width, int height, Sprite parentSprite,int id) {
        super(parentSprite);
        this.initialize(width, height);
        p = new Paint();
        super.identdrag = 0;
        super.id = id;
    }

    private void initialize(int width, int height) {
        rect = new RectF(0, 0, width, height);
    }

    /**
     * Test if our rectangle contains the point specified.
     //     */


    protected void drawSprite(Canvas g) {
        // g.setColor(Color.BLACK);
        p.setColor(Color.BLACK);
        g.drawOval(rect,p);
    }

    public String toString() {
        return "RectangleSprite: " + rect;
    }
}
