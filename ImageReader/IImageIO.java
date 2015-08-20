/*************************************************************************
    > File Name: IImageIO.java
    > Author: william
    > Mail: williamjwking@gmail.com
*/
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;

public IImageIO {
    public Image myRead(String filePath) {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        byte[] b = new byte[file.length];
        dis.read(b); 
    }
}

