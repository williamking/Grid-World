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
import imagereader.*;


public class IImageProcessor1 implements IImageProcessor {
    
    //These are the information addresses in the byte array
    private static final int WIDTH_ADD = 18;
    private static final int HEIGHT_ADD = 22;
    private static final int DATA_ADD = 10;
    private static final int SIZE_ADD = 2;
   
    //Transform an image to byte[] 
    public byte[] getImageData(Image srcImage) {
        BufferedImage src = (BufferedImage)srcImage;
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

    //Get the width according to the byte array of an image
    public int getWidth(byte[] data) {
        int value = (data[WIDTH_ADD] & 0xff) | ((data[WIDTH_ADD + 1] << 8) & 0xff00)
        | ((data[WIDTH_ADD + 2] << 24) >>> 8) | (data[WIDTH_ADD + 3] << 24);
        return value;
    }

    //Get the height according to the byte array of an image
    public int getHeight(byte[] data) {
        int value = (data[HEIGHT_ADD] & 0xff) | ((data[HEIGHT_ADD + 1] << 8) & 0xff00)
        | ((data[HEIGHT_ADD + 2] << 24) >>> 8) | (data[HEIGHT_ADD + 3] << 24);
        return value;
    }

    //Get the image pixel data address in the byte array
    public int getDataAddress(byte[] data) {
        int value = (data[DATA_ADD] & 0xff) | ((data[DATA_ADD + 1] << 8) & 0xff00)
        | ((data[DATA_ADD + 2] << 24) >>> 8) | (data[DATA_ADD + 3] << 24);
        return value;
    }

    //Get the size of image
    public int getSize(byte[] data) {
        int value = (data[SIZE_ADD] & 0xff) | ((data[SIZE_ADD + 1] << 8) & 0xff00)
        | ((data[SIZE_ADD + 2] << 24) >>> 8) | (data[SIZE_ADD + 3] << 24);
        return value;
    }

    //Write the byte array to a file
    public void makeFile(byte[] imgData, String path) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(imgData);
            BufferedImage img = ImageIO.read(in);
            ImageIO.write(img, "bmp", new File(path));
        } catch (Exception e) {
            System.out.println("Creating file failed....");
        }
    }

    //Create a image in red chanel from another image
    public Image showChanelR(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        int width = getWidth(imgData);
        int height = getHeight(imgData);
        int addr = getDataAddress(imgData);
        int rowByteNum = width * 3;
        if (rowByteNum % 4 != 0) {
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        }
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                //Set the chanel to 0 except red
                imgData[addr + i * rowByteNum + j * 3] = 0;
                imgData[addr + i * rowByteNum + j * 3 + 1] = 0;
            }
        }
        return createImage(imgData);
    }

    //Create a image in green chanel from another image
    public Image showChanelG(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        int width = getWidth(imgData);
        int height = getHeight(imgData);
        int addr = getDataAddress(imgData);
        int rowByteNum = width * 3;
        if (rowByteNum % 4 != 0) {
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        }
        //Each pixel is 3 bytes, 
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                //Set the chanel to 0 except green
                imgData[addr + i * rowByteNum + j * 3] = 0;
                imgData[addr + i * rowByteNum + j * 3 + 2] = 0;
            }
        }
        return createImage(imgData);
    }

    //Create a image in blue chanel from another image
    public Image showChanelB(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        int width = getWidth(imgData);
        int height = getHeight(imgData);
        int addr = getDataAddress(imgData);
        int rowByteNum = width * 3;
        if (rowByteNum % 4 != 0) {
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        }
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                //Set the chanel to 0 except blue
                imgData[addr + i * rowByteNum + j * 3 + 2] = 0;
                imgData[addr + i * rowByteNum + j * 3 + 1] = 0;
            }
        }
        return createImage(imgData);
    }
    
    //Create a gray image from another image
    public Image showGray(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        int width = getWidth(imgData);
        int height = getHeight(imgData);
        int addr = getDataAddress(imgData);
        int rowByteNum = width * 3;
        if (rowByteNum % 4 != 0) {
            rowByteNum = (rowByteNum / 4 + 1) * 4;
        }
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                // Caculate the gray value
                double grey1 = ((int)(imgData[addr + i * rowByteNum + j * 3]) & 0x000000ff) * 0.114 + ((int)(imgData[addr + i * rowByteNum + j * 3 + 1]) & 0x000000ff) * 0.587 + ((int)(imgData[addr + i * rowByteNum + j * 3 + 2]) & 0x000000ff) * 0.299;
                double grey = Math.ceil(grey1) - 1;
                //Set the chanel of each pixel to gray value
                imgData[addr + i * rowByteNum + j * 3 + 2] = (byte)(int)grey;
                imgData[addr + i * rowByteNum + j * 3 + 1] = (byte)(int)grey;
                imgData[addr + i * rowByteNum + j * 3] = (byte)(int)grey;
            }
        }
        return createImage(imgData);
    }

    public BufferedImage createImage(byte[] imgData) {
        BufferedImage rImg = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(imgData);
            rImg = ImageIO.read(in); 
        } catch (Exception e) {
            System.out.println("Failed");
        } 
        return rImg;
    }
}
