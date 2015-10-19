package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import gui.VideoWindow;
import helpers.FileOpener;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class FileOpenerActionListener implements ActionListener {

	private FileOpener fo;
	private EmbeddedMediaPlayerComponent mpc;
	private EmbeddedMediaPlayer video;
	private VideoWindow vw;
	private String vidPath;

	public FileOpenerActionListener(FileOpener fo, EmbeddedMediaPlayerComponent mpc, EmbeddedMediaPlayer video, VideoWindow vw) {
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
			video.playMedia(vidPath);
			if (vw.playbackStatus.equals("ff") == true) {
				vw.ffTask.cancel(true);
				vw.playbackStatus = "normal";
				vw.setEnableAudioCont(true);
			} else if (vw.playbackStatus.equals("rw") == true) {
				vw.rwTask.cancel(true);
				vw.playbackStatus = "normal";
				vw.setEnableAudioCont(true);
			}
		}
	}

}
