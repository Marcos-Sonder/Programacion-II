import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// HERENCIA: Estudiante y Docente son Personas.
abstract class Persona {
    private final String nombre;
    private final String ci;

    protected Persona(String nombre, String ci) {
        this.nombre = nombre;
        this.ci = ci;
    }

    public String getNombre() { return nombre; }
    public String getCi() { return ci; }
}

class Estudiante extends Persona {
    private final String codigo;
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    public Estudiante(String nombre, String ci, String codigo) {
        super(nombre, ci);
        this.codigo = codigo;
    }

    public String getCodigo() { return codigo; }

    public Inscripcion inscribirse(Materia materia) {
        if (materia == null) throw new IllegalArgumentException("La materia no puede ser nula.");

        // Evita inscribir dos veces al mismo estudiante en la misma materia.
        for (Inscripcion i : inscripciones) {
            if (i.getMateria() == materia) {
                throw new IllegalStateException("El estudiante ya está inscrito en esta materia.");
            }
        }

        Inscripcion nueva = new Inscripcion(this, materia, LocalDate.now());
        inscripciones.add(nueva);
        materia.registrarInscripcion(nueva);
        return nueva;
    }

    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    @Override
    public String toString() {
        return "Estudiante " + getNombre() + " (código " + codigo + ")";
    }
}

class Docente extends Persona {
    private final String especialidad;
    private final List<Materia> materias = new ArrayList<>();
    private final List<Ayudante> ayudantes = new ArrayList<>();
    private Carrera carrera;

    public Docente(String nombre, String ci, String especialidad) {
        super(nombre, ci);
        this.especialidad = especialidad;
    }

    public void dictar(Materia materia) {
        if (materia == null) throw new IllegalArgumentException("La materia no puede ser nula.");
        if (materias.contains(materia)) return;
        materia.asignarDocente(this); // valida que no exista otro docente
        materias.add(materia);
    }

    public Acta generarActa(Materia materia) {
        // DEPENDENCIA: Acta se usa temporalmente y no se guarda como atributo.
        return new Acta(materia);
    }

    void registrarAyudante(Ayudante ayudante) {
        if (!ayudantes.contains(ayudante)) ayudantes.add(ayudante);
    }

    void asignarCarrera(Carrera nuevaCarrera) {
        if (carrera != null && carrera != nuevaCarrera) {
            throw new IllegalStateException("El docente ya pertenece a otra carrera.");
        }
        carrera = nuevaCarrera;
    }

    public List<Materia> getMaterias() {
        return Collections.unmodifiableList(materias);
    }

    public List<Ayudante> getAyudantes() {
        return Collections.unmodifiableList(ayudantes);
    }

    @Override
    public String toString() {
        return "Docente " + getNombre() + " (" + especialidad + ")";
    }
}

// COMPOSICIÓN: Universidad crea y administra sus carreras.
class Universidad {
    private final String nombre;
    private final List<Carrera> carreras = new ArrayList<>();

    public Universidad(String nombre) { this.nombre = nombre; }

    public Carrera crearCarrera(String nombreCarrera) {
        Carrera carrera = new Carrera(nombreCarrera);
        carreras.add(carrera);
        return carrera;
    }

    public String getNombre() { return nombre; }
    public List<Carrera> getCarreras() {
        return Collections.unmodifiableList(carreras);
    }
}

// AGREGACIÓN: Carrera incorpora docentes que ya existen independientemente.
class Carrera {
    private final String nombre;
    private final List<Docente> docentes = new ArrayList<>();

    Carrera(String nombre) { this.nombre = nombre; }

    public void agregarDocente(Docente docente) {
        if (docente == null) throw new IllegalArgumentException("El docente no puede ser nulo.");
        if (docentes.contains(docente)) return;
        docente.asignarCarrera(this);
        docentes.add(docente);
    }

    public String getNombre() { return nombre; }
    public List<Docente> getDocentes() {
        return Collections.unmodifiableList(docentes);
    }
}

// COMPOSICIÓN: los horarios se crean dentro de Materia.
// Horario es una clase anidada y su constructor es privado.
class Materia {
    private final String codigo;
    private final int creditos;
    private Docente docente;
    private final List<Horario> horarios = new ArrayList<>();
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    public Materia(String codigo, int creditos) {
        this.codigo = codigo;
        this.creditos = creditos;
    }

    public Horario agregarHorario(LocalDate fecha, String aula) {
        Horario horario = new Horario(fecha, aula);
        horarios.add(horario);
        return horario;
    }

    void registrarInscripcion(Inscripcion inscripcion) {
        if (!inscripciones.contains(inscripcion)) inscripciones.add(inscripcion);
    }

    void asignarDocente(Docente nuevoDocente) {
        if (docente != null && docente != nuevoDocente) {
            throw new IllegalStateException("La materia ya tiene un docente asignado.");
        }
        docente = nuevoDocente;
    }

    public String getCodigo() { return codigo; }
    public int getCreditos() { return creditos; }
    public Docente getDocente() { return docente; }
    public List<Horario> getHorarios() {
        return Collections.unmodifiableList(horarios);
    }
    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    static class Horario {
        private final LocalDate fecha;
        private final String aula;

        private Horario(LocalDate fecha, String aula) {
            this.fecha = fecha;
            this.aula = aula;
        }

        public LocalDate getFecha() { return fecha; }
        public String getAula() { return aula; }
    }
}

interface Calificable {
    double calcularNota();
}

// ASOCIACIÓN: Inscripcion conoce a un Estudiante y una Materia.
class Inscripcion implements Calificable {
    private final Estudiante estudiante;
    private final Materia materia;
    private final LocalDate fecha;
    private Double nota; // null significa que todavía no se registró.

    Inscripcion(Estudiante estudiante, Materia materia, LocalDate fecha) {
        this.estudiante = estudiante;
        this.materia = materia;
        this.fecha = fecha;
    }

    public void registrarNota(double nota) {
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100.");
        }
        this.nota = nota;
    }

    @Override
    public double calcularNota() {
        if (nota == null) {
            throw new IllegalStateException("La nota todavía no fue registrada.");
        }
        return nota;
    }

    public boolean tieneNotaRegistrada() { return nota != null; }
    public Estudiante getEstudiante() { return estudiante; }
    public Materia getMateria() { return materia; }
    public LocalDate getFecha() { return fecha; }
}

// DEPENDENCIA: Acta recibe Materia como parámetro y construye su contenido.
class Acta {
    private final LocalDate fecha = LocalDate.now();
    private final String contenido;

    Acta(Materia materia) {
        StringBuilder sb = new StringBuilder();
        sb.append("ACTA ").append(materia.getCodigo())
          .append(" - ").append(fecha).append(System.lineSeparator());

        for (Inscripcion i : materia.getInscripciones()) {
            sb.append(i.getEstudiante().getNombre()).append(" : ");
            if (i.tieneNotaRegistrada()) {
                sb.append(i.calcularNota());
            } else {
                sb.append("Pendiente");
            }
            sb.append(System.lineSeparator());
        }
        contenido = sb.toString();
    }

    @Override
    public String toString() { return contenido; }
}

// PUNTO 7: Ayudante ES un Estudiante y se asocia con un Docente.
class Ayudante extends Estudiante {
    private Docente docente;

    public Ayudante(String nombre, String ci, String codigo) {
        super(nombre, ci, codigo);
    }

    public void colaborarCon(Docente docente) {
        if (docente == null) throw new IllegalArgumentException("El docente no puede ser nulo.");
        if (this.docente != null && this.docente != docente) {
            throw new IllegalStateException("El ayudante ya colabora con otro docente.");
        }
        this.docente = docente;
        docente.registrarAyudante(this);
    }

    public Docente getDocente() { return docente; }

    @Override
    public String toString() {
        return "Ayudante " + getNombre() + " (código " + getCodigo() + ")";
    }
}

public class SistemaAcademico {
    public static void main(String[] args) {
        Universidad umsa = new Universidad("Universidad Mayor de San Andrés");
        Carrera informatica = umsa.crearCarrera("Informática");

        Docente docente = new Docente("Ana Alba", "1234567", "Ingeniería de Software");
        informatica.agregarDocente(docente);

        Materia poo = new Materia("INF-121", 6);
        docente.dictar(poo);

        poo.agregarHorario(LocalDate.of(2026, 7, 2), "Aula 101");
        poo.agregarHorario(LocalDate.of(2026, 7, 9), "Aula 101");

        Estudiante e1 = new Estudiante("Carlos Flores", "7654321", "E-001");
        Estudiante e2 = new Estudiante("Maria Salas", "7654322", "E-002");
        Inscripcion i1 = e1.inscribirse(poo);
        Inscripcion i2 = e2.inscribirse(poo);
        i1.registrarNota(85);
        i2.registrarNota(92);

        // Ejemplo de Ayudante: hereda de Estudiante y colabora con el docente.
        Ayudante ayudante = new Ayudante("Luis Rojas", "7654323", "A-001");
        ayudante.colaborarCon(docente);

        System.out.println(docente);
        System.out.println(e1);
        System.out.println(e2);
        System.out.println("Nota de " + e1.getNombre() + " : " + i1.calcularNota());
        System.out.println(docente.generarActa(poo));
    }
}
