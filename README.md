<!--Título-->
<h1 align="center">Mi Calculadora - Aplicación Android</h1>

<!--Banner-->
<img src="https://i.imgur.com/zqQvdUl.png" alt="Mi Calculadora Banner">

## Descripción

>[!NOTE]
> **Mi Calculadora** es una aplicación Android desarrollada en Java que permite realizar operaciones matemáticas básicas de manera simple, rápida y con una interfaz amigable.
Incluye una pantalla Splash animada con la API oficial de Google y un diseño atractivo con gradientes en los botones.
>
>Funcionalidades principales:
>
> - Operaciones básicas: **suma, resta, multiplicación y división**.
> - Manejo de números decimales y validación de errores (ej. división por cero).
> - **Interfaz moderna** con colores personalizados y diseño intuitivo.
> - Splash Screen animado (fade out) implementado con (`androidx.core.splashscreen`).

## Capturas de Pantalla

>[!NOTE]
> Estas son algunas vistas representativas del funcionamiento de la aplicación.
> <br>
>
>### Interfaz Principal
>
>| Splash | Calculadora | Operación | Resultado |
>|--------|------------|-----------|-----------|
>| <img src="https://i.imgur.com/6VJ2YZt.png" width="170px"> | <img src="https://i.imgur.com/Q5jCbrP.png" width="170px"> | <img src="https://i.imgur.com/nJf6r0O.png" width="170px"> | <img src="https://i.imgur.com/2nXbr8F.png" width="170px"> |

## Características

>[!NOTE]
> - Operaciones básicas (+, -, ×, ÷).
> - Formato inteligente en resultados: muestra punto final si es entero.
> - Validación de errores: muestra Error en divisiones por cero.
> - Interfaz responsive: diseño adaptado a pantallas pequeñas y grandes.
> - Código optimizado: uso de interfaces funcionales, lambdas y HashMap para operaciones.
> - Compatibilidad: Android 7.0+ (API 24).

## Tecnologías Utilizadas

>[!NOTE]
> - Lenguaje: Java
> - Android SDK: 24+
> - Bibliotecas:
>   - `androidx.core:core-splashscreen` → Implementación oficial del Splash Screen API.
>   - `AppCompat` y `Material Components` para UI.

## Instalación

>[!TIP]
> 1. Clona el repositorio:
>    ```bash
>    git clone https://github.com/tu-usuario/micalculadora.git
>    ```
> 2. Abre el proyecto en Android Studio.
> 3. Sincroniza las dependencias en `build.gradle`.
> 4. Compila y ejecuta en un emulador o dispositivo Android.

## Uso

>[!TIP]
> 1. Ingresa los números deseados.
> 2. Selecciona una operación: +, -, ×, ÷.
> 3. Pulsa "=" para mostrar el resultado.
> 4. Pulsa C para reiniciar o limpiar pantalla.
> 5. Continúa operando con el resultado anterior.

## Gradle

>[!TIP]
> Asegúrate de agregar la siguiente dependencia:
>
> ```gradle
> implementation 'androidx.core:core-splashscreen:1.0.1'
> ```

## Contribución

>[!NOTE]
> Si deseas mejorar esta aplicación:
> 1. Crea un "Issue" con tu propuesta.
> 2. Realiza los cambios en una rama aparte.
> 3. Envía una Pull Request.

## Licencia

>[!IMPORTANT]
> Este proyecto está bajo la **Licencia Apache 2.0**.
>
> ```
> Copyright (c) 2025 JavCodeDev
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
> http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
> ```

## Contacto

>[!IMPORTANT]
> Si tienes preguntas o deseas más información:
>
> - Nombre: Javier Callally
> - Correo: jcallally@gmail.com
> - GitHub: https://github.com/jcallally
