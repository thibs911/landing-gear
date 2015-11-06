package controller;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Created by Cl�ment on 28/10/2015.
 */
public class ToggleSwitch extends HBox implements ObservableValue {

    private ArrayList<ChangeListener> listeners = new ArrayList<>();
    private final Label label = new Label();
    private final Button button = new Button();

    protected SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
    public SimpleBooleanProperty switchOnProperty() { return switchedOn; }

    private void init() {

        label.setText("UP");

        getChildren().addAll(label, button);
        button.setOnAction((e) -> switchedOn.set(!switchedOn.get()));
        label.setOnMouseClicked(e -> switchedOn.set(!switchedOn.get()));
        setStyle();
        bindProperties();
    }

    private void setStyle() {
        //Default Width
        setWidth(80);
        label.setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: green; -fx-text-fill:black; -fx-background-radius: 4;");
        setAlignment(Pos.CENTER_LEFT);
    }

    private void bindProperties() {
        label.prefWidthProperty().bind(widthProperty().divide(2));
        label.prefHeightProperty().bind(heightProperty());
        button.prefWidthProperty().bind(widthProperty().divide(2));
        button.prefHeightProperty().bind(heightProperty());
    }

    public ToggleSwitch() {
        init();
        switchedOn.addListener((a,b,c) -> {
            if (c) {
                label.setText("DOWN");
                setStyle("-fx-background-color: red;");
                label.toFront();
                for (ChangeListener listener : listeners) {
                    listener.changed(switchedOn,!(switchOnProperty().getValue()),switchOnProperty().getValue());
                }
            }
            else {
                label.setText("UP");
                setStyle("-fx-background-color: green;");
                button.toFront();
                for (ChangeListener listener : listeners) {
                    listener.changed(switchedOn,!(switchOnProperty().getValue()), switchOnProperty().getValue());
                }
            }
        });
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Object getValue() {
        return null;
    }
}