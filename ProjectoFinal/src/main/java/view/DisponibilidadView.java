package view;

import controller.GestorCitas;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Psicologo;

import java.util.List;

public class DisponibilidadView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();

        Label lblTitulo = new Label("Disponibilidad de Psicólogos");
        TableView<Psicologo> tablaPsicologos = new TableView<>();
        cargarTablaDisponibilidad(tablaPsicologos);

        VBox layout = new VBox(10, lblTitulo, tablaPsicologos);
        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Disponibilidad de Psicólogos");
        stage.show();
    }

    private void cargarTablaDisponibilidad(TableView<Psicologo> tabla) {
        List<Psicologo> psicologos = gestorCitas.obtenerPsicologos();
        tabla.getItems().clear();
        tabla.getItems().addAll(psicologos);
    }
}

