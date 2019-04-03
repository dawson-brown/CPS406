public class Song implements java.io.Serializable {
    /*
    * The name of the song
    */
    String name;

    /*
    * the songs year of release
    */
    int year;

    /*
    * the songs artist
    */
    String artist;

    /*
    * the songs length in seconds
    */
    double length;

    public Song(String name, String artist, int year, double length){

        this.name = name;
        this.artist = artist;
        this.year = year;
        this.length = length;

    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("name: ");

        string.append(this.name);
        string.append(", artist: ");
        string.append(this.artist);
        string.append(", year: ");
        string.append(this.year);
        string.append(", length: ");
        string.append(this.length);

        return string.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Song)) {
            return false;
        }

        Song o_song = (Song) o;

        return o_song.name.equals(this.name) &&
                o_song.artist.equals(this.artist) &&
                o_song.year == this.year &&
                o_song.length == this.length;
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 17 * result + this.name.hashCode();
        result = 17 * result + this.artist.hashCode();
        result = 17 * result + this.year;
        result = 17 * result + (int)this.length;
        return result;
    }

}
