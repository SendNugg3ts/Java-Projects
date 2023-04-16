package Entities;

public abstract class MainEntity {
    protected  float x,y;
    protected int width, height;
    public MainEntity(float x, float y, int width, int height){
        this.x  = x;
        this.y  = y;
        this.width = width;
        this.height = height;
    }
}
