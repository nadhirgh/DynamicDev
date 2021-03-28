/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import animatefx.animation.BounceIn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author NADHIR
 */
public class StatistiqueClasseController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private Pane pnlStatus;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblStatusMini;
    @FXML
    private BarChart<?, ?> bar;
    @FXML
    private NumberAxis Y;
    @FXML
    private CategoryAxis X;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        new BounceIn(pnlStatus).play();

        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("3A2", 24));
        set1.getData().add(new XYChart.Data("3A5", 30));

        set1.getData().add(new XYChart.Data("3A15", 29));
        set1.getData().add(new XYChart.Data("3A19", 30));

        set1.getData().add(new XYChart.Data("3A20", 29));

        bar.getData().addAll(set1);
    }

}
