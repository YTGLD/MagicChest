package com.ytgld.magic_chest.renderer;

public class Light {
    public Light() {
    }

    public static class ARGB {
        public ARGB() {
        }

        public static int color(int alpha, int red, int green, int blue) {
            return alpha << 24 | red << 16 | green << 8 | blue;
        }
    }
}
