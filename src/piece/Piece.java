package piece;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import piano.Key;

public class Piece {
	
	private ArrayList<MyTrack> tracks;
	private float resolution;
	private float BPM;
	private float tickPerSec;
	private long tickLengh;
	private Sequence sequence;
	
	public Piece() {
		tracks = new ArrayList<>();
	}
	
	public void loadPieceFromMidi(String path) {
		try {
			sequence = MidiSystem.getSequence(new File(path));
			
			this.resolution = sequence.getResolution();
			this.tickLengh = sequence.getTickLength();
			
	        int trackNumber = 0;
	        for (Track track :  sequence.getTracks()) {
	        	tracks.add(new MyTrack(trackNumber));
	            for (int i = 0; i < track.size(); i++) { 
	            		
	                MidiEvent event = track.get(i);
	                long tick = event.getTick();
	                int channelNumber;
	                MidiMessage message = event.getMessage();
	                
	                if (message instanceof ShortMessage) {
	                	
	                    ShortMessage sm = (ShortMessage) message;
	                    channelNumber =  sm.getChannel();
	                    if(getChannel(trackNumber, channelNumber) == null) {
	                    	tracks.get(trackNumber).addChannel(new Channel(channelNumber));
	                    }
	                    Channel channel = getChannel(trackNumber, channelNumber);
	                    
	                    if (sm.getCommand() == ShortMessage.NOTE_ON) {
	                    	
	                        int key = sm.getData1();
	                        int velocity = sm.getData2();
	                        
	                        if(velocity == 0) {
	                        	for(Key k : channel.getNotes()) {
	                        		if(k.getTickOff() != 0) continue;
	                        		if(k.getValue() == key) {
	                        			k.setTickOff(tick);
	                        		}
	                        	}
	                        }
	                        else {
	                        	channel.addKey(new Key(key, tick, velocity));
	                        }
	                    } 
	                    else if (sm.getCommand() == ShortMessage.NOTE_OFF) {
	                    	int key = sm.getData1();	                     
	                      
                        	for(Key k : channel.getNotes()) {
                        		if(k.getTickOff() != 0) continue;
                        		if(k.getValue() == key) {
                        			k.setTickOff(tick);
                        		}
	                        }
	                    } 
	                    else if(sm.getCommand() == ShortMessage.PROGRAM_CHANGE) {	   
	                    	if(getChannel(trackNumber, sm.getData1()) == null) {
		                    	tracks.get(trackNumber).addChannel(new Channel(sm.getData1()));
		                    }
	                    	getChannel(trackNumber, sm.getData1()).setIntrument(sm.getData2());	 
	                    	System.out.println("Change instrument Channel : " + sm.getData1() + " Intru : " + sm.getData2());
	                    	
	                    }
	                }
	               
	            }
	            trackNumber++;
	        }
	        
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Sequence getSequence() {
		return this.sequence;
	}
	
	public Channel getChannel(int trackId, int channelId) {
		for(Channel c : getTrack(trackId).getChannels()) {
			if(c.getNumber() == channelId) return c;
		}
		return null;
	}
	
	public MyTrack getTrack(int trackid) {
		for(MyTrack t : tracks) {
			if(t.getTrackNumber() == trackid) return t;
		}
		return null;
	}
	
	public String toString() {
		String str = "";
		 str += "Resolution : " + resolution + "\n";
		 str += "Size : " + tickLengh + "\n";
		 for(MyTrack t : tracks) {
			 for(Channel c : t.getChannels()) {
				 for(Key key : c.getNotes()) {
					 str += key + "\n";
				 }
			 }
		 }
		 return str;
	}
}
