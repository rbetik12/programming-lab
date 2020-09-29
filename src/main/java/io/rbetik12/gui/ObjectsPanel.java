package io.rbetik12.gui;

import io.rbetik12.models.MusicBand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class ObjectsPanel extends JPanel {

    private int squareW = 50;
    private int squareH = 50;
    private Timer timer;
    private float alpha;
    final private Random random = new Random();
    private int greenValue;
    private int blueValue;

    public ObjectsPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        alpha = 0;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (alpha < 1) {
                    alpha += 0.01;
                    if (alpha > 1)
                        alpha = 1;
                    repaint();
                } else {
                    timer.stop();
                }
            }
        });

        timer.start();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("Clicked at: " + mouseEvent.getPoint());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));


        for (MusicBand e : BandsManager.getBands()) {
            greenValue = random.nextInt() % 255;
            if (greenValue < 0) greenValue = greenValue + 255;
            blueValue = random.nextInt() % 255;
            if (blueValue < 0) blueValue = blueValue + 255;
            g.setColor(new Color((e.getAuthor().getId() + 128) % 255 / 256f, greenValue / 256f, blueValue / 256f));

            g.fillRect(e.getCoordinates().getX().intValue(), e.getCoordinates().getY().intValue(), squareW, squareH);

            g.setColor(Color.black);
            g.drawRect(e.getCoordinates().getX().intValue(), e.getCoordinates().getY().intValue(), squareW, squareH);
            g.drawString(e.getName(), e.getCoordinates().getX().intValue(), e.getCoordinates().getY().intValue() + squareH / 2);
        }
    }

    private boolean checkForCollision() {
        return false;
    }
}