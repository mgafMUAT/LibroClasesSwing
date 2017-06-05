/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm.app;

import javax.swing.JOptionPane;

/**
 * Clase creada para propósitos de mostrar mensajes emergentes
 *
 * @author MauricioAcencio
 */
public class Mensaje {

    /**
     * Muestra una ventana de información con un mensaje emergente
     *
     * @param msg El mensaje de la ventana
     */
    public static void info(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
