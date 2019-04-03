import java.util.*;

public class Playlist implements java.io.Serializable {

    LinkedList<Song> playlist;

    public Playlist(){
        playlist = new LinkedList<Song>();
    }

    public boolean add(Song song){
        return this.playlist.add(song);
    }
    
    /*
     * Sort playlist based on Song's name
     */
    public void sortByName() {
    	Collections.sort(this.playlist, Song.SongNameComparator);
    }
    
    /*
     * Sort playlist based on Artists name
     */
    public void sortByArtist() {
    	Collections.sort(this.playlist, Song.SongArtistComparator);
    }
    
    /*
     * Sort playlist from oldest to newest songs based on year of production
     */
    public void sortByYearOldtoNew() {
    	Collections.sort(this.playlist, Song.SongYearComparatorOtoN);
    }
    
    /*
     * Sort playlist from newest to oldest songs based on year of production
     */
    public void sortByYearNewtoOld() {
    	Collections.sort(this.playlist, Song.SongYearComparatorNtoO);
    }
    
    /*
     * Sort playlist based on songs length
     */
    public void sortByLength() {
    	Collections.sort(this.playlist, Song.SongLengthComparator);
    }
    
    /*
     * Remove song from current playlist
     */
    public boolean delete(Song song) { 
    	return this.playlist.remove(song);
    	}

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(int i=0; i<this.playlist.size(); i++){
            string.append(this.playlist.get(i).toString());
            string.append("\n");
        }
        return string.toString();
    }
}
