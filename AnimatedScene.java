package animatedscene;

import java.io.*; 
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class AnimatedScene extends JFrame implements Runnable {
    static final int WINDOW_WIDTH = 1200;
    static final int WINDOW_HEIGHT = 800;
    final int XBORDER = 0;
    final int YBORDER = 0;
    final int YTITLE = 25;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    int boatxpos;
    int boatypos;
    int boatrot;
    int shipypos;
    int rocketxpos;
    int rocketypos;
    int timecount;
    double explosionScale;
    boolean rocketVisible = false;
    boolean explosionVisible = false;
    boolean textVisible = false;
    boolean textEnd = false;
    
    
    
    
    
    
    Image image;
    Graphics2D g;
    

    
    static AnimatedScene frame;
    public static void main(String[] args) {
        frame = new AnimatedScene();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public AnimatedScene() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                    
                }
                if (e.BUTTON3 == e.getButton())
                    //right button
                    reset();

                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
          
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }

 

////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

 /*       g.setColor(Color.magenta);
        RectCentered(100,100,100,100,true);
//        RectTransform(100,100,0,1,1,true);
        
        g.setColor(Color.green);
        RectCentered(170,170,30,30,true);
        
        
        g.setColor(Color.blue);
        OvalCentered(200,200,40,40,true);
*/
 /*       g.setColor(Color.black);
        OvalCentered(500,500,400,400,false);
        
        g.setColor(Color.green);
        OvalCentered(430,590,40,40,true);
        
         g.setColor(Color.green);
        OvalCentered(590,590,40,40,true);
        
        g.setColor(Color.black);
        OvalCentered(510,440,90,90,false);
        
        g.setColor(Color.LIGHT_GRAY);
        RectCentered(510,530,30,30,true);
        
        g.setColor(Color.LIGHT_GRAY);
        RectCentered(500,530,30,30,true);
        
        g.setColor(Color.LIGHT_GRAY);
        RectCentered(520,530,30,30,true);
        
        
        g.setColor(new Color(150,39,86));
        StringCentered(680, 680,"Hello","Broadway",30);

        g.setColor(Color.blue);
        ArcCentered(100,100,160,100,45,270,true);
       // x pos, y pos, width, height, arc angle, size  
        g.setColor(Color.RED);
        RectTransform(220,220,45,4.6,5.6,false);
       // x pos, y pos, rotate (deg), x scale, y scale 
         g.setColor(Color.orange);
 */    //   ArcTransform(45,270,350,325,-45,3.0,2.5,false);
       // int start,int sweep,int xpos,int ypos,double rot,double xscale,double yscale,boolean fill
       
       
       g.setColor(new Color(0,162,232)); //sky
       RectCentered(500,620,3000,600,true);
       
        g.setColor(Color.BLUE); //ocean
       RectCentered(500,25,3000,600,true);
       
       DrawBoat(boatxpos,boatypos,boatrot,1.4,1.4,Color.BLACK); //12 point polygon
       // 460, 353, 0
       g.setColor(new Color(239, 228, 176)); //rectangle
       RectCentered(45,335,300,20,true);
       
       g.setColor(new Color(120, 74, 50)); //trunk
       RectCentered(85,370,20,50,true);
       
       g.setColor(Color.yellow); //oval
        OvalCentered(1180,780,160,160,true);
       
       
       g.setColor(new Color(0, 128, 0));
       ArcTransform(45,270,85,390,90,2.1,2.1,true); //use of an arc
       
       DrawRock(1130,345,90,1.5,1.5,Color.GRAY); //second draw method
       
   //    g.setColor(Color.black); //text 
     //  StringCentered(415, 370,"HMS","Britannic Bold",20);
       
       //four draw methods
       g.setColor(new Color(120, 74, 50)); //trunk
       RectCentered(175,370,20,50,true);
       g.setColor(new Color(0, 128, 0));
       ArcTransform(45,270,175,390,90,2.1,2.1,true); //leaves
                     
       
       g.setColor(new Color(120, 74, 50)); //trunk
       RectCentered(130,370,20,50,true);
       g.setColor(new Color(0, 128, 0));
       ArcTransform(45,270,130,390,90,2.1,2.1,true); //leaves
       
       g.setColor(new Color(120, 74, 50)); //trunk
       RectCentered(40,370,20,50,true);
       g.setColor(new Color(0, 128, 0));
       ArcTransform(45,270,40,390,90,2.1,2.1,true); //leaves
       
       DrawShip(500,shipypos,0,1.5,1.5,Color.BLACK);
       
       g.setColor(Color.BLACK);
       StringTransform(" "+timecount,"Arial",200, 600,0,1.3,1.3);
       
       
       
       if(explosionVisible == true)
       {
       DrawExplosion(1000,423,0,explosionScale,explosionScale,Color.yellow);
       }
       
       
       if(rocketVisible == true)
       {
       DrawRocket(rocketxpos,rocketypos,120,0.3,0.3,Color.BLACK );
       }
       
       if(textVisible == true)
       {
       StringCentered(860,470,"BOOM!!","Britannic Bold",30);
       }
       
       if(textEnd == true)
       {
       StringCentered(600, 400,"The End!","Britannic Bold",40);
       }
       
       
 //      DrawHouse(250,250,0,1.1,1.1,Color.BLACK);
       
  //     DrawDoor(250,350,90,1.5,1.5,Color.BLACK);
       

   //    DrawRocket(700,700,180,1.2,1.2,Color.red);
       
  //     DrawSquare(650, 550, 0, 1.2, 1.2,Color.GREEN);
     
        
      
       gOld.drawImage(image, 0, 0, null);
        
    
    }


////////////////////////////////////////////////////////////////////////////  
    public void RectTransform(int xpos,int ypos,double rot,double xscale,double yscale,boolean fill)
    {      
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);  
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        if (fill)
        {
            g.fillRect(-10,-10,20,20);   
        }
        else
        {
            g.drawRect(-10,-10,20,20);               
        }
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }    
    
    public void OvalTransform(int xpos,int ypos,double rot,double xscale,double yscale,boolean fill)
    {      
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);  
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        if (fill)
        {
            g.fillOval(-10,-10,20,20);   
        }
        else
        {
            g.drawOval(-10,-10,20,20);               
        }
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }        
    
    
    public void ArcTransform(int start,int sweep,int xpos,int ypos,double rot,double xscale,double yscale,boolean fill)
    {      
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);  
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        if (fill)
        {
            g.fillArc(-10,-10,20,20,start,sweep);   
        }
        else
        {
            g.drawArc(-10,-10,20,20,start,sweep);               
        }
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }        
    
    public void StringTransform(String text,String font,int xpos,int ypos,double rot,double xscale,double yscale)
    {      
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);  
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        g.setFont (new Font (font,Font.PLAIN, 10)); 
        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();               
        g.drawString(text, -width/2, height/4 );                

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }       
    
////////////////////////////////////////////////////////////////////////////////

    public void RectCentered(int xpos,int ypos,int width,int height,boolean fill)
    {
        xpos = xpos - width/2;
        ypos = ypos + height/2;
        xpos = getX(xpos);
        ypos = getYNormal(ypos);
        if (fill)
        {
            g.fillRect(xpos,ypos,width,height);              
        }
        else
        {
            g.drawRect(xpos,ypos,width,height);              
        }        
    }    
    
    public void OvalCentered(int xpos,int ypos,int width,int height,boolean fill)
    {
        xpos = xpos - width/2;
        ypos = ypos + height/2;
        xpos = getX(xpos);
        ypos = getYNormal(ypos);
        if (fill)
        {
            g.fillOval(xpos,ypos,width,height);              
        }
        else
        {
            g.drawOval(xpos,ypos,width,height);              
        }        
    }        
    
    public void ArcCentered(int xpos,int ypos,int width,int height,int start,int sweep,boolean fill)
    {
        xpos = xpos - width/2;
        ypos = ypos + height/2;
        xpos = getX(xpos);
        ypos = getYNormal(ypos);
        if (fill)
        {
            g.fillArc(xpos,ypos,width,height,start,sweep);              
        }
        else
        {
            g.drawArc(xpos,ypos,width,height,start,sweep);              
        }        
    }        
    
    public void StringCentered(int xpos,int ypos,String text,String font,int size)
    {
        g.setFont (new Font (font,Font.PLAIN, size)); 
        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();
        xpos = xpos - width/2;
        ypos = ypos - height/4;
        xpos = getX(xpos);
        ypos = getYNormal(ypos);
        g.drawString(text, xpos, ypos);           
    }    
    
    
////////////////////////////////////////////////////////////////////////////  
    public void RectOrigin(int xpos,int ypos,double rot,double xscale,double yscale,boolean fill)
    {
        int xposMod = xpos;
        int yposMod = -ypos;
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        if (fill)
            g.fillRect(-10,-10,20,20);   
        else
            g.drawRect(-10,-10,20,20);               
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }         
        
    public void OvalOrigin(int xpos,int ypos,double rot,double xscale,double yscale,boolean fill)
    {
        int xposMod = xpos;
        int yposMod = -ypos;
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        if (fill)
        {
            g.fillOval(-10,-10,20,20);   
        }
        else
        {
            g.drawOval(-10,-10,20,20);               
        }
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }         
    public void ArcOrigin(int start,int sweep,int xpos,int ypos,double rot,double xscale,double yscale,boolean fill)
    {
        int xposMod = xpos;
        int yposMod = -ypos;
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        if (fill)
        {
            g.fillArc(-10,-10,20,20,start,sweep);   
        }
        else
        {
            g.drawArc(-10,-10,20,20,start,sweep);               
        }
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }         
                    
    public void StringOrigin(String text,String font,int xpos,int ypos,double rot,double xscale,double yscale)
    {
        int xposMod = xpos;
        int yposMod = -ypos;
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
         
        g.setFont (new Font (font,Font.PLAIN, 10)); 
        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();               
        g.drawString(text, -width/2, height/4 );                

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);        
    }         
    
    public void PolygonOrigin(int xvals[],int yvals[],boolean fill)
    {
        for (int i=0;i<xvals.length;i++)
        {
            yvals[i] = -yvals[i];
        }
        if (fill)
        {
            g.fillPolygon(xvals, yvals, xvals.length);
        }
        else
        {
            g.drawPolygon(xvals, yvals, xvals.length);            
        }
    }
        
////////////////////////////////////////////////////////////////
public void DrawHouse(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(color);
        int xvals[] = {0,20,-20};
        int yvals[] = {30,20,20};
        PolygonOrigin(xvals,yvals,true);
        RectOrigin(0,0,0,2,2,true);
       
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    }   
        
public void DrawRocket(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(Color.white);
        int xvals[] = {0,20,-20};
        int yvals[] = {80,40,40};
        PolygonOrigin(xvals,yvals,true);
       
        g.setColor(Color.GRAY);
        RectOrigin(0,0,0,2,4,true);
       
        g.setColor(Color.black);
        int xvals2[] = {20,30,-30,-20};
        int yvals2[] = {-40,-60,-60,-40};
        PolygonOrigin(xvals2,yvals2,true);
         
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    } 
   
public void DrawExplosion(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(Color.yellow);
        int xvals[] = {-20, -10, -30, -10, -30, -10, -10, 10, 30, 40, 30, 40, 20, 10, 0 };
        int yvals[] = {30, 10, 0, -20, -30, -20, -30, -10, -30, -10, 0, 20, 20, 30, 20};
        PolygonOrigin(xvals,yvals,true);
                 
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    } 
    public void DrawBoat(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor((new Color(185, 122, 87)));
        int xvals[] = {0, 30, 60, -60, -30};
        int yvals[] = {-20, -20, 20, 20, -20};
        PolygonOrigin(xvals,yvals,true);
       
        g.setColor((new Color(185, 122, 87)));
        int xvals2[] = {-30, -20, -20, -30};
        int yvals2[] = {10, 10, 50, 50};
        PolygonOrigin(xvals2,yvals2,true);
         
        g.setColor((new Color(185, 122, 87)));
        int xvals3[] = {20, 30, 30, 20};
        int yvals3[] = {10, 10, 50, 50};
        PolygonOrigin(xvals3,yvals3,true);
        
        g.setColor(Color.WHITE);
        int xvals4[] = {-40, -40, -10, -10};
        int yvals4[] = {40, 70, 70, 40};
        PolygonOrigin(xvals4,yvals4,true);
        
        g.setColor(Color.WHITE);
        int xvals5[] = {10, 40, 40, 10};
        int yvals5[] = {70, 70, 40, 40};
        PolygonOrigin(xvals5,yvals5,true);
        
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    } 
    
    public void DrawRock(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(Color.GRAY);
        int xvals[] = {20, -10, -20, -30, -20, 0, 20, 20};
        int yvals[] = {35, 10, 20, 0, -30, -45, -30, 10};
        PolygonOrigin(xvals,yvals,true);
        RectOrigin(0,0,0,2,2,true);
       
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    }   


    public void DrawDoor(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(color);
        int xvals[] = {32,-32, -32, -15, -15, 20, 20, 32, 32};
        int yvals[] = {45, 45, 22, 22, -21, -21, 24, 24, 45};
        PolygonOrigin(xvals,yvals,true);
        
       
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    }  
    
    
    public void DrawSquare(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor(color.green);
        int xvals[] = {20, 20, -20, -20};
        int yvals[] = {-20, 30, 30, -20};
        PolygonOrigin(xvals,yvals,true);
        
         g.setColor(Color.blue);
        int xvals2[] = {-20, -40, -40, -20};
        int yvals2[] = {30, 30, -20, -20};
        PolygonOrigin(xvals2,yvals2,true);
        
        g.setColor(Color.blue);
        int xvals3[] = {20, 40, 40, 20};
        int yvals3[] = {30, 30, -20, -20};
        PolygonOrigin(xvals3,yvals3,true);
        
       
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    } 
    public void DrawShip(int xpos,int ypos,double rot,double xscale,double yscale,Color color)
    {
        int xposMod = getX(xpos);
        int yposMod = getYNormal(ypos);
        g.translate(xposMod,yposMod);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.setColor((new Color(163, 73, 164)));
        int xvals[] = {40, -40, -20, 20};
        int yvals[] = {10, 10, -10, -10};
        PolygonOrigin(xvals,yvals,true);
                      
        g.setColor(Color.white);
        int xvals2[] = {20, 10, -10, -20};
        int yvals2[] = {10, 20, 20, 10};
        PolygonOrigin(xvals2,yvals2,true);
         
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xposMod,-yposMod);
    } 
    
   
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .04;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {

      boatxpos = 460;
      boatypos = 353;
      boatrot = 0;
      shipypos = 790;
      rocketxpos = 500;
      rocketypos = 690;
      explosionScale = 0.9;
      rocketVisible = false;
      explosionVisible = false;
      textVisible = false;
      textEnd = false;
      timecount = 0;         
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
         
            reset();
        }
        
        if (boatxpos < 1030)
        {
        boatxpos +=3;
        }
        else if (boatrot > -45)
        {
        boatrot -=3;
        }
        
        
        
        if(timecount > 255 & timecount < 315)
        {
        shipypos-=2;
        }
        else if(timecount > 330)
        {
        shipypos+=2;
        }
        
        if (timecount > 325 & timecount < 400)
        {
        rocketVisible = true;
        rocketxpos+=7;
        rocketypos-=4;
        }

       if (timecount > 401) 
       {
       rocketVisible = false;
       textVisible = false;
       }
       
        
        
        
      if(timecount > 395 & timecount < 405)
      {
      explosionVisible = true;
      textVisible = true;
      explosionScale+=0.5;
      }
        
      if(timecount > 405)
      {
      explosionVisible = false;
      }
        
        
      if(timecount > 419 & timecount < 670)
      {
      boatypos-=2;
      boatrot-=2;
      } 
      
      if(timecount > 600)
      {
      textEnd = true;
      }
      
      
      
        timecount++;
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x + XBORDER);
    }

    public int getY(int y) {
        return (y + YBORDER + YTITLE);
    }

    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    
    public int getWidth2() {
        return (xsize - getX(0) - XBORDER);
    }

    public int getHeight2() {
        return (ysize - getY(0) - YBORDER);
    }
}
