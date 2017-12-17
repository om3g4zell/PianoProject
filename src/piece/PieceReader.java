package piece;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class PieceReader {

	private Piece piece;
	private Sequencer sequencer;
	
	public PieceReader() {
		try {
			sequencer = MidiSystem.getSequencer();
			
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() {
		try {
			sequencer.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void loadPiece(Piece p) {
		this.piece = p;
		try {
			sequencer.setSequence(p.getSequence());
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void StartMusic() {
		sequencer.setMicrosecondPosition(0);
		sequencer.start();
	}
	
	public void StopMusic() {
		sequencer.stop();
	}
	
	public void stop() {
		sequencer.close();
	}
}
