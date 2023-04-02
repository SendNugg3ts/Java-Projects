package Utilz;

public class Constant {
    public static class Directions{
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;

    }
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int JUMP_ANTICIPATION= 2;
        public static final int JUMP =4;
        public static final int FALL = 5;
        public static final int GROUND = 6;
        public static final int HIT = 7;
        public static final int DEAD_HIT = 8;
        public static final int DEAD_GROUND = 9;
        public static final int DOOR_IN = 10;
        public static final int DOOR_OUT =11;
        public static int GetSpriteAmount(int player_action){
            return switch (player_action) {
                case DOOR_IN, DOOR_OUT -> 16;
                case IDLE -> 26;
                case RUN -> 14;
                case JUMP_ANTICIPATION -> 1;
                case FALL -> 2;
                case GROUND -> 3;
                case HIT -> 8;
                case DEAD_HIT -> 6;
                case DEAD_GROUND, JUMP -> 4;
                default -> 0;
            };
        }
    }
}
