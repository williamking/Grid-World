package stage3;

import imagereader.IImageProcessor;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class NewImageProcesser implements IImageProcessor{
	public Image showChanelB(Image toBeProcessed) {
		ColorFilter blue = new ColorFilter("blue");
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(toBeProcessed.getSource(), blue));  
        return img;
	}

	@Override
	public Image showChanelG(Image toBeProcessed) {
		ColorFilter green = new ColorFilter("green");
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(toBeProcessed.getSource(), green));  
        return img;
	}

	@Override
	public Image showChanelR(Image toBeProcessed) {
		ColorFilter red = new ColorFilter("red");
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(toBeProcessed.getSource(), red));  
        return img;
	}

	@Override
	public Image showGray(Image toBeProcessed) {
		ColorFilter gray = new ColorFilter("gray");
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(toBeProcessed.getSource(), gray));  
        return img;
	}

}
class ColorFilter extends RGBImageFilter {
    private String colorString = "";
	ColorFilter(String color) {
		colorString = color;
		canFilterIndexColorModel = true;
	}
	@Override
	public int filterRGB(int x, int y, int rgb) {
		if (colorString == "red") {
			return (0xFFFF0000 & rgb);
		}
		if (colorString == "green") {
			return (0xFF00FF00 & rgb);
		}
		if (colorString == "blue") {
			return (0xFF0000FF & rgb);
		}
		if (colorString == "gray") {
			int newRGB = (int) (((0x00FF0000 & rgb) >> 16) * 0.299 + ((0x0000FF00 & rgb ) >> 8) * 0.587 +
					            (0x000000FF & rgb) * 0.114);
			return 0xFF000000 + (newRGB << 16) + (newRGB << 8) + newRGB;
		}
		return 0;
	}
	
}