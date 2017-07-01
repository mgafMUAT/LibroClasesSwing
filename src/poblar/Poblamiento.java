/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblar;

//import java.util.Random;
//import org.orm.PersistentException;
//import org.orm.PersistentTransaction;
//import orm.*;
/**
 * Clase para automatizar el poblamiento inicial
 *
 * @author MauricioGabriel
 */
public class Poblamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Random r = new Random();
//        String[] ramos = {
//            "Matemática", "Lenguaje", "Sociedad", "Inglés", "Comunicación"
//        };
//        PersistentTransaction t = LibroClasePersistentManager.instance().getSession().beginTransaction();
//        Curso cur = CursoDAO.getCursoByORMID(1);
//        for (int h = 2; h <= 5; h++) {
//            for (int j = 1; j <= 10; j++) {
//                Actividad actv = ActividadDAO.createActividad();
//                actv.setNombre("Actividad " + j);
//                actv.setDescripcion("");
//                actv.setTipo(r.nextBoolean() ? "Práctica" : "Teórica");
//                actv.setAsignatura_id_fk();
//                ActividadDAO.save(actv);
//                if (j <= 5) {
//                    for (int i = 2; i <= 30; i++) {
//                        Nota n = new Nota();
//                        n.setNota(7);
//                        n.setEstudiante_id_fk(EstudianteDAO.loadEstudianteByORMID(i));
//                        n.setActividad_id_fk(actv);
//                        NotaDAO.save(n);
//                    }
//                }
//            }
//        }
//        for (byte i = 2; i <= 16; i++) {
//            Curso cur = CursoDAO.getCursoByORMID(i);
//            byte n = (byte) ((i + 1) / 2);
//            char l = i % 2 == 0 ? 'B' : 'A';
//            for (int j = 0; j < 5; j++) {
//                Asignatura ramo = AsignaturaDAO.createAsignatura();
//                ramo.setProfesorid_pk(ProfesorDAO.getProfesorByORMID(j + 1));
//                ramo.setCurso_id_fk(cur);
//                ramo.setNombre(ramos[j]);
//                AsignaturaDAO.save(ramo);
//                for (int k = 0; k < 10; k++) {
//                    Actividad actv = ActividadDAO.createActividad();
//                    actv.setNombre("Actividad " + j);
//                    actv.setDescripcion("");
//                    actv.setTipo(r.nextBoolean() ? "Práctica" : "Teórica");
//                    actv.setAsignatura_id_fk(ramo);
//                    ActividadDAO.save(actv);
//                }
//            }
//            Apoderado apod = ApoderadoDAO.createApoderado();
//            apod.setPersona_id_fk(PersonaDAO.createPersona());
//            apod.getPersona_id_fk().setNombre("Apoderado" + n + l);
//            apod.getPersona_id_fk().setRut(rut(r));
//            PersonaDAO.save(apod.getPersona_id_fk());
//            ApoderadoDAO.save(apod);
//            for (int j = 0; j < 30; j++) {
//                Estudiante alum = EstudianteDAO.createEstudiante();
//                alum.setPersona_id_fk(PersonaDAO.createPersona());
//                alum.getPersona_id_fk().setNombre("Alumno" + n + l + (i + 1));
//                String rut = rut(r);
//                alum.getPersona_id_fk().setRut(rut);
//                alum.setAgnoIngreso(2017);
//                alum.setMatricula(rut.concat("17"));
//                alum.setApoderado_id_fk(apod);
//                PersonaDAO.save(alum.getPersona_id_fk());
//                EstudianteDAO.save(alum);
//                Curso_estudiante ce = Curso_estudianteDAO.createCurso_estudiante();
//                ce.setCurso_id_fk(cur);
//                ce.setEstudiante_id_fk(alum);
//                Curso_estudianteDAO.save(ce);
//                for (int k = 0; k < 30; k++) {
//                    Asistencia asist = AsistenciaDAO.createAsistencia();
//                    asist.setEstudiante_id_fk(alum);
//                    asist.setPresente(true);
//                    asist.setFecha(String.format("%02d-05-2017", j));
//                    AsistenciaDAO.save(asist);
//                }
//                for (Asignatura asig : cur.asignatura.toArray()) {
//                    for (Actividad actv : asig.actividad.toArray()) {
//                        Nota nota = new Nota();
//                        nota.setNota(7);
//                        nota.setEstudiante_id_fk(alum);
//                        nota.setActividad_id_fk(actv);
//                        NotaDAO.save(nota);
//                    }
//                }
//            }
//        }
//        t.commit();
    }
//
//    private static String rut(Random r) {
//        int f = r.nextInt(2) + 1;
//        String l = Integer.toString(r.nextInt(11));
//        if (l.equals("10")) {
//            l = "k";
//        }
//        return String.format("%d%06d%s", f, r.nextInt(1000000), l);
//    }

    }
