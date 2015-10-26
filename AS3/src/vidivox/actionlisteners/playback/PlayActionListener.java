package vidivox.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import vidivox.Main;
import vidivox.gui.VideoWindow;

/**
 * This class is used to play/pause the video.
 * @author John Ramirez (jram948)
 *
 */
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
		//Changes the icon of the button
		if (video.isPlaying()) {
			btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
			vw.playbackStatus = "paused";
		} else {
			btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));
			
			//If video is fast forwarding, cancels this process
			if (vw.playbackStatus.equals("ff")) {
				vw.setEnableButtons(true);
				
				vw.ffTask.cancel(true);
				if (!vw.volumeStatus.equals("muted")) {
					video.mute(false);
				}
				vw.setEnableAudioCont(true);
	
			//If video is rewinding, cancels this process
			} else if (vw.playbackStatus.equals("rw")) {
				vw.setEnableButtons(true);
				
				vw.rwTask.cancel(true);
				if (!vw.volumeStatus.equals("muted")) {
					video.mute(false);
				}
				vw.setEnableAudioCont(true);
			}
			vw.playbackStatus = "normal";
		}
		video.pause();
		System.out.println("playbackStatus = " + vw.playbackStatus);
	}

}
