package br.com.radio.dto;

import br.com.radio.model.Midia;


public class MusicTags {
	
	public int trackLength = 0;
	public String title;
	public String artist;
	public String album;
	public String comment;
	public String date;
	public String genre;
	
	public void copyToMidia( Midia midia )
	{
		if ( trackLength == 0 )
			trackLength = 1;

		midia.setDuracao( trackLength );

		midia.setTitle( title );
		midia.setArtist( artist == null ? "" : artist );
		midia.setAlbum( album );
		midia.setComment( comment );
		midia.setDatetag( date );
		midia.setGenre( genre );
	}
	
}
