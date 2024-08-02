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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//import com.example.proyecto_idnp.Adaptadores.Cuadro;
//import com.example.proyecto_idnp.Modelos.CuadrosViewModel;

import com.example.proyecto_idnp.Modelos.CanvasViewModel;
import com.example.proyecto_idnp.Modelos.ObrasViewModel;
import com.example.proyecto_idnp.R;

import java.util.ArrayList;
import java.util.List;

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
    private float scaleX, scaleY;
    private ObrasViewModel itemViewModel;
    private CanvasViewModel canvasModel;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private float [][] romms;
    private ArrayList<Icon> icons = new ArrayList<>();

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
        romms = new float[][] {
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
//        pictureDrawable = AppCompatResources.getDrawable(context, R.drawable.icon_cuadro);
//        if (pictureDrawable != null) {
//            int left = (int) (506 * scaleX);
//            int top = (int) (2020 * scaleY);
//            int right = (int) (556 * scaleX);
//            int bottom = (int) (2070 * scaleY);
//            pictureDrawable.setBounds(left, top, right, bottom);
//            pictureDrawable.draw(canvas);
//        }

        // ==============================
        icons.clear();
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

        for (float[] center : roomCenters) {
            for (int i = 0; i < 1; i++) {
                Drawable drawable = AppCompatResources.getDrawable(context, R.drawable.icon_cuadro);
                if (drawable != null) {
                    float offsetX = (i % 2) * 50;
                    float offsetY = (i / 2) * 50;
                    float left = center[0] - 30 + offsetX;
                    float top = center[1] + 80 - offsetY;
                    float right = center[0] + 20 + offsetX;
                    float bottom = center[1] + 130 - offsetY;
                    icons.add(new Icon(drawable, left, top, right, bottom));
                }
            }
        }

        for (Icon icon : icons) {
            icon.draw(canvas, scaleX, scaleY);
        }

    }

    public void setListenerPictures(ObrasViewModel itemViewModel){
        this.itemViewModel = itemViewModel;
    }
    public void setListenerRooms(CanvasViewModel canvasModel){
        this.canvasModel = canvasModel;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();
        int x_negative = (int) (253 * scaleX);
        int x_positive = (int) (684 * scaleX);
        int y_negative = (int) (1763 * scaleX);
        int y_positive = (int) (2080 * scaleX);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (pointX >= x_negative && pointX <= x_positive && pointY >= y_negative && pointY <= y_positive) {
                    canvasModel.setSelectRoomById(2);
                    Log.d("MapView","onTouchEvent Puntos " + pointX + " " + pointY );
                } else {
                    for (Icon icon : icons) {
                        if (icon.contains(pointX, pointY, scaleX, scaleY)) {
                            Log.d(TAG, "Icon touched at " + pointX + ", " + pointY);
                            break;
                        }
                    }
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

    private static class Icon {
        Drawable drawable;
        float left;
        float top;
        float right;
        float bottom;

        Icon(Drawable drawable, float left, float top, float right, float bottom) {
            this.drawable = drawable;
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        void draw(Canvas canvas, float scaleX, float scaleY) {
            drawable.setBounds((int) (left * scaleX), (int) (top * scaleY), (int) (right * scaleX), (int) (bottom * scaleY));
            drawable.draw(canvas);
        }

        boolean contains(float x, float y, float scaleX, float scaleY) {
            return x >= (left * scaleX) && x <= (right * scaleX) && y >= (top * scaleY) && y <= (bottom * scaleY);
        }
    }
}

