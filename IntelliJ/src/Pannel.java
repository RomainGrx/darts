import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;


public class Pannel extends JPanel{

    public Player[] players;
    public int nRound, n;
    public Graphics2D g2d;
    public int WIDTH, HEIGHT, MINWIDTH, MINHEIGHT,NBROWS, NBCOLS;
    private Stroke BASICSTROKE, WEIRDSTROKE;
    private Font BASICFONT, SCOREFONT;
    private FontMetrics BASICMETRICS, SCOREMETRICS;
    private Color[] SCORECOLOR = new Color[]{Color.WHITE, Color.YELLOW, new Color(0xFF8F00), Color.RED};
    private int[] SCOREORDERED = new int[]{6,10,15,2,17,3,19,7,16,8,11,14,9,12,5,20,1,18,4,13};



    Pannel(Player[] players){
        this.setBackground(Color.WHITE);
        this.players = players;
    }

    @Override
    public void paint(Graphics g) {
        Dimension currentWindow = this.getSize();
        this.WIDTH = this.getWidth();
        this.HEIGHT = this.getHeight();
        this.NBROWS = ( int ) Math.floor(Math.sqrt(this.players.length));
        this.NBCOLS = Math.round(this.players.length / NBROWS);
        this.MINWIDTH = this.WIDTH/this.NBCOLS;
        this.MINHEIGHT = this.HEIGHT/this.NBROWS;
        this.BASICSTROKE = new BasicStroke(Math.min(this.MINWIDTH/225, this.MINHEIGHT/225));
        this.WEIRDSTROKE = new BasicStroke(Math.min(this.MINWIDTH/215, this.MINHEIGHT/215));
        this.BASICFONT = new Font ("Helvetica", Font.BOLD | Font.ITALIC, Math.min(this.MINWIDTH/20, this.MINHEIGHT/20));
        this.BASICMETRICS = this.getFontMetrics(this.BASICFONT);
        this.SCOREFONT = new Font ("Helvetica", Font.BOLD , Math.min(this.MINWIDTH/30, this.MINHEIGHT/30));
        this.SCOREMETRICS = this.getFontMetrics(this.SCOREFONT);
        g2d = (Graphics2D) g;
        g2d.setStroke(this.BASICSTROKE);
        g2d.setColor(Color.BLACK);
        g2d.setFont(this.BASICFONT);


        this.g2d.drawString("Tour "+this.nRound+"/"+this.n, (int)(0.05*this.WIDTH), (int)(0.05*this.HEIGHT));

        if (players.length > 0) {
            for (int j = 0; j < NBROWS; j++) {
                if ((j + 1) * NBROWS > this.players.length) {
                    NBCOLS = this.players.length - (j + 1) * NBROWS;
                }
                for (int k = 0; k < NBCOLS; k++) {
                    addPlayer(players[j * NBROWS + k], k * this.WIDTH / NBCOLS, j * this.HEIGHT / NBROWS, this.WIDTH / NBCOLS, this.HEIGHT / NBROWS);
                }
            }
        }
    }

    private void addPlayer(Player player, int x, int y, int width, int height){
        int r = (int) Math.min(width/4, height/4), rBull = r/10, band = r/5,widthPoints = (int) Math.min(width/4, height/4), heightPoints = (int) Math.min(width/8, height/8),
                textWidth = this.BASICMETRICS.stringWidth(player.name), textHeight = this.BASICMETRICS.getHeight();

        Point center = new Point(x + width/2 , y + height/2 );
        Rectangle pointsRect = new Rectangle(x + width/2 - widthPoints/2, y + 7 * height/8 - heightPoints/2, widthPoints, heightPoints);
        Rectangle nameRect = new Rectangle(x + width/2 - textWidth/2, y + 1 * height/8 - textHeight/2, textWidth, textHeight);
        double scaleRect = 0.25;
        Rectangle turnRect = new Rectangle((int)(nameRect.x - scaleRect*nameRect.width/2), (int)(nameRect.y), (int)((1+scaleRect)*nameRect.width), (int)((1+scaleRect)*nameRect.height));

        // Set background color
        if (player.TURN){
            this.g2d.setColor(Color.BLACK);
            this.g2d.fillRect(turnRect.x, turnRect.y, turnRect.width, turnRect.height);
            this.g2d.setColor(Color.WHITE);
        }

        // Name
        g2d.drawString(player.name, nameRect.x, nameRect.y+textHeight);
        this.g2d.setColor(Color.BLACK);

        // Cible
        g2d.fillOval(center.x-(r+band), center.y-(r+band), 2*(r+band), 2*(r+band));
        g2d.setColor(Color.WHITE);
        g2d.fillOval(center.x-r, center.y-r, 2*r, 2*r);
        g2d.setColor(Color.BLACK);

        this.g2d.setFont(this.SCOREFONT);
        double theta = Math.PI/20;
        int iter = 0;

        while(true){
            if (theta > 2*Math.PI){
                break;
            }
            if (iter < 20){
                String scoreToShow = Integer.toString(this.SCOREORDERED[iter]);
                this.g2d.setColor(Color.WHITE);
                this.g2d.drawString(scoreToShow, (int)(center.x - this.SCOREMETRICS.stringWidth(scoreToShow)/2 + (r + band/2) * Math.cos(theta - Math.PI/20)), (int)(center.y  + this.SCOREMETRICS.getHeight()/2 + (r + band/2) * Math.sin(theta - Math.PI/20) ));
                this.g2d.setColor(Color.BLACK);
                if (this.SCOREORDERED[iter] >= 15){
                    this.g2d.setColor(this.SCORECOLOR[player.score[this.SCOREORDERED[iter]-15]]);
                    this.g2d.fillArc(center.x-r, center.y-r, 2*r, 2*r, -(int)Math.toDegrees(theta), (int)Math.toDegrees(Math.PI/10));
                    this.g2d.setColor(Color.BLACK);
                    this.g2d.setStroke(this.WEIRDSTROKE);
                    this.g2d.drawLine((int)(center.x + rBull * Math.cos(theta-Math.PI/10)), (int)(center.y + rBull * Math.sin(theta-Math.PI/10)), (int)(center.x + r * Math.cos(theta-Math.PI/10)), (int)(center.y + r * Math.sin(theta-Math.PI/10)));
                    this.g2d.setStroke(this.BASICSTROKE);
                }
            }
            this.g2d.drawLine((int)(center.x + rBull * Math.cos(theta)), (int)(center.y + rBull * Math.sin(theta)), (int)(center.x + r * Math.cos(theta)), (int)(center.y + r * Math.sin(theta)));
            theta += Math.PI/10;
            iter++;
        }
        this.g2d.drawOval(center.x-rBull, center.y-rBull, 2*rBull, 2*rBull);
        this.g2d.setColor(this.SCORECOLOR[player.score[6]]);
        this.g2d.fillOval(center.x-rBull, center.y-rBull, 2*rBull, 2*rBull);
        this.g2d.setColor(Color.BLACK);
        this.g2d.setFont(this.BASICFONT);

        // Points
        String pointsString = Integer.toString(player.points);
        this.g2d.drawRect(pointsRect.x, pointsRect.y, pointsRect.width, pointsRect.height);
        g2d.drawString(pointsString, x + width/2 - this.BASICMETRICS.stringWidth(pointsString)/2, (y + 7 * height/8 + this.BASICMETRICS.getHeight()/2));

    }

}