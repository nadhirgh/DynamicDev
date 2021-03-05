/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.DepenseService;
import Services.ReclamationService;
import Services.RevenuService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class ficheFinanceController implements Initializable {

    @FXML
    private PieChart pc_stat;
    @FXML
    private PieChart pc_stat1;
    
    DepenseService ds ;
    RevenuService rs ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      rs = new RevenuService();
      ds = new DepenseService();
         ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Depense", ds.afficher().size()),
                new PieChart.Data("Revenu", rs.afficher().size())
                );
         
        
        pc_stat.setData(pieChartData);
        pc_stat.setTitle("Rapport Financeier En Nombre");
        
        ObservableList<PieChart.Data> pieChartData1 =
                FXCollections.observableArrayList(
                new PieChart.Data("Depense", ds.totalDepense()),
                new PieChart.Data("Revenu", rs.totalRevenu())
                );
        pc_stat1.setData(pieChartData1);
        pc_stat1.setTitle("Rapport Financeier En Valeur");
    }    
    
}
