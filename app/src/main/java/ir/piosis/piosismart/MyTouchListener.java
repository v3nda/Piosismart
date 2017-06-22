package ir.piosis.piosismart;

import android.content.Context;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by PIOSISLP on 6/3/2017.
 */

public class MyTouchListener extends View implements View.OnTouchListener, View.OnDragListener {

    MyTouch myTouch;
    int lpd = 800;
    boolean drag = false;

    public MyTouchListener(Context context, boolean mdrag, int longPressDuration, MyTouch myTouch) {
        super(context);
        this.myTouch = myTouch;
        lpd = longPressDuration;
        drag = mdrag;
    }

    public MyTouchListener(Context context, boolean mdrag, MyTouch myTouch) {
        super(context);
        this.myTouch = myTouch;
        drag = mdrag;

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        Long testtime = event.getDownTime();

        if (testtime + lpd < event.getEventTime()) {


            myTouch.onLongClickDown();


            final float X = view.getX();
            final float Y = view.getY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    myTouch.onLongClickUp();
                    break;
                case MotionEvent.ACTION_MOVE:
                    myTouch.onLongClickMove();
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    view.setX(event.getRawX() - 150);
                    view.setY(event.getRawY() - 300);
                    view.setLayoutParams(layoutParams);
                    break;
            }
            return true;
        }
        if (testtime + lpd >= event.getEventTime()) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    myTouch.onClick();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
            }
            return true;
        }
        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {



            if (event.getAction() == DragEvent.ACTION_DROP) {
                //handle the dragged view being dropped over a target view
                Button dropped = (Button) event.getLocalState();
                Button dropTarget = (Button) v;


                //stop displaying the view where it was before it was dragged
//            dropped.setVisibility(View.INVISIBLE);
                ViewGroup.LayoutParams layoutParams = dropped.getLayoutParams();
                ViewGroup.LayoutParams layoutParams2 = dropTarget.getLayoutParams();

                LinearLayout.LayoutParams linearLayoutDropped = (LinearLayout.LayoutParams) dropped.getLayoutParams();
                LinearLayout.LayoutParams linearLayoutDropTarget = (LinearLayout.LayoutParams) dropTarget.getLayoutParams();


                LinearLayout linDropped = (LinearLayout) dropped.getParent();
                LinearLayout linDropTarget = (LinearLayout) dropTarget.getParent();

//            ((ViewGroup) dropped.getParent()).removeView(dropped);
//            ((ViewGroup) dropTarget.getParent()).removeView(dropTarget);


                ViewGroup linearLayout = (ViewGroup) dropped.getParent();
                ViewGroup linearLayout2 = (ViewGroup) dropTarget.getParent();
                int droppedindex = linearLayout.indexOfChild(dropped);
                int dropTargetindex = linearLayout2.indexOfChild(dropTarget);

                if (linearLayout != linearLayout2) {

                    linearLayout.removeView(dropped);
                    linearLayout2.removeView(dropTarget);
                    linearLayout2.addView(dropped, dropTargetindex);
                    linearLayout.addView(dropTarget, droppedindex);
                } else {
                    int lind = linDropped.indexOfChild(dropped);
                    int lindt = linDropTarget.indexOfChild(dropTarget);
//                dropTarget.setLayoutParams(linearLayoutDropped);
//                dropped.setLayoutParams(linearLayoutDropTarget);
                    Button droppeddup = dropped;
                    Button dropTargetdup = dropTarget;
                    linDropped.removeView(dropped);
                    linDropped.addView(droppeddup, lindt);
                    linDropped.removeView(dropTarget);
                    linDropped.addView(dropTargetdup, lind);

//    ((ViewGroup) dropped.getParent()).removeView(dropTarget);
//    ((ViewGroup) dropTarget.getParent()).removeView(dropped);
//    ((ViewGroup) dropped.getParent()).addView(dropTarget);
//    ((ViewGroup) dropTarget.getParent()).addView(dropped);
                }


                //if an item has already been dropped here, there will be different string
//            String text=dropTarget.getText().toString();
                //if there is already an item here, set it back visible in its original place
//            if(text.equals(text1.getText().toString())) text1.setVisibility(View.VISIBLE);
//            else if(text.equals(text2.getText().toString())) text2.setVisibility(View.VISIBLE);
//            else if(text.equals(text3.getText().toString())) text3.setVisibility(View.VISIBLE);
//
//            //update the text and color in the target view to reflect the data being dropped
//            dropTarget.setText(dropped.getText());
//            dropTarget.setBackgroundColor(Color.BLUE);
            }

        return true;
    }


    public static interface MyTouch extends OnTouchListener {
        public void onClick();

        public void onLongClickDown();

        public void onLongClickUp();

        public void onLongClickMove();

        public void onDrag();


    }
}
