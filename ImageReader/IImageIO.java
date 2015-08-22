/*************************************************************************
    > File Name: IImageIO.java
    > Author: william
    > Mail: williamjwking@gmail.com
*/
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.*;

public class IImageIO {

    public Image myRead(String filePath) {
        File file = new File(filePath);
        Image img = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            byte[] imageData = new byte[(int)(file.length())];
            dis.read(imageData); 
            img = Toolkit.getDefaultToolkit().createImage(imageData);
        } catch (Exception e) {
            System.out.println("Error path!");
        }
        return img;
    }

    public Image myWrite(Image image, String filePath) {
        BufferedImage src = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        try {
            ImageIO.write(src, "bmp", new File(filePath));
        } catch (Exception e) {
            System.out.println("Error reading!");
        } finally {
            return image;

        }
    }
}

