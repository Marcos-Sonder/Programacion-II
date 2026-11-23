public class PruebasIntegridad {
    public static void main(String[] args) {
        Universidad u = new Universidad("UMSA");
        Carrera c1 = u.crearCarrera("Informática");
        Carrera c2 = u.crearCarrera("Matemática");

        Docente d1 = new Docente("Ana", "111", "Software");
        Docente d2 = new Docente("Luis", "222", "Datos");
        c1.agregarDocente(d1);
        Materia m = new Materia("INF-121", 6);
        d1.dictar(m);

        Estudiante e = new Estudiante("Carlos", "333", "E-01");

        // Caso 1: doble inscripción.
        e.inscribirse(m);
        intentar("Doble inscripción", () -> e.inscribirse(m));

        // Caso 2: dos docentes en una materia.
        intentar("Dos docentes en un curso", () -> d2.dictar(m));

        // Caso 3: nota no registrada.
        Inscripcion sinNota = new Estudiante("María", "444", "E-02").inscribirse(m);
        intentar("Nota sin registrar", () -> sinNota.calcularNota());

        // Caso 4: un docente agregado a dos carreras.
        intentar("Docente en dos carreras", () -> c2.agregarDocente(d1));

        // Caso 5: composición no estricta.
        // No se puede hacer new Materia.Horario(...): su constructor es privado.
        System.out.println("Composición estricta: Horario solo se crea con materia.agregarHorario(...).");

        System.out.println("Pruebas de integridad finalizadas.");
    }

    private static void intentar(String caso, Runnable accion) {
        try {
            accion.run();
            System.out.println(caso + ": NO se detectó el problema.");
        } catch (RuntimeException ex) {
            System.out.println(caso + ": controlado -> " + ex.getMessage());
        }
    }
}
