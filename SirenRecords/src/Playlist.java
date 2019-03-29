import java.util.*;

public class Playlist {

    LinkedList<Song> playlist;

    public Playlist(){
        playlist = new LinkedList<Song>();
    }

    public boolean add(Song song){
        return this.playlist.add(song);
    }

    public boolean delete(Song song) { return this.playlist.remove(song); }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("Playlist:\n");
        for(int i=0; i<this.playlist.size(); i++){
            string.append(this.playlist.get(i).toString());
            string.append("\n");
        }
        return string.toString();
    }
}
