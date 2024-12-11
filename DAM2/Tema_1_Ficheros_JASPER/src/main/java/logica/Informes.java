package logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Informes {
	public static void generarInformes(List<DatosAlumno> datos) throws JRException {
		String ficheroJasper = "C:\\Users\\aacal\\JaspersoftWorkspace\\MyReports\\listadoAlumnos.jasper";
		String informePdf = "reports\\listadoAlumnos.pdf";
		JRBeanCollectionDataSource camposInformes = new JRBeanCollectionDataSource(datos);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
		Map<String, Object> params = new HashMap<>();
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInformes);
		JasperExportManager.exportReportToPdfFile(informe, informePdf);
		
	}
}
