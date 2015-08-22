/*************************************************************************
    > File Name: IImageIO.java
    > Author: william
    > Mail: williamjwking@gmail.com
*/
import java.awt.Image;
import imagereader.IImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.*;

public class IImageIO1 implements IImageIO {

    public Image myRead(String filePath) {
        File file = new File(filePath);
        System.out.println(filePath);
        BufferedImage img = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            int length = (int)(file.length());
            ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
            byte[] imageData = new byte[length];
            int n;
            while ((n = fis.read(imageData)) != -1) {
               bos.write(imageData, 0, n); 
            }
            fis.close();
            bos.close();
            byte[] bb = bos.toByteArray();
            //img = Toolkit.getDefaultToolkit().createImage(bb);
            ByteArrayInputStream in = new ByteArrayInputStream(bb);
            img = ImageIO.read(in);
            ImageIO.write(img, "bmp", new File("/home/william/Documents/233.bmp"));
            System.out.println(length);
            System.out.println(bb.length);
            System.out.println(Integer.toHexString(bb[56] & 0xFF));
            System.out.println(img.getWidth(null));
        } catch (Exception e) {
            System.out.println("Error path!");
        }
        return img;
    }

    public Image myWrite(Image image, String filePath) {
        //BufferedImage src = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        BufferedImage src = (BufferedImage)image;
        try {
            /**
            File file = new File(filePath);
            if (!file.exists())
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    System.out.println("Creating fail!");
                }
            */
            System.out.println(src.getWidth(null));
            System.out.println(ImageIO.write(src, "BMP", new File(filePath)));
        } catch (Exception e) {
            System.out.println("Error writing!");
        } finally {
            return src;
        }
    }
}

