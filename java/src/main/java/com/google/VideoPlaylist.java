package com.google;

import java.util.ArrayList;

/** A class used to represent a Playlist */
class VideoPlaylist {

  private String playlistId;
  private String title;
  private ArrayList<Video> videos;

  VideoPlaylist(String title) {
    this.title = title;
    this.playlistId = title.toLowerCase();
    this.videos = new ArrayList<>();
  }

  /** Returns the name of the playlist. */
  String getTitle() {
    return title;
  }

  /** Returns the playlist id of the playlist. */
  String getPlaylistId() {
    return playlistId;
  }

  /** Returns a collection of the videos of the playlist. */
  ArrayList<Video> getVideos() {
    return videos;
  }

  boolean addVideoToPlaylist (Video video) {
      if (videos.contains(video)) {
          return false;
      } else {
          videos.add(video);
          return true;
      }
  }

  boolean removeVideo (Video video) {
    if (!videos.contains(video)) {
        return false;
    } else {
        videos.remove(video);
        return true;
    }
}

  void clearPlaylist() {
      this.videos = new ArrayList<>();
  }

}
