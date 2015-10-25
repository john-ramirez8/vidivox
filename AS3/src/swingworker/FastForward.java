package swingworker;

import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * This FastForward class is used to fast forward the video.
 * @author John Ramirez (jram948)
 * 
 */
public class FastForward extends SwingWorker<Void, Void> {

	private EmbeddedMediaPlayer video;
	
	// Constructor
	public FastForward(EmbeddedMediaPlayer video) {
		this.video = video;
	}
	
	// Skips the video forwards (by 20 milliseconds at a time)
	@Override
	protected Void doInBackground() throws Exception {
		while (!isCancelled()) {
			video.skip(20);
			Thread.sleep(1);
		}
		return null;
	}

}
