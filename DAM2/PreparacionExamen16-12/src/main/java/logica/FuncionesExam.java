package logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import UtilidadesTeclado.Teclado;
import jakarta.xml.bind.JAXBException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class FuncionesExam {
	public static void intercambiado(int idJuego) {
	    try {
	        BibliotecaVideojuegos bv = Funciones.leerFicheroXMLJAXB();
	        boolean found = false;

	        for (Intercambios intercambio : bv.getIntercambios()) {
	            if (intercambio.getId_juego() == idJuego) {
	                found = true;
	                System.out.println("Intercambio encontrado:");
	                System.out.println("ID Intercambio: " + intercambio.getId_intercambio());
	                System.out.println("ID Juego: " + intercambio.getId_juego());

	                // Información del usuario emisor
	                Usuarios emisor = bv.getUsuarios().stream()
	                    .filter(usuario -> usuario.getId_usuario() == intercambio.getId_emisor())
	                    .findFirst().orElse(null);
	                if (emisor != null) {
	                    System.out.println("Usuario Emisor: " + emisor.getNombre() + " (ID: " + emisor.getId_usuario() + ")");
	                }

	                // Información del usuario receptor
	                Usuarios receptor = bv.getUsuarios().stream()
	                    .filter(usuario -> usuario.getId_usuario() == intercambio.getId_receptorm())
	                    .findFirst().orElse(null);
	                if (receptor != null) {
	                    System.out.println("Usuario Receptor: " + receptor.getNombre() + " (ID: " + receptor.getId_usuario() + ")");
	                }
	            }
	        }

	        if (!found) {
	            System.out.println("No se encontraron intercambios para el ID del juego: " + idJuego);
	        }
	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }
	}
	public static void eliminaUsuario(int idUsuario) {
	    try {
	        // Cargar la biblioteca desde el archivo XML
	        BibliotecaVideojuegos bv = Funciones.leerFicheroXMLJAXB();

	        // Buscar el usuario por ID
	        Usuarios usuarioAEliminar = bv.getUsuarios().stream()
	                .filter(u -> u.getId_usuario() == idUsuario)
	                .findFirst()
	                .orElse(null);

	        if (usuarioAEliminar == null) {
	            System.out.println("No existe un usuario con el ID " + idUsuario + " en la base de datos.");
	            return;
	        }

	        // Verificar si el usuario tiene intercambios
	        boolean tieneIntercambios = bv.getIntercambios().stream()
	                .anyMatch(i -> i.getId_emisor() == idUsuario || i.getId_receptorm() == idUsuario);

	        if (tieneIntercambios) {
	            System.out.println("El usuario con ID " + idUsuario + " tiene intercambios registrados.");
	            System.out.println("Eliminando usuario y sus intercambios asociados...");

	            // Eliminar intercambios asociados
	            bv.getIntercambios().removeIf(i -> i.getId_emisor() == idUsuario || i.getId_receptorm() == idUsuario);

	            // Si el usuario es emisor de un intercambio, eliminar el juego asociado
	            bv.getIntercambios().stream()
	                .filter(i -> i.getId_emisor() == idUsuario)
	                .forEach(i -> bv.getJuegos().removeIf(j -> j.getId_juego() == i.getId_juego()));
	        } else {
	            // Confirmación para eliminar un usuario sin intercambios
	            System.out.print("El usuario con ID " + idUsuario + " no tiene intercambios registrados.\n"
	                    + "¿Seguro que deseas eliminarlo? (s/n): ");
	            String opcion = Teclado.leerCadena().toLowerCase();

	            while (!opcion.equals("s") && !opcion.equals("n")) {
	                System.out.print("Por favor, responde con 's' para Sí o 'n' para No: ");
	                opcion = Teclado.leerCadena().toLowerCase();
	            }

	            if (opcion.equals("n")) {
	                System.out.println("El usuario con ID " + idUsuario + " no será eliminado.");
	                return;
	            }
	        }

	        // Eliminar al usuario
	        bv.getUsuarios().removeIf(u -> u.getId_usuario() == idUsuario);

	        // Escribir los cambios en el archivo XML
	        Funciones.escribirFicheroXMLJAXB(bv);

	        System.out.println("El usuario con ID " + idUsuario + " y sus datos asociados han sido eliminados correctamente.");
	    } catch (JAXBException e) {
	        System.err.println("Error al procesar el archivo XML: " + e.getMessage());
	    }
	}

	public static void generaInforme(int idUsuario) {
	    try {
	        BibliotecaVideojuegos bv = Funciones.leerFicheroXMLJAXB();
	        Usuarios usuario = bv.getUsuarios().stream()
	            .filter(u -> u.getId_usuario() == idUsuario)
	            .findFirst().orElse(null);

	        if (usuario == null) {
	            System.out.println("No se encontró un usuario con ID: " + idUsuario);
	            return;
	        }

	        // Filtrar intercambios del usuario
	        List<Intercambios> intercambiosUsuario = bv.getIntercambios().stream()
	            .filter(i -> i.getId_emisor() == idUsuario || i.getId_receptorm() == idUsuario)
	            .toList();

	        if (intercambiosUsuario.isEmpty()) {
	            System.out.println("El usuario no tiene intercambios registrados.");
	            return;
	        }

	        // Configurar JasperReports
	        String ficheroJasper = "C:\\Users\\aacal\\JaspersoftWorkspace\\MyReports\\listadoIntercambios.jasper";
	        String informePdf = "reports\\intercambiosUsuario_" + idUsuario + ".pdf";

	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(intercambiosUsuario);
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

	        Map<String, Object> params = new HashMap<>();
	        params.put("NombreUsuario", usuario.getNombre());
	        params.put("IDUsuario", usuario.getId_usuario());

	        JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, dataSource);
	        JasperExportManager.exportReportToPdfFile(informe, informePdf);

	        System.out.println("Informe generado: " + informePdf);
	    } catch (JAXBException | JRException e) {
	        e.printStackTrace();
	    }
	}
}
