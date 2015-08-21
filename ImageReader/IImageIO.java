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
import java.imageio.ImageIO;

public IImageIO {

    public Image myRead(String filePath) {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        byte[] imageData = new byte[file.length];
        dis.read(imageData); 
        return Toolkit.createImage(imageData);
    }

    public Image myWrite(Image image, String filePath) {
        ImageIO.write(image, "bmp", newFile(filePath));_
    }

}

