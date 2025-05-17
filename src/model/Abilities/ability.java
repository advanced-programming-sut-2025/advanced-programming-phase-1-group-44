package model.Abilities;

public abstract class ability {
    public int level;
    public int xp;
    public void levelUp(){
        if(this.level < 4 && this.xp >= (this.level + 1) * 100 + 50){
            this.level++;
        }
    }
    public void addXP(int x){
        this.xp += x;
        this.levelUp();
    }
}
