package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class chestObj extends SuperObject{
    
    GamePanel gp;

    public chestObj(GamePanel gp) {

        this.gp = gp;

        name = "Chest";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/chest.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}