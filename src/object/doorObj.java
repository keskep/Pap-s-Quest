package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class doorObj extends SuperObject{
    
    GamePanel gp;

    public doorObj(GamePanel gp) {

        this.gp = gp;

        name = "Door";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/door.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}