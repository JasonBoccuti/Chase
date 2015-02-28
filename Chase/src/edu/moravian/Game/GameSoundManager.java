package edu.moravian.Game;

import java.util.ArrayList;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class GameSoundManager {
	
	private ArrayList<Music> playlist = new ArrayList<Music>();
	private Music chaLaHeadChaLa, rockTheDragon, dragonBall;
	private Music quickTips;
	private int playlistCount;
	private Boolean isPlaying;
	
	public GameSoundManager() throws SlickException {
		chaLaHeadChaLa = new Music("res/Cha-la-head-cha-la.wav");
	    rockTheDragon = new Music("res/RockTheDragon.wav");
	    dragonBall = new Music("res/DragonBall.wav");
	    quickTips = new Music("res/QuickTips.wav");
	    
	    playlist.add(chaLaHeadChaLa);
	    playlist.add(rockTheDragon);
	    playlist.add(dragonBall);
	    
	    playlistCount = -1;
	}
	
	public void playNextSong() {
		if (playlistCount >= 2) {
			playlistCount = -1;
		}
		playlistCount++;
		playlist.get(playlistCount).play();
	}
	
	public void pauseSong() {
		playlist.get(playlistCount).pause();
	}
	
	public void resumeSong() {
		playlist.get(playlistCount).resume();
	}
	
	public void playSound() {
		quickTips.play();
	}
	
	public Boolean isPlaying() {
		if(playlist.get(playlistCount).playing()) {
			isPlaying = true;
		}
		else {
			isPlaying = false;
		}
		return isPlaying;
	}

}
