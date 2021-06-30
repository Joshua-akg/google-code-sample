package com.google;

import java.util.Objects;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private boolean alreadyPlaying = false;
  private boolean paused = false;
  private Video videoPlaying = null;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("showAllVideos needs implementation");
  }

  public void playVideo(String videoId) {
    Video toBePlayed = videoLibrary.getVideo(videoId);
    if (Objects.isNull(toBePlayed)) {
      System.out.println("Cannot play video: Video does not exist");
    } 
      else {
        if (alreadyPlaying) {
          System.out.println("Stopping video: "+videoPlaying.getTitle());
        } else {
          alreadyPlaying = true;
        }
        System.out.println("Playing video: "+toBePlayed.getTitle());
        videoPlaying = toBePlayed;
        paused = false;
      }
  }

  public void stopVideo() {
    if (alreadyPlaying) {
      System.out.println("Stopping video: "+videoPlaying.getTitle());
      alreadyPlaying = false;
      videoPlaying = null;
    } else {
      System.out.println("Cannot stop video: No video is currently playing");
    }    
  }

  public void playRandomVideo() {
    System.out.println("playRandomVideo needs implementation");
  }

  public void pauseVideo() {
    if (!alreadyPlaying) {
      System.out.println("Cannot pause video: No video is currently playing");      
    } else
        if (paused) {
          System.out.println("Video already paused: "+videoPlaying.getTitle());
        } else {
          System.out.println("Pausing video: "+videoPlaying.getTitle());
          paused = true;
        }
  }

  public void continueVideo() {
    if (!alreadyPlaying) {
      System.out.println("Cannot continue video: No video is currently playing");
    } else {
      if (!paused) {
        System.out.println("Cannot continue video: Video is not paused");
      } else {
        System.out.println("Continuing video: "+videoPlaying.getTitle());
        paused = false;
      }
    }
  }

  public void showPlaying() {
    if (!alreadyPlaying) {
      System.out.println("No video is currently playing");
    } else {
      if (!paused) {
        System.out.printf("Currently playing: %s (%s) [%s] %n", videoPlaying.getTitle(), videoPlaying.getVideoId(), String.join(" ", videoPlaying.getTags()));
      } else {
        System.out.printf("Currently playing: %s (%s) [%s] - PAUSED%n", videoPlaying.getTitle(), videoPlaying.getVideoId(), String.join(" ", videoPlaying.getTags()));
      }
    }
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}