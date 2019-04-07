import java.util.*;
import static org.junit.Assert.*;				
import org.junit.Test;

public class Playlist implements java.io.Serializable {
	
	public static final long OneHOUR = (long) 3600;
	public static final long ThreeHOUR = (long) 10800;
	
    LinkedList<Song> playlist;
    Long playlistLength;
    
    public Playlist(){
        playlist = new LinkedList<Song>();
        playlistLength = (long)0;
 
    }

    public boolean add(Song song){
    	this.playlistLength += song.getLength();
        return this.playlist.add(song);     
    }
    
    /*
     * Gets the most recent(last) song added to the playlist
     */
  public Song getRecentSong() {
	  return this.playlist.getLast();
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
    public boolean searchPlaylist(Playlist playlist) {
    	if(playlist != null)
    		return true;
    	else
    		return false;
    }
    
    /*
     * Checks to see if length of playlist is between 1 to 3 hours
     * @return return false if not return true if successful
     */
    public boolean getPlaylistLength() {
    	return (this.playlistLength >= OneHOUR && this.playlistLength <= ThreeHOUR) ? true : false;
    }
    
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("Playlist:\n");
        for(int i=0; i<this.playlist.size(); i++){
            string.append(this.playlist.get(i).toString());
            string.append("\n");
        }
        return string.toString();
    }
    /*
     * Testers for testing each methods of our playlist object
     */
    @Test
    public void testLinkedList() {
    	Playlist playlist = new Playlist();
    	assertEquals(null,playlist.playlist.get(0));
    }
    public void testAdd() {
    	Playlist list = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	list.add(song);
    	assertEquals(song, list.getRecentSong());
    }
    public void testDelete() {
    	Playlist list = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("Test2", "TestArtist2", 2020, 570);
    	list.add(song);
    	list.add(song2);
    	list.delete(song2);
    	assertEquals(song, list.getRecentSong());
    }
    public void testGetPlaylistLength() {
    	Playlist list = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("ATest2", "TestArtist2", 2020, 570);
    	list.add(song);
    	list.add(song2);
    	assertEquals(false, list.getPlaylistLength()); 	
    }
    public void testSortByName() {
    	Playlist list = new Playlist();
    	Playlist sortedList = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("ATest2", "TestArtist2", 2020, 570);
    	list.add(song);
    	list.add(song2);
    	sortedList.add(song2);
    	sortedList.add(song);
    	list.sortByName();
    	assertEquals(sortedList,list);
    }
    public void testSortByArtist() {
    	Playlist list = new Playlist();
    	Playlist sortedList = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("ATest2", "ATestArtist2", 2020, 570);
    	list.add(song);
    	list.add(song2);
    	sortedList.add(song2);
    	sortedList.add(song);
    	list.sortByArtist();
    	assertEquals(sortedList,list);
    }
    public void testSortByYearOldtoNew() {
    	Playlist list = new Playlist();
    	Playlist sortedList = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("ATest2", "ATestArtist2", 2018, 570);
    	list.add(song);
    	list.add(song2);
    	sortedList.add(song2);
    	sortedList.add(song);
    	list.sortByYearOldtoNew();
    	assertEquals(sortedList,list);
    }
    public void testSortByYearNewtoOld() {
    	Playlist list = new Playlist();
    	Playlist sortedList = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("ATest2", "ATestArtist2", 2018, 570);
    	list.add(song);
    	list.add(song2);
    	sortedList.add(song);
    	sortedList.add(song2);
    	list.sortByYearNewtoOld();
    	assertEquals(sortedList,list);
    }
    public void testSortByLength() {
    	Playlist list = new Playlist();
    	Playlist sortedList = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("ATest2", "ATestArtist2", 2018, 570);
    	list.add(song2);
    	list.add(song);
    	sortedList.add(song);
    	sortedList.add(song2);
    	list.sortByLength();
    	assertEquals(sortedList,list);
    }
    public void testToString() {
    	Playlist list = new Playlist();
    	Song song = new Song("Test", "TestArtist", 2019, 560);
    	Song song2 = new Song("ATest2", "ATestArtist2", 2018, 570);
    	list.add(song);
    	list.add(song2);
    	StringBuilder playlistAsString = new StringBuilder(song.toString()); 
    	playlistAsString.append("\n");
    	playlistAsString.append(song2.toString());
    	assertEquals(playlistAsString,list.toString());
    }

}