
# 🧥 Sistema de Gestión de Tienda de Ropa

Proyecto final para la asignatura **Gestión de Software** de primer año de Ingeniería Informática. Este sistema fue desarrollado en **Java 8** usando **NetBeans**, y permite a una tienda de ropa gestionar su inventario, registrar ventas y devoluciones, y realizar búsquedas por nombre o categoría. El sistema cuenta con una interfaz amigable y pruebas con **JUnit 4**.

---

## 🌟 Objetivo del proyecto

Desarrollar un sistema que le permita a una tienda de ropa gestionar su inventraio, realizar ventas y devoluciones y buscar artículos por nombre o categoría, aplicando conceptos de la Programación  Orientada a Objetos, Introducción a la Gestión del Software y principios DevOps.

---

## 🛠️ Funcionalidades principales

- 📦 **Gestión de inventario**
  - Agregar, editar y eliminar artículos
  - Atributos: ID de articulo, nombre, categoría, precio, cantidad en inventario

- 💰 **Registro de ventas y devoluciones**
  - Disminución/ajuste del inventario
  - Atributos: ID de venta, ID de devolucion, ID de articulo, cantidad vendida/devuelta, fecha, estado(isDevuelto)

- 🔍 **Búsqueda eficiente**
  - Por nombre completo 
  - Por categoría
  - Atributos: nombre, categoría

- 👁️ **Vista amigable**
  - Menú interactivo 
   
---

- ✅ **Pruebas unitarias**
  - Cobertura funcional de lógica de negocio con JUnit 4
  - Pruebas específicas para: ventas, devoluciones, búsqueda

---

## 💻 Tecnologías utilizadas

| Tecnología   | Descripción                                  |
|--------------|----------------------------------------------|
| Java 8       | Lenguaje de programación principal           |
| NetBeans     | Entorno de desarrollo (IDE)                  |
| JUnit 4      | Framework de pruebas unitarias               |

---

## 🧪 Ejecución de pruebas unitarias en NetBeans

Para verificar automáticamente el correcto funcionamiento del sistema:

1. Abre el proyecto en **NetBeans**.
2. Haz clic derecho sobre el nombre del proyecto.
3. Selecciona la opción `Test` o `Run Tests`.
4. Revisa los resultados en la ventana de salida del IDE.

---

## 🔄 Principios DevOps aplicados

Este proyecto integra prácticas fundamentales de DevOps para fomentar eficiencia, colaboración y calidad continua:

- 🔁 **Integración continua (CI):** Se integraron cambios con frecuencia y se probaron de inmediato.
- 🧪 **Pruebas automatizadas:** Implementadas con JUnit 4 para detectar errores rápidamente.
- 📋 **Documentación continua:** README con instrucciones claras sobre instalación, pruebas y uso.
- 🔧 **Iteración rápida:** Mejoras sucesivas aplicadas a la estructura del código.

---

## 🚀 Cómo ejecutar el sistema en NetBeans

1. Asegúrate de tener instalado **NetBeans** con soporte para **Java 8**.
2. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/Erika-RC/ProyectoGestSoft
