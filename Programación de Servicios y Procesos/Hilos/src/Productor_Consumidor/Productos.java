package Productor_Consumidor;

class Productos {
    private int cantidad = 0;
    
    public synchronized int getCantidad() {
        return cantidad;
    }
    
    public synchronized void agregarProductos(int n) {
        cantidad += n;
        System.out.println("Productor repone 10 productos. Total: " + cantidad);
    }
    
    public synchronized boolean consumirProducto() {
        if (cantidad > 0) {
            cantidad--;
            System.out.println("Consumidor "+Thread.currentThread().getName()+" compra un producto. Restantes: " + cantidad);
            return true;
        }
        return false;
    }
}