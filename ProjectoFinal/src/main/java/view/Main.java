package view;

import controller.GestorCitas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Psicologo;

public class Main extends Application {
    private final GestorCitas gestorCitas = new GestorCitas();
    @Override
    public void start(Stage primaryStage) {

        gestorCitas.inicializarDatos();
        // Crear botones para abrir las diferentes vistas
        Button btnRegistroEstudiantes = new Button("Registrar Estudiantes");
        Button btnGestionCitas = new Button("Gestionar Citas");
        Button btnDisponibilidadPsicologos = new Button("Ver Disponibilidad de Psicólogos");


        // Acciones de los botones
        btnRegistroEstudiantes.setOnAction(e -> new RegistroEstudianteView().mostrar("Estudiante registrado correctamente."));
        btnGestionCitas.setOnAction(e -> new GestionCitasView().mostrar());
        btnDisponibilidadPsicologos.setOnAction(e -> new DisponibilidadView().mostrar());

        // Layout
        VBox layout = new VBox(10, btnRegistroEstudiantes, btnGestionCitas, btnDisponibilidadPsicologos);
        Scene scene = new Scene(layout, 400, 300);

        // Configurar la ventana principal
        primaryStage.setTitle("Sistema de Gestión de Citas - Clínica Psicológica");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
