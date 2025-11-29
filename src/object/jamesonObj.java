package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class jamesonObj extends SuperObject{
    
    GamePanel gp;

    public jamesonObj(GamePanel gp) {

        this.gp = gp;

        name = "Jameson";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/jameson.png"));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}