package swingworker;

import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class FastForward extends SwingWorker<Void, Void> {

	private EmbeddedMediaPlayer video;
	
	// Constructor
	public FastForward(EmbeddedMediaPlayer video) {
		this.video = video;
	}
	
	// Skips the video forwards (by 20 milliseconds)
	@Override
	protected Void doInBackground() throws Exception {
		while (!isCancelled()) {
			video.skip(20);
			Thread.sleep(1);
		}
		return null;
	}

}
