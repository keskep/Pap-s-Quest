package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class keyObj extends SuperObject{

    public keyObj() {

        name = "Key";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
