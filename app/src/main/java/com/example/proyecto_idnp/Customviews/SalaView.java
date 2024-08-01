package com.example.proyecto_idnp.Customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.proyecto_idnp.R;

public class SalaView extends View {

    private Context context;
    private Paint paintRoom;
    private Canvas canvas;
    private float scaleX, scaleY;
    private Drawable pictureDrawable;

    public SalaView(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public SalaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    private void initPaint(){
        paintRoom = new Paint();
        paintRoom.setColor(Color.GRAY);
        paintRoom.setStyle(Paint.Style.STROKE);
        paintRoom.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        calculateScale();
        drawMapLayout();
//        drawNameSpaces();
        drawPicture();
    }
    private void drawMapLayout(){
        int propTouch = canvas.getHeight() / canvas.getWidth();
        int proporcion_x = 431 * propTouch;
        int proporcion_y = 317 * propTouch;
        int left = canvas.getWidth()/2 - proporcion_x;
        int top = canvas.getHeight()/2 - proporcion_y;
        int right = canvas.getWidth()/2 + proporcion_x;
        int bootom = canvas.getHeight()/2 + proporcion_y;
        canvas.drawRect(left, top, right, bootom,paintRoom);
    }
    private void calculateScale(){
        scaleX = canvas.getWidth();
        scaleY = canvas.getHeight();
    }
    private void drawPicture() {
        pictureDrawable = AppCompatResources.getDrawable(context, R.drawable.cuadros_icon_blue);
        if (pictureDrawable != null) {
            int left = (int) (456 * scaleX);
            int top = (int) (1980 * scaleY);
            int right = (int) (556 * scaleX);
            int bottom = (int) (2080 * scaleY);
            pictureDrawable.setBounds(left, top, right, bottom);
            pictureDrawable.draw(canvas);
        }
    }
}
