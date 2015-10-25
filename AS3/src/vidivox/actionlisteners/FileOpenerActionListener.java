package vidivox.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import vidivox.gui.VideoWindow;
import vidivox.helpers.FileOpener;

/**
 * This class is used to open up a video file that is going to be played.
 * @author John Ramirez (jram948)
 *
 */
public class FileOpenerActionListener implements ActionListener {

	private FileOpener fo;
	private EmbeddedMediaPlayerComponent mpc;
	private EmbeddedMediaPlayer video;
	private VideoWindow vw;
	private String vidPath;

	public FileOpenerActionListener(FileOpener fo, EmbeddedMediaPlayerComponent mpc,
			EmbeddedMediaPlayer video, VideoWindow vw) {
		this.fo = fo;
		this.mpc = mpc;
		this.video = video;
		this.vw = vw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File vid = fo.openFile();
		
		if (vid != null) {
			mpc.setVisible(true);
			vidPath = vid.getAbsolutePath();
			
			//Cancels any fast forwarding/rewinding
			if (vw.playbackStatus.equals("ff")) {
				vw.ffTask.cancel(true);
				vw.playbackStatus = "normal";
				vw.setEnableAudioCont(true);
			} else if (vw.playbackStatus.equals("rw")) {
				vw.rwTask.cancel(true);
				vw.playbackStatus = "normal";
				vw.setEnableAudioCont(true);
			}
			
			//Plays the video
			video.setRepeat(true);
			video.playMedia(vidPath);
		}
	}

}
