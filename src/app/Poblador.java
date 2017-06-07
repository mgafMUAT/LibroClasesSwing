/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import orm.*;
import java.text.ParseException;
import java.util.ArrayList;
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
    private String actvNombre;
    private String actvTipo;
    private String actvDesc;
    private String actvFecha;
    private boolean actvEv;
    private String apodNombre;
    private String apodRut;
    private String alumNombre;
    private String alumRut;
    private int alumIngreso;
    private boolean anotPos;
    private ArrayList<Anotaciones> anotaciones;
    private boolean anotLista;
    private ArrayList<Asistencia> asistencia;
    private boolean asiste;
    private ArrayList<Float> notas;
//
//    /**
//     * Recive un set caracteres contenidos en un nodo
//     *
//     * @param buffer los caracteres
//     * @param start posicion de inicio en el arreglo
//     * @param length el no. de caracteres a usar
//     */
//    @Override
//    public void characters(char[] buffer, int start, int length) {
//        temp = new String(buffer, start, length);
//    }
//
//    /**
//     * Recive el inicio de un nodo
//     *
//     * @param uri la direccion del nombre del nodo
//     * @param localName nombre local del nodo
//     * @param qName nombre completo del nodo, con prefijo
//     * @param attributes atributos del nodo
//     * @throws SAXException para errores posibles durante el parseo
//     */
//    @Override
//    public void startElement(String uri, String localName,
//            String qName, Attributes attributes) throws SAXException {
//        temp = "";
//        if (qName.equalsIgnoreCase("colegio")) {
//            String nombre = attributes.getValue("nombre");
//            String direccion = attributes.getValue("direccion");
//            Principal.colegio = new Colegio(nombre, direccion);
//        } else if (qName.equalsIgnoreCase("curso")) {
//            byte nivel = Byte.parseByte(attributes.getValue("nivel"));
//            char letra = attributes.getValue("letra").charAt(0);
//            curso = new Curso(nivel, letra);
//        } else if (qName.equalsIgnoreCase("profesor")) {
//            String nombre = attributes.getValue("nombre");
//            String rut = attributes.getValue("rut");
//            profesor = new Profesor(nombre, rut);
//        } else if (qName.equalsIgnoreCase("ramo")) {
//            String nombre = attributes.getValue("nombre");
//            ramo = new Ramo(nombre, profesor);
//            Principal.colegio.profesores.add(profesor);
//        } else if (qName.equalsIgnoreCase("actividad")) {
//            actvNombre = attributes.getValue("nombre");
//        } else if (qName.equalsIgnoreCase("apoderado")) {
//            apodNombre = attributes.getValue("nombre");
//            apodRut = attributes.getValue("rut");
//        } else if (qName.equalsIgnoreCase("anotaciones")) {
//            anotaciones = new ArrayList<>();
//        } else if (qName.equalsIgnoreCase("anotacion")) {
//            anotPos = attributes.getValue("p").equals("true");
//        } else if (qName.equalsIgnoreCase("asistencia")) {
//            asistencia = new ArrayList<>();
//        } else if (qName.equalsIgnoreCase("fecha")) {
//            asiste = attributes.getValue("presente").equals("true");
//        } else if (qName.equalsIgnoreCase("notas")) {
//            notas = new ArrayList<>();
//        }
//    }
//
//    /**
//     * Recive el cierre de un nodo
//     *
//     * @param uri la direccion del nombre del nodo
//     * @param localName nombre local del nodo
//     * @param qName nombre completo del nodo, con prefijo
//     * @throws SAXException para errores posibles durante el parseo
//     */
//    @Override
//    public void endElement(String uri, String localName, String qName)
//            throws SAXException {
//        if (qName.equalsIgnoreCase("tipo")) {
//            actvTipo = temp;
//        } else if (qName.equalsIgnoreCase("descripcion")) {
//            actvDesc = temp;
//        } else if (qName.equalsIgnoreCase("fechaA")) {
//            actvFecha = temp;
//        } else if (qName.equalsIgnoreCase("evaluado")) {
//            actvEv = "true".equals(temp);
//        } else if (qName.equalsIgnoreCase("actividad")) {
//            try {
//                ramo.planificacion.add(new Actividad(actvNombre, actvTipo, actvDesc, actvFecha, actvEv));
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//        } else if (qName.equalsIgnoreCase("ramo")) {
//            curso.ramos.add(ramo);
//        } else if (qName.equalsIgnoreCase("nombre")) {
//            alumNombre = temp;
//        } else if (qName.equalsIgnoreCase("rut")) {
//            alumRut = temp;
//        } else if (qName.equalsIgnoreCase("ingreso")) {
//            alumIngreso = Integer.parseInt(temp);
//        } else if (qName.equalsIgnoreCase("anotacion")) {
//            anotaciones.add(new Anotacion(temp, anotPos));
//        } else if (qName.equalsIgnoreCase("anotaciones")) {
//            anotLista = true;
//        } else if (qName.equalsIgnoreCase("fecha")) {
//            try {
//                asistencia.add(new Asistencia(temp, asiste));
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//        } else if (qName.equalsIgnoreCase("nota")) {
//            notas.add(Float.parseFloat(temp));
//        } else if (qName.equalsIgnoreCase("alumno")) {
//            Alumno al;
//            boolean esta = false;
//            int i;
//            for (i = 0; i < Principal.apoderados.size(); i++) {
//                if (Principal.apoderados.get(i).getRut().equals(apodRut)) {
//                    esta = true;
//                    break;
//                }
//            }
//            al = esta ? new Alumno(alumNombre, alumRut, alumIngreso,
//                    Principal.apoderados.get(i)) : new Alumno(alumNombre, alumRut,
//                    alumIngreso, apodNombre, apodRut);
//            if (!esta) {
//                Principal.apoderados.add(al.apoderado);
//            }
//            al.curso = this.curso;
//            if (this.anotLista) {
//                al.registro = this.asistencia;
//            }
//            al.anotaciones = this.anotaciones;
//            al.notas = this.notas;
//            curso.alumnos.add(al);
//        } else if (qName.equalsIgnoreCase("curso")) {
//            curso.colegio = Principal.colegio;
//            Principal.colegio.cursos.add(curso);
//        }
//    }
}
