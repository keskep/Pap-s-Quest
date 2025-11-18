package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class hellObj extends SuperObject{

    public hellObj() {

        name = "Hell";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/hell.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
