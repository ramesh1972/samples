/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package ai.djl.examples.quickdraw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.translate.TranslateException;

public final class PaintView extends View {

    private static final int BRUSH_SIZE = 20;
    private static final int DEFAULT_PAINT_COLOR = Color.WHITE;
    private static final int DEFAULT_BG_COLOR = Color.BLACK;

    private static final float TOUCH_TOLERANCE = 4;
    private static final ColorDrawable BACKGROUND = new ColorDrawable(Color.BLACK);

    private float x;
    private float y;
    private Path path;
    private ArrayList<Path> paths = new ArrayList<>();
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private Paint bitmapPaint = new Paint(Paint.DITHER_FLAG);
    private Paint textPaint;
    private ImageView imageView;
    private TextView textView;
    private int textSize;
    private Bound maxBound;
    private ImageFactory factory;
    private Predictor<Image, Classifications> predictor;

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        factory = ImageFactory.getInstance();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(DEFAULT_PAINT_COLOR);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAlpha(0xff);
        textSize = context.getResources().getDimensionPixelSize(R.dimen.text_size);
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
    }

    public void init(DisplayMetrics metrics, ImageView imageView, TextView textView, Predictor<Image, Classifications> predictor) {
        this.imageView = imageView;
        this.textView = textView;
        this.predictor = predictor;
        int width = metrics.widthPixels;
        int height = Math.min(width, metrics.heightPixels);

        maxBound = new Bound();
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    public void clear() {
        paths.clear();
        maxBound = new Bound();
        imageView.setImageDrawable(BACKGROUND);
        invalidate();
    }

    public void share() {
        Context context = getContext();
        String result = textView.getText().toString();
        if (result.isEmpty()) {
            Toast.makeText(context, "Please draw something first", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] lines = result.split("\n");

        int width = getWidth();
        int height = getHeight();
        int newHeight = height + 6 * textSize;
        Bitmap newBitmap = Bitmap.createBitmap(width, newHeight, Bitmap.Config.ARGB_8888);
        Canvas newCanvas = new Canvas(newBitmap);
        newCanvas.drawColor(Color.BLACK);
        newCanvas.drawBitmap(bitmap, 0, 0, null);
        textPaint.setTextAlign(Paint.Align.LEFT);
        int position = height;
        for (String line : lines) {
            newCanvas.drawText(line, textSize, position, textPaint);
            position += textSize;
        }
        textPaint.setTextAlign(Paint.Align.RIGHT);
        newCanvas.drawText("Powered by DJL (https://djl.ai)", width - textSize, position, textPaint);

        File cachePath = new File(context.getCacheDir(), "images");
        cachePath.mkdirs();
        try (FileOutputStream is = new FileOutputStream(cachePath + "/image.png")) {
            newBitmap.compress(Bitmap.CompressFormat.PNG, 100, is);
        } catch (IOException e) {
            Log.e("DoodleDraw", null, e);
        }
        File imagePath = new File(context.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(context, "ai.djl.examples.quickdraw.fileprovider", newFile);
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        this.canvas.drawColor(DEFAULT_BG_COLOR);

        for (Path path : paths) {
            paint.setColor(DEFAULT_PAINT_COLOR);
            paint.setStrokeWidth(BRUSH_SIZE);
            this.canvas.drawPath(path, paint);
        }
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y) {
        path = new Path();
        paths.add(path);
        path.reset();
        path.moveTo(x, y);
        this.x = x;
        this.y = y;
    }

    private void touchMove(float x, float y) {
        if (x < 0 || x > getWidth() || y < 0 || y > getHeight()) {
            return;
        }
        float dx = Math.abs(x - this.x);
        float dy = Math.abs(y - this.y);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(this.x, this.y, (x + this.x) / 2, (y + this.y) / 2);
            this.x = x;
            this.y = y;
        }
    }

    private void touchUp() {
        path.lineTo(this.x, this.y);
        maxBound.add(new Path(path));
    }

    @SuppressLint("DefaultLocale")
    public void runInference() {
        RectF bound = maxBound.getBound();
        int x = (int) bound.left;
        int y = (int) bound.top;
        int width = (int) Math.ceil(bound.width());
        int height = (int) Math.ceil(bound.height());
        //width must be >0
        if (width <=0) return;
        //y+height must be <= bitmap.height()
        if (y+height>bitmap.getHeight()) return;
        // do crop
        Bitmap bmp = Bitmap.createBitmap(bitmap, x, y, width, height);
        // do scaling
        Bitmap bmp64 = Bitmap.createScaledBitmap(bmp, 64, 64, true);
        try {
            Classifications classifications = predictor.predict(factory.fromImage(bmp64));
            imageView.setImageBitmap(bmp);

            List<Classifications.Classification> list = classifications.topK(3);
            StringBuilder sb = new StringBuilder();
            for (Classifications.Classification classification : list) {
                sb.append(classification.getClassName())
                        .append(": ")
                        .append(String.format("%.2f%%", 100 * classification.getProbability()))
                        .append("\n");
            }
            textView.setText(sb.toString());
        } catch (TranslateException e) {
            Log.e("DoodleDraw", null, e);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                runInference();
                invalidate();
                break;
        }

        return true;
    }
}
