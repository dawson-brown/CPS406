import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SirenRecords {

    private Account current_account;
    private boolean signed_in;
    private File account_file;

    public SirenRecords(){
        current_account = null;
        signed_in = false;
        account_file = new File("accounts");
        try {
            account_file.createNewFile();
        } catch (Exception e){
            System.out.println("Error.");
            System.exit(-1);
        }
    }

    public Account currentAccount(){
        return current_account;
    }

    public boolean signedIn(){
        return signed_in;
    }

    /*
     * signup with the given credentials. This methods calculates a SHA-256 hash for the password, and then appends
     * username:hash to the account_file. This method will not allow duplicate usernames.
     * @param username the username to be added
     * @param password the user's password
     * @return true for success, false for failure
     */
    public boolean signup(String username, String password){
        PrintWriter oFile;
        MessageDigest digest;
        String exists = findEntry(username);

        if (exists == null) {
            try {
                oFile = new PrintWriter(new FileWriter(this.account_file, true));
            } catch (Exception e) {
                return false;
            }

            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                return false;
            }
            String hash_password = hexString(digest.digest(password.getBytes(StandardCharsets.UTF_8)));

            oFile.append(username + ":" + hash_password + "\n");

            oFile.close();

            signed_in = true;
            current_account = new Account(username);

            return true;
        }
        return false;
    }

    /*
    * do a linear search of the account_file for a given username
    * @param username the username to search for
    * @return the account_file (username:hash) entry, otherwise null
    */
    public String findEntry(String username){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(account_file));
        } catch (FileNotFoundException e){
            return null;
        }

        String line;

        try {
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username)){
                    return line;
                }
            }
        } catch (IOException e){
            return null;
        }

        return null;
    }


    /*
    * login using the provided credentials. This method will search account_file for a matching username
    * to the one provided. If one is found, it will calculate the SHA-256 digest for the given password
    * and compare that digest to the digest stored in account_file
    * @param username the username to search for
    * @param password the plaintext password to be hashed and compared
    * @return true on succes, false on failure
    */
    public boolean login(String username, String password){
        String t_entry = findEntry(username);
        boolean login = false;
        MessageDigest digest;

        if (t_entry != null) {
            String[] entry = t_entry.split(":");
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e){
                return false;
            }
            String hash_password = hexString(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
            login =  hash_password.equals(entry[1]);
            if (login) {
                current_account = new Account(username);
                signed_in = true;
                return true;
            }
        }

        return false;
    }

    public void logout(){
        signed_in = false;
        current_account = null;
    }

    /*
    * create a string of hexadecimal characters
    * @param a string of bytes
    * @return the hex string
    */
    private static String hexString(byte[] bytes){
        StringBuilder hexString = new StringBuilder("");

        for (int i=0; i<bytes.length; i++){
            hexString.append(String.format("%02x", bytes[i]));
        }

        return hexString.toString();
    }

    /*
    * below are accessor methods for methods in Account.java. The SirenRecords class acts a
    * middle man between a user and their account. All user actions go through SirenRecords
    */
    public void addPlaylist(String playlist){ current_account.addPlaylist(playlist); }
    public boolean addToPlayList(Song song) { return current_account.addToPlayList(song); }
    public void removeFromPlayList(Song song) { current_account.removeFromPlayList(song); }
    public String printPlayList() {
        if (current_account.current_list == null)
            return "None Selected.";
        return "Playlist:\n" + current_account.printPlayList();
    }
    public String printPlayLists() {
        if (current_account.playlistsSize()==0){
            return "None.";
        }
        return "Playlists:\n" + current_account.printPlayLists();
    }
    public boolean selectPlayList(String playlist_name) { return current_account.selectPlayList(playlist_name); }
}