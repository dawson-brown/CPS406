import java.util.Scanner;

public class Main {
    /*
     *
     * This is a temporary Command line tool used to interact with the system until there is a GUI
     * At the moment, this tool doesn't make use of the Account class api--only Playlist and Song
     * See below for usage
     *
     */

    public static String[] simpleParse(String command, String input){
        String[] parsed;

        parsed = input.split("\\|");

        if (parsed.length != 4) {
            System.out.println("Invalid number of arguments:" + parsed.length);
            return null;
        }

        return parsed;
    }
    
    public static String[] simpleParsePlaylist(String command, String input){
        String[] parsed;

        parsed = input.split("\\|");

        if (parsed.length != 1) {
            System.out.println("Invalid number of arguments:" + parsed.length);
            return null;
        }

        return parsed;
    }


    /*
    * A simple command line tool for adding, deleting, and printing songs in a playlist
    *
    * Usage: type -?  to see usage
    */
    public static void main(String[] args) {

        Song song;
        Scanner scan = new Scanner(System.in);
        Playlist list = new Playlist();
        String input_string;
        String[] input;
        String command;

        while(true){

            input_string = scan.nextLine();
            command = input_string.substring(0,2>input_string.length() ? input_string.length() : 2);

            if (command.equals("-a") | command.equals("-d")) {
                input = simpleParse(command, input_string.substring(3));

                if (input != null) {
                    song = new Song(input[0], input[1], Integer.parseInt(input[2]), Long.parseLong(input[3]));
                    if (command.equals("-a")){
                        list.add(song);
                        System.out.println("Song added");
                    } else {
                        list.delete(song);
                        System.out.println("Song deleted");
                    }
                }
                

            }
            else if(command.equals("-s")) {
            	input= simpleParsePlaylist(command, input_string.substring(0));
            	if(input != null) {
            		if(input[0].equals("SortByYearOldtoNew")) {
            			list.sortByYearOldtoNew();
            		}
            		else if(input[0].equals("MostRecent")) {
            			list.sortByYearNewtoOld();
            		}
            		else if(input[0].equals("SortByYearArtist")) {
            			list.sortByArtist();
            		}
            		else if(input[0].equals("SortAlphabetically")) {
            			list.sortByName();
            		}
            		else if(input[0].equals("SortShortestToLongest")) {
            			list.sortByLength();
            		}
            		else {
            			System.out.println("Sorting playlist unssuccesful, please enter valid argument");
            		}
            	}
            	
            	
            }
            else if (command.equals("-p")) {
                System.out.println(list.toString());
            } else if (command.equals("-?")){
                System.out.println("Usage:\n-p   --to print\n-q   --to quit\n" +
                        "-a song name|artist name|year|length   --to add a song\n" +
                        "-d song name|artist name|year|length   --to delete a song\n" + 
                        "-s sort by		--to sort playlist" +
                        "-?   --to this usage message."
                );
            } else if (command.equals("-q")) {
                break;
            } else {
                System.out.println("Invalid arguments.");
            }


        }
    }
}