package Productor_Consumidor;

class Consumidor extends Thread {
    private Productos productos;
    
    public Consumidor(Productos productos) {
        this.productos = productos;
    }
    
    @Override
    public void run() {
        while (true) {
            if (productos.getCantidad() > 0) {
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                productos.consumirProducto();
            }
        }
    }
}