package com.real.Classes.ThreadClasses;

import com.real.Classes.Handlers.Physics2D;
import com.real.Classes.Handlers.ProgramLoop;

public class ProgramClock{

    protected static int windowWidth;
    protected static int windowHeight;

    public static void initializeThread(int Width, int Height) {
        windowHeight = Height;
        windowWidth = Width;
    }

    public static void run() {
        Physics2D.SetSimSpeed(0f);
        
        ProgramLoop.initWindow(windowWidth, windowHeight);
        ProgramLoop.run2D();
    }
    
}