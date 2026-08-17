package edu.unisabana.dyas.patterns;

import edu.unisabana.dyas.patterns.adapter.AdvancedAudioPlayerAdapter;
import edu.unisabana.dyas.patterns.adapter.PremiumAudioPlayerAdapter;
import edu.unisabana.dyas.patterns.utils.AudioPlayer;

/*

## Decisión de diseño

Se implementó un adaptador por proveedor: `AdvancedAudioPlayerAdapter` y `PremiumAudioPlayerAdapter`.

Esta decisión separa la lógica de integración de cada API externa. Si se agrega un nuevo proveedor,
solo debemos crear otro adaptador que implemente `AudioPlayer`, sin modificar los adaptadores existentes
ni las clases de terceros.

 */

public class Client {
    public static void main(String[] args) {

        AudioPlayer advancedPlayer = new AdvancedAudioPlayerAdapter();
        advancedPlayer.play("mp4", "video.mp4");
        advancedPlayer.stop();

        advancedPlayer.play("vlc", "pelicula.vlc");
        advancedPlayer.stop();

        AudioPlayer premiumPlayer = new PremiumAudioPlayerAdapter();
        premiumPlayer.play("flac", "album.flac");
        premiumPlayer.stop();

        premiumPlayer.play("aac", "cancion.aac");
        premiumPlayer.stop();
    }
}