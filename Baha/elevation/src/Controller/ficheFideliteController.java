/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.ReclamationService;
import Services.RecommendationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class ficheFideliteController implements Initializable {

    @FXML
    private PieChart pc_stat;

    ReclamationService rs ;
    RecommendationService res ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        res = new RecommendationService();
        rs = new ReclamationService();
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Recommendation", res.afficher().size()),
                new PieChart.Data("Reclamation", rs.afficher().size())
                );
         
          
        pc_stat.setData(pieChartData);
        pc_stat.setTitle("Nbr Recommendation VS Nbr Reclamatione");
        
        
     
    
}
}
