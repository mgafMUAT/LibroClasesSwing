package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import orm.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 * Clase creada para guardar / transformar los datos almacenados en documentos
 * varios de texto.
 *
 * @author Mauricio Acencio
 * @see Poblador
 */
public class Transformador {

    private static final int[] CODE = {108, 114, 98, 103, 102, 108, 114, 105, 107, 103, 98};

    /**
     * Guarda el registro actual del libro en archivos xml y json.
     *
     * @throws Exception Errores posibles de m\u00e9todoa implementados
     */
    public static void guardar() throws Exception {
        String nl = System.getProperty("line.separator");
        Institucion col = Principal.colegio;
        String nombre = col.getNombre();
        BufferedWriter gxml = new BufferedWriter(new FileWriter("data\\" + nombre + ".xml"));
        gxml.write("<?xml version=\"1.0\"?>" + nl);
        gxml.write("<registro>" + nl);
        gxml.write("    <verificar>" + codigo() + "</verificar>" + nl);
        Persona perdir = col.directivo.toArray()[0].getPersona_id_fk();
        gxml.write("    <colegio nombre=\"" + col.getNombre() + "\" direccion=\""
                + perdir.getNombre() + "\" rut=\"" + perdir.getRut() + ">" + nl);
        for (Curso cur : col.curso.toArray()) {
            gxml.write("        <curso nivel=\"" + cur.getNivel() + "\" letra=\""
                    + cur.getLetra() + "\">" + nl);
            ArrayList<Estudiante> almns = new ArrayList<>();
            Curso_estudiante[] ces = cur.curso_estudiante.toArray();
            for (Curso_estudiante ce : ces) {
                if (ce.getCurso_id_fk().equals(cur)) {
                    almns.add(ce.getEstudiante_id_fk());
                }
            }
            for (Estudiante al : almns) {
                gxml.write("            <alumno>" + nl);
                Persona ap = al.getApoderado_id_fk().getPersona_id_fk();
                gxml.write("                <apoderado nombre=\"" + ap.getNombre()
                        + "\" rut=\"" + ap.getRut() + "\"/>" + nl);
                gxml.write("                <nombre>" + al.getPersona_id_fk().getNombre() + "</nombre>"
                        + nl);
                gxml.write("                <rut>" + al.getPersona_id_fk().getRut() + "</rut>" + nl);
                gxml.write("                <ingreso>" + al.getAgnoIngreso()
                        + "</ingreso>" + nl);
                gxml.write("                <anotaciones>" + nl);
                for (Anotaciones an : al.anotaciones.toArray()) {
                    gxml.write("                    <anotacion p=\""
                            + an.getEsPositiva() + "\">" + an.getObservacion()
                            + "</anotacion>" + nl);
                }
                gxml.write("                </anotaciones>" + nl);
                gxml.write("                <asistencia>" + nl);
                for (Asistencia reg : al.asistencia.toArray()) {
                    gxml.write("                    <fecha presente=\""
                            + reg.getPresente() + "\">" + reg.getFecha()
                            + "</fecha>" + nl);
                }
                gxml.write("                </asistencia>" + nl);
                gxml.write("            </alumno>" + nl);
            }
            for (Asignatura ramo : cur.asignatura.toArray()) {
                Profesor p = ramo.getProfesorid_pk();
                gxml.write("            <profesor nombre=\"" + p.getPersona_id_fk().getNombre()
                        + "\" rut=\"" + p.getPersona_id_fk().getRut() + "\"/>" + nl);
                gxml.write("            <ramo nombre=\"" + ramo.getNombre()
                        + "\">" + nl);
                for (Actividad actv : ramo.actividad.toArray()) {
                    gxml.write("                <actividad nombre=\""
                            + actv.getNombre() + "\">" + nl);
                    gxml.write("                    <tipo>" + actv.getTipo()
                            + "</tipo>" + nl);
                    gxml.write("                    <descripcion>"
                            + actv.getDescripcion() + "</descripcion>" + nl);
                    for (Nota n : actv.nota.toArray()) {
                        gxml.write("                    <nota al=\"" +
                                n.getEstudiante_id_fk().getMatricula() + "\">"
                                + n.getNota() + "</nota>" + nl);
                    }
                    gxml.write("                </actividad>" + nl);
                }
                gxml.write("            </ramo>" + nl);
            }
            gxml.write("        </curso>" + nl);
        }
        gxml.write("    </colegio>" + nl);
        gxml.write("</registro>");
        gxml.close();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(new StreamSource("data\\"
                + "style\\xml2json.xslt"));
        transformer.transform(new StreamSource("data\\" + nombre + ".xml"),
                new StreamResult("data\\" + nombre + ".json"));
    }

    /**
     * Genera un informe del tipo especificado a partir del registro actual a
     * varios formatos.
     *
     * @param n Nº que representa el tipo de informe a generar: 1 = informe para
     * apoderados, 2 = informe sobre apoderados con m\u00e1s de un pupilo
     * @throws Exception Errores posibles de m\u00e9todoa implementados
     */
    public static void generarInforme(int n) throws Exception {
        String nl = System.getProperty("line.separator");
        String name = "data\\informe" + n;
        BufferedWriter gxml = new BufferedWriter(new FileWriter(name + ".xml"));
        Institucion col = Principal.colegio;
        ArrayList<Apoderado> aps = Principal.apoderados;
        gxml.write("<?xml version=\"1.0\"?>" + nl);
        gxml.write("<informe>" + nl);
        if (n == 1) {
            for (Apoderado ap : aps) {
                gxml.write("    <apoderado nombre=\"" + ap.getPersona_id_fk().getNombre() + "\" rut"
                        + "=\"" + ap.getPersona_id_fk().getRut() + "\">" + nl);
                for (Estudiante al : ap.estudiante.toArray()) {
                    gxml.write("        <alumno nombre=\"" + al.getPersona_id_fk().getNombre() + "\">"
                            + nl);
                    ArrayList<Asignatura> asigs = new ArrayList<>();
                    Curso_estudiante[] ces = al.curso_estudiante.toArray();
                    for (Curso_estudiante ce : ces) {
                        asigs.addAll(Arrays.asList(ce.getCurso_id_fk().asignatura.toArray()));
                    }
                    for (Asignatura ramo : asigs) {
                        for (Actividad actv : ramo.actividad.toArray()) {
                            gxml.write("                    <actividad nombre=\""
                                    + actv.getNombre() + "\">" + nl);
                            gxml.write("                        <ramo>" + ramo.getNombre()
                                    + "</ramo>" + nl);
                            gxml.write("                        <tipo>" + actv.getTipo()
                                    + "</tipo>" + nl);
                            gxml.write("                        <descripcion>" + nl);
                            gxml.write("                            " + actv.getDescripcion() + nl);
                            gxml.write("                        </descripcion>" + nl);
                            gxml.write("                        <evaluado>"
                                    + (!actv.nota.isEmpty() ? "si" : "no") + "</evaluado>" + nl);
                            gxml.write("                    </actividad>" + nl);
                        }
                    }
                    String prom = null;
                    if (al.nota != null) {
                        gxml.write("            <notas>" + nl);
                        float sum = 0;
                        for (Nota nota : al.nota.toArray()) {
                            gxml.write("                <nota>" + nota.getNota() + "</nota>" + nl);
                            sum += nota.getNota();
                        }
                        prom = String.format("%.1f", sum / al.nota.size());
                        boolean apr = prom.compareTo("4.0") < 0;
                        gxml.write("                <promedio aprueba=\"" + (apr
                                ? "si" : "no") + "\">" + prom + "</promedio>" + nl);
                        gxml.write("            </notas>" + nl);
                    }
                    for (Anotaciones anot : al.anotaciones.toArray()) {
                        gxml.write("                <anotacion>" + nl);
                        gxml.write("                    " + anot.getObservacion() + nl);
                        gxml.write("                </anotacion>" + nl);
                    }
                    if (!al.asistencia.isEmpty()) {
                        gxml.write("            <asistencia>" + nl);
                        int pres = 0;
                        for (Asistencia reg : al.asistencia.toArray()) {
                            gxml.write("                    <fecha presente=\""
                                    + (reg.getPresente() ? "presente" : "ausente")
                                    + "\">" + reg.getFecha()
                                    + "</fecha>" + nl);
                            if (reg.getPresente()) {
                                pres++;
                            }
                        }
                        prom = String.format("%.0f", (float) pres * 100 / al.asistencia.size());
                        gxml.write("                <porcentaje>" + prom
                                + "%</porcentaje>" + nl);
                        gxml.write("            </asistencia>" + nl);
                    }
                    gxml.write("        </alumno>" + nl);
                }
                gxml.write("    </apoderado>" + nl);
            }
        } else if (n == 2) {
            for (Apoderado ap : aps) {
                if (ap.estudiante.size() > 1) {
                    gxml.write("    <apoderado nombre=\"" + ap.getPersona_id_fk().getNombre() + "\" rut"
                            + "=\"" + ap.getPersona_id_fk().getRut() + "\"/>" + nl);
                    for (Estudiante al : ap.estudiante.toArray()) {
                        gxml.write("        <alumno>" + al.getPersona_id_fk().getNombre()
                                + "</alumno>" + nl);
                    }
                    gxml.write("    </apoderado>" + nl);
                }
            }
        }
        gxml.write("</informe>" + nl);
        gxml.close();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(new StreamSource("data\\"
                + "style\\xml2json.xslt"));
        transformer.transform(new StreamSource(name + ".xml"), new StreamResult(
                name + ".json"));
        tf = TransformerFactory.newInstance();
        transformer = tf.newTransformer(new StreamSource("data\\"
                + "style\\informe" + n + "html.xsl"));
        transformer.transform(new StreamSource(name + ".xml"), new StreamResult(
                name + ".html"));
        tf = TransformerFactory.newInstance();
        transformer = tf.newTransformer(new StreamSource("data\\"
                + "style\\informe" + n + "doc.xsl"));
        transformer.transform(new StreamSource(name + ".xml"), new StreamResult(
                name + ".doc"));
        tf = TransformerFactory.newInstance();
        transformer = tf.newTransformer(new StreamSource("data\\"
                + "style\\informe" + n + "xls.xsl"));
        transformer.transform(new StreamSource(name + ".xml"), new StreamResult(
                name + ".xls"));
    }

    public static boolean verificar(java.io.File file) {
        DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docB = docBF.newDocumentBuilder();
            Document doc = docB.parse(file);
            doc.getDocumentElement().normalize();
            String text = doc.getElementsByTagName("verificar").item(0).getTextContent();
            return text.equals(codigo());
        } catch (Exception e) {
            return false;
        }
    }

    public static String codigo() {
        StringBuilder sb = new StringBuilder(11);
        for (int i : CODE) {
            sb.append((char) i);
        }
        return sb.toString();
    }
}
