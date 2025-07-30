package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /** === Vistas === */
    private TextView tvDisplay;

    /** === Arrays de IDs === */
    private int[] numberButtons;
    private int[] operationButtons;

    /** === Variables === */
    private final StringBuilder currentInput = new StringBuilder();   // Entrada actual por pantalla
    private double firstOperand = 0;                            // Primer operando
    private String currentOperation = "";                       // Operación actual (+, -, x, ÷)
    private boolean isNewOperation = true;                      // Bandera: iniciar nueva operación

    /** === Interfaz que define el method de las operaciones matemáticas === */
    @FunctionalInterface
    interface Operation {
        double apply(double a, double b);
    }

    /** === Mapa de operaciones (clave-valor) === */
    @SuppressWarnings("Convert2MethodRef")
    private final Map<String, Operation> operationsX = new HashMap<>() {{
        put("+", (a, b) -> a + b);
        put("-", (a, b) -> a - b);
        put("x", (a, b) -> a * b);
        put("÷", (a, b) -> (b == 0) ? Double.NaN : a / b);
    }};

    /** === Variable del Splash === */
    private long splashStartTime;

    @SuppressWarnings("Convert2MethodRef")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Instala el SplashScreen API antes del onCreate
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        // Guardar tiempo de inicio
        splashStartTime = System.currentTimeMillis();

        // Mantener Splash al menos 2 segundos
        splashScreen.setKeepOnScreenCondition(() ->
                (System.currentTimeMillis() - splashStartTime) < 2000
        );

        // Animación fade out del splash (logo desaparece lentanmente al iniciar la app)
        splashScreen.setOnExitAnimationListener(splashScreenView ->
                splashScreenView.getView().animate()
                .alpha(0f)
                .setDuration(500L)
                .withEndAction(() -> splashScreenView.remove())
                .start());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listeners();
    }

    /** === Inicialización === */
    private void init() {
        // Pantalla calculadora
        tvDisplay = findViewById(R.id.tv_pantalla);

        // Botones numéricos y punto
        numberButtons = new int[]{
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_punto
        };

        // Botones de operación
        operationButtons = new int[]{
                R.id.btn_sum, R.id.btn_res, R.id.btn_mul, R.id.btn_div
        };
    }

    /** === Escucha de eventos === */
    private void listeners() {
        // Botones numéricos y punto
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        // Botones de operación
        for (int id : operationButtons) {
            findViewById(id).setOnClickListener(this::onOperationClick);
        }

        // Botón "="
        findViewById(R.id.btn_igual).setOnClickListener(this::onEqualClick);

        // Botón "C" (limpiar)
        findViewById(R.id.btn_clear).setOnClickListener(v -> resetCalculator());
    }

    /** === Maneja la pulsación de los botones numéricos y del punto === */
    private void onNumberClick(View view) {
        Button btn = (Button) view;
        String value = btn.getText().toString();

        if (isNewOperation && currentOperation.isEmpty()) {
            // Reinicia solo si no hay operación pendiente
            currentInput.setLength(0);
        }
        isNewOperation = false;

        // Evita que se ingresen múltiples puntos
        if (value.equals(".") && currentInput.toString().contains(".")) return;

        currentInput.append(value);
        tvDisplay.setText(currentInput.toString());
    }

    /** === Guarda la operación seleccionada y el primer operando === */
    private void onOperationClick(View view) {
        Button btn = (Button) view;
        String operation = btn.getText().toString();

        if (currentInput.length() > 0) {
            firstOperand = Double.parseDouble(currentInput.toString());
        }
        currentOperation = operation;
        currentInput.setLength(0);  //Limpia entrada para el segundo número
        tvDisplay.setText(formatResult(firstOperand));  // Muestra primer operando
        isNewOperation = true;      // Preparar para el segundo número
    }

    /** === Calcula el resultado cuando se presiona "=" === */
    @SuppressLint("SetTextI18n")
    private void onEqualClick(View view) {
        if (currentOperation.isEmpty() || currentInput.length() == 0) return;

        double secondOperand = Double.parseDouble(currentInput.toString());
        // Obtener la operación del mapa. Ej. (a, b) -> a + b
        Operation op = operationsX.get(currentOperation);

        if (op != null) {
            double result = op.apply(firstOperand, secondOperand);

            // Validar división por cero (Double.NaN)
            if (Double.isNaN(result)) {
                tvDisplay.setText("Error");
            } else {
                tvDisplay.setText(formatResult(result));
            }
            resetAfterResult(result);
        } else {
            tvDisplay.setText("Error");
        }
    }

    /** === Después del cálculo, prepara la calculadora para continuar operando con el resultado === */
    private void resetAfterResult(double result) {
        firstOperand = result;
        currentInput.setLength(0);
        currentOperation = "";
        isNewOperation = true;
    }

    /** === Restablece la calculadora al estado inicial === */
    private void resetCalculator() {
        tvDisplay.setText("0");
        currentInput.setLength(0);
        firstOperand = 0;
        currentOperation = "";
        isNewOperation = true;
    }

    /** === Formatea el resultado: muestra un punto final si es entero === */
    private String formatResult(double value) {
        if (value == (long) value) { // 15.0 == 15 -> true (entero)
            return String.format(Locale.US, "%d.", (long) value); // 15.
        } else { // 15.75 (decimal)
            return String.format(Locale.US, "%s", value); // 15.75
        }
    }
}
