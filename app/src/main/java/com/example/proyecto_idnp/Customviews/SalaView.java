package com.example.proyecto_idnp.Customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.proyecto_idnp.Modelos.CanvasViewModel;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;

public class SalaView extends View {

    private Context context;
    private Paint paintRoom;
    private Canvas canvas;
    private float scaleX, scaleY;
    private Drawable pictureDrawable;
    private CanvasViewModel canvasModel;
    private ObrasViewModel obrasViewModel;

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
        drawPicture();
    }
    private void drawMapLayout(){
        int propTouch = canvas.getHeight() / canvas.getWidth();
        int proporcion_x = 431 * propTouch;
        int proporcion_y = 317 * propTouch;
        int left = canvas.getWidth()/2 - proporcion_x;
        int top = canvas.getHeight()/2 - proporcion_y;
        int right = canvas.getWidth()/2 + proporcion_x;
        int bottom = canvas.getHeight()/2 + proporcion_y;
        canvas.drawRect(left, top, right, bottom,paintRoom);
    }
    private void calculateScale(){
        scaleX = canvas.getWidth();
        scaleY = canvas.getHeight();
    }
    private void drawPicture() {
        pictureDrawable = AppCompatResources.getDrawable(context, R.drawable.icon_cuadro);
        if (pictureDrawable != null) {
            int propTouch = canvas.getHeight() / canvas.getWidth();
            int proporcion_x = 431 * propTouch;
            int proporcion_y = 317 * propTouch;
            int bottom = canvas.getHeight()/2 + proporcion_y - 10;
            int right = canvas.getWidth()/2 + proporcion_x - 128 * propTouch;
            int left = right - 100;
            int top = bottom - 100;
            pictureDrawable.setBounds(left, top, right, bottom);
            pictureDrawable.draw(canvas);
        }
    }
    public void setListener(ObrasViewModel obrasViewModel){
        this.obrasViewModel = obrasViewModel;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boolean clicked = pictureDrawable.getBounds().contains(pointX,pointY);
                if (clicked) {
                    obrasViewModel.setObraSeleccionadaPorId(1);
                    Log.d("MapView","onTouchEvent Puntos " + pointX + " " + pointY );
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MapView","Puntos " + pointX + " " + pointY );
            default:
                return false;
        }
        invalidate();
        return true;
    }
}
