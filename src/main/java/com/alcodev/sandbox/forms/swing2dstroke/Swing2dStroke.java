package com.alcodev.sandbox.forms.swing2dstroke;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;

/**
 * User: mnijurin
 * Date: 19.10.12
 * Time: 10:07
 */
public class Swing2dStroke {
    private static final int SIZE = 200;
    private static final double PAD = 20d;

    private static class CompositeStroke implements Stroke {

        private Stroke stroke1, stroke2;

        public CompositeStroke(Stroke stroke1, Stroke stroke2) {
            this.stroke1 = stroke1;
            this.stroke2 = stroke2;
        }

        @Override
        public Shape createStrokedShape(Shape shape) {
            return stroke2.createStrokedShape(
                    stroke1.createStrokedShape(shape));
        }
    }

    public static void main(String[] args) {
        final BufferedImage bi = new BufferedImage(
                SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Arc2D.Double shape = new Arc2D.Double(PAD, 2 * PAD,
                (SIZE - 2 * PAD), (SIZE - 2 * PAD), 0, 180d, Arc2D.OPEN);
        g.setColor(Color.white);
        g.fillRect(0, 0, SIZE, SIZE);
        BasicStroke s1 = new BasicStroke(16f);
        BasicStroke s2 = new BasicStroke(1f);
        g.setStroke(new CompositeStroke(s1, s2));
        g.setColor(Color.black);
        g.draw(shape);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new JLabel(new ImageIcon(bi)));
                f.pack();
                f.setVisible(true);
            }
        });

    }
}
