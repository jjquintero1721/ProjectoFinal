package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean isAuthenticated = false; // Bandera para comprobar si el usuario está autenticado

    @Override
    public void start(Stage primaryStage) {
        // Mostrar la pantalla de inicio de sesión
        LoginView loginView = new LoginView();
        loginView.mostrar(primaryStage, () -> cargarVistaPrincipal(primaryStage)); // Cargar la vista principal después del login
    }

    private void cargarVistaPrincipal(Stage primaryStage) {
        // Crear botones para acceder a las diferentes vistas
        Button btnRegistroEstudiantes = new Button("Registrar Estudiantes");
        Button btnGestionCitas = new Button("Gestionar Citas");
        Button btnDisponibilidadPsicologos = new Button("Ver Disponibilidad de Psicólogos");


        // Acciones de los botones
        btnRegistroEstudiantes.setOnAction(e -> new RegistroEstudianteView().mostrar());
        btnGestionCitas.setOnAction(e -> new GestionCitasView().mostrar());
        btnDisponibilidadPsicologos.setOnAction(e -> new DisponibilidadView().mostrar());


        // Layout principal
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

