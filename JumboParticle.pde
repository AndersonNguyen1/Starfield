NormalParticle[] particleNormal;
OddballParticle particleOdd;
JumboParticle particleJumbo;

void setup()
{
    size(500, 500);
    particleNormal = new NormalParticle[500];
    particleOdd = new OddballParticle(255, 255);
    particleJumbo = new JumboParticle(255, 255);

    for (int i = 0; i < particleNormal.length; i++)
    {
        particleNormal[i] = new NormalParticle(255, 255, 5.0);
    }
}

void draw()
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

    particleJumbo.show(); 
    particleJumbo.move();
    particleJumbo.reset();
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

    void initialize(double x, double y, float particleSize)
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

    void show()
    {
        fill(colorR, colorG, colorB);
        ellipse((float)xLocation, (float)yLocation, sizeParticle, sizeParticle);
    }
    
    void move()
    { 
        yLocation = yLocation + Math.sin(angle);
        xLocation = xLocation + Math.cos(angle);
    }

    void reset()
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
    
    void move()
    {
        xLocation = xLocation + ((float)(Math.random()*5) - 2.5);
        yLocation = yLocation + ((float)(Math.random()*5) - 2.5);
    }
}

class JumboParticle extends NormalParticle
{
    JumboParticle(int x, int y)
    {
        super(x, y, 300);
    }
}
