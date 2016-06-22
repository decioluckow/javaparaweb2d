package br.com.javaparaweb.capitulo12.primefaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@RequestScoped
public class VendaColunasBean {

	private BarChartModel vendaColunas;

	public VendaColunasBean() { 
		ChartSeries brasil = new ChartSeries();
		ChartSeries estadosUnidos = new ChartSeries();
		ChartSeries inglaterra = new ChartSeries();

		this.vendaColunas = new BarChartModel();

		brasil.setLabel("Brasil");
		brasil.set("2013", 120);
		brasil.set("2014", 40);
		brasil.set("2015", 79);

		estadosUnidos.setLabel("Estados Unidos");
		estadosUnidos.set("2013", 40);
		estadosUnidos.set("2014", 150);
		estadosUnidos.set("2015", 180);

		inglaterra.setLabel("Inglaterra");
		inglaterra.set("2013", 70);
		inglaterra.set("2014", 80);
		inglaterra.set("2015", 90);

		this.vendaColunas.addSeries(brasil);
		this.vendaColunas.addSeries(estadosUnidos);
		this.vendaColunas.addSeries(inglaterra);
		this.vendaColunas.setTitle("Gráfico de vendas por país");
		this.vendaColunas.setLegendPosition("ne");
		Axis xAxis = this.vendaColunas.getAxis(AxisType.X);
		xAxis.setLabel("Ano das vendas");

		Axis yAxis = this.vendaColunas.getAxis(AxisType.Y);
		yAxis.setLabel("Volume de vendas");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	public BarChartModel getVendaColunas() {
		return this.vendaColunas;
	}
}
