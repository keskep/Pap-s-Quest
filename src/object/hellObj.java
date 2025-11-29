package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class hellObj extends SuperObject{
    
    GamePanel gp;

    public hellObj(GamePanel gp) {

        this.gp = gp;

        name = "Hell";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/hell.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
