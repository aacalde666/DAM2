package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;

import main.Main;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Fun {
	public static JSONArray leerFicheroJSON(File f) throws IOException {
		String cadena = "";
		//Nos ayuda a que encuentre el fichero dentro de las carpetas, 
		//ej: en la carpeta resources del main
		InputStream  inputStream = Main.class.getClassLoader().getResourceAsStream(f.getName());
		BufferedReader ent = new BufferedReader(new InputStreamReader(inputStream));
		String linea;
		while ((linea = ent.readLine()) != null) {
			cadena+=linea;
		}
		ent.close();
		JSONArray notas = new JSONArray(cadena);
		return notas;
	}
	public static void generarInformes(List<Pedido> pedidos) throws JRException {
		String ficheroJasper = "C:\\Users\\aacal\\JaspersoftWorkspace\\MyReports\\Pedidos.jasper";
		String informePdf = "reports\\Pedidos.pdf";
		JRBeanCollectionDataSource camposInformes = new JRBeanCollectionDataSource(pedidos);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
		Map<String, Object> params = new HashMap<>();
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInformes);
		JasperExportManager.exportReportToPdfFile(informe, informePdf);
		
	}
	public static File getFichero(String fich) {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("conf.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el fichero "+conf.toString());
		}
		return new File(conf.getProperty(fich));
	}
}
