package edu.unisabana.dyas.patterns.adapter;

import edu.unisabana.dyas.patterns.utils.AudioPlayer;
import edu.unisabana.dyas.patterns.utils.PremiumAudioPlayer;

// Adaptador que traduce la interfaz AudioPlayer a la API de PremiumAudioPlayer.
public class PremiumAudioPlayerAdapter implements AudioPlayer {

    private static final int DEFAULT_VOLUME = 80;

    private final PremiumAudioPlayer premiumPlayer = new PremiumAudioPlayer();

    @Override
    public void play(String audiotype, String filename) {
        premiumPlayer.playAudio(audiotype, filename, DEFAULT_VOLUME);
    }

    @Override
    public void stop() {
        premiumPlayer.halt();
    }
}
