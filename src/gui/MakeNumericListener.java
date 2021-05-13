package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class MakeNumericListener implements ChangeListener {
    private final TextField textField;

    public MakeNumericListener(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        String newVal = (String) newValue;
        if (!newVal.matches("\\d*"))
            textField.setText(newVal.replaceAll("[^\\d]", ""));
    }
}
