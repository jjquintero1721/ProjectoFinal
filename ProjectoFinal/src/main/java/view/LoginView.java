package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Usuario;
import repository.UsuarioRepository;

public class LoginView {
    private final UsuarioRepository usuarioRepo = new UsuarioRepository();

    public void mostrar(Stage primaryStage, Runnable onLoginSuccess) {
        VBox layout = new VBox(10);

        Label lblTitulo = new Label("Iniciar Sesión");
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Usuario");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        Button btnLogin = new Button("Iniciar Sesión");
        Button btnRegister = new Button("Registrarse");

        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            Usuario usuario = usuarioRepo.obtenerPorUsername(username);

            if (usuario != null && usuario.getPassword().equals(password)) {
                onLoginSuccess.run(); // Ejecutar callback para cargar la vista principal
            } else {
                mostrarMensaje("Usuario o contraseña incorrectos.");
            }
        });

        btnRegister.setOnAction(e -> new RegistroUsuarioView().mostrar());

        layout.getChildren().addAll(lblTitulo, txtUsername, txtPassword, btnLogin, btnRegister);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.show();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}


