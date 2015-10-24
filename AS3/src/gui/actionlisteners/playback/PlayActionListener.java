package gui.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import helpers.Main;
import gui.VideoWindow;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class PlayActionListener implements ActionListener {

	private EmbeddedMediaPlayer video;
	private JButton btnPlay;
	private VideoWindow vw;
	
	public PlayActionListener(EmbeddedMediaPlayer video, JButton btnPlay, VideoWindow vw) {
		this.video = video;
		this.btnPlay = btnPlay;
		this.vw = vw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (video.isPlaying()) {
			btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
		} else {
			btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));
			if (vw.playbackStatus.equals("ff")) {
				vw.setEnableButtons(true);
				
				vw.ffTask.cancel(true);
				if (!vw.volumeStatus.equals("muted")) {
					video.mute(false);
				}
				vw.playbackStatus = "normal";
				vw.setEnableAudioCont(true);
			} else if (vw.playbackStatus.equals("rw")) {
				vw.setEnableButtons(true);
				
				vw.rwTask.cancel(true);
				if (!vw.volumeStatus.equals("muted")) {
					video.mute(false);
				}
				vw.playbackStatus = "normal";
				vw.setEnableAudioCont(true);
			}
		}
		video.pause();
		
	}

}
