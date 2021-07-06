package com.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private PlaylistLibrary playlistLibrary;
  private boolean alreadyPlaying = false;
  private boolean paused = false;
  private Video videoPlaying = null;
  private HashMap<String, String> flagReasons;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.playlistLibrary = new PlaylistLibrary();
    this.flagReasons = new HashMap<>();
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
    try {
      if (!playlistLibrary.contains(playlistName)) {
          playlistLibrary.addPlaylist(playlistName);
          System.out.printf("Successfully created new playlist: %s%n",playlistName);
        } else {
          System.out.println("Cannot create playlist: A playlist with the same name already exists");
      }      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    if (!playlistLibrary.contains(playlistName)) { //playlist does not exist
      System.out.printf("Cannot add video to %s: Playlist does not exist",playlistName);
      if (!videoLibrary.contains(videoId)) { //and video does not exist
        System.out.printf("Cannot add video to %s: Video does not exist%n",playlistName);
      }
    } else { //playlist exists
      if (!videoLibrary.contains(videoId)) { //and video does not exist
        System.out.printf("Cannot add video to %s: Video does not exist%n",playlistName);
      } else { //Video exists
        if (playlistLibrary.getPlaylist(playlistName).addVideoToPlaylist(videoLibrary.getVideo(videoId))) {
          System.out.printf("Added video to %s: %s%n",playlistName,videoLibrary.getVideo(videoId).getTitle());
          //System.out.println("");
        } else {
          System.out.printf("Cannot add video to %s: Video already added%n",playlistName);
        }
      }
    }
  }

  public void showAllPlaylists() {
    if (playlistLibrary.getPlaylistNumber() == 0) {
      System.out.println("No playlists exist yet");
    } else {
      ArrayList<VideoPlaylist> allPlaylists = playlistLibrary.allPlaylists();
      allPlaylists.sort(new PlaylistSorter());

      System.out.println("Showing all playlists:");
      for (VideoPlaylist playlist : allPlaylists) {
        System.out.println(playlist.getTitle());        
      }
    }
  }

  public void showPlaylist(String playlistName) {
    if (!playlistLibrary.contains(playlistName)) {
      System.out.printf("Cannot show playlist %s: Playlist does not exist%n",playlistName);
    } else {
      ArrayList<Video> videos = playlistLibrary.getPlaylist(playlistName).getVideos();

      System.out.println("Showing playlist: "+playlistName);
      if (videos.isEmpty()) {
        System.out.println("No videos here yet");
      } else {
        for (Video video : videos) {
          System.out.printf("%s (%s) [%s] %n", video.getTitle(), video.getVideoId(), String.join(" ", video.getTags()));                    
        }
      }
    }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    if (!playlistLibrary.contains(playlistName)) { //playlist does not exist
      System.out.printf("Cannot remove video from %s: Playlist does not exist",playlistName);
      if (!videoLibrary.contains(videoId)) { //and video does not exist
        System.out.printf("Cannot remove video from %s: Video does not exist%n",playlistName);
      }
    } else { //playlist exists
      if (!videoLibrary.contains(videoId)) { //and video does not exist
        System.out.printf("Cannot remove video from %s: Video does not exist%n",playlistName);
      } else { //Video exists
        if (playlistLibrary.getPlaylist(playlistName).removeVideo(videoLibrary.getVideo(videoId))) {
          System.out.printf("Removed video from %s: %s%n",playlistName,videoLibrary.getVideo(videoId).getTitle());
        } else {
          System.out.printf("Cannot remove video from %s: Video is not in playlist%n",playlistName);
        }
      }
    }
  }

  public void clearPlaylist(String playlistName) {
    if (playlistLibrary.getPlaylist(playlistName) != null) {
      playlistLibrary.getPlaylist(playlistName).clearPlaylist();
      System.out.printf("Successfully removed all videos from %s%n",playlistName);
    } else {
      System.out.printf("Cannot clear playlist %s: Playlist does not exist%n",playlistName);
    }
  }

  public void deletePlaylist(String playlistName) {
    if (playlistLibrary.remove(playlistName)) {
      System.out.println("Deleted playlist: "+playlistName);
    } else {
      System.out.printf("Cannot delete playlist %s: Playlist does not exist%n",playlistName);
    }
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
      System.out.printf("%d) %s (%s) [%s] %n", i+1, current.getTitle(),
                                              current.getVideoId(),
                                              String.join(" ", current.getTags()));
      containsMap.put(i, containsList.get(i));      
    }
    System.out.printf("Would you like to play any of the above? If yes, specify the number of the video.%nIf your answer is not a valid number, we will assume it's a no.%n");

    Scanner input = new Scanner(System.in);
    String choice = input.nextLine();
    input.close();

    try {
      if (!Objects.isNull(containsMap.get(Integer.valueOf(choice)-1))) {
        this.playVideo(containsMap.get(Integer.valueOf(choice)-1).getVideoId());      
      }            
    } catch (Exception e) {
    }
  }

  public void searchVideosWithTag(String videoTag) {
    List<Video> allVideos = videoLibrary.getVideos();
    List<Video> containsList = new ArrayList<>();
    HashMap<Integer,Video> containsMap = new HashMap<>();

    for (Video video : allVideos) {
      if (video.getTags().contains(videoTag.toLowerCase())) {
        containsList.add(video);
      }      
    }

    if (containsList.isEmpty()) {
      System.out.println("No search results for "+videoTag);
      return;
    }
    containsList.sort(new VideoSorter());

    System.out.printf("Here are the results for %s:%n",videoTag);

    for (int i = 0; i < containsList.size(); i++) {
      Video current = containsList.get(i);
      System.out.printf("%d) %s (%s) [%s] %n", i+1, current.getTitle(),
                                              current.getVideoId(),
                                              String.join(" ", current.getTags()));
      containsMap.put(i, containsList.get(i));      
    }
    System.out.printf("Would you like to play any of the above? If yes, specify the number of the video.%nIf your answer is not a valid number, we will assume it's a no.%n");

    Scanner input = new Scanner(System.in);
    String choice = input.nextLine();
    input.close();

    try {
      if (!Objects.isNull(containsMap.get(Integer.valueOf(choice)-1))) {
        this.playVideo(containsMap.get(Integer.valueOf(choice)-1).getVideoId());      
      }            
    } catch (Exception e) {
    }
  }

  public void flagVideo(String videoId) {
    // System.out.println("flagVideo needs implementation");
    if (!videoLibrary.contains(videoId)) {
      System.out.printf("Cannot flag video: Video does not exist");
    }
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}