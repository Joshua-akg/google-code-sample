package com.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private boolean alreadyPlaying = false;
  private boolean paused = false;
  private Video videoPlaying = null;
  private HashMap<String, String> playlists;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    List<Video> allVideos = videoLibrary.getVideos();
    allVideos.sort(new VideoSorter());

    System.out.println("Here's a list of all available videos:");

    for (Video video : allVideos) {
      System.out.printf("%s (%s) [%s]%n", video.getTitle(), video.getVideoId(), String.join(" ", video.getTags()));      
    }
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
    if (alreadyPlaying) {
      System.out.println("Stopping video: "+videoPlaying.getTitle());
    }
    List<Video> allVideos = videoLibrary.getVideos();
    System.out.println("Playing video: "+allVideos.get((int)Math.random()*allVideos.size()).getTitle());
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
    // if (!Objects.isNull(playlists.values()) && playlists.values().contains(playlistName.toLowerCase())) {
    //   System.out.println("Cannot create playlist: A playlist with the same name already exists");
    // } else {
    //   System.out.println("Successfully created new playlist: "+playlistName);
    //   playlists.put(playlistName, playlistName.toLowerCase());
    // }
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
    List<Video> allVideos = videoLibrary.getVideos();
    List<Video> containsList = new ArrayList<>();
    HashMap<Integer,Video> containsMap = new HashMap<>();

    for (Video video : allVideos) {
      if (video.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
        containsList.add(video);
      }      
    }

    if (containsList.isEmpty()) {
      System.out.println("No search results for "+searchTerm);
      return;
    }
    containsList.sort(new VideoSorter());

    System.out.printf("Here are the results for %s:%n",searchTerm);

    for (int i = 0; i < containsList.size(); i++) {
      Video current = containsList.get(i);
      System.out.printf("%d) %s (%s) [%s] %n",current.getTitle(),
                                              current.getVideoId(),
                                              String.join(" ", current.getTags()));
      containsMap.put(i, containsList.get(i));      
    }
    System.out.printf("Would you like to play any of the above? If yes, specify the number of the video.%nIf your answer is not a valid number, we will assume it's a no.");

    Scanner input = new Scanner(System.in);
    String choice = input.nextLine();
    System.out.println("The choice is"+choice);
    input.close();

    // if (!Objects.isNull(containsMap.get(choice))) {
    //   this.playVideo(containsMap.get(choice).getVideoId());      
    // }
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