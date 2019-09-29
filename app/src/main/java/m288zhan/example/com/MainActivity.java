package m288zhan.example.com;

import android.app.Dialog;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    mycanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvas = (mycanvas) findViewById(R.id.mycanvas);
        makeSprite();
        ImageButton loadBtn = (ImageButton) findViewById(R.id.LoadBtn);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                System.out.println("hdsjhdueiwfhwefbeuihfwe");
               canvas.reset();
               makeSprite();
               canvas.invalidate();
            }
        });

        ImageButton NewModelButton = (ImageButton) findViewById(R.id.NewModelButton1);
        NewModelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                System.out.println("jdksjdskjdsjds");
                canvas.reset();
                makemodelSprite();
                canvas.invalidate();
            }
        });

        ImageButton aboutBtn = (ImageButton) findViewById(R.id.AboutButton1);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.enlarge);
                TextView imageView = dialog.findViewById(R.id.enlarge_view);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.show();

                }
        });
    }

    public Sprite makeSprite() {
        // create four different parts
        Sprite body = new RectangleSprite(200, 300,0);
        Sprite head = new OvalSprite(200, 250,50,1);
        Sprite leftupparm =  new OvalSprite(50, 200,360,2);
        Sprite rightupparm = new OvalSprite(50, 200,360,3);
        Sprite leftlowarm =  new OvalSprite(50, 150,135,4);
        Sprite rightlowarm = new OvalSprite(50, 150,135,5);
        Sprite lefthand =  new OvalSprite(70,80 ,35,6);
        Sprite righthand = new OvalSprite(70, 80,35,7);

        Sprite leftuppleg =  new OvalSprite(50,200 ,90,8);
        Sprite rightuppleg = new OvalSprite(50, 200,90,9);

        Sprite leftlowleg =  new OvalSprite(50,150 ,90,10);
        Sprite rightlowleg = new OvalSprite(50, 150,90,11);

        Sprite leftfoot =  new OvalSprite(50,80 ,90,12);
        Sprite rightfoot = new OvalSprite(50, 80,90,13);

       // Sprite thirdSprite = new RectangleSprite(70, 30);

        // define them based on relative, successive transformations
        Matrix body_firstm  = new Matrix();
        body_firstm.postTranslate(600,300);

        Matrix head_secondm  = new Matrix();
        head_secondm.postTranslate(0,-250);
        Matrix leftupparm_m  = new Matrix();
        leftupparm_m.postTranslate(-25,0);
        Matrix leftlowarm_m  = new Matrix();
        leftlowarm_m.postTranslate(0,190);
        Matrix rightlowarm_m  = new Matrix();
        rightlowarm_m.postTranslate(0,190);
        Matrix rightupparm_m  = new Matrix();
        rightupparm_m.postTranslate(175,0);
        Matrix lefthand_m  = new Matrix();
        lefthand_m.postTranslate(0,140);
        Matrix righthand_m  = new Matrix();
        righthand_m.postTranslate(0,140);
        Matrix leftuppleg_m  = new Matrix();
        leftuppleg_m.postTranslate(-25,300);
        Matrix rightuppleg_m  = new Matrix();
        rightuppleg_m.postTranslate(175,300);
        Matrix leftlowleg_m  = new Matrix();
        leftlowleg_m.postTranslate(0,190);
        Matrix rightlowleg_m  = new Matrix();
        rightlowleg_m.postTranslate(0,190);

        Matrix leftfoot_m  = new Matrix();
        leftfoot_m.postTranslate(0,140);
        Matrix rightfoot_m  = new Matrix();
        rightfoot_m.postTranslate(0,140);




        body.transform(body_firstm);
        head.transform(head_secondm);
        leftupparm.transform(leftupparm_m);
        rightupparm.transform(rightupparm_m);
        rightlowarm.transform(rightlowarm_m);
        leftlowarm.transform(leftlowarm_m);
        lefthand.transform(lefthand_m);
        righthand.transform(righthand_m);
        leftuppleg.transform(leftuppleg_m);
        rightuppleg.transform(rightuppleg_m);
        leftlowleg.transform(leftlowleg_m);
        rightlowleg.transform(rightlowleg_m);

        leftfoot.transform(leftfoot_m);
        rightfoot.transform(rightfoot_m);



        // build scene graph
        body.addChild(head);
        body.addChild(leftupparm);
        body.addChild(rightupparm);
        body.addChild(rightlowarm);
        body.addChild(leftlowarm);
        body.addChild(leftuppleg);
        body.addChild(rightuppleg);


        rightupparm.addChild(rightlowarm);
        leftupparm.addChild(leftlowarm);
        leftlowarm.addChild(lefthand);
        rightlowarm.addChild(righthand);

        leftuppleg.addChild(leftlowleg);
        rightuppleg.addChild(rightlowleg);

        leftlowleg.addChild(leftfoot);
        rightlowleg.addChild(rightfoot);

        canvas.addSprite(body);
        canvas.addSprite(head);
        canvas.addSprite(leftupparm);
        canvas.addSprite(rightupparm);
        canvas.addSprite(rightlowarm);
        canvas.addSprite(leftlowarm);
        canvas.addSprite(lefthand);
        canvas.addSprite(righthand);
        canvas.addSprite(leftuppleg);
        canvas.addSprite(rightuppleg);
        canvas.addSprite(leftlowleg);
        canvas.addSprite(rightlowleg);
        canvas.addSprite(leftfoot);
        canvas.addSprite(rightfoot);

        OvalSprite r2 = (OvalSprite) leftupparm;
        RectF rr2 = r2.rect;

        OvalSprite r3 = (OvalSprite) rightupparm;
        RectF rr3 = r3.rect;

        leftupparm.transform.preRotate((float) 30, rr2.width()/2,0);
        rightupparm.transform.preRotate((float) -30, rr3.width()/2,0);
        return body;
    }


    public Sprite makemodelSprite() {
        // create four different parts
        Sprite body = new RectangleSprite(200, 400,0);
        Sprite leftear =  new OvalSprite(40,150 ,50,1);
        Sprite rightear = new OvalSprite(40, 150,50,1);
        Sprite leftupparm =  new OvalSprite(150, 350,40,8);
        Sprite rightupparm = new OvalSprite(150, 350,40,8);
        Sprite leftlowarm =  new OvalSprite(80, 400,50,8);
        Sprite rightlowarm = new OvalSprite(80, 400,50,8);
        Sprite leftroot=  new OvalSprite(40,100,50,8);
        Sprite rightroot = new OvalSprite(40, 100,50,8);

        // Sprite thirdSprite = new RectangleSprite(70, 30);

        // define them based on relative, successive transformations
        Matrix body_firstm  = new Matrix();
        body_firstm.postTranslate(600,300);

        Matrix leftear_m  = new Matrix();
        leftear_m.postTranslate(40,-150);
        Matrix rightear_m  = new Matrix();
        rightear_m.postTranslate(140,-150);

        Matrix leftupparm_m  = new Matrix();
        leftupparm_m.postTranslate(-75,200);
        Matrix leftlowarm_m  = new Matrix();
        leftlowarm_m.postTranslate(-40,200);

        Matrix rightupparm_m  = new Matrix();
        rightupparm_m.postTranslate(125,200);
        Matrix rightlowarm_m  = new Matrix();
        rightlowarm_m.postTranslate(160,180);


        Matrix leftroot_m  = new Matrix();
        leftroot_m.postTranslate(40,400);
        Matrix rightroot_m  = new Matrix();
        rightroot_m.postTranslate(140,400);







        body.transform(body_firstm);
        leftear.transform(leftear_m);
        rightear.transform(rightear_m);
        leftupparm.transform(leftupparm_m);
        rightupparm.transform(rightupparm_m);
        rightlowarm.transform(rightlowarm_m);
        leftlowarm.transform(leftlowarm_m);
        leftroot.transform(leftroot_m);
        rightroot.transform(rightroot_m);



        // build scene graph
        body.addChild(leftear);
        body.addChild(rightear);
        body.addChild(leftupparm);
        body.addChild(rightupparm);
        body.addChild(rightlowarm);
        body.addChild(leftlowarm);
        body.addChild(leftroot);
        body.addChild(rightroot);



        canvas.addSprite(body);
        canvas.addSprite(leftear);
        canvas.addSprite(rightear);
        canvas.addSprite(leftupparm);
        canvas.addSprite(rightupparm);
        canvas.addSprite(rightlowarm);
        canvas.addSprite(leftlowarm);
        canvas.addSprite(leftroot);
        canvas.addSprite(rightroot);

        OvalSprite r2 = (OvalSprite) leftupparm;
        RectF rr2 = r2.rect;

        OvalSprite r3 = (OvalSprite) rightupparm;
        RectF rr3 = r3.rect;

        OvalSprite r4 = (OvalSprite) leftlowarm;
        RectF rr4 = r4.rect;

        OvalSprite r5 = (OvalSprite) leftupparm;
        RectF rr5 = r5.rect;

        leftupparm.transform.preRotate((float) 130, rr2.width()/2,0);
        rightupparm.transform.preRotate((float) -130, rr3.width()/2,0);
        leftlowarm.transform.preRotate((float) 30, rr4.width()/2,0);
        rightlowarm.transform.preRotate((float) -30, rr5.width()/2,0);
        return body;
    }
}
