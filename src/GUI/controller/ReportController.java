package GUI.controller;

import Core.model.User;
import Core.mutual.Report;
import GUI.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.util.List;

public class ReportController implements Controller {
    @FXML
    private Label dayInputShowLabel;
    @FXML
    private Label dayOutputShowLabel;
    @FXML
    private Label weekInputShowLabel;
    @FXML
    private Label weekOutputShowLabel;
    @FXML
    private Label monthInputShowLabel;
    @FXML
    private Label monthOutputShowLabel;
    @FXML
    private Label yearInputShowLabel;
    @FXML
    private Label yearOutputShowLabel;

    @FXML
    private PieChart dayPieChart;
    @FXML
    private PieChart weekPieChart;
    @FXML
    private PieChart monthPieChart;
    @FXML
    private PieChart yearPieChart;

    public void setPieChar(String bookID) {
        Report report = new Report();
        List<List<PieChart.Data>> lists = report.reportData(bookID, System.currentTimeMillis());

        double dayOut = lists.get(0).get(0).getPieValue();
        double dayIn = lists.get(0).get(1).getPieValue();
        double weekOut = lists.get(1).get(0).getPieValue();
        double weekIn = lists.get(1).get(1).getPieValue();
        double monthOut = lists.get(2).get(0).getPieValue();
        double monthIn = lists.get(2).get(1).getPieValue();
        double yearOut = lists.get(3).get(0).getPieValue();
        double yearIn = lists.get(3).get(1).getPieValue();

        if (dayOut == 0 && dayIn == 0) {
            setEmptyPic(dayPieChart, dayOutputShowLabel, dayInputShowLabel);
        } else {
            dayOutputShowLabel.setText(String.valueOf(dayOut));
            dayInputShowLabel.setText(String.valueOf(dayIn));
            dayPieChart.setData(FXCollections.observableArrayList(lists.get(0)));
        }
        if (weekOut == 0 && weekIn == 0) {
            setEmptyPic(weekPieChart, weekOutputShowLabel, weekInputShowLabel);
        } else {
            weekOutputShowLabel.setText(String.valueOf(weekOut));
            weekInputShowLabel.setText(String.valueOf(weekIn));
            weekPieChart.setData(FXCollections.observableArrayList(lists.get(1)));
        }
        if (monthOut == 0 && monthIn == 0) {
            setEmptyPic(monthPieChart, monthOutputShowLabel, monthInputShowLabel);
        } else {
            monthOutputShowLabel.setText(String.valueOf(monthOut));
            monthInputShowLabel.setText(String.valueOf(monthIn));
            monthPieChart.setData(FXCollections.observableArrayList(lists.get(2)));
        }
        if (yearOut == 0 && yearIn == 0) {
            setEmptyPic(yearPieChart, yearOutputShowLabel, yearInputShowLabel);
        } else {
            yearOutputShowLabel.setText(String.valueOf(yearOut));
            yearInputShowLabel.setText(String.valueOf(yearIn));
            yearPieChart.setData(FXCollections.observableArrayList(lists.get(3)));
        }
    }

    private void setEmptyPic(PieChart pie, Label out, Label in) {
        pie.setData(FXCollections.observableList(List.of(new PieChart.Data("空", 1))));
        out.setText("0");
        in.setText("0");
    }

    @Override
    public void initialize(User user) {

    }
}
