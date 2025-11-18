package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class jamesonObj extends SuperObject{

    public jamesonObj() {

        name = "Jameson";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/jameson.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}