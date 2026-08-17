package edu.unisabana.dyas.patterns.adapter;

import edu.unisabana.dyas.patterns.utils.AdvancedAudioPlayer;
import edu.unisabana.dyas.patterns.utils.AudioPlayer;

// Adaptador que traduce la interfaz AudioPlayer a la API de AdvancedAudioPlayer.
public class AdvancedAudioPlayerAdapter implements AudioPlayer {

    private final AdvancedAudioPlayer advancedPlayer = new AdvancedAudioPlayer();

    @Override
    public void play(String audiotype, String filename) {

        if (audiotype.equalsIgnoreCase("mp4")) {

            advancedPlayer.playMp4(filename);

        } else if (audiotype.equalsIgnoreCase("vlc")) {

            advancedPlayer.playVlc(filename);

        }
    }

    @Override
    public void stop() {
        advancedPlayer.stop();
    }
}
