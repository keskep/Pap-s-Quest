package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;
    boolean isMoving = false;
    int pixelCounter = 0;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // Subtract half a tile length from both screenX & screenY
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // making player actually centered

        solidArea = new Rectangle(); // Makes character's collision smaller
        solidArea.x = 1;
        solidArea.y = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 46;
        solidArea.height = 46;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        
        up1 = setup("pap_up_1");
        up2 = setup("pap_up_2");
        down1 = setup("pap_down_1");
        down2 = setup("pap_down_2");
        left1 = setup("pap_left_1");
        left2 = setup("pap_left_2");
        right1 = setup("pap_right_1");
        right2 = setup("pap_right_2");
    }
    
    public BufferedImage setup(String imageName) {

        UtilityTools uTools = new UtilityTools();
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/" + imageName + ".png"));
            image = uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void update(){

        if (!isMoving) {
            if (keyH.upPressed == true || keyH.downPressed == true // No animations when idle
                    || keyH.leftPressed == true || keyH.rightPressed == true) {

                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                isMoving = true;

                // Check Tile Collision
                collisionOn = false;
                gp.cCheck.checkTile(this); // Check tile collision

                // Check Object Collision
                int objIndex = gp.cCheck.checkObject(this, true);
                pickUpObject(objIndex);
            } else {
                standCounter++;
                if (standCounter == 20) {
                    spriteNum = 1; // Standstill animation
                    standCounter = 0;
                }
            }
        }
        if (isMoving) {
            // If collision is false player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed; // case "up": worldY -= speed; break;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            spriteCounter++;
            if (spriteCounter > 10) { // Frequency of animations
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            pixelCounter += speed;

            if (pixelCounter == 48) { // might be a problem when character levels up...
                isMoving = false;
                pixelCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {

        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    gp.playSFX(1);
                    hasKey ++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.playSFX(3);
                        gp.obj[i] = null;
                        hasKey --;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Hell":
                    gp.playSFX(2);
                    gp.obj[i] = null;
                    speed += 2;
                    gp.ui.showMessage("You drank hell!!");
                    break;
                case "Jameson":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSFX(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); //(x, y, width, height)

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);

        // Troubleshoot collisions
        // g2.setColor(Color.red);
        // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
