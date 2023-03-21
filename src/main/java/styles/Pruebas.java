/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package styles;

import com.roberto_rw.entidades.Cita;
import org.example.fachadas.ILogicaNegocio;
import org.example.fachadas.LogicaNegocio;

/**
 *
 * @author jegav
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ILogicaNegocio logicaNegocio = new LogicaNegocio();

        Cita cita = logicaNegocio.obtenerCita(12L);
        cita.setEmpleado(logicaNegocio.obtenerEmpleado(3L));
        logicaNegocio.actualizarCita(cita);
    }
    
}
