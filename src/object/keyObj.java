package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class keyObj extends SuperObject{
    
    GamePanel gp;

    public keyObj(GamePanel gp) {
        
        this.gp = gp;

        name = "Key";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/key.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
