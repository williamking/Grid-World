import imagereader.*;

public class Test {
    public static void main(String[] args) { 
        IImageIO1 iimageio = new IImageIO1();
        IImageProcessor1 iimageprocessor = new IImageProcessor1();
        Runner runner = new Runner();
        runner.run(iimageio, iimageprocessor);
    }
}
