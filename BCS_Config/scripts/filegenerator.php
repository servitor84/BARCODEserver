<?php

//----------------------- START OF CONFIGURATION ------------------------------

//number of files to generate
$fileCount = 10;

//filename prefix
$filePrefix = "test";

//output directory
$outputDir = '/home/alex/java/ArivatoXMLImporter/importer/input';

/**
 * Path of the test file
 */
$inputFile = '/home/alex/java/ArivatoXMLImporter/scripts/test.tif';

/**
 * Path to template file
 */
$templateFile = '/home/alex/java/ArivatoXMLImporter/scripts/template.xml';

/**
 * Path to signature file
 */
$signatureFile = '/home/alex/java/ArivatoXMLImporter/scripts/test.pkcs7';

/**
 * Path to log file
 */
$protocolFile = '/home/alex/java/ArivatoXMLImporter/scripts/test.log';

//name of the ELO input mask
$mask = "Testmaske";

$useSignalFile = true;

$randomInputFile = true;

$useSignatureFile = true;

$useProtocolFile = true;

//index values
$indexList[] = array("name" => "1", "value" => "XML-Wert1");
$indexList[] = array("name" => "2", "value" => "XML-Wert2");
$indexList[] = array("name" => "3", "value" => "XML-Wert3");

//destination values
#$destList[] = array("value" => "¶test¶test2");
$destList[] = array("value" => "¶[test]¶[test2]", "type" => "Testmaske");
$destList[] = array("value" => "¶[test]");
$destList[] = array("value" => "¶[test]¶[test3]", "type" => "Testmaske");

$recipientList = array("webmaster@theghost19.de");

//---------------------- END OF CONFIGURATION ---------------------------------

//---------------------- START OF SCRIPT --------------------------------------


/** DO NOT EDIT THIS PART OF THE SCRIPT! */

$template = file_get_contents($templateFile);

$filename = basename($inputFile);
$pos = strrpos($filename, '.');

$ext = substr($filename, $pos+1);
$filename = substr($filename, 0, $pos);

for($idx = 1; $idx <= $fileCount; $idx++)
{
	$index = sprintf("%1\$d_%2\$06d", time(), $idx);
//  $index = sprintf("%2\$06d", time(), $idx);
	$filepath = $outputDir.DIRECTORY_SEPARATOR.$filename.$index.'.'.$ext;
	$vars = array("#{index}", "#{xdate}", "#{mask}", "#{filepath}");
	$values = array($index, date("Ymd"), $mask, $filepath);

	$listContent = '';

	foreach($indexList as $indexData)
	{
		$listContent .= '<index name="'.$indexData['name'].'" value="'.$indexData['value']."\" />\n";
	}

	$vars[] = "#{indexlist}";
	$values[] = $listContent;

	$listContent = '';

	foreach($destList as $destData)
	{
		if ( !empty($destData['type']) )
    	$listContent .= '<destination value="'.$destData['value'].'" type="'.$destData['type']."\"/>\n";
		else
			$listContent .= '<destination value="'.$destData['value']."\" />\n";
	}

	$vars[] = "#{destlist}";
	$values[] = $listContent;

  $listContent = '';

  foreach($recipientList as $email) {
    $listContent .= "<email>$email</email>\n";
  }
  
  $vars[] = "#{recipientlist}";
  $values[] = $listContent;

	$content = str_replace($vars, $values, $template);

	$outputfile = $outputDir.DIRECTORY_SEPARATOR.$filePrefix.$index.'.xml';

 	file_put_contents($outputfile, $content);

  if ( $useSignalFile )
  {
  	$sigfile = $outputDir.DIRECTORY_SEPARATOR.$filePrefix.$index.'.sig';
    touch($sigfile);
  }

  if ( $useSignatureFile ) {
    copy($signatureFile, $outputDir.DIRECTORY_SEPARATOR.$filename.$index.'.pkcs7');
  }

  if ( $useProtocolFile ) {
    copy($protocolFile, $outputDir.DIRECTORY_SEPARATOR.$filename.$index.'.log');
  }

	if ( $randomInputFile ) {
		$content ='';
		for($i=0; $i < 50; $i++) {
			$content .= uniqid($filePrefix, true)."\n";
		}
		file_put_contents($filepath, $content);
	} else {
	 	copy($inputFile, $filepath);
	}

//  	echo "$filepath\n";

// 	echo "$outputfile\n";
}

//---------------------- END OF SCRIPT ----------------------------------------

?>
