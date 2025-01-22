package EjLienzo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LienzoClienteSincronizado extends JPanel {

    private final List<Point> puntos;
    private Point puntoInicio;
    private final Cliente cliente;

    public LienzoClienteSincronizado() {
        puntos = new CopyOnWriteArrayList<>();
        puntoInicio = null;
        cliente = new Cliente();
        cliente.conectar();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicio = e.getPoint();
                puntos.add(puntoInicio);
                cliente.enviarPunto(puntoInicio);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                puntoInicio = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point nuevoPunto = e.getPoint();
                puntos.add(nuevoPunto);
                cliente.enviarPunto(nuevoPunto);
                repaint();
            }
        });

        new Thread(cliente).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        for (int i = 0; i < puntos.size() - 1; i++) {
            Point p1 = puntos.get(i);
            Point p2 = puntos.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lienzo Sincronizado");
            LienzoClienteSincronizado lienzo = new LienzoClienteSincronizado();
            lienzo.setPreferredSize(new Dimension(500, 400));
            frame.add(lienzo);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private class Cliente implements Runnable {
        private final String SERVIDOR_IP = "localhost";
        private final int PUERTO = 12345;
        private Socket socket;
        private BufferedReader entrada;
        private PrintWriter salida;

        public void conectar() {
            try {
                socket = new Socket(SERVIDOR_IP, PUERTO);
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                // Leer puntos históricos
                String mensaje;
                while (!(mensaje = entrada.readLine()).equals("END")) {
                    String[] coords = mensaje.split(",");
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    puntos.add(new Point(x, y));
                }
            } catch (IOException e) {
                System.err.println("Error al conectar con el servidor: " + e.getMessage());
            }
        }

        public void enviarPunto(Point punto) {
            if (salida != null) {
                salida.println(punto.x + "," + punto.y);
            }
        }

        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    String[] coords = mensaje.split(",");
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    puntos.add(new Point(x, y));
                    repaint();
                }
            } catch (IOException e) {
                System.err.println("Conexión con el servidor finalizada: " + e.getMessage());
            }
        }
    }
}
