package view;

import controller.GestorCitas;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cita;

import java.util.List;

public class GestionCitasView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();

        Label lblTitulo = new Label("Gestión de Citas");
        Button btnRegistrarCita = new Button("Registrar Nueva Cita");
        Button btnCancelarCita = new Button("Cancelar Cita");
        Button btnModificarCita = new Button("Modificar Cita");

        // Tabla para mostrar las citas existentes
        TableView<Cita> tablaCitas = new TableView<>();
        cargarTablaCitas(tablaCitas);

        // Acción para registrar nueva cita
        btnRegistrarCita.setOnAction(e -> new RegistroCitaView().mostrar());

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

        // Acción para modificar una cita
        btnModificarCita.setOnAction(e -> {
            Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
            if (citaSeleccionada != null) {
                TextInputDialog dialog = new TextInputDialog(citaSeleccionada.getFechaHora().toString());
                dialog.setTitle("Modificar Cita");
                dialog.setHeaderText("Modificar Fecha y Hora");
                dialog.setContentText("Ingrese la nueva fecha y hora (YYYY-MM-DDTHH:MM):");

                dialog.showAndWait().ifPresent(nuevaFechaHora -> {
                    gestorCitas.modificarCita(citaSeleccionada.getId(), nuevaFechaHora);
                    cargarTablaCitas(tablaCitas);
                    mostrarMensaje("Cita modificada correctamente.");
                });
            } else {
                mostrarMensaje("Por favor, seleccione una cita.");
            }
        });

        // Layout
        VBox layout = new VBox(10, lblTitulo, tablaCitas, btnRegistrarCita, btnCancelarCita, btnModificarCita);
        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Gestión de Citas");
        stage.show();
    }

    private void cargarTablaCitas(TableView<Cita> tabla) {
        List<Cita> citas = gestorCitas.obtenerCitas();
        tabla.getItems().clear();
        tabla.getItems().addAll(citas);
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}
