package net.chiappone.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kurtis Chiappone
 * @date 10/9/2016
 */
public class UnixUtil {

	private static final Logger logger = LoggerFactory.getLogger( UnixUtil.class );

	/**
	 * @param dfResult
	 * @return
	 */
	public static Map<String, String> parseDiskSpace( String dfResult ) {

		Map<String, String> map = new TreeMap<String, String>();

		try {

			BufferedReader reader = new BufferedReader( new StringReader( dfResult ) );
			String line;

			while ( ( line = reader.readLine() ) != null ) {

				String[] split = line.split( "\\s+" );

				if ( split.length >= 5 ) {

					String percentage = split[ 4 ].replace( "%", "" );
					String name = split[ 5 ];
					map.put( name, percentage );

				}

			}

		} catch ( IOException e ) {

			logger.error( "Error parsing df result", e );

		}

		return map;

	}

	/**
	 * @param duResult
	 * @return
	 */
	public static String parseDiskUsage( String duResult ) {

		String result = duResult;

		if ( result == null || result.isEmpty() ) {

			return "0";

		}

		Pattern p = Pattern.compile( "(\\d+).*" );
		Matcher m = p.matcher( duResult );

		if ( m.matches() ) {

			result = m.group( 1 );

		}

		return result;

	}

	/**
	 * @param lsResult
	 * @return
	 */
	public static String parseFileSize( String lsResult ) {

		String size = "0";

		if ( lsResult != null ) {

			String[] split = lsResult.split( "\\s+" );

			if ( split.length >= 4 ) {

				size = split[ 4 ];

			}

		}

		return size;

	}

}
