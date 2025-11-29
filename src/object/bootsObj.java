package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class bootsObj extends SuperObject{
    
    GamePanel gp;

    public bootsObj(GamePanel gp) {

        this.gp = gp;

        name = "Boots";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/boots.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
