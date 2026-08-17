# AudioPlayer

### Descripción del Problema:
Tienes un reproductor de audio (`AudioPlayer`) que debe soportar formatos de dos proveedores externos, cada uno con su propia interfaz — incompatibles entre sí y **no modificables**:

* `AdvancedAudioPlayer`: reproduce MP4 y VLC mediante `playMp4(fileName)` / `playVlc(fileName)` / `stop()`.
* `PremiumAudioPlayer`: reproduce cualquier códec mediante un único método `playAudio(codec, path, volumePercent)` / `halt()`, y siempre exige un volumen explícito (0-100).

Necesitas que el resto de la aplicación pueda reproducir archivos de **cualquiera** de los dos proveedores (tipos `"mp4"`, `"vlc"`, `"flac"`, `"aac"`) usando siempre la misma interfaz `AudioPlayer`, sin que `Client` conozca `AdvancedAudioPlayer` ni `PremiumAudioPlayer` directamente.

### Solución Propuesta:
Adaptador(es) que permitan que la interfaz `AudioPlayer` utilice `AdvancedAudioPlayer` y `PremiumAudioPlayer` como si fueran un reproductor normal. Usted decide si le conviene un adaptador por proveedor o uno que combine ambos internamente — justifique la decisión.

Para los tipos que resuelva con `PremiumAudioPlayer`, `AudioPlayer.play(audioType, fileName)` no recibe volumen: la solución debe definir y documentar un volumen por defecto razonable.

### Estructura del Código:

El código consta de las siguientes clases:

#### `AudioPlayer` (Interfaz):
Define la interfaz básica para un reproductor de audio: `play(audioType, fileName)` / `stop()`.

#### `AdvancedAudioPlayer` (Clase, de terceros):
Reproduce MP4 y VLC con su propia API.

#### `PremiumAudioPlayer` (Clase, de terceros):
Reproduce cualquier códec (incluye `"flac"` y `"aac"`) con su propia API, exigiendo volumen en cada llamada.

#### `Client` (Clase):
Punto de entrada de la aplicación.

### Resultado Esperado:
`Client` debe poder reproducir archivos `"mp4"`, `"vlc"`, `"flac"` y `"aac"` usando exclusivamente `AudioPlayer.play(audioType, fileName)` / `AudioPlayer.stop()`, sin referenciar `AdvancedAudioPlayer` ni `PremiumAudioPlayer` directamente en ningún punto.

Recuerde los comandos para la ejecución del programa

Para compilar

```bash
mvn compile
```

Para ejecutar la aplicación:

```bash
mvn exec:java -Dexec.mainClass=edu.unisabana.dyas.patterns.Client
```

### Criterios de evaluación

* Diseño.
	1. `AudioPlayer`, `AdvancedAudioPlayer` y `PremiumAudioPlayer` no deben modificarse.
	2. `Client` debe depender únicamente de la interfaz `AudioPlayer`, sin referenciar directamente `AdvancedAudioPlayer` ni `PremiumAudioPlayer`.
	3. Debe ser posible agregar un tercer proveedor en el futuro sin modificar `Client` ni los adaptadores existentes.
* Funcionalidad.
	1. `play("mp4", ...)` y `play("vlc", ...)` delegan correctamente en `AdvancedAudioPlayer`.
	2. `play("flac", ...)` y `play("aac", ...)` delegan correctamente en `PremiumAudioPlayer`, usando un volumen por defecto documentado.
	3. `stop()` detiene correctamente el reproductor subyacente que esté activo (`playMp4`/`playVlc` → `stop()`; `flac`/`aac` → `halt()`).



## Decisión de diseño

Se implementó un adaptador por proveedor: `AdvancedAudioPlayerAdapter` y `PremiumAudioPlayerAdapter`.

Esta decisión separa la lógica de integración de cada API externa. Si se agrega un nuevo proveedor, solo debemos crear otro adaptador que implemente `AudioPlayer`, sin modificar los adaptadores existentes ni las clases de terceros.
