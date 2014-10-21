import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

NormalParticle[] particleNormal;
OddballParticle particleOdd;
JumboParticle particleJumbo;

public void setup()
{
    size(500, 500);
    particleNormal = new NormalParticle[500];
    particleOdd = new OddballParticle(255, 255);
    particleJumbo = new JumboParticle(255, 255);

    for (int i = 0; i < particleNormal.length; i++)
    {
        particleNormal[i] = new NormalParticle(255, 255, 5.0f);
    }
}

public void draw()
{   
    background(0);
    for (int i = 0; i < particleNormal.length; i++)
    {
        particleNormal[i].show();
        particleNormal[i].move();
        particleNormal[i].reset();
    }

    particleOdd.show(); 
    particleOdd.move();
    particleOdd.reset();

    // particleJumbo.show(); 
    // particleJumbo.move();
    // particleJumbo.reset();
}

interface andersonParticles
{
    public void move();
    public void show();
}

class NormalParticle implements andersonParticles
{
    int colorR;
    int colorG;
    int colorB;
    float sizeParticle;
    double xLocation;
    double yLocation;
    double angle;
    double originalX; 
    double originalY;
    float originalSize;
    float radius;

    NormalParticle(int x, int y, float particleSize)
    {
        originalX = x;
        originalY = y;
        originalSize = particleSize;
        initialize(x, y, particleSize);
    }

    public void initialize(double x, double y, float particleSize)
    {
        colorR = ((int)(Math.random()*256)+1);
        colorG = ((int)(Math.random()*256)+1);
        colorB = ((int)(Math.random()*256)+1);
        sizeParticle = particleSize;
        xLocation = x;
        yLocation = y;
        angle = Math.random()*361;
        radius = sizeParticle/2;        
    }

    public void show()
    {
        fill(colorR, colorG, colorB);
        ellipse((float)xLocation, (float)yLocation, sizeParticle, sizeParticle);
    }
    
    public void move()
    { 
        yLocation = yLocation + Math.sin(angle);
        xLocation = xLocation + Math.cos(angle);
    }

    public void reset()
    {
        if (xLocation > 500 + radius || xLocation < 0 - radius|| yLocation > 500 + radius|| yLocation < 0 - radius)
            initialize(originalX, originalY, originalSize);
    }
}

class OddballParticle extends NormalParticle
{
    OddballParticle(int x, int y)
    {
        super(x, y, 30);
    }
    
    public void move()
    {
        xLocation = xLocation + ((float)(Math.random()*5) - 2.5f);
        yLocation = yLocation + ((float)(Math.random()*5) - 2.5f);
    }
}

class JumboParticle extends NormalParticle
{
    JumboParticle(int x, int y)
    {
        super(x, y, 300);
    }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
