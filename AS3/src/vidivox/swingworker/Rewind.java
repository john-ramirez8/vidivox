package vidivox.swingworker;

import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * This Rewind class is used to rewind the video.
 * @author John Ramirez (jram948)
 * 
 */
public class Rewind extends SwingWorker<Void, Void> {

	private EmbeddedMediaPlayer video;
	
	// Constructor
	public Rewind(EmbeddedMediaPlayer video) {
		this.video = video;
	}
	
	// Skips the video backwards (by 20 milliseconds at a time)
	@Override
	protected Void doInBackground() throws Exception {
		while (!isCancelled()) {
			video.skip(-20);
			Thread.sleep(1);
		}
		return null;
	}

}
