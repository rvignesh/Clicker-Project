package edu.rutgers;

public class ClickerIdUtil {

	/* from http://code.google.com/p/iclicker-moodle-integrate/source/diff?spec=svn90&r=90&format=side&path=/trunk/iclicker_service.php
	 * 	$p1 = hexdec('0'.$clicker_id{1});
	 *  $p2 = hexdec(substr($clicker_id, 2, 2));
	 *  $p3 = hexdec(substr($clicker_id, 4, 2));
	 *  $p4 = $p1 ^ $p2 ^ $p3;
	 *  $part4 = strtoupper(dechex($p4));
	 *  $part4 = (count($part4) == 1 ? '0'.$part4 : $part4);
	 *  $alternateId = '0' . substr($clicker_id, 1, 5) . $part4;
	 */
	public static String translate(final String id) {
		if(id.length() != 9) {
			throw new RuntimeException("clicker id must be 8 characters - received "+id);
		}
		char firstChar = id.charAt(1);
		
		if(firstChar == '3') {
			int intId = Integer.parseInt(id.substring(1, 9), 16);
			int transId = intId ^ 0x20000020;
			return '#'+Integer.toHexString(transId).toUpperCase();
		}
				
		if(firstChar == '2' || firstChar == '4' || firstChar == '8') {
			int p1 = Integer.parseInt("0"+id.charAt(2), 16);
			int p2 = Integer.parseInt(id.substring(3, 5), 16);
			int p3 = Integer.parseInt(id.substring(5, 7), 16);
			int p4 = p1 ^ p2 ^ p3;
			String part4 = Integer.toHexString(p4).toUpperCase();
			if(part4.length() == 1) {
				part4 = '0' + part4;
			}
			return "#0"+id.substring(2,7)+part4;
		}
		
		return id;
	}

	
	/*	from http://code.google.com/p/iclicker-moodle-integrate/source/browse/trunk/iclicker_service.php:
	 *  $idArray[0] = substr($clicker_id, 0, 2);
	 *  $idArray[1] = substr($clicker_id, 2, 2);
	 *  $idArray[2] = substr($clicker_id, 4, 2);
	 *  $idArray[3] = substr($clicker_id, 6, 2);
	 *  $checksum = 0;
	 *  foreach ($idArray as $piece) {
	 *      $hex = hexdec($piece);
	 *      $checksum = $checksum ^ $hex;
	 *  }
	 *  if ($checksum != 0) {
	 *      throw new ClickerIdInvalidException("clicker_id checksum (" + $checksum + ") validation failed", ClickerIdInvalidException::F_CHECKSUM, $clicker_id);
	 *  }
	 */
	public static boolean isValidClickerId(String id) {
		int p1 = Integer.parseInt(id.substring(1, 3), 16);
		int p2 = Integer.parseInt(id.substring(3, 5), 16);
		int p3 = Integer.parseInt(id.substring(5, 7), 16);
		int p4 = Integer.parseInt(id.substring(7, 9), 16);
		
		int checksum = p1 ^ p2 ^ p3 ^ p4;
		return checksum == 0;
	}
	
	
}
