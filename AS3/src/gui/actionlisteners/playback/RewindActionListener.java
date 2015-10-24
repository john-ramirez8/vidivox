package gui.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import swingworker.Rewind;
import gui.VideoWindow;
import helpers.Main;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class RewindActionListener implements ActionListener {

	private JButton btnPlay;
	private EmbeddedMediaPlayer video;
	private VideoWindow vw;
	
	public RewindActionListener(JButton btnPlay, EmbeddedMediaPlayer video, VideoWindow vw) {
		this.btnPlay = btnPlay;
		this.video = video;
		this.vw = vw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		vw.setEnableAudioCont(false);
		if (vw.playbackStatus.equals("normal")) {
			vw.setEnableButtons(false);
			
			vw.playbackStatus = "rw";
			video.pause();
			video.mute(true);
			vw.rwTask = new Rewind(video);
			vw.rwTask.execute();
			
			btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
		} else if (vw.playbackStatus.equals("ff")) {
			vw.playbackStatus = "rw";
			video.mute(true);
			vw.rwTask = new Rewind(video);
			vw.ffTask.cancel(true);
			vw.rwTask.execute();
		}
		
	}

}
