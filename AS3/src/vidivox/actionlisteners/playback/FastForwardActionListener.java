package vidivox.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import vidivox.Main;
import vidivox.gui.VideoWindow;
import vidivox.swingworker.FastForward;

/**
 * This class is used to invoke the fast forwarding of the video.
 * @author John Ramirez (jram948)
 *
 */
public class FastForwardActionListener implements ActionListener {

	private EmbeddedMediaPlayer video;
	private JButton btnPlay;
	private VideoWindow vw;
	
	public FastForwardActionListener(EmbeddedMediaPlayer video, JButton btnPlay, VideoWindow vw) {
		this.video = video;
		this.btnPlay = btnPlay;
		this.vw = vw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		vw.setEnableAudioCont(false);
		
		if (vw.playbackStatus.equals("normal")) {
			vw.setEnableButtons(false);

			vw.playbackStatus = "ff";			
			video.pause();
			video.mute(true);
			vw.ffTask = new FastForward(video);
			vw.ffTask.execute(); //Fast forward the video
			
			btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
		} else if (vw.playbackStatus.equals("rw")) {
			vw.playbackStatus = "ff";
			
			video.mute(true);
			vw.ffTask = new FastForward(video);
			vw.rwTask.cancel(true); //Cancels the rewinding
			vw.ffTask.execute();
		}
		
	}

}
