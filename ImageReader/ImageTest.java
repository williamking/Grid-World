/*************************************************************************
    > File Name: ImageTest.java
    > Author: william
    > Mail: williamjwking@gmail.com
*/

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import imagereader.*;

public class ImageTest {
    private IImageProcessor1 processor = new IImageProcessor1();
    private IImageIO1 io = new IImageIO1();
    private String srcPath = "/home/william/Grid-World/ImageReader/bmptest/";
    private String distPath = "/home/william/Grid-World/ImageReader/bmptest/goal/";
    private String srcName = "1.bmp";
    private String redName = "1_red.bmp";
    private String blueName = "1_blue.bmp";
    private String greenName = "1_green.bmp";
    private String grayName ="1_gray.bmp";
    private String redGoalName = "1_red_goal.bmp";
    private String blueGoalName = "1_blue_goal.bmp";
    private String greenGoalName = "1_green_goal.bmp";
    private String grayGoalName ="1_gray_goal.bmp";
    
    //Produce new image file
    @Before
    public void setUp() throws Exception{} {
        Image img = io.myRead(srcPath + srcName);
        Image redImg = processor.showChanelR(img);
        Image greenImg = processor.showChanelG(img);
        Image blueImg = processor.showChanelB(img);
        Image grayImg = processor.showGray(img);
        io.myWrite(redImg, distPath + redName);
        io.myWrite(greenImg, distPath + greenName);
        io.myWrite(blueImg, distPath + blueName);
        io.myWrite(grayImg, distPath + grayName);
    }

    @After
    public void over() {
        System.out.println("Test over.");
    }

    @Test
    public void testFiles() {
        //Test red image
        try {
        BufferedImage img = ImageIO.read(new File(distPath + redName));
        WritableRaster raster1 = img.getRaster();
        BufferedImage img2 = ImageIO.read(new File(distPath +redGoalName));
        WritableRaster raster2 = img2.getRaster();
        //Test image width and height
        assertEquals(img.getWidth(null), img2.getWidth(null));
        assertEquals(img.getHeight(null), img2.getHeight(null));
        int[] p = new int[img.getWidth(null) * img.getHeight(null)];
        int[] p2 = new int[img2.getWidth(null) * img2.getHeight(null)];
        int[] pixels1 = raster1.getPixels(0, 0, raster1.getWidth(), raster1.getHeight(), p);
        int[] pixels2 = raster2.getPixels(0, 0, raster2.getWidth(), raster2.getHeight(), p2);
        assertEquals(pixels1.equals(pixels2), true);


        img = ImageIO.read(new File(distPath + greenName));
        raster1 = img.getRaster();
        img2 = ImageIO.read(new File(distPath + greenGoalName));
        raster2 = img2.getRaster();
        //Test image width and height
        System.out.println("Test size");
        assertEquals(img.getWidth(null), img2.getWidth(null));
        assertEquals(img.getHeight(null), img2.getHeight(null));
        //Test pixels
        pixels1 = raster1.getPixels(0, 0, raster1.getWidth(), raster1.getHeight(), p);
        pixels2 = raster2.getPixels(0, 0, raster2.getWidth(), raster2.getHeight(), p2);
        assertEquals(pixels1.equals(pixels2), true);

        img = ImageIO.read(new File(distPath + blueName));
        raster1 = img.getRaster();
        img2 = ImageIO.read(new File(distPath +blueGoalName));
        raster2 = img2.getRaster();
        //Test image width and height
        System.out.println("Test size");
        assertEquals(img.getWidth(null), img2.getWidth(null));
        assertEquals(img.getHeight(null), img2.getHeight(null));
        //Test pixels
        pixels1 = raster1.getPixels(0, 0, raster1.getWidth(), raster1.getHeight(), p);
        pixels2 = raster2.getPixels(0, 0, raster2.getWidth(), raster2.getHeight(), p2);
        assertEquals(pixels1.equals(pixels2), true);

        img = ImageIO.read(new File(distPath + grayName));
        raster1 = img.getRaster();
        img2 = ImageIO.read(new File(distPath + grayGoalName));
        raster2 = img2.getRaster();
        //Test image width and height
        System.out.println("Test size");
        assertEquals(img.getWidth(null), img2.getWidth(null));
        assertEquals(img.getHeight(null), img2.getHeight(null));
        //Test pixels
        pixels1 = raster1.getPixels(0, 0, raster1.getWidth(), raster1.getHeight(), p);
        pixels2 = raster2.getPixels(0, 0, raster2.getWidth(), raster2.getHeight(), p2);
        assertEquals(pixels1.equals(pixels2), true);

        } catch (Exception e) {
            System.out.print("Error");
        } 
    }

}
