package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Usuario;
import repository.UsuarioRepository;

public class RegistroUsuarioView {
    private final UsuarioRepository usuarioRepo = new UsuarioRepository();

    public void mostrar() {
        Stage stage = new Stage();

        VBox layout = new VBox(10);

        Label lblTitulo = new Label("Registrar Usuario");
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Usuario");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        Button btnRegistrar = new Button("Registrar");

        btnRegistrar.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                mostrarMensaje("Por favor, complete todos los campos.");
                return;
            }

            if (usuarioRepo.obtenerPorUsername(username) != null) {
                mostrarMensaje("El usuario ya existe.");
                return;
            }

            Usuario usuario = new Usuario(username, password);
            usuarioRepo.guardar(usuario);
            mostrarMensaje("Usuario registrado exitosamente.");
            stage.close();
        });

        layout.getChildren().addAll(lblTitulo, txtUsername, txtPassword, btnRegistrar);
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Registro de Usuario");
        stage.show();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}
