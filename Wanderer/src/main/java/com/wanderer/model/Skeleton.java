package com.wanderer.model;

import javax.imageio.ImageIO;
import java.io.File;

public class Skeleton extends Character{
    public Skeleton() {
        this.HP = 2 * this.LVL * (int) (6.0 * Math.random() + 1);
        this.DP = this.LVL / 2 * (int) (6.0 * Math.random() + 1);
        this.SP = this.LVL * (int) (6.0 * Math.random() + 1);
        charID = numOfNPCs;
        numOfNPCs++;

        try {
            image = ImageIO.read(new File("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\skeleton.png"));
        } catch (Exception e) {
            System.out.println("Cannot find skeletonImg");
        }
    }
    public void NPCLvlUp(){
        this.LVL++;
        this.HP = 2 * this.LVL * (int) (6.0 * Math.random() + 1);
        this.DP = this.LVL / 2 * (int) (6.0 * Math.random() + 1);
        this.SP = this.LVL * (int) (6.0 * Math.random() + 1);
    }
}
