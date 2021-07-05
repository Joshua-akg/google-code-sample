package com.google;
import java.util.Comparator;

public class PlaylistSorter implements Comparator<VideoPlaylist> {
    @Override
    public int compare(VideoPlaylist p1, VideoPlaylist p2) {
        return p1.getTitle().compareToIgnoreCase(p2.getTitle());
    }
}