package synthetizer;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


public class SynthetizerManager {
	
	private Synthesizer synthe;
	
	private MidiChannel[] channels;
	
	public  SynthetizerManager() {
		try {
			synthe = MidiSystem.getSynthesizer();
			channels = synthe.getChannels();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		if(!synthe.isOpen())
			try {
				synthe.open();
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
	}
	
	public void playNote(int note, int velocity, int channel, int time) {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				channels[channel].noteOn(note, velocity);
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				channels[channel].noteOff(note);
			}
		});
		th.start();
	}
	
	public void playNote(int note, int channel) {
		channels[channel].noteOn(note, 1000);
	}
	
	public void stopNote(int note, int channel) {
		channels[channel].noteOff(note);
	}
	
	
	public void changeInstrument(int channel, int instrument) {
		
		if(channels[channel].getProgram() != instrument)
			channels[channel].programChange(instrument);
	}
	
	public void close() {
		if(synthe.isOpen()) synthe.close();
	}
}
