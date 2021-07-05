package com.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.HashMap;

public class PlaylistLibrary {
    private HashMap<String, VideoPlaylist> playlists;

    PlaylistLibrary() {
        this.playlists = new HashMap<>();
    }

    VideoPlaylist getPlaylist(String playlistName) {
        return this.playlists.get(playlistName.toLowerCase());
    }

    boolean contains(String playlistName) {
        try {          
            if (playlists.containsKey(playlistName.toLowerCase()))           // if (Objects.isNull(this.playlists.get(playlistName.toLowerCase())))
                return true;
            else 
                return false;           
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Root of the problem :(");
            return false;
        }
    }

    int getPlaylistNumber() {
        return this.playlists.size();
    }

    void addPlaylist(String playlistName) {
        playlists.put(playlistName.toLowerCase(), new VideoPlaylist(playlistName));
    }

    void removeFromPlaylist(VideoPlaylist playlist) {
        this.playlists.remove(playlist.getPlaylistId(), playlist);
    }

    boolean remove(String playlistName) {
        if (this.contains(playlistName)) {
            this.playlists.remove(playlistName.toLowerCase());
            return true;
        } else {
            return false;
        }
    }

    List<VideoPlaylist> allPlaylists() {
        return (List<VideoPlaylist>)playlists.values();
    }
    
}