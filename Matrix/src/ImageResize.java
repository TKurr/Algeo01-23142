import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;

public class ImageResize {
    public static void main(String filename, int newHeight, int newWidth) throws IOException {
        try {
            File input_file = Paths.get("public/" + filename).toFile();

            BufferedImage image = ImageIO.read(input_file);

            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();

            BufferedImage outputImage = new BufferedImage(newWidth, newHeight, image.getType());
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    int srcX = x * originalWidth / newWidth;
                    int srcY = y * originalHeight / newHeight;
                    int pixel = image.getRGB(srcX, srcY);
                    outputImage.setRGB(x, y, pixel);
                }
            }

            WritableRaster raster = outputImage.getRaster();
            if (originalWidth < newWidth || originalHeight < newHeight) {
                for (int x = 0; x < newWidth - 3; x += 1) {
                    for (int y = 0; y < newHeight - 3; y += 1) {
                        double[][] inputMatrixRed = new double[4][4];
                        double[][] inputMatrixGreen = new double[4][4];
                        double[][] inputMatrixBlue = new double[4][4];

                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                int[] pixel = new int[4];
                                raster.getPixel(x + i, y + j, pixel);
                                inputMatrixRed[i][j] = pixel[0];
                                inputMatrixGreen[i][j] = pixel[1];
                                inputMatrixBlue[i][j] = pixel[2];
                            }
                        }

                        inputMatrixRed = BicubicSpline.pixelSmoothening(inputMatrixRed);
                        inputMatrixGreen = BicubicSpline.pixelSmoothening(inputMatrixGreen);
                        inputMatrixBlue = BicubicSpline.pixelSmoothening(inputMatrixBlue);

                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                int red = (int) Math.max(0, Math.min(255, inputMatrixRed[i][j]));
                                int green = (int) Math.max(0, Math.min(255, inputMatrixGreen[i][j]));
                                int blue = (int) Math.max(0, Math.min(255, inputMatrixBlue[i][j]));
                                int[] pixel = { red, green, blue, 255 };
                                raster.setPixel(x + i, y + j, pixel);
                            }
                        }
                    }
                }
            }
            File output_file = Paths.get("test/output/images/" + filename).toFile();
            ImageIO.write(outputImage, "png", output_file);

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
