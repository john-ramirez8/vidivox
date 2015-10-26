package vidivox.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import vidivox.Main;
import vidivox.gui.VideoWindow;
import vidivox.swingworker.Rewind;

/**
 * This class is used to invoke the rewinding of the video.
 * @author John Ramirez (jram948)
 *
 */
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
			vw.playbackStatus = "rw";
			video.pause();
			video.mute(true);
			
			vw.rwTask = new Rewind(video);
			vw.rwTask.execute(); //Rewinds the video	
			vw.setEnableButtons(false);

			btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
		} else if (vw.playbackStatus.equals("paused")) {
			vw.playbackStatus = "rw";
			
			video.mute(true);
			vw.rwTask = new Rewind(video);
			vw.rwTask.execute();
			vw.setEnableButtons(false);
		} else if (vw.playbackStatus.equals("ff")) {
			vw.playbackStatus = "rw";
			
			video.mute(true);
			vw.rwTask = new Rewind(video);
			vw.ffTask.cancel(true); //Cancels the fast forwarding
			vw.rwTask.execute();
		}
		
	}

}
