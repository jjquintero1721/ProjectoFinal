package controller;

import model.Cita;
import model.Estudiante;
import model.Psicologo;
import repository.CitaRepository;
import repository.EstudianteRepository;
import repository.PsicologoRepository;

import java.util.List;

public class GestorCitas {
    private final CitaRepository citaRepo = new CitaRepository();
    private final EstudianteRepository estudianteRepo = new EstudianteRepository();
    private final PsicologoRepository psicologoRepo = new PsicologoRepository();

    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante.getCodigoEstudiante() == null || estudiante.getCodigoEstudiante().isEmpty()) {
            throw new IllegalArgumentException("El código del estudiante es obligatorio.");
        }
        estudianteRepo.guardar(estudiante);
    }

    public void registrarPsicologo(Psicologo psicologo) {
        psicologoRepo.guardar(psicologo);
    }

    public void registrarCita(Cita cita) {
        citaRepo.guardar(cita);
        actualizarDisponibilidadPsicologo(cita.getPsicologo(), cita.getFechaHora().toString(), false);
    }

    public void modificarCita(Long idCita, String nuevaFechaHora) {
        citaRepo.modificarCita(idCita, nuevaFechaHora);
    }

    public void cancelarCita(Long idCita) {
        Cita cita = citaRepo.obtenerPorId(idCita);
        citaRepo.cancelarCita(idCita);
        actualizarDisponibilidadPsicologo(cita.getPsicologo(), cita.getFechaHora().toString(), true);
    }

    public List<Psicologo> obtenerPsicologos() {
        return psicologoRepo.obtenerTodos();
    }

    public Psicologo obtenerPsicologoPorNombre(String nombre) {
        return psicologoRepo.obtenerPorNombre(nombre);
    }

    public boolean verificarDisponibilidadPsicologo(String nombre, String horario) {
        return psicologoRepo.verificarDisponibilidad(nombre, horario);
    }

    private void actualizarDisponibilidadPsicologo(Psicologo psicologo, String horario, boolean disponible) {
        String horarios = psicologo.getHorariosDisponibles();
        if (disponible) {
            horarios += "," + horario;
        } else {
            horarios = horarios.replace(horario, "");
        }
        psicologoRepo.actualizarDisponibilidad(psicologo, horarios);
    }
    public List<Estudiante> obtenerEstudiantes() {
        return estudianteRepo.obtenerTodos();
    }
    public List<Cita> obtenerCitas() {
        return citaRepo.obtenerTodas();
    }

}
