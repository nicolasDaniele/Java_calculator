package com.mycompany.calculadora;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
    }
}

class Ventana extends JFrame {

    public Ventana() {

        Calculadora calculadora = new Calculadora();

        setVisible(true);
        setSize(230, 315);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(calculadora);
    }

}

class Calculadora extends JPanel {

    private JTextField txtDisplay;

    private boolean primerNumero;
    private float resultado;
    private String ultimaOperacion = "=";

    public Calculadora() {

        setLayout(null);

        primerNumero = true;

        txtDisplay = new JTextField("0");
        txtDisplay.setBounds(10, 10, 195, 50);
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 20));
        txtDisplay.setEditable(false);
        add(txtDisplay);

        // Instancias de clases ActionListeners
        PresionaNumero pn = new PresionaNumero();
        PresionaOperador po = new PresionaOperador();

        agregarBoton("7", pn, 10, 70);
        agregarBoton("8", pn, 60, 70);
        agregarBoton("9", pn, 110, 70);
        agregarBoton("/", po, 160, 70);
        agregarBoton("4", pn, 10, 120);
        agregarBoton("5", pn, 60, 120);
        agregarBoton("6", pn, 110, 120);
        agregarBoton("*", po, 160, 120);
        agregarBoton("1", pn, 10, 170);
        agregarBoton("2", pn, 60, 170);
        agregarBoton("3", pn, 110, 170);
        agregarBoton("-", po, 160, 170);
        agregarBoton("0", pn, 10, 220);
        agregarBoton(".", pn, 60, 220);
        agregarBoton("=", po, 110, 220);
        agregarBoton("+", po, 160, 220);
    }

    // Método que crea los botones y los agrega al panel
    private void agregarBoton(String texto, ActionListener accion, int x, int y) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, 45, 45);
        btn.addActionListener(accion);
        add(btn);
    }

    // Cuando presionamos la tecla de un número ocurre esto:
    private class PresionaNumero implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String entrada = e.getActionCommand();

            if (primerNumero) {
                txtDisplay.setText("");
                primerNumero = false;
            }

            txtDisplay.setText(txtDisplay.getText() + entrada);
        }
    }

    // Cuando presionamos la tecla de un operador ocurre esto:
    private class PresionaOperador implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String operacion = e.getActionCommand();

            calcular(Float.parseFloat(txtDisplay.getText()));

            ultimaOperacion = operacion;

            primerNumero = true;
        }

    }

    // Método que realiza la operación correspondiente al operador que se haya presionado
    public void calcular(float x) {

        if (ultimaOperacion.equals("+")) {
            resultado += x;
        } else if (ultimaOperacion.equals("-")) {
            resultado -= x;
        } else if (ultimaOperacion.equals("*")) {
            resultado *= x;
        } else if (ultimaOperacion.equals("/")) {
            resultado /= x;
        } else if (ultimaOperacion.equals("=")) {
            resultado = x;
        }

        txtDisplay.setText("" + resultado);
    }
}
