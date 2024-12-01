package view;

import controller.GestorCitas;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cita;

import java.util.List;

public class GestionCitasView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();

        // Crear el título
        Label lblTitulo = new Label("Gestión de Citas");

        // Crear el TableView
        TableView<Cita> tablaCitas = new TableView<>();

        // Configurar las columnas
        TableColumn<Cita, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cita, String> colEstudiante = new TableColumn<>("Estudiante");
        colEstudiante.setCellValueFactory(c ->
                c.getValue().getEstudiante() != null ?
                        new SimpleStringProperty(c.getValue().getEstudiante().getNombre()) :
                        new SimpleStringProperty("")
        );

        TableColumn<Cita, String> colPsicologo = new TableColumn<>("Psicólogo");
        colPsicologo.setCellValueFactory(c ->
                c.getValue().getPsicologo() != null ?
                        new SimpleStringProperty(c.getValue().getPsicologo().getNombre()) :
                        new SimpleStringProperty("")
        );

        TableColumn<Cita, String> colFechaHora = new TableColumn<>("Fecha y Hora");
        colFechaHora.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getFechaHora().toString())
        );

        // Agregar columnas al TableView
        tablaCitas.getColumns().addAll(colId, colEstudiante, colPsicologo, colFechaHora);

        // Cargar las citas en la tabla
        cargarTablaCitas(tablaCitas);

        // Botones
        Button btnRegistrarCita = new Button("Registrar Nueva Cita");
        Button btnModificarCita = new Button("Modificar Cita");
        Button btnCancelarCita = new Button("Cancelar Cita");

        // Acción para registrar una nueva cita
        btnRegistrarCita.setOnAction(e -> new RegistroCitaView().mostrar());

        // Acción para modificar una cita
        btnModificarCita.setOnAction(e -> {
            Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
            if (citaSeleccionada != null) {
                TextInputDialog dialog = new TextInputDialog(citaSeleccionada.getFechaHora().toString());
                dialog.setTitle("Modificar Cita");
                dialog.setHeaderText("Modificar Fecha y Hora");
                dialog.setContentText("Ingrese la nueva fecha y hora (YYYY-MM-DDTHH:MM):");

                dialog.showAndWait().ifPresent(nuevaFechaHora -> {
                    try {
                        gestorCitas.modificarCita(citaSeleccionada.getId(), nuevaFechaHora);
                        cargarTablaCitas(tablaCitas);
                        mostrarMensaje("Cita modificada correctamente.");
                    } catch (IllegalArgumentException ex) {
                        mostrarMensaje(ex.getMessage());
                    } catch (Exception ex) {
                        mostrarMensaje("Error al modificar la cita: " + ex.getMessage());
                    }
                });
            } else {
                mostrarMensaje("Por favor, seleccione una cita.");
            }
        });

        // Acción para cancelar una cita
        btnCancelarCita.setOnAction(e -> {
            Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
            if (citaSeleccionada != null) {
                gestorCitas.cancelarCita(citaSeleccionada.getId());
                cargarTablaCitas(tablaCitas);
                mostrarMensaje("Cita cancelada correctamente.");
            } else {
                mostrarMensaje("Por favor, seleccione una cita.");
            }
        });

        // Layout
        VBox layout = new VBox(10, lblTitulo, tablaCitas, btnRegistrarCita, btnModificarCita, btnCancelarCita);
        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Gestión de Citas");
        stage.show();
    }

    private void cargarTablaCitas(TableView<Cita> tabla) {
        List<Cita> citas = gestorCitas.obtenerCitas();
        tabla.setItems(FXCollections.observableArrayList(citas));
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}
