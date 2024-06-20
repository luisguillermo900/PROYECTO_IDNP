package com.example.proyecto_idnp.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class MapView extends View {
    private Paint paintRoom;
    private Paint textRoom;
    private Paint textPatios;
    private Context context;
    private Canvas canvas;

    public MapView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
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
        drawMapLayout();
        drawMarkers();
        drawNameSpaces();
    }

    private void drawMapLayout() {
        canvas.drawRect(25, 20, 1050, 337, paintRoom); // Marco

        canvas.drawRect(25, 20, 253, 337, paintRoom);      // Baños
        canvas.drawRect(797, 20, 1050, 337, paintRoom);    // Baños

        canvas.drawRect(253, 337, 684, 654, paintRoom);    // Sala 7
        canvas.drawRect(25, 337, 253, 971, paintRoom);     // Sala 6

        canvas.drawRect(253, 1129, 684, 1446, paintRoom);  // Sala 5
        canvas.drawRect(25, 1129, 253, 1446, paintRoom);   // Sala 4
        canvas.drawRect(25, 1446, 253, 1763, paintRoom);   // Sala 3
        canvas.drawRect(25, 1763, 253, 2080, paintRoom);   // Sala 2
        canvas.drawRect(253, 1763, 684, 2080, paintRoom);  // Sala 1

        canvas.drawRect(797, 337, 1050, 654, paintRoom);   // Administrativo
        canvas.drawRect(797, 654, 1050, 1129, paintRoom);  // Espacio libre
        canvas.drawRect(797, 1129, 1050, 1446, paintRoom); // Sala 8
        canvas.drawRect(797, 1446, 1050, 1763, paintRoom); // Sala 9
        canvas.drawRect(797, 1763, 1050, 2080, paintRoom); // Sala 10
    }

    private void drawMarkers() {
        paintRoom.setColor(Color.RED);
        paintRoom.setStyle(Paint.Style.FILL);
        canvas.drawCircle(456, 1980, 10, paintRoom);
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
            canvas.drawText("Sala " + (aux++), array[0], array[1], textRoom);
        }
    }

    private void drawNamesPatiosAndBathroom() {
        canvas.drawText("Patio 1", 410, 1627, textPatios);
        canvas.drawText("Patio 2", 410, 903, textPatios);
        canvas.drawText("Patio 3", 410, 198, textPatios);
        canvas.drawText("Baños", 860, 198, textPatios);
    }

    private void drawEntrance() {
        canvas.save();
        canvas.rotate(270, 750, 2050);
        canvas.drawText("Ingreso", 750, 2050, textPatios);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int contentWidth = 1100;
        int contentHeight = 2200;
        setMeasuredDimension(contentWidth, contentHeight);
    }
}

