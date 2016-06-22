package br.com.javaparaweb.capitulo12.jfreechart;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;
import javax.faces.bean.*;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class CidadeBean {
	private StreamedContent	grafico;
	private static final Logger	log	= Logger.getLogger(CidadeBean.class.getName()); 

	public CidadeBean() {
		try {
			JFreeChart graficoPizza = ChartFactory.createPieChart("5 cidades mais populosas de SC", 
				this.geraDados(), true, true, false); 
			File arquivoGrafico = new File("pizza.png"); 
			ChartUtilities.saveChartAsPNG(arquivoGrafico, graficoPizza, 500, 300); 
			this.grafico = new DefaultStreamedContent(new FileInputStream(arquivoGrafico), "image/png"); 
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
	}
	private DefaultPieDataset geraDados() {
		DefaultPieDataset dts = new DefaultPieDataset(); 
		dts.setValue("Blumenau", new Double(334002.0));
		dts.setValue("Criciúma", new Double(204667.0));
		dts.setValue("Florianopólis", new Double(461524.0));
		dts.setValue("Joinville", new Double(554601.0));
		dts.setValue("São José", new Double(228561.0));
		return dts;
	}
	public StreamedContent	getGrafico() {
		return this.grafico;
	}
}
