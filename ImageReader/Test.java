import imagereader.*;

public class Test {
    //This is then main program
    public static void main(String[] args) { 
        IImageIO1 iimageio = new IImageIO1();
        IImageProcessor1 iimageprocessor = new IImageProcessor1();
        Runner runner = new Runner();
        runner.run(iimageio, iimageprocessor);
    }
}
