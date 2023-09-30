package com.starfall.entity;
//用于计算经验值
public class Exp {
    private int level;
    private int exp;
    private int MaxExp;
    private int present;
    public Exp(int level, int exp){
        this.level = level;
        this.exp = exp;
        this.MaxExp = iMaxExp(this.level);
        this.present = iPercent(this.level,this.exp,this.MaxExp);
    }
    public int iPercent(int level,int exp,int maxExp){
        int beforeExp = iMaxExp(level-1);
        double num1 =  maxExp - beforeExp;
        double num2 = exp - beforeExp;
        double result = num2 / num1;
        return (int) (result*100);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMaxExp() {
        return MaxExp;
    }

    public void setMaxExp(int maxExp) {
        MaxExp = maxExp;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "Exp{" +
                "level=" + level +
                ", exp=" + exp +
                ", MaxExp=" + MaxExp +
                ", present=" + present +
                '}';
    }
    public int iMaxExp(int level) {
        switch (level){
            case 0:
                return 100;
            case 1:
                return 240;
            case 2:
                return 689;
            case 3:
                return 1689;
            case 4:
                return 3489;
            case 5:
                return 5456;
            case 6:
                return 8214;
            case 7:
                return 12089;
            case 8:
                return 18345;
            case 9:
                return 26847;
            case 10:
                return 38745;
            case 11:
                return 49532;
            case 12:
                return 70392;
            case 13:
                return 99999;
        }
        return 0;
    }

}
