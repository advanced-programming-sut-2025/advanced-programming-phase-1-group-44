package model;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class AbsoluteImageDrawer {
    private Texture texture;
    private byte[] rawImageData;
    private int originalWidth, originalHeight;

    public AbsoluteImageDrawer(String internalPath) {
        try {
            // 1. لود مستقیم پیکسل‌ها
            Pixmap pixmap = new Pixmap(Gdx.files.internal(internalPath));
            originalWidth = pixmap.getWidth();
            originalHeight = pixmap.getHeight();

            // 2. ذخیره داده‌های خام پیکسلی
            rawImageData = new byte[originalWidth * originalHeight * 4];
//            BufferUtils.copy(pixmap.getPixels(), rawImageData, rawImageData.length);

            // 3. ساخت تکستچر از داده‌های خام
            texture = new Texture(originalWidth, originalHeight, Pixmap.Format.RGBA8888);
            texture.draw(pixmap, 0, 0);

            pixmap.dispose();
        } catch (Exception e) {
            Gdx.app.error("CRITICAL", "Failed to load image: " + e.getMessage());

            // 4. ساخت تصویر قرمز جایگزین برای دیباگ
            createErrorTexture();
        }
    }

    private void createErrorTexture() {
        originalWidth = 64;
        originalHeight = 64;

        Pixmap errorPixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        errorPixmap.setColor(Color.RED);
        errorPixmap.fill();
        errorPixmap.setColor(Color.WHITE);
        errorPixmap.drawLine(0, 0, 64, 64);
        errorPixmap.drawLine(64, 0, 0, 64);

        texture = new Texture(errorPixmap);
        errorPixmap.dispose();
    }

    public void render(SpriteBatch batch, float x, float y, float targetWidth, float targetHeight) {
        if (texture == null) return;

        // 5. محاسبه نسبت بدون هیچ کتابخانه‌ای
        float aspectRatio = (float)originalHeight / originalWidth;
        float finalWidth, finalHeight;

        if (targetHeight / targetWidth > aspectRatio) {
            finalWidth = targetWidth;
            finalHeight = targetWidth * aspectRatio;
        } else {
            finalHeight = targetHeight;
            finalWidth = targetHeight / aspectRatio;
        }

        // 6. رسم با استفاده از داده‌های پایه
        batch.draw(
            texture,
            x + (targetWidth - finalWidth) / 2,
            y + (targetHeight - finalHeight) / 2,
            finalWidth,
            finalHeight
        );

        // 7. ذخیره فریم فعلی برای دیباگ
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            saveDebugScreenshot();
        }
    }

    private void saveDebugScreenshot() {
        try {
            byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
            BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
            PixmapIO.writePNG(Gdx.files.external("debug_screenshot.png"), pixmap);
            pixmap.dispose();
        } catch (Exception e) {
            Gdx.app.error("DEBUG", "Failed to save screenshot: " + e.getMessage());
        }
    }

    public void dispose() {
        if (texture != null) texture.dispose();
    }
}
