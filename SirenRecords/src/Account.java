import java.util.HashMap;

public class Account {

    String name;
    HashMap<String, Playlist> playlists;

    public Account(String name){
        this.name = name;
        playlists = new HashMap<String, Playlist>();
    }

    /*
    * adds a new, empty playlist to the users hashtable of playlists
    * @param playlist_name is used in the name of the playlist (username+playlist_name)
    */
    public void addPlaylist(String playlist_name){
        playlists.put(this.name + playlist_name, new Playlist());
    }

    /*
    * return a playlist of a given name
    * @param playlist_name the name of the playlist to be accessed
    * @return the playlist if successful, otherwise null
    */
    public Playlist getPlaylist(String playlist_name){
        String code = this.name + playlist_name;
        if (playlists.containsKey(code))
            return playlists.get(code);
        return null;
    }

    /*
    * add a song to the specified playlist
    * @param playlist_name the name of the playlist to modify
    * @param song the song to add to the playlist
    */
    public void addToPlayList(String playlist_name, Song song){
        getPlaylist(playlist_name).add(song);
    }
}

