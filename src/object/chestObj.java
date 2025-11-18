package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class chestObj extends SuperObject{

    public chestObj() {

        name = "Chest";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}