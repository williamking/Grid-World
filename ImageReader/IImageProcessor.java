/*************************************************************************
    > File Name: IImageProcessor.java
    > Author: william
    > Mail: williamjwking@gmail.com
*/

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;


public class IImageProcessor {
    
    public byte[] getImageData(Image srcImage) {
        BufferedImage src = new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] imgData = null;
        try {
            ImageIO.write(src, "bmp", bos);
            imgData = bos.toByteArray();
        } catch (Exception e){
            System.out.println("Err src!");
        }
        return imgData;
    }

    public int getWidth(byte[] data) {
        return (int)(data[18]);
    }

    public int getHeight(byte[] data) {
        return (int)(data[22]);
    }

    public int getDataAddress(byte[] data) {
        return (int)(data[10]);
    }

    public int getSize(byte[] data) {
        return (int)(data[2]);
    }

    public Image showChanelR(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        int width = getWidth(imgData);
        int height = getHeight(imgData);
        int addr = getDataAddress(imgData);
        int length = getSize(imgData);
        int rowByteNum = width * 3;
        if (rowByteNum % 4 != 0)
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                imgData[addr + i * rowByteNum + j * 3] = 0;
                imgData[addr + i * rowByteNum + j * 3 + 1] = 0;
            }
        }
        return Toolkit.getDefaultToolkit().createImage(imgData); 
    }

    public Image showChanelG(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        int width = getWidth(imgData);
        int height = getHeight(imgData);
        int addr = getDataAddress(imgData);
        int length = getSize(imgData);
        int rowByteNum = width * 3;
        if (rowByteNum % 4 != 0)
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                imgData[addr + i * rowByteNum + j * 3] = 0;
                imgData[addr + i * rowByteNum + j * 3 + 2] = 0;
            }
        }
        return Toolkit.getDefaultToolkit().createImage(imgData); 
    }

    public Image showChanelB(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        int width = getWidth(imgData);
        int height = getHeight(imgData);
        int addr = getDataAddress(imgData);
        int length = getSize(imgData);
        int rowByteNum = width * 3;
        if (rowByteNum % 4 != 0)
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                imgData[addr + i * rowByteNum + j * 3 + 2] = 0;
                imgData[addr + i * rowByteNum + j * 3 + 1] = 0;
            }
        }
        return Toolkit.getDefaultToolkit().createImage(imgData); 
    }
}
