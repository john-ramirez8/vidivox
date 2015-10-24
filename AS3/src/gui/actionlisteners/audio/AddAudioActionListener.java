package gui.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.ProgressBar;
import gui.VideoWindow;
import helpers.FileOpener;
import helpers.WindowManager;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class AddAudioActionListener implements ActionListener {

	private final FileOpener mp3Opener;
	private final FileOpener videoOpener;
	private final WindowManager wm;
	private JPanel contentPane;
	private String vidPath;
	private VideoWindow vw;
	private ProgressBar progressBar;
	private EmbeddedMediaPlayer video;
	
	public AddAudioActionListener(JPanel contentPane, String vidPath, VideoWindow vw, EmbeddedMediaPlayer video) {
		this.contentPane = contentPane;
		this.vidPath = vidPath;
		this.vw = vw;
		this.video = video;
		
		mp3Opener = new FileOpener(".mp3", this.vw);
		videoOpener = new FileOpener(".avi", this.vw);
		wm = new WindowManager(this.contentPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File mp3 = mp3Opener.openFile();
		
		if (mp3 != null) {
			JOptionPane.showMessageDialog(contentPane, "Now choose the name and where to save the file");
			String newVidName = videoOpener.saveFile();
			
			if (newVidName != null) {
				progressBar = new ProgressBar(vidPath, mp3, newVidName, video);
				wm.openWindow(progressBar);
			}
		}

	}

}
