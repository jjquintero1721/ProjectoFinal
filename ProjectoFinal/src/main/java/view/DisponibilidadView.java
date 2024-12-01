package view;

import controller.GestorCitas;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Psicologo;

import java.util.List;

public class DisponibilidadView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();

        // Crear el título
        TableView<Psicologo> tablaPsicologos = new TableView<>();

        // Configurar columnas
        TableColumn<Psicologo, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Psicologo, String> colEspecialidad = new TableColumn<>("Especialidad");
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));

        TableColumn<Psicologo, String> colHorariosDisponibles = new TableColumn<>("Horarios Disponibles");
        colHorariosDisponibles.setCellValueFactory(new PropertyValueFactory<>("horariosDisponibles"));

        // Agregar columnas a la tabla
        tablaPsicologos.getColumns().addAll(colNombre, colEspecialidad, colHorariosDisponibles);

        // Cargar los datos
        cargarTablaDisponibilidad(tablaPsicologos);

        // Configurar el layout
        VBox layout = new VBox(10, tablaPsicologos);
        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Disponibilidad de Psicólogos");
        stage.show();
    }

    private void cargarTablaDisponibilidad(TableView<Psicologo> tabla) {
        // Obtener datos desde el controlador
        List<Psicologo> psicologos = gestorCitas.obtenerPsicologos();

        // Asignar datos al TableView
        tabla.setItems(FXCollections.observableArrayList(psicologos));
    }
}
