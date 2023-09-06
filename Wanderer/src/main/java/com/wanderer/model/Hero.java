package com.wanderer.model;

import com.wanderer.mapFunc.MapGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Hero extends Character {
    BufferedImage heroDown;
    BufferedImage heroUp;
    BufferedImage heroLeft;
    BufferedImage heroRight;
    int heroPositionX;
    int HeroPositionY;

    Character target;


    public Hero() {
        this.MaxHP = 38;
        this.HP = 20 + 3 * (int) (6.0 * Math.random() + 1);
        this.DP = 2 * (int) (6.0 * Math.random() + 1);
        this.SP = 5 + (int) (6.0 * Math.random() + 1);

        this.heroPositionX = 0;
        this.HeroPositionY = 0;
        try {
            heroDown = ImageIO.read(new File("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\hero-down.png"));
            heroUp = ImageIO.read(new File("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\hero-up.png"));
            heroLeft = ImageIO.read(new File("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\hero-left.png"));
            heroRight = ImageIO.read(new File("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\hero-right.png"));
        } catch (Exception e) {
            System.out.println("Hero position image not found.");
        }
    }

    @Override
    public void battle(Character npc) {
        target = npc;
    }

    public boolean strike(MapGenerator map) {
        if (target == null) {
            return true;
        }
        int SV = this.SP + (2 * (int) (6.0 * Math.random() + 1));
        if (SV > target.DP) {
            target.HP -= SV;
        }

        if (target.HP <= 0) {
            target.die(map);
            target = null;
            this.levelUp();
            return true;
        }
        target.strike(this);
        if (this.HP <= 0) {
            this.die(map);
        }
        return false;
    }

    public void levelUp() {
        this.LVL++;
        this.MaxHP += (this.LVL) * (int) (6.0 * Math.random() + 1);
        this.DP += (this.LVL) * (int) (6.0 * Math.random() + 1);
        this.SP += (this.LVL) * (int) (6.0 * Math.random() + 1);
    }

    public void heroRegen(){
        int regen = new Random().nextInt(1,11);
        switch (regen) {
            case 1: this.HP = this.MaxHP;

            case 2,3,4,5: if (this.HP + this.HP*0.3 > this.getMaxHP()){
                this.HP = this.MaxHP;
            } else
                this.HP += this.HP*0.3;

            case 6,7,8,9,10: if (this.HP + this.HP*0.1 > this.getMaxHP()) {
                this.HP = this.MaxHP;
            } else this.HP += this.HP*0.1;
        }
    }

    public BufferedImage getHeroDown() {
        return heroDown;
    }

    public BufferedImage getHeroUp() {
        return heroUp;
    }

    public BufferedImage getHeroLeft() {
        return heroLeft;
    }

    public BufferedImage getHeroRight() {
        return heroRight;
    }

    public int getHeroPositionX() {
        return heroPositionX;
    }

    public int getHeroPositionY() {
        return HeroPositionY;
    }

    public void setHeroPositionX(int heroPositionX) {
        this.heroPositionX = heroPositionX;
    }

    public void setHeroPositionY(int heroPositionY) {
        HeroPositionY = heroPositionY;
    }
}
