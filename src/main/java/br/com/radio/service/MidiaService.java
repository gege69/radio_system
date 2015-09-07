package br.com.radio.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaRepository;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

@Component
public class MidiaService {

	@Autowired
	private MidiaRepository midiaRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	
	public Integer saveUpload( MultipartFile file, Long[] categorias ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		String basePath = "/home/pazin/teste/";
		
		byte[] bytes = file.getBytes();
		
		LongHashFunction l = LongHashFunction.xx_r39();
		long hashXX = l.hashBytes( bytes, 0, bytes.length );

		String hash = Long.toString( hashXX );

		File arquivo = null;
		Integer size = 0;
		
		Midia midia = midiaRepo.findByFilehash( hash );
		
		if ( midia == null )
		{
			String path = basePath + file.getOriginalFilename();
			
			arquivo = new File( path );

			size = IOUtils.copy( file.getInputStream(), new FileOutputStream( arquivo ) );
			
			midia = new Midia();
			
			midia.setDataUpload( new Date() );
			midia.setNome( file.getOriginalFilename() );
			midia.setFilepath( path );
			midia.setFilehash( hash );
			midia.setMimetype( "audio/mpeg" );  // file.getContentType()
			midia.setFilesize( size );
			midia.setExtensao( FilenameUtils.getExtension( file.getOriginalFilename() ) );
			midia.setValido( true );
			midia.setCached( false );
		}
		else
		{
			size = midia.getFilesize();
			
			arquivo = new File( midia.getFilepath() );
		}

		atualizaIDTags( midia, arquivo );
		

		if ( categorias != null && categorias.length > 0 )
		{
			List<Long> ids = Arrays.asList( categorias );
			
			List<Categoria> categoriaList = categoriaRepo.findByIdCategoriaIn( ids );
			
			midia.setCategorias( categoriaList );
		}
		midiaRepo.save( midia );
		
		return size;
	}

	private void atualizaIDTags( Midia midia, File arquivo ) throws IOException, UnsupportedTagException, InvalidDataException
	{
		Mp3File mp3File = new Mp3File( arquivo );
		
		if ( mp3File.hasId3v2Tag() )
		{
			ID3v2 id3v2Tag;
			
			id3v2Tag = mp3File.getId3v2Tag();
			
			midia.setTitle( id3v2Tag.getTitle() );
			midia.setArtist( id3v2Tag.getArtist() );
			midia.setAlbum( id3v2Tag.getAlbum() );
			midia.setComment( id3v2Tag.getComment() );
			midia.setDatetag( id3v2Tag.getYear() );
			midia.setGenre( id3v2Tag.getGenreDescription() );
		}
		else if ( mp3File.hasId3v1Tag() )
		{
			ID3v1 id3v1Tag;
			
			id3v1Tag = mp3File.getId3v1Tag();
			
			midia.setTitle( id3v1Tag.getTitle() );
			midia.setArtist( id3v1Tag.getArtist() );
			midia.setAlbum( id3v1Tag.getAlbum() );
			midia.setComment( id3v1Tag.getComment() );
			midia.setDatetag( id3v1Tag.getYear() );
			midia.setGenre( id3v1Tag.getGenreDescription() );
		}
		
	}
	
	
}
