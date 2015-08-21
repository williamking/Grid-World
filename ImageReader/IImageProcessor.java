/*************************************************************************
    > File Name: IImageProcessor.java
    > Author: william
    > Mail: williamjwking@gmail.com
*/

import java.awt.Image;
import java.awt.image.*;
import java.imageio.*;
import java.io.*;


public IImageProcessor {
    
    public byte[] getImageData(Image srcImage) {
        BufferImage src = new BufferImage(srcImage.getWidth(null), srcImage.getHeight(null), BufferImage.TYPE_INT_ARGB);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(src, "bmp", bos);
        return bos.toByteArray(); 
    }

    public showChanelR(Image srcImage) {
        byte[] imgData = getImageData(srcImage);
        
    }

}
