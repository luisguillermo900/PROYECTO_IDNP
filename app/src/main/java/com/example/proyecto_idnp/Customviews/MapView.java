package com.example.proyecto_idnp.Customviews;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.proyecto_idnp.Adaptadores.Cuadro;
import com.example.proyecto_idnp.Modelos.CuadrosViewModel;
import com.example.proyecto_idnp.R;

public class MapView extends View {
    private static final String TAG = "MapView";
    private static final float DRAW_WIDTH = 1050f;
    private static final float DRAW_HEIGHT = 2080f;
    private Paint paintRoom;
    private Paint textRoom;
    private Paint textPatios;
    private Context context;
    private Canvas canvas;
    private Drawable pictureDrawable;
    private CuadrosViewModel itemViewModel;
    private float scaleX, scaleY;

    public MapView(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    private void initPaint() {
        paintRoom = new Paint();
        paintRoom.setColor(Color.GRAY);
        paintRoom.setStyle(Paint.Style.STROKE);
        paintRoom.setStrokeWidth(20);

        textRoom = new Paint();
        textRoom.setColor(Color.RED);
        textRoom.setTextSize(50);
        textRoom.setAntiAlias(true);

        textPatios = new Paint();
        textPatios.setColor(Color.GREEN);
        textPatios.setTextSize(50);
        textPatios.setAntiAlias(true);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        calculateScale();
        drawMapLayout();
        drawNameSpaces();
        drawPicture();
    }

    private void calculateScale(){

        float canvasWidth = canvas.getWidth();
        float canvasHeight = canvas.getHeight();
        scaleX = canvasWidth / DRAW_WIDTH;
        scaleY = canvasHeight / DRAW_HEIGHT;
    }

    private void drawMapLayout() {

        float [][] romms = {

                {25, 20, 1050, 2080}, // Marco
                //{10, 20, 1040, 2070}, // Marco
                {25, 20, 253, 337}, // Administrativo
                {797, 20, 1050, 337}, // Baños

                {253, 337, 684, 654}, // Sala 7
                {25, 337, 253, 971}, // Sala 6

                {253, 1129, 684, 1446}, // Sala 5
                {25, 1129, 253, 1446}, // Sala 4
                {25, 1446, 253, 1763}, // Sala 3
                {25, 1763, 253, 2080}, // Sala 2
                {253, 1763, 684, 2080}, // Sala 1

                {797, 337, 1050, 654}, // Administrativo
                {797, 654, 1050, 1129}, // Espacio libre
                {797, 1129, 1050, 1446}, // Sala 8
                {797, 1446, 1050, 1763}, // Sala 9
                {797, 1763, 1050, 2080}, // Sala 10
        };

        for (float[] array : romms) {
            canvas.drawRect(array[0] * scaleX, array[1] * scaleY,array[2] * scaleX, array[3] * scaleY, paintRoom);
        }
    }

    private void drawNameSpaces() {
        drawNamesRooms();
        drawNamesPatiosAndBathroom();
        drawEntrance();
    }

    private void drawNamesRooms() {
        float[][] roomCenters = {
                {410, 1940}, // Sala 1
                {74, 1940}, // Sala 2
                {74, 1627}, // Sala 3
                {74, 1310}, // Sala 4
                {410, 1310}, // Sala 5
                {74, 654}, // Sala 6
                {410, 505}, // Sala 7
                {860, 1310}, // Sala 8
                {860, 1627}, // Sala 9
                {860, 1940}  // Sala 10
        };
        int aux = 1;
        for (float[] array : roomCenters) {
            canvas.drawText("Sala " + (aux++), array[0] * scaleX, array[1] * scaleY, textRoom);
        }
    }

    private void drawNamesPatiosAndBathroom() {
        canvas.drawText("Patio 1", 410 * scaleX, 1627 * scaleY, textPatios);
        canvas.drawText("Patio 2", 410 * scaleX, 903 * scaleY, textPatios);
        canvas.drawText("Patio 3", 410 * scaleX, 198 * scaleY, textPatios);
        canvas.drawText("Baños", 860 * scaleX, 198 * scaleY, textPatios);
    }

    private void drawEntrance() {
        canvas.save();
        canvas.rotate(270, 750 * scaleX, 2050 * scaleY);
        canvas.drawText("Ingreso", 750 * scaleX, 2050 * scaleY, textPatios);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec,  heightSpec);
        int width = MeasureSpec.getSize(widthSpec);
        int height = (int) (DRAW_HEIGHT * (width / DRAW_WIDTH));
        setMeasuredDimension(width, height);
        invalidate();
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
            Log.d(TAG, "Dibujando icono de cuadro ");
        }
    }

    public void setListener(CuadrosViewModel itemViewModel) {
        this.itemViewModel = itemViewModel;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (pictureDrawable != null && pictureDrawable.getBounds().contains(pointX, pointY)) {
                    itemViewModel.setCuadroSeleccionadoPorId(1);
                }
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
}

