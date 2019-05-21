package org.yeheng.dogavoider;

import android.util.Log;
import android.widget.ImageView;

public class BackgroundScroller extends Thread {
    private ImageView iv1;
    private ImageView iv2;
    private int viewportPixels;
    public BackgroundScroller(ImageView iv1, ImageView iv2, int viewportPixels) {
        this.iv1 = iv1;
        this.iv2 = iv2;
        this.viewportPixels = viewportPixels;
    }
    @Override
    public void run() {
        while(true) {
            iv1.post(new Runnable() {
                @Override
                public void run() {
                    if (iv2.getY() < 0) {
                        iv1.setY(iv1.getY() + 1);
                    } else {
                        // Move iv1 to the top
                        iv1.setY(0);
                    }
                }
            });
            iv2.post(new Runnable() {
                @Override
                public void run() {
                    iv2.setY(iv1.getY() - viewportPixels);

                }
            });
            Log.d("BackgroundScroller", "iv1.Y = " + iv1.getY() + " iv2.Y = " + (iv1.getY() - viewportPixels) + " iv2.height = " + iv2.getHeight());

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
