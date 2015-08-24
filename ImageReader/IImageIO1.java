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
import java.awt.image.*;
import javax.imageio.*;

public class IImageIO1 implements IImageIO {
    
    //For creating an image from a bmp file
    public Image myRead(String filePath) {
        File file = new File(filePath);
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
            //Use byte array to create an image
            ByteArrayInputStream in = new ByteArrayInputStream(bb);
            img = ImageIO.read(in);
        } catch (Exception e) {
            System.out.println("Error path!");
        }
        return img;
    }

    //For creating an bmp file from an image
    public Image myWrite(Image image, String filePath) {
        BufferedImage src = (BufferedImage)image;
        //This part I use the java interface ImageIO.write()
        try {
            ImageIO.write(src, "BMP", new File(filePath));
        } catch (Exception e) {
            System.out.println("Error writing!");
        } finally {
            return src;
        }
    }
}

