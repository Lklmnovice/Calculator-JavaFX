package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Calculator;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Text expressionField;
    @FXML
    private Text numberField;
    private Calculator c;

    private boolean endCalculation;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c = new Calculator();
        endCalculation = false;
    }

    @FXML
    void allClear() {
        c.allClear();
        updateExpressionFiled();
        numberField.setText("");
    }

    @FXML
    void delete() {
        c.delete();
        updateExpressionFiled();

    }

    @FXML
    void eval() {
        numberField.setText("" +  c.eval());
        expressionField.setText("");
        endCalculation = true;
    }

    @FXML
    void insert(ActionEvent e) {
        if (endCalculation) {
            endCalculation = false;
            allClear();
        }
        c.insert(( (Button)e.getSource() ).getText().charAt(0) );
        updateExpressionFiled();
    }

    private void updateExpressionFiled() {
        expressionField.setText(c.getExpression());
    }

}


