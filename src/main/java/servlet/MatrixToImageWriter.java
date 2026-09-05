package Servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.google.zxing.common.BitMatrix;

class MatrixToImageWriter {

    static void writeToStream(BitMatrix matrix, String png, ServletOutputStream outputStream) throws IOException {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }

        try {
            ImageIO.write(image, png, outputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write QR code image to servlet output stream", e);
        }
    }
}
