package gui.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.VideoWindow;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * This class mutes/unmutes the video.
 * @author John Ramirez (jram948)
 *
 */
public class MuteActionListener implements ActionListener {

	private EmbeddedMediaPlayer video;
	private VideoWindow vw;
	
	public MuteActionListener(EmbeddedMediaPlayer video, VideoWindow vw) {
		this.video = video;
		this.vw = vw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//If video is unmuted, mute it, else unmute it.
		if (vw.volumeStatus.equals("unmuted")) {
			video.mute(true);
			vw.volumeStatus = "muted";
		} else {
			video.mute(false);
			vw.volumeStatus = "unmuted";
		}
	}

}
