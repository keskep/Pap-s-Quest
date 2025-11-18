package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class doorObj extends SuperObject{

    public doorObj() {

        name = "Door";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}