package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class bootsObj extends SuperObject{

    public bootsObj() {

        name = "Boots";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
