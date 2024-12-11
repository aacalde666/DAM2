package Productor_Consumidor;

class Productor extends Thread {
    private Productos productos;
    
    public Productor(Productos productos) {
        this.productos = productos;
    }
    
    @Override
    public void run() {
        while (true) {
            if (productos.getCantidad() == 0) {
                productos.agregarProductos(10);
            }
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}