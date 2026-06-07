package systems.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MusicPlayer {

    private final ArrayList<String> playlist = new ArrayList<>();
    private final ArrayList<String> filenames = new ArrayList<>();
    private int currentIndex = -1;
    private String currentSong = null;
    private boolean isPlaying = false;
    private Clip clip = null;

    public MusicPlayer() {
        playlist.add("Pamungkas - To The Bone");
        playlist.add("Rex Orange County - Best Friend");
        playlist.add("Backstreet Boys - Shape Of My Heart");
        playlist.add("Taylor Swift - You Belong with Me");
        playlist.add("Gloria Gaynor - I Will Survive");

        filenames.add("Pamungkas - To The Bone (Official Music Video) (1).wav");
        filenames.add("Rex Orange County - Best Friend (Official Audio) (1).wav");
        filenames.add("YTDown_YouTube_Backstreet-Boys-Shape-Of-My-Heart-Offici_Media_OT5msu-dap8_009_128k (1).wav");
        filenames.add("Taylor Swift - You Belong With Me (1).wav");
        filenames.add("Gloria Gaynor - I Will Survive (Lyrics) (1).wav");
    }

    public ArrayList<String> getPlaylist() {
        return playlist;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int size() {
        return playlist.size();
    }

    public boolean play(int index) {
        if (index < 1 || index > playlist.size()) return false;
        stop();
        currentIndex = index - 1;
        currentSong = playlist.get(currentIndex);
        return startClip(filenames.get(currentIndex));
    }

    public boolean shuffle() {
        if (playlist.isEmpty()) return false;
        stop();
        ArrayList<Integer> idx = new ArrayList<>();
        for (int i = 0; i < playlist.size(); i++) idx.add(i);
        Collections.shuffle(idx);
        ArrayList<String> newTitles = new ArrayList<>();
        ArrayList<String> newFiles = new ArrayList<>();
        for (int i : idx) {
            newTitles.add(playlist.get(i));
            newFiles.add(filenames.get(i));
        }
        playlist.clear();
        filenames.clear();
        playlist.addAll(newTitles);
        filenames.addAll(newFiles);
        currentIndex = 0;
        currentSong = playlist.get(0);
        return startClip(filenames.get(0));
    }

    public void stop() {
        try {
            if (clip != null && clip.isOpen()) {
                clip.stop();
                clip.close();
            }
        } catch (Exception ignored) {
        }
        clip = null;
        isPlaying = false;
        currentSong = null;
        currentIndex = -1;
    }

    private boolean startClip(String filename) {
        try {
            File f = new File("src/systems/music/songs/" + filename);
            if (!f.exists()) {
                isPlaying = false;
                return false;
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;
            return true;
        } catch (Exception e) {
            System.err.println("[MusicPlayer] Gagal play: " + e.getMessage());
            isPlaying = false;
            return false;
        }
    }
}
