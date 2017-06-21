/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import orm.*;
import java.util.ArrayList;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase creada para procesar {@code libro.xml} a fin de poblar el libro a
 * partir de un registro inicial.
 *
 * @author Mauricio Acencio
 * @see Transformador
 */
public class Poblador extends DefaultHandler {

    private String temp;
    private Curso curso;
    private Asignatura ramo;
    private Profesor profesor;
    private Institucion colegio;
    private Actividad actv;
    private Apoderado apod;
    private Estudiante alum;
    private Anotaciones anot;
    private Asistencia asist;
    private String rAl;
    private Nota nota;
    private ArrayList<String> rutsAl;
    private ArrayList<String> rutsAp;
    private ArrayList<Estudiante> alms;
    private ArrayList<Apoderado> apods;
    private PersistentTransaction t;

    /**
     * Recive un set caracteres contenidos en un nodo
     *
     * @param buffer los caracteres
     * @param start posicion de inicio en el arreglo
     * @param length el no. de caracteres a usar
     */
    @Override
    public void characters(char[] buffer, int start, int length) {
        temp = new String(buffer, start, length);
    }

    /**
     * Recive el inicio de un nodo
     *
     * @param uri la direccion del nombre del nodo
     * @param localName nombre local del nodo
     * @param qName nombre completo del nodo, con prefijo
     * @param attributes atributos del nodo
     * @throws SAXException para errores posibles durante el parseo
     */
    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        temp = "";
        if (qName.equalsIgnoreCase("colegio")) {
            colegio = InstitucionDAO.createInstitucion();
            colegio.setNombre(attributes.getValue("nombre"));
            String direccion = attributes.getValue("direccion");
            Directivo dir = DirectivoDAO.createDirectivo();
            dir.setPersona_id_fk(PersonaDAO.createPersona());
            dir.getPersona_id_fk().setNombre(direccion);
            String rut = attributes.getValue("rut");
            dir.getPersona_id_fk().setRut(rut);
            colegio.directivo.add(dir);
            apods = new ArrayList<>();
            rutsAp = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("curso")) {
            curso = CursoDAO.createCurso();
            curso.setNivel(Byte.parseByte(attributes.getValue("nivel")));
            curso.setLetra(attributes.getValue("letra").charAt(0));
            rutsAl = new ArrayList<>();
            alms = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("profesor")) {
            profesor = ProfesorDAO.createProfesor();
            profesor.setPersona_id_fk(PersonaDAO.createPersona());
            profesor.getPersona_id_fk().setNombre(attributes.getValue("nombre"));
            profesor.getPersona_id_fk().setRut(attributes.getValue("rut"));
        } else if (qName.equalsIgnoreCase("ramo")) {
            String nombre = attributes.getValue("nombre");
            ramo = new Asignatura();
            ramo.setNombre(nombre);
            ramo.setProfesorid_pk(profesor);
        } else if (qName.equalsIgnoreCase("actividad")) {
            actv = ActividadDAO.createActividad();
            actv.setNombre(attributes.getValue("nombre"));
        } else if (qName.equalsIgnoreCase("alumno")) {
            alum = EstudianteDAO.createEstudiante();
            alum.setPersona_id_fk(PersonaDAO.createPersona());
        } else if (qName.equalsIgnoreCase("apoderado")) {
            String rut = attributes.getValue("rut");
            if (rutsAp.contains(rut)) {
                apod = apods.get(rutsAp.indexOf(rut));
            } else {
                apod = ApoderadoDAO.createApoderado();
                apod.setPersona_id_fk(PersonaDAO.createPersona());
                apod.getPersona_id_fk().setNombre(attributes.getValue("nombre"));
                apod.getPersona_id_fk().setRut(rut);
                rutsAp.add(rut);
                apods.add(apod);
            }
            alum.setApoderado_id_fk(apod);
        } else if (qName.equalsIgnoreCase("anotacion")) {
            anot = AnotacionesDAO.createAnotaciones();
            anot.setEsPositiva(attributes.getValue("p").equals("true"));
        } else if (qName.equalsIgnoreCase("fecha")) {
            asist = AsistenciaDAO.createAsistencia();
            asist.setPresente(attributes.getValue("presente").equals("true"));
        } else if (qName.equalsIgnoreCase("nota")) {
            nota = NotaDAO.createNota();
            rAl = attributes.getValue("al");
            if (rutsAl.contains(rAl)) {
                Estudiante get = alms.get(rutsAl.indexOf(rAl));
                nota.setEstudiante_id_fk(get);
            } else {
                throw new SAXException("Nota aparece sin alumno registrado");
            }
        }
    }

    /**
     * Recive el cierre de un nodo
     *
     * @param uri la direccion del nombre del nodo
     * @param localName nombre local del nodo
     * @param qName nombre completo del nodo, con prefijo
     * @throws SAXException para errores posibles durante el parseo
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("tipo")) {
            actv.setTipo(temp);
        } else if (qName.equalsIgnoreCase("descripcion")) {
            actv.setDescripcion(temp);
        } else if (qName.equalsIgnoreCase("nota")) {
            nota.setNota(Float.parseFloat(temp));
            nota.setActividad_id_fk(actv);
            actv.nota.add(nota);
        } else if (qName.equalsIgnoreCase("actividad")) {
            ramo.actividad.add(actv);
        } else if (qName.equalsIgnoreCase("ramo")) {
            curso.asignatura.add(ramo);
        } else if (qName.equalsIgnoreCase("nombre")) {
            alum.getPersona_id_fk().setNombre(qName);
        } else if (qName.equalsIgnoreCase("rut")) {
            alum.getPersona_id_fk().setRut(temp);
            rutsAl.add(temp);
        } else if (qName.equalsIgnoreCase("ingreso")) {
            alum.setAgnoIngreso(Integer.parseInt(temp));
            String digs = temp.substring(2);
            String rut = alum.getPersona_id_fk().getRut();
            alum.setMatricula(rut.concat(digs));
        } else if (qName.equalsIgnoreCase("anotacion")) {
            anot.setObservacion(temp);
            alum.anotaciones.add(anot);
        } else if (qName.equalsIgnoreCase("anotaciones")) {
        } else if (qName.equalsIgnoreCase("fecha")) {
            asist.setFecha(temp);
            alum.asistencia.add(asist);
        } else if (qName.equalsIgnoreCase("alumno")) {
            Curso_estudiante ce = Curso_estudianteDAO.createCurso_estudiante();
            ce.setCurso_id_fk(curso);
            ce.setEstudiante_id_fk(alum);
            curso.curso_estudiante.add(ce);
            alms.add(alum);
        } else if (qName.equalsIgnoreCase("curso")) {
            colegio.curso.add(curso);
        } else if (qName.equalsIgnoreCase("colegio")) {
            try {
                t = LibroClasePersistentManager.instance().getSession().beginTransaction();
                for (Apoderado ap : apods) {
                    PersonaDAO.save(ap.getPersona_id_fk());
                    ApoderadoDAO.save(ap);
                }
                for (Curso cur : colegio.curso.toArray()) {
                    CursoDAO.save(cur);
                    for (Asignatura asig : cur.asignatura.toArray()) {
                        PersonaDAO.save(asig.getProfesorid_pk().getPersona_id_fk());
                        ProfesorDAO.save(asig.getProfesorid_pk());
                        AsignaturaDAO.save(asig);
                        for (Actividad actividad : asig.actividad.toArray()) {
                            ActividadDAO.save(actividad);
                        }
                    }
                    for (Curso_estudiante ce : cur.curso_estudiante.toArray()) {
                        Estudiante est = ce.getEstudiante_id_fk();
                        System.out.println(est == null);
                        PersonaDAO.save(est.getPersona_id_fk());
                        EstudianteDAO.save(est);
                        for (Anotaciones antcn : est.anotaciones.toArray()) {
                            AnotacionesDAO.save(antcn);
                        }
                        for (Nota nt : est.nota.toArray()) {
                            ActividadDAO.save(nt.getActividad_id_fk());
                            NotaDAO.save(nt);
                        }
                    }
                    for (Curso_estudiante ce : cur.curso_estudiante.toArray()) {
                        Curso_estudianteDAO.save(ce);
                    }
                }
                PersonaDAO.save(colegio.directivo.toArray()[0].getPersona_id_fk());
                DirectivoDAO.save(colegio.directivo.toArray()[0]);
                InstitucionDAO.save(colegio);
                t.commit();
            } catch (PersistentException ex) {
                throw new SAXException(ex);
            }
            Principal.colegio = colegio;
        }
    }
}
