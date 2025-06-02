# NewTeam3 - Pablo Zuil, Nicolas Ortega, Antoine Lopez y PabloDLF

Una aplicación de escritorio desarrollada en Java y Kotlin con JavaFX, orientada a gestionar interacciones en un entorno de tipo tótem multimedia. Este proyecto fue creado como parte de un desarrollo académico o colaborativo por el equipo NewTeam3.

## 🚀 Tecnologías utilizadas

- **Java 21**
- **Kotlin (JVM)**
- **JavaFX** (Controles, FXML)
- **Gradle**
- **JLink** (para distribución)
- **Jacoco** (para cobertura de tests)
- **Dokka** (para documentación)
- **JUnit 5** (para testing)

## 🛠️ Configuración y ejecución

### Requisitos

- JDK 21
- Gradle (o usar el wrapper incluido `./gradlew`)

### Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/New-Team3-dev-zuil.git
cd New-Team3-dev-zuil
```

### Correr el programa
```bash
./gradlew run
```
### Para ejecutar los test
```bash
./gradlew test
```

### Para generar la documentacion HTML de Dokka
```bash
./gradlew dokkaHtml

```

### Estructura del proyecto

├── build.gradle

├── settings.gradle

├── gradlew / gradlew.bat

├── src/

│   ├── main/

│   │   ├── java/        # Código fuente Java/Kotlin

│   │   ├── resources/   # Archivos FXML, imágenes, etc.

│   └── test/            # Tests unitarios

├── documentación/       # Documentación técnica (PDF)
