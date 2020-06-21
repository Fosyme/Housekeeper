package GUI.controller;

import Core.Order;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.DataFormat;

import java.text.ParseException;
import java.time.LocalDate;

public class ReportController {
    @FXML
    private PieChart thisDayPieChart;

    @FXML
    private PieChart thisWeekPieChart;

    @FXML
    private PieChart thismonthPieChart;

    @FXML
    private PieChart thisSeasonPieChart;

    @FXML
    private PieChart thisYearPieChart;

    @FXML
    private Label thisYearOutputShowLabel;

    @FXML
    private Label thisWeekInputLabel1;

    @FXML
    private Label thisSeasonOutputShowLabel4;

    @FXML
    private Label thisDayInputShowLabel;

    @FXML
    private Label thisMonthBalanceShowLabel3;

    @FXML
    private Label thisMonthOutputShowLabel3;

    @FXML
    private Label thisDayOutputLabel;

    @FXML
    private Label thisMonthOutputLabel3;

    @FXML
    private Label thisWeekOutputShowLabel1;

    @FXML
    private Label thisYearInputShowLabel5;

    @FXML
    private Label thisWeekInputShowLabel1;

    @FXML
    private Label thisDayOutputShowLabel;

    @FXML
    private Label thisSeasonInputLabel4;

    @FXML
    private Label thisSeasonOutputLabel4;

    @FXML
    private Label thisYearOutputLabel5;

    @FXML
    private Label thisWeekOutputLabel1;

    @FXML
    private Label thisYearInputLabel5;

    @FXML
    private Label thisSeasonInputShowLabel4;

    @FXML
    private Label thisMonthInputLabel3;

    @FXML
    private Label thisMonthInputShowLabel3;

    @FXML
    private Label thisDayInputLabel;

    public void initialization() {

    }
}
